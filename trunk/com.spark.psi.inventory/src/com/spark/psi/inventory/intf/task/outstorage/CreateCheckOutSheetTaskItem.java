package com.spark.psi.inventory.intf.task.outstorage;

import com.jiuqi.dna.core.type.GUID;

public class CreateCheckOutSheetTaskItem {
	private GUID RECID         ;//�б�ʶ
	private GUID sheetId       ;//���ⵥrecid
	private GUID goodsId       ;//��Ʒrecid
	private String goodsCode     ;//��Ʒ����
	private String goodsNo       ;//��Ʒ����
	private String goodsName     ;//��Ʒ����
	private String goodsSpec     ;//��Ʒ���
	private String unit          ;//��Ʒ��λ
	private int scale         ;//��Ʒ��������
	private double price         ;//��Ʒ����
	private double avgCost       ;//���ɱ�
	private double amount        ;//��Ʒ���
	private double realCount     ;//�������� 
	public GUID getRECID() {
		return RECID;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
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
	public double getAvgCost() {
		return avgCost;
	}
	public void setAvgCost(double avgCost) {
		this.avgCost = avgCost;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRealCount() {
		return realCount;
	}
	public void setRealCount(double realCount) {
		this.realCount = realCount;
	}
	 

}
