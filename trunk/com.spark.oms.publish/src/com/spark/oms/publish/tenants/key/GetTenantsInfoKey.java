package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ȡ�⻧��Ϣ
 * GetTenantsInfoKey & TenantsDetailInfo
 */
public class GetTenantsInfoKey {

	//�⻧Id
	private GUID tenantsId;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
}
