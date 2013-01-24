package com.spark.oms.publish.order.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

public class OrderServiceReceiptInfo {
	/**
	 * id
	 */
	private GUID RECID;
	
	/**
	 * 交款方式
	 */
	private String paymentType;
	
	/**
	 * 收付款标志
	 */
	private String takeOffFlag;
	
	/**
	 * 收缴金额合计
	 */
	private double receiptAmount;
	
	/**
	 * 客户Id
	 */
	private GUID tenantsRECID;
	
	/**
	 * 客户名称
	 */
	private String tenantsName;
	
	/**
	 * 销售人员编号
	 */
	private String SalerNo;
	
	/**
	 * 销售人员
	 */
	private GUID salerRECID;
	
	/**
	 * 销售人员姓名
	 */
	private String salerName;
	
	/**
	 * 记录生成日期
	 */
	private long recordDate;
	
	/**
	 * 收款人
	 */
	private GUID PayeeRECID;
	
	/**
	 * 收款人姓名
	 */
	private String payeeName;
	
	/**
	 * 收款状态
	 */
	private String payeestatus;
	
	/**
	 * 收款日期
	 */
	private long payeeDate;
	
	/**
	 * 开票人
	 */
	private GUID drawerRECID;
	
	/**
	 * 开票人姓名
	 */
	private String drawerName;
	
	/**
	 * 开票状态
	 */
	private String drawstatus;
	
	/**
	 * 开票日期
	 */
	private long drawDate;
	
	/**
	 *账户id
	 */
	private GUID accountRECID;
	
	/**
	 * 账户卡号
	 */
	private String accountNo;
	
	/**
	 * 开户人
	 */
	private String accountHolder;
	
	/**
	 * 开户行
	 */
	private String accountName;
	
	/**
	 * 银行
	 */
	private String bankName;
	
	/**
	 * 缴款记录状态
	 */
	private String status;
	
	/**
	 * 产品系列
	 */
	private String productSerial;
	
	/**
	 * 状态
	 */
	private String chargestatus;
	
	/**
	 * 记录类型
	 */
	private String recordType;
	
	private double refundAmount;
	
	private double dueAmount;
	
	/**
	 * 设置实际付款金额
	 * @return
	 */
	private double doneAmount;
	
	/**
	 * 订单服务
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
