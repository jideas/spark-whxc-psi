package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * 
 * <p>����Ա��id��ù���Ĳֿ��б�</p>
 *


 *
 
 * @version 2012-3-12
 */
public class GetStoreListByManagerKey extends Key{
	
	/**
	 * �ֿ�״̬ Ĭ��Ϊ��ѯ�����õĲֿ�
	 */
	private StoreStatus[] StoreStatuss = {StoreStatus.ENABLE};

	/**
	 * ��ѯ�ֿ����Ա����Ĳֿ��б�
	 * @param storeManagerId
	 */
	public GetStoreListByManagerKey(GUID storeManagerId){
	    super(storeManagerId);
    }

	public StoreStatus[] getStoreStatus(){
    	return StoreStatuss;
    }

	public void setStoreStatus(StoreStatus... StoreStatus){
    	this.StoreStatuss = StoreStatus;
    }

	
}
