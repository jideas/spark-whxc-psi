package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * �޸��⻧���
 * @author Administrator
 *
 */
public class UpdateTenantShortNameTask extends SimpleTask {

	private String BossMobile;//�ֻ�����
	private String sendMsg;//��������
	private GUID TenantsId;//�⻧id

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