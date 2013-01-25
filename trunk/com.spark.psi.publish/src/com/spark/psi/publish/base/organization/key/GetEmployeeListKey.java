package com.spark.psi.publish.base.organization.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EmployeeStatus;
import com.spark.psi.publish.QueryScope;

/**
 * ��ѯԱ����Ϣ�б�ļ�ֵ����
 * 
 */
public class GetEmployeeListKey {

	/**
	 * �Ƿ��ѯ������˾��Χ��Ա��
	 */
	private boolean queryAll = true;

	/**
	 * ����ID��queryAllΪfalseʱ��Ч��Ϊ��ʱ��ѯ��˾ֱ����
	 */
	private GUID departmentId;

	/**
	 * Ա��״̬
	 */
	private EmployeeStatus status;

	/**
	 * ��ɫ
	 */
	private boolean authorized;
	
	/**
	 * ���˷�Χ
	 */
	private QueryScope queryScope;
	
	/**
	 * ��ɫ���ƹ��˷�Χ
	 */
	private int roleScope;

	/**
	 * @return the queryAll
	 */
	public boolean isQueryAll() {
		return queryAll;
	}

	/**
	 * @param queryAll
	 *            the queryAll to set
	 */
	public void setQueryAll(boolean queryAll) {
		this.queryAll = queryAll;
	}

	/**
	 * @return the departmentId
	 */
	public GUID getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(GUID departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the status
	 */
	public EmployeeStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}

	/**
	 * @return the authorized
	 */
	public boolean isAuthorized() {
		return authorized;
	}

	/**
	 * @param authorized
	 *            the authorized to set
	 */
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	public QueryScope getQueryScope(){
    	return queryScope;
    }

	public void setQueryScope(QueryScope queryScope){
    	this.queryScope = queryScope;
    }

	public int getRoleScope(){
    	return roleScope;
    }

	/**
	 * ��ɫ���ƹ��˷�Χ
	 * 0 ��ͨ����ɫ����
	 * 1 �����˽�ɫ��Ա��
	 * 2 û�����ý�ɫ��Ա��
	 * @param roleScope void
	 */
	public void setRoleScope(int roleScope){
    	this.roleScope = roleScope;
    }

	
}
