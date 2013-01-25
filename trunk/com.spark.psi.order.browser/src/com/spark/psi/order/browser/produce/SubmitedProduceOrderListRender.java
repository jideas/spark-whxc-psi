package com.spark.psi.order.browser.produce;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.produceorder.entity.ProduceOrderItem;

public class SubmitedProduceOrderListRender extends ProduceOrderListRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(SubmitedProduceOrderListProcessor.ColumnName.code.name(), 300, JWT.LEFT, "�������");
		columns[1] = new STableColumn(SubmitedProduceOrderListProcessor.ColumnName.count.name(), 240, JWT.RIGHT, "��Ʒ����");
		columns[2] = new STableColumn(SubmitedProduceOrderListProcessor.ColumnName.createInfo.name(), 240, JWT.LEFT, "�Ƶ�");
		columns[3] = new STableColumn(SubmitedProduceOrderListProcessor.ColumnName.planFinishDate.name(), 240, JWT.CENTER, "�ƻ��������");
		columns[0].setGrab(true);
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
			return item.getCreator() + "(" + DateUtil.dateFromat(item.getCreateDate()) + ")";
		case 3:
			return DateUtil.dateFromat(item.getPlanDate());
		}
		return null;
	}
	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("����������");
		new Label(headerLeftArea).setID(SubmitedProduceOrderListProcessor.ID_Label_Count);
	}

}
