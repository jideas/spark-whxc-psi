/**
 * 
 */
package com.spark.psi.inventory.intf.key.pub;

import com.jiuqi.dna.core.type.GUID;

/**
 * 查询指定商品条目在各个仓库的库存列表
 * @author 97
 *
 */
public class GetGoodsInventoryKey {
	/**
	 * 商品条目Id
	 */
	private GUID goodsItemId;

	/**
	 * 构造函数
	 * 
	 * @param goodsItemId
	 */
	public GetGoodsInventoryKey(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	/**
	 * @return the goodsItemId
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}
}
