package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * 
 * <p>根据员工id查询关联的仓库列表</p>
 *


 *
 
 * @version 2012-3-12
 */
public class GetStoreBySaleManKey extends Key{

	/**
	 * 仓库状态 默认为查询已启用的仓库
	 */
	private StoreStatus[] StoreStatuss = {StoreStatus.ENABLE};

	/**
	 * 查询销售经理或员工关联的已启用的仓库列表 
	 * @param salesManId
	 */
	public GetStoreBySaleManKey(GUID salesManId){
	    super(salesManId);
    }
	
	/**
	 * 查询销售经理或员工关联的指定状态的仓库列表
	 * @param salesManId
	 * @param StoreStatus 仓库状态
	 */
	public GetStoreBySaleManKey(GUID salesManId,StoreStatus... StoreStatuss){
	    super(salesManId);
	    this.StoreStatuss = StoreStatuss;
    }

	public StoreStatus[] getStoreStatus(){
    	return StoreStatuss;
    }	

}
