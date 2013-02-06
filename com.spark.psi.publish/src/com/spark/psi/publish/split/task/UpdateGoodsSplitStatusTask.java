package com.spark.psi.publish.split.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;

public class UpdateGoodsSplitStatusTask extends SimpleTask {

	private GoodsSplitStatus status;
	
	public GUID id;

	private String reason;

	public UpdateGoodsSplitStatusTask(GUID id,GoodsSplitStatus status) {
		this.status = status;
	}

	public UpdateGoodsSplitStatusTask(GUID id,GoodsSplitStatus status, String reason) {
		this.status = status;
		this.reason = reason;
	}

	public GoodsSplitStatus getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

	public GUID getId() {
		return id;
	}
}
