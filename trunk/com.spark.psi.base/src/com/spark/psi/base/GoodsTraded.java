package com.spark.psi.base;

/**
 * 
 * <p>商品条目交易情况</p>
 *	
 * 查询方法 GoodsTraded + GetGoodsTradedByGoodsItemIdKey
 *


 *
 
 * @version 2012-3-9
 */
public interface GoodsTraded{
	
	/**
	 * 销售次数
	 * 
	 * @return int
	 */
	public int getSalesCount();

	/**
	 * 最近销售单价
	 * 
	 * @return double
	 */
	public double getRecentSalesAmount();

	/**
	 * 采购次数
	 * 
	 * @return int
	 */
	public int getPurcahseCount();

	/**
	 * 最近采购单价
	 * 
	 * @return double
	 */
	public double getRecentPurchaseAmount();
	
	
	
}
