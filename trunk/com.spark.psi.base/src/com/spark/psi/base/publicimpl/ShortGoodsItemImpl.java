package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;
import com.spark.psi.publish.base.goods.entity.ShortGoodsItem;

public class ShortGoodsItemImpl implements ShortGoodsItem {

	public ShortGoodsItemImpl() {

	}

	public ShortGoodsItemImpl(GoodsInfo goodsInfo, GoodsItemData itemData) {
		this.id = itemData.getId();
		this.name = goodsInfo.getName();
		this.goodsCode = goodsInfo.getCode();
		this.goodsNo = itemData.getGoodsItemNo();
		this.goodsSpec = itemData.getSpec();
		this.goodsUnit = itemData.getUnit();
		this.salesPrice = itemData.getSalePrice();
		this.originalPrice = itemData.getOriginalPrice();
		this.bomId = itemData.getBomId();
	}

	private GUID id;
	private String name, goodsCode, goodsNo;
	private String goodsSpec;
	private String goodsUnit;
	private double salesPrice;
	private double originalPrice;// ‘≠º€
	private GUID bomId;

	public String getBomStatus() {
		if (null == bomId) {
			return "Œ¥≈‰÷√";
		} else {
			return "“—≈‰÷√";
		}
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
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

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public GUID getBomId() {
		return bomId;
	}

	public void setBomId(GUID bomId) {
		this.bomId = bomId;
	}
}
