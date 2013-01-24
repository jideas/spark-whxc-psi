package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.goods.entity.GoodsTraderLogItem;

/**
 * 指定商品的采购情况列表视图
 */
public class GoodsPurchaseSummaryRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {
//		super.afterFooterRender();
		
		footerArea.dispose();
		
		new Label(headerLeftArea).setText("编号/条码：");
		
		new Label(headerLeftArea)
				.setID(GoodsPurchaseSummaryProcessor.ID_LABEL_GOODSNUMBER);
		
		new Label(headerLeftArea).setText("    ");
		
		new Label(headerLeftArea).setText("商品名称：");
		
		new Label(headerLeftArea)
				.setID(GoodsPurchaseSummaryProcessor.ID_LABEL_GOODSNAME);
	}
	
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn("", 100, JWT.CENTER, "供应商");
		columns[1] = new STableColumn("", 100, JWT.CENTER, "商品属性");
		columns[1].setGrab(true);
		columns[2] = new STableColumn("", 50, JWT.CENTER, "单位");
		columns[3] = new STableColumn("", 65, JWT.CENTER, "采购次数");
		
		columns[4] = new STableColumn("", 90, JWT.CENTER, "累计采购数量");
		columns[5] = new STableColumn("", 90, JWT.CENTER, "最近采购数量");
		columns[6] = new STableColumn("", 90, JWT.CENTER, "最近采购单价");
		columns[7] = new STableColumn("", 90, JWT.CENTER, "最近采购日期");
		
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		GoodsTraderLogItem item = (GoodsTraderLogItem)element;
		switch(columnIndex) {
		case 0:
			return item.getPartnerName();
		case 1:
			return item.getProperty();
		case 2:
			return item.getUnit();
		case 3:
			return String.valueOf(item.getCount());
		case 4:
			return item.getTotalTraderCount();
		case 5:
			return item.getRecentCount();
		case 6:
			return DoubleUtil.getRoundStr(item.getRecentPrice());
		case 7:
			return DateUtil.dateFromat(item.getRecentDate());
		default:
			return "";
		}
	}
}
