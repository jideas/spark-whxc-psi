/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.spark.psi.order.browser.util.OrderListProcessor.Columns;



/**
 * 待审批的销售订单列表视图
 *
 */
public class ApprovalingSalesOrderListRender extends SalesOrderListRender{

	@Override
	protected Columns[] getColumnsEnumList() {
		return new Columns[]{Columns.DeliveryDate, Columns.OrderNumber, Columns.PartnerName,
				Columns.Type, Columns.Amount, Columns.Creator, Columns.CreateDate};
	}
	
}
