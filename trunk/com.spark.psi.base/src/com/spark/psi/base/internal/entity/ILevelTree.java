package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 级次树对象接口
 
 *
 */
public interface ILevelTree {
	
	public String getPath();
	
	public GUID getId();
	
	public boolean isValid();
	
}
