package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �Ը�ҵ��ϵͳ�������޸���Ӫ���⻧�ֻ����������
 *
 *
 */
public class ModifyTenantMobilePublicTask extends SimpleTask {

	
	private GUID tenantRecid;
	
	private String mobilePhone;
	
	//private String oldMobilePhone;//��ʱ���ã��޸�ǰ���ֻ����룬�Ƿ���Ҫ��ǿ�����жϣ��Է����޸��ֻ�����
	
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