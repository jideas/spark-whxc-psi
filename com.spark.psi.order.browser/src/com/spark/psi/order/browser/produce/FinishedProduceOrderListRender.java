package com.spark.psi.order.browser.produce;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.produceorder.entity.ProduceOrderItem;

public class FinishedProduceOrderListRender extends ProduceOrderListRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(FinishedProduceOrderListProcessor.ColumnName.code.name(), 200, JWT.LEFT, "�������");
		columns[1] = new STableColumn(FinishedProduceOrderListProcessor.ColumnName.count.name(), 180, JWT.RIGHT, "��Ʒ����");
		columns[2] = new STableColumn(FinishedProduceOrderListProcessor.ColumnName.planFinishDate.name(), 180, JWT.CENTER, "�ƻ��������");
//		columns[3] = new STableColumn(FinishedProduceOrderListProcessor.ColumnName.finishPerson.name(), 150, JWT.CENTER, "ȷ�����");
		columns[3] = new STableColumn(FinishedProduceOrderListProcessor.ColumnName.finishDate.name(), 180, JWT.CENTER, "�������");
		for(STableColumn s:columns){
			s.setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		ProduceOrderItem item = (ProduceOrderItem)element;
		switch(columnIndex) {
		case 0:
			return StableUtil.toLink(Action.Detail.name(), "", item.getBillsNo());
		case 1:
			return DoubleUtil.getRoundStr(item.getGoodsCount());
		case 2:
			return DateUtil.dateFromat(item.getPlanDate());
		case 3:
			return DateUtil.dateFromat(item.getFinishDate());
			
		}
		return null;
	}
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("����������");
		new Label(headerLeftArea).setID(FinishedProduceOrderListProcessor.ID_Label_Count);
	}

}
