package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteReportLossItemDetTask extends SimpleTask {
	private GUID[] ids;
	
	public DeleteReportLossItemDetTask(GUID... ids) {
		this.ids = ids;
	}
	
	public GUID[] getIds() {
		return this.ids;
	}
}


