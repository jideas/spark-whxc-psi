package com.spark.psi.publish;

/**
 * 
 * <p>合作伙伴类型 客户 or 供应商</p>
 *


 *
 
 * @version 2012-3-12
 */
public enum PartnerType{
	/**
	 * 供应商
	 */
	Supplier("01", "供应商"),
	/**
	 * 客户
	 */
	Customer("02", "客户");
	
	final String code;
	
	private String name;
	
	PartnerType(String value, String name){
		this.code = value;
		this.name = name;
	}
	
	public static PartnerType getPartnerTypeByValue(String value){
		for(PartnerType pt : PartnerType.values()){
	        if(pt.code.equals(value)){
	        	return pt;
	        }
        }
		return null;
	}

	public String getCode(){
    	return code;
    }

	public String getName() {
		return name;
	}
	
}
