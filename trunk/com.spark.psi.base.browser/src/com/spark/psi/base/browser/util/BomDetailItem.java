package com.spark.psi.base.browser.util;

import com.jiuqi.dna.core.type.GUID;

public class BomDetailItem {

	private GUID id;

	private String goodsCode, goodsNo, goodsName, goodsSpec, goodsUnit;
	private int scale;
	private double baseCount, lossRate, realCount, goodsLossRate;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
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

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
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

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public double getGoodsLossRate() {
		return goodsLossRate;
	}

	public void setGoodsLossRate(double goodsLossRate) {
		this.goodsLossRate = goodsLossRate;
	}

	public double getBaseCount() {
		return baseCount;
	}

	public void setBaseCount(double baseCount) {
		this.baseCount = baseCount;
	}

	public double getLossRate() {
		return lossRate;
	}

	public void setLossRate(double lossRate) {
		this.lossRate = lossRate;
	}

	public double getRealCount() {
		return realCount;
	}

	public void setRealCount(double realCount) {
		this.realCount = realCount;
	}
}
