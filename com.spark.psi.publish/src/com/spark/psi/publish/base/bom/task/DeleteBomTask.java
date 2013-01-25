package com.spark.psi.publish.base.bom.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteBomTask extends SimpleTask {

	private GUID id;

	public GUID getId() {
		return id;
	}

	public DeleteBomTask(GUID id) {
		this.id = id;
	}
}
