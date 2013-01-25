package com.spark.deliverticket.intf.publish.entity;

import java.util.List;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketItem;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketListEntity;

public class DeliverTicketListEntityImpl extends DeliverTicketListEntity {

	public DeliverTicketListEntityImpl(List<DeliverTicketItem> dataList,
			long totalCount) {
		super(dataList, totalCount);
		// TODO Auto-generated constructor stub
	}

}
