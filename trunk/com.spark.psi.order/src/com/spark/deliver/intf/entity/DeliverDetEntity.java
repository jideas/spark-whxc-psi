package com.spark.deliver.intf.entity;

import com.jiuqi.dna.core.type.GUID;

public class DeliverDetEntity {

	private GUID id;//	�б�ʶ
	private GUID deliverSheetId;//	���͵�ID
	private GUID onlineOrderId;//	���϶���Id
	private String onlineOrderNo;//	�������
	private String memberRealName;//	��Ա����
	private double orderAmount;//	�������
	
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
