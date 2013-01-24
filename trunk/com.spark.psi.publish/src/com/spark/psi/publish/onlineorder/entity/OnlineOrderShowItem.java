package com.spark.psi.publish.onlineorder.entity;

import com.spark.psi.publish.MultItemRowObject;

public class OnlineOrderShowItem extends MultItemRowObject {
	
	private String billsNo;
	private String realName;
	private double totalAmount;
	private long createDate;
	private String stationName;
	private double count;
	private String goodsName;
	private String goodsSpec;
	// 发货人
	private String consignor;
	// 发货日期
	private long deliverDate;
	private boolean isToDoor;
	
	public OnlineOrderShowItem(String id, int rowSpan, boolean isFirstItem) {
		super(id, rowSpan, isFirstItem);
	}
	
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public long getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(long deliverDate) {
		this.deliverDate = deliverDate;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
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

	public String getConsignor() {
		return consignor;
	}

	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}

	public void setToDoor(boolean isToDoor) {
		this.isToDoor = isToDoor;
	}

	public boolean isToDoor() {
		return isToDoor;
	}
	
}
