package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获取银行账户列表
 *  GetTenantsAccountItemKey & List<AccountInfo>
 */
public class GetTenantsAccountItemKey {
	//租户信息
	private GUID tenantsId;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	

}
