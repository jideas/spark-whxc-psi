package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.internal.entity.ReportLossItemDet;

public class CreateReportLossItemDetTask extends SimpleTask {
	private ReportLossItemDet[] itemDets;

	public CreateReportLossItemDetTask(ReportLossItemDet[] itemDets) {
		this.itemDets = itemDets;
	}
	
	public ReportLossItemDet[] getItemDets() {
		return itemDets;
	}
	
}
