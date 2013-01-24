package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

public class GetBillNumInfoKey {

	private GUID RECID;

	private String billCode;
	
	private GUID tenantsRECID;
	
	private GUID serviceRECID;

	
	public GetBillNumInfoKey(GUID rECID, String billCode, GUID tenantsRECID,
			GUID serviceRECID) {
		super();
		RECID = rECID;
		this.billCode = billCode;
		this.tenantsRECID = tenantsRECID;
		this.serviceRECID = serviceRECID;
	}

	public GUID getRECID() {
		return RECID;
	}

	public String getBillCode() {
		return billCode;
	}

	public GUID getTenantsRECID() {
		return tenantsRECID;
	}

	public GUID getServiceRECID() {
		return serviceRECID;
	}
	
	
}