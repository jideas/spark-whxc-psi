package com.spark.b2c.publish.JointVenture.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class JointVentureLogListEntity extends ListEntity<JointVentureLogItem> {

	public JointVentureLogListEntity(List<JointVentureLogItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
