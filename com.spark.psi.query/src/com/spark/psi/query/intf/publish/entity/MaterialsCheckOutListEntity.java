package com.spark.psi.query.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class MaterialsCheckOutListEntity extends ListEntity<MaterialsCheckOutItem> {

	public MaterialsCheckOutListEntity(List<MaterialsCheckOutItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
