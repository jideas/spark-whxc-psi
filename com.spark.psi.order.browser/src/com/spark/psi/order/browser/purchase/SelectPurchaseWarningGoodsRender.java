/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.order.browser.purchase.SelectPurchaseWarningGoodsProcessor.Columns;
import com.spark.psi.order.browser.purchase.SelectPurchaseWarningGoodsProcessor.SelectItem;
import com.spark.psi.order.browser.util.OrderUtilFactory;
import com.spark.psi.publish.inventory.entity.InventoryInfo;

/**
 * 从预警材料中选择采购材料视图
 *
 */
public class SelectPurchaseWarningGoodsRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		Button confirm = new Button(footerRightArea,JWT.APPEARANCE3);
		confirm.setID(SelectPurchaseWarningGoodsProcessor.ID_CONFIRM);
		confirm.setText(" 确认选择 ");
		new Label(footerRightArea).setText(" ");
		Button cancel = new Button(footerRightArea,JWT.APPEARANCE3);
		cancel.setID(SelectPurchaseWarningGoodsProcessor.ID_CANCEL);
		cancel.setText(" 放弃选择 ");
		
		//================左侧提示==============
		Label text = new Label(footerLeftArea);
		text.setText("说明： 采购建议=采购需求-采购中数量-库存数量+库存下限");
		text.setForeground(Color.COLOR_VIOLET);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[10];
		columns[0] = new STableColumn(Columns.GoodsName.name(), 100, JWT.LEFT, Columns.GoodsName.getTitle());
		columns[1] = new STableColumn(Columns.GoodsProperties.name(), 100, JWT.LEFT, Columns.GoodsProperties.getTitle());
		columns[2] = new STableColumn(Columns.GoodsUnit.name(), 50, JWT.RIGHT, Columns.GoodsUnit.getTitle());
		columns[3] = new STableColumn(Columns.Store.name(), 100, JWT.LEFT, Columns.Store.getTitle());
		columns[4] = new STableColumn(Columns.DeliveryingCount.name(), 100, JWT.RIGHT, Columns.DeliveryingCount.getTitle());
		columns[5] = new STableColumn(Columns.PurchasingCount.name(), 100, JWT.RIGHT, Columns.PurchasingCount.getTitle());
		columns[6] = new STableColumn(Columns.InventoryCount.name(), 100, JWT.RIGHT, Columns.InventoryCount.getTitle());
		columns[7] = new STableColumn(Columns.InventoryCountLimit.name(), 100, JWT.RIGHT, Columns.InventoryCountLimit.getTitle());
		columns[8] = new STableColumn(Columns.AdviseCount.name(), 100, JWT.RIGHT, Columns.AdviseCount.getTitle());
		columns[9] = new SNumberEditColumn(Columns.ThisPurchaseCount.name(), 100, JWT.RIGHT, Columns.ThisPurchaseCount.getTitle());
		OrderUtilFactory.addAllColumnParam(columns, true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		SelectItem item = (SelectItem)element;
		InventoryInfo gi = item.getGoodsInventoryDetail();
		switch (columnIndex) {
		case 0:
			return gi.getGoodsName();
		case 1:
	return gi.getGoodsProperties();
		case 2:
			return gi.getGoodsUnit();
		case 3:
			return gi.getStoreName();
		case 4:
			return DoubleUtil.getRoundStr(gi.getDeliveryingCount());
		case 5:
			return DoubleUtil.getRoundStr(gi.getPurchasingCount());
		case 6:
			return DoubleUtil.getRoundStr(gi.getCount());
		case 7:
			return getInventoryLimit(gi.getUpperLimitCount(), gi.getLowerLimitCount());
		case 8:
			return DoubleUtil.getRoundStr(gi.getAdviseCount());
		case 9:
			return item.getThisPurchaseCount() == null?null:item.getThisPurchaseCount()+"";
		default:
			return "";
		}
	}

	public static String getInventoryLimit(double upper, double lower){
		String up = upper>0?DoubleUtil.getRoundStr(upper):"——";
		String lo = lower>0?DoubleUtil.getRoundStr(lower):"——";
		return up+" / "+lo;
	}
	
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
}
