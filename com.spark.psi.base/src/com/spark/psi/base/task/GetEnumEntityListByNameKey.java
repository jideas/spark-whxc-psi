package com.spark.psi.base.task;

import com.spark.psi.publish.EnumType;

/**
 * 
 * <p>ͨ�����Ĳ�ѯö���ֵ�����б�</p>
 *


 *
 
 * @version 2012-8-1
 */
public class GetEnumEntityListByNameKey{
	
	private String name;
	
	private EnumType type;
	
	public GetEnumEntityListByNameKey(EnumType type,String name){
	    this.name = name;
	    this.type = type;
    }

	public String getName(){
    	return name;
    }

	public EnumType getType(){
    	return type;
    }

	
	
}
