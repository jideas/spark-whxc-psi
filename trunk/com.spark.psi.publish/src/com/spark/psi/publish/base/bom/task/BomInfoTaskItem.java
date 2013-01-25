package com.spark.psi.publish.base.bom.task;

import com.jiuqi.dna.core.type.GUID;

public class BomInfoTaskItem {

	private GUID materialId;
	
	private double count,lossRate,realCount;

	public GUID getMaterialId() {
		return materialId;
	}

	public void setMaterialId(GUID materialId) {
		this.materialId = materialId;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public double getLossRate() {
		return lossRate;
	}

	public void setLossRate(double lossRate) {
		this.lossRate = lossRate;
	}

	public double getRealCount() {
		return realCount;
	}

	public void setRealCount(double realCount) {
		this.realCount = realCount;
	}
}
