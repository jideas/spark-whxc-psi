package com.spark.oms.publish.order.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获取订单服务配置信息
 */
public class GetOrderServiceConfigKey {
	private GUID serviceId;

	public GUID getServiceId() {
		return serviceId;
	}

	public void setServiceId(GUID serviceId) {
		this.serviceId = serviceId;
	}
	

}
