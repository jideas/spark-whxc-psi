package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.def.obja.StructField;

/**
 * ���۶�������ά��
 
 *
 */
public final class SalesOrderInfoImpl extends OrderInfoImpl implements com.spark.psi.publish.order.entity.SalesOrderInfo{
	
	/**
	 * ���۶�����Ʒ��ϸ
	 */
	private SalesOrderGoodsItemImpl[] salesOrderGoodsItems;
	
	@StructField
	private double discountAmount;//	�����ۿ�	N

	public double getDiscountAmount(){
    	return discountAmount;
    }


	public SalesOrderGoodsItemImpl[] getSalesOrderGoodsItems(){
    	return salesOrderGoodsItems;
    }


	/**
	 * @param salesOrderGoodsItems the salesOrderGoodsItems to set
	 */
	public void setSalesOrderGoodsItems(
			SalesOrderGoodsItemImpl[] salesOrderGoodsItems) {
		this.salesOrderGoodsItems = salesOrderGoodsItems;
	}


	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
}
