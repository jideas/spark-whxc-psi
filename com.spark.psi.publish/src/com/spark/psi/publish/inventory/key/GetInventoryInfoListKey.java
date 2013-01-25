package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 查询指定商品条目列表在指定仓库的库存详细情况
 * 
 */
public class GetInventoryInfoListKey {

	/**
	 * 商品条目Id数组
	 */
	private GUID[] goodsItemIds;

	/**
	 * 仓库ID数组，为空查询在所有仓库
	 */
	private GUID[] storeIds;

	/**
	 * 构造函数
	 * 
	 * @param goodsItemIds 商品条目Id数组 ，不能为空
	 * @param storeId 仓库ID数组，为空查询在所有仓库
	 */
	public GetInventoryInfoListKey(GUID[] goodsItemIds, GUID[] storeIds) {
		super();
		this.goodsItemIds = goodsItemIds;
		this.storeIds = storeIds;
	}

	/**
	 * @return the goodsItemIds
	 */
	public GUID[] getGoodsItemIds() {
		return goodsItemIds;
	}

	/**
	 * @return the storeIds
	 */
	public GUID[] getStoreIds() {
		return storeIds;
	}
}
