package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获取收货地址
 * GetTenantsDeliveryAddrItemKey &DeliveryAddressInfo
 */
public class GetTenantsDeliveryAddrItemKey {
	
	//租户Id
	private GUID tenantsId;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

}
