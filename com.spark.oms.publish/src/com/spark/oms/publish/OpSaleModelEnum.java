package com.spark.oms.publish;

public enum OpSaleModelEnum {
	Retail("01","零售"),
	Wholesale("02","批发"),
	All("03","全部");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
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
