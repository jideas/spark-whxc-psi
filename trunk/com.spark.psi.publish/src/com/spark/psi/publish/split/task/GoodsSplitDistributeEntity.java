package com.spark.psi.publish.split.task;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

public class GoodsSplitDistributeEntity {

	private GUID storeId;

	private List<GoodsSplitTaskDet> dets;

	public GUID getStoreId() {
		return storeId;
	}

	public List<GoodsSplitTaskDet> getDets() {
		return dets;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public void setDets(List<GoodsSplitTaskDet> dets) {
		this.dets = dets;
	}
}
