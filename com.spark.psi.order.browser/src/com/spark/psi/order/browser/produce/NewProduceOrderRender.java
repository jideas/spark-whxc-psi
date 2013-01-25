package com.spark.psi.order.browser.produce;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;

public class NewProduceOrderRender extends PSIListPageRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(NewProduceOrderProcessor.ColumnName.goodsNo.name(), 200, JWT.LEFT, "��Ʒ����");
		columns[1] = new STableColumn(NewProduceOrderProcessor.ColumnName.goodsName.name(), 350, JWT.LEFT, "��Ʒ����");
		columns[2] = new STableColumn(NewProduceOrderProcessor.ColumnName.goodsSpec.name(), 150, JWT.LEFT, "���");
		columns[3] = new STableColumn(NewProduceOrderProcessor.ColumnName.goodsUnit.name(), 150, JWT.CENTER, "��λ");
		columns[4] = new SNumberEditColumn(NewProduceOrderProcessor.ColumnName.count.name(), 150, JWT.RIGHT, "����");
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		GoodsItemInfo item = (GoodsItemInfo)element;
		switch(columnIndex) {
		case 0:
			return item.getItemData().getGoodsItemNo();
		case 1:
			return item.getBaseInfo().getName();
		case 2:
			return item.getItemData().getSpec();
		case 3:
			return item.getItemData().getUnit();
		}
		return null;
	}

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		
		new Label(headerLeftArea).setText("����ѡ����Ʒ��");
		
		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setText(" �����Ʒ ");
		button.setID(NewProduceOrderProcessor.ID_Button_AddGoods);
		
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" �� �� ");
		button.setID(NewProduceOrderProcessor.ID_Button_Summary);
	}
	
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

}
