package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.InventoryInfo;

/**
 * ��Ʒ�����ϸ����<br>
 * ��ѯ������<br>
 * (1)����GetGoodsInventoryDetailListKey��ѯInventoryInfoImpl�б�<br>
 * (2)����GetWarningGoodsItemListKey��ѯInventoryInfoImpl�б�<br>
 */
public class InventoryInfoImpl implements InventoryInfo {

	/**
	 * ��Ʒ��Ŀid
	 */
	private GUID goodsItemId;

	/**
	 * �ֿ�ID
	 */
	private GUID storeId;

	/**
	 * �������
	 */
	private double count;

	/**
	 * �����
	 */
	private double amount;

	/**
	 * �ɹ�������
	 */
	private double purchasingCount;

	/**
	 * �ɹ��н��
	 */
	private double purchasingAmount;

	/**
	 * ��������
	 */
	private double deliveryingCount;

	/**
	 * ����������
	 */
	private double deliveryingAmount;

	/**
	 * �����������
	 */
	private double upperLimitCount;

	/**
	 * �����������
	 */
	private double lowerLimitCount;

	/**
	 * ���������
	 */
	private double upperLimitAmount;

	/**
	 * ���������
	 */
	private double lowerLimitAmount;

	/**
	 * �ɹ���������
	 */
	private double adviseCount;

	private String goodsName;
	private String goodsProperties;
	private String goodsUnit;
	private int countDecimal;
	private String storeName;
	private String goodsCode, goodsNo;

	public String getGoodsProperties() {
		return goodsProperties;
	}

	public void setGoodsProperties(String goodsProperties) {
		this.goodsProperties = goodsProperties;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
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

	public int getCountDecimal() {
		return countDecimal;
	}

	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
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

	public double getPurchasingCount() {
		return purchasingCount;
	}

	public void setPurchasingCount(double purchasingCount) {
		this.purchasingCount = purchasingCount;
	}

	public double getPurchasingAmount() {
		return purchasingAmount;
	}

	public void setPurchasingAmount(double purchasingAmount) {
		this.purchasingAmount = purchasingAmount;
	}

	public double getDeliveryingCount() {
		return deliveryingCount;
	}

	public void setDeliveryingCount(double deliveryingCount) {
		this.deliveryingCount = deliveryingCount;
	}

	public double getDeliveryingAmount() {
		return deliveryingAmount;
	}

	public void setDeliveryingAmount(double deliveryingAmount) {
		this.deliveryingAmount = deliveryingAmount;
	}

	public double getUpperLimitCount() {
		return upperLimitCount;
	}

	public void setUpperLimitCount(double upperLimitCount) {
		this.upperLimitCount = upperLimitCount;
	}

	public double getLowerLimitCount() {
		return lowerLimitCount;
	}

	public void setLowerLimitCount(double lowerLimitCount) {
		this.lowerLimitCount = lowerLimitCount;
	}

	public double getUpperLimitAmount() {
		return upperLimitAmount;
	}

	public void setUpperLimitAmount(double upperLimitAmount) {
		this.upperLimitAmount = upperLimitAmount;
	}

	public double getLowerLimitAmount() {
		return lowerLimitAmount;
	}

	public void setLowerLimitAmount(double lowerLimitAmount) {
		this.lowerLimitAmount = lowerLimitAmount;
	}

	public double getAdviseCount() {
		return adviseCount;
	}

	public void setAdviseCount(double adviseCount) {
		this.adviseCount = adviseCount;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setCountDecimal(int countDecimal) {
		this.countDecimal = countDecimal;
	}

	public int getScale() {
		return countDecimal;
	}
}
