package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ����������ӿ�
 
 *
 */
public interface ILevelTree {
	
	public String getPath();
	
	public GUID getId();
	
	public boolean isValid();
	
}
