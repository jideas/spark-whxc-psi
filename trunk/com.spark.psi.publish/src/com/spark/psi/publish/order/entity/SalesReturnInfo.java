package com.spark.psi.publish.order.entity;


/**
 * �����˻�����ά��
 * @author zhoulijun
 *
 */
public interface SalesReturnInfo extends OrderInfo{
	
//	/**
//	 * �����˻���Ʒ��ϸ
//	 */
//	private SalesReturnGoodsItem[] returnGoodsItems;

	public SalesReturnGoodsItem[] getReturnGoodsItems();
}
