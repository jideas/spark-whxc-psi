package com.spark.psi.order.browser.produce;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.produceorder.entity.ProduceOrderItem;

public class SubmitingProduceOrderListRender extends ProduceOrderListRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(SubmitingProduceOrderListProcessor.ColumnName.code.name(), 200, JWT.LEFT, "订单编号");
		columns[1] = new STableColumn(SubmitingProduceOrderListProcessor.ColumnName.count.name(), 200, JWT.RIGHT, "商品数量");
		columns[2] = new STableColumn(SubmitingProduceOrderListProcessor.ColumnName.planFinishDate.name(), 200, JWT.CENTER, "计划完成日期");
		columns[3] = new STableColumn(SubmitingProduceOrderListProcessor.ColumnName.status.name(), 200, JWT.CENTER, "单据状态");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
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
			return item.getStatus().getName();
		}
		return null;
	}
	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("单据数量：");
		new Label(headerLeftArea).setID(ProducingProduceOrderListProcessor.ID_Label_Count);
		
		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setText(" 新增订单 ");
		button.setID(SubmitingProduceOrderListProcessor.ID_Button_Add); 
	} 
	
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
}
