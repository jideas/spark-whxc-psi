package com.spark.psi.publish.base.organization.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.EmployeeStatus;

/**
 * �û���Ϣ <br>
 * ��ѯ����������ID��ѯEmployeeItem�б�
 */
public interface EmployeeItem {
	
	
	/**
	 * ��ÿɲ��������б�
	 * 
	 * @return Action[]
	 */
	public Action[] getActions();

	/**
	 * id
	 * 
	 * @return GUID
	 */
	public GUID getId();

	/**
	 * Ա������
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * �ֻ�����
	 * 
	 * @return String
	 */
	public String getMobileNo();

	/**
	 * ���֤����
	 * 
	 * @return String
	 */
	public String getIdNumber();

	/**
	 * ����
	 * 
	 * @return long
	 */
	public long getBirthday();

	/**
	 * ����
	 * 
	 * @return String
	 */
	public String getEmail();

	/**
	 * ְ��
	 * 
	 * @return String
	 */
	public String getPosition();

	/**
	 * ��ɫ
	 * 
	 * @return int
	 */
	public int getRoles();

	/**
	 * ��ɫ����
	 * 
	 * @return String
	 */
	public String getRolesInfo();
 
	/**
	 * ����
	 * 
	 * @return String
	 */
	public GUID getDepartmentId();

	/**
	 * ��������
	 * 
	 * @return String
	 */
	public String getDepartmentName();

	/**
	 * Ա��״̬
	 * ��ְ or ��ְ
	 * 
	 * @return EmployeeStatus
	 */
	public EmployeeStatus getStatus();

	
	
	
}
