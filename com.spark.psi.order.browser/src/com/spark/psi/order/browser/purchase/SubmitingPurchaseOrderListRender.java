/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.order.browser.util.OrderListProcessor.Columns;

/**
 * 待提交采购订单列表视图
 *
 */
public class SubmitingPurchaseOrderListRender extends PurchaseOrderListRender{

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		new Label(headerLeftArea, JWT.APPEARANCE3).setText("请先处理已退回订单，再新建采购订单!");
		new Button(footerLeftArea,JWT.APPEARANCE3).setID(SubmitingPurchaseOrderListProcessor.ID_BUTTON_NEW_ORADER);
		new Label(footerLeftArea).setText("  ");
		new Button(footerLeftArea,JWT.APPEARANCE3).setID(SubmitingPurchaseOrderListProcessor.ID_BUTTON_SUBMIT_ORADER);
	}
//	
//	@Override
//	protected void afterHeaderRender() {
//		super.afterHeaderRender();
//		new Label(headerLeftArea, JWT.APPEARANCE3).setText("请先处理已退回订单，再新建采购订单!");
//	}

	@Override
	protected Columns[] getColumnsEnumList() {
		return new Columns[]{Columns.DeliveryDate, Columns.OrderNumber, Columns.PartnerName, Columns.Type, Columns.Amount, Columns.Creator, Columns.CreateDate};
	}

	@Override
	public STableStyle getTableStyle() {
		//设置复选
		STableStyle t = new STableStyle();
		t.setPageAble(false);
		t.setSelectionMode(SSelectionMode.Multi);
		return t;
	}
}
