package com.spark.psi.order.browser.internal;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.order.entity.PromotionItem;

/**
 * 
 * <p>销售订单商品明细</p>
 *


 *
 
 * @version 2012-3-1
 */
public class SalesOrderGoodsItemImpl extends OrderGoodsItemImpl implements com.spark.psi.publish.order.entity.SalesOrderGoodsItem{
	
	@StructField
	private double discountCount;//	折扣率	Num(5,4)
	private double planPrice;
	@StructField
	private double discountAmount;//	折扣额	NUM(17,2)
	private PromotionItem[] promotionList;//
	private GUID promotionId;
	private double goodsItemPrice;
	public GUID getPromotionId() {
		return promotionId;
	}
	public PromotionItem[] getPromotionList() {
		return promotionList;
	}
	public void setPromotionList(PromotionItem... promotionList) {
		this.promotionList = promotionList;
	}
	public double getDiscountCount(){
    	return discountCount;
    }
	public double getDiscountAmount(){
    	return discountAmount;
    }
	public double getPlanPrice() {
		return planPrice;
	}
	public void setPlanPrice(double planPrice) {
		this.planPrice = planPrice;
	}
	/**
	 * @param discountCount the discountCount to set
	 */
	public void setDiscountCount(double discountCount) {
		this.discountCount = discountCount;
	}
	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public double getGoodsItemPrice() {
		return goodsItemPrice;
	}
	public void setGoodsItemPrice(double goodsItemPrice) {
		this.goodsItemPrice = goodsItemPrice;
	}
	public void setPromotionId(GUID promotionId) {
		this.promotionId = promotionId;
	} 
}
