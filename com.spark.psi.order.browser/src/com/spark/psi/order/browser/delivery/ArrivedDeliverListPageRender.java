package com.spark.psi.order.browser.delivery;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.deliver.entity.DeliverItem;

public class ArrivedDeliverListPageRender extends DeliverListPageRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(ArrivedDeliverListPageProcessor.ColumnName.billsNo.name(), 180, JWT.LEFT, "���ݱ��");
//		columns[1] = new STableColumn(ArrivedDeliverListPageProcessor.ColumnName.orderCount.name(), 150, JWT.RIGHT, "��������");
		columns[1] = new STableColumn(ArrivedDeliverListPageProcessor.ColumnName.station.name(), 200, JWT.LEFT, "�ŵ�");
		columns[2] = new STableColumn(ArrivedDeliverListPageProcessor.ColumnName.deliveryPerson.name(), 180, JWT.CENTER, "����Ա");
		columns[3] = new STableColumn(ArrivedDeliverListPageProcessor.ColumnName.sendDate.name(), 180, JWT.CENTER, "��������");
		columns[4] = new STableColumn(ArrivedDeliverListPageProcessor.ColumnName.confirmArrivePerson.name(), 180, JWT.CENTER, "ȷ�ϵ���");
		columns[5] = new STableColumn(ArrivedDeliverListPageProcessor.ColumnName.arrivedDate.name(), 180, JWT.CENTER, "��������");
		columns[1].setGrab(true);
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
			return item.getDeliverPerson();
		case 3:
			return DateUtil.dateFromat(item.getCreateDate(), DateUtil.DATE_TIME_PATTERN);
		case 4:
			return item.getConsignee();
		case 5:
			return DateUtil.dateFromat(item.getConsigneeDate(), DateUtil.DATE_TIME_PATTERN);
		}
		return null;
	}

}
