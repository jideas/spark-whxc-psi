package com.spark.psi.query.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class SalesListEntity extends ListEntity<SalesItem> {

	public SalesListEntity(List<SalesItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
