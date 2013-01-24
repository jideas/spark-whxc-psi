package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得部门的父节点ID</p>
 *


 *
 
 * @version 2012-3-8
 */
public class GetParentDepartmentIdKey{
	
	
	private final GUID id;
	
	public GetParentDepartmentIdKey(final GUID id){
		this.id = id;
	}

	public GUID getId(){
    	return id;
    }

	
}
