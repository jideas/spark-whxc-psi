package com.spark.deliver.intf.entity;

import com.jiuqi.dna.core.type.GUID;

public class DeliverDetEntity {

	private GUID id;//	行标识
	private GUID deliverSheetId;//	配送单ID
	private GUID onlineOrderId;//	网上订单Id
	private String onlineOrderNo;//	订单编号
	private String memberRealName;//	会员名称
	private double orderAmount;//	订单金额
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getDeliverSheetId() {
		return deliverSheetId;
	}
	public void setDeliverSheetId(GUID deliverSheetId) {
		this.deliverSheetId = deliverSheetId;
	}
	public GUID getOnlineOrderId() {
		return onlineOrderId;
	}
	public void setOnlineOrderId(GUID onlineOrderId) {
		this.onlineOrderId = onlineOrderId;
	}
	public String getOnlineOrderNo() {
		return onlineOrderNo;
	}
	public void setOnlineOrderNo(String onlineOrderNo) {
		this.onlineOrderNo = onlineOrderNo;
	}
	public String getMemberRealName() {
		return memberRealName;
	}
	public void setMemberRealName(String memberRealName) {
		this.memberRealName = memberRealName;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

}
