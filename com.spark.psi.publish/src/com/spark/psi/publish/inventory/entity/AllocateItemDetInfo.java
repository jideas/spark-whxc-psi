package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

public class AllocateItemDetInfo {
	private GUID  id;
	private InventoryAllocateItemDet[] items;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public InventoryAllocateItemDet[] getItems() {
		return items;
	}
	public void setItems(InventoryAllocateItemDet[] items) {
		this.items = items;
	}
	
	
}
