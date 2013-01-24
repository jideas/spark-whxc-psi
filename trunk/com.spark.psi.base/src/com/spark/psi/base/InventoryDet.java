package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;

public interface InventoryDet {

	public GUID getId();
	public GUID getShelfId();
	public int getShelfNo();
	public int getTiersNo();
	public GUID getStockId();
	public double getCount();
	public long getProduceDate();
	public GUID getInventoryId();
	public GUID getStoreId();
}
