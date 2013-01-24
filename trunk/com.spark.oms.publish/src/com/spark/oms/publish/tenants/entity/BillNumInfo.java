package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;

public interface BillNumInfo {
	
	public GUID getRECID();

	public String getBillCode();

	public String getIsAutoGen();

	public String getPrefix();

	public int getScale();

	public String getFirstSerial();

	public String getYearFlag();

	public String getMonthFlag();

	public String getDayFlag();

	public GUID getTenantsRECID();

	public GUID getServiceRECID();
}
