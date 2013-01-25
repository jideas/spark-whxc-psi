package com.spark.order.intf.facade;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>商品对订单历史记录</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-12-1
 */
public interface FGoodsBillsLog {
	/**
	 * @return the cuspGuid
	 */
	public GUID getCuspGuid();
	/**
	 * @return the goodsProGuid
	 */
	public GUID getGoodsProGuid();
	/**
	 * @return the billsGuid
	 */
	public GUID getBillsGuid();
	/**
	 * @return the billsNo
	 */
	public String getBillsNo();
	/**
	 * @return the price
	 */
	public double getPrice();
	/**
	 * @return the okDate
	 */
	public long getOkDate();
}
