package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class UpdateTenantsBossInfoTask extends SimpleTask{
	private GUID tenantsId;
	private String bossMobile;
	public GUID getTenantsId() {
		return tenantsId;
	}
	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	public String getBossMobile() {
		return bossMobile;
	}
	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}
	
	

}
