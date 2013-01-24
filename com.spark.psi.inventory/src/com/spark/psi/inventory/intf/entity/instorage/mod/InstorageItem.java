package com.spark.psi.inventory.intf.entity.instorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>
 * ��ⵥ��ϸʵ��
 * </p>
 * 
 * 
 * 
 * 
 * @author ��־��
 * @version 2011-11-4
 */

public class InstorageItem {
	private GUID id;// �б�ʶ
	private GUID sheetId;// ��ⵥrecid
	private GUID goodsId;// ��Ʒrecid
	private String goodsCode;// ��Ʒ����
	private String goodsNo;// ��Ʒ����
	private String goodsName;// ��Ʒ����
	private String goodsSpec;// ��Ʒ���
	private String unit;// ��Ʒ��λ
	private int scale;// ��Ʒ��������
	private double price;// ��Ʒ����
	private double amount;// ��Ʒ���
	private double count;// Ԥ���������
	private double checkinCount;// ���������
	private double inspectCount;// ��������
	private double thisTimeCount;

	public double getThisTimeCount() {
		return thisTimeCount;
	}

	public void setThisTimeCount(double thisTimeCount) {
		this.thisTimeCount = thisTimeCount;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public GUID getSheetId() {
		return sheetId;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

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

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public double getCheckinCount() {
		return checkinCount;
	}

	public void setCheckinCount(double checkinCount) {
		this.checkinCount = checkinCount;
	}

	public double getInspectCount() {
		return inspectCount;
	}

	public void setInspectCount(double inspectCount) {
		this.inspectCount = inspectCount;
	}

}
