package com.spark.psi.publish.base.organization.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EmployeeStatus;
import com.spark.psi.publish.QueryScope;

/**
 * 查询员工信息列表的键值对象
 * 
 */
public class GetEmployeeListKey {

	/**
	 * 是否查询整个公司范围的员工
	 */
	private boolean queryAll = true;

	/**
	 * 部门ID（queryAll为false时有效，为空时查询公司直属）
	 */
	private GUID departmentId;

	/**
	 * 员工状态
	 */
	private EmployeeStatus status;

	/**
	 * 角色
	 */
	private boolean authorized;
	
	/**
	 * 过滤范围
	 */
	private QueryScope queryScope;
	
	/**
	 * 角色控制过滤范围
	 */
	private int roleScope;

	/**
	 * @return the queryAll
	 */
	public boolean isQueryAll() {
		return queryAll;
	}

	/**
	 * @param queryAll
	 *            the queryAll to set
	 */
	public void setQueryAll(boolean queryAll) {
		this.queryAll = queryAll;
	}

	/**
	 * @return the departmentId
	 */
	public GUID getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(GUID departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the status
	 */
	public EmployeeStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}

	/**
	 * @return the authorized
	 */
	public boolean isAuthorized() {
		return authorized;
	}

	/**
	 * @param authorized
	 *            the authorized to set
	 */
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	public QueryScope getQueryScope(){
    	return queryScope;
    }

	public void setQueryScope(QueryScope queryScope){
    	this.queryScope = queryScope;
    }

	public int getRoleScope(){
    	return roleScope;
    }

	/**
	 * 角色控制过滤范围
	 * 0 不通过角色过滤
	 * 1 设置了角色的员工
	 * 2 没有设置角色的员工
	 * @param roleScope void
	 */
	public void setRoleScope(int roleScope){
    	this.roleScope = roleScope;
    }

	
}
