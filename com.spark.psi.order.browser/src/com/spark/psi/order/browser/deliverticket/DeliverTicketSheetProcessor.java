package com.spark.psi.order.browser.deliverticket;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketInfo;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketInfoItem;

public class DeliverTicketSheetProcessor<TItem> extends
		SimpleSheetPageProcessor<TItem> {
	
	public static final String ID_Label_CustomerName = "Label_CustomerName"; 
	public static final String ID_Label_MobileNo = "Label_MobileNo"; 
	public static final String ID_Label_StationName = "Label_StationName"; 
	public static final String ID_Label_DeliverDate = "Label_DeliverDate";
	public static final String ID_Label_TotalAmount = "Label_TotalAmount";
	
	public static enum ColumnName {
		goodsNo, goodsName, unit, count, price, amount
	}
	
	private DeliverTicketInfo sheetInfo = null;
	
	@Override
	public void init(Situation context) {
		super.init(context);
		GUID sheetId = (GUID)getArgument();
		sheetInfo = context.find(DeliverTicketInfo.class, sheetId);
	}

	
	@Override
	public void process(Situation situation) {
		super.process(situation);
		createLabelControl(ID_Label_CustomerName).setText(sheetInfo.getMemberRealName());
		createLabelControl(ID_Label_MobileNo).setText(sheetInfo.getMobilePhone());
		createLabelControl(ID_Label_StationName).setText(sheetInfo.getStationName());
		createLabelControl(ID_Label_DeliverDate).setText(DateUtil.dateFromat(sheetInfo.getCreateDate()));
		createLabelControl(ID_Label_TotalAmount).setText(DoubleUtil.getRoundStr(sheetInfo.getTotalAmount()));
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
		return "制单：" + sheetInfo.getCreator();
	}

	@Override
	protected String[] getSheetExtraInfo() {
		return null;
	}

	@Override
	protected String getSheetNumber() {
		return sheetInfo.getSheetNo();
	}

	@Override
	protected String getSheetTitle() {
		return "发货单";
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
	protected void initSheetData() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getElementId(Object element) {
		DeliverTicketInfoItem item = (DeliverTicketInfoItem)element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return sheetInfo.getItems();
	}

}
