package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class MaterialsOrmEntity{
	
	private GUID id;//	�б�ʶ
	private String materialCode;//	��Ʒ����
	private String materialName;//	��Ʒ����
	private String namePY;
	private GUID categoryId;//	��Ʒ����GUID
	private String remark;//	��ע
	private boolean canDelete;//	�Ƿ����ɾ��
	private boolean refFlag;//	������ʶ
	private String inventoryWarningType;//	���Ԥ������
	private long createDate;//	���ݲ���ʱ��
	private long lastModifyDate;//	��������޸�ʱ��
	private String lastModifyPerson;//	��������޸���
	private GUID creatorId;//	������Guid
	private String creator;//	������
	private String status;
	private int shelfLife;//	������
	private int warningDay;//	Ԥ������
	private boolean isJointVenture;// 	�Ƿ���Ӫ��Ʒ
	private GUID supplierId;//	��Ӫ��Ӧ��
	private double percentage;//	���


	
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
