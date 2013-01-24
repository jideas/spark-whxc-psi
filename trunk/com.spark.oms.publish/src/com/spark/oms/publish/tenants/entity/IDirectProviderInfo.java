package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;

public interface IDirectProviderInfo {

	public GUID getRECID();

	public String getDirectProviderFlag();

	public GUID getTenantsRECID();

	public String getProductSerial();
}