package com.spark.psi.publish;

/**
 * 
 * <p>���۵�״̬</p>
 *


 *
 
 * @version 2012-3-6
 */
public enum RetailStatus{
	
	Completed("07","�����"),
	Cache("21","�ҵ�״̬"),
	Delivery("22","�ͻ��տ�");
	
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
