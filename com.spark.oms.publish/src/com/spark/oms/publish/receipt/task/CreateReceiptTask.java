package com.spark.oms.publish.receipt.task;

import java.util.Date;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 生成缴款记录
 * 销售人员，新建缴款或客户管理退款操作生成
 */
public class CreateReceiptTask  extends SimpleTask{
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
