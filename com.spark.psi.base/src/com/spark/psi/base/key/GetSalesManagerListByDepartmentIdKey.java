package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得指定部门的销售经理列表</p>
 *


 *
 
 * @version 2012-3-8
 */
public class GetSalesManagerListByDepartmentIdKey{
	
	private final GUID id;
	
	public GetSalesManagerListByDepartmentIdKey(final GUID id){
		this.id = id;
	}

	public GUID getId(){
    	return id;
    }


}
