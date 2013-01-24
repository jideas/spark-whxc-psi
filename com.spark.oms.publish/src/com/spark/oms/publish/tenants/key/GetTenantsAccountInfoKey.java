package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获取租户账户信息
 * GetTenantsAccountInfoKey & AccountInfo
 */
public class GetTenantsAccountInfoKey {
	private GUID id;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}
}
