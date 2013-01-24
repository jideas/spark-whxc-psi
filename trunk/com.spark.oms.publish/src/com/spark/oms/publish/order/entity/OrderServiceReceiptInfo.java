package com.spark.oms.publish.order.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

public class OrderServiceReceiptInfo {
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
	 * ������Ա���
	 */
	private String SalerNo;
	
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
	private long recordDate;
	
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
	 *�˻�id
	 */
	private GUID accountRECID;
	
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
	 * �ɿ��¼״̬
	 */
	private String status;
	
	/**
	 * ��Ʒϵ��
	 */
	private String productSerial;
	
	/**
	 * ״̬
	 */
	private String chargestatus;
	
	/**
	 * ��¼����
	 */
	private String recordType;
	
	private double refundAmount;
	
	private double dueAmount;
	
	/**
	 * ����ʵ�ʸ�����
	 * @return
	 */
	private double doneAmount;
	
	/**
	 * ��������
	 * @return
	 */
	private List<OrderServiceInfo> orderServiceList;
	
	
	
	public double getDoneAmount() {
		return doneAmount;
	}

	public void setDoneAmount(double doneAmount) {
		this.doneAmount = doneAmount;
	}
	
	public String getSalerNo() {
		return SalerNo;
	}

	public void setSalerNo(String salerNo) {
		SalerNo = salerNo;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderServiceInfo> getOrderServiceList() {
		return orderServiceList;
	}

	public void setOrderServiceList(List<OrderServiceInfo> orderServiceList) {
		this.orderServiceList = orderServiceList;
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

	public long getRecordDate() {
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

	public void setRecordDate(long recordDate) {
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

	public String getProductSerial() {
		return productSerial;
	}

	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}

	public GUID getAccountRECID() {
		return accountRECID;
	}

	public void setAccountRECID(GUID accountRECID) {
		this.accountRECID = accountRECID;
	}

	public String getChargestatus() {
		return chargestatus;
	}

	public void setChargestatus(String chargestatus) {
		this.chargestatus = chargestatus;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	
	
}
