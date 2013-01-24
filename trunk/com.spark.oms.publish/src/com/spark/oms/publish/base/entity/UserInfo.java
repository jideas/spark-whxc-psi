package com.spark.oms.publish.base.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.EnabledStatus;
import com.spark.oms.publish.UserStatus;
import com.spark.oms.publish.UserType;

/**
 * 用于用户设置编辑使用
 * 
 *
 */
public interface UserInfo {
	
	/**
	 * 用户(销售)id
	 * @return
	 */
	public GUID getRECID();

	/**
	 * 用户(销售)编号
	 * @return
	 */
	public String getUserCode();
	
	/**
	 * 用户(销售)姓名
	 * @return
	 */
	public String getUserName();
	
	/**
	 * 部门
	 * @return
	 */
	public String getDepartment();
	
	/**
	 * 岗位
	 * @return
	 */
	public String getDuty();
	
	/**
	 * 手机
	 * @return
	 */
	public String getMobilePhone();
	
	/**
	 * 电子邮件
	 * @return
	 */
	public String getEmail();
	
	/**
	 * 入职日期
	 */
	public long getEntryDate();
	
	/**
	 * 销售职级
	 * @return
	 */
	public String getSalesRank();
	
	/**
	 * 工作地点
	 * @return
	 */
	public String getWorkingProvince();
	/**
	 * 工作地点
	 * @return
	 */
	public String getWorkingCity();
	/**
	 * 工作地点
	 * @return
	 */
	public String getWorkingCounty();
	
	/**
	 * 入职地点
	 * @return
	 */
	public String getProvince();
	/**
	 * 入职地点
	 * @return
	 */
	public String getCity();
	/**
	 * 入职地点
	 * @return
	 */
	public String getCounty();
	
	/**
	 * 用户在职状态
	 * @return
	 */
	public UserStatus getUserStatus();
	
	/**
	 * 用户启用状态
	 * @return
	 */
	public EnabledStatus getEnabledStatus();
	
	/**
	 * 创建人
	 * @return
	 */
	public GUID getCreatePerson();
	/**
	 * 创建人
	 * @return
	 */
	public String getCreatePersonName();
	/**
	 * 创建时间
	 * @return
	 */
	public long getCreateDate();

	/**
	 * 更新人
	 * @return
	 */
	public GUID getUpdatePerson();
	/**
	 * 更新人
	 * @return
	 */
	public String getUpdatePersonName();
	/**
	 * 更新时间
	 * @return
	 */
	public long getUpdateDate();
	
	/**
	 * 离职日期
	 */
	public long getTerminalDate();
	
	/**
	 * 登录密码
	 */
	public String getPassWord();
	
	/**
	 * 用户角色权限
	 * @return
	 */
	public RoleItem[] getRoleItems();
	
	/**
	 * 用户 类型
	 * @return
	 */
	public UserType getUserType();
	
	public String getWorkingArea();
	
	public String getArea();
}