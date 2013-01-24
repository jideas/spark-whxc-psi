package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得商品库存情况</p>
 *


 *
 
 * @version 2012-3-9
 */
public class GetGoodsInventoryByStoreIdKey {

	private GUID storeId;
	
	/**
	 * 查询指定仓库的库存情况
	 * @param storeId  仓库id
	 */
	public GetGoodsInventoryByStoreIdKey(GUID storeId){
		this.storeId = storeId;
    }

	public GUID getStoreId(){
    	return storeId;
    }
	
	

}
