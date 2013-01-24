package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class OrganizationTask extends SimpleTask {
	
	private GUID organizationId;
	private String organizationName;
	private GUID organizationParent;
	private int sort;
	private GUID TenantsRECID;
	private String ProductSerial;
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

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public GUID getOrganizationParent() {
		return organizationParent;
	}

	public void setOrganizationParent(GUID organizationParent) {
		this.organizationParent = organizationParent;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public GUID getTenantsRECID() {
		return TenantsRECID;
	}

	public void setTenantsRECID(GUID tenantsRECID) {
		TenantsRECID = tenantsRECID;
	}

	public String getProductSerial() {
		return ProductSerial;
	}

	public void setProductSerial(String productSerial) {
		ProductSerial = productSerial;
	}
	
	//加操作成功标志与错误提示字符串
	private boolean isSuccess = false;
	
	private String eMsg;

	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String geteMsg() {
		return eMsg;
	}
	public void seteMsg(String eMsg) {
		this.eMsg = eMsg;
	}	
}
