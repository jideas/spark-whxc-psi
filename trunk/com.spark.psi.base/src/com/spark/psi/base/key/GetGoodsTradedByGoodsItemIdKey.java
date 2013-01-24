package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>查询商品条目的交易记录</p>
 *


 *
 
 * @version 2012-3-9
 */
public class GetGoodsTradedByGoodsItemIdKey extends Key{

	public GetGoodsTradedByGoodsItemIdKey(GUID goodsItemId){
	    super(goodsItemId);
    }

}
