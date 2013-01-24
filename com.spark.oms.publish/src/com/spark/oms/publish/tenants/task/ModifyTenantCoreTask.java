package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 租户核心数据修改
 * 全称、简称、总经理姓名	须同步到认证
 * 总经理手机号码			须同步到认证并短信提示
 *
 */
public class ModifyTenantCoreTask extends SimpleTask {

	/**
	 * 租户Id，不能为空
	 */
	private GUID tenantRecid;

	/**
	 * 租户最新手机号码，可以为空
	 */
	private String mobilePhone;

	/**
	 * 是否发送短信提醒，默认为false不发送
	 */
	private boolean sendSMSTag;

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

	public boolean isSendSMSTag() {
		return sendSMSTag;
	}

	public void setSendSMSTag(boolean sendSMSTag) {
		this.sendSMSTag = sendSMSTag;
	}
}