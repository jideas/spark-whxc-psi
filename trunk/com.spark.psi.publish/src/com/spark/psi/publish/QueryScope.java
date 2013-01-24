package com.spark.psi.publish;

import com.jiuqi.dna.core.type.GUID;

/**
 * 查询范围对象<br>
 */
public final class QueryScope {

	/**
	 * 范围名称（例如公司、我的单据、部门名称等）
	 */
	private String name;

	/**
	 * 类型（部门或者个人）
	 */
	private ScopeType type;

	/**
	 * 如果查询范围类型是部门时的部门ID
	 */
	private GUID departmentId;

	/**
	 * 是否查询子部门
	 */
	private boolean recursive = true;

	/**
	 * 查询个人
	 * 
	 * @param name
	 */
	public QueryScope(String name) {
		this.name = name;
		this.type = ScopeType.Mine;
		this.departmentId = GUID.emptyID;
	}

	public QueryScope(String name, GUID departmentId,ScopeType type) {
		this.type = type;
		this.name = name;
		this.departmentId = departmentId; 
	}

	/**
	 * 查询部门
	 * 
	 * @param name
	 * @param departmentId
	 */
	public QueryScope(String name, GUID departmentId) {
		if (departmentId == null) {
			throw new IllegalArgumentException("departmentId不能为空");
		}
		this.name = name;
		this.departmentId = departmentId;
		this.type = ScopeType.Department;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public ScopeType getType() {
		return type;
	}

	/**
	 * @return the departmentId
	 */
	public GUID getDepartmentId() {
		return departmentId;
	}

	/**
	 * @return the recursive
	 */
	public boolean isRecursive() {
		return recursive;
	}

	/**
	 * @param recursive
	 *            the recursive to set
	 */
	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}

	/**
	 * 查询范围的类型
	 * 
	 */
	public static enum ScopeType {
		Department, Mine, All
	}

}
