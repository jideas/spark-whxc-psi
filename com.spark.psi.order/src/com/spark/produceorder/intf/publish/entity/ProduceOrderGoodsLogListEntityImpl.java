package com.spark.produceorder.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.produceorder.entity.ProduceOrderGoodsLog;
import com.spark.psi.publish.produceorder.entity.ProduceOrderGoodsLogListEntity;

public class ProduceOrderGoodsLogListEntityImpl extends ProduceOrderGoodsLogListEntity {

	public ProduceOrderGoodsLogListEntityImpl(List<ProduceOrderGoodsLog> dataList,
			long totalCount) {
		super(dataList, totalCount);
		// TODO Auto-generated constructor stub
	}

}
