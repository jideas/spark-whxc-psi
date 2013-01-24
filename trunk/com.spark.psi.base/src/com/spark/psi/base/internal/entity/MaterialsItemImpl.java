package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Materials;
import com.spark.psi.base.MaterialsCategory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.MaterialsStatus;

@StructClass
public class MaterialsItemImpl implements MaterialsItem {

	private GUID id;//	行标识
	private String materialCode;//	商品编码
	private String materialNo;//	商品条码
	private String materialName;//	商品名称
	private String namePY;//	拼音
	private GUID categoryId;//	商品分类GUID
	private String spec;//	规格
	private int scale;//	精度
	private String inventoryStrategy;//	库存策略
	private String materialUnit;//	商品单位
	private double avgBuyPrice;//	平均采购价格
	private double totalStoreUpper;//	总库存上限数量
	private double totalStoreFlore;//	总库存下限数量
	private double totalStoreAmount;//	总库存上限金额
	private int shelfLife;//	保质期
	private int warningDay;//	预警天数
	private double salePrice;//	销售价格
	private double standardPrice;//	标准价格
	private double planPrice;//	计划价格
	private MaterialsStatus status;//	商品状态
	private String remark;//	备注
	private boolean canDelete;//	是否可以删除
	private boolean refFlag;//	关联标识
	private long createDate;//	数据插入时间
	private long lastModifyDate;//	数据最后修改时间
	private String lastModifyPerson;//	数据最后修改人
	private InventoryWarningType warningType;//	商品预警类型
	private String[] materialProperties;//	分类属性值
	private GUID creatorId;//	创建人Guid
	private String creator;//	创建人
	private GUID materialId;//	材料ID
	private double lossRate;//	损耗率
	private Materials material;
	private MaterialsCategory category;
	private double recentPurchasePrice;
	
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
	public MaterialsStatus getStatus() {
		return status;
	}
	public void setStatus(MaterialsStatus status) {
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
	public InventoryWarningType getWarningType() {
		return warningType;
	}
	public void setWarningType(InventoryWarningType warningType) {
		this.warningType = warningType;
	}
	public String[] getMaterialProperties() {
		return materialProperties;
	}
	public void setMaterialProperties(String[] materialProperties) {
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
	public double getLossRate() {
		return lossRate;
	}
	public void setLossRate(double lossRate) {
		this.lossRate = lossRate;
	}
	public Materials getMaterial() {
		return material;
	}
	public void setMaterial(Materials material) {
		this.material = material;
	}
	public MaterialsCategory getCategory() {
		return category;
	}
	public void setCategory(MaterialsCategory category) {
		this.category = category;
	}
	public void setRecentPurchasePrice(double recentPurchasePrice) {
		this.recentPurchasePrice = recentPurchasePrice;
	}
	public double getRecentPurchasePrice() {
		return recentPurchasePrice;
	}

}
