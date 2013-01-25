package com.spark.b2c.publish.JointVenture.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class JointSettlementListEntity extends ListEntity<JointSettlementItem> {

	public JointSettlementListEntity(List<JointSettlementItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
