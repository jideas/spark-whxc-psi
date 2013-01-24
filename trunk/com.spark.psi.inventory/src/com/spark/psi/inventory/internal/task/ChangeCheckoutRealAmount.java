package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class ChangeCheckoutRealAmount extends SimpleTask {

	private GUID id;
	private double amount;
	
	public ChangeCheckoutRealAmount(GUID id,double amount){
		this.id = id;this.amount = amount;
	}

	public GUID getId() {
		return id;
	} 

	public double getAmount() {
		return amount;
	} 
}
