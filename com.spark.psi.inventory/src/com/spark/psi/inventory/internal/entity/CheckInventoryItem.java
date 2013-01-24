package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/** 
 * <p>����̵���ϸ</p> 
 */
public class CheckInventoryItem{
	private GUID recid;
	private GUID tenantsGuid;//�⻧ID
	private GUID goodsGuid;//��ƷID
	private String remark;//˵��
	private GUID checkOrdGuid;//�̵㵥��ţ���Ӧ�̵㵥
	private double carryCount;//��������
	private double realCount = new Double(-1);//ʵ������,Ĭ��Ϊ-1
	private String goodsName;//��Ʒ����
	private String goodsAttr;//��Ʒ����
	private String unit;//��λ
	private String goodsItemNo;//���
	private String goodsItemCode;
	private String createPerson;//������
	private long createDate;//����ʱ��
	private GUID goodsTypeGuid;//��Ʒ����
	private String newGoods;//�Ƿ�Ϊ��������Ʒ
	private int goodsScale;//��Ʒ����
	
	public int getGoodsScale() {
		return goodsScale;
	}
	public void setGoodsScale(int goodsScale) {
		this.goodsScale = goodsScale;
	}
	public String getNewGoods() {
		return newGoods;
	}
	public void setNewGoods(String newGoods) {
		this.newGoods = newGoods;
	}
	public GUID getGoodsTypeGuid() {
		return goodsTypeGuid;
	}
	public void setGoodsTypeGuid(GUID goodsTypeGuid) {
		this.goodsTypeGuid = goodsTypeGuid;
	}
	public GUID getRecid() {
		return recid;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	public String getRemark() {
		return remark;
	}
	public GUID getCheckOrdGuid() {
		return checkOrdGuid;
	}
	public Double getCarryCount() {
		return carryCount;
	}
	public Double getRealCount() {
		return realCount;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public String getGoodsAttr() {
		return goodsAttr;
	}
	public String getUnit() {
		return unit;
	} 
	public String getCreatePerson() {
		return createPerson;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCheckOrdGuid(GUID checkOrdGuid) {
		this.checkOrdGuid = checkOrdGuid;
	}
	public void setCarryCount(double carryCount) {
		this.carryCount = carryCount;
	}
	public void setRealCount(double realCount) {
		this.realCount = realCount;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	} 
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public String getGoodsItemNo() {
		return goodsItemNo;
	}
	public void setGoodsItemNo(String goodsItemNo) {
		this.goodsItemNo = goodsItemNo;
	}
	public String getGoodsItemCode() {
		return goodsItemCode;
	}
	public void setGoodsItemCode(String goodsItemCode) {
		this.goodsItemCode = goodsItemCode;
	} 
}
