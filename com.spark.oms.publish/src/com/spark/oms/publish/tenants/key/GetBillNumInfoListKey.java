package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

public class GetBillNumInfoListKey {

	/**
	 * �⻧
	 */
	private GUID tenantsRECID;
	
	/**
	 * ��Ʒϵ��
	 */
	private String productSerial;

	public GUID getTenantsRECID() {
		return tenantsRECID;
	}

	public void setTenantsRECID(GUID tenantsRECID) {
		this.tenantsRECID = tenantsRECID;
	}

	public String getProductSerial() {
		return productSerial;
	}

	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}
	
	

}
