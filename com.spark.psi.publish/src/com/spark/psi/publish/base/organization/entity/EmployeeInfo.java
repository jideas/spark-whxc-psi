package com.spark.psi.publish.base.organization.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EmployeeStatus;

/**
 * 用户信息 <br>
 * 查询方法：根据ID查询EmployeeInfo对象
 */
public interface EmployeeInfo {


	public GUID getId();

	public String getName();

	public String getMobileNo();

	public String getIdNumber();

	public long getBirthday();

	public String getEmail();

	public String getPosition();

	public int getRoles();

	public GUID getDepartmentId();

	public EmployeeStatus getStatus();

	public GUID getLogo();
	
	public String getLandLineNumber();
	
	public String getStyle();
	
}
