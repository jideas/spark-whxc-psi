package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;


/**
 * 
 * <p>销售订单商品明细</p>
 *


 *
 
 * @version 2012-3-1
 */
public interface SalesOrderGoodsItem extends OrderGoodsItem{
	
//	@StructField
//	private double discountCount;//	折扣率	Num(5,4)
//	@StructField
//	private double discountAmount;//	折扣额	NUM(17,2)

	public double getPlanPrice();
	public double getDiscountCount();
	public double getDiscountAmount();
	/**
	 * 获得明细商品促销集合
	 * @return List<PromotionItem>
	 */
	public PromotionItem[] getPromotionList();
	public GUID getPromotionId();
	/**
	 * 获得商品原销售单价
	 * @return double
	 */
	public double getGoodsItemPrice();
}
