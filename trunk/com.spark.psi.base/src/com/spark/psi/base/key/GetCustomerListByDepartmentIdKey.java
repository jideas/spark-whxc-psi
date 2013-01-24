package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>ͨ������ID��ѯ�ͻ��б��������ﲿ�ŵĿͻ���</p>
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
