package com.spark.psi.account.intf.entity.receipt;

import com.jiuqi.dna.core.type.GUID;


public class ReceiptLogEntity {

	private GUID id;// 标识
	private GUID receiptsId;// 通知单id
	private String receiptNo;// 通知单编号
	private GUID checkoutSheetId;// 入库单id
	private String sheetNo;// 入库单号
	private GUID relevantBillId;// 相关单据Id
	private String relevantBillNo;// 相关单据
	private long checkinDate;// 入库日期
	private double amount;// 收款金额
	private double molingAmount;// 抹零金额
	private GUID receiptPersonId;// 收款人
	private String receiptPersonName;// 姓名
	private long receiptDate;// 收款时间
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getReceiptsId() {
		return receiptsId;
	}
	public void setReceiptsId(GUID receiptsId) {
		this.receiptsId = receiptsId;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public GUID getCheckoutSheetId() {
		return checkoutSheetId;
	}
	public void setCheckoutSheetId(GUID checkoutSheetId) {
		this.checkoutSheetId = checkoutSheetId;
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
	public double getMolingAmount() {
		return molingAmount;
	}
	public void setMolingAmount(double molingAmount) {
		this.molingAmount = molingAmount;
	}
	public GUID getReceiptPersonId() {
		return receiptPersonId;
	}
	public void setReceiptPersonId(GUID receiptPersonId) {
		this.receiptPersonId = receiptPersonId;
	}
	public String getReceiptPersonName() {
		return receiptPersonName;
	}
	public void setReceiptPersonName(String receiptPersonName) {
		this.receiptPersonName = receiptPersonName;
	}
	public long getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(long receiptDate) {
		this.receiptDate = receiptDate;
	}

	
}
