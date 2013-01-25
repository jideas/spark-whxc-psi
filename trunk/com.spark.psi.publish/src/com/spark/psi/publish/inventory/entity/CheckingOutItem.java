package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;

/**
 * ���������б���Ŀ<br>
 * ��ѯ������ListEntry<CheckingOutItem>+GetCheckingOutListKey
 * ok
 */
public interface CheckingOutItem {

	/**
	 * ��ȡ���ⵥid
	 */
	public GUID getSheetId(); 

	/**
	 * ��ȡ��������
	 */
	public long getCreateDate();

	/**
	 * ��ȡ�ƻ�����ʱ��
	 */
	public long getPlanCheckoutDate();

	/**
	 * ��ȡ�ϴγ���ʱ��
	 */
	public long getLastCheckoutDate();

	/**
	 * ��ȡ��ص��ݱ��
	 */
	public String getRelaBillsNo();

	/**
	 * ��ȡ����ֿ�id
	 */
	public GUID getStoreId();

	/**
	 * ��ȡ����ֿ�����
	 */
	public String getStoreName();

	/**
	 * ��ȡ���ⵥ״̬
	 */
	public CheckingOutStatus getStatus();

	/**
	 * ��ȡ��������
	 */
	public CheckingOutType getType();

	/**
	 * ��ȡ����ȷ����
	 */
	public GUID[] getCheckoutEmployeeIds();

	/**
	 * ��ȡ����ȷ����
	 */
	public String getCheckoutEmployees();
}