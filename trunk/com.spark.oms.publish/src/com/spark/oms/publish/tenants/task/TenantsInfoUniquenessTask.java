package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class TenantsInfoUniquenessTask extends SimpleTask {
	
	// �⻧��ʶ
	private GUID tenantId;
	// ������д
	private String shortName;
	// ����
	private String name;
	// �ܾ����ֻ�
	private String bossMobile;
	
	private boolean Status = false;

	public GUID getTenantId() {
		return tenantId;
	}

	public void setTenantId(GUID tenantId) {
		this.tenantId = tenantId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBossMobile() {
		return bossMobile;
	}

	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
}