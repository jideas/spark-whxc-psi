package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.account.entity.ReceiptingOrPayingItem;

public class ReceiptingOrPayingItemImpl implements ReceiptingOrPayingItem {

	private GUID sheetId;
	private String sheetNo;
	private double amount;
	private double askedAmount;
	private GUID relaBillsId;
	private String relaBillsNo;
	private long checkInOrOutDate;

	public GUID getSheetId() {
		return sheetId;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAskedAmount() {
		return askedAmount;
	}

	public void setAskedAmount(double askedAmount) {
		this.askedAmount = askedAmount;
	}

	public GUID getRelaBillsId() {
		return relaBillsId;
	}

	public void setRelaBillsId(GUID relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	public String getRelaBillsNo() {
		return relaBillsNo;
	}

	public void setRelaBillsNo(String relaBillsNo) {
		this.relaBillsNo = relaBillsNo;
	}

	public long getCheckInOrOutDate() {
		return checkInOrOutDate;
	}

	public void setCheckInOrOutDate(long checkInOrOutDate) {
		this.checkInOrOutDate = checkInOrOutDate;
	}

}
