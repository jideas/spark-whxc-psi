package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.MaterialsStatus;
//import com.spark.psi.publish.InventoryWarningType;

public interface MaterialsItem {

	public GUID getId();
	public String getMaterialCode() ;
	public String getMaterialNo();
	public String getMaterialName() ;
	public String getNamePY();
	public GUID getCategoryId();
	public String getSpec();
	public int getScale();
	public String getInventoryStrategy();
	public String getMaterialUnit();
	public double getAvgBuyPrice();
	public double getTotalStoreUpper();
	public double getTotalStoreFlore();
	public double getTotalStoreAmount() ;
	public int getShelfLife();
	public int getWarningDay() ;
	public double getSalePrice();
	public double getStandardPrice();
	public double getPlanPrice() ;
	public MaterialsStatus getStatus();
	public String getRemark();
	public boolean isCanDelete();
	public boolean isRefFlag();
	public long getCreateDate();
	public long getLastModifyDate() ;
	public String getLastModifyPerson();
	public InventoryWarningType getWarningType();
	public String[] getMaterialProperties();
	public GUID getCreatorId();
	public String getCreator();
	public GUID getMaterialId();
	public double getLossRate();
	public Materials getMaterial();
	public MaterialsCategory getCategory();
	public double getRecentPurchasePrice();
}
