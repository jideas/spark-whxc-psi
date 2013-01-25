package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ���۴���������<BR>
 * ��ѯ������ListEntry<RetailSubmitingItem>+GetRetailSubmitingListKey
 * 
 */
public interface RetailSubmitingItem {

	/**
	 * ID
	 * @return the salesId
	 */
	public GUID getId();

	/**
	 * ��������
	 * @return the salesName
	 */
	public String getSalesName();

	/**
	 * �������
	 * @return the amount
	 */
	public double getAmount();

	/**
	 * ʱ�ڷ�Χ
	 * @return the beginDate
	 */
	public long getBeginDate();

	/**
	 * ˢ���׵�����
	 * @return the cardRecordCount
	 */
	public int getCardRecordCount();

	/**
	 * ˢ���׵����
	 * @return the cardRecordAmount
	 */
	public double getCardRecordAmount();

}
