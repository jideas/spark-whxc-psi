package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;

public final class GoodsListOnSaleRender extends GoodsListRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender(); 
		Button delButton = new Button(footerRightArea, JWT.APPEARANCE3);
		delButton.setID(GoodsListOnSaleProcessor.ID_Button_Export777);
		delButton.setText(" �������ӳ����� ");
	}
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(GoodsListOnSaleProcessor.ColumnName.name.name(), 150, JWT.LEFT, "��Ʒ����");
		columns[1] = new STableColumn(GoodsListOnSaleProcessor.ColumnName.code.name(), 150, JWT.LEFT, "���");
		columns[2] = new STableColumn(GoodsListOnSaleProcessor.ColumnName.shelfLife.name(), 150, JWT.CENTER, "������");
		columns[3] = new STableColumn(GoodsListOnSaleProcessor.ColumnName.warningDay.name(), 150, JWT.CENTER, "Ԥ������");

		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		
		columns[0].setSortable(true);
		columns[1].setSortable(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		GoodsInfo goodsInfo = (GoodsInfo)element;
		switch(columnIndex) {
		case 0:
			return StableUtil.toLink("detail", "aaa", goodsInfo.getName());
		case 1:
			return goodsInfo.getCode();
		case 2:
			return goodsInfo.getShelfLife() + "��";
		case 3:
			return goodsInfo.getWarningDay() + "��";
		}
		return null;
	}

	@Override
	protected String getStatusButtonText() {
		return "ͣ����Ʒ";
	}

}
