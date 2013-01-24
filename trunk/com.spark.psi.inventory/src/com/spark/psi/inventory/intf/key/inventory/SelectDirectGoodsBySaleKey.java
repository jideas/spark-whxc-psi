package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * �������۶�����ֱ����Ʒ
 * @author modi
 *
 */
public class SelectDirectGoodsBySaleKey {
	private GUID tenantsGuid;
	private final GUID saleRecid;
	public SelectDirectGoodsBySaleKey(GUID saleRecid) {
		super();
		this.saleRecid = saleRecid;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	public GUID getSaleRecid() {
		return saleRecid;
	}
}
