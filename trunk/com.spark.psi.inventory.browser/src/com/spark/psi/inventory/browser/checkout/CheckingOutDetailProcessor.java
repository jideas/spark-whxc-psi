package com.spark.psi.inventory.browser.checkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
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
import com.spark.psi.base.Inventory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.inventory.browser.checkin.ExtendSimpleSheetPageProcessor;
import com.spark.psi.inventory.browser.internal.DistributeShelfProcessor;
import com.spark.psi.inventory.browser.internal.DistributeShelfRender;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageProcessor;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageRender;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.checkout.task.SureCheckOutTask;
import com.spark.psi.publish.inventory.checkout.task.SureCheckOutTaskItem;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;
import com.spark.psi.publish.inventory.entity.CheckingOutInfo;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

/**
 * 出库中间表明细列表处理器
 */
public class CheckingOutDetailProcessor extends ExtendSimpleSheetPageProcessor<CheckingGoodsItem> implements
		IDataProcessPrompt {

	public final static String ID_Button_CheckOut = "button_CheckOut";
	public final static String ID_FooterRightArea = "footerRightArea";

	public final static String ID_Label_Customer = "label_Customer";
	public final static String ID_Label_Store = "label_Store";
	public final static String ID_Label_PlanDate = "label_PlanDate";
	public final static String ID_Label_RelatedNumber = "label_RelatedNumber";

	public final static String ID_Text_DeliveryPerson = "Text_DeliveryPerson";
	public final static String ID_Text_DeliveryUnit = "Text_DeliveryUnit";
	public final static String ID_Text_VoucherNumber = "Text_VoucherNumber";

	public static enum Columns {
		GoodsCode, GoodsName, GoodsProperties, GoodsUnit, CheckingCount, CheckedCount, CheckCount, GoodsNo
	}

	private Button checkOut;

	CheckingOutInfo info;

	private Text deliveryPersonText, deliveryUnitText, voucherNumberText;

	@Override
	public void process(Situation situation) {
		super.process(situation);
		initControls();
		registerValidator();
	}

	private void initControls() {

		this.createMemoText().setEnabled(false);
		if (null == info) {
			return;
		}
		this.createMemoText().setText(info.getRemark());
		this.showCheckedOutItemPage(info.getCheckedOutItems());
		this.lblProcessingStatusValue.setText(info.getStatus().getName());
		if (!info.getStatus().equals(CheckingOutStatus.None) && null != info.getCheckedOutItems()
				&& info.getCheckedOutItems().length > 0) {
			this.lblLink.setText("　提货记录");
		}
		if (CheckingOutType.Return.equals(info.getType())) {
			this.createControl(ID_Label_Customer, Label.class).setText("供应商：" + info.getPartnerName());
			this.createControl(ID_Label_Store, Label.class).setText("  出库仓库：" + info.getStoreName());
		} else if (!info.getType().isMaterialTakeOrReturn() && info.getType().isRealGoods()) {
			this.createControl(ID_Label_Customer, Label.class).setText("客户：" + info.getPartnerName());
			this.createControl(ID_Label_Store, Label.class).setText("  出库仓库：" + info.getStoreName());
		} else {
			this.createControl(ID_Label_Store, Label.class).setText("出库仓库：" + info.getStoreName());
		}

		if (CheckingOutType.Sales.equals(info.getType())) {
			this.createControl(ID_Label_PlanDate, Label.class).setText(
					"  预计出库日期：" + DateUtil.dateFromat(info.getPlanCheckoutDate()));
		}
		this.createControl(ID_Label_RelatedNumber, Label.class).setText("  相关单据：" + info.getRelaBillsNo());
		// 制单
		Label createDate = this.createControl(ID_Label_Label_ExtraInfo, Label.class);
		createDate.setText("制单：" + DateUtil.dateFromat(info.getCreateDate()));
		if ((CheckingOutStatus.Finish.equals(info.getStatus()) || CheckingOutStatus.Stop.equals(info.getStatus()))) {
			return;
		}

		this.createMemoText().setText(info.getRemark());

		this.deliveryPersonText = this.createTextControl(ID_Text_DeliveryPerson);
		this.deliveryUnitText = this.createTextControl(ID_Text_DeliveryUnit);
		this.voucherNumberText = this.createTextControl(ID_Text_VoucherNumber);
		registerNotEmptyValidator(deliveryPersonText, "提货人");
		registerNotEmptyValidator(deliveryUnitText, "提货单位");

		// 确认出库
		checkOut = new Button(this.createControl(ID_FooterRightArea, Composite.class), JWT.APPEARANCE3);
		checkOut.setID(ID_Button_CheckOut);
		checkOut.setText("确认出库");
		GridData gd = new GridData();
		gd.widthHint = 80;
		gd.heightHint = 28;
		checkOut.setLayoutData(gd);

		checkOut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				processData();
			}
		});
	}

	/**
	 * 确认出库
	 */
	private boolean save() {
		if (!validateInput()) {
			return false;
		}
		if (!validateStoreStatus()) {
			return false;
		}
		if (!validateInventoryCount()) {
			return false;
		}
		final String[] rowIds = table.getAllRowId();
		if (rowIds == null) {
			return false;
		}
		Map<GUID, CheckingGoodsItem> map = new HashMap<GUID, CheckingGoodsItem>();
		for (CheckingGoodsItem item : info.getCheckingGoodsItems()) {
			map.put(item.getId(), item);
		}
		final SureCheckOutTask task = new SureCheckOutTask();
		task.setId(info.getSheetId());
		List<SureCheckOutTaskItem> itemList = new ArrayList<SureCheckOutTaskItem>();
		List<DistributeInventoryItem> inventorys = new ArrayList<DistributeInventoryItem>();
		for (int i = 0; i < rowIds.length; i++) {
			String rowId = rowIds[i];
			String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
			if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
				itemList.add(new SureCheckOutTaskItem(GUID.valueOf(rowId), Double.valueOf(values[0])));
				// 准备货位数据
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
		task.setItems(itemList);

		task.setDeliveryPerson(this.deliveryPersonText.getText());
		task.setDeliveryDepartment(this.deliveryUnitText.getText());
		task.setVoucherNumber(this.voucherNumberText.getText());
		// 选择货位
		if (info.getType() == CheckingOutType.Mateiral_Return) {
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
						if (task.isSuccess()) {
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				}
			});
			context.bubbleMessage(request);
		} else {
			PageController pc = new PageController(InventoryShelfInfoPageProcessor.class,
					InventoryShelfInfoPageRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, info.getStoreId(), inventorys
					.toArray(new DistributeInventoryItem[inventorys.size()]));
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			windowStyle.setSize(820, 480);
			MsgRequest request = new MsgRequest(pci, "选择货位", windowStyle);

			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					if (null != returnValue) {
						task.setInventoryItems((DistributeInventoryItem[]) returnValue);
						getContext().handle(task);

						if (task.isSuccess()) {
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				}
			});
			context.bubbleMessage(request);
		}
		return false;

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

	/**
	 * 合并不同单价材料，检查库存是否够用
	 * 
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validateInventoryCount() {
		boolean canCheckOut = true;

		String[] rowIds = table.getAllRowId();

		List<SureCheckOutTaskItem> itemList = new ArrayList<SureCheckOutTaskItem>();
		for (int i = 0; i < rowIds.length; i++) {
			String rowId = rowIds[i];
			String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
			if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
				itemList.add(new SureCheckOutTaskItem(GUID.valueOf(rowId), Double.valueOf(values[0])));
			}
		}
		if (CheckIsNull.isEmpty(itemList)) {
			return false;
		}
		SureCheckOutTaskItem[] items = new SureCheckOutTaskItem[itemList.size()];
		for (int i = 0; i < itemList.size(); i++) {
			items[i] = itemList.get(i);
		}

		Map<GUID, Item> map = new HashMap<GUID, Item>();
		for (SureCheckOutTaskItem item : items) {
			for (CheckingGoodsItem det : info.getCheckingGoodsItems()) {
				if (det.getId().equals(item.getId())) {
					if (map.containsKey(det.getGoodsItemId())) {
						map.get(det.getGoodsItemId()).setCheckCount(
								item.getCount() + map.get(det.getGoodsItemId()).getCheckCount());
					} else {
						Item thisItem = new Item();
						thisItem.setGoodsItemId(det.getGoodsItemId());
						thisItem.setCheckCount(item.getCount());
						map.put(det.getGoodsItemId(), thisItem);
					}
					break;
				}
			}

		}
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Entry entry = (Entry) iterator.next();
			Item item = (Item) entry.getValue();
			ResourceToken<Inventory> token = getContext().findResourceToken(Inventory.class, info.getStoreId(),
					item.getGoodsItemId());
			if (null == token || item.getCheckCount() > token.getFacade().getCount()) {
				MaterialsItemInfo goods = getContext().find(MaterialsItemInfo.class, item.getGoodsItemId());
				alert("材料：" + goods.getBaseInfo().getName() + ",超出库存数量！");
				canCheckOut = false;
				return canCheckOut;
			}
		}
		return canCheckOut;
	}

	/**
	 * 注册表格验证器 void
	 */
	protected void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "出库材料不能为空！";
				}

				List<SureCheckOutTaskItem> itemList = new ArrayList<SureCheckOutTaskItem>();
				String[] rowIds = table.getAllRowId();
				for (int i = 0; i < rowIds.length; i++) {
					String rowId = rowIds[i];
					String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
					if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
						itemList.add(new SureCheckOutTaskItem(GUID.valueOf(rowId), Double.valueOf(values[0])));
					}
				}
				if (CheckIsNull.isEmpty(itemList)) {
					return "请填写出库材料数量！";
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
						return "材料：" + goodsName + "，超出该材料未出库数量！";
					}
				}
				return null;
			}
		});
	}

	private void initData() {
		if (null != this.getArgument()) {
			if (getArgument() instanceof CheckingOutInfo) {
				info = (CheckingOutInfo) getArgument();
			} else if (getArgument() instanceof String) {
				info = getContext().find(CheckingOutInfo.class, GUID.valueOf((String) getArgument()));
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
				return String.valueOf(((CheckingGoodsItem) element).getCheckCount());
			}
		}

		return "";
	}

	@Override
	protected String[] getBaseInfoCellContent() {
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent() {
		return null;
	}

	@Override
	protected String getRemark() {
		return info.getRemark();
	}

	@Override
	protected String getSheetApprovalInfo() {
		return null;
	}

	@Override
	protected String getSheetCreateInfo() {
		return null;
	}

	@Override
	protected String[] getSheetExtraInfo() {
		return null;
	}

	@Override
	protected String getSheetNumber() {
		if (null != info) {
			return null;
		}
		return null;
	}

	@Override
	protected String getSheetTitle() {
		if (null != info) {
			CheckingOutType type = info.getType();
			if (CheckingOutType.Return.equals(type)) {
				return "采购退货出库单";
			} else {
				return type.getName() + "单";
			}
		}
		return "出库单";
	}

	@Override
	protected String[] getSheetType() {
		return null;
	}

	@Override
	protected String getStopCause() {
		if (CheckingOutStatus.Stop.equals(info.getStatus())) {
			return "中止原因：" + info.getStopReason();
		}
		return null;
	}

	@Override
	protected void initSheetData() {

		initData();
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

	protected static class Item {
		/**
		 * 此次出入库数量
		 */
		private double checkCount;
		/**
		 * 已出入库数量
		 */
		private double checkedCount;
		/**
		 * 需出入库的数量
		 */
		private double checkingCount;
		/**
		 * 单价
		 */
		private double price;
		/**
		 * 材料条目ID
		 */
		private GUID goodsItemId;
		/**
		 * RECID
		 */
		private GUID id;

		public double getCheckCount() {
			return checkCount;
		}

		public void setCheckCount(double checkCount) {
			this.checkCount = checkCount;
		}

		public double getCheckedCount() {
			return checkedCount;
		}

		public void setCheckedCount(double checkedCount) {
			this.checkedCount = checkedCount;
		}

		public double getCheckingCount() {
			return checkingCount;
		}

		public void setCheckingCount(double checkingCount) {
			this.checkingCount = checkingCount;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		public void setGoodsItemId(GUID goodsItemId) {
			this.goodsItemId = goodsItemId;
		}

		public void setId(GUID id) {
			this.id = id;
		}

		public GUID getId() {
			return id;
		}
	}

}
