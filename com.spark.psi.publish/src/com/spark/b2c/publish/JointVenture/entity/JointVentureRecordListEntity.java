package com.spark.b2c.publish.JointVenture.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class JointVentureRecordListEntity extends ListEntity<JointVentureRecordItem> {

	public JointVentureRecordListEntity(List<JointVentureRecordItem> dataList,
			long totalCount) {
		super(dataList, totalCount);
	}

}
