package com.spark.psi.query.browser.util;

public class OnlineSalesSearchCondition {

	private long deliverDateBegin; // 发货日期
	private long deliverDateEnd; // 发货日期
	private String customerName;
	private String goodsCode;// 商品编码
	private String goodsNo;// 商品条码
	private String goodsName;// 商品名称
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
	public long getCreateDateBegin() {
		return deliverDateBegin;
	}
	public void setDeliverDateBegin(long deliverDateBegin) {
		this.deliverDateBegin = deliverDateBegin;
	}
	public long getDeliverDateEnd() {
		return deliverDateEnd;
	}
	public void setDeliverDateEnd(long deliverDateEnd) {
		this.deliverDateEnd = deliverDateEnd;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
