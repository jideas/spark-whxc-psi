package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

public class GetInventoryItemDetsByItemIdsKey {
	
	private GUID[] itemIds;
	public GetInventoryItemDetsByItemIdsKey(GUID... itemIds) {
		this.itemIds = itemIds;
	}
	
	public GUID[] getItemIds() {
		return this.itemIds;
	}
}
