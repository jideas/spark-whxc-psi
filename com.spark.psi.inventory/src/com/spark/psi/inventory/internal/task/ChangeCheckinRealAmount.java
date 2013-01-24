package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class ChangeCheckinRealAmount extends SimpleTask {
	private GUID id;
	private double amount;

	public ChangeCheckinRealAmount(GUID id, double amount) {
		this.id = id;
		this.amount = amount;
	}

	public GUID getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}
}
