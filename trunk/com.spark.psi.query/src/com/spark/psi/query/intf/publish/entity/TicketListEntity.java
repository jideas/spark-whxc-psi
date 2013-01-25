package com.spark.psi.query.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class TicketListEntity extends ListEntity<TicketItem> {

	public TicketListEntity(List<TicketItem> dataList, long totalCount) {
		super(dataList, totalCount);
	}

}
