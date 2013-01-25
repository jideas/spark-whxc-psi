package com.spark.psi.publish.order.entity;


/**
 * 销售订单详情维护
 
 *
 */
public interface SalesOrderInfo extends OrderInfo {
	
//	/**
//	 * 销售订单商品明细
//	 */
//	private SalesOrderGoodsItem[] salesOrderGoodsItems;
	
//	@StructField
//	private double discountAmount;//	整单折扣	N

	public double getDiscountAmount();


	public SalesOrderGoodsItem[] getSalesOrderGoodsItems();
}
