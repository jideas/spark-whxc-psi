package com.spark.psi.base.key.materials;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>���ָ�������ֱ���¼�����</p>
 *


 *
 
 * @version 2012-4-6
 */
public class GetChildrenMaterialsCategoryListKey{
	
	private GUID parentId;
	
	public GetChildrenMaterialsCategoryListKey(GUID parentId){
		this.parentId = parentId;
	}

	public GUID getParentId(){
    	return parentId;
    }

	


}
