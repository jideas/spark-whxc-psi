package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.InventoryBookItem;

/**
 * ���̨����<br>
 * ��ѯ������ListEntry<InventoryBookItem>+GetInventoryBookKey
 */
public class InventoryBookItemImpl implements InventoryBookItem {

	/**
	 * ��Ʒ��ĿId
	 */
	private GUID goodsItemId;

	/**
	 * ��Ʒ��Ŀ����
	 */
	private String goodsItemCode;

	/**
	 * ��Ʒ��Ŀ����
	 */
	private String goodsItemName;

	/**
	 * ��Ʒ��Ŀ����
	 */
	private String goodsItemProperties;

	/**
	 * ��Ʒ��Ŀ��λ
	 */
	private String goodsItemUnit;

	/**
	 * �ڳ�����
	 */
	private double beginningCount;

	/**
	 * �ڳ����
	 */
	private double beginningAmount;

	/**
	 * �������
	 */
	private double checkedInCount;

	/**
	 * �����
	 */
	private double checkedInAmount;

	/**
	 * ��������
	 */
	private double checkedOutCount;

	/**
	 * ������
	 */
	private double checkedOutAmount;

	/**
	 * ��ĩ����
	 */
	private double endingCount;

	/**
	 * ��ĩ���
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
