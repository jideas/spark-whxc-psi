package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>查询商品可用库存</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-4-19
 */
public class GetAvailableCountKey {

	private GUID storeId,goodsItemId;

	public GUID getStoreId() {
		return storeId;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	/**
	 * 
	 * @param storeId 仓库ID
	 * @param goodsItemId 商品条目ID
	 */
	public GetAvailableCountKey(GUID storeId, GUID goodsItemId) {
		this.storeId = storeId;
		this.goodsItemId = goodsItemId;
	}
}
