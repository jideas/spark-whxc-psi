package com.spark.psi.base.key.organization;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ѯָ�����ŵ��������Ȳ����б�</p>
 *


 *
 
 * @version 2012-4-6
 */
public class GetAncestorsDepartmentListKey{
	
	
	private GUID deptId;
	
	public GetAncestorsDepartmentListKey(GUID deptId){
		this.deptId = deptId;
	}

	public GUID getDeptId(){
    	return deptId;
    }

	
}
