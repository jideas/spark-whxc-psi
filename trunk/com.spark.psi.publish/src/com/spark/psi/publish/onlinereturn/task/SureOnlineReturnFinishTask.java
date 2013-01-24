package com.spark.psi.publish.onlinereturn.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class SureOnlineReturnFinishTask extends SimpleTask {
	public SureOnlineReturnFinishTask(GUID id) {
		this.id = id;
	}

	private GUID id;

	public GUID getId() {
		return id;
	}
}
