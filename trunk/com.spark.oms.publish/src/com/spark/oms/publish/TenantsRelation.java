package com.spark.oms.publish;

public enum TenantsRelation {
	Family("01","家人"),
	Friend("02","朋友"),
	Schoolmate("03","同学"),
	Colleague("04","同事"),
	Cognate("05","亲戚"),
	Other("06","其他");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private TenantsRelation(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static TenantsRelation getTenantsRelation(String code){
		if(Family.code.equals(code)){
			return Family;
		}else if(Friend.code.equals(code)){
			return Friend;
		}else if(Friend.code.equals(code)){
			return Friend;
		}else if(Schoolmate.code.equals(code)){
			return Schoolmate;
		}else if(Colleague.code.equals(code)){
			return Colleague;
		}else if(Cognate.code.equals(code)){
			return Cognate;
		}else if(Other.code.equals(code)){
			return Other;
		}else{
			return null;
		}
	}
}
