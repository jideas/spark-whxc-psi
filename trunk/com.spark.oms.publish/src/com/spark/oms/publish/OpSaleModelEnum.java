package com.spark.oms.publish;

public enum OpSaleModelEnum {
	Retail("01","����"),
	Wholesale("02","����"),
	All("03","ȫ��");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private OpSaleModelEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OpSaleModelEnum getOpSaleModel(String code){
		if(Retail.code.equals(code)){
			return Retail;
		}else if(Wholesale.code.equals(code)){
			return Wholesale;
		}else if(All.code.equals(code)){
			return All;
		}else{
			return null;
		}
	}
	
}
