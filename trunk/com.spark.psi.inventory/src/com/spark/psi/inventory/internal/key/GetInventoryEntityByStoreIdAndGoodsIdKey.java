package com.spark.psi.inventory.internal.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�����ݿ��ѯһ������¼</p>
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
