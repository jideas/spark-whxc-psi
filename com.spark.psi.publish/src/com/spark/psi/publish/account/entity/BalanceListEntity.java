package com.spark.psi.publish.account.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class BalanceListEntity extends ListEntity<BalanceItem> {

	public BalanceListEntity(List<BalanceItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
