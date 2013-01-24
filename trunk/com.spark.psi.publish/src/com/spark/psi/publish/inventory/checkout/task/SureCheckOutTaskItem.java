package com.spark.psi.publish.inventory.checkout.task;

import com.jiuqi.dna.core.type.GUID;

public class SureCheckOutTaskItem {

	private GUID id;
	private Double count;

	public SureCheckOutTaskItem(GUID id, Double count) {
		this.id = id;
		this.count = count;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

}
