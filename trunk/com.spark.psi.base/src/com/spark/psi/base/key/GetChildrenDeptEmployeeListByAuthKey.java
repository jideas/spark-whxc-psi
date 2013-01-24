package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * 
 * <p>查询当前部门及子部门的拥有指定职能的员工列表</p>
 *


 *
 
 * @version 2012-3-14
 */
public class GetChildrenDeptEmployeeListByAuthKey extends GetEmployeeListByAuthKey {
	
	/**
	 * 部门id
	 */
	private GUID departmentId;
	
	/**
	 * 部门的层级深度
	 */
	private Level level = Level.Max;
	
	/**
	 * 查询子部门指定职能的员工列表（包含指定部门的员工）
	 * @param departmentId 部门id
	 */
	public GetChildrenDeptEmployeeListByAuthKey(GUID departmentId){
	    this.departmentId = departmentId;
    }
	
	/**
	 * 查询子部门指定职能的员工列表（包含指定部门的员工）
	 * @param auths 职能列表
	 * @param departmentId 部门id
	 */
	public GetChildrenDeptEmployeeListByAuthKey(GUID departmentId,Auth... auths){
	    super(auths);
	    this.departmentId = departmentId;
    }
	
	/**
	 * 查询子部门指定职能的员工列表（包含指定部门的员工）
	 * @param auths 职能列表
	 * @param departmentId 部门id
	 * @param level 查询深度
	 */
	public GetChildrenDeptEmployeeListByAuthKey(GUID departmentId,Level level,Auth... auths){
		this(departmentId,auths);
	}

	public GUID getDepartmentId(){
    	return departmentId;
    }

	public Level getLevel(){
    	return level;
    }
	
}
