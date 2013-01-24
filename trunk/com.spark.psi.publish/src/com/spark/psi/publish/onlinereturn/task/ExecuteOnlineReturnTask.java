package com.spark.psi.publish.onlinereturn.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class ExecuteOnlineReturnTask extends SimpleTask {
	public ExecuteOnlineReturnTask(GUID id) {
		this.id = id;
	}

	private GUID id;

	public GUID getId() {
		return id;
	}
}
