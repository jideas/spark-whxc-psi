package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * 修改交货需求
 *
 */
public class InventoryDeliveringTask extends SimpleTask {
	private GUID storeId;
	private GUID stockId;
	
	private Double toDeliverCount;
	private InventoryType inventoryType = InventoryType.Materials;
	
	public Double getToDeliverCount() {
		return toDeliverCount;
	}

	/**
	 * 修改指定商品在指定仓库中的库存信息：库存数量，库存金额，采购在途数量，发货需求数量
	 * @param tenantsGuid 指定的租户
	 * @param storeId 指定的仓库
	 * @param stockId 存货Id
	 */
	public InventoryDeliveringTask( GUID storeId, GUID stockId) {
		this.storeId = storeId;
		this.stockId = stockId;
	}
	
	public GUID getStoreId() {
		return storeId;
	}
	public GUID getStockId() {
		return stockId;
	}
	
	
	/**
	 * 设置库存改变数量和单位
	 * 累加库存数量传入正数，减少库存数量传入负数
	 * @param changeCount
	

	/**
	 * 设置发货需求数量
	 * @param toDeliverCount
	 */
	public void setToDeliverCount(Double toDeliverCount) {
		this.toDeliverCount = toDeliverCount;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}
	
}
