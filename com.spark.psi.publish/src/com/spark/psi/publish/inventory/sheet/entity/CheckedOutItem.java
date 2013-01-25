package com.spark.psi.publish.inventory.sheet.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

public interface CheckedOutItem {

	/**
	 * ��ȡ���ⵥid
	 */
	public GUID getSheetId();

	public String getSheetNo();

	/**
	 * ��ȡ����ʱ��
	 */
	public long getCheckOutDate();

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
	 * ��ȡ��������
	 */
	public CheckingOutType getType();

	public GUID getCheckedOutPerson();

	public String getCheckedOutPersonName();

	public String getTakerUnit();

	public String getTaker();

	public String getVouchersNumber();

}
