package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ѯ�ֿ��ָ����Ʒ�ɹ�������;������</p>
 *


 *
 
 * @version 2012-3-9
 */
public class GetAboutGoodsCountByGoodsItemIdKey{
	
	private GUID goodsItemId;
	private GUID storeId;
	
	/**
	 * ��ѯ�ֿ��ָ����Ʒ�ɹ�������;������
	 * @param goodsItemId ��Ʒ��Ŀid
	 * @param storeId �ֿ�id
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
