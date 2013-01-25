/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.spark.psi.order.browser.util.OrderListProcessor.Columns;



/**
 * �����������۶����б���ͼ
 *
 */
public class ApprovalingSalesOrderListRender extends SalesOrderListRender{

	@Override
	protected Columns[] getColumnsEnumList() {
		return new Columns[]{Columns.DeliveryDate, Columns.OrderNumber, Columns.PartnerName,
				Columns.Type, Columns.Amount, Columns.Creator, Columns.CreateDate};
	}
	
}
