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

	private GUID id;//	�б�ʶ
	private String materialCode;//	��Ʒ����
	private String materialNo;//	��Ʒ����
	private String materialName;//	��Ʒ����
	private String namePY;//	ƴ��
	private GUID categoryId;//	��Ʒ����GUID
	private String spec;//	���
	private int scale;//	����
	private String inventoryStrategy;//	������
	private String materialUnit;//	��Ʒ��λ
	private double avgBuyPrice;//	ƽ���ɹ��۸�
	private double totalStoreUpper;//	�ܿ����������
	private double totalStoreFlore;//	�ܿ����������
	private double totalStoreAmount;//	�ܿ�����޽��
	private int shelfLife;//	������
	private int warningDay;//	Ԥ������
	private double salePrice;//	���ۼ۸�
	private double standardPrice;//	��׼�۸�
	private double planPrice;//	�ƻ��۸�
	private MaterialsStatus status;//	��Ʒ״̬
	private String remark;//	��ע
	private boolean canDelete;//	�Ƿ����ɾ��
	private boolean refFlag;//	������ʶ
	private long createDate;//	���ݲ���ʱ��
	private long lastModifyDate;//	��������޸�ʱ��
	private String lastModifyPerson;//	��������޸���
	private InventoryWarningType warningType;//	��ƷԤ������
	private String[] materialProperties;//	��������ֵ
	private GUID creatorId;//	������Guid
	private String creator;//	������
	private GUID materialId;//	����ID
	private double lossRate;//	�����
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