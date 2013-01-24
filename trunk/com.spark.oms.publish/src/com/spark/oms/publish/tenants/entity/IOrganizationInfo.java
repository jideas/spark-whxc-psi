package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;

public interface IOrganizationInfo {

	public GUID getOrganizationId();

	public String getOrganizationName();

	public GUID getOrganizationParent();

	public int getSort();

	public GUID getTenantsRECID();

	public String getProductSerial();
}
