package com.spark.psi.base.key;

import com.spark.psi.publish.StoreStatus;


/**
 * 
 * <p>��ѯ�ֿ��б�</p>
 *


 *
 
 * @version 2012-3-9
 */
public class GetAllStoreListKey{
	
	
	/**
	 * �ֿ�״̬ Ĭ��Ϊ��ѯ�����õĲֿ�
	 */
	private StoreStatus[] StoreStatuss = {StoreStatus.ENABLE};
	
	public GetAllStoreListKey(){
	}
	
	public GetAllStoreListKey(StoreStatus... StoreStatuss){
		this.StoreStatuss = StoreStatuss;
	}
	
	
	public StoreStatus[] getStoreStatuss(){
    	return StoreStatuss;
    }	
	
}
