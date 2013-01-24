package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.account.entity.PaymentInfoItem;

public class PaymentInfoItemImpl implements PaymentInfoItem {

	private GUID id;// 标识
	private GUID paymentId;// 申请单id
	private GUID checkinSheetId;// 入库单id
	private String sheetNo;// 入库单号
	private GUID relevantBillId;// 相关单据Id
	private String relevantBillNo;// 相关单据
	private long checkinDate;// 入库日期
	private double amount;// 入库金额
	private double askAmount;// 申请金额
	private double paidAmount;// 已付金额
	private double payingAmount;// 未付金额
	private double molingAmount;// 抹零金额
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(GUID paymentId) {
		this.paymentId = paymentId;
	}
	public GUID getCheckinSheetId() {
		return checkinSheetId;
	}
	public void setCheckinSheetId(GUID checkinSheetId) {
		this.checkinSheetId = checkinSheetId;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public GUID getRelevantBillId() {
		return relevantBillId;
	}
	public void setRelevantBillId(GUID relevantBillId) {
		this.relevantBillId = relevantBillId;
	}
	public String getRelevantBillNo() {
		return relevantBillNo;
	}
	public void setRelevantBillNo(String relevantBillNo) {
		this.relevantBillNo = relevantBillNo;
	}
	public long getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(long checkinDate) {
		this.checkinDate = checkinDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAskAmount() {
		return askAmount;
	}
	public void setAskAmount(double askAmount) {
		this.askAmount = askAmount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getPayingAmount() {
		return payingAmount;
	}
	public void setPayingAmount(double payingAmount) {
		this.payingAmount = payingAmount;
	}
	public double getMolingAmount() {
		return molingAmount;
	}
	public void setMolingAmount(double molingAmount) {
		this.molingAmount = molingAmount;
	}

	
}
