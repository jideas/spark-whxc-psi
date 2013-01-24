package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;

public final class GoodsListOnSaleJointRender extends GoodsListRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(GoodsListOnSaleJointProcessor.ColumnName.supplier.name(), 120, JWT.LEFT, "供应商");
		columns[1] = new STableColumn(GoodsListOnSaleJointProcessor.ColumnName.name.name(), 150, JWT.LEFT, "商品名称");
		columns[2] = new STableColumn(GoodsListOnSaleJointProcessor.ColumnName.code.name(), 150, JWT.LEFT, "编号");
		columns[3] = new STableColumn(GoodsListOnSaleJointProcessor.ColumnName.shelfLife.name(), 150, JWT.CENTER, "保质期");
		columns[4] = new STableColumn(GoodsListOnSaleJointProcessor.ColumnName.warningDay.name(), 150, JWT.CENTER, "预警天数");

		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		columns[4].setGrab(true);
		
		columns[0].setSortable(true);
		columns[1].setSortable(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		GoodsInfo goodsInfo = (GoodsInfo)element;
		switch(columnIndex) {
		case 0:
//			return goodsInfo.getSupplier();
		case 1:
			return StableUtil.toLink("detail", "", goodsInfo.getName());
		case 2:
			return goodsInfo.getCode();
		case 3:
			return goodsInfo.getShelfLife() + "天";
		case 4:
			return goodsInfo.getWarningDay() + "天";
		}
		return null;
	}
	
	@Override
	protected String getStatusButtonText() {
		return "停售商品";
	}
}