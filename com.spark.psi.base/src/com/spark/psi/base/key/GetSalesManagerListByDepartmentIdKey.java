package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>���ָ�����ŵ����۾����б�</p>
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
