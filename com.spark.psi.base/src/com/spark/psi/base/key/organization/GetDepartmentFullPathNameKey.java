package com.spark.psi.base.key.organization;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ò��ŵ�ȫ·������</p>
 * �֣��������� > ����һ��


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
