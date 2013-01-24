package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class DeleteReportLossItemTask extends SimpleTask {
	private GUID[] itemIds;

	
	public DeleteReportLossItemTask(GUID[] itemIds) {
		this.itemIds = itemIds;
	}
	
	public GUID[] getItemIds() {
		return itemIds;
	}
}	
