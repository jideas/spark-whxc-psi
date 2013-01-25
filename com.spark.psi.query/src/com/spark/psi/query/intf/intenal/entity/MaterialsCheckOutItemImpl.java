package com.spark.psi.query.intf.intenal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckOutItem;

/**
 * 材料出库单查询实体<BR>
 * 查询方法：<BR>
 * MaterialsCheckOutListEntity+GetMaterialsCheckOutListKey;
 *
 */
public class MaterialsCheckOutItemImpl implements MaterialsCheckOutItem {

	private GUID sheetId;//	出库单recid
	private String sheetNo;//	出库单号
	private GUID goodsId;//	商品recid
	private String goodsCode;//	商品编码
	private String goodsName;//	商品名称
	private String unit;//	商品单位
	private double amount;//	商品金额
	private double count;//	出库数量
	private double cost;//成本
	private CheckingOutType checkingOutType;
	private long createDate;
	private String department;
	private GUID produceSheetId;
	private String produceSheetNo;
	
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
	public GUID getSheetId() {
		return sheetId;
	}
	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public CheckingOutType getCheckingOutType() {
		return checkingOutType;
	}
	public void setCheckingOutType(CheckingOutType checkingOutType) {
		this.checkingOutType = checkingOutType;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	

}
