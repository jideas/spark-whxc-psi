/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.spark.psi.order.browser.util.OrderListProcessor.Columns;


/**
 * 待审批的采购订单列表视图
 *
 */
public class ApprovalingPurchaseOrderListRender extends PurchaseOrderListRender{
	
	@Override
	protected Columns[] getColumnsEnumList() {
		return new Columns[]{Columns.DeliveryDate, Columns.OrderNumber, Columns.PartnerName, Columns.Type, Columns.Amount, Columns.Creator, Columns.CreateDate};
	}
}
