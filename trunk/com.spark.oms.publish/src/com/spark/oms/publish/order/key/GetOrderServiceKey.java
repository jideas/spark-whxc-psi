package com.spark.oms.publish.order.key;

import com.jiuqi.dna.core.type.GUID;
/**
 * ��ȡ�����������
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
