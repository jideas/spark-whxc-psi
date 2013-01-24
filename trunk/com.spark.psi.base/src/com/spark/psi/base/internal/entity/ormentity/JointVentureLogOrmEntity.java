package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class JointVentureLogOrmEntity {

	private GUID id;
	private GUID supplierId;//	π©”¶…ÃId
	private GUID materialId;//	
	private String materialName;//	
	private String materialCode;//	
	private String materialNo	;//
	private String materialUnit;//	
	private long beginDate	;//
	private long endDate	;//
	private double percentage	;//
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
	public long getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(long beginDate) {
		this.beginDate = beginDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getId() {
		return id;
	}

	
}
