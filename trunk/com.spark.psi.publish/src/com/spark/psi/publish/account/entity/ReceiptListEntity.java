package com.spark.psi.publish.account.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class ReceiptListEntity extends ListEntity<ReceiptItem> {

	private double totalAmount;
	public ReceiptListEntity(List<ReceiptItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
