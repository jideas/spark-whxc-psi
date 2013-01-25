package com.spark.psi.query.intf.intenal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DeliverTicketType;
import com.spark.psi.query.intf.publish.entity.TicketItem;

/**
 * 发货单查询实体<BR>
 * 查询方法：<BR>
 * DeliverTicketListEntity+GetDeliverTicketListKey;
 * 
 */
public class TicketItemImpl implements TicketItem {

	private GUID ticketId;// 订单GUID
	private GUID goodsId;// 商品ID
	private String goodsCode;// 商品编码
	private String goodsNo;// 商品条码
	private String goodsName;// 商品名称
	private String unit;// 单位
	private double price;// 单价
	private double count;// 数量
	private double amount;// 金额
	private String sheetNo;// 单据编号
	private long createDate;// 发货日期
	private DeliverTicketType deliverTicketType;// 发货类型
	private GUID memberId;//	客户ID
	private String memberRealName;//	客户名称
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public DeliverTicketType getDeliverTicketType() {
		return deliverTicketType;
	}
	public void setDeliverTicketType(DeliverTicketType deliverTicketType) {
		this.deliverTicketType = deliverTicketType;
	}
	public GUID getMemberId() {
		return memberId;
	}
	public void setMemberId(GUID memberId) {
		this.memberId = memberId;
	}
	public String getMemberRealName() {
		return memberRealName;
	}
	public void setMemberRealName(String memberRealName) {
		this.memberRealName = memberRealName;
	}


}
