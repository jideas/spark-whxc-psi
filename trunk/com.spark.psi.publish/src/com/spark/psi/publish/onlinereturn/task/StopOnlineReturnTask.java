package com.spark.psi.publish.onlinereturn.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class StopOnlineReturnTask extends SimpleTask {

	public StopOnlineReturnTask(GUID id, String reason) {
		this.id = id;
		this.reason = reason;
	}

	private GUID id;
	private String reason;

	public GUID getId() {
		return id;
	}

	public String getReason() {
		return reason;
	}
}
