package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 修改租户简称
 * @author Administrator
 *
 */
public class UpdateTenantShortNameTask extends SimpleTask {

	private String BossMobile;//手机号码
	private String sendMsg;//发送内容
	private GUID TenantsId;//租户id

	public String getBossMobile() {
		return BossMobile;
	}

	public void setBossMobile(String bossMobile) {
		BossMobile = bossMobile;
	}

	public String getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}

	public GUID getTenantsId() {
		return TenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		TenantsId = tenantsId;
	}
}