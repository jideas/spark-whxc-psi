package com.spark.psi.publish.smessage.entity;

import com.spark.psi.publish.SMessageType;

public class SMessageMonitorItem{

	private SMessageType type; 
	
	private boolean showMonitor = true;

	public SMessageMonitorItem(SMessageType type, boolean showMonitor){
		this.type= type; 
		this.showMonitor = showMonitor;
	}

	public boolean equals(Object obj){
		if(null == obj||null==type){
			return false;
		}
		if(obj instanceof SMessageMonitorItem){
			SMessageMonitorItem smti = (SMessageMonitorItem)obj;
			return this.type.equals(smti.getType());
		}
		return false;
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

	/**
     * @return the showMonitor
     */
    public boolean isShowMonitor(){
    	return showMonitor;
    }

	/**
     * @param showMonitor the showMonitor to set
     */
    public void setShowMonitor(boolean showMonitor){
    	this.showMonitor = showMonitor;
    }
}
