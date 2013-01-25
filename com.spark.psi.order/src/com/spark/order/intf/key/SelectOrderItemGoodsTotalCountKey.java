package com.spark.order.intf.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;

/**
 * <p>��ѯ������ϸ��Ʒ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

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
