package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.intf.inventoryenum.RefactorGoodsItemType;

/**
 * 
 * <p>拆装明细</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class DismountingItem{
	private GUID RECID;//
	private GUID tenantsGuid;//
	private String dismFlag;
	private GUID dismGuid;
	private double dismCount;
	private double storeCost;
	private GUID goodsGuid;
	private String createPerson;
	private long createDate;
	private String goodsName;
	private String goodsAttr;
	private String unit;
	private String goodsNo;
	private double money;
	private int goodsScale;//商品精度
	private Double storeNumber;//原库存数量,这个用于对比开始拆装的时候用户输入的拆装前的商品数量是不是大于库存数量,数据库没这个字段
	private GUID goodsTypeGuid;//商品类型的GUID，不存数据库，用于传值的
	
	public GUID getRECID() {
		return RECID;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public String getDismFlag() {
		return dismFlag;
	}
	public GUID getDismGuid() {
		return dismGuid;
	}
	public Double getDismCount() {
		return dismCount;
	}
	public Double getStoreCost() {
		return storeCost;
	}
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public String getGoodsAttr() {
		return goodsAttr;
	}
	public String getUnit() {
		return unit;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	public void setDismFlag(String dismFlag) {
		this.dismFlag = dismFlag;
	}
	public void setDismGuid(GUID dismGuid) {
		this.dismGuid = dismGuid;
	}
	public void setDismCount(Double dismCount) {
		this.dismCount = dismCount;
	}
	public void setStoreCost(Double storeCost) {
		this.storeCost = storeCost;
	}
	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getMoney() {
		return money;
	}
	public void setGoodsScale(int goodsScale) {
		this.goodsScale = goodsScale;
	}
	public int getGoodsScale() {
		return goodsScale;
	}
	public void setStoreNumber(Double storeNumber) {
		this.storeNumber = storeNumber;
	}
	public Double getStoreNumber() {
		return storeNumber;
	}
	public void setGoodsTypeGuid(GUID goodsTypeGuid) {
		this.goodsTypeGuid = goodsTypeGuid;
	}
	public GUID getGoodsTypeGuid() {
		return goodsTypeGuid;
	}
	
	
}
