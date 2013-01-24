package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfoItem;

public class CheckOutBaseInfoItemImpl implements CheckOutBaseInfoItem {
	private GUID RECID;
	private GUID sheetId;
	private GUID goodsId;
	private String goodsCode;
	private String goodsNo;
	private String goodsName;
	private String goodsSpec;
	private String unit;
	private int scale;
	private double price;
	private double avgCost;
	private double amount;
	private double realCount;
	private String createPerson;
	private long createDate;
	public GUID getRECID() {
		return RECID;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	public GUID getSheetId() {
		return sheetId;
	}
	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
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
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAvgCost() {
		return avgCost;
	}
	public void setAvgCost(double avgCost) {
		this.avgCost = avgCost;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRealCount() {
		return realCount;
	}
	public void setRealCount(double realCount) {
		this.realCount = realCount;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

}
