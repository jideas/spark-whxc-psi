package com.spark.psi.base;

/**
 * 
 * <p>客户信用额度</p>
 *
 * 查询方法 CustomerCretid.class+GetCustomerCretidByCustomerIdKey


 *
 
 * @version 2012-3-9
 */
public interface CustomerCretid{

	/**
	 * 信用额度
	 */
	public double getCreditAmount();
	/**
	 * 账期
	 */
	public double getAccountPeriod();
	/**
	 * 已超信用额度金额
	 */
	public double getOverCreditAmount();
	/**
	 * 已超账期天数
	 */
	public double getOverCreditDay();
	/**
	 * 账期预警天数
	 */
	public double getRemindDay();
	/**
	 * 账期预警金额
	 */
	public double getRemindingAmount();

		
}
