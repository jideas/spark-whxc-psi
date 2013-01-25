package com.spark.deliverticket.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketInfoItem;

/**
 * 
 * 发货单商品明细
 *
 */
public class DeliverTicketInfoItemImpl implements DeliverTicketInfoItem {

	private GUID id;//	行标识
	private GUID ticketId;//	订单GUID
	private GUID goodsId;//	商品ID
	private String goodsCode;//	商品编码
	private String goodsNo;// 商品条码
	private String goodsName;// 商品名称
	private String goodsSpec;// 商品规格
	private String unit;// 单位
	private int goodsScale;// 商品小数位数
	private double price;// 单价
	private double count;// 数量
	private double disRate;// 折扣率
	private double disAmount;// 折扣额
	private double amount;//	金额
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getTicketId() {
		return ticketId;
	}
	public void setTicketId(GUID ticketId) {
		this.ticketId = ticketId;
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
	public double getDisRate() {
		return disRate;
	}
	public void setDisRate(double disRate) {
		this.disRate = disRate;
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

}
