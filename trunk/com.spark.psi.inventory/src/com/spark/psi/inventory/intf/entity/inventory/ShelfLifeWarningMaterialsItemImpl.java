package com.spark.psi.inventory.intf.entity.inventory;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ShelfLifeWarningType;
import com.spark.psi.publish.inventory.entity.ShelfLifeWarningMaterialsItem;

public class ShelfLifeWarningMaterialsItemImpl implements
		ShelfLifeWarningMaterialsItem {
	private GUID storeId;
	private String storeName;
	private GUID materialId;
	private String materialName;
	private String materialCode;
	private String materialNo;
	private String materialSpec;
	private String materialUnit;
	private double count;
	private ShelfLifeWarningType shelfLifeWarningType;
	private long produceDate;
	private int shelfLife;
	private int warningDay;

	public String getMaterialNo() {
		return materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public GUID getMaterialId() {
		return materialId;
	}

	public void setMaterialId(GUID materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialSpec() {
		return materialSpec;
	}

	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}

	public String getMaterialUnit() {
		return materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public ShelfLifeWarningType getShelfLifeWarningType() {
		return shelfLifeWarningType;
	}

	public void setShelfLifeWarningType(
			ShelfLifeWarningType shelfLifeWarningType) {
		this.shelfLifeWarningType = shelfLifeWarningType;
	}

	public long getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(long produceDate) {
		this.produceDate = produceDate;
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

}
