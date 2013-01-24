package com.spark.psi.base.key.goods;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得指定分类的直属下级分类</p>
 *


 *
 
 * @version 2012-4-6
 */
public class GetChildrenGoodsCategoryListKey{
	
	private GUID parentId;
	
	public GetChildrenGoodsCategoryListKey(GUID parentId){
		this.parentId = parentId;
	}

	public GUID getParentId(){
    	return parentId;
    }

	


}
