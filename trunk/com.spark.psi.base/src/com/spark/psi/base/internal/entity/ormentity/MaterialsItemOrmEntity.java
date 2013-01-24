package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class MaterialsItemOrmEntity{
	private GUID id;//	行标识
	private String materialCode;//	编码
	private String materialNo;//	条码
	private String materialName;//	名称
	private String namePY;//	拼音
	private GUID categoryId;//	分类GUID
	private String spec;//	规格
	private int scale;//	精度
	private String inventoryStrategy;//	库存策略
	private String materialUnit;//	单位
	private double avgBuyPrice;//	平均采购价格
	private double totalStoreUpper;//	总库存上限数量
	private double totalStoreFlore;//	总库存下限数量
	private double totalStoreAmount;//	总库存上限金额
	private int shelfLife;//	保质期
	private int warningDay;//	预警天数
	private double salePrice;//	销售价格
	private double standardPrice;//	标准价格
	private double planPrice;//	计划价格
	private String status;//	商品状态
	private String remark;//	备注
	private boolean canDelete;//	是否可以删除
	private boolean refFlag;//	关联标识
	private long createDate;//	数据插入时间
	private long lastModifyDate;//	数据最后修改时间
	private String lastModifyPerson;//	数据最后修改人
	private String warningType;//商品预警类型
	private String materialProperties;//	分类属性值
	private GUID creatorId;//	创建人Guid
	private String creator;//	创建人
	private GUID materialId;
	private double lossRate;
	private double recentPurchasePrice;//	最近采购价格	

	
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
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
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
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public String getInventoryStrategy() {
		return inventoryStrategy;
	}
	public void setInventoryStrategy(String inventoryStrategy) {
		this.inventoryStrategy = inventoryStrategy;
	}
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	public double getAvgBuyPrice() {
		return avgBuyPrice;
	}
	public void setAvgBuyPrice(double avgBuyPrice) {
		this.avgBuyPrice = avgBuyPrice;
	}
	public double getTotalStoreUpper() {
		return totalStoreUpper;
	}
	public void setTotalStoreUpper(double totalStoreUpper) {
		this.totalStoreUpper = totalStoreUpper;
	}
	public double getTotalStoreFlore() {
		return totalStoreFlore;
	}
	public void setTotalStoreFlore(double totalStoreFlore) {
		this.totalStoreFlore = totalStoreFlore;
	}
	public double getTotalStoreAmount() {
		return totalStoreAmount;
	}
	public void setTotalStoreAmount(double totalStoreAmount) {
		this.totalStoreAmount = totalStoreAmount;
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
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public double getStandardPrice() {
		return standardPrice;
	}
	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}
	public double getPlanPrice() {
		return planPrice;
	}
	public void setPlanPrice(double planPrice) {
		this.planPrice = planPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	public String getMaterialProperties() {
		return materialProperties;
	}
	public void setMaterialProperties(String materialProperties) {
		this.materialProperties = materialProperties;
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
	public GUID getMaterialId() {
		return materialId;
	}
	public void setMaterialId(GUID materialId) {
		this.materialId = materialId;
	}
	public void setLossRate(double lossRate) {
		this.lossRate = lossRate;
	}
	public double getLossRate() {
		return lossRate;
	}
	public void setRecentPurchasePrice(double recentPurchasePrice) {
		this.recentPurchasePrice = recentPurchasePrice;
	}
	public double getRecentPurchasePrice() {
		return recentPurchasePrice;
	}


}
