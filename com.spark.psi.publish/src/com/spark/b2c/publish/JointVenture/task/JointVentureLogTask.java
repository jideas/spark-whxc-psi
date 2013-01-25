package com.spark.b2c.publish.JointVenture.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

public class JointVentureLogTask extends Task<JointVentureLogTask.Method> {

	public enum Method
	{
		Create,
		Update;
	}
	
	private GUID supplierId;//	π©”¶…ÃId
	private GUID materialId;//	
	private String materialName;//	
	private String materialCode;//	
	private String materialNo	;//
	private String materialUnit;//
	private Double percentage	;//
	public GUID getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(GUID supplierId) {
		this.supplierId = supplierId;
	}
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
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	
}
