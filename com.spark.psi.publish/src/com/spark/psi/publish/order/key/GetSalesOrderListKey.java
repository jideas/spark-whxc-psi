package com.spark.psi.publish.order.key;


/**
 * 
 * <p>获得销售订单、退货单列表</p>
 *


 *
 
 * @version 2012-3-12
 */
public class GetSalesOrderListKey extends GetOrderListKey{
	public GetSalesOrderListKey(int offset, int count, boolean queryTotal, OrderType... orderTypes){
		super(offset, count, queryTotal, orderTypes);
	}
	public GetSalesOrderListKey(int offset, int count, boolean queryTotal){
		super(offset, count, queryTotal, OrderType.values());
	}
}
