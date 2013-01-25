package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 查询指定商品条目在各个仓库的库存列表
 * 
 */
public class GetInventorySummaryKey {

	/**
	 * 商品条目Id
	 */
	private GUID goodsItemId;

	/**
	 * 构造函数
	 * 
	 * @param goodsItemId
	 */
	public GetInventorySummaryKey(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	/**
	 * @return the goodsItemId
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}
}
