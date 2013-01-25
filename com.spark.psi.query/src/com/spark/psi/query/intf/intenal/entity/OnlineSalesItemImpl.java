package com.spark.psi.query.intf.intenal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.query.intf.publish.entity.OnlineSalesItem;

/**
 * 
 * 线上商品销售记录<BR>
 * 查询方法：<BR>
 * GetOnlineSalesListKey+OnlineSalesListEntity;
 *
 */
public class OnlineSalesItemImpl implements OnlineSalesItem {

	private GUID goodsId;
	private String goodsCode;
	private String goodsNo;
	private String goodsName;
	private String goodsSpec;
	private String unit;
	private double count;
	private double amount;
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
	

}
