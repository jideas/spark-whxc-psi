package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>获取仓库初始化库存列表</p>
 *
 */

public class GetInitedInventoryEntityKey {

	private GUID storeId;

	public GetInitedInventoryEntityKey(GUID storeId)
	{
		this.storeId = storeId;
	}
	public GUID getStoreId() {
		return storeId;
	}
}
