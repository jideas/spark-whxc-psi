package com.spark.psi.publish;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯ��Χ����<br>
 */
public final class QueryScope {

	/**
	 * ��Χ���ƣ����繫˾���ҵĵ��ݡ��������Ƶȣ�
	 */
	private String name;

	/**
	 * ���ͣ����Ż��߸��ˣ�
	 */
	private ScopeType type;

	/**
	 * �����ѯ��Χ�����ǲ���ʱ�Ĳ���ID
	 */
	private GUID departmentId;

	/**
	 * �Ƿ��ѯ�Ӳ���
	 */
	private boolean recursive = true;

	/**
	 * ��ѯ����
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
	 * ��ѯ����
	 * 
	 * @param name
	 * @param departmentId
	 */
	public QueryScope(String name, GUID departmentId) {
		if (departmentId == null) {
			throw new IllegalArgumentException("departmentId����Ϊ��");
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
	 * ��ѯ��Χ������
	 * 
	 */
	public static enum ScopeType {
		Department, Mine, All
	}

}
