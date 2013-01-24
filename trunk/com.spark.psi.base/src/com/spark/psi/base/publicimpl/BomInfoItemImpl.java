package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.bom.entity.BomInfoItem;

public class BomInfoItemImpl implements BomInfoItem {

	private double count;
	private double lossRate;
	private String materialCode;
	private GUID materialId;
	private String materialName;
	private String materialNo;
	private String materialSpec;
	private String materialUnit;
	private double realCount;

	public void setCount(double count) {
		this.count = count;
	}

	public void setLossRate(double lossRate) {
		this.lossRate = lossRate;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public void setMaterialId(GUID materialId) {
		this.materialId = materialId;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	public void setRealCount(double realCount) {
		this.realCount = realCount;
	}

	public double getCount() {
		return count;
	}

	public double getLossRate() {
		return lossRate;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public GUID getMaterialId() {
		return materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public String getMaterialNo() {
		return materialNo;
	}

	public String getMaterialSpec() {
		return materialSpec;
	}

	public String getMaterialUnit() {

		return materialUnit;
	}

	public double getRealCount() {
		return realCount;
	}

}
