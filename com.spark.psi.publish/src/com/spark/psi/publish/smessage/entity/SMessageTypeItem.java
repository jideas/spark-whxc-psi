package com.spark.psi.publish.smessage.entity;

import com.spark.psi.publish.SMessageType;

public class SMessageTypeItem{

	private SMessageType type;

	private boolean isBuddling = true; 

	public SMessageTypeItem(SMessageType type,boolean isBuddling ){
		this.type= type;
		this.isBuddling = isBuddling; 
	}

	public boolean equals(Object obj){
		if(null == obj||null==type){
			return false;
		}
		if(obj instanceof SMessageTypeItem){
			SMessageTypeItem smti = (SMessageTypeItem)obj;
			return this.type.equals(smti.getType());
		}
		return false;
	} 

	/**
     * @return the isBuddling
     */
    public boolean isBuddling(){
    	return isBuddling;
    }

	/**
     * @param isBuddling the isBuddling to set
     */
    public void setBuddling(boolean isBuddling){
    	this.isBuddling = isBuddling;
    }

	/**
     * @return the type
     */
    public SMessageType getType(){
    	return type;
    }

	/**
     * @param type the type to set
     */
    public void setType(SMessageType type){
    	this.type = type;
    }
    
    public String getCode(){
    	if(null==type){
    		return null;
    	}
    	return this.type.getCode();
    }
    public String getTitle(){
    	if(null==type){
    		return null;
    	}
    	return this.type.getTitle();
    } 
}
