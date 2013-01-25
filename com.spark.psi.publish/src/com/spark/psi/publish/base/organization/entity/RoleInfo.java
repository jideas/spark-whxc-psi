package com.spark.psi.publish.base.organization.entity;

/**
 * ��ɫ��Ϣ <br>
 * ��ѯ������<br>
 * (1)ֱ�ӻ�ȡRoleInfo�б�
 * 
 */
public interface RoleInfo {
	
	/**
	 * �ս�ɫ��һ����ɫ��û��
	 */
	public static final int Empty_Role = 0;
	
	/**�����ɫ*/
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