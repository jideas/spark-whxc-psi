package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>���ݸ�id����Ӳ����б�</p>
 *


 *
 
 * @version 2012-3-8
 */
public class GetDepartmentChildrenListByParentKey{
	
	private final GUID parent;
	
	public GetDepartmentChildrenListByParentKey(final GUID parent){
		this.parent = parent;
	}

	public GUID getParent(){
    	return parent;
    }
	
	
}
