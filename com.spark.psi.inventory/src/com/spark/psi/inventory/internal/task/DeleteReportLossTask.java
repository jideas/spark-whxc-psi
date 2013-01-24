package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteReportLossTask extends SimpleTask {
	private GUID id;

	public DeleteReportLossTask(GUID id) {
		this.id = id;
	}
	
	public GUID getId() {
		return id;
	}
}
