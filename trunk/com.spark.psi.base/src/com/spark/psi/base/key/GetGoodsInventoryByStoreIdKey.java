package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�����Ʒ������</p>
 *


 *
 
 * @version 2012-3-9
 */
public class GetGoodsInventoryByStoreIdKey {

	private GUID storeId;
	
	/**
	 * ��ѯָ���ֿ�Ŀ�����
	 * @param storeId  �ֿ�id
	 */
	public GetGoodsInventoryByStoreIdKey(GUID storeId){
		this.storeId = storeId;
    }

	public GUID getStoreId(){
    	return storeId;
    }
	
	

}
