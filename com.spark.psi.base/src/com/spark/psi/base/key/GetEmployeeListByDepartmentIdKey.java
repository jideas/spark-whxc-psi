package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;


/**
 * 
 * <p>查询指定部门（不包含子部门 )的员工列表</p>
 *


 *
 
 * @version 2012-5-18
 */
public class GetEmployeeListByDepartmentIdKey{

	private Auth[] auths;
	
	private GUID departmentId;
	
	public GetEmployeeListByDepartmentIdKey(GUID departmentId){
	    this.departmentId = departmentId;
    }
	
	public GetEmployeeListByDepartmentIdKey(GUID departmentId,Auth... auths){
	    this.departmentId = departmentId;
	    this.auths = auths;
    }

	public Auth[] getAuths(){
    	return auths;
    }

	public GUID getDepartmentId(){
    	return departmentId;
    }
	
}
