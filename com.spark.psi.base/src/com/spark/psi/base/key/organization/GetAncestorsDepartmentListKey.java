package com.spark.psi.base.key.organization;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>查询指定部门的所有祖先部门列表</p>
 *


 *
 
 * @version 2012-4-6
 */
public class GetAncestorsDepartmentListKey{
	
	
	private GUID deptId;
	
	public GetAncestorsDepartmentListKey(GUID deptId){
		this.deptId = deptId;
	}

	public GUID getDeptId(){
    	return deptId;
    }

	
}
