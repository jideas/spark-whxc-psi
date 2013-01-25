package com.spark.order.intf.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;

/**
 * ¶©µ¥Ã÷Ï¸¸¸Àà
 */
@StructClass
public class OrderDet {
	private GUID RECID;
	private GUID billsId;
	private GUID goodsId;
	private String goodsCode;
	private String goodsNo;
	private String goodsName;
	private String goodsSpec;
	private String unit;
	private int scale;
	private double price;
	private double count;
	private double amount;
	private String creator;
	private long createDate;
	public GUID getRECID() {
		return RECID;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	public GUID getBillsId() {
		return billsId;
	}
	public void setBillsId(GUID billsId) {
		this.billsId = billsId;
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
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
}
