package com.spark.psi.base;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.entity.ILevelTree;
import com.spark.psi.publish.Auth;

/**
 * 部门信息<br>
 * 
 * 查询方法：<br>
 * (1)根据id查询Department
 * 
 */
public interface Department extends ILevelTree {


	/**
	 * 部门ID
	 * @return
	 */
	public GUID getId();
	public String getCode();
	/**
	 * 部门名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 获得子部门列表
	 * 
	 * @return Department[]
	 */
	public Department[] getChildren(final Context context,Auth... auth);
	
	/**
	 * 获得子孙部门列表
	 * 
	 * @return Department[]
	 */
	public Department[] getDescendants(final Context context,Auth... auth);
	
	/**
	 * 获得祖先部门列表
	 * 
	 * @return Department[]
	 */
	public Department[] getAncestors(final Context context);
	
	/**
	 * 获得上级部门
	 * 
	 * @return Department
	 */
	public Department getParent(final Context context);
	
	/**
	 * 上级部门id
	 */
	public GUID getParent();
//	
//	/**
//	 * 获得员工总数量（包换子孙部门的员工）
//	 * 
//	 * @return int
//	 */
//	public int getEmployeeCount();
	
	/**
	 * 是否拥有指定职能
	 * @param auths
	 * @return
	 */
	public boolean hasAuth(Auth...auths);
	
	/**
	 * 职能列表
	 * 
	 * @return Auth[]
	 */
	public Auth[] getAuths();
	
	/**
	 * 排序索引
	 * 
	 * @return int
	 */
	public int getSortIndex();
	
	/**
	 * 租户id
	 * 
	 * @return GUID
	 */
	public GUID getTenantId();
	
	/**
	 * 是否有效
	 * 
	 * @return boolean
	 */
	public boolean isValid();
	
}
