package com.spark.oms.publish.receipt.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��֧��¼
 */
public class PaymentInfo {
	//id
	private GUID id;
	//��Ŀ����
	private String name;
	//��Ŀ����
	private String type;
	//����
	private double income;
	//֧��
	private double outlay;
	//����ܼ�
	private double totalAmount;
	//��¼����
	private long recordDate;
	//������Դ
	private String sourceType;
	//�շѷ���
	private GUID   orderServiceId;
	public GUID getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public double getIncome() {
		return income;
	}
	public double getOutlay() {
		return outlay;
	}
	public String getSourceType() {
		return sourceType;
	}
	public GUID getOrderServiceId() {
		return orderServiceId;
	}
	
	public long getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(long recordDate) {
		this.recordDate = recordDate;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public void setOutlay(double outlay) {
		this.outlay = outlay;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public void setOrderServiceId(GUID orderServiceId) {
		this.orderServiceId = orderServiceId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
