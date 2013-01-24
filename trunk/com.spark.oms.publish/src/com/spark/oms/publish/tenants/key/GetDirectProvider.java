package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

public class GetDirectProvider {

	private GUID tenantsRECID;

	private String productSerial;

	public GUID getTenantsRECID() {
		return tenantsRECID;
	}

	public String getProductSerial() {
		return productSerial;
	}

	public GetDirectProvider(GUID tenantsRECID, String productSerial) {
		super();
		this.tenantsRECID = tenantsRECID;
		this.productSerial = productSerial;
	}

}