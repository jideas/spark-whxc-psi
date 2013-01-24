package com.spark.psi.base.key.store;

import com.spark.psi.publish.StoreStatus;

/**
 * 
 * <p>查询当前用户拥有权限的仓库guid列表</p>
 * context.find(Store[],GetUserStoreGuidsKey)
 * @version 2012-5-10
 */
public class GetUserStoreGuidsKey {
	
	private StoreStatus[] storeStatus;
	
	public GetUserStoreGuidsKey(){
		storeStatus = new StoreStatus[2];
		storeStatus[0] = StoreStatus.ENABLE;
		storeStatus[1] = StoreStatus.ONCOUNTING;
    }
	
	public GetUserStoreGuidsKey(StoreStatus... storeStatus){
		this.storeStatus = storeStatus;
	}

	public StoreStatus[] getStoreStatus(){
    	return storeStatus;
    }

	
}