package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>查询仓库对指定商品采购即将在途的数量</p>
 *


 *
 
 * @version 2012-3-9
 */
public class GetAboutGoodsCountByGoodsItemIdKey{
	
	private GUID goodsItemId;
	private GUID storeId;
	
	/**
	 * 查询仓库对指定商品采购即将在途的数量
	 * @param goodsItemId 商品条目id
	 * @param storeId 仓库id
	 */
	public GetAboutGoodsCountByGoodsItemIdKey(GUID goodsItemId,GUID storeId){
		this.goodsItemId = goodsItemId;
		this.storeId = storeId;
    }

	public GUID getGoodsItemId(){
    	return goodsItemId;
    }

	public GUID getStoreId(){
    	return storeId;
    }

	
}
