package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;
/**
 * 
 * 根据仓库ID+存货ID查询库存明细
 *
 */
public class GetInventoryDetByStoreAndStockIdKey {

	private GUID stockId;
	private GUID storeId;
	public GUID getStoreId() {
		return storeId;
	}

	private InventoryType inventoryType;

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public GUID getStockId() {
		return stockId;
	}

	/**
	 * @param storeId 仓库ID
	 * @param stockId 存货ID
	 * @param inventoryType 存货类型
	 */
	public GetInventoryDetByStoreAndStockIdKey(GUID storeId,GUID stockId,InventoryType inventoryType) {
		super();
		this.storeId = storeId;
		this.stockId = stockId;
		this.inventoryType = inventoryType;
	}
	
}
