package com.spark.psi.account.browser.joint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementInfo;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementInfoItem;
import com.spark.b2c.publish.JointVenture.entity.JointVentureRecordItem;
import com.spark.b2c.publish.JointVenture.entity.JointVentureRecordListEntity;
import com.spark.b2c.publish.JointVenture.key.GetJointVentureRecordListKey;
import com.spark.b2c.publish.JointVenture.task.JointSettlementTask;
import com.spark.b2c.publish.JointVenture.task.UpdateJointSettlementStatusTask;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;

public class NewJointPaySheetProcessor<TItem> extends SimpleSheetPageProcessor<TItem> {

	public static final String ID_Text_Supplier = "Text_Supplier";
	public static final String ID_Date_Begin = "Date_Begin";
	public static final String ID_Date_End = "Date_End";
	public static final String ID_Button_ConfirmSelect = "Button_ConfirmSelect";
	public static final String ID_Text_TotalPayingAmount = "Text_TotalAmount";
	public static final String ID_Text_AdjustAmount = "Text_AdjustAmount";
	public static final String ID_Label_TotalSaleAmount = "Label_TotalSaleAmount";
	public static final String ID_Label_TotalPercentageAmount = "Label_TotalPercentageAmount";
	public static final String ID_Button_Save = "Button_Save";
	public static final String ID_Button_Submit = "Button_Submit";
	
	public static enum ColumnName {
		materialName("材料名称"), 
		materialCode("材料编号"), 
		materialNo("材料条码"), 
		price("单价"), 
		count("销售数量"), 
		amount("销售金额"),
		percentage("提成比例"),
		percentageAmount("提成金额");
		
		private String title;
		private ColumnName(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return this.title;
		}
	}
	
	public static enum TableExtraValueName {
		goodsId, goodsCode, goodsNo, goodsSpec, goodsUnit,
		goodsName, goodsPrice, count, amount, percentage, percentageAmount
	}
	
	private SupplierInfo supplierInfo;
	
	private SDatePicker beginDate       = null;
	private SDatePicker endDate         = null;
	private Text adjustAmountText       = null;
	private Text totalPayingAmountText  = null;
	private Label totalSaleAmountLabel  = null;
	private Label percetageAmountLabel  = null;
	private Text supplierText           = null;
	private Text memoText               = null;
	
	private double totalSaleAmount = 0.0;
	private double totalPercentageAmount = 0.0;
	
	private StringBuffer recordItemIds = new StringBuffer();
	
	private List<RecordShowItem> showItemList = null;
	
	private JointSettlementInfo info = null;
	private boolean isInitShow = true;
	@Override
	public void process(Situation situation) {
		super.process(situation);
		beginDate = createControl(ID_Date_Begin, SDatePicker.class);
		endDate = createControl(ID_Date_End, SDatePicker.class);
		supplierText = createTextControl(ID_Text_Supplier);
		adjustAmountText = createTextControl(ID_Text_AdjustAmount);
		totalPayingAmountText = createTextControl(ID_Text_TotalPayingAmount);
		totalSaleAmountLabel = createLabelControl(ID_Label_TotalSaleAmount);
		percetageAmountLabel = createLabelControl(ID_Label_TotalPercentageAmount);
		memoText = createMemoText();
		
		adjustAmountText.addClientEventHandler(JWT.CLIENT_EVENT_DOCUMENT, "Account.handleAdjustDocChange");
		totalPayingAmountText.setEnabled(false);
		
		final Button confirmButton = createButtonControl(ID_Button_ConfirmSelect);
		final Button saveButton = createButtonControl(ID_Button_Save);
		final Button submitButton = createButtonControl(ID_Button_Submit);
		
		supplierText.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 选择供应商
				MsgRequest request = CommonSelectRequest
					.createSelectSupplierRequest();
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						GUID supplierId = (GUID)returnValue;
						if (supplierId != null) {
							supplierInfo = getContext().find(SupplierInfo.class, supplierId);
							supplierText.setText(supplierInfo == null ? "" : supplierInfo.getShortName());
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
		confirmButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确定选择
				if (!validateConfirm()) return;
				if (isInitShow) {
					isInitShow = false;
				}
				table.render();
			}
		});
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 保存
				if (!validate()) return;
				doSave();
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
		submitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 提交
				if (!validate()) return;
				GUID id = doSave();
				if (null == id) return;
				UpdateJointSettlementStatusTask task = new UpdateJointSettlementStatusTask(id);
				getContext().handle(task, UpdateJointSettlementStatusTask.Method.Submit);
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
		
		initData();
	}
	
	private void initData() {
		if (null == info) return;
		beginDate.setDate(DateUtil.convertLongToDate(info.getBeginDate()));
		endDate.setDate(DateUtil.convertLongToDate(info.getEndDate()));
		
		totalSaleAmountLabel.setText(DoubleUtil.getRoundStr(info.getSalesAmount()));
		percetageAmountLabel.setText(DoubleUtil.getRoundStr(info.getPercentageAmount()));
		totalPayingAmountText.setText(DoubleUtil.getRoundStr(info.getAmount()));
		adjustAmountText.setText(DoubleUtil.getRoundStr(info.getAdjustAmount()));
		memoText.setText(info.getRemark());
		totalSaleAmount = info.getSalesAmount();
		
		supplierInfo = getContext().find(SupplierInfo.class, info.getSupplierId());
		supplierText.setText(supplierInfo == null ? "" : supplierInfo.getShortName());
	}
	
	private GUID doSave() {
		
		double adjustAmount = 0.0;
		String adjustAmountStr = adjustAmountText.getText();
		if (StringUtils.isNotEmpty(adjustAmountStr)) {
			adjustAmount = DoubleUtil.strToDouble(adjustAmountStr);
		}
		double totalPayingAmount = 0.0;
		String totalPayingAmountStr = totalPayingAmountText.getText();
		if (StringUtils.isNotEmpty(totalPayingAmountStr)) {
			totalPayingAmount = DoubleUtil.strToDouble(totalPayingAmountStr);
		}
		JointSettlementTask task = new JointSettlementTask();
		
		task.setSupplierName(supplierInfo.getName());
		task.setNamePY(PinyinHelper.getLetter(supplierInfo.getName()));
		task.setShortName(supplierInfo.getShortName());
		task.setSupplierNo(supplierInfo.getNumber());
		task.setSupplierId(supplierInfo.getId());
		task.setBeginDate(beginDate.getDate().getTime());
		task.setEndDate(endDate.getDate().getTime());
		task.setSalesAmount(totalSaleAmount);
		task.setPercentageAmount(totalPercentageAmount);
		task.setAdjustAmount(adjustAmount);
		task.setAmount(totalPayingAmount);
		task.setRemark(memoText.getText());
		task.setRecordIds(recordItemIds.toString());
		
		JointSettlementTask.Item[] items;
		if (isInitShow) {
			items = new JointSettlementTask.Item[info.getItems().length];
			JointSettlementTask.Item item = null;
			int index = 0;
			for (JointSettlementInfoItem jItem : info.getItems()) {
				item = task.new Item();
				item.setGoodsId(jItem.getGoodsId());
				item.setGoodsCode(jItem.getGoodsCode());
				item.setGoodsNo(jItem.getGoodsNo());
				item.setGoodsSpec(jItem.getGoodsSpec());
				item.setGoodsUnit(jItem.getGoodsUnit());
				item.setGoodsName(jItem.getGoodsName());
				item.setGoodsPrice(jItem.getGoodsPrice());
				item.setCount(jItem.getCount());
				item.setAmount(jItem.getAmount());
				item.setPercentage(jItem.getPercentage());
				item.setPercentageAmount(jItem.getPercentageAmount());
				
				items[index] = item;
				index++;
			}
		} else {
			items = new JointSettlementTask.Item[showItemList.size()];
			JointSettlementTask.Item item = null;
			int index = 0;
			for (RecordShowItem rItem : showItemList) {
				item = task.new Item();
				item.setGoodsId(rItem.getGoodsId());
				item.setGoodsCode(rItem.getGoodsCode());
				item.setGoodsNo(rItem.getGoodsNo());
				item.setGoodsSpec(rItem.getGoodsSpec());
				item.setGoodsUnit(rItem.getGoodsUnit());
				item.setGoodsName(rItem.getGoodsName());
				item.setGoodsPrice(rItem.getGoodsPrice());
				item.setCount(rItem.getCount());
				item.setAmount(rItem.getAmount());
				item.setPercentage(rItem.getPercentage());
				item.setPercentageAmount(rItem.getPercentageAmount());
				items[index] = item;
				index++;
			}
		}
		task.setItems(items);
		if (null == info) {
			task.setId(GUID.randomID());
			getContext().handle(task, JointSettlementTask.Method.Create);
		} else {
			task.setId(info.getId());
			getContext().handle(task, JointSettlementTask.Method.Update);
		}
		return task.getId();
	}
	
	private boolean validate() {
		if (null == supplierInfo) {
			alert("请先选择供应商。");
			return false;
		}
		
		if (null == info || info.getItems().length < 1) {
			alert("无销售记录，不能进行结算。");
			return false;
		}
		double adjustAmount = 0.0;
		String adjustAmountStr = adjustAmountText.getText();
		if (StringUtils.isNotEmpty(adjustAmountStr)) {
			adjustAmount = DoubleUtil.strToDouble(adjustAmountStr);
		}
		if ((adjustAmount + totalPercentageAmount) > totalSaleAmount) {
			alert("调整金额不能大于销售总额。");
			return false;
		}
		return true;
	}
	
	private boolean validateConfirm() {
		if (null == supplierInfo) {
			alert("请选择供应商。");
			return false;
		}
		
		if (beginDate.getDate().getTime() > endDate.getDate().getTime()) {
			alert("开始日期不能晚于结束日期。");
			return false;
		}
		return true;
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
		return info == null ? null : info.getSheetNo();
	}
	@Override
	protected String getSheetTitle() {
		return info == null ? "新增结算单" : "联营结算单";
	}
	@Override
	protected String[] getSheetType() {
		return null;
	}
	@Override
	protected String getStopCause() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void initSheetData() {
		if (getArgument() != null) {
			GUID id = (GUID) getArgument();
			info = getContext().find(JointSettlementInfo.class, id);
		}
	}
	@Override
	public String getElementId(Object element) {
		if (element instanceof JointSettlementInfoItem) {
			JointSettlementInfoItem item = (JointSettlementInfoItem)element;
			return item.getId().toString();
		} else {
			RecordShowItem item = (RecordShowItem)element;
			return item.getId().toString();
		}
	}
	
	@Override
	public SNameValue[] getExtraData(Object element) {
//		JointVentureRecordItem item = (JointVentureRecordItem)element;
//		return new SNameValue[]{new SNameValue(TableExtraValueName.goodsId.name(), item.getGoodsId().toString()),
//				new SNameValue(TableExtraValueName.sheetId.name(), item.getSheetId().toString()),
//				new SNameValue(TableExtraValueName.sheetNo.name(), item.getSheetNo()),
//				new SNameValue(TableExtraValueName.count.name(), item.getCount() + "")};
		return null;
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (null != info && isInitShow) {
			return info.getItems();
		}
		if (null == supplierInfo) {
			totalSaleAmountLabel.setText(DoubleUtil.getRoundStr(totalSaleAmount));
			percetageAmountLabel.setText(DoubleUtil.getRoundStr(totalPercentageAmount));
			return null;
		}
		GetJointVentureRecordListKey key = new GetJointVentureRecordListKey(0, Integer.MAX_VALUE, false, supplierInfo.getId());
		key.setBeginTime(beginDate.getDate().getTime());
		key.setEndTime(endDate.getDate().getTime());
		key.setNeverSettlement(true);
		ListEntity<JointVentureRecordItem> listEntity = context.find(JointVentureRecordListEntity.class, key);
		if (null == listEntity) return null;
		totalSaleAmount = 0.0;
		for (int index = 0; index < listEntity.getItemList().size(); index++) {
			JointVentureRecordItem item = listEntity.getItemList().get(index);
			totalSaleAmount += item.getAmount();
			recordItemIds.append(item.getRECID().toString());
			if (index != listEntity.getItemList().size() - 1) {
				recordItemIds.append(",");
			}
		}
		totalSaleAmountLabel.setText(DoubleUtil.getRoundStr(totalSaleAmount));
		
		showItemList = getShowItemListWithGroupByGoodsIdAndPercentage(listEntity.getItemList());
		
		totalPercentageAmount = 0.0;
		for (RecordShowItem item : showItemList) {
			totalPercentageAmount += item.getPercentageAmount();
		}
		percetageAmountLabel.setText(DoubleUtil.getRoundStr(totalPercentageAmount));
		
		totalPayingAmountText.setText(DoubleUtil.getRoundStr(DoubleUtil.sub(totalSaleAmount, totalPercentageAmount)));
		return showItemList.toArray(new RecordShowItem[0]);
	}
	
	
	private List<RecordShowItem> getShowItemListWithGroupByGoodsIdAndPercentage(List<JointVentureRecordItem> sourceList) {
		List<RecordShowItem> showItemList = new ArrayList<RecordShowItem>();
		Map<GUID, List<JointVentureRecordItem>> groupByGoodsList = getRecordGroupByGoodsId(sourceList);
		Iterator<GUID> gIt = groupByGoodsList.keySet().iterator();
		while (gIt.hasNext()) {
			GUID goodsId = gIt.next();
			List<JointVentureRecordItem> goodsRecordList = groupByGoodsList.get(goodsId);
			Map<Double, List<JointVentureRecordItem>> groupByPercetageList = getRecordGroupByPercentage(goodsRecordList);
			Iterator<Double> pIt= groupByPercetageList.keySet().iterator();
			while (pIt.hasNext()) {
				double percetage = pIt.next();
				List<JointVentureRecordItem> percetageRecordList = groupByPercetageList.get(percetage);
				showItemList.add(convertRecordItemListToShowItem(percetageRecordList));
			}
		}
		return showItemList;
	}
	
	private Map<GUID, List<JointVentureRecordItem>> getRecordGroupByGoodsId(List<JointVentureRecordItem> sourceList) {
		Map<GUID, List<JointVentureRecordItem>> goodsShowItemStroe = new HashMap<GUID, List<JointVentureRecordItem>>();
		for (JointVentureRecordItem sItem : sourceList) {
			GUID goodsId = sItem.getGoodsId();
			List<JointVentureRecordItem> goodsRecordItemList = null;
			if (null == goodsShowItemStroe.get(goodsId)) {
				goodsRecordItemList = new ArrayList<JointVentureRecordItem>();
			} else {
				goodsRecordItemList = goodsShowItemStroe.get(goodsId);
			}
			goodsRecordItemList.add(sItem);
			goodsShowItemStroe.put(goodsId, goodsRecordItemList);
		}
		return goodsShowItemStroe;
	}
	
	private Map<Double, List<JointVentureRecordItem>> getRecordGroupByPercentage(List<JointVentureRecordItem> sourceList) {
		Map<Double, List<JointVentureRecordItem>> percentageShowItemStroe = new HashMap<Double, List<JointVentureRecordItem>>();
		for (JointVentureRecordItem sItem : sourceList) {
			double percentage = sItem.getPercentage();
			List<JointVentureRecordItem> percentageRecordItemList = null;
			if (null == percentageShowItemStroe.get(percentage)) {
				percentageRecordItemList = new ArrayList<JointVentureRecordItem>();
			} else {
				percentageRecordItemList = percentageShowItemStroe.get(percentage);
			}
			percentageRecordItemList.add(sItem);
			percentageShowItemStroe.put(percentage, percentageRecordItemList);
		}
		return percentageShowItemStroe;
	}
	
	private RecordShowItem convertRecordItemListToShowItem(List<JointVentureRecordItem> sItemList) {
		if (sItemList.size() < 1) return null;
		double totalCount = 0.0;
		double totalAmount = 0.0;
		double totalPercentageAmount = 0.0;
		for (JointVentureRecordItem rItem : sItemList) {
			totalCount += rItem.getCount();
			totalAmount += rItem.getAmount();
			totalPercentageAmount += rItem.getAmount() * rItem.getPercentage(); 
		}
		RecordShowItem item = new RecordShowItem(GUID.randomID());
		item.setGoodsId(sItemList.get(0).getGoodsId());
		item.setGoodsCode(sItemList.get(0).getGoodsCode());
		item.setGoodsNo(sItemList.get(0).getGoodsNo());
		item.setGoodsSpec(sItemList.get(0).getGoodsSpec());
		item.setGoodsName(sItemList.get(0).getGoodsName());
		item.setGoodsPrice(sItemList.get(0).getGoodsPrice());
		item.setPercentage(sItemList.get(0).getPercentage());

		item.setCount(totalCount);
		item.setAmount(totalAmount);
		item.setPercentageAmount(totalPercentageAmount);
		return item;
	}

}

class RecordShowItem {
	private GUID id; // 用于显示时用
	private GUID goodsId;
	private String goodsCode;
	private String goodsNo;
	private String goodsSpec;
	private String goodsUnit;
	private String goodsName;
	private double goodsPrice;
	private double count;
	private double amount;
	private double percentage;
	private double percentageAmount;
	
	public RecordShowItem(GUID id) {
		this.id = id;
	}
	public GUID getId() {
		return id;
	}
	public GUID getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsSpec() {
		return goodsSpec;
	}
	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public double getPercentageAmount() {
		return percentageAmount;
	}
	public void setPercentageAmount(double percentageAmount) {
		this.percentageAmount = percentageAmount;
	}
	
	
}
