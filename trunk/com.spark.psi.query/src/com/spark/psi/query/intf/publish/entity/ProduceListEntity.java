package com.spark.psi.query.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class ProduceListEntity extends ListEntity<ProduceItem> {

	public ProduceListEntity(List<ProduceItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
