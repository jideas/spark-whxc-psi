package com.spark.psi.inventory.intf.entity.instorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>
 * 入库单明细实体
 * </p>
 * 
 * 
 * 
 * 
 * @author 王志坚
 * @version 2011-11-4
 */

public class InstorageItem {
	private GUID id;// 行标识
	private GUID sheetId;// 入库单recid
	private GUID goodsId;// 商品recid
	private String goodsCode;// 商品编码
	private String goodsNo;// 商品条码
	private String goodsName;// 商品名称
	private String goodsSpec;// 商品规格
	private String unit;// 商品单位
	private int scale;// 商品数量精度
	private double price;// 商品单价
	private double amount;// 商品金额
	private double count;// 预计入库数量
	private double checkinCount;// 已入库数量
	private double inspectCount;// 待检数量
	private double thisTimeCount;

	public double getThisTimeCount() {
		return thisTimeCount;
	}

	public void setThisTimeCount(double thisTimeCount) {
		this.thisTimeCount = thisTimeCount;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
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

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public double getCheckinCount() {
		return checkinCount;
	}

	public void setCheckinCount(double checkinCount) {
		this.checkinCount = checkinCount;
	}

	public double getInspectCount() {
		return inspectCount;
	}

	public void setInspectCount(double inspectCount) {
		this.inspectCount = inspectCount;
	}

}
