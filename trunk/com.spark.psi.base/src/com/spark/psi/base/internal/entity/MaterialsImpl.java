package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Materials;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.MaterialsStatus;

@StructClass
public class MaterialsImpl implements Materials{
	private GUID Id;
	private String materialCode;
	private String materialName;
	private String namePY;
	private GUID categoryId;
	private String remark;
	private boolean canDalete;
	private boolean refFlag;
	private InventoryWarningType inventoryWarningType;
	private long createDate;
	private long lastModifyDate;
	private String lastModifyPerson;
	private GUID creatorId;
	private String creator;
	private MaterialsStatus status;
	private int shelfLife;
	private int warningDay;
	private boolean isJointVenture;// 	是否联营商品
	private GUID supplierId;//	联营供应商
	private double percentage;//	提成
	
	public boolean isJointVenture() {
		return isJointVenture;
	}
	public void setJointVenture(boolean isJointVenture) {
		this.isJointVenture = isJointVenture;
	}
	public GUID getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(GUID supplierId) {
		this.supplierId = supplierId;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public GUID getId() {
		return Id;
	}
	public void setId(GUID id) {
		Id = id;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getNamePY() {
		return namePY;
	}
	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}
	public GUID getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(GUID categoryId) {
		this.categoryId = categoryId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean isCanDalete() {
		return canDalete;
	}
	public void setCanDalete(boolean canDalete) {
		this.canDalete = canDalete;
	}
	public boolean isRefFlag() {
		return refFlag;
	}
	public void setRefFlag(boolean refFlag) {
		this.refFlag = refFlag;
	}
	public InventoryWarningType getInventoryWarningType() {
		return inventoryWarningType;
	}
	public void setInventoryWarningType(InventoryWarningType inventoryWarningType) {
		this.inventoryWarningType = inventoryWarningType;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public long getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(long lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifyPerson() {
		return lastModifyPerson;
	}
	public void setLastModifyPerson(String lastModifyPerson) {
		this.lastModifyPerson = lastModifyPerson;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public MaterialsStatus getStatus() {
		return status;
	}
	public void setStatus(MaterialsStatus status) {
		this.status = status;
	}
	public int getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}
	public int getWarningDay() {
		return warningDay;
	}
	public void setWarningDay(int warningDay) {
		this.warningDay = warningDay;
	}
	
	

}
