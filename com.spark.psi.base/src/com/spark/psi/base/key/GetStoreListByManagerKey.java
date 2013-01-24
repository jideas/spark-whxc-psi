package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * 
 * <p>根据员工id获得管理的仓库列表</p>
 *


 *
 
 * @version 2012-3-12
 */
public class GetStoreListByManagerKey extends Key{
	
	/**
	 * 仓库状态 默认为查询已启用的仓库
	 */
	private StoreStatus[] StoreStatuss = {StoreStatus.ENABLE};

	/**
	 * 查询仓库管理员管理的仓库列表
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
