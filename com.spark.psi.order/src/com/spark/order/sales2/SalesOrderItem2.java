package com.spark.order.sales2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderItemFather;

/**
 * <p>���۶�����ϸ2</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */
public class SalesOrderItem2 extends OrderItemFather{
	protected double discount;//	�ۿ���	numeric	6
	protected double disAmount;//	�ۿ۶�	numeric	17
	protected GUID promotionId;//	������ƷGuid	guid	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(double disAmount) {
		this.disAmount = disAmount;
	}
	public GUID getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(GUID promotionId) {
		this.promotionId = promotionId;
	}
}
