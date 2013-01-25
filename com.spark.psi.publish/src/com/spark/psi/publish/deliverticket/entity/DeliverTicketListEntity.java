package com.spark.psi.publish.deliverticket.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class DeliverTicketListEntity extends ListEntity<DeliverTicketItem> {

	public DeliverTicketListEntity(List<DeliverTicketItem> dataList,
			long totalCount) {
		super(dataList, totalCount);
	}

}
