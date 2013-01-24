package com.spark.psi.base.key.organization;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * 
 * <p>获得直属下级拥有指定职能的部门</p>
 *


 *
 
 * @version 2012-4-6
 */
public class GetChildrenDepartmentListKey{
	
	private GUID deptId;
	
	private Auth[] auths;
	
	public GetChildrenDepartmentListKey(GUID deptId,Auth... auths){
		this.deptId = deptId;
		this.auths = auths;
	}

	public GUID getDeptId() {
		return deptId;
	}

	public Auth[] getAuths() {
		return auths;
	}

}
