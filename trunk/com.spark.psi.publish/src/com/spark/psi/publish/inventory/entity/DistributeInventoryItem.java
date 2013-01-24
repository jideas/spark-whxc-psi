package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

public class DistributeInventoryItem {
	private GUID stockId;
	private String name;
	private double count;
	private int scale;
	private DistributeInventoryItemDet[] inventoryDetItems;

	public final void setStockId(GUID stockId) {
		this.stockId = stockId;
	}
	/**
	 * @return the goodsName
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @param goodsName
	 *            the goodsName to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	
	public final void setCount(double count) {
		this.count = count;
	}
	public final GUID getStockId() {
		return stockId;
	}
	public final double getCount() {
		return count;
	}
	public final DistributeInventoryItemDet[] getInventoryDetItems() {
		return inventoryDetItems;
	}
	public final void setInventoryDetItems(DistributeInventoryItemDet[] inventoryDetItems) {
		this.inventoryDetItems = inventoryDetItems;
	}
	public int getScale() {
		return 2;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	
}
