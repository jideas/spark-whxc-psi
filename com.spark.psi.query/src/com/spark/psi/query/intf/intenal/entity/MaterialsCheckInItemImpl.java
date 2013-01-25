package com.spark.psi.query.intf.intenal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckInItem;

/**
 * 材料入库单查询实体<BR>
 * 查询方法：<BR>
 * MaterialsCheckInListEntity+GetMaterialsCheckInListKey;
 *
 */
public class MaterialsCheckInItemImpl implements MaterialsCheckInItem {

	private GUID sheetId;//	入库单recid
	private String sheetNo;//入库单号
	private GUID goodsId;//	商品recid
	private String goodsCode;//	商品编码
	private String goodsName;//	商品名称
	private String unit;//	商品单位
	private double price;//	商品单价
	private double amount;//	商品金额
	private double count;//	入库数量
	private double standardCost;//参考成本
	private double standardAmount;//参考金额
	private GUID purchaseSheetId;
	private String purchaseSheetNo;
	private long checkinDate;
	private CheckingInType checkingInType;
	private GUID partnerId;
	private String partnerName;
	private String partnerNo;
	private String department;
	
	public long getCheckinDate() {
		return checkinDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setCheckinDate(long checkinDate) {
		this.checkinDate = checkinDate;
	}
	public CheckingInType getCheckingInType() {
		return checkingInType;
	}
	public void setCheckingInType(CheckingInType checkingInType) {
		this.checkingInType = checkingInType;
	}
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public GUID getPurchaseSheetId() {
		return purchaseSheetId;
	}
	public void setPurchaseSheetId(GUID purchaseSheetId) {
		this.purchaseSheetId = purchaseSheetId;
	}
	public String getPurchaseSheetNo() {
		return purchaseSheetNo;
	}
	public void setPurchaseSheetNo(String purchaseSheetNo) {
		this.purchaseSheetNo = purchaseSheetNo;
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
	public void setPartnerNo(String partnerNo) {
		this.partnerNo = partnerNo;
	}
	public String getPartnerNo() {
		return partnerNo;
	}
	
	

	
}
