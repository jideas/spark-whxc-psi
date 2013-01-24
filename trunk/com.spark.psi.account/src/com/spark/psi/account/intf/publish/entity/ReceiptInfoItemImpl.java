package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.account.entity.ReceiptInfoItem;

public class ReceiptInfoItemImpl implements ReceiptInfoItem {

	private GUID id;// ��ʶ
	private GUID receiptsId;// ���뵥id
	private GUID checkoutSheetId;// ���ⵥid
	private String sheetNo;// ���ⵥ��
	private GUID relevantBillId;// ��ص���Id
	private String relevantBillNo;// ��ص���
	private long checkoutDate;// ��������
	private double amount;// ������
	private double receiptedAmount;// ���ս��
	private double molingAmount;// Ĩ����
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
	public long getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(long checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getReceiptedAmount() {
		return receiptedAmount;
	}
	public void setReceiptedAmount(double receiptedAmount) {
		this.receiptedAmount = receiptedAmount;
	}
	public double getMolingAmount() {
		return molingAmount;
	}
	public void setMolingAmount(double molingAmount) {
		this.molingAmount = molingAmount;
	}

	
}
