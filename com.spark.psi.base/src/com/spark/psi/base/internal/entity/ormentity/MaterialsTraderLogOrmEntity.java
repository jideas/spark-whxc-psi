package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class MaterialsTraderLogOrmEntity{
	
	private GUID id;
	
	private GUID materialsItemId;
	
	private GUID materialsId;
	
	private GUID partnerId;
	
	private String data;
	
	private String type;

	
	
	public GUID getMaterialsId(){
    	return materialsId;
    }

	public void setMaterialsId(GUID MaterialsId){
    	this.materialsId = MaterialsId;
    }

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public GUID getMaterialsItemId(){
    	return materialsItemId;
    }

	public void setMaterialsItemId(GUID MaterialsItemId){
    	this.materialsItemId = MaterialsItemId;
    }

	public GUID getPartnerId(){
    	return partnerId;
    }

	public void setPartnerId(GUID partnerId){
    	this.partnerId = partnerId;
    }

	public String getData(){
    	return data;
    }

	public void setData(String data){
    	this.data = data;
    }

	public String getType(){
    	return type;
    }

	public void setType(String type){
    	this.type = type;
    }
	
	
	
}
