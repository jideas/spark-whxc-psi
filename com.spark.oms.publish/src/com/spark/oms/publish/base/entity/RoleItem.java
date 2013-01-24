package com.spark.oms.publish.base.entity;

import com.jiuqi.dna.core.type.GUID;

public interface RoleItem {
	
	/**
	 * ��ɫGUID
	 * @return
	 */
	public GUID getRECID();

	/**
	 * ��ɫCode
	 * @return
	 */
	public String getRoleCode();
	/**
	 * ��ɫ����
	 * @return
	 */
	public String getRoleName();
	/**
	 * ������GUID
	 * @return
	 */
	public GUID getCreatePerson();
	/**
	 * ������
	 * @return
	 */
	public String getCreatePersonName();
	/**
	 * ����ʱ��
	 * @return
	 */
	public long getCreateDate();

	/**
	 * ������GUID
	 * @return
	 */
	public GUID getUpdatePerson();
	/**
	 * ������
	 * @return
	 */
	public String getUpdatePersonName();
	/**
	 * ����ʱ��
	 * @return
	 */
	public long getUpdateDate();
	
	/**
	 * ��ȡ��ɫ��Ӧ�Ĺ���ģ��
	 * @return
	 */
	public FunctionItem[] getFunctionItems();
}
