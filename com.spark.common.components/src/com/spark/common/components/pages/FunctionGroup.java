package com.spark.common.components.pages;

public enum FunctionGroup{
	
	Sales("01","Sales"),
	Inventory("02","Inventory"),
	Account("03","Finance"),
	Purchase("04","Buy"),
	Other("05","Other"),
	Hide("06","Hide");
	
	final String code,name;
	
	private FunctionGroup(String code,String name){
	    this.code = code;
	    this.name = name;
    }

	public String getCode(){
    	return code;
    }

	public String getName(){
    	return name;
    }
	
	public static FunctionGroup getFunctionGroupByCode(String code){
		for(FunctionGroup fg : FunctionGroup.values()){
	        if(fg.getCode().equals(code)){
	        	return fg;
	        }
        }
		return null;
	}
	
}
