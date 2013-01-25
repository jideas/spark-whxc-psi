package com.spark.psi.order.browser.online;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.OnlineOrderType;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.base.station.entity.StationInfo;
import com.spark.psi.publish.base.station.entity.StationItem;
import com.spark.psi.publish.base.station.key.GetStationListKey;
import com.spark.psi.publish.onlineorder.task.CreateOnlineOrderTask;

public class NewOnlineOrderProcessor<TItem> extends
		SimpleSheetPageProcessor<TItem> {
	
	public static final String ID_Text_CustomerName = "Text_CustomerName";
	public static final String ID_Text_CustomerPhone = "Text_CustmoerPhone";
	public static final String ID_Text_Consignee = "Text_Consignee";
	public static final String ID_Date_Delivery = "Date_Delivery";
	public static final String ID_List_Station = "List_Statioin";
	public static final String ID_Text_Address = "Text_Address";
	public static final String ID_Button_AddGoods = "Button_AddGoods";
	public static final String ID_Button_Save = "Button_Save";
	
	public static enum ColumnName {
		name, code, spec, unit, count, unitCost, discountRate, discountAmount, amount
	}
	
	public static enum TableExtraValueName {
		name, code, number, spec, unit, goodsScale, salePrice
	}
	
	private Text        nameText           =  null;
	private Text        phoneText          =  null;
	private Text        consigneeText      =  null;
	private SDatePicker date               =  null;
	private LWComboList listStation        =  null;
	private Text        addressText        =  null;
	private Text        memoText           =  null;
	
	@Override
	public void process(final Situation situation) {
		super.process(situation);
		final Button addGoodsBtn = createControl(ID_Button_AddGoods, Button.class);
		final Button saveBtn = createControl(ID_Button_Save, Button.class);
		nameText = createControl(ID_Text_CustomerName, Text.class);
		phoneText = createControl(ID_Text_CustomerPhone, Text.class);
		consigneeText = createControl(ID_Text_Consignee, Text.class);
		date = createControl(ID_Date_Delivery, SDatePicker.class);
		listStation = createControl(ID_List_Station, LWComboList.class);
		addressText = createControl(ID_Text_Address, Text.class);
		memoText = createMemoText();
		
		regiestValidators();
		
		listStation.getList().setSource(new ListSourceAdapter() {
			
			private Map<String, StationItem> idMap = new HashMap<String, StationItem>();
			
			public String getElementId(Object element) {
				StationItem item = (StationItem) element;
				return item.getId().toString();
			}
			
			public Object getElementById(String id) {
				return idMap.get(id);
			}
			
			public String getText(Object element) {
				StationItem item = (StationItem) element;
				return item.getName();
			}
			
			@SuppressWarnings("unchecked")
			public Object[] getElements(Object inputElement) {
				GetStationListKey key = new GetStationListKey();
				key.setQueryAll(true);
				ListEntity<StationItem> stationListEntity = situation.find(ListEntity.class, key);
				if (null == stationListEntity) return null;
				for (StationItem item : stationListEntity.getItemList()) {
					idMap.put(item.getId().toString(), item);
				}
				return stationListEntity.getItemList().toArray(new StationItem[0]);
			}
		});
		listStation.getList().setInput(null);
		
		addGoodsBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = CommonSelectRequest.createSelectGoodsRequest(true);
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						GoodsItemInfo[] items = (GoodsItemInfo[])returnValue;
						String[] rowIds = table.getAllRowId();
						for (GoodsItemInfo item : items) {
							if (isContainsElement(rowIds, item.getItemData().getId().toString())) {
								continue;
							}
							table.addRow(item);
						}
						table.renderUpate();
					}
				});
				situation.bubbleMessage(request);
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//  保存
				if (!validateInput()) {
					return;
				} else {
					doSave();
					getContext().bubbleMessage(new MsgResponse(true));
				}
				
			}
		});
	}

	private void regiestValidators() {
		registerNotEmptyValidator(nameText, "客户");
		registerNotEmptyValidator(phoneText, "电话");
		registerNotEmptyValidator(consigneeText, "收货人");
		registerNotEmptyValidator(listStation, "配送站点");
		registerNotEmptyValidator(addressText, "配送地址");
		registerInputValidator(new TableDataValidator(table) {
			
			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "商品不能为空。";
				}
				return null;
			}
			
			@Override
			protected String validateCell(String rowId, String columnName) {
				String[] validateValues = table.getEditValue(rowId, ColumnName.count.name(), ColumnName.amount.name());
				if (StringUtils.isEmpty(validateValues[0])) {
					return "数量不能为空。";
				}
				if (StringUtils.isEmpty(validateValues[1])) {
					return "金额不能为空。";
				}
				return null;
			}
		});
	}
	
	private void doSave() {
		CreateOnlineOrderTask task = new CreateOnlineOrderTask();
		task.setAddress(addressText.getText());
		task.setConsignee(consigneeText.getText());
		task.setConsigneeTel(phoneText.getText());
		task.setDeliveryeDate(date.getDate().getTime());
		task.setMemberId(GUID.randomID());
		task.setRealName(nameText.getText());
		task.setRemark(memoText.getText());
		task.setStationId(GUID.tryValueOf(listStation.getList().getSeleted()));
		StationInfo staion = getContext().find(StationInfo.class, task.getStationId());
		task.setStationName(staion.getShortName());
		task.setType(OnlineOrderType.Common.getCode());
		
		double totalAmount = 0;
		String[] rowIds = table.getAllRowId();
		CreateOnlineOrderTask.Item[] items = new CreateOnlineOrderTask.Item[rowIds.length];
		CreateOnlineOrderTask.Item item = null;
		int itemIndex = 0;
		for (String rowId : rowIds) {
			String[] values = table.getEditValue(rowId, ColumnName.amount.name(), ColumnName.count.name());
			String[] extraValues = table.getExtraData(rowId, TableExtraValueName.name.name(), TableExtraValueName.code.name(), 
					TableExtraValueName.number.name(), TableExtraValueName.spec.name(), TableExtraValueName.unit.name(), 
					TableExtraValueName.goodsScale.name(), TableExtraValueName.salePrice.name());
			item = task.new Item();
			item.setAmount(DoubleUtil.strToDouble(values[0]));
			item.setCount(DoubleUtil.strToDouble(values[1]));
			
			item.setGoodsName(extraValues[0]);
			item.setGoodsCode(extraValues[1]);
			item.setGoodsNo(extraValues[2]);
			item.setGoodsSpec(extraValues[3]);
			item.setUnit(extraValues[4]);
			item.setGoodsScale(StringUtils.isEmpty(extraValues[5]) ? 0 : Integer.parseInt(extraValues[5]));
			item.setPrice(DoubleUtil.strToDouble(extraValues[6]));
			item.setGoodsId(GUID.tryValueOf(rowId));
			
			items[itemIndex] = item;
			
			totalAmount += item.getAmount();
			itemIndex++;
		}
		task.setTotalAmount(totalAmount);
		task.setItems(items);
		
		getContext().handle(task);
	}
	
	/**
	 * 判断字符数组中是否含有指定的字符串
	 * @param strArray
	 * @param element
	 * @return
	 */
	private boolean isContainsElement(String[] strArray, String element) {
		for (String str : strArray) {
			if (str.equals(element)) {
				return true;
			}
		}
		return false;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getSheetTitle() {
		return "新增网上订单";
	}
	@Override
	protected String[] getSheetType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getStopCause() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void initSheetData() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getElementId(Object element) {
		GoodsItemInfo item = (GoodsItemInfo)element;
		return item.getItemData().getId().toString();
	}
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getValue(Object element, String columnName) {
		if (ColumnName.count.name().equals(columnName)) {
			return "";
		} else if (ColumnName.discountRate.name().equals(columnName)) {
			return "";
		} else if (ColumnName.discountAmount.name().equals(columnName)) {
			return "";
		} else if (ColumnName.amount.name().equals(columnName)) {
			return "";
		}
		return null;
	}
	@Override
	protected String[] getElementActionIds(Object element) {
		return new String[] { Action.Delete.name() };
	}
	@Override
	public SNameValue[] getExtraData(Object element) {
		GoodsItemInfo item = (GoodsItemInfo)element;
		return new SNameValue[] { new SNameValue(TableExtraValueName.name.name(), item.getBaseInfo().getName()),
				new SNameValue(TableExtraValueName.code.name(), item.getBaseInfo().getCode()), 
				new SNameValue(TableExtraValueName.number.name(), item.getItemData().getGoodsItemNo()), 
				new SNameValue(TableExtraValueName.spec.name(), item.getItemData().getSpec()),
				new SNameValue(TableExtraValueName.unit.name(), item.getItemData().getUnit()),
				new SNameValue(TableExtraValueName.goodsScale.name(), item.getItemData().getScale() + ""),
				new SNameValue(TableExtraValueName.salePrice.name(), DoubleUtil.getRoundStr(item.getItemData().getSalePrice()))};
	}
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Delete.name() };
	}
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Delete.name().equals(actionName)) {
			table.removeRow(rowId);
			table.renderUpate();
		}
	}
	
}
