package com.spark.oms.publish.base.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.EnabledStatus;
import com.spark.oms.publish.UserStatus;

/**
 * 用户列表（职能部门，销售团队）
 */
public interface UserItem {
	
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
	
	
	//工作地点
	public String getWorkingProvince();
	public String getWorkingCity();
	public String getWorkingCounty();
	
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
}