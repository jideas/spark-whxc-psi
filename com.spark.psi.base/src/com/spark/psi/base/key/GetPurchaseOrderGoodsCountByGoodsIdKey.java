package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ѯ�ɹ������Ķ�������</p>
 *	δ���ɳ��ⵥ����Ʒ����


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
