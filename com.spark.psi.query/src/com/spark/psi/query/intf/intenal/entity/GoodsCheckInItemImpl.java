package com.spark.psi.query.intf.intenal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.query.intf.publish.entity.GoodsCheckInItem;

/**
 * 成品入库单查询实体<BR>
 * 查询方法：<BR>
 * GoodsCheckInListEntity+GetGoodsCheckInListKey;
 * 
 */
public class GoodsCheckInItemImpl implements GoodsCheckInItem {

	private GUID sheetId;// 入库单recid
	private String sheetNo;
	private GUID goodsId;// 商品recid
	private String goodsCode;// 商品编码
	private String goodsName;// 商品名称
	private String unit;// 商品单位
	private double cost;// 实际成本
	private double amount;// 商品金额
	private double count;// 入库数量
	private double standardCost;
	private double standardAmount;
	private GUID produceSheetId;
	private String produceSheetNo;
	private String department;
	private long createDate;
	private boolean needProduce;
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getStandardCost() {
		return standardCost;
	}
	public void setStandardCost(double standardCost) {
		this.standardCost = standardCost;
	}
	public double getStandardAmount() {
		return standardAmount;
	}
	public void setStandardAmount(double standardAmount) {
		this.standardAmount = standardAmount;
	}
	public GUID getProduceSheetId() {
		return produceSheetId;
	}
	public void setProduceSheetId(GUID produceSheetId) {
		this.produceSheetId = produceSheetId;
	}
	public String getProduceSheetNo() {
		return produceSheetNo;
	}
	public void setProduceSheetNo(String produceSheetNo) {
		this.produceSheetNo = produceSheetNo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setNeedProduce(boolean needProduce) {
		this.needProduce = needProduce;
	}
	public boolean isNeedProduce() {
		return needProduce;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	

}
