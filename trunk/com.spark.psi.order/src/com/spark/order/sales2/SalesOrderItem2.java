package com.spark.order.sales2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderItemFather;

/**
 * <p>销售定单明细2</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */
public class SalesOrderItem2 extends OrderItemFather{
	protected double discount;//	折扣率	numeric	6
	protected double disAmount;//	折扣额	numeric	17
	protected GUID promotionId;//	促销商品Guid	guid	
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
