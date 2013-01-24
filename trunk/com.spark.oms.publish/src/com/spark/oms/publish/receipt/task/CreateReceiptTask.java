package com.spark.oms.publish.receipt.task;

import java.util.Date;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ���ɽɿ��¼
 * ������Ա���½��ɿ��ͻ������˿��������
 */
public class CreateReceiptTask  extends SimpleTask{
	/**
	 * id
	 */
	private GUID RECID;
	
	/**
	 * ���ʽ
	 */
	private String paymentType;
	
	/**
	 * �ո����־
	 */
	private String takeOffFlag;
	
	/**
	 * �սɽ��ϼ�
	 */
	private double receiptAmount;
	
	/**
	 * �ͻ�Id
	 */
	private GUID tenantsRECID;
	
	/**
	 * �ͻ�����
	 */
	private String tenantsName;
	
	/**
	 * ������Ա
	 */
	private GUID salerRECID;
	
	/**
	 * ������Ա����
	 */
	private String salerName;
	
	/**
	 * ��¼��������
	 */
	private Date recordDate;

	public GUID getRECID() {
		return RECID;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getTakeOffFlag() {
		return takeOffFlag;
	}

	public void setTakeOffFlag(String takeOffFlag) {
		this.takeOffFlag = takeOffFlag;
	}

	public double getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public GUID getTenantsRECID() {
		return tenantsRECID;
	}

	public void setTenantsRECID(GUID tenantsRECID) {
		this.tenantsRECID = tenantsRECID;
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

	public GUID getSalerRECID() {
		return salerRECID;
	}

	public void setSalerRECID(GUID salerRECID) {
		this.salerRECID = salerRECID;
	}

	public String getSalerName() {
		return salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
}
