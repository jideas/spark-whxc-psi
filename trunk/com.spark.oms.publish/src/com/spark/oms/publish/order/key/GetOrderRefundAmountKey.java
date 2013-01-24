package com.spark.oms.publish.order.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获取停用服务可退款金额
 * @author mengyongfeng
 *
 */
public class GetOrderRefundAmountKey {
	private GUID orderServiceId;

	public GUID getOrderServiceId() {
		return orderServiceId;
	}

	public void setOrderServiceId(GUID orderServiceId) {
		this.orderServiceId = orderServiceId;
	}

	
	
	

}
