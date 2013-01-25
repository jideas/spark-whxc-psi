package com.spark.psi.publish.base.organization.entity;

/**
 * 角色信息 <br>
 * 查询方法：<br>
 * (1)直接获取RoleInfo列表
 * 
 */
public interface RoleInfo {
	
	/**
	 * 空角色，一个角色都没有
	 */
	public static final int Empty_Role = 0;
	
	/**助理角色*/
	public static final int Assistant_Role = 1 << 9;

	/**
	 * @return the code
	 */
	public int getCode();

	/**
	 * @return the maskCodes
	 */
	public int[] getMaskCodes();

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @return the description
	 */
	public String getDescription();

}