package com.spark.psi.order.browser.delivery;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.deliver.entity.DeliverItem;

public class UnDeliverListPageRender extends DeliverListPageRender {
	
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(UnDeliverListPageProcessor.ColumnName.billsNo.name(), 200, JWT.LEFT, "单据编号");
//		columns[1] = new STableColumn(UnDeliverListPageProcessor.ColumnName.orderCount.name(), 150, JWT.RIGHT, "订单数量");
		columns[1] = new STableColumn(UnDeliverListPageProcessor.ColumnName.station.name(), 300, JWT.LEFT, "门店");
		columns[2] = new STableColumn(UnDeliverListPageProcessor.ColumnName.sendPerson.name(), 200, JWT.CENTER, "发货人");
		columns[3] = new STableColumn(UnDeliverListPageProcessor.ColumnName.sendDate.name(), 200, JWT.CENTER, "发货日期");
//		columns[4] = new STableColumn(UnDeliverListPageProcessor.ColumnName.planDate.name(), 150, JWT.CENTER, "预计到货日期");
		
		for (STableColumn column : columns) {
			column.setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		DeliverItem item = (DeliverItem)element;
		switch(columnIndex) {
		case 0:
			return StableUtil.toLink(Action.Detail.name(), "", item.getSheetNo());
		case 1:
			return item.getStationName();
		case 2:
			return item.getCreator();
		case 3:
			return DateUtil.dateFromat(item.getCreateDate(), DateUtil.DATE_TIME_PATTERN);
//		case 4:
////			return DateUtil.dateFromat(item.get)
//		case 5:
		}
		return null;
	}

}
