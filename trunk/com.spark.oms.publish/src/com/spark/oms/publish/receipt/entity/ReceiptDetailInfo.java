package com.spark.oms.publish.receipt.entity;

import com.jiuqi.dna.core.type.GUID;

public class ReceiptDetailInfo {
	
	/**
	 * id
	 */
	private GUID RECID;

	/**
	 * ���ʽ
	 */
	private String paymentType;

	/**
	 * ��¼����
	 * 
	 * @return
	 */
	private String recordType;

	/**
	 * �˻�����/֧Ʊ��
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


	//��Ʒ����
	private String PRODUCTSERIAL;
	private String PRODUCTCODE;
	//���ù��
	private String productItem;
	private String PRODUCTITEMNAME;
	private int USERNUM;
	private int USERUPOFFSET;
	
	//�Ʒ�����
	private String billingCycle;

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

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPRODUCTSERIAL() {
		return PRODUCTSERIAL;
	}

	public void setPRODUCTSERIAL(String pRODUCTSERIAL) {
		PRODUCTSERIAL = pRODUCTSERIAL;
	}

	public String getPRODUCTCODE() {
		return PRODUCTCODE;
	}

	public void setPRODUCTCODE(String pRODUCTCODE) {
		PRODUCTCODE = pRODUCTCODE;
	}

	public String getProductItem() {
		return productItem;
	}

	public void setProductItem(String productItem) {
		this.productItem = productItem;
	}

	public String getPRODUCTITEMNAME() {
		return PRODUCTITEMNAME;
	}

	public void setPRODUCTITEMNAME(String pRODUCTITEMNAME) {
		PRODUCTITEMNAME = pRODUCTITEMNAME;
	}

	public int getUSERNUM() {
		return USERNUM;
	}

	public void setUSERNUM(int uSERNUM) {
		USERNUM = uSERNUM;
	}

	public int getUSERUPOFFSET() {
		return USERUPOFFSET;
	}

	public void setUSERUPOFFSET(int uSERUPOFFSET) {
		USERUPOFFSET = uSERUPOFFSET;
	}

	public String getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}
}