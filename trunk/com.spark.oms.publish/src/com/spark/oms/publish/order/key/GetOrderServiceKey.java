package com.spark.oms.publish.order.key;

import com.jiuqi.dna.core.type.GUID;
/**
 * 获取服务运行情况
 * GetOrderRunstatusKey&OrderServiceInfo
 */
public class GetOrderServiceKey {
	private GUID serviceId;

	public GUID getServiceId() {
		return serviceId;
	}

	public void setServiceId(GUID serviceId) {
		this.serviceId = serviceId;
	}
	
}
