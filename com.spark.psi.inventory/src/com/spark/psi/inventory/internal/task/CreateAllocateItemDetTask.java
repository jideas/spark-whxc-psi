package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.internal.entity.AllocateItemDet;

public class CreateAllocateItemDetTask extends SimpleTask {
	private AllocateItemDet[] itemDets;
	
	public CreateAllocateItemDetTask(AllocateItemDet[] itemDets) {
		this.itemDets = itemDets;
	}

	public AllocateItemDet[] getItemDets() {
		return itemDets;
	}
	
}
