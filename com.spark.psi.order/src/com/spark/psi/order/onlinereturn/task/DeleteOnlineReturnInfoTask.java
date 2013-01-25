package com.spark.psi.order.onlinereturn.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteOnlineReturnInfoTask extends SimpleTask {
	public DeleteOnlineReturnInfoTask(GUID id) {
		this.id = id;
	}

	private GUID id;

	public GUID getId() {
		return id;
	}
}
