package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

public class GetTenantsItemBySaleManagerKey {
	/**
	 * ������ԱRECID
	 */
	private GUID saleManagerRECID;
	
	/**
	 * �⻧����
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
