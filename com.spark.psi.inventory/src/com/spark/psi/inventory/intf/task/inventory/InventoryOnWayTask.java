/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-11       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * 修改库存在途数量
 *
 */
public class InventoryOnWayTask extends SimpleTask {
	private GUID storeId;
	private GUID stockId;
	
	private Double onWayCount;
	private InventoryType inventoryType = InventoryType.Materials;
	
	public Double getOnWayCount() {
		return onWayCount;
	}

	/**
	 * 修改指定商品在指定仓库中的库存信息：库存数量，库存金额，采购在途数量，发货需求数量
	 * @param storeId 指定的仓库
	 * @param stockId 存货Id
	 * 
	 */
	public InventoryOnWayTask( GUID storeId, GUID stockId) {
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
	 * 设置采购在途数量
	 * @param onWayCount
	 */
	public void setOnWayCount(Double onWayCount) {
		this.onWayCount = onWayCount;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}
	
}
