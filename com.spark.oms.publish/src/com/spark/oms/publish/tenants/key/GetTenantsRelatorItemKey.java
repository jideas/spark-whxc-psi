package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获得客户联系人列表
 * GetTenantsRelatorItemKey & ContactInfo
 *
 */
public class GetTenantsRelatorItemKey {
	
	//租户Id
	private GUID tenantsId;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

}
