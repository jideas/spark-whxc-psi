package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * 采购清单
 * </p>
 * 获得采购需求清单 查询方法：ListEntity<PurchaseGoodsItem>+String
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * 
 * @version 2012-2-22
 */
public class PurchaseGoodsItemImpl implements com.spark.psi.publish.order.entity.PurchaseGoodsItem {

	private GUID goodsItemId;// 商品条目id

	private String goodsCode, goodsNo;// 商品条码 V（20）

	private String goodsName;// 商品名称 V(50)

	private String properties;// 商品属性 V(100)

	private String unit;// 单位 C（2）

	private int countDecimal;// 商品小数位数 INT

	private double price = -1;// 单价 NUM(17,2)

	private double count;// 数量 NUM(10,2)

	private GUID supplierId; // 供应商id

	private double recentPrice; // 上次采购单价

	private GUID storeId; // 仓库ID

	private String storeName; // 仓库名称

	// 扩展
	private long directDeliveryDate;// 直供交货日期
	private String salesMemo;// 销售订单备注
	private boolean directDelivery;
	private GUID id;
	private GUID contactId;
	private String supplierName;
	private String supplierShorName;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierShorName() {
		return supplierShorName;
	}

	public void setSupplierShorName(String supplierShorName) {
		this.supplierShorName = supplierShorName;
	}

	public GUID getContactId() {
		return contactId;
	}

	public void setContactId(GUID contactId) {
		this.contactId = contactId;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getProperties() {
		return properties;
	}

	public String getUnit() {
		return unit;
	}

	public int getScale() {
		return countDecimal;
	}

	public double getPrice() {
		return price;
	}

	public double getCount() {
		return count;
	}

	public GUID getSupplierId() {
		return supplierId;
	}

	public double getRecentPrice() {
		return recentPrice;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param goodsItemId
	 *            the goodsItemId to set
	 */
	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	/**
	 * @param goodsName
	 *            the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(String properties) {
		this.properties = properties;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @param countDecimal
	 *            the countDecimal to set
	 */
	public void setCountDecimal(int countDecimal) {
		this.countDecimal = countDecimal;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(double count) {
		this.count = count;
	}

	/**
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(GUID supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @param recentPrice
	 *            the recentPrice to set
	 */
	public void setRecentPrice(double recentPrice) {
		this.recentPrice = recentPrice;
	}

	/**
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	/**
	 * @param storeName
	 *            the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the directDelivery
	 */
	public boolean isDirectDelivery() {
		return directDelivery;
	}

	/**
	 * @param directDelivery
	 *            the directDelivery to set
	 */
	public void setDirectDelivery(boolean directDelivery) {
		this.directDelivery = directDelivery;
	}

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	public long getDirectDeliveryDate() {
		return directDeliveryDate;
	}

	public void setDirectDeliveryDate(long directDeliveryDate) {
		this.directDeliveryDate = directDeliveryDate;
	}

	public String getSalesMemo() {
		return salesMemo;
	}

	public void setSalesMemo(String salesMemo) {
		this.salesMemo = salesMemo;
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
}
