package com.spark.psi.account.browser.util;

import com.jiuqi.dna.core.type.GUID;

public class ReceiptDetailUtil {

	private GUID id;

	private GUID billsId;

	private String billsNo, relaBillsNo;

	private double checkoutAmount, receiptedAmount, molingAmount;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public GUID getBillsId() {
		return billsId;
	}

	public void setBillsId(GUID billsId) {
		this.billsId = billsId;
	}

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}

	public String getRelaBillsNo() {
		return relaBillsNo;
	}

	public void setRelaBillsNo(String relaBillsNo) {
		this.relaBillsNo = relaBillsNo;
	}

	public double getCheckoutAmount() {
		return checkoutAmount;
	}

	public void setCheckoutAmount(double checkoutAmount) {
		this.checkoutAmount = checkoutAmount;
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
