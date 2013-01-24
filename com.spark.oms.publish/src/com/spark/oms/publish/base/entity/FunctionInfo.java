package com.spark.oms.publish.base.entity;

import com.jiuqi.dna.core.type.GUID;

public interface FunctionInfo {
	
	public GUID getRECID();

	/**
	 * @return 功能模块编号
	 */
	public String getCode();

	/**
	 * @return 功能模块名称
	 */
	public String getName();
	
	/**
	 * @return 上级功能模块
	 */
	public String getParent();
	
	/**
	 * @return 功能模块类别
	 */
	public String getSort();
}
