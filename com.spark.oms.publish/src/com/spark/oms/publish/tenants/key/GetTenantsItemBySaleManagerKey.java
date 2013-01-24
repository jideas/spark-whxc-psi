package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

public class GetTenantsItemBySaleManagerKey {
	/**
	 * 销售人员RECID
	 */
	private GUID saleManagerRECID;
	
	/**
	 * 租户名称
	 */
	private String tenantsName;

	public GUID getSaleManagerRECID() {
		return saleManagerRECID;
	}

	public void setSaleManagerRECID(GUID saleManagerRECID) {
		this.saleManagerRECID = saleManagerRECID;
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

}
