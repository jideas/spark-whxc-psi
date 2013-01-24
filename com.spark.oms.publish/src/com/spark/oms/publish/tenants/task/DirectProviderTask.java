package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DirectProviderTask extends SimpleTask {

	private GUID RECID;
	private String directProviderFlag;
	private GUID tenantsRECID;
	private String productSerial;
	
	public GUID getRECID() {
		return RECID;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	public String getDirectProviderFlag() {
		return directProviderFlag;
	}
	public void setDirectProviderFlag(String directProviderFlag) {
		this.directProviderFlag = directProviderFlag;
	}
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
