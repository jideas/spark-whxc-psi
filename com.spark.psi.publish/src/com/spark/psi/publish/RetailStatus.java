package com.spark.psi.publish;

/**
 * 
 * <p>零售单状态</p>
 *


 *
 
 * @version 2012-3-6
 */
public enum RetailStatus{
	
	Completed("07","已完成"),
	Cache("21","挂单状态"),
	Delivery("22","送货收款");
	
	final String key;
	final String name;
	
	
	
	public String getKey(){
    	return key;
    }



	public String getName(){
    	return name;
    }
	
	private RetailStatus(String key,String name){
		this.key = key;
		this.name = name;
	}

}
