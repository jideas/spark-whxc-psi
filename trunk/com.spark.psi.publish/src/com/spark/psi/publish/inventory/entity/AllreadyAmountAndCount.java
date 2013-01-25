package com.spark.psi.publish.inventory.entity;

public class AllreadyAmountAndCount {

	private double billAmount, billCount, allreadyAmount, allreadyCount,PaidOrReceiptedAmount;

	public double getPaidOrReceiptedAmount() {
		return PaidOrReceiptedAmount;
	}

	public void setPaidOrReceiptedAmount(double paidOrReceiptedAmount) {
		PaidOrReceiptedAmount = paidOrReceiptedAmount;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public double getBillCount() {
		return billCount;
	}

	public void setBillCount(double billCount) {
		this.billCount = billCount;
	}

	public double getAllreadyAmount() {
		return allreadyAmount;
	}

	public void setAllreadyAmount(double allreadyAmount) {
		this.allreadyAmount = allreadyAmount;
	}

	public double getAllreadyCount() {
		return allreadyCount;
	}

	public void setAllreadyCount(double allreadyCount) {
		this.allreadyCount = allreadyCount;
	}
}
