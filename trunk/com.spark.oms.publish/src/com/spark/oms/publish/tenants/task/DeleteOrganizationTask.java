package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteOrganizationTask extends SimpleTask {
	
	private GUID organizationId;
	private String DelMark;

	public String getDelMark() {
		return DelMark;
	}

	public void setDelMark(String delMark) {
		DelMark = delMark;
	}

	public GUID getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(GUID organizationId) {
		this.organizationId = organizationId;
	}
}