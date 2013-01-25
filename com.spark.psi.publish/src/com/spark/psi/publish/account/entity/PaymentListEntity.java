package com.spark.psi.publish.account.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class PaymentListEntity extends ListEntity<PaymentItem> {

	public PaymentListEntity(List<PaymentItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
