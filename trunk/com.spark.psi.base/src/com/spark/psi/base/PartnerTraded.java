package com.spark.psi.base;

/**
 * 
 * <p>客户供应商交易情况</p>
 *	查询方法 PartnerTraded.class + GetPartnerTradedByPartnerIdKey
 *	


 *
 
 * @version 2012-3-9
 */
public interface PartnerTraded{

	/**
	 * 订单数量（交易次数）
	 * 
	 * @return int
	 */
	public int getOrderCount();
	
	/**
	 * 订单金额
	 * 
	 * @return double
	 */
	public double getOrderAmount();

	/**
	 * 退货次数
	 * 
	 * @return int
	 */
	public int getReturnCount();

	/**
	 * 退货金额
	 * 
	 * @return double
	 */
	public double getReturnAmount();

	/**
	 * 收（付）款金额
	 * 
	 * @return double
	 */
	public double getBalanceAmount();
	
	
}	
