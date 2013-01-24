package com.spark.psi.publish.base.goods.entity;

/**
 * 
 * <p>商品交易记录</p>
 *


 *
 
 * @version 2012-4-27
 */
public interface GoodsTraderLogItem{
	
	public enum TraderType {
		Sales,
		Purchase
	}
	
	
	/**
	 * 客户供应商名称
	 * 
	 * @return String
	 */
	public String getPartnerName();
	
	/**
	 * 商品条目属性
	 * 
	 * @return String
	 */
	public String getProperty();
	public String getGoodsSpec();
	
	/**
	 * 商品单位
	 * 
	 * @return String
	 */
	public String getUnit();
	
	
	/**
	 * 商品状态
	 * 
	 * @return String
	 */
	public String getStatus();
	
	/**
	 * 交易次数
	 * 
	 * @return int
	 */
	public int getCount();
	
	/**
	 * 累计交易金额
	 * 
	 * @return int
	 */
	public double getTotalTraderAmount();
	
	/**
	 * 累计交易次数
	 * 
	 * @return int
	 */
	public String getTotalTraderCount();
	
	/**
	 * 最近交易金额
	 * 
	 * @return double
	 */
	public double getRecentPrice();
	
	/**
	 * 最近交易数量
	 * 
	 * @return int
	 */
	public String getRecentCount();
	
	/**
	 * 最近交易日期
	 * 
	 * @return long
	 */
	public long getRecentDate();
	
	/**
	 * 最近10次交易单价列表
	 * 
	 * @return double[]
	 */
	public double[] getPriceList();
}
