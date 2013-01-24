package com.spark.psi.inventory.intf.publish.checkout.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.inventory.checkout.entity.CheckoutSheetItem;

public class CheckoutSheetItemImpl implements CheckoutSheetItem {

	private GUID id;

	private String billsNo;

	private String relaBillsNo;

	private GUID relaBillsId;

	private long checkoutDate;
	
	private String storeName;

	private CheckingOutType sheetType;

	private String checkoutPersonName;

	private double amount;

	public GUID getId() {
		return id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setId(GUID id) {
		this.id = id;
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

	public GUID getRelaBillsId() {
		return relaBillsId;
	}

	public void setRelaBillsId(GUID relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	public CheckingOutType getSheetType() {
		return sheetType;
	}

	public void setSheetType(CheckingOutType sheetType) {
		this.sheetType = sheetType;
	}

	public String getCheckoutPersonName() {
		return checkoutPersonName;
	}

	public void setCheckoutPersonName(String checkoutPersonName) {
		this.checkoutPersonName = checkoutPersonName;
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

}
