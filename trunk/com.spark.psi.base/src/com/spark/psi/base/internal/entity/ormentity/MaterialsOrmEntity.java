package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class MaterialsOrmEntity{
	
	private GUID id;//	行标识
	private String materialCode;//	商品编码
	private String materialName;//	商品名称
	private String namePY;
	private GUID categoryId;//	商品分类GUID
	private String remark;//	备注
	private boolean canDelete;//	是否可以删除
	private boolean refFlag;//	关联标识
	private String inventoryWarningType;//	库存预警类型
	private long createDate;//	数据插入时间
	private long lastModifyDate;//	数据最后修改时间
	private String lastModifyPerson;//	数据最后修改人
	private GUID creatorId;//	创建人Guid
	private String creator;//	创建人
	private String status;
	private int shelfLife;//	保质期
	private int warningDay;//	预警天数
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
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
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
	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}
	public String getNamePY() {
		return namePY;
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
	public boolean isCanDelete() {
		return canDelete;
	}
	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
	public boolean isRefFlag() {
		return refFlag;
	}
	public void setRefFlag(boolean refFlag) {
		this.refFlag = refFlag;
	}
	public String getInventoryWarningType() {
		return inventoryWarningType;
	}
	public void setInventoryWarningType(String inventoryWarningType) {
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
	public void setStatus(String status) {
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
	public String getStatus() {
		return status;
	}
	
	

}
