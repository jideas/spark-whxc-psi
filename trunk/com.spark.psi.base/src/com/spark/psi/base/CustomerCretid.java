package com.spark.psi.base;

/**
 * 
 * <p>�ͻ����ö��</p>
 *
 * ��ѯ���� CustomerCretid.class+GetCustomerCretidByCustomerIdKey


 *
 
 * @version 2012-3-9
 */
public interface CustomerCretid{

	/**
	 * ���ö��
	 */
	public double getCreditAmount();
	/**
	 * ����
	 */
	public double getAccountPeriod();
	/**
	 * �ѳ����ö�Ƚ��
	 */
	public double getOverCreditAmount();
	/**
	 * �ѳ���������
	 */
	public double getOverCreditDay();
	/**
	 * ����Ԥ������
	 */
	public double getRemindDay();
	/**
	 * ����Ԥ�����
	 */
	public double getRemindingAmount();

		
}
