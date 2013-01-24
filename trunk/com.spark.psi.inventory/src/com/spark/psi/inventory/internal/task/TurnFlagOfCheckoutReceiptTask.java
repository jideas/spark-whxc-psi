package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class TurnFlagOfCheckoutReceiptTask extends SimpleTask {

	private GUID id;

	public GUID getId() {
		return id;
	}

	public TurnFlagOfCheckoutReceiptTask(GUID id) {
		this.id = id;
	}

}
