package com.spark.psi.inventory.browser.checkin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.inventory.browser.internal.DistributeShelfProcessor;
import com.spark.psi.inventory.browser.internal.DistributeShelfRender;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;
import com.spark.psi.publish.inventory.entity.CheckingInInfo;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.inventory.sheet.task.CheckInSheetTaskItem;
import com.spark.psi.publish.inventory.sheet.task.InspectCheckinTask;
import com.spark.psi.publish.inventory.sheet.task.SureCheckInTask;
import com.spark.psi.publish.inventory.sheet.task.InspectCheckinTask.InspectItem;
import com.spark.psi.publish.inventory.sheet.task.SureCheckInTask.CheckInError;

/**
 * 入库单明细列表处理器
 */
public class CheckingInDetailProcessor extends ExtendSimpleSheetPageProcessor<CheckingGoodsItem> implements
		IDataProcessPrompt {

	public final static String ID_Button_CheckIn = "button_CheckIn";
	public final static String ID_Button_Inspect = "Button_Inspect";

	public final static String ID_FooterLeftArea = "footerLeftArea";
	public final static String ID_FooterRightArea = "footerRightArea";
	public final static String ID_RenderButtonArea = "renderButtonArea";
	public final static String ID_StopCauseArea = "stopCauseArea";

	public final static String ID_Label_Supplier = "label_Supplier";
	public final static String ID_Label_Store = "label_Store";
	public final static String ID_Label_PlanDate = "label_PlanDate";
	public final static String ID_Label_RelatedNumber = "label_RelatedNumber";

	public static enum Columns {
		GoodsCode, GoodsNo, GoodsName, GoodsProperties, GoodsUnit, CheckingCount, CheckedCount, InspectCount, Count_Inspect, CheckCount
	}

	private Button checkIn;

	CheckingInInfo info;

	@Override
	public void process(Situation situation) {

		super.process(situation);
		registerValidator();
		initContorls();
	}

	private void initContorls() {
		this.createMemoText().setEnabled(false);
		if (null == info) {
			return;
		}
		this.showCheckedInItemPage(info.getCheckedInItems());
		this.lblProcessingStatusValue.setText(info.getStatus().getName());
		if (!info.getStatus().equals(CheckingInStatus.None) && null != info.getCheckedInItems()
				&& info.getCheckedInItems().length > 0) {
			this.lblLink.setText("　确认入库");
		}
		this.createMemoText().setText(info.getRemark());
		// 制单
		Label createDate = this.createControl(ID_Label_Label_ExtraInfo, Label.class);
		createDate.setText("制单：" + DateUtil.dateFromat(info.getCreateDate()));
		if (CheckingInType.GoodsSplit.equals(info.getType())) {
			this.createControl(ID_Label_Store, Label.class).setText("入库仓库：" + info.getStoreName());
		} else {
			if (CheckingInType.Return.equals(info.getType())) {
				this.createControl(ID_Label_Supplier, Label.class).setText("客户：" + info.getPartnerName());
			} else {
				this.createControl(ID_Label_Supplier, Label.class).setText("供应商：" + info.getPartnerName());
			}

			this.createControl(ID_Label_Store, Label.class).setText("  入库仓库：" + info.getStoreName());
		}
		if (CheckingInType.Purchase.equals(info.getType())) {
			this.createControl(ID_Label_PlanDate, Label.class).setText(
					"  预计入库日期：" + DateUtil.dateFromat(info.getPlanCheckinDate()));
		}
		this.createControl(ID_Label_RelatedNumber, Label.class).setText("  相关单据：" + info.getRelaBillsNo());
		if (CheckingInStatus.Finish.equals(info.getStatus()) || CheckingInStatus.Stop.equals(info.getStatus())) {
			return;
		} 
		GridData gd = new GridData();
		gd.widthHint = 80;
		gd.heightHint = 28;
		checkIn = new Button(this.createControl(ID_RenderButtonArea, Composite.class), JWT.APPEARANCE3);
		checkIn.setID(ID_Button_CheckIn);
		checkIn.setText("确认入库");
		checkIn.setLayoutData(gd);
		checkIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processData();
			}
		});
	}

	/**
    *
    */
	private boolean save() {
		if (!validateInput()) {
			return false;
		}
		if (!validateStoreStatus()) {
			return false;
		}
		final Map<GUID, CheckingGoodsItem> map = new HashMap<GUID, CheckingGoodsItem>();
		for (CheckingGoodsItem item : info.getCheckingGoodsItems()) {
			map.put(item.getId(), item);
		}
		String[] rowIds = table.getAllRowId();
		if (rowIds != null) {
			List<CheckInSheetTaskItem> itemList = new ArrayList<CheckInSheetTaskItem>();
			List<DistributeInventoryItem> inventorys = new ArrayList<DistributeInventoryItem>();
			for (int i = 0; i < rowIds.length; i++) {
				String rowId = rowIds[i];
				String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
				if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
					itemList.add(new CheckInSheetTaskItem(GUID.valueOf(rowId), DoubleUtil.strToDouble(values[0])));
					DistributeInventoryItem dii = new DistributeInventoryItem();
					CheckingGoodsItem goods = map.get(GUID.valueOf(rowId));
					dii.setStockId(goods.getGoodsItemId());
					dii.setName(context.find(MaterialsItem.class, goods.getGoodsItemId()).getMaterialName());
					dii.setCount(DoubleUtil.strToDouble(values[0]));
					inventorys.add(dii);
				}
			}
			if (CheckIsNull.isEmpty(itemList)) {
				return false;
			}
			final SureCheckInTask task = new SureCheckInTask(info.getSheetId(), itemList);

			PageController pc = new PageController(DistributeShelfProcessor.class, DistributeShelfRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, info.getStoreId(), inventorys
					.toArray(new DistributeInventoryItem[inventorys.size()]));
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			windowStyle.setSize(720, 480);
			MsgRequest request = new MsgRequest(pci, "选择货位", windowStyle);
			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					if (null != returnValue) {
						task.setInventoryItems((DistributeInventoryItem[]) returnValue);
						getContext().handle(task);
						if (!task.isSuccess()) {
							List<CheckInError> errors = task.getErrors();
							if (CheckIsNull.isNotEmpty(errors)) {
								StringBuffer message = new StringBuffer();
								for (CheckInError error : errors) {
									message.append(error.getMessage());
								}
								alert(message.toString());
								return;
							}
						}
						InspectCheckinTask task = new InspectCheckinTask(true);
						String[] rowIds = table.getAllRowId();
						if (rowIds == null) {
							return;
						}
						List<InspectItem> itemList = new ArrayList<InspectItem>();
						for (int i = 0; i < rowIds.length; i++) {
							String rowId = rowIds[i];
							String[] values = table.getEditValue(rowId, Columns.Count_Inspect.name());
							if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
								itemList.add(task.new InspectItem(GUID.valueOf(rowId), DoubleUtil
										.strToDouble(values[0]), map.get(GUID.valueOf(rowId)).getInspectCount()));
							}
						}
						if (itemList.size() == 0) {
							context.bubbleMessage(new MsgResponse(true));
							return;
						}
						task.setItems(itemList);
						context.handle(task);
						if (task.isSuccess()) {
							context.bubbleMessage(new MsgResponse(true));
						}
					}
				}
			});
			context.bubbleMessage(request);
			return true;
		} else {
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	private boolean validateStoreStatus() {

		GetStoreListKey key = new GetStoreListKey(StoreStatus.ENABLE);
		ListEntity<StoreItem> listEntity = getContext().find(ListEntity.class, key);
		if (null == listEntity || CheckIsNull.isEmpty(listEntity.getItemList())) {
			alert("仓库停用或盘点中，请检查！");
			return false;
		}
		for (StoreItem item : listEntity.getItemList()) {
			if (item.getId().equals(info.getStoreId())) {
				return true;
			}
		}
		alert("仓库停用或盘点中，请检查！");
		return false;
	}

	private void initData() {
		if (null != this.getArgument()) {
			if (getArgument() instanceof CheckingInInfo) {
				info = (CheckingInInfo) this.getArgument();
			} else if (getArgument() instanceof String) {
				info = getContext().find(CheckingInInfo.class, GUID.valueOf((String) getArgument()));
			}
		}
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (null == info) {
			return null;
		}
		if (CheckIsNull.isNotEmpty(info.getCheckingGoodsItems())) {
			return info.getCheckingGoodsItems();
		}
		return null;
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof CheckingGoodsItem) {
			CheckingGoodsItem item = (CheckingGoodsItem) element;
			return item.getId().toString();
		}
		return "";
	}

	public String getValue(Object element, String columnName) {
		if (element instanceof CheckingGoodsItem) {
			if (Columns.CheckCount.name().equals(columnName)) {
				return DoubleUtil.getRoundStr(((CheckingGoodsItem) element).getCheckCount());
			}
		}

		return "";
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
		return info.getRemark();
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
		return null;// info.getSeetNumber();
	}

	@Override
	protected String getSheetTitle() {
		if (null != info) {
			if (CheckingInType.Irregular.equals(info.getType())) {
				return "零星采购入库单";
			} else if (CheckingInType.Return.equals(info.getType())) {
				return "销售退货入库单";
			}  else if (CheckingInType.GoodsSplit.equals(info.getType())) {
				return "成品拆分入库单";
			} else {
				return info.getType().getName() + "单";
			}
		}
		return "入库单";
	}

	@Override
	protected String[] getSheetType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getStopCause() {
		if (CheckingInStatus.Stop.equals(info.getStatus())) {
			return "中止原因：" + info.getStopReason();
		}
		return null;
	}

	@Override
	protected void initSheetData() {

		initData();

	}

	/**
	 * 注册表格验证器 void
	 */
	protected void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "入库材料不能为空";
				}
				List<CheckInSheetTaskItem> itemList = new ArrayList<CheckInSheetTaskItem>();
				for (String rowId : table.getAllRowId()) {
					String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
					if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
						itemList.add(new CheckInSheetTaskItem(GUID.valueOf(rowId), Double.valueOf(values[0])));
					}
				}

				if (CheckIsNull.isEmpty(itemList)) {
					return "请填写入库材料数量";
				}
				return null;
			}

			@Override
			protected String validateCell(String rowId, String columnName) {
				String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
				if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
					String[] columnValue = table.getEditValue(rowId, Columns.CheckCount.name());
					String goodsName = table.getExtraData(rowId, Columns.GoodsName.name())[0];

					String checkingCount = table.getExtraData(rowId, Columns.CheckingCount.name())[0] == "" ? "0"
							: table.getExtraData(rowId, Columns.CheckingCount.name())[0];
					String checkedCount = table.getExtraData(rowId, Columns.CheckedCount.name())[0] == "" ? "0" : table
							.getExtraData(rowId, Columns.CheckedCount.name())[0];
					if (DoubleUtil.sub(Double.valueOf(columnValue[0]), DoubleUtil.sub(Double.valueOf(checkingCount),
							Double.valueOf(checkedCount))) > 0) {

						return "材料：" + goodsName + "，超出可入库数量";
					}

				}
				return null;
			}
		});
	}

	@Override
	public SNameValue[] getExtraData(Object element) {
		if (element instanceof CheckingGoodsItem) {
			CheckingGoodsItem item = (CheckingGoodsItem) element;
			MaterialsItemInfo goods = getContext().find(MaterialsItemInfo.class, item.getGoodsItemId());
			return new SNameValue[] { new SNameValue(Columns.GoodsName.name(), goods.getBaseInfo().getName()),
					new SNameValue(Columns.CheckingCount.name(), item.getCheckingCount() + ""),
					new SNameValue(Columns.CheckedCount.name(), item.getCheckedCount() + "") };
		}
		return null;
	}

	/**
	 * 提示信息
	 */
	public String getPromptMessage() {
		return null;
	}

	/**
	 * 处理数据
	 */
	public boolean processData() {
		if (save()) {
			resetDataChangedstatus();
			return true;
		} else {
			return false;
		}
	}

}
