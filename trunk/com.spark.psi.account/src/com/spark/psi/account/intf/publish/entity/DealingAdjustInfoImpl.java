package com.spark.psi.account.intf.publish.entity;

import com.spark.psi.publish.account.entity.DealingAdjustInfo;

public class DealingAdjustInfoImpl implements DealingAdjustInfo {
	private String sheetNumber;
	private long adjustDate;
	private double adjustAmount;
	private String adjustReason;
	public String getSheetNumber() {
		return sheetNumber;
	}
	public void setSheetNumber(String sheetNumber) {
		this.sheetNumber = sheetNumber;
	}
	public long getAdjustDate() {
		return adjustDate;
	}
	public void setAdjustDate(long adjustDate) {
		this.adjustDate = adjustDate;
	}
	public double getAdjustAmount() {
		return adjustAmount;
	}
	public void setAdjustAmount(double adjustAmount) {
		this.adjustAmount = adjustAmount;
	}
	public String getAdjustReason() {
		return adjustReason;
	}
	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}
	
	
}
