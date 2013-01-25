package com.spark.psi.publish.base.organization.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.EmployeeStatus;

/**
 * 用户信息 <br>
 * 查询方法：根据ID查询EmployeeItem列表
 */
public interface EmployeeItem {
	
	
	/**
	 * 获得可操作动作列表
	 * 
	 * @return Action[]
	 */
	public Action[] getActions();

	/**
	 * id
	 * 
	 * @return GUID
	 */
	public GUID getId();

	/**
	 * 员工姓名
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * 手机号码
	 * 
	 * @return String
	 */
	public String getMobileNo();

	/**
	 * 身份证号码
	 * 
	 * @return String
	 */
	public String getIdNumber();

	/**
	 * 生日
	 * 
	 * @return long
	 */
	public long getBirthday();

	/**
	 * 邮箱
	 * 
	 * @return String
	 */
	public String getEmail();

	/**
	 * 职务
	 * 
	 * @return String
	 */
	public String getPosition();

	/**
	 * 角色
	 * 
	 * @return int
	 */
	public int getRoles();

	/**
	 * 角色名称
	 * 
	 * @return String
	 */
	public String getRolesInfo();
 
	/**
	 * 部门
	 * 
	 * @return String
	 */
	public GUID getDepartmentId();

	/**
	 * 部门名称
	 * 
	 * @return String
	 */
	public String getDepartmentName();

	/**
	 * 员工状态
	 * 在职 or 离职
	 * 
	 * @return EmployeeStatus
	 */
	public EmployeeStatus getStatus();

	
	
	
}
