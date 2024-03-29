package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ShelfLifeWarningType;

public interface ShelfLifeWarningMaterialsItem {
	public GUID getId();
	
	public GUID getStoreId();

	public String getStoreName();

	public GUID getMaterialId();

	public String getMaterialName();
	
	public String getMaterialCode();
	
	public String getMaterialNo();

	public String getMaterialSpec();

	public String getMaterialUnit();

	public double getCount();

	public ShelfLifeWarningType getShelfLifeWarningType();

	public long getProduceDate();

	public int getShelfLife();

	public int getWarningDay();
	
	public int getShelfNo();

	public int getTiersNo();
}
