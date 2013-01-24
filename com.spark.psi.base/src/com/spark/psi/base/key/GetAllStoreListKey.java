package com.spark.psi.base.key;

import com.spark.psi.publish.StoreStatus;


/**
 * 
 * <p>查询仓库列表</p>
 *


 *
 
 * @version 2012-3-9
 */
public class GetAllStoreListKey{
	
	
	/**
	 * 仓库状态 默认为查询已启用的仓库
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
