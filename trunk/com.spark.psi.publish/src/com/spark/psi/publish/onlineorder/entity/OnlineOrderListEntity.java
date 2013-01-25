package com.spark.psi.publish.onlineorder.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class OnlineOrderListEntity extends ListEntity<OnlineOrderItem> {

	protected double totalAmount;
	
	public double getTotalAmount() {
		return totalAmount;
	}
	public OnlineOrderListEntity(List<OnlineOrderItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
