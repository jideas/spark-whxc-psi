package com.spark.psi.order.browser.deliverticket;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketItem;

public class DeliverTicketListRender extends PSIListPageRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5]; 
		columns[0] = new STableColumn(DeliverTicketListProcessor.ColumnName.sheetNo.name(), 200, JWT.LEFT, "单据编号");
		columns[1] = new STableColumn(DeliverTicketListProcessor.ColumnName.onlineOrderNo.name(), 200, JWT.LEFT, "订单编号");
		columns[2] = new STableColumn(DeliverTicketListProcessor.ColumnName.customerName.name(), 200, JWT.CENTER, "会员");
		columns[3] = new STableColumn(DeliverTicketListProcessor.ColumnName.stationName.name(), 300, JWT.LEFT, "门店");
		columns[4] = new STableColumn(DeliverTicketListProcessor.ColumnName.deliverDate.name(), 200, JWT.CENTER, "发货日期");
		for (STableColumn column : columns) {
			column.setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		DeliverTicketItem item = (DeliverTicketItem)element;
		switch(columnIndex) {
		case 0:
			return StableUtil.toLink(Action.Detail.name(), "", item.getSheetNo());
		case 1:
			return item.getOnlineOrderNo();
		case 2:
			return item.getMemberRealName();
		case 3:
			return item.getStationName();
		case 4:
			return DateUtil.dateFromat(item.getCreateDate(), DateUtil.DATE_TIME_PATTERN);
		}
		return null;
	}

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		
		new Label(headerLeftArea).setText("单据数量：");
		new Label(headerLeftArea).setID(DeliverTicketListProcessor.ID_Label_Count);
		new Label(headerLeftArea).setText("  从：");
		// 
		new SDatePicker(headerLeftArea).setID(DeliverTicketListProcessor.ID_DATE_BEGIN);
		new Label(headerLeftArea).setText(" 到：");
		new SDatePicker(headerLeftArea).setID(DeliverTicketListProcessor.ID_DATE_End);
		new Button(headerLeftArea).setID(DeliverTicketListProcessor.ID_BUTTON_QueryButton);
		
		new SSearchText2(headerRightArea).setID(DeliverTicketListProcessor.ID_Search);
		Button button = new Button(headerRightArea, JWT.APPEARANCE3);
		button.setText(" 高级搜索 ");
		button.setID(DeliverTicketListProcessor.ID_Button_AdvanceSearch);
	}
}
