package com.spark.psi.query.browser.util;

public class OnlineSalesSearchCondition {

	private long deliverDateBegin; // ��������
	private long deliverDateEnd; // ��������
	private String customerName;
	private String goodsCode;// ��Ʒ����
	private String goodsNo;// ��Ʒ����
	private String goodsName;// ��Ʒ����
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
