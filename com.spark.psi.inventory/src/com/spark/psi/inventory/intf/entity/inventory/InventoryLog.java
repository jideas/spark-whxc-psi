/**
 * 
 */
package com.spark.psi.inventory.intf.entity.inventory;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * 库存流水
 *
 */
public class InventoryLog {
	private GUID RECID;//唯一标识
	private GUID storeGuid;//仓库guid
	private GUID goodsGuid;//商品GUID
	private String createPerson;//创建人
	private long createdDate;//创建日期
	private String goodsName;//商品名称
	private String goodsAttr;//商品属性
	private String goodsUnit;//商品单位
	private GUID goodsTypeGuid;//商品类型GUID
	private String goodsNo;//商品编号
	private int goodsScale;//商品精度
	private String streamType;//流水类型,到StoreStreamTypeEnum这个里面去找相应的枚举
	private long currDate;//创建时间
	private double instoCount;//入库数量
	private double instoAmount;//入库金额
	private double outstoCount;//出库数量
	private double outstoAmount;//出库数量
	private GUID orderGuid;//引起变化的单据的GUID
	private String orderNo;//引起变化的单据的编号
	private InventoryType inventoryType;
	/*----------------以下数据新增的时候不用传------------------------*/
	private int monthNo;//月,不传
	private int dayNo;//周,不传
	private int quarter;//季度,不传
	private int yearNo;//年,不传
	
	
	public int getMonthNo() {
		return monthNo;
	}
	public void setMonthNo(int monthNo) {
		this.monthNo = monthNo;
	}
	public int getDayNo() {
		return dayNo;
	}
	public void setDayNo(int dayNo) {
		this.dayNo = dayNo;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public int getYearNo() {
		return yearNo;
	}
	public void setYearNo(int yearNo) {
		this.yearNo = yearNo;
	}
	public GUID getRECID() {
		return RECID;
	}
	
	public GUID getStoreGuid() {
		return storeGuid;
	}
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	public long getCreatedDate() {
		return createdDate;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public String getGoodsAttr() {
		return goodsAttr;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public GUID getGoodsTypeGuid() {
		return goodsTypeGuid;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public int getGoodsScale() {
		return goodsScale;
	}
	public String getStreamType() {
		return streamType;
	}
	public long getCurrDate() {
		return currDate;
	}
	public GUID getOrderGuid() {
		return orderGuid;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public double getInstoCount() {
		return instoCount;
	}
	public double getInstoAmount() {
		return instoAmount;
	}
	public double getOutstoCount() {
		return outstoCount;
	}
	public double getOutstoAmount() {
		return outstoAmount;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	
	public void setStoreGuid(GUID storeGuid) {
		this.storeGuid = storeGuid;
	}
	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}
	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public void setGoodsTypeGuid(GUID goodsTypeGuid) {
		this.goodsTypeGuid = goodsTypeGuid;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public void setGoodsScale(int goodsScale) {
		this.goodsScale = goodsScale;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}
	public void setCurrDate(long currDate) {
		this.currDate = currDate;
	}
	public void setOrderGuid(GUID orderGuid) {
		this.orderGuid = orderGuid;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setInstoCount(double instoCount) {
		this.instoCount = instoCount;
	}
	public void setInstoAmount(double instoAmount) {
		this.instoAmount = instoAmount;
	}
	public void setOutstoCount(double outstoCount) {
		this.outstoCount = outstoCount;
	}
	public void setOutstoAmount(double outstoAmount) {
		this.outstoAmount = outstoAmount;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}
	public InventoryType getInventoryType() {
		return inventoryType;
	}
}
