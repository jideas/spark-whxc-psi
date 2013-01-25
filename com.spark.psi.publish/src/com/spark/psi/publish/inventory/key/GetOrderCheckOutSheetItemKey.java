package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 用于查询指定订单的出库单信息的key
 * 
 */
public class GetOrderCheckOutSheetItemKey {

	/**
	 * 订单id
	 */
	private GUID orderId;

	/**
	 * 
	 * @param orderId
	 */
	public GetOrderCheckOutSheetItemKey(GUID orderId) {
		super();
		this.orderId = orderId;
	}

	/**
	 * @return the orderId
	 */
	public GUID getOrderId() {
		return orderId;
	}

}
