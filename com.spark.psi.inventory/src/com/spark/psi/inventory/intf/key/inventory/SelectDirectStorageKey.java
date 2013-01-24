package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * 查询当租户直供库存,默认查询当前租户的
 * @author modi
 *
 */
public class SelectDirectStorageKey {
	private final GUID tenantsGuid;
	

	public SelectDirectStorageKey(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}

	/**
	 * @return the tenantsGuid
	 */
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	
}
