package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.FunctionPosition;

public class FunctionPositionImpl implements FunctionPosition{
	
	private GUID id;
	
	private String functionId;
	
	private GUID userId;
	
	private int xindex,yindex;
	
	private boolean putMain;

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public String getFunctionId(){
    	return functionId;
    }

	public void setFunctionId(String functionId){
    	this.functionId = functionId;
    }

	public void setUserId(GUID userid){
    	this.userId = userid;
    }

	public int getXindex(){
    	return xindex;
    }

	public void setXindex(int xindex){
    	this.xindex = xindex;
    }

	public int getYindex(){
    	return yindex;
    }

	public void setYindex(int yindex){
    	this.yindex = yindex;
    }


	public void setButMain(boolean butMain){
    	this.putMain = butMain;
    }

	public GUID getUserId(){
	    return userId;
    }

	public boolean isPutMain(){
	    return putMain;
    }

}
