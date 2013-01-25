package com.spark.produceorder.intf.entity;

import com.jiuqi.dna.core.type.GUID;

public class ProduceGoodsDetEntity {

	private GUID id;//	行标识
	private GUID billsId;//	订单GUID
	private GUID goodsId;//	商品Guid
	private String goodsCode;//	商品编号
	private String goodsNo;//	商品条码
	private String goodsName;//	商品名称
	private String goodsSpec;//	商品规格
	private String unit;//	单位
	private int goodsScale;//	商品小数位数
	private double count;//	数量
	private GUID bomId;//	bom表Id
	private double finishedCount;//	已完成数量
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
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
	public int getGoodsScale() {
		return goodsScale;
	}
	public void setGoodsScale(int goodsScale) {
		this.goodsScale = goodsScale;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public GUID getBomId() {
		return bomId;
	}
	public void setBomId(GUID bomId) {
		this.bomId = bomId;
	}
	public double getFinishedCount() {
		return finishedCount;
	}
	public void setFinishedCount(double finishedCount) {
		this.finishedCount = finishedCount;
	}

}
