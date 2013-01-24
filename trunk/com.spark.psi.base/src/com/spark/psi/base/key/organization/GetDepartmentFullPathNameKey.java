package com.spark.psi.base.key.organization;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得部门的全路径名称</p>
 * 咧：销售中心 > 销售一部


 *
 
 * @version 2012-4-24
 */
public class GetDepartmentFullPathNameKey{
	
	private GUID id;
	
	private boolean isShowRoot = false;
	
	public GetDepartmentFullPathNameKey(GUID id,boolean isShowRoot){
		this.isShowRoot = isShowRoot;
		this.id = id;
    }
	
	public GetDepartmentFullPathNameKey(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }

	public boolean isShowRoot(){
    	return isShowRoot;
    }
		
	
}
