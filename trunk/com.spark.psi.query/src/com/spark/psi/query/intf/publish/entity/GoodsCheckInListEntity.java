package com.spark.psi.query.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class GoodsCheckInListEntity extends ListEntity<GoodsCheckInItem> {

	public GoodsCheckInListEntity(List<GoodsCheckInItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
