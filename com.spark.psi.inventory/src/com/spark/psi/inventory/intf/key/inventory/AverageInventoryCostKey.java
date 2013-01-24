package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * <p>从库存计算商品平均库存成本</p>
 *
 */

public class AverageInventoryCostKey {

	private GUID goodsItemId;
	private InventoryType inventoryType;

	public AverageInventoryCostKey(GUID goodsItemId,InventoryType inventoryType) {
		this.goodsItemId = goodsItemId;
		this.inventoryType = inventoryType;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}
	
}
