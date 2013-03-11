package com.spark.psi.inventory.browser.checkin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.CheckInOutStoreSource;
import com.spark.psi.base.browser.material.MaterialsSelectParameters;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.inventory.entity.CheckingInItem;
import com.spark.psi.publish.inventory.sheet.task.AdjustCheckinTask;
import com.spark.psi.publish.inventory.sheet.task.AdjustCheckinTaskItem;

public class NewAdjustCheckingInProcessor extends PSIListPageProcessor<CheckingInItem> implements IDataProcessPrompt {

	public static final String ID_Text_Provider = "Text_Provider";
	public static final String ID_List_Store = "List_Store";
	public static final String ID_Button_AddGoods = "Button_AddGoods";
	public static final String ID_Text_Memo = "Text_Memo";
	public static final String ID_Label_Amount = "Label_Amount";
	public static final String ID_Button_Confirm = "Button_Confirm";

	public static enum Column {
		code("材料编号"), number("材料条码"), name("材料名称"), property("规格"), unit("单位"), amount("金额");

		private String title;

		private Column(String title) {
			this.title = title;
		}

		public String getTitle() {
			return title;
		}
	}

	private final Map<String, MaterialsItemInfo> goodsItemStore = new HashMap<String, MaterialsItemInfo>();

	private LWComboList storeList;
	private Text providerText;
	private Text memoText;

	private GUID supplierId;

	@Override
	public void process(final Situation context) {
		super.process(context);

		storeList = createControl(ID_List_Store, LWComboList.class);
		CheckInOutStoreSource CheckInOutStoreSource = new CheckInOutStoreSource(StoreStatus.ENABLE);
		storeList.getList().setSource(CheckInOutStoreSource);
		storeList.getList().setInput(null);

		if (CheckInOutStoreSource.getSize() == 1) {
			storeList.setSelection(CheckInOutStoreSource.getFirstStoreId() == null ? null : CheckInOutStoreSource
					.getFirstStoreId().toString());
		}

		providerText = createControl(ID_Text_Provider, Text.class);
		providerText.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MsgRequest request = CommonSelectRequest.createSelectSupplierRequest();
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						if (null != returnValue) {
							supplierId = (GUID) returnValue;
							SupplierInfo supplierInfo = context.find(SupplierInfo.class, supplierId);
							providerText.setText(supplierInfo.getShortName());
						}
					}
				});
				context.bubbleMessage(request);
			}
		});

		final Button addGoodsBtn = createControl(ID_Button_AddGoods, Button.class);
		addGoodsBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MsgRequest request = CommonSelectRequest.createSelectMaterialsRequest(new MaterialsSelectParameters(
						null, false, false, false, false));
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						if (null != returnValue && returnValue instanceof MaterialsItemInfo[]) {
							Set<String> rowIds = new HashSet<String>();
							for (String rowId : table.getAllRowId()) {
								rowIds.add(rowId);
							}
							MaterialsItemInfo[] selectedGoodsItems = (MaterialsItemInfo[]) returnValue;
							for (MaterialsItemInfo goodsItem : selectedGoodsItems) {
								if (rowIds.contains(goodsItem.getItemData().getId().toString())) {
									continue;
								}
								table.addRow(goodsItem);
								goodsItemStore.put(goodsItem.getItemData().getId().toString(), goodsItem);
							}
							table.renderUpate();
						}
					}
				});
				context.bubbleMessage(request);
			}
		});
		memoText = createControl(ID_Text_Memo, Text.class);

		final Button confirmBtn = createControl(ID_Button_Confirm, Button.class);
		confirmBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (doSave()) {
					context.bubbleMessage(new MsgResponse(true));
				}
			}
		});

		registerNotEmptyValidator(providerText, "供应商");
		registerNotEmptyValidator(storeList, "仓库");
		registerInputValidator(new TableDataValidatorImpl());

		table.addClientEventHandler(SEditTable.CLIENT_EVENT_VALUE_CHANGED, "InventoryClient.handleTableDataChanged");
	}

	private boolean doSave() {
		if (!validateInput()) {
			return false;
		}
		AdjustCheckinTask task = new AdjustCheckinTask();
		fillDataToTask(task);
		StoreItem store = getContext().find(StoreItem.class, task.getStoreId());
		if (null == store) {
			alert("仓库已不可用！");
			return false;
		} else if (StoreStatus.DISABLED.equals(store.getStatus())) {
			alert("仓库处于未启用状态，不能使用。");
			return false;
		} else if (StoreStatus.ONCOUNTING.equals(store.getStatus())
				|| StoreStatus.STOPANDONCOUNTING.equals(store.getStatus())) {
			alert("仓库处于盘点中状态，不能使用。");
			return false;
		} else if (StoreStatus.STOP.equals(store.getStatus())) {
			alert("仓库处于停用状态，不能使用。");
			return false;
		}
		getContext().handle(task);
		return true;
	}

	private void fillDataToTask(AdjustCheckinTask task) {
		task.setPartnerId(supplierId);
		task.setStoreId(GUID.tryValueOf(storeList.getList().getSeleted()));
		task.setRemark(memoText.getText());
		String[] rowIds = table.getAllRowId();
		List<AdjustCheckinTaskItem> items = new ArrayList<AdjustCheckinTaskItem>();
		for (String rowId : rowIds) {
			String amount = table.getEditValue(rowId, Column.amount.name())[0];
			items.add(new AdjustCheckinTaskItem(GUID.valueOf(rowId), DoubleUtil.strToDouble(amount)));
		}
		task.setItems(items);
	}

	@Override
	public String getElementId(Object element) {
		MaterialsItemInfo goodsItem = (MaterialsItemInfo) element;
		return goodsItem.getItemData().getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return null;
	}

	@Override
	public String getValue(Object element, String columnName) {
		if (columnName.equals(Column.amount.name())) {
			return "";
		}
		return null;
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Delete.name() };
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		if (element != null) {
			return new String[] { Action.Delete.name() };
		}
		return null;
	}

	@Override
	public SNameValue[] getExtraData(Object element) {
		return new SNameValue[] { new SNameValue(Column.amount.name(), "") };
	}

	@Override
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (actionName.equals(Action.Delete.name())) {
			table.removeRow(rowId);
			goodsItemStore.remove(rowId);
			table.renderUpate();
		}
	}

	@Override
	protected String validateCell(String rowId, String columnName) {
		String[] columnValue = table.getEditValue(rowId, columnName);
		Column column = Column.valueOf(columnName);
		if (columnValue == null || columnValue.length < 0 || StringHelper.isEmpty(columnValue[0])) {
			return column.getTitle() + "不能为空";
		}
		return null;
	}

	@Override
	protected String validateRowCount(int rowCount) {
		if (rowCount < 1) {
			return "请先添加材料！";
		} else {
			return null;
		}
	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		return doSave();
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
