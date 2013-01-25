package com.spark.psi.order.browser.delivery;

import com.spark.psi.publish.MultItemRowObject;

public class DeliverDetailShowItem extends MultItemRowObject {

	public DeliverDetailShowItem(String id, int rowSpan, boolean isFirstItem) {
		super(id, rowSpan, isFirstItem);
	}
	
	private String sheetNo;
	private String goodsName;
	private String goodsSpec;
	private double goodsCount;
	private String memeberName;
	private double amount;
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
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
	public String getMemeberName() {
		return memeberName;
	}
	public void setMemeberName(String memeberName) {
		this.memeberName = memeberName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(double goodsCount) {
		this.goodsCount = goodsCount;
	}
	
	

}
