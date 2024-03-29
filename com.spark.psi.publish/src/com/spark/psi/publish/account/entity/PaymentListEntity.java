package com.spark.psi.publish.account.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class PaymentListEntity extends ListEntity<PaymentItem> {

	private double totalAmount;
	public PaymentListEntity(List<PaymentItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
