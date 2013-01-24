package com.spark.psi.publish.inventory.sheet.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;

public interface CheckedInItem {

	/**
	 * ��ȡ��ⵥid
	 */
	public GUID getSheetId();

	public String getSheetNo();

	/**
	 * ��ȡ��������
	 */
	public long getCreateDate();

	/**
	 * ��ȡ���ʱ��
	 */
	public long getCheckinDate(); 

	/**
	 * ��ȡ��ص��ݱ��
	 */
	public String getRelaBillsNo();

	/**
	 * ��ȡ���ֿ�id
	 */
	public GUID getStoreId();

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
	
	public GUID getCheckedInPerson();
	public String getCheckedInPersonName();

}
