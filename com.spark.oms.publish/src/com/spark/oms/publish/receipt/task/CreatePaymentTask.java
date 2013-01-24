package com.spark.oms.publish.receipt.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ������֧��¼
 * 
 * @author mengyongfeng
 *
 */
public class CreatePaymentTask extends SimpleTask {
	//id
	private GUID id;
	//��Ŀ����
	private String name;
	//��Ŀ����
	private String type;
	//�������Ĭ��ֵ
	private double income = 0;
	//֧������Ĭ��ֵ
	private double outlay = 0;
	//��¼����
	private long recordDate;
	//�շѷ���
	private GUID   receiptId;
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
	
	public GUID getReceiptId() {
		return receiptId;
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
	
	public void setReceiptId(GUID receiptId) {
		this.receiptId = receiptId;
	}
}
