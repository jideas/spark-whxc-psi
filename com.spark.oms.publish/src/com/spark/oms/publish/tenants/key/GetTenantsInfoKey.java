package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获取租户信息
 * GetTenantsInfoKey & TenantsDetailInfo
 */
public class GetTenantsInfoKey {

	//租户Id
	private GUID tenantsId;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
}
