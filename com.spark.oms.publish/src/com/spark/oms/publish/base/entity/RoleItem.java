package com.spark.oms.publish.base.entity;

import com.jiuqi.dna.core.type.GUID;

public interface RoleItem {
	
	/**
	 * 角色GUID
	 * @return
	 */
	public GUID getRECID();

	/**
	 * 角色Code
	 * @return
	 */
	public String getRoleCode();
	/**
	 * 角色名称
	 * @return
	 */
	public String getRoleName();
	/**
	 * 创建人GUID
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
	 * 更新人GUID
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
	 * 获取角色对应的功能模块
	 * @return
	 */
	public FunctionItem[] getFunctionItems();
}
