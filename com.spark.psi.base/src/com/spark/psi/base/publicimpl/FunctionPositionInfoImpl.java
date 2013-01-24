package com.spark.psi.base.publicimpl;

import java.util.List;
import java.util.Map;

import com.spark.psi.publish.base.function.FunctionPositionInfo;

public class FunctionPositionInfoImpl implements FunctionPositionInfo{

	List<FunctionPositionImpl> list;
	
	public FunctionPositionInfoImpl(List<FunctionPositionImpl> list){
		this.list = list;
    }
	
	public FunctionPosition getFunctionPosition(String functionId){
		for(FunctionPositionImpl entity : list){
	        if(entity.getFunctionId().equals(functionId)){
	        	return entity;
	        }
        }
		return null;
	}

	public FunctionPosition[] getUserFunctionPositions(){
		return list.toArray(new FunctionPosition[0]);
	}

	public static class FunctionPositionImpl implements FunctionPositionInfo.FunctionPosition {

		private String functionId;
		
		private Map<String, Integer> rolePriority;
		
		private int xindex,yindex;
		
		private boolean putMain;
		
		private boolean inited;
		

		public String getFunctionId(){
        	return functionId;
        }

		public void setFunctionId(String functionId){
        	this.functionId = functionId;
        }

		public Map<String, Integer> getRolePriority(){
        	return rolePriority;
        }

		public void setRolePriority(Map<String, Integer> rolePriority){
        	this.rolePriority = rolePriority;
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

		public boolean isPutMain(){
        	return putMain;
        }

		public void setPutMain(boolean putMain){
        	this.putMain = putMain;
        }

		public boolean isInited(){
        	return inited;
        }

		public void setInited(boolean inited){
        	this.inited = inited;
        }
			
	}
	
}
