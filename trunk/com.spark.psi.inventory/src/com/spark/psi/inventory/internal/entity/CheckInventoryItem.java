package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/** 
 * <p>库存盘点明细</p> 
 */
public class CheckInventoryItem{
	private GUID recid;
	private GUID tenantsGuid;//租户ID
	private GUID goodsGuid;//商品ID
	private String remark;//说明
	private GUID checkOrdGuid;//盘点单编号，对应盘点单
	private double carryCount;//账面数量
	private double realCount = new Double(-1);//实盘数量,默认为-1
	private String goodsName;//商品名称
	private String goodsAttr;//商品属性
	private String unit;//单位
	private String goodsItemNo;//编号
	private String goodsItemCode;
	private String createPerson;//创建人
	private long createDate;//创建时间
	private GUID goodsTypeGuid;//商品分类
	private String newGoods;//是否为新增的商品
	private int goodsScale;//商品精度
	
	public int getGoodsScale() {
		return goodsScale;
	}
	public void setGoodsScale(int goodsScale) {
		this.goodsScale = goodsScale;
	}
	public String getNewGoods() {
		return newGoods;
	}
	public void setNewGoods(String newGoods) {
		this.newGoods = newGoods;
	}
	public GUID getGoodsTypeGuid() {
		return goodsTypeGuid;
	}
	public void setGoodsTypeGuid(GUID goodsTypeGuid) {
		this.goodsTypeGuid = goodsTypeGuid;
	}
	public GUID getRecid() {
		return recid;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	public String getRemark() {
		return remark;
	}
	public GUID getCheckOrdGuid() {
		return checkOrdGuid;
	}
	public Double getCarryCount() {
		return carryCount;
	}
	public Double getRealCount() {
		return realCount;
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
	public String getCreatePerson() {
		return createPerson;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCheckOrdGuid(GUID checkOrdGuid) {
		this.checkOrdGuid = checkOrdGuid;
	}
	public void setCarryCount(double carryCount) {
		this.carryCount = carryCount;
	}
	public void setRealCount(double realCount) {
		this.realCount = realCount;
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
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public String getGoodsItemNo() {
		return goodsItemNo;
	}
	public void setGoodsItemNo(String goodsItemNo) {
		this.goodsItemNo = goodsItemNo;
	}
	public String getGoodsItemCode() {
		return goodsItemCode;
	}
	public void setGoodsItemCode(String goodsItemCode) {
		this.goodsItemCode = goodsItemCode;
	} 
}
