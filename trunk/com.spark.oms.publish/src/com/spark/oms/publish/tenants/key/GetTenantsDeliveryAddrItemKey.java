package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ȡ�ջ���ַ
 * GetTenantsDeliveryAddrItemKey &DeliveryAddressInfo
 */
public class GetTenantsDeliveryAddrItemKey {
	
	//�⻧Id
	private GUID tenantsId;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

}
