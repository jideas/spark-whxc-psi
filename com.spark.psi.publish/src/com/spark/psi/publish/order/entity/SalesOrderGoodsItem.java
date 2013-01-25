package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;


/**
 * 
 * <p>���۶�����Ʒ��ϸ</p>
 *


 *
 
 * @version 2012-3-1
 */
public interface SalesOrderGoodsItem extends OrderGoodsItem{
	
//	@StructField
//	private double discountCount;//	�ۿ���	Num(5,4)
//	@StructField
//	private double discountAmount;//	�ۿ۶�	NUM(17,2)

	public double getPlanPrice();
	public double getDiscountCount();
	public double getDiscountAmount();
	/**
	 * �����ϸ��Ʒ��������
	 * @return List<PromotionItem>
	 */
	public PromotionItem[] getPromotionList();
	public GUID getPromotionId();
	/**
	 * �����Ʒԭ���۵���
	 * @return double
	 */
	public double getGoodsItemPrice();
}
