package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;
/**
 * 
 * 根据存货ID查询库存明细
 *
 */
public class GetInventoryDetByStockIdKey {

	private GUID stockId;
	private InventoryType inventoryType;

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public GUID getStockId() {
		return stockId;
	}

	/**
	 * 
	 * @param stockId 存货ID
	 * @param inventoryType 存货类型
	 */
	public GetInventoryDetByStockIdKey(GUID stockId,InventoryType inventoryType) {
		super();
		this.stockId = stockId;
		this.inventoryType = inventoryType;
	}
	
}
