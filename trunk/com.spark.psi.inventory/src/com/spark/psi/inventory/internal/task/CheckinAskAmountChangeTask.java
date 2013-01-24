package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class CheckinAskAmountChangeTask extends SimpleTask{

	private GUID id;
	private double askAmount;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public double getAskAmount() {
		return askAmount;
	}
	public void setAskAmount(double askAmount) {
		this.askAmount = askAmount;
	}
}
