package com.spark.oms.publish.product.entity;

import com.jiuqi.dna.core.type.GUID;

public class ProductVersionInfo {
	//��ʶ
	private GUID RECID;
	//��Ʒ��ʶ
	private GUID productRECID;
	//��Ʒ��Ŀ��ʶ
	private GUID productItemRECID;
	//�Ƽ�����
	private String billingCycle;
	//�Ƽۼ۸�
	private double billingPrice;
	//������
	private int prolongDay;
	public GUID getRECID() {
		return RECID;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	public GUID getProductRECID() {
		return productRECID;
	}
	public void setProductRECID(GUID productRECID) {
		this.productRECID = productRECID;
	}
	public GUID getProductItemRECID() {
		return productItemRECID;
	}
	public void setProductItemRECID(GUID productItemRECID) {
		this.productItemRECID = productItemRECID;
	}
	public String getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}
	public double getBillingPrice() {
		return billingPrice;
	}
	public void setBillingPrice(double billingPrice) {
		this.billingPrice = billingPrice;
	}
	public int getProlongDay() {
		return prolongDay;
	}
	public void setProlongDay(int prolongDay) {
		this.prolongDay = prolongDay;
	}
	
}
