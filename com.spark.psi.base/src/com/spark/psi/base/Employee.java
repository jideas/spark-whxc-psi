package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * 
 * <p>员工资源接口</p>
 *


 *
 
 * @version 2012-4-1
 */
public interface Employee{

	public GUID getId();
	
	public String getName();

	public String getMobileNo();
	
	public String getLandLineNumber();
	
	public GUID getLogo();

	public String getIdNumber();

	public long getBirthday();

	public String getEmail();

	public String getPosition();

	public int getRoles();

	public String getStatus();
	
	public long getCreateDate();
	
	public String getStyle();

	/**
	 * 租户id
	 * 
	 * @return GUID
	 */
	public GUID getTenantId();

	public GUID getDepartmentId();

	

	/**
	 * 判断当前用户是否拥有所指定的其中一种权限
	 * @param auths
	 * @return
	 */
	public boolean hasAuth(Auth... auths);
	
	/**
	 * 判断当前用户是否拥有所指定的所有权限
	 * 
	 * @param auths
	 * @return boolean
	 */
	public boolean hasAllAuth(Auth...auths);

	/**
	 * 
	 * @return
	 */
	public Auth[] getAcls();

}
