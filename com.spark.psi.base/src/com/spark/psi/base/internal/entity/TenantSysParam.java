package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.SysParamKey;

@StructClass
public class TenantSysParam{
	
	private GUID id;

	private GUID tenantId;
	
	private String key;
	
	private boolean yes;

	public TenantSysParam(GUID tenantsGuid,SysParamKey key){
		this.tenantId = tenantsGuid;
		this.key = key.toString();
	}
	
	

	public TenantSysParam(GUID tenantsGuid,SysParamKey key,boolean value){
		this.tenantId = tenantsGuid;
		this.key = key.toString();
		this.yes = value;
	}
	public TenantSysParam(){
    }
	public GUID getTenantId(){
    	return tenantId;
    }

	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }

	public String getKey(){
    	return key;
    }

	public void setKey(String key){
    	this.key = key;
    }

	public boolean isYes(){
    	return yes;
    }

	public void setYes(boolean yes){
    	this.yes = yes;
    }

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	
}
