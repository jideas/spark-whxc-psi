package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;

public class FinishedOnlineOrderListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		GridData gd = new GridData();
		gd.widthHint = 100;
		
		new Label(headerLeftArea).setText("����������");
		Label lb = new Label(headerLeftArea);
		lb.setID(FinishedOnlineOrderListProcessor.ID_Label_Count);
		lb.setLayoutData(gd);
		
		new Label(headerLeftArea).setText("������");
		lb = new Label(headerLeftArea);
		lb.setID(FinishedOnlineOrderListProcessor.ID_Label_Amount);
		lb.setLayoutData(gd);
		
		
		SSearchText2 search = new SSearchText2(headerRightArea);
		search.setID(FinishedOnlineOrderListProcessor.ID_Search);
		
		Button advancedBtn = new Button(headerRightArea, JWT.APPEARANCE3);
		advancedBtn.setText(" �߼����� ");
		advancedBtn.setID(FinishedOnlineOrderListProcessor.ID_Search_Advanced);
		
	}
	
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		int index = 0;
		columns[index++] = new STableColumn(FinishedOnlineOrderListProcessor.ColumnName.code.name(), 150, JWT.LEFT, "�������");
		columns[index++] = new STableColumn(FinishedOnlineOrderListProcessor.ColumnName.amount.name(), 140, JWT.RIGHT, "�������");
		columns[index++] = new STableColumn(FinishedOnlineOrderListProcessor.ColumnName.bookingTime.name(), 140, JWT.CENTER, "�µ�ʱ��");
		columns[index++] = new STableColumn(FinishedOnlineOrderListProcessor.ColumnName.arriveTime.name(), 140, JWT.CENTER, "����ʱ��");
		columns[index++] = new STableColumn(FinishedOnlineOrderListProcessor.ColumnName.finishTime.name(), 140, JWT.CENTER, "���ʱ��");
		columns[index++] = new STableColumn(FinishedOnlineOrderListProcessor.ColumnName.customerName.name(), 140, JWT.CENTER, "��Ա");
		columns[index++] = new STableColumn(FinishedOnlineOrderListProcessor.ColumnName.isToDoor.name(), 80, JWT.CENTER, "�ͻ�����");
		columns[index++] = new STableColumn(FinishedOnlineOrderListProcessor.ColumnName.station.name(), 150, JWT.LEFT, "վ��");
		columns[7].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		OnlineOrderItem item = (OnlineOrderItem) element;
		switch(columnIndex) {
		case 0:
			return StableUtil.toLink(Action.Detail.name(), "", item.getBillsNo());
		case 1:
			return DoubleUtil.getRoundStr(item.getTotalAmount());
		case 2:
			return DateUtil.dateFromat(item.getCreateDate(), DateUtil.DATE_TIME_PATTERN);
		case 3:
			return DateUtil.dateFromat(item.getArrivedConfirmDate(), DateUtil.DATE_TIME_PATTERN);
		case 4:
			return DateUtil.dateFromat(item.getConsignedDate(), DateUtil.DATE_TIME_PATTERN);
		case 5:
			return item.getRealName();
		case 6:
			return item.isToDoor()?"��":"��";
		case 7:
			return item.getStationName();
		}
		return null;
	}

}
