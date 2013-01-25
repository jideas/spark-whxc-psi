package com.spark.psi.publish.base.organization.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * 组织结构树 <br>
 * (1)查询方法：直接查询DepartmentTree对象
 */
public interface DepartmentTree {

	public DepartmentNode getNodeById(GUID id);
	

	/**
	 * 获取主租户节点
	 * 
	 * @return
	 */
	public DepartmentNode getRoot();

	/**
	 * 部门树节点
	 */
	public interface DepartmentNode {

		/**
		 * 
		 * @return
		 */
		public GUID getId();

		/**
		 * 
		 * @return
		 */
		public String getName();
		
		/**
		 * 获得全路径部门名称
		 * 
		 * @return String
		 */
		public String getFillPathName();

		/**
		 * 
		 * @return
		 */
		public DepartmentNode[] getChildren();

		/**
		 * 
		 * @return
		 */
		public DepartmentNode getParent();

		/**
		 * 是否拥有指定职能中的一项
		 * 
		 * @param auths
		 * @return boolean
		 */
		public boolean hasAuth(Auth... auths);
	}
}
