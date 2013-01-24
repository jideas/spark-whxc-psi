package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class GoodsOrmEntity{
	
	private GUID id;//	标识
	private String goodsCode;//	商品编码
	private String goodsName;//	商品名称
	private String namePY;//	名称拼音
	private GUID categoryId;//	商品分类GUID
	private double salePrice;//	统一销售价格
	private boolean isJointVenture;// 	是否联营商品
	private GUID supplierId;//	联营供应商
	private String remark;//	备注
	private int shelfLife;//	保质期
	private int warningDay;//	预警天数
	private boolean canDelete;//	是否可以删除a
	private boolean refFlag;//	是否关联
	private long createDate;//	数据插入时间
	private long lastModifyDate;//	数据最后修改时间
	private String lastModifyPerson;//	数据最后修改人
	private GUID creatorId;//	创建人Guid
	private String creator;//	创建人 
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
