package com.spark.psi.account.intf.entity.jointSettlement;

import com.jiuqi.dna.core.type.GUID;

public class JointSettlementDetEntity {

	private GUID id;
	private GUID goodsId;
	private GUID sheetId;
	private String goodsCode;
	private String goodsNo;
	private String goodsSpec;
	private String goodsUnit;
	private String goodsName;
	private double goodsPrice;
	private double count;
	private double amount;
	private double percentage;
	private double percentageAmount;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}
	public GUID getSheetId() {
		return sheetId;
	}
	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
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
	public String getGoodsSpec() {
		return goodsSpec;
	}
	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
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
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public void setPercentageAmount(double percentageAmount) {
		this.percentageAmount = percentageAmount;
	}
	public double getPercentageAmount() {
		return percentageAmount;
	}

}
