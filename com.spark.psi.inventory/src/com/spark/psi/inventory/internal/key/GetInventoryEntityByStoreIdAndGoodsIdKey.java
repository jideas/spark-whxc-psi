package com.spark.psi.inventory.internal.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>从数据库查询一条库存记录</p>
 *
 */

public class GetInventoryEntityByStoreIdAndGoodsIdKey {

	private GUID storeId,stockId;

	public GUID getStoreId() {
		return storeId;
	}

	public GetInventoryEntityByStoreIdAndGoodsIdKey(GUID storeId,GUID stockId) {
		this.storeId = storeId;
		this.stockId = stockId;
	}

	public GUID getStockId() {
		return stockId;
	}

}
