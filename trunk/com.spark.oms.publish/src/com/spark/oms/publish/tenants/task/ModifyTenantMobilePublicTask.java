package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 对各业务系统公布的修改运营中租户手机号码的任务
 *
 *
 */
public class ModifyTenantMobilePublicTask extends SimpleTask {

	
	private GUID tenantRecid;
	
	private String mobilePhone;
	
	//private String oldMobilePhone;//暂时不用，修改前的手机号码，是否需要加强检验判断，以防乱修改手机号码
	
	public GUID getTenantRecid() {
		return tenantRecid;
	}
	public void setTenantRecid(GUID tenantRecid) {
		this.tenantRecid = tenantRecid;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}