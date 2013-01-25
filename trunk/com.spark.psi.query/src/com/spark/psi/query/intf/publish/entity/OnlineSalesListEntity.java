package com.spark.psi.query.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class OnlineSalesListEntity extends ListEntity<OnlineSalesItem> {

	private double totalAmount;
	public OnlineSalesListEntity(List<OnlineSalesItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
