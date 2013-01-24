package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * 
 * <p>更新库存金额</p>
 *
 */
public class AdjustInventoryAmountTask extends SimpleTask {

	private GUID stockId;
	private GUID[] storeIds;
	private double avgInventoryCost;
	private InventoryType inventoryType = InventoryType.Materials;
	
	public GUID getStockId() {
		return stockId;
	}

	public GUID[] getStoreIds() {
		return storeIds;
	}

	public double getAvgInventoryCost() {
		return avgInventoryCost;
	}

	/**
	 * 
	 * @param stockId 存货ID
	 * @param storeIds 库存ID数组
	 * @param avgInventoryCost 平均库存成本
	 */
	public AdjustInventoryAmountTask(GUID stockId, GUID[] storeIds,
			double avgInventoryCost) {
		super();
		this.stockId = stockId;
		this.storeIds = storeIds;
		this.avgInventoryCost = avgInventoryCost;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}
	
	
}
