/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.outstorage.entity
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-7       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.outstorage.mod;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/** 
 */

public class OutstorageItem {
	private GUID RECID;
	private GUID sheetId;
	private GUID goodsId;
	private String goodsCode;
	private String goodsNo;
	private String goodsName;
	private String goodsSpec;
	private String unit;
	private int scale;
	private double price;
	private double disRate;
	private double amount;
	private String creator;
	private long createDate;
	private double planCount;
	private double checkoutCount; 

	private double thisTimeCount;
	private double storeCount;
	private InventoryType goodsStorageType = InventoryType.Goods;

	public GUID getRECID() {
		return RECID;
	}

	public double getPlanCount() {
		return planCount;
	}

	public void setPlanCount(double planCount) {
		this.planCount = planCount;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public double getDisRate() {
		return disRate;
	}

	public void setDisRate(double disRate) {
		this.disRate = disRate;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	} 

	public double getCheckoutCount() {
		return checkoutCount;
	}

	public void setCheckoutCount(double checkoutCount) {
		this.checkoutCount = checkoutCount;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public double getThisTimeCount() {
		return thisTimeCount;
	}

	public void setThisTimeCount(double thisTimeCount) {
		this.thisTimeCount = thisTimeCount;
	}

	public double getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(double storeCount) {
		this.storeCount = storeCount;
	}

	public InventoryType getGoodsStorageType() {
		return goodsStorageType;
	}

	public void setGoodsStorageType(InventoryType goodsStorageType) {
		this.goodsStorageType = goodsStorageType;
	}

	public boolean equals(Object det) {
		boolean b = false;
		if (null == det) {
			return b;
		}
		if (det instanceof OutstorageItem) {
			OutstorageItem id = (OutstorageItem) det;
			if (null == id.getGoodsId()) {
				return b;
			}
			if (id.getGoodsId().equals(this.getGoodsId())) {
				b = true;
			}
		}
		return b;
	}
}
