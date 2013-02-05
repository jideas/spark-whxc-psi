package com.spark.psi.publish.split.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;

public class UpdateGoodsSplitStatusTask extends SimpleTask {

	private GoodsSplitStatus status;

	private String reason;

	public UpdateGoodsSplitStatusTask(GoodsSplitStatus status) {
		this.status = status;
	}

	public UpdateGoodsSplitStatusTask(GoodsSplitStatus status, String reason) {
		this.status = status;
		this.reason = reason;
	}

	public GoodsSplitStatus getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}
}
