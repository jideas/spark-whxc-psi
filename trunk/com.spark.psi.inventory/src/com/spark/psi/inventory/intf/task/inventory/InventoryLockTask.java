package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * 修改库存锁定数量（零售、调拨调用）
 *
 */
public class InventoryLockTask extends SimpleTask {
	private GUID storeId;
	private GUID stockId;
	
	
	/**
	 * 锁定数量
	 */
	private Double lockedCount;
	private InventoryType inventoryType = InventoryType.Materials;
	
	/**
	 * 修改指定商品在指定仓库中的库存信息：库存数量，库存金额，采购在途数量，发货需求数量
	 * @param storeId 指定的仓库
	 * @param stockId 存货Id
	 */
	public InventoryLockTask( GUID storeId, GUID stockId) {
		this.storeId = storeId;
		this.stockId = stockId;
	}
	
	public GUID getStorId() {
		return storeId;
	}
	public GUID getStockId() {
		return stockId;
	}

	public void setLockedCount(Double lockedCount) {
		this.lockedCount = lockedCount;
	}

	public Double getLockedCount() {
		return lockedCount;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}
}
