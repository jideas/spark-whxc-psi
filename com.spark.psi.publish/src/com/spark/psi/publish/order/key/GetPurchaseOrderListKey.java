package com.spark.psi.publish.order.key;


/**
 * 
 * <p>查询采购订单、采购退货列表</p>
 *


 *
 
 * @version 2012-3-12
 */
public class GetPurchaseOrderListKey extends GetOrderListKey{
	public GetPurchaseOrderListKey(int offset, int count, boolean queryTotal, OrderType... orderTypes){
		super(offset, count, queryTotal, orderTypes);
	}
	public GetPurchaseOrderListKey(int offset, int count, boolean queryTotal){
		super(offset, count, queryTotal, OrderType.values());
	}
}
