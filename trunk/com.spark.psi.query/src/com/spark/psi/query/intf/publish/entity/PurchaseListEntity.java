package com.spark.psi.query.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class PurchaseListEntity extends ListEntity<PurchaseItem> {

	public PurchaseListEntity(List<PurchaseItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
