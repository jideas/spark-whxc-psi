package com.spark.psi.publish.base.partner.entity;

/**
 * 
 * <p>�ͻ��ѳ��������</p>
 *  context.find(CustomerOverCredit.class,GetCustomerIsOverCreditDayKey);


 *
 
 * @version 2012-6-27
 */
public interface CustomerOverCredit{

	/**
	 * �ٽ����ڽ��
	 * 
	 * @return double
	 */
	public double getApproachedCreditAmount();
	
	/**
	 * �������ڽ��
	 * 
	 * @return double
	 */
	public double getOverCreditAmount();
	
}
