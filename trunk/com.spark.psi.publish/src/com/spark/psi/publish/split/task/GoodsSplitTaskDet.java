package com.spark.psi.publish.split.task;

import com.jiuqi.dna.core.type.GUID;

public class GoodsSplitTaskDet {
	private GUID id;
	private double count;
	private String reason;

	public GUID getId() {
		return id;
	}

	public double getCount() {
		return count;
	}

	public String getReason() {
		return reason;
	}

	public GoodsSplitTaskDet(GUID id, double count) {
		this.id = id;
		this.count = count;
	}

	public GoodsSplitTaskDet(GUID id, double count, String reason) {
		this.id = id;
		this.count = count;
		this.reason = reason;
	}
}
