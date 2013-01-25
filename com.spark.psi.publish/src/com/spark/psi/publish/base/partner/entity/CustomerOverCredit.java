package com.spark.psi.publish.base.partner.entity;

/**
 * 
 * <p>客户已超帐期情况</p>
 *  context.find(CustomerOverCredit.class,GetCustomerIsOverCreditDayKey);


 *
 
 * @version 2012-6-27
 */
public interface CustomerOverCredit{

	/**
	 * 临近帐期金额
	 * 
	 * @return double
	 */
	public double getApproachedCreditAmount();
	
	/**
	 * 超过帐期金额
	 * 
	 * @return double
	 */
	public double getOverCreditAmount();
	
}
