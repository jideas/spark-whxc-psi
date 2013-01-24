package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.InventoryBookItem;

/**
 * 库存台账项<br>
 * 查询方法：ListEntry<InventoryBookItem>+GetInventoryBookKey
 */
public class InventoryBookItemImpl implements InventoryBookItem {

	/**
	 * 商品条目Id
	 */
	private GUID goodsItemId;

	/**
	 * 商品条目代码
	 */
	private String goodsItemCode;

	/**
	 * 商品条目名称
	 */
	private String goodsItemName;

	/**
	 * 商品条目属性
	 */
	private String goodsItemProperties;

	/**
	 * 商品条目单位
	 */
	private String goodsItemUnit;

	/**
	 * 期初数量
	 */
	private double beginningCount;

	/**
	 * 期初金额
	 */
	private double beginningAmount;

	/**
	 * 入库数量
	 */
	private double checkedInCount;

	/**
	 * 入库金额
	 */
	private double checkedInAmount;

	/**
	 * 出库数量
	 */
	private double checkedOutCount;

	/**
	 * 出库金额
	 */
	private double checkedOutAmount;

	/**
	 * 期末数量
	 */
	private double endingCount;

	/**
	 * 期末金额
	 */
	public double endingAmount;

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	public String getGoodsItemCode() {
		return goodsItemCode;
	}

	public void setGoodsItemCode(String goodsItemCode) {
		this.goodsItemCode = goodsItemCode;
	}

	public String getGoodsItemName() {
		return goodsItemName;
	}

	public void setGoodsItemName(String goodsItemName) {
		this.goodsItemName = goodsItemName;
	}

	public String getGoodsItemProperties() {
		return goodsItemProperties;
	}

	public void setGoodsItemProperties(String goodsItemProperties) {
		this.goodsItemProperties = goodsItemProperties;
	}

	public String getGoodsItemUnit() {
		return goodsItemUnit;
	}

	public void setGoodsItemUnit(String goodsItemUnit) {
		this.goodsItemUnit = goodsItemUnit;
	}

	public double getBeginningCount() {
		return beginningCount;
	}

	public void setBeginningCount(double beginningCount) {
		this.beginningCount = beginningCount;
	}

	public double getBeginningAmount() {
		return beginningAmount;
	}

	public void setBeginningAmount(double beginningAmount) {
		this.beginningAmount = beginningAmount;
	}

	public double getCheckedInCount() {
		return checkedInCount;
	}

	public void setCheckedInCount(double checkedInCount) {
		this.checkedInCount = checkedInCount;
	}

	public double getCheckedInAmount() {
		return checkedInAmount;
	}

	public void setCheckedInAmount(double checkedInAmount) {
		this.checkedInAmount = checkedInAmount;
	}

	public double getCheckedOutCount() {
		return checkedOutCount;
	}

	public void setCheckedOutCount(double checkedOutCount) {
		this.checkedOutCount = checkedOutCount;
	}

	public double getCheckedOutAmount() {
		return checkedOutAmount;
	}

	public void setCheckedOutAmount(double checkedOutAmount) {
		this.checkedOutAmount = checkedOutAmount;
	}

	public double getEndingCount() {
		return endingCount;
	}

	public void setEndingCount(double endingCount) {
		this.endingCount = endingCount;
	}

	public double getEndingAmount() {
		return endingAmount;
	}

	public void setEndingAmount(double endingAmount) {
		this.endingAmount = endingAmount;
	}
	
	

}
