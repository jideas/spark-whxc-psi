package com.spark.oms.publish.base.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.FunctionType;

public interface FunctionItem {

	/**
	 * ¹¦ÄÜGUID
	 * @return
	 */
	public GUID getRECID();
	
	public String getMFCode();
	
	public String getMFName();
	
	public String getMFParent();
	
	public FunctionType getMFType();
}
