package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class TenantOrmEntity{
	
	private GUID id;
	
	private String config;

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public String getConfig(){
    	return config;
    }

	public void setConfig(String config){
    	this.config = config;
    }
	
	
	
}
