package com.spark.onlineorder.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;

/**
 * ���϶�����Ʒ��ϸ<BR>
 * ��ѯ������<BR>
 * ID+List<OnlineOrderInfoItem>;
 *
 */
public class OnlineOrderInfoItemImpl implements  OnlineOrderInfoItem{

	private GUID id;//	�б�ʶ
	private GUID billsId;//	����GUID
	private GUID goodsId;//	��ƷID
	private String goodsCode;//	��Ʒ����
	private String goodsNo;//	��Ʒ����
	private String goodsName;//	��Ʒ����
	private String goodsSpec;//	��Ʒ���
	private String unit;//	��λ
	private int goodsScale;//	��ƷС��λ��
	private double price;//	����
	private double count;//	����
	private double discount;//	�ۿ���
	private double disAmount;//	�ۿ۶�
	private double amount;//	���
	private GUID promotionId;//	������ƷGuid
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getBillsId() {
		return billsId;
	}
	public void setBillsId(GUID billsId) {
		this.billsId = billsId;
	}
	public GUID getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsSpec() {
		return goodsSpec;
	}
	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getGoodsScale() {
		return goodsScale;
	}
	public void setGoodsScale(int goodsScale) {
		this.goodsScale = goodsScale;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public GUID getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(GUID promotionId) {
		this.promotionId = promotionId;
	}

}
