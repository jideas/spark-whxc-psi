package com.spark.oms.publish.receipt.entity;

import com.jiuqi.dna.core.type.GUID;

public class ReceiptInfo {
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
	 * ������Աno
	 */
	private String salerNo;
	
	/**
	 * ��¼��������
	 */
	private Long recordDate;
	
	/**
	 * �տ���
	 */
	private GUID PayeeRECID;
	
	/**
	 * �տ�������
	 */
	private String payeeName;
	
	/**
	 * �տ�״̬
	 */
	private String payeestatus;
	
	/**
	 * �տ�����
	 */
	private long payeeDate;
	
	/**
	 * ��Ʊ��
	 */
	private GUID drawerRECID;
	
	/**
	 * ��Ʊ������
	 */
	private String drawerName;
	
	/**
	 * ��Ʊ״̬
	 */
	private String drawstatus;
	
	/**
	 * ��Ʊ����
	 */
	private long drawDate;
	
	/**
	 * �˻�����
	 */
	private String accountNo;
	
	/**
	 * ������
	 */
	private String accountHolder;
	
	/**
	 * ������
	 */
	private String accountName;
	
	/**
	 * ����
	 */
	private String bankName;
	
	/**
	 * ״̬
	 * @return
	 */
	private String status;
	
	/**
	 * ��¼����
	 * @return
	 */
	private String recordType;
	
	/**
	 * �˿���
	 * @return
	 */
	private double refundAmount;
	
	/**
	 * Ӧ�ս��
	 * @return
	 */
	private double dueAmount;

	public double getDueAmount() {
		return dueAmount;
	}


	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}


	public double getRefundAmount() {
		return refundAmount;
	}


	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}


	public String getRecordType() {
		return recordType;
	}


	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}


	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}


	public GUID getRECID() {
		return RECID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getTakeOffFlag() {
		return takeOffFlag;
	}

	public double getReceiptAmount() {
		return receiptAmount;
	}

	public GUID getTenantsRECID() {
		return tenantsRECID;
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public GUID getSalerRECID() {
		return salerRECID;
	}

	public String getSalerName() {
		return salerName;
	}

	public Long getRecordDate() {
		return recordDate;
	}

	public GUID getPayeeRECID() {
		return PayeeRECID;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public String getPayeestatus() {
		return payeestatus;
	}

	public long getPayeeDate() {
		return payeeDate;
	}

	public GUID getDrawerRECID() {
		return drawerRECID;
	}

	public String getDrawerName() {
		return drawerName;
	}

	public String getDrawstatus() {
		return drawstatus;
	}

	public long getDrawDate() {
		return drawDate;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public void setTakeOffFlag(String takeOffFlag) {
		this.takeOffFlag = takeOffFlag;
	}

	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public void setTenantsRECID(GUID tenantsRECID) {
		this.tenantsRECID = tenantsRECID;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

	public void setSalerRECID(GUID salerRECID) {
		this.salerRECID = salerRECID;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}

	public void setRecordDate(Long recordDate) {
		this.recordDate = recordDate;
	}

	public void setPayeeRECID(GUID payeeRECID) {
		PayeeRECID = payeeRECID;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public void setPayeestatus(String payeestatus) {
		this.payeestatus = payeestatus;
	}

	public void setPayeeDate(long payeeDate) {
		this.payeeDate = payeeDate;
	}

	public void setDrawerRECID(GUID drawerRECID) {
		this.drawerRECID = drawerRECID;
	}

	public void setDrawerName(String drawerName) {
		this.drawerName = drawerName;
	}

	public void setDrawstatus(String drawstatus) {
		this.drawstatus = drawstatus;
	}

	public void setDrawDate(long drawDate) {
		this.drawDate = drawDate;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getSalerNo() {
		return salerNo;
	}


	public void setSalerNo(String salerNo) {
		this.salerNo = salerNo;
	}
	

	
	
}
