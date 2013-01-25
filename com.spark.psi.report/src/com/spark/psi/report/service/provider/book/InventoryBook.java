/**
 * 
 */
package com.spark.psi.report.service.provider.book;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.InventoryBookInfo;

/**
 * ø‚¥ÊÃ®’À
 */
public class InventoryBook implements InventoryBookInfo {
	private GUID goodsId;
	private int goodsScale;
	private String goodsNo, goodsCode, goodsName, goodsAttr, goodsUnit, inventoryType;
	private double count_begin, amount_begin, instoCount, instoAmount, outstoCount, outstoAmount, count_end,
			amount_end;

	/**
	 * @return the goodsScale
	 */
	public int getGoodsScale() {
		return goodsScale;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	/**
	 * @param goodsScale
	 *            the goodsScale to set
	 */
	public void setGoodsScale(int goodsScale) {
		this.goodsScale = goodsScale;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	/**
	 * @return the goodsId
	 */
	public GUID getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId
	 *            the goodsId to set
	 */
	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * @return the goodsNo
	 */
	public String getGoodsNo() {
		return goodsNo;
	}

	/**
	 * @param goodsNo
	 *            the goodsNo to set
	 */
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName
	 *            the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @return the goodsAttr
	 */
	public String getGoodsAttr() {
		return goodsAttr;
	}

	/**
	 * @param goodsAttr
	 *            the goodsAttr to set
	 */
	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}

	/**
	 * @return the goodsUnit
	 */
	public String getGoodsUnit() {
		return goodsUnit;
	}

	/**
	 * @param goodsUnit
	 *            the goodsUnit to set
	 */
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	/**
	 * @return the count_begin
	 */
	public double getCount_begin() {
		return count_begin;
	}

	/**
	 * @param countBegin
	 *            the count_begin to set
	 */
	public void setCount_begin(double countBegin) {
		count_begin = countBegin;
	}

	/**
	 * @return the amount_begin
	 */
	public double getAmount_begin() {
		return amount_begin;
	}

	/**
	 * @param amountBegin
	 *            the amount_begin to set
	 */
	public void setAmount_begin(double amountBegin) {
		amount_begin = amountBegin;
	}

	/**
	 * @return the instoCount
	 */
	public double getInstoCount() {
		return instoCount;
	}

	/**
	 * @param instoCount
	 *            the instoCount to set
	 */
	public void setInstoCount(double instoCount) {
		this.instoCount += instoCount;
	}

	/**
	 * @return the instoAmount
	 */
	public double getInstoAmount() {
		return instoAmount;
	}

	/**
	 * @param instoAmount
	 *            the instoAmount to set
	 */
	public void setInstoAmount(double instoAmount) {
		this.instoAmount += instoAmount;
	}

	/**
	 * @return the outstoCount
	 */
	public double getOutstoCount() {
		return outstoCount;
	}

	/**
	 * @param outstoCount
	 *            the outstoCount to set
	 */
	public void setOutstoCount(double outstoCount) {
		this.outstoCount += outstoCount;
	}

	/**
	 * @return the outstoAmount
	 */
	public double getOutstoAmount() {
		return outstoAmount;
	}

	/**
	 * @param outstoAmount
	 *            the outstoAmount to set
	 */
	public void setOutstoAmount(double outstoAmount) {
		this.outstoAmount += outstoAmount;
	}

	/**
	 * @return the count_end
	 */
	public double getCount_end() {
		return count_end;
	}

	/**
	 * @param countEnd
	 *            the count_end to set
	 */
	public void setCount_end(double countEnd) {
		count_end = countEnd;
	}

	/**
	 * @return the amount_end
	 */
	public double getAmount_end() {
		return amount_end;
	}

	/**
	 * @param amountEnd
	 *            the amount_end to set
	 */
	public void setAmount_end(double amountEnd) {
		amount_end = amountEnd;
	}

}
