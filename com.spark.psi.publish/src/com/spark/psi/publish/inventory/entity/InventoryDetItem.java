package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * �����ϸ<BR>
 * ��ѯ������<BR>
 * GetInventoryDetByStockIdKey+List<InventoryDetItem>
 *
 */
public interface InventoryDetItem {

	public GUID getId();
	public GUID getShelfId();
	public int getShelfNo();
	public int getTiersNo();
	public GUID getStockId();
	public double getCount();
	public long getProduceDate();
	public GUID getInventoryId();
	public GUID getStoreId();
	public String getStoreName();
	public String getName();
	public String getCode();
	public String getStockNo();
	public String getSpec();
	public String getUnit();
	public String[] getProperties();
}
