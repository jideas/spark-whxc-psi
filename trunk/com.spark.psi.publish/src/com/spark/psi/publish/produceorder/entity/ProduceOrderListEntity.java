package com.spark.psi.publish.produceorder.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class ProduceOrderListEntity extends ListEntity<ProduceOrderItem> {

	public ProduceOrderListEntity(List<ProduceOrderItem> dataList,
			long totalCount) {
		super(dataList, totalCount);
	}

}
