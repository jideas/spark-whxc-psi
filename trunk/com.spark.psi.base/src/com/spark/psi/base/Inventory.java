package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * <p>
 * ¿â´æ
 * </p>
 */
public interface Inventory {

	public GUID getId();
	public GUID getStoreId();
	public GUID getStockId();
	public double getInitCount();
	public double getInitAmount();
	public double getInitCost();
	public String getName();
	public String getCode();
	public String getStockNo();
	public double getCount();
	public double getAmount();
	public String getUnit();
	public String getSpec();
	public double getOnWayCount() ;
	public double getToDeliverCount();
	public double getUpperLimitCount();
	public double getLowerLimitCount();
	public double getUpperLimitAmount();
	public String getInventoryType();
	public double getLockedCount();
	public int getScale();
	public String getProperties();

}
