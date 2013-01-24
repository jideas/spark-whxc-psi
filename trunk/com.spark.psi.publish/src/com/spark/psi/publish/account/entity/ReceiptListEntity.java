package com.spark.psi.publish.account.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class ReceiptListEntity extends ListEntity<ReceiptItem> {

	public ReceiptListEntity(List<ReceiptItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
