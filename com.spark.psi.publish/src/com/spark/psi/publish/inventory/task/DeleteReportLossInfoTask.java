package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteReportLossInfoTask extends SimpleTask {
	private GUID id;

	public DeleteReportLossInfoTask(GUID id) {
		this.id = id;
	}
	
	public GUID getId() {
		return id;
	}
}
