package com.spark.psi.base.browser.bom;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.base.browser.goods.GoodsListProcessor;
import com.spark.psi.publish.base.goods.entity.ShortGoodsItem;

public final class BOM_GoodsItemListRender extends PSIGoodsListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();

		//
		new Label(headerLeftArea).setText("商品数量：");
		Label label = new Label(headerLeftArea);
		label.setID(GoodsListProcessor.ID_Label_Count);
		GridData gdLabel = new GridData();
		gdLabel.widthHint = 120;
		label.setLayoutData(gdLabel);
		new Label(headerLeftArea).setText("  ");

	}

	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setSelectionMode(SSelectionMode.None);
		tableStyle.setRowHeight(24);
		return tableStyle;
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(BOM_GoodsItemListProcessor.ColumnName.code.name(), 150, JWT.LEFT, "商品编号");
		columns[1] = new STableColumn(BOM_GoodsItemListProcessor.ColumnName.goodsNo.name(), 150, JWT.LEFT, "商品条码");
		columns[2] = new STableColumn(BOM_GoodsItemListProcessor.ColumnName.name.name(), 150, JWT.LEFT, "商品名称");
		columns[3] = new STableColumn(BOM_GoodsItemListProcessor.ColumnName.goodsSpec.name(), 150, JWT.LEFT, "商品规格");
		columns[4] = new STableColumn(BOM_GoodsItemListProcessor.ColumnName.unit.name(), 80, JWT.CENTER, "商品单位");
		columns[5] = new STableColumn(BOM_GoodsItemListProcessor.ColumnName.bomStatus.name(), 100, JWT.CENTER, "BOM状态");
//		columns[6] = new STableColumn(GoodsListOnSaleProcessor.ColumnName.name.name(), 150, JWT.LEFT, "销售价格");
//		columns[7] = new STableColumn(GoodsListOnSaleProcessor.ColumnName.name.name(), 150, JWT.LEFT, "原价");

		columns[2].setGrab(true); 

		columns[0].setSortable(true); 
		columns[2].setSortable(true); 
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		ShortGoodsItem goods = (ShortGoodsItem) element;
		switch (columnIndex) {
		case 0:
			return goods.getGoodsCode();
		case 1:
			return goods.getGoodsNo();
		case 2:
			return StableUtil.toLink("detail", "aaa", goods.getName());
		case 3:
			return goods.getGoodsSpec();
		case 4:
			return goods.getGoodsUnit();
		case 5:
			return goods.getBomStatus();
			// case 6:
			// return DoubleUtil.getRoundStr(goods.getSalesPrice());
			// case 7:
			// return DoubleUtil.getRoundStr(goods.getOriginalPrice());
		}
		return null;
	}

	protected String getStatusButtonText() {

		return "停售商品";
	}

}
