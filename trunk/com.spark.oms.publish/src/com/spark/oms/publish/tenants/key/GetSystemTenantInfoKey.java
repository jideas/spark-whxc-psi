package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

public class GetSystemTenantInfoKey {

	// ��ʶ
	private GUID id;
	// ����
	private String name;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}