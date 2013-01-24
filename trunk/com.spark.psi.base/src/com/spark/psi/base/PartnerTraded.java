package com.spark.psi.base;

/**
 * 
 * <p>�ͻ���Ӧ�̽������</p>
 *	��ѯ���� PartnerTraded.class + GetPartnerTradedByPartnerIdKey
 *	


 *
 
 * @version 2012-3-9
 */
public interface PartnerTraded{

	/**
	 * �������������״�����
	 * 
	 * @return int
	 */
	public int getOrderCount();
	
	/**
	 * �������
	 * 
	 * @return double
	 */
	public double getOrderAmount();

	/**
	 * �˻�����
	 * 
	 * @return int
	 */
	public int getReturnCount();

	/**
	 * �˻����
	 * 
	 * @return double
	 */
	public double getReturnAmount();

	/**
	 * �գ���������
	 * 
	 * @return double
	 */
	public double getBalanceAmount();
	
	
}	
