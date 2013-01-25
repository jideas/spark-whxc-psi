package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryWarningType;

/**
 * 创建或者更新材料分类（包括子条目）
 * 
 */
public class MaterialsInfoTask extends Task<MaterialsInfoTask.Method> {

	public enum Method {
		Create, Update
	}
	
	public enum ItemMethod{
		/**编辑*/
		Update,
		/**删除*/
		Delete
	}

	/**
	 * 材料ID
	 */
	private GUID id;

	/**
	 * 材料代码
	 */
	private String code;

	/**
	 * 材料名称
	 */
	private String name;

	/**
	 * 统一销售价格
	 */
	private double salePrice;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 所属分类ID
	 */
	private GUID categoryId;

	/**
	 * 明细条目
	 */
	private Item[] items;
	
	private int shelfLife;
	private int warningDay;
	private InventoryWarningType inventoryWarningType;
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

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the salePrice
	 */
	public double getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice
	 *            the salePrice to set
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return the memo
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the categoryId
	 */
	public GUID getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(GUID categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the items
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(Item[] items) {
		this.items = items;
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

	public void setInventoryWarningType(InventoryWarningType inventoryWarningType) {
		this.inventoryWarningType = inventoryWarningType;
	}

	public InventoryWarningType getInventoryWarningType() {
		return inventoryWarningType;
	}

	/**
	 * 条目
	 */
	public final static class Item {private ItemMethod method = ItemMethod.Update;
	
	/**
	 * 条目ID
	 */
	private GUID id;

	/**
	 * 销售价格
	 */
	private double salePrice;

	/**
	 * 条目属性值
	 */
	private String[] propertyValues;

	/**
	 * 状态
	 */
	private boolean onsale;
	
	private String materialsNo;//条码
//	private double originalPrice;//	原价
	private double lossRate;// 	损耗率
	private String materialsSpec;//规格
	
	private String inventoryStrategy;
	
	private double standardPrice;//	标准价格
	private double planPrice;//	计划价格
	private String unit;
	public String getInventoryStrategy() {
		return inventoryStrategy;
	}

	public void setInventoryStrategy(String inventoryStrategy) {
		this.inventoryStrategy = inventoryStrategy;
	}

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @return the salePrice
	 */
	public double getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice
	 *            the salePrice to set
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return the propertyValues
	 */
	public String[] getPropertyValues() {
		return propertyValues;
	}

	/**
	 * @param propertyValues
	 *            the propertyValues to set
	 */
	public void setPropertyValues(String[] propertyValues) {
		this.propertyValues = propertyValues;
	}

	/**
	 * @return the onsale
	 */
	public boolean isOnsale() {
		return onsale;
	}

	/**
	 * @param onsale
	 *            the onsale to set
	 */
	public void setOnsale(boolean onsale) {
		this.onsale = onsale;
	}

	public String getMaterialsNo() {
		return materialsNo;
	}

	public void setMaterialsNo(String materialsNo) {
		this.materialsNo = materialsNo;
	}

	public double getLossRate() {
		return lossRate;
	}

	public void setLossRate(double lossRate) {
		this.lossRate = lossRate;
	}

	public String getMaterialsSpec() {
		return materialsSpec;
	}

	public void setMaterialsSpec(String materialsSpec) {
		this.materialsSpec = materialsSpec;
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

	public ItemMethod getMethod(){
    	return method;
    }

	public void setMethod(ItemMethod method){
    	this.method = method;
    }

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}	
		
	}

}
