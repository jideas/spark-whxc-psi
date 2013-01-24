package com.spark.psi.account.intf.entity.receipt;

import com.jiuqi.dna.core.type.GUID;


public class ReceiptLogEntity {

	private GUID id;// ��ʶ
	private GUID receiptsId;// ֪ͨ��id
	private String receiptNo;// ֪ͨ�����
	private GUID checkoutSheetId;// ��ⵥid
	private String sheetNo;// ��ⵥ��
	private GUID relevantBillId;// ��ص���Id
	private String relevantBillNo;// ��ص���
	private long checkinDate;// �������
	private double amount;// �տ���
	private double molingAmount;// Ĩ����
	private GUID receiptPersonId;// �տ���
	private String receiptPersonName;// ����
	private long receiptDate;// �տ�ʱ��
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
