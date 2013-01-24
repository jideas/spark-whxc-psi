package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class GoodsOrmEntity{
	
	private GUID id;//	��ʶ
	private String goodsCode;//	��Ʒ����
	private String goodsName;//	��Ʒ����
	private String namePY;//	����ƴ��
	private GUID categoryId;//	��Ʒ����GUID
	private double salePrice;//	ͳһ���ۼ۸�
	private boolean isJointVenture;// 	�Ƿ���Ӫ��Ʒ
	private GUID supplierId;//	��Ӫ��Ӧ��
	private String remark;//	��ע
	private int shelfLife;//	������
	private int warningDay;//	Ԥ������
	private boolean canDelete;//	�Ƿ����ɾ��a
	private boolean refFlag;//	�Ƿ����
	private long createDate;//	���ݲ���ʱ��
	private long lastModifyDate;//	��������޸�ʱ��
	private String lastModifyPerson;//	��������޸���
	private GUID creatorId;//	������Guid
	private String creator;//	������ 
	private String status;
	private String inventoryWarningType;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setInventoryWarningType(String inventoryWarningType) {
		this.inventoryWarningType = inventoryWarningType;
	}
	public String getInventoryWarningType() {
		return inventoryWarningType;
	}

}
