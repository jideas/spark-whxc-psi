package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;

/**
 * 供应商的授权商品列表视图
 */
public class AuthorizedGoodsListRender extends PSIListPageRender {
	
	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		
		new Label(headerLeftArea).setText("授权商品数量：");
		new Label(headerLeftArea).setID(AuthorizedGoodsListProcessor.ID_LABEL_B2BCOUNT);
		new Label(headerLeftArea).setText("    ");		
		new Label(headerLeftArea).setText("未关联商品数量：");		
		new Label(headerLeftArea).setID(AuthorizedGoodsListProcessor.ID_LABEL_UNB2BCOUNT);
	}
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		
		columns[0] = new STableColumn("", 200, JWT.CENTER, "授权供应商");		
		columns[1] = new STableColumn("", 150, JWT.CENTER, "商品名称");
		columns[2] = new STableColumn("", 150, JWT.CENTER, "商品属性");
		columns[3] = new STableColumn("", 150, JWT.CENTER, "价格");
		columns[4] = new STableColumn("", 150, JWT.CENTER, "编号&frasl; 条码","关联商品");//转义字符
		columns[5] = new STableColumn("", 200, JWT.CENTER, "商品名称","关联商品");
		columns[6] = new STableColumn("", 200, JWT.CENTER, "商品属性","关联商品");
		columns[7] = new STableColumn("", 200, JWT.CENTER, "单位","关联商品");
		
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	public String getText(Object element, int columnIndex) {
//		AuthorizedGoodsItemItem item = (AuthorizedGoodsItemItem) element;
		switch (columnIndex) {
		case 0:
			return "";	
		default:
			return "";
		}
	}
}
