package com.spark.psi.inventory.intf.task.outstorage;

import com.jiuqi.dna.core.type.GUID;

public class CreateCheckOutSheetTaskItem {
	private GUID RECID         ;//行标识
	private GUID sheetId       ;//出库单recid
	private GUID goodsId       ;//商品recid
	private String goodsCode     ;//商品编码
	private String goodsNo       ;//商品条码
	private String goodsName     ;//商品名称
	private String goodsSpec     ;//商品规格
	private String unit          ;//商品单位
	private int scale         ;//商品数量精度
	private double price         ;//商品单价
	private double avgCost       ;//库存成本
	private double amount        ;//商品金额
	private double realCount     ;//出库数量 
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
	 

}
