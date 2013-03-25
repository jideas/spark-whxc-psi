package com.spark.psi.publish.onlinereturn.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class OnlineReturnListEntity extends ListEntity<OnlineReturnItem> {

	private double totalAmount;
	public OnlineReturnListEntity(List<OnlineReturnItem> dataList,
			long totalCount) {
		super(dataList, totalCount);
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	
}
