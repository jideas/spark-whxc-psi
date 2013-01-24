package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>查询采购订单的订货数量</p>
 *	未生成出库单的商品数量


 *
 
 * @version 2012-3-29
 */
public class GetPurchaseOrderGoodsCountByGoodsIdKey{
	
	private GUID goodsItemId,StoreId;
	
	public GetPurchaseOrderGoodsCountByGoodsIdKey(GUID goodsItemId,GUID storeId){
	    this.goodsItemId = goodsItemId;
	    this.StoreId = storeId;
    }

	public GUID getGoodsItemId(){
    	return goodsItemId;
    }
	
	public GUID getStoreId(){
    	return StoreId;
    }

	
	
	
}
