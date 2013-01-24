package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.checkout.entity.ProduceCheckoutInfoInfoItem;

public class ProduceCheckoutInfoInfoItemImpl implements ProduceCheckoutInfoInfoItem {

	private GUID materialId;
	private String materialName;
	private String materialNo;
	private String materialCode;
	private String materialSpec;
	private String materialUnit;
	private int scale;
	private double planCount;
	private double realCount;

	public GUID getMaterialId() {
		return materialId;
	}

	public void setMaterialId(GUID materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialNo() {
		return materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialSpec() {
		return materialSpec;
	}

	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}

	public String getMaterialUnit() {
		return materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public double getPlanCount() {
		return planCount;
	}

	public void setPlanCount(double planCount) {
		this.planCount = planCount;
	}

	public double getRealCount() {
		return realCount;
	}

	public void setRealCount(double realCount) {
		this.realCount = realCount;
	} 
}
