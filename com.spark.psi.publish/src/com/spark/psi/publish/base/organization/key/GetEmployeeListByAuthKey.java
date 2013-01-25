package com.spark.psi.publish.base.organization.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>通过部门和职能获得员工列表</p>
 *


 *
 
 * @version 2012-3-13
 */
public class GetEmployeeListByAuthKey extends LimitKey{
	
	/**
	 * 职能
	 */
	private Auth[] auths;

	/**
	 * 是否查询整个公司范围的员工
	 */
	private boolean queryAll = false;

	/**
	 * 部门ID（queryAll为false时有效，为空时查询公司直属）
	 */
	private GUID departmentId;

	/**
	 * 查询当前用户部门下(递归到最底层部门)拥有指定职能的员工列表
	 * @param auths 职能数组
	 */
	public GetEmployeeListByAuthKey(Auth... auths){
		super(0,20,false);
		this.auths = auths;
	}
	
	/**
	 * 查询整个公司拥有指定职能的员工列表
	 * @param queryAll 为true时查询整个公司
	 * @param auths
	 */
	public GetEmployeeListByAuthKey(boolean queryAll,Auth... auths){
		super(0,20,false);
		this.auths = auths;
		this.queryAll = queryAll;
	}
	
	/**
	 * 查询指定部门下(递归到最底层部门)拥有指定职能的员工列表
	 * @param departmentId
	 * @param auths
	 */
	public GetEmployeeListByAuthKey(GUID departmentId,Auth... auths){
		super(0,20,false);
		this.auths = auths;
		this.departmentId = departmentId;
	}

	public Auth[] getAuths(){
    	return auths;
    }

	public boolean isQueryAll(){
    	return queryAll;
    }

	public GUID getDepartmentId(){
    	return departmentId;
    }
	
	
}
