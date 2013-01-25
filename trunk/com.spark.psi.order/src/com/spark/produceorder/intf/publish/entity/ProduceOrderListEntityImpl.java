package com.spark.produceorder.intf.publish.entity;

import java.util.List;
import com.spark.psi.publish.produceorder.entity.ProduceOrderItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderListEntity;

public class ProduceOrderListEntityImpl extends ProduceOrderListEntity {

	public ProduceOrderListEntityImpl(List<ProduceOrderItem> dataList,
			long totalCount) {
		super(dataList, totalCount);
	}

}
