package com.spark.order.intf.publish.entity;

import java.util.List;

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
	private double buyAvgPrice;
 
	public double getBuyAvgPrice() {
		return buyAvgPrice;
	}
	public void setBuyAvgPrice(double buyAvgPrice) {
		this.buyAvgPrice = buyAvgPrice;
	}
	@StructField
	private double discountAmount;//	折扣额	NUM(17,2)
	private PromotionItem[] promotionList;//
	private GUID promotionId;//零售单送货上门专用
	private double goodsItemPrice;
	public GUID getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(GUID promotionId) {
		this.promotionId = promotionId;
	}
	public double getDiscountCount(){
    	return discountCount;
    }
	public double getDiscountAmount(){
    	return discountAmount;
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
	public PromotionItem[] getPromotionList() {
		return promotionList;
	}
	public void setPromotionList(List<PromotionItem> promotionList) {
		this.promotionList = promotionList.toArray(new PromotionItem[promotionList.size()]);
	}
	public double getGoodsItemPrice() {
		return goodsItemPrice;
	}
	public void setGoodsItemPrice(double goodsItemPrice) {
		this.goodsItemPrice = goodsItemPrice;
	}
}
