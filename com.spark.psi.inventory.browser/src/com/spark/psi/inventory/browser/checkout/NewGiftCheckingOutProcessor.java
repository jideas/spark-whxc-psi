package com.spark.psi.inventory.browser.checkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.CheckInOutStoreSource;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageProcessor;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.inventory.entity.CheckingInItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.inventory.sheet.task.GiftCheckoutTask;
import com.spark.psi.publish.inventory.sheet.task.GiftCheckoutTaskItem;

public class NewGiftCheckingOutProcessor extends PSIListPageProcessor<CheckingInItem> implements IDataProcessPrompt {

	public static final String ID_List_Store = "List_Store";
	public static final String ID_Button_AddGoods = "Button_AddGoods";
	public static final String ID_Text_Memo = "Text_Memo";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public final static String ID_Text_DeliveryPerson = "Text_DeliveryPerson";
	public final static String ID_Text_DeliveryUnit = "Text_DeliveryUnit";
	public final static String ID_Text_VoucherNumber = "Text_VoucherNumber";

	public static enum Column {
		code("材料编号"), number("材料条码"), name("材料名称"), property("规格"), unit("单位"), count("数量");

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
	private Text memoText;
	private Text deliveryPersonText, deliveryUnitText, voucherNumberText;

	@Override
	public void process(final Situation context) {
		super.process(context);
		storeList = createControl(ID_List_Store, LWComboList.class);
		CheckInOutStoreSource CheckInOutStoreSource = new CheckInOutStoreSource(StoreStatus.ENABLE);
		storeList.getList().setSource(CheckInOutStoreSource);
		storeList.getList().setInput(null);
		if (CheckInOutStoreSource.getSize() == 1) {
			storeList.setSelection(CheckInOutStoreSource.getFirstStoreId() == null ? null : CheckInOutStoreSource.getFirstStoreId()
					.toString());
		}
		storeList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});
		final Button addGoodsBtn = createControl(ID_Button_AddGoods, Button.class);
		addGoodsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String storeId = storeList.getText();
				if (CheckIsNull.isEmpty(storeId)) {
					alert("请选择出库仓库！");
					return;
				}
				MsgRequest request = CommonSelectRequest.createSelectMaterialsRequest(GUID.valueOf(storeId), null);
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						if (null != returnValue && returnValue instanceof MaterialsItemInfo[]) {
							MaterialsItemInfo[] selectedGoodsItems = (MaterialsItemInfo[]) returnValue;
							for (MaterialsItemInfo goodsItem : selectedGoodsItems) {
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
				doSave();
			}
		});
		registerNotEmptyValidator(storeList, "仓库");
		registerInputValidator(new TableDataValidatorImpl());

		this.deliveryPersonText = this.createTextControl(ID_Text_DeliveryPerson);
		this.deliveryUnitText = this.createTextControl(ID_Text_DeliveryUnit);
		this.voucherNumberText = this.createTextControl(ID_Text_VoucherNumber);
	}

	private boolean doSave() {

		final GiftCheckoutTask task = new GiftCheckoutTask();
		if (!validateInput()) {
			return false;
		}
		if (!fillDataToTask(task)) {
			return false;
		}
		if (task.getItems() == null || task.getItems().size() == 0) {
			alert("请填写材料入库数量！");
			return false;
		}
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
		List<DistributeInventoryItem> inventorys = new ArrayList<DistributeInventoryItem>();
		for (GiftCheckoutTaskItem item : task.getItems()) {
			DistributeInventoryItem dii = new DistributeInventoryItem();
			dii.setStockId(item.getGoodsId());
			dii.setName(context.find(MaterialsItem.class, item.getGoodsId()).getMaterialName());
			dii.setCount(item.getCount());
			inventorys.add(dii);
		}
		PageController pc = new PageController(InventoryShelfInfoPageProcessor.class,
				InventoryShelfInfoPageRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, task.getStoreId(), inventorys
				.toArray(new DistributeInventoryItem[inventorys.size()]));
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(820, 480);
		MsgRequest request = new MsgRequest(pci, "选择货位", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null != returnValue) {
					task.setDistributeInventoryItems((DistributeInventoryItem[]) returnValue);
					getContext().handle(task);
					getContext().bubbleMessage(new MsgResponse(true));
				}
			}
		});
		context.bubbleMessage(request);
		return true;
	}

	private boolean fillDataToTask(GiftCheckoutTask task) {
		task.setStoreId(GUID.tryValueOf(storeList.getList().getSeleted()));
		task.setRemark(memoText.getText());
		task.setDeliveryPerson(this.deliveryPersonText.getText());
		task.setDeliveryDepartment(this.deliveryUnitText.getText());
		task.setVoucherNumber(this.voucherNumberText.getText());
		String[] rowIds = table.getAllRowId();
		List<GiftCheckoutTaskItem> items = new ArrayList<GiftCheckoutTaskItem>();
		for (String rowId : rowIds) {
			double count = DoubleUtil.strToDouble(table.getEditValue(rowId, Column.count.name())[0]);
			if (count == 0) {
				continue;
			}
			GUID goodsId = GUID.valueOf(rowId);
			Inventory inventory = getContext().find(Inventory.class, GUID.valueOf(this.storeList.getText()), goodsId);
			if (null!=inventory&&inventory.getCount() < count) {
				alert("出库数量不能大于当前库存数量！");
				return false;
			}
			items.add(new GiftCheckoutTaskItem(goodsId, count));
		}
		task.setItems(items);
		return true;
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
		if (columnName.equals(Column.count.name())) {
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
		return new SNameValue[] { new SNameValue(Column.count.name(), "") };
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
