package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemData;
import com.spark.psi.publish.constant.GoodsScale;

/**
 * 材料明细条目数据对象<br>
 * 查询方法：无
 */
public class MaterialsItemDataImpl implements MaterialsItemData{

	/**
	 * 条目ID
	 */
	protected GUID id;

	/**
	 * 材料条码
	 */
	protected String materialNo;
	
	/**
	 * 销售价格
	 */
	protected double salePrice;
	
	/**
	 * 计划价格
	 */
	protected double planPrice;
	
	/**
	 * 标准价格
	 */
	protected double standardPrice;

	/**
	 * 最近采购单价
	 */
	protected double recentPurchasePrice;

	/**
	 * 平均库存成本
	 */
	protected double avgBuyPrice;

	/**
	 * 条目属性值
	 */
	protected String[] propertyValues;

	/**
	 * 条目属性值
	 */
	protected String propertiesWithoutUnit;

	/**
	 * 条目属性值
	 */
	protected String propertiesWithUnit;

	/**
	 * 总库存金额上限
	 */
	protected double inventoryUpperLimit;
	/**
	 * 总库存上限数量
	 */
	protected double totalStoreUpperLimit;
	/**
	 * 总库存下限数量
	 */
	protected double totalStoreLowerLimit;

	/**
	 * 状态
	 */
	protected MaterialsStatus status;

	/**
	 * 关联标识
	 */
	protected boolean refFlag;
	
	/**
	 * 数量小数位
	 */
	protected int countDecimal;
	
	private double originalPrice;
	private String materialSpec;
	private double lossRate;
	
	/**
	 * 库存策略
	 */
	private String inventoryStrategy;
	
	
	public String getInventoryStrategy() {
		return inventoryStrategy;
	}

	public void setInventoryStrategy(String inventoryStrategy) {
		this.inventoryStrategy = inventoryStrategy;
	}

	public int getCountDecimal() {
		return countDecimal;
	}

	public String getUnit(){
		return this.getPropertyValues()[0];
	}
	
	/**
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * 
	 * @return
	 */

	public double getSalePrice() {
		return salePrice;
	}

	/**
	 * 
	 * @return
	 */
	public double getAvgBuyPrice() {
		return avgBuyPrice;
	}

	/**
	 * 
	 * @return
	 */
	public String[] getPropertyValues() {
		return propertyValues;
	}

	/**
	 * 
	 * @return
	 */
	public String getPropertiesWithoutUnit() {
		return propertiesWithoutUnit;
	}

	/**
	 * 
	 * @return
	 */
	public String getPropertiesWithUnit() {
		return propertiesWithUnit;
	}

	/**
	 * 
	 * @return
	 */
	public double getInventoryUpperLimit() {
		return inventoryUpperLimit;
	}

	/**
	 * 
	 * @return
	 */
	public MaterialsStatus getStatus() {
		return status;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRefFlag() {
		return refFlag;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	

	public String getMaterialSpec() {
		return materialSpec;
	}

	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}

	public double getLossRate() {
		return lossRate;
	}

	public void setLossRate(double lossRate) {
		this.lossRate = lossRate;
	}

	public void setId(GUID id){
    	this.id = id;
    }

	public void setSalePrice(double salePrice){
    	this.salePrice = salePrice;
    }

	public void setRecentPurchasePrice(double recentPurchasePrice){
    	this.recentPurchasePrice = recentPurchasePrice;
    }
	
	public void setAvgBuyPrice(double avgBuyPrice){
    	this.avgBuyPrice = avgBuyPrice;
    }

	public void setPropertyValues(String[] propertyValues){
    	this.propertyValues = propertyValues;
    }

	public void setPropertiesWithoutUnit(String propertiesWithoutUnit){
    	this.propertiesWithoutUnit = propertiesWithoutUnit;
    }

	public void setPropertiesWithUnit(String propertiesWithUnit){
    	this.propertiesWithUnit = propertiesWithUnit;
    }

	public void setInventoryUpperLimit(double inventoryUpperLimit){
    	this.inventoryUpperLimit = inventoryUpperLimit;
    }

	public void setStatus(MaterialsStatus status){
    	this.status = status;
    }

	public void setRefFlag(boolean refFlag){
    	this.refFlag = refFlag;
    }

	public int getScale(){
    	return GoodsScale.PSI_SCALE;
    }

	public void setCountDecimal(int countDecimal){
    	this.countDecimal = countDecimal;
    }

	public double getTotalStoreUpperLimit(){
    	return totalStoreUpperLimit;
    }

	public void setTotalStoreUpperLimit(double totalStoreUpperLimit){
    	this.totalStoreUpperLimit = totalStoreUpperLimit;
    }

	public double getTotalStoreLowerLimit(){
    	return totalStoreLowerLimit;
    }

	public void setTotalStoreLowerLimit(double totalStoreLowerLimit){
    	this.totalStoreLowerLimit = totalStoreLowerLimit;
    }

	public String getMaterialNo() {
		return materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	public double getPlanPrice() {
		return planPrice;
	}

	public void setPlanPrice(double planPrice) {
		this.planPrice = planPrice;
	}

	public double getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}

	public double getRecentPurchasePrice() {
		return this.recentPurchasePrice;
	}

}
