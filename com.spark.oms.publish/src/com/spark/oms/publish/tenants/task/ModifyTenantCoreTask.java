package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �⻧���������޸�
 * ȫ�ơ���ơ��ܾ�������	��ͬ������֤
 * �ܾ����ֻ�����			��ͬ������֤��������ʾ
 *
 */
public class ModifyTenantCoreTask extends SimpleTask {

	/**
	 * �⻧Id������Ϊ��
	 */
	private GUID tenantRecid;

	/**
	 * �⻧�����ֻ����룬����Ϊ��
	 */
	private String mobilePhone;

	/**
	 * �Ƿ��Ͷ������ѣ�Ĭ��Ϊfalse������
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