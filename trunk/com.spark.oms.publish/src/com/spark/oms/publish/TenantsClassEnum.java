package com.spark.oms.publish;

public enum TenantsClassEnum {
	Potential("01","潜在客户"),
	Intention("02","意向客户"),
	Official("03","正式客户"),
	Protected("04","保号客户"),
	Lossed("05","流失客户"),
	History("06","备案客户");
	;
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private TenantsClassEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static TenantsClassEnum getTenantsClass(String code){
		if(Potential.code.equals(code)){
			return Potential;
		}else if(Intention.code.equals(code)){
			return Intention;
		}else if(Intention.code.equals(code)){
			return Intention;
		}else if(Official.code.equals(code)){
			return Official;
		}else if(Protected.code.equals(code)){
			return Protected;
		}else if(Lossed.code.equals(code)){
			return Lossed;
		}else if(History.code.equals(code)){
			return History;
		}else{
			return null;
		}
	}
}
