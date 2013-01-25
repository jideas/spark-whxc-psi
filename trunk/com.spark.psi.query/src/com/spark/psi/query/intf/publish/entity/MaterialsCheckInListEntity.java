package com.spark.psi.query.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class MaterialsCheckInListEntity extends ListEntity<MaterialsCheckInItem> {

	public MaterialsCheckInListEntity(List<MaterialsCheckInItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
