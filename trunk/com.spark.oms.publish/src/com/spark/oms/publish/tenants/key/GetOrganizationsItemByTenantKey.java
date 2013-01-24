package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

public class GetOrganizationsItemByTenantKey {

	private GUID tenant;

	public GUID getTenant() {
		return tenant;
	}

	public void setTenant(GUID tenant) {
		this.tenant = tenant;
	}
}