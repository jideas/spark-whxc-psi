package com.spark.psi.publish.base.b2b.entity;

import com.jiuqi.dna.core.type.GUID;

public interface OnlineOrderItem {

	/**
	 * �������
	* @Title: getOrderNumber 
	* @param @return    ���� 
	* @return String    �������� 
	* @throws
	 */
	public String getOrderNumber();

	/**
	 * ��Ӧ�̱��
	* @Title: getPartnerId 
	* @param @return    ���� 
	* @return GUID    �������� 
	* @throws
	 */
	public GUID getPartnerId();

	/**
	 * ��Ӧ�̼��
	* @Title: getPartnerShortName 
	* @param @return    ���� 
	* @return String    �������� 
	* @throws
	 */
	public String getPartnerShortName();

	/**
	 * ��Ӧ��ȫ��
	* @Title: getPartnerName 
	* @param @return    ���� 
	* @return String    �������� 
	* @throws
	 */
	public String getPartnerName();

	/**
	 * ��������
	* @Title: getCreateDate 
	* @param @return    ���� 
	* @return long    �������� 
	* @throws
	 */
	public long getCreateDate();

	/**
	 * ���
	* @Title: getAmount 
	* @param @return    ���� 
	* @return double    �������� 
	* @throws
	 */
	public double getAmount();

	/**
	 * ��ϸ
	* @Title: getOrderDetail 
	* @param @return    ���� 
	* @return String    �������� 
	* @throws
	 */
	public String getOrderDetail();

}
