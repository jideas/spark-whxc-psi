package com.spark.psi.order.browser.delivery;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.deliver.entity.DeliverItem;

public class DeliveryingListPageRender extends DeliverListPageRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(DeliveryingListPageProcessor.ColumnName.billsNo.name(), 200, JWT.LEFT, "���ݱ��");
//		columns[1] = new STableColumn(DeliveryingListPageProcessor.ColumnName.orderCount.name(), 150, JWT.RIGHT, "��������");
		columns[1] = new STableColumn(DeliveryingListPageProcessor.ColumnName.station.name(), 300, JWT.LEFT, "�ŵ�");
		columns[2] = new STableColumn(DeliveryingListPageProcessor.ColumnName.sendPerson.name(), 200, JWT.CENTER, "������");
		columns[3] = new STableColumn(DeliveryingListPageProcessor.ColumnName.sendDate.name(), 200, JWT.CENTER, "��������");
//		columns[4] = new STableColumn(DeliveryingListPageProcessor.ColumnName.planDate.name(), 150, JWT.CENTER, "Ԥ�Ƶ�������");
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
			
		}
		return null;
	}

}
