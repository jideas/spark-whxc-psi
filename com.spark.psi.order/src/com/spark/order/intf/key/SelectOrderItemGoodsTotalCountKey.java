package com.spark.order.intf.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;

/**
 * <p>查询订单明细商品总数量</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-18
 */
public class SelectOrderItemGoodsTotalCountKey {
	private final GUID orderId;
	private final OrderEnum orderEnum;
	/**
	 * @param orderId
	 * @param billsEnum
	 */
	public SelectOrderItemGoodsTotalCountKey(GUID orderId, OrderEnum orderEnum) {
		super();
		this.orderId = orderId;
		this.orderEnum = orderEnum;
	}
	public GUID getOrderId() {
		return orderId;
	}
	public OrderEnum getOrderEnum() {
		return orderEnum;
	}
}
