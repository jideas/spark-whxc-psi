package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;

public interface ExamRecord {
	
	public GUID getRecid();
	public GUID getTenantsGuid();
	public GUID getUserGuid();
	public long getExamDate();
	public String getBusType();
	public String getBusTypeName();
	public String getStatus();

}
