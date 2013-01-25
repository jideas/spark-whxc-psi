package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * ������Ʒ��ϸ
 * </p>
 * ����/�˻� ��Ʒ��ϸ����
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * 
 * 
 * @author ������
 * @version 2012-2-22
 */
public class OrderGoodsItemImpl implements com.spark.psi.publish.order.entity.OrderGoodsItem {

	private GUID goodsItemId;// ��Ʒ��Ŀid

	private String goodsNo;

	private String code;// ��Ʒ���� V��20��

	private String name;// ��Ʒ���� V(50)

	private String properties;// ��Ʒ���� V(100)

	private String unit;// ��λ C��2��

	private int countDecimal;// ��ƷС��λ�� INT

	private double price = -1;// ���� NUM(17,2)

	private double count;// ���� NUM(10,2)

	private double amount;// ��� NUM(17,2
	private GUID id;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
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

	public double getAmount() {
		return amount;
	}

	/**
	 * @param goodsItemId
	 *            the goodsItemId to set
	 */
	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getGoodsCode() {
		return code;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public String getSpec() {
		return properties;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

}
