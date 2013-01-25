package com.spark.psi.publish.onlinereturn.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteOnlineReturnTask extends SimpleTask {
	public DeleteOnlineReturnTask(GUID id) {
		this.id = id;
	}

	private GUID id;

	public GUID getId() {
		return id;
	}
}
