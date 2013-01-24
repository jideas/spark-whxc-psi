package com.spark.psi.publish.inventory.checkout.task;

import com.jiuqi.dna.core.type.GUID;

public class MaterialCheckOutTaskItem {

	private GUID mateiralId;
	
	private double planCount;

	public GUID getMateiralId() {
		return mateiralId;
	}

	public void setMateiralId(GUID mateiralId) {
		this.mateiralId = mateiralId;
	}

	public double getPlanCount() {
		return planCount;
	}

	public void setPlanCount(double planCount) {
		this.planCount = planCount;
	}
	
	
}
