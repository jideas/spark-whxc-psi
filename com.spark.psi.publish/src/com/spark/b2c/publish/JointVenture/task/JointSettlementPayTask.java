package com.spark.b2c.publish.JointVenture.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * Ω·À„∏∂øÓ
 *
 */
public class JointSettlementPayTask extends SimpleTask {

	private GUID id;
	private double amount;
	private double molingAmount;
	public JointSettlementPayTask(GUID id, double amount, double molingAmount) {
		super();
		this.id = id;
		this.amount = amount;
		this.molingAmount = molingAmount;
	}
	public GUID getId() {
		return id;
	}
	public double getAmount() {
		return amount;
	}
	public double getMolingAmount() {
		return molingAmount;
	}
	
}
