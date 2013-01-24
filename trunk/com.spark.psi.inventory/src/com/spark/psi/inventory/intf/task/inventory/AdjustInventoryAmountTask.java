package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * 
 * <p>���¿����</p>
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
	 * @param stockId ���ID
	 * @param storeIds ���ID����
	 * @param avgInventoryCost ƽ�����ɱ�
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
