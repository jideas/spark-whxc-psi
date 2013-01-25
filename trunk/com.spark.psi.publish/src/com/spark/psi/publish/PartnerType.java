package com.spark.psi.publish;

/**
 * 
 * <p>����������� �ͻ� or ��Ӧ��</p>
 *


 *
 
 * @version 2012-3-12
 */
public enum PartnerType{
	/**
	 * ��Ӧ��
	 */
	Supplier("01", "��Ӧ��"),
	/**
	 * �ͻ�
	 */
	Customer("02", "�ͻ�");
	
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
