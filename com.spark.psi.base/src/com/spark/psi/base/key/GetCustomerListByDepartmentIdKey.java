package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>通过部门ID查询客户列表（包含子孙部门的客户）</p>
 *


 *
 
 * @version 2012-3-27
 */
public class GetCustomerListByDepartmentIdKey{
	
	private final GUID departmentId;
	
	public GetCustomerListByDepartmentIdKey(GUID departmentId){
		this.departmentId = departmentId;
	}

	public GUID getDepartmentId(){
    	return departmentId;
    }	
	
}
