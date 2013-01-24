package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * 
 * <p> 查询所有祖先部门（从本级一直追溯到公司级一水儿的部门）指定职能的员工列表（包含指定部门的员工）</p>
 *


 *
 
 * @version 2012-3-15
 */
public class GetAncestorDeptEmployeeListByAuthKey extends
        GetEmployeeListByAuthKey
{

	/**
	 * 部门id
	 */
	private GUID departmentId;
	
	/**
	 * 部门的层级深度
	 */
	private Level level = Level.Max;
	
	/**
	 * 查询指定部门的祖先部门
	 * @param departmentId 部门id
	 */
	public GetAncestorDeptEmployeeListByAuthKey(GUID departmentId){
		super(new Auth[0]);
		this.departmentId = departmentId;
	}
	
	/**
	 * 查询所有祖先部门（从本级一直追溯到公司级一水儿的部门）指定职能的员工列表（包含指定部门的员工）
	 * @param auths 职能列表
	 * @param departmentId 部门id
	 * 暂时未实现
	 */
	public GetAncestorDeptEmployeeListByAuthKey(Auth[] auths,GUID departmentId){
	    super(auths);
	    this.departmentId = departmentId;
    }
	
	/**
	 * 查询所有祖先部门（从本级一直追溯到公司级一水儿的部门）指定职能的员工列表（包含指定部门的员工）
	 * @param auths 职能列表
	 * @param departmentId 部门id
	 * @param level 查询深度
	 * 暂时未实现
	 */
	private GetAncestorDeptEmployeeListByAuthKey(Auth[] auths,GUID departmentId,Level level){
		this(auths,departmentId);
	}

	public GUID getDepartmentId(){
    	return departmentId;
    }
	
	public int getLevel(){
    	return level.getLevel();
    }
	
	

}
