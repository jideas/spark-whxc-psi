package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;

public class CheckInBaseInfoItemImpl implements CheckInBaseInfoItem {
	private GUID id;
	private GUID sheetId;
	private GUID goodsId;
	private String goodsCode;
	private String goodsNo;
	private String goodsName;
	private String goodsSpec;
	private String unit;
	private int scale;
	private double price;
	private double amount;
	private double realCount;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
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
		return 2;
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

}
