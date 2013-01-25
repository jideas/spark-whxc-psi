package com.spark.psi.order.onlinereturn.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnDet;

public class OnlineReturnDetImpl implements OnlineReturnDet {
	private GUID RECID;
	private GUID sheetId;
	private GUID onlineBillsItemId;
	private GUID goodsId;
	private String goodsCode;
	private String goodsNo;
	private String goodsSpec;
	private String goodsUnit;
	private String goodsName;
	private double count, billsCount;
	private double price;
	private double amount;

	public double getBillsCount() {
		return billsCount;
	}

	public void setBillsCount(double billsCount) {
		this.billsCount = billsCount;
	}

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

	public GUID getOnlineBillsItemId() {
		return onlineBillsItemId;
	}

	public void setOnlineBillsItemId(GUID onlineBillsItemId) {
		this.onlineBillsItemId = onlineBillsItemId;
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

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
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

}
