package com.spark.order.sales.intf.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderDet;

/**
 * <p>���۶�����ϸ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-10-19
 */
@StructClass
public class SaleOrderItem extends OrderDet{
	@StructField
	private double disRate;//	�ۿ���	Num(5,4)
	private double buyAvgPrice;
	@StructField
	private double disAmount;//	�ۿ۶�	NUM(17,2)
	private double goodsPrice;//��Ʒ�۸�
	
	private GUID promotionGuid;//����GUID
	/**
	 * 
	 * @return GUID
	 */
	public GUID getPromotionGuid() {
		return promotionGuid;
	}
	/**
	 * 
	 * @param promotionGuid void
	 */
	public void setPromotionGuid(GUID promotionGuid) {
		this.promotionGuid = promotionGuid;
	} 
	public double getBuyAvgPrice() {
		return buyAvgPrice;
	}
	public void setBuyAvgPrice(double buyAvgPrice) {
		this.buyAvgPrice = buyAvgPrice;
	}
	/**
	 * @return the goodsPrice
	 */
	public double getGoodsPrice() {
		return goodsPrice;
	}
	/**
	 * @param goodsPrice the goodsPrice to set
	 */
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	  
	/**
	 * @return the disAmount
	 */
	public double getDisAmount() {
		return disAmount;
	}
 
	public double getDisRate() {
		return disRate;
	}
	public void setDisRate(double disRate) {
		this.disRate = disRate;
	}
	/**
	 * @param disAmount the disAmount to set
	 */
	public void setDisAmount(double disAmount) {
		this.disAmount = disAmount;
	}
	
}
