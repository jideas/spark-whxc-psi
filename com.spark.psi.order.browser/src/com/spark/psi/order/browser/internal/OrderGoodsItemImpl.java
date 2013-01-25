package com.spark.psi.order.browser.internal;

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
class OrderGoodsItemImpl implements com.spark.psi.publish.order.entity.OrderGoodsItem {

	private GUID goodsItemId;// ��Ʒ��Ŀid

	private String goodsCode;// ��Ʒ���� V��20��

	private String name;// ��Ʒ���� V(50)

	private String spec;// ��Ʒ��� V(100)

	private String unit;// ��λ C��2��

	private int scale;// ��ƷС��λ�� INT

	private double price;// ���� NUM(17,2)

	private double count;// ���� NUM(10,2)

	private double amount;// ��� NUM(17,2
	private GUID id;

	private String goodsNo;

	public GUID getId() {
		return id;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	public double getPrice() {
		return price;
	}

	public double getCount() {
		return count;
	}

	public void setScale(int scale) {
		this.scale = scale;
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public int getScale() {
		return scale;
	}

}
