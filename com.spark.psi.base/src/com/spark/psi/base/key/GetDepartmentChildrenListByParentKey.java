package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>根据父id获得子部门列表</p>
 *


 *
 
 * @version 2012-3-8
 */
public class GetDepartmentChildrenListByParentKey{
	
	private final GUID parent;
	
	public GetDepartmentChildrenListByParentKey(final GUID parent){
		this.parent = parent;
	}

	public GUID getParent(){
    	return parent;
    }
	
	
}
