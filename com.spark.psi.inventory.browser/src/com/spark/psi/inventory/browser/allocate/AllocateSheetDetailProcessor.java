package com.spark.psi.inventory.browser.allocate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.SetPSICaseUtil;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;
import com.spark.psi.publish.inventory.key.GetAvailableCountKey;
import com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask;
import com.spark.psi.publish.inventory.task.InventoryAllocateTask;
import com.spark.psi.publish.inventory.task.UpdateInventoryAllocateSheetTask;
import com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask.AllocateStockItem;

/**
 * 库存调拨单编辑和查看界面处理器
 */
@Deprecated
public class AllocateSheetDetailProcessor extends
		SimpleSheetPageProcessor<InventoryAllocateSheetInfo.AllocateGoodsItem>
		implements IDataProcessPrompt {

	public final static String ID_List_SourceStore = "List_SourceStore";
	public final static String ID_List_TargetStore = "List_TargetStore";
	public final static String ID_Button_SelectGoods = "Button_SelectGoods";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Submit = "Button_Submit";
	public final static String ID_Button_Approval = "Button_Approval";
	public final static String ID_Button_Deny = "Button_Deny";
	public final static String ID_Button_AllocateOut = "Button_AllocateOut";
	public final static String ID_Button_AllocateIn = "Button_AllocateIn";
	public final static String ID_Text_Cause = "Text_Cause";

	public final static String ID_HeaderLeftArea = "headerLeftArea";
	public final static String ID_HeaderRightArea = "headerRightArea";
	public final static String ID_FooterLeftArea = "footerLeftArea";
	public final static String ID_FooterRightArea = "footerRightArea";

	public static enum Columns {
		code, number, name, spec, unit,	availableCount,	allocateCount
	}

	private Button selectGoodsButton;
	private Button saveButton;
	private Button submitButton;
	private Button approvalButton;
	private Button denyButton;
	private Button allocateOut;
	private Button allocateIn;

	// private Text causeText;

	private LWComboList sourceStoreList;
	private LWComboList targetStoreList;

	GUID sourceStoreId;

	private String sheetId = null;
	private InventoryAllocateSheetInfo info = null;

	private Set<String> rowIds;

	public void process(Situation context) {
		super.process(context);

		initControls();
		initButton();
		registerValidator();
	}

	private void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "调拨材料不能为空";
				}

				List<AllocateStockItem> itemList = new ArrayList<AllocateStockItem>();
				String[] rowIds = table.getAllRowId();
				for (int i = 0; i < rowIds.length; i++) {
					String rowId = rowIds[i];
					String[] values = table.getEditValue(rowId,
							Columns.allocateCount.name());
					if (CheckIsNull.isNotEmpty(values[0])
							&& Double.valueOf(values[0]) > 0) {
						itemList.add(new AllocateStockItem(GUID.valueOf(rowId),
								Double.valueOf(values[0])));
					}
				}
				if (CheckIsNull.isEmpty(itemList)) {
					return "请填写调拨材料数量";
				}
				return null;
			}

			@Override
			protected String validateCell(String rowId, String columnName) {
				String[] values = table.getEditValue(rowId,
						Columns.allocateCount.name());
				if (CheckIsNull.isNotEmpty(values[0])
						&& Double.valueOf(values[0]) > 0
						&& info != null
						&& !InventoryAllocateStatus.Submitting.equals(info
								.getStatus())) {
					String[] columnValue = table.getEditValue(rowId,
							Columns.allocateCount.name());
					String goodsName = table.getExtraData(rowId,
							Columns.name.name())[0];

					String availableCount = table.getExtraData(rowId,
							Columns.availableCount.name())[0] == "" ? "0"
							: table.getExtraData(rowId,
									Columns.availableCount.name())[0];
					if (DoubleUtil.sub(Double.valueOf(columnValue[0]),
							getAvailableCount(rowId)) > 0) {
						return "材料：" + goodsName + "，超出可用库存数量";
					}
				}
				return null;
			}
		});
	}

	private void initButton() {
		Composite sheetButtonArea = this.createControl(ID_FooterRightArea,
				Composite.class);
		GridData gd = new GridData();
		gd.widthHint = 80;
		gd.heightHint = 28;
		if (CheckIsNull.isEmpty(info)
				|| InventoryAllocateStatus.Submitting.equals(info.getStatus())
				|| InventoryAllocateStatus.Rejected.equals(info.getStatus())) {

			submitButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
			submitButton.setID(ID_Button_Submit);
			submitButton.setText(" 提交申请 ");
			submitButton.setLayoutData(gd);
			new Label(sheetButtonArea).setText(" ");
			saveButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
			saveButton.setID(ID_Button_Save);
			saveButton.setText("   保存   ");
			saveButton.setLayoutData(gd);

			selectGoodsButton = this.createControl(ID_Button_SelectGoods,
					Button.class);
			if (selectGoodsButton != null) {
				selectGoodsButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MsgRequest request = CommonSelectRequest
								.createSelectGoodsRequest(GUID
										.valueOf(sourceStoreList.getText()));
						request.setResponseHandler(new ResponseHandler() {
							public void handle(Object returnValue,
									Object returnValue2, Object returnValue3,
									Object returnValue4) {
								GoodsItemInfo[] items = (GoodsItemInfo[]) returnValue;
								rowIds = new HashSet<String>();
								for (String rowId : table.getAllRowId()) {
									rowIds.add(rowId);
								}
								for (GoodsItemInfo goods : items) {
									String itemId = goods.getItemData().getId()
											.toString();
									if (!rowIds.contains(itemId)) {
										rowIds.add(itemId);
										AllocateShowItem item = new AllocateShowItem();

										item
												.setAvailableCount(getAvailableCount(itemId));

										item.setStockItemCode(goods
												.getBaseInfo().getCode());
										item.setStockItemId(goods.getItemData()
												.getId());
										item.setStockItemName(goods
												.getBaseInfo().getName());
										// item.setCountDecimal(goods.getItemData().get);
										item.setStockSpec(goods
												.getItemData()
												.getPropertiesWithoutUnit());
										item.setStockItemUnit(goods
												.getItemData()
												.getPropertyValues()[0]);
										table.addRow(item);
									}
								}
								table.renderUpate();
							}
						});
						getContext().bubbleMessage(request);
					}
				});
			}

			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (!validateInput()) {
						return;
					}
					if (!validateStore()) {
						return;
					}
					String[] rowIds = table.getAllRowId();
					AllocateStockItem[] items = new AllocateStockItem[rowIds.length];

					for (int i = 0; i < rowIds.length; i++) {
						String value = table.getEditValue(rowIds[i],
								Columns.allocateCount.name())[0];
						items[i] = new AllocateStockItem(GUID
								.valueOf(rowIds[i]), Double.valueOf(value));
					}
					if (CheckIsNull.isEmpty(sheetId)) {
						CreateInventoryAllocateSheetTask task = new CreateInventoryAllocateSheetTask(
								GUID.valueOf(sourceStoreList.getText()), GUID
										.valueOf(targetStoreList.getText()),
								createMemoText().getText(), items);
						getContext().handle(task);
						sheetId = String.valueOf(task.getSheetId());
					} else {
						UpdateInventoryAllocateSheetTask task = new UpdateInventoryAllocateSheetTask(
								GUID.valueOf(sheetId), GUID
										.valueOf(sourceStoreList.getText()),
								GUID.valueOf(targetStoreList.getText()),
								createMemoText().getText(), items);
						getContext().handle(task);
					}
					InventoryAllocateTask task = new InventoryAllocateTask(GUID
							.valueOf(sheetId));
					getContext().handle(task,
							InventoryAllocateTask.Method.Submit);
					if (!task.isSuccess()) {
						if (null != task.getErrors()
								&& task.getErrors().length > 0) {
							StringBuilder message = new StringBuilder();
							for (InventoryAllocateTask.Error error : task
									.getErrors()) {
								if (message.length() > 0) {
									message.append(";");
								}
								message.append(error.getMessage());
							}
							alert(message.toString());
						}
						return;
					}
					getContext().bubbleMessage(new MsgResponse(true));
				}
			});
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					processData();
				}

			});
			resetButtonStatus();
		} else {
			if (InventoryAllocateStatus.Submitted.equals(info.getStatus())
					&& isNotEmployee()) {
				approvalButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
				approvalButton.setID(ID_Button_Approval);
				approvalButton.setText("批准申请");
				approvalButton.setLayoutData(gd);
				new Label(sheetButtonArea).setText(" ");
				denyButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
				denyButton.setID(ID_Button_Deny);
				denyButton.setText("退回申请");
				denyButton.setLayoutData(gd);
				approvalButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						InventoryAllocateTask task = new InventoryAllocateTask(
								GUID.valueOf(sheetId));
						getContext().handle(task,
								InventoryAllocateTask.Method.Approval);
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
				denyButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						new SetPSICaseUtil(getContext(),
								SetPSICaseUtil.ReturnCause) {

							@Override
							protected void action(String cause) {
								InventoryAllocateTask task = new InventoryAllocateTask(
										GUID.valueOf(sheetId));
								task.setDenyReason(cause);
								getContext().handle(task,
										InventoryAllocateTask.Method.Deny);
								getContext().bubbleMessage(
										new MsgResponse(true));
							}
						};
					}
				});
			}
			if (InventoryAllocateStatus.Allocating.equals(info.getStatus())
					&& hasAuthToAllocateOut()) {
				if (!validateInput()) {
					return;
				}
				allocateOut = new Button(sheetButtonArea, JWT.APPEARANCE3);
				allocateOut.setID(ID_Button_AllocateOut);
				allocateOut.setText("确认出库");
				allocateOut.setLayoutData(gd);
				allocateOut.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						InventoryAllocateTask task = new InventoryAllocateTask(
								GUID.valueOf(sheetId));
						getContext().handle(task,
								InventoryAllocateTask.Method.AllocateOut);
						if (!task.isSuccess()) {
							if (CheckIsNull.isNotEmpty(task.getErrors())) {
								StringBuilder message = new StringBuilder();
								for (InventoryAllocateTask.Error error : task
										.getErrors()) {
									if (message.length() > 0) {
										message.append(";");
									}
									message.append(error.getMessage());
								}
								alert(message.toString());
							}
							return;
						}
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
			}
			if (InventoryAllocateStatus.AllocateOut.equals(info.getStatus())
					&& hasAuthToAllocateIn()) {
				if (!validateInput()) {
					return;
				}
				allocateIn = new Button(sheetButtonArea, JWT.APPEARANCE3);
				allocateIn.setID(ID_Button_AllocateIn);
				allocateIn.setText("确认入库");
				allocateIn.setLayoutData(gd);
				allocateIn.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						InventoryAllocateTask task = new InventoryAllocateTask(
								GUID.valueOf(sheetId));
						getContext().handle(task,
								InventoryAllocateTask.Method.AllocateIn);
						if (!task.isSuccess()) {
							if (CheckIsNull.isNotEmpty(task.getErrors())) {
								StringBuilder message = new StringBuilder();
								for (InventoryAllocateTask.Error error : task
										.getErrors()) {
									if (message.length() > 0) {
										message.append(";");
									}
									message.append(error.getMessage());
								}
								alert(message.toString());
							}
							return;
						}
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
			}
		}
	}

	protected boolean saveData() {
		if (!validateInput()) {
			return false;
		}
		if (!validateStore()) {
			return false;
		}
		String[] rowIds = table.getAllRowId();
		AllocateStockItem[] items = new AllocateStockItem[rowIds.length];

		for (int i = 0; i < rowIds.length; i++) {
			String value = table.getEditValue(rowIds[i], Columns.allocateCount
					.name())[0];
			items[i] = new AllocateStockItem(GUID.valueOf(rowIds[i]), Double
					.valueOf(value));

		}

		if (CheckIsNull.isEmpty(sheetId)) {

			CreateInventoryAllocateSheetTask task = new CreateInventoryAllocateSheetTask(
					GUID.valueOf(sourceStoreList.getText()), GUID
							.valueOf(targetStoreList.getText()),
					createMemoText().getText(), items);
			getContext().handle(task);
			sheetId = task.getSheetId().toString();
			hint("保存成功");
		} else {

			UpdateInventoryAllocateSheetTask task = new UpdateInventoryAllocateSheetTask(
					GUID.valueOf(sheetId), GUID.valueOf(sourceStoreList
							.getText()), GUID
							.valueOf(targetStoreList.getText()),
					createMemoText().getText(), items);
			getContext().handle(task);
			hint("保存成功");
		}

		AllocateSheetDetailProcessor.this.getArgument();
		getContext().bubbleMessage(new MsgResponse(false));
		return true;
	}

	private boolean hasAuthToAllocateIn() {
		GUID destinationStoreId = info.getDestinationStoreId();
		if (getContext().find(Boolean.class, Auth.Boss)) {
			return true;
		}
		GetStoreListKey key = new GetStoreListKey(StoreStatus.ENABLE);
		ListEntity<StoreItem> listEntity = getContext().find(ListEntity.class,
				key);
		if (null == listEntity || CheckIsNull.isEmpty(listEntity.getItemList())) {
			return false;
		}
		boolean hasStore = false;
		for (StoreItem item : listEntity.getItemList()) {
			if (item.getId().equals(destinationStoreId)
					|| item.getId().equals(destinationStoreId)) {
				hasStore = true;
				break;
			}
		}
		return hasStore;
	}

	private boolean hasAuthToAllocateOut() {
		GUID sourceStoreId = info.getSourceStoreId();
		if (getContext().find(Boolean.class, Auth.Boss)) {
			return true;
		}
		GetStoreListKey key = new GetStoreListKey(StoreStatus.ENABLE);
		ListEntity<StoreItem> listEntity = getContext().find(ListEntity.class,
				key);
		if (null == listEntity || CheckIsNull.isEmpty(listEntity.getItemList())) {
			return false;
		}
		boolean hasStore = false;
		for (StoreItem item : listEntity.getItemList()) {
			if (item.getId().equals(sourceStoreId)
					|| item.getId().equals(sourceStoreId)) {
				hasStore = true;
				break;
			}
		}
		return hasStore;
	}

	private boolean isNotEmployee() {
		boolean isNotEmployee = getContext().find(Boolean.class, Auth.Boss)
				|| getContext().find(Boolean.class, Auth.StoreKeeperManager);
		return isNotEmployee;
	}

	protected boolean validateStore() {
		GUID sourceStoreId = GUID.valueOf(sourceStoreList.getText());
		GUID targetStoreId = GUID.valueOf(targetStoreList.getText());
		if (getContext().find(Boolean.class, Auth.Boss)) {
			return true;
		}
		StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE,
				StoreStatus.ONCOUNTING };
		GetStoreListKey key = new GetStoreListKey(statuss);
		ListEntity<StoreItem> listEntity = getContext().find(ListEntity.class,
				key);
		if (null == listEntity || CheckIsNull.isEmpty(listEntity.getItemList())) {
			alert("您没有操作权限，请重新选择仓库！");
			return false;
		}
		boolean hasStore = false;
		for (StoreItem item : listEntity.getItemList()) {
			if (item.getId().equals(sourceStoreId)
					|| item.getId().equals(targetStoreId)) {
				hasStore = true;
				break;
			}
		}
		if (!hasStore) {
			alert("您没有操作权限，请重新选择仓库！");
		}
		return hasStore;
	}

	@Override
	public String[] getTableActionIds() {
		if (null == info
				|| InventoryAllocateStatus.Submitting.equals(info.getStatus())
				|| InventoryAllocateStatus.Rejected.equals(info.getStatus())) {
			return new String[] { Action.Delete.name() };
		}
		return null;
	}

	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		rowIds.remove(rowId);
		table.removeRow(rowId);
		table.renderUpate();
	}

	/**
	 * 初始化数据 void
	 */
	private void initData() {

		if (CheckIsNull.isNotEmpty(this.getArgument())) {
			sheetId = (String) this.getArgument();
			info = (InventoryAllocateSheetInfo) this.getArgument2();
		}

	}

	private void resetButtonStatus() {
		boolean enabled = !StringUtils.isEmpty(sourceStoreList.getText())
				&& !StringUtils.isEmpty(targetStoreList.getText())
				&& !sourceStoreList.getText().equals(targetStoreList.getText());
		if (CheckIsNull.isEmpty(saveButton))
			return;
		saveButton.setEnabled(enabled);
		submitButton.setEnabled(enabled);
		selectGoodsButton.setEnabled(enabled);
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		String sheetId = (String) this.getArgument();
		if (CheckIsNull.isEmpty(sheetId)) {

			return null;
		}
		InventoryAllocateSheetInfo info = getContext().find(
				InventoryAllocateSheetInfo.class, GUID.valueOf(sheetId));
		if (CheckIsNull.isEmpty(info)) {
			return null;
		}

		return getItems(info.getItems());
	}

	private AllocateShowItem[] getItems(
			com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo.AllocateGoodsItem[] items) {
		AllocateShowItem[] rItems = new AllocateShowItem[items.length];
		for (int i = 0; i < items.length; i++) {
			AllocateShowItem item = new AllocateShowItem();
			item.setAllocateCount(items[i].getAllocateCount());
			GUID storeId;
			if (null != sourceStoreList) {
				storeId = GUID.valueOf(sourceStoreList.getText());
			} else {
				storeId = info.getSourceStoreId();
			}
			GetAvailableCountKey key = new GetAvailableCountKey(storeId,
					items[i].getGoodsItemId());
			Double availableCount = getContext().find(Double.class, key);
			if (null != availableCount) {
				item.setAvailableCount(availableCount);
			}
			item.setStockItemCode(items[i].getGoodsItemCode());
			item.setStockItemId(items[i].getGoodsItemId());
			item.setStockItemName(items[i].getGoodsItemName());
			item.setScale(items[i].getScale());
			item.setStockSpec(items[i].getGoodsItemProperties());
			item.setStockItemUnit(items[i].getGoodsItemUnit());
			rItems[i] = item;
		}
		return rItems;
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof AllocateShowItem) {
			if (null == rowIds) {
				rowIds = new HashSet<String>();
			}
			rowIds.add(((AllocateShowItem) element).getStockItemId().toString());
			return ((AllocateShowItem) element).getStockItemId().toString();
		}
		return null;
	}

	public String getValue(Object element, String columnName) {
		if (element instanceof AllocateShowItem) {
			if (Columns.allocateCount.name().equals(columnName)) {
				return String.valueOf(((AllocateShowItem) element).getAllocateCount());
			}
		}

		return "0";
	}

	@Override
	protected String[] getBaseInfoCellContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getRemark() {
		if (null == info) {
			return null;
		}
		return info.getCause();
	}

	@Override
	protected String getSheetApprovalInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetCreateInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getSheetExtraInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetNumber() {
		if (null == info) {
			return null;
		}
		return info.getSheetNumber();
	}

	@Override
	protected String getSheetTitle() {
		return "调拨单";
	}

	@Override
	protected String[] getSheetType() {
		return null;
	}

	@Override
	protected String getStopCause() {
		return null;
	}

	@Override
	public SNameValue[] getExtraData(Object element) {
		AllocateShowItem item = (AllocateShowItem) element;
		return new SNameValue[] {
				new SNameValue(Columns.availableCount.name(), item
						.getAvailableCount()
						+ ""),
				new SNameValue(Columns.name.name(), item
						.getStockItemName()) };
	}

	@Override
	protected void initSheetData() {
		initData();
	}

	/**
	 * 初始化页面控件 void
	 */
	private void initControls() {
		if (null != info) {
			this.createMemoText().setText(info.getCause());
		}

		if (null == info
				|| InventoryAllocateStatus.Submitting.equals(info.getStatus())
				|| InventoryAllocateStatus.Rejected.equals(info.getStatus())) {

			new Label(this.createControl(ID_HeaderLeftArea, Composite.class))
					.setText("选择调出仓库：");
			sourceStoreList = new LWComboList(this.createControl(
					ID_HeaderLeftArea, Composite.class), JWT.APPEARANCE3);
			sourceStoreList
					.setID(AllocateSheetDetailProcessor.ID_List_SourceStore);

			new Label(this.createControl(ID_HeaderLeftArea, Composite.class))
					.setText("   选择调入仓库：");
			targetStoreList = new LWComboList(this.createControl(
					ID_HeaderLeftArea, Composite.class), JWT.APPEARANCE3);
			targetStoreList
					.setID(AllocateSheetDetailProcessor.ID_List_TargetStore);
			StoreSource storeSource;
			if (getContext().find(Boolean.class, Auth.Boss)) {
				storeSource = new StoreSource();
			} else {
				StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE,
						StoreStatus.ONCOUNTING };
				storeSource = new StoreSource(true, statuss);
			}

			sourceStoreList.getList().setSource(storeSource);
			sourceStoreList.getList().setInput(null);
			targetStoreList.getList().setSource(storeSource);
			targetStoreList.getList().setInput(null);
			if (CheckIsNull.isNotEmpty(info)) {
				sourceStoreList.setSelection(String.valueOf(info
						.getSourceStoreId()));
				targetStoreList.setSelection(String.valueOf(info
						.getDestinationStoreId()));
			}
			sourceStoreList.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					sourceStoreId = GUID.valueOf(sourceStoreList.getText());
					table.render();
					resetButtonStatus();
				}
			});

			targetStoreList.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					resetButtonStatus();
				}
			});
		} else {
			StoreInfo sourceStore = getContext().find(StoreInfo.class,
					info.getSourceStoreId());
			String sourceStoreName = "";
			if (null != sourceStore) {
				sourceStoreName = sourceStore.getName();
			}
			new Label(this.createControl(ID_HeaderLeftArea, Composite.class))
					.setText("调出仓库：" + sourceStoreName);

			StoreInfo targetStore = getContext().find(StoreInfo.class,
					info.getDestinationStoreId());
			String targetStoreName = "";
			if (null != targetStore) {
				targetStoreName = targetStore.getName();
			}
			new Label(this.createControl(ID_HeaderLeftArea, Composite.class))
					.setText("   调入仓库：" + targetStoreName);
			table.setEnabled(false);
			this.createMemoText().setEditable(false);
			// this.createMemoText().setEnabled(false);
		}

	}

	private double getAvailableCount(String itemId) {
		ResourceToken<Inventory> token = getContext().findResourceToken(
				Inventory.class, GUID.valueOf(sourceStoreList.getText()),
				GUID.valueOf(itemId));
		double availableCount = 0;
		if (null != token) {
			availableCount = token.getFacade().getCount()
					- token.getFacade().getLockedCount()
					- token.getFacade().getToDeliverCount();
		}
		return availableCount;
	}

//	public static class Item {
//		/**
//		 * 材料条目ID
//		 * 
//		 * @return
//		 */
//		private GUID goodsItemId;
//
//		/**
//		 * 材料条目编码
//		 * 
//		 * @return
//		 */
//		private String goodsItemCode;
//
//		/**
//		 * 材料条目名称
//		 * 
//		 * @return
//		 */
//		private String goodsItemName;
//
//		/**
//		 * 材料条目属性
//		 * 
//		 * @return
//		 */
//		private String goodsItemProperties;
//
//		/**
//		 * 材料条目单位
//		 * 
//		 * @return
//		 */
//		private String goodsItemUnit;
//
//		/**
//		 * 材料数量小数位
//		 * 
//		 * @return
//		 */
//		private int countDecimal;
//
//		/**
//		 * 可用库存
//		 * 
//		 * @return
//		 */
//		private double availableCount;
//
//		/**
//		 * 调拨数量
//		 * 
//		 * @return
//		 */
//		private double allocateCount;
//
//		public GUID getGoodsItemId() {
//			return goodsItemId;
//		}
//
//		public void setGoodsItemId(GUID goodsItemId) {
//			this.goodsItemId = goodsItemId;
//		}
//
//		public String getGoodsItemCode() {
//			return goodsItemCode;
//		}
//
//		public void setGoodsItemCode(String goodsItemCode) {
//			this.goodsItemCode = goodsItemCode;
//		}
//
//		public String getGoodsItemName() {
//			return goodsItemName;
//		}
//
//		public void setGoodsItemName(String goodsItemName) {
//			this.goodsItemName = goodsItemName;
//		}
//
//		public String getGoodsItemProperties() {
//			return goodsItemProperties;
//		}
//
//		public void setGoodsItemProperties(String goodsItemProperties) {
//			this.goodsItemProperties = goodsItemProperties;
//		}
//
//		public String getGoodsItemUnit() {
//			return goodsItemUnit;
//		}
//
//		public void setGoodsItemUnit(String goodsItemUnit) {
//			this.goodsItemUnit = goodsItemUnit;
//		}
//
//		public int getScale() {
//			return countDecimal;
//		}
//
//		public void setCountDecimal(int countDecimal) {
//			this.countDecimal = countDecimal;
//		}
//
//		public double getAvailableCount() {
//			return availableCount;
//		}
//
//		public void setAvailableCount(double availableCount) {
//			this.availableCount = availableCount;
//		}
//
//		public double getAllocateCount() {
//			return allocateCount;
//		}
//
//		public void setAllocateCount(double allocateCount) {
//			this.allocateCount = allocateCount;
//		}
//	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		if (saveData()) {
			resetDataChangedstatus();
			return true;
		} else {
			return false;
		}
	}

}
