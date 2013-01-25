/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.order.browser.util.OrderListProcessor.Columns;

/**
 * 待提交销售订单列表视图
 *
 */
public class SubmitingSalesOrderListRender extends SalesOrderListRender{

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//下左边
		new Button(footerLeftArea,JWT.APPEARANCE3).setID(SubmitingSalesOrderListProcessor.ID_BUTTON_NEW_ORDER);
		new Label(footerLeftArea).setText(" ");
		new Button(footerLeftArea,JWT.APPEARANCE3).setID(SubmitingSalesOrderListProcessor.ID_BUTTON_SUBMIT_ORDER);
	}
	
	@Override
	protected Columns[] getColumnsEnumList() {
		return new Columns[]{Columns.DeliveryDate, Columns.OrderNumber, Columns.PartnerName,
				Columns.Type, Columns.Amount, Columns.Creator, Columns.CreateDate, Columns.status};
	}	

	public STableStyle getTableStyle() {		
		STableStyle t = new STableStyle();
		t.setPageAble(false);
		t.setSelectionMode(SSelectionMode.Multi);//设置复选
		return t;
	}
}