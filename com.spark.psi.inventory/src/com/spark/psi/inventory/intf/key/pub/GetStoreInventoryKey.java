/**
 * 
 */
package com.spark.psi.inventory.intf.key.pub;

import com.jiuqi.dna.core.type.GUID;

/**
 * 查询指定仓库的库存列表
 * @author 97
 *
 */
public class GetStoreInventoryKey {
	/**
	 * 仓库Id
	 */
	private GUID storeId;

	/**
	 * 构造函数
	 * 
	 * @param goodsItemId
	 */
	public GetStoreInventoryKey(GUID storeId) {
		this.storeId = storeId;
	}
	
	public GUID getStoreId() {
		return storeId;
	}
	
}
