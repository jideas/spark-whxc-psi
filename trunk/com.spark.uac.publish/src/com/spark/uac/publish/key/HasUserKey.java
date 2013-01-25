package com.spark.uac.publish.key;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>查询认证中心是否有指定用户</p>
 *


 *
 
 * @version 2012-4-22
 */
@StructClass
public class HasUserKey {
	
	private GUID id;
	
	public HasUserKey(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }
	
	
}