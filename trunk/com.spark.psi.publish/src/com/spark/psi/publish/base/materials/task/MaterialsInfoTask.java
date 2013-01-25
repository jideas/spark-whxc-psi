package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryWarningType;

/**
 * �������߸��²��Ϸ��ࣨ��������Ŀ��
 * 
 */
public class MaterialsInfoTask extends Task<MaterialsInfoTask.Method> {

	public enum Method {
		Create, Update
	}
	
	public enum ItemMethod{
		/**�༭*/
		Update,
		/**ɾ��*/
		Delete
	}

	/**
	 * ����ID
	 */
	private GUID id;

	/**
	 * ���ϴ���
	 */
	private String code;

	/**
	 * ��������
	 */
	private String name;

	/**
	 * ͳһ���ۼ۸�
	 */
	private double salePrice;

	/**
	 * ��ע
	 */
	private String remark;

	/**
	 * ��������ID
	 */
	private GUID categoryId;

	/**
	 * ��ϸ��Ŀ
	 */
	private Item[] items;
	
	private int shelfLife;
	private int warningDay;
	private InventoryWarningType inventoryWarningType;
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
	 * ��Ŀ
	 */
	public final static class Item {private ItemMethod method = ItemMethod.Update;
	
	/**
	 * ��ĿID
	 */
	private GUID id;

	/**
	 * ���ۼ۸�
	 */
	private double salePrice;

	/**
	 * ��Ŀ����ֵ
	 */
	private String[] propertyValues;

	/**
	 * ״̬
	 */
	private boolean onsale;
	
	private String materialsNo;//����
//	private double originalPrice;//	ԭ��
	private double lossRate;// 	�����
	private String materialsSpec;//���
	
	private String inventoryStrategy;
	
	private double standardPrice;//	��׼�۸�
	private double planPrice;//	�ƻ��۸�
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
