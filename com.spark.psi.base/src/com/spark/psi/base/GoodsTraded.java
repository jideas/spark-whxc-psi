package com.spark.psi.base;

/**
 * 
 * <p>��Ʒ��Ŀ�������</p>
 *	
 * ��ѯ���� GoodsTraded + GetGoodsTradedByGoodsItemIdKey
 *


 *
 
 * @version 2012-3-9
 */
public interface GoodsTraded{
	
	/**
	 * ���۴���
	 * 
	 * @return int
	 */
	public int getSalesCount();

	/**
	 * ������۵���
	 * 
	 * @return double
	 */
	public double getRecentSalesAmount();

	/**
	 * �ɹ�����
	 * 
	 * @return int
	 */
	public int getPurcahseCount();

	/**
	 * ����ɹ�����
	 * 
	 * @return double
	 */
	public double getRecentPurchaseAmount();
	
	
	
}
