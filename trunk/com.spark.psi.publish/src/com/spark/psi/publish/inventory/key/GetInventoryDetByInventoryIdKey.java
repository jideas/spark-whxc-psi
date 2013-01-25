package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * ¸ù¾Ý¿â´æID²éÑ¯¿â´æÃ÷Ï¸
 *
 */
public class GetInventoryDetByInventoryIdKey {

	private GUID inventoryId;

	public GUID getInventoryId() {
		return inventoryId;
	}

	/**
	 * 
	 * @param inventoryId ¿â´æID
	 */
	public GetInventoryDetByInventoryIdKey(GUID inventoryId) {
		super();
		this.inventoryId = inventoryId;
	}
	
}
