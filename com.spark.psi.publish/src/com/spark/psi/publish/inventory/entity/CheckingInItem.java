package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;

/**
 * ��ⵥ�б���Ŀ<br>
 * ��ѯ������ListEntry<CheckingInItem>+GetCheckingInListKey
 * ok
 */
public interface CheckingInItem {

	/**
	 * ��ȡ��ⵥid
	 */
	public GUID getSheetId(); 
	/**
	 * ��ȡ��������
	 */
	public long getCreateDate();

	/**
	 * ��ȡ�ƻ����ʱ��
	 */
	public long getPlanCheckinDate();

	/**
	 * ��ȡ�ϴ����ʱ��
	 */
	public long getLastCheckinDate();

	/**
	 * ��ȡ��ص��ݱ��
	 */
	public String getRelaBillsNo();

	/**
	 * ��ȡ���ֿ�id
	 */
	public GUID getStoreId();
	
	public String getRemark();

	/**
	 * ��ȡ���ֿ�����
	 */
	public String getStoreName();

	/**
	 * ��ȡ��ⵥ״̬
	 */
	public CheckingInStatus getStatus();

	/**
	 * ��ȡ�������
	 */
	public CheckingInType getType();

	/**
	 * ��ȡ���ȷ����
	 */
	public GUID[] getCheckinEmployeeIds();

	/**
	 * ��ȡ���ȷ����
	 */
	public String getCheckinEmployees();

}
