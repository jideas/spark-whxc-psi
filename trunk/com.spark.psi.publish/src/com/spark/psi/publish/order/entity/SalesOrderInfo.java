package com.spark.psi.publish.order.entity;


/**
 * ���۶�������ά��
 
 *
 */
public interface SalesOrderInfo extends OrderInfo {
	
//	/**
//	 * ���۶�����Ʒ��ϸ
//	 */
//	private SalesOrderGoodsItem[] salesOrderGoodsItems;
	
//	@StructField
//	private double discountAmount;//	�����ۿ�	N

	public double getDiscountAmount();


	public SalesOrderGoodsItem[] getSalesOrderGoodsItems();
}
