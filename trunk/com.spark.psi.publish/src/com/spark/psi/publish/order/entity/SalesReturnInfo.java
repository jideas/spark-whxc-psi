package com.spark.psi.publish.order.entity;


/**
 * 销售退货详情维护
 * @author zhoulijun
 *
 */
public interface SalesReturnInfo extends OrderInfo{
	
//	/**
//	 * 销售退货商品明细
//	 */
//	private SalesReturnGoodsItem[] returnGoodsItems;

	public SalesReturnGoodsItem[] getReturnGoodsItems();
}
