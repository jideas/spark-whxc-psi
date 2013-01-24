package com.spark.oms.publish;

public enum TenantsRelation {
	Family("01","����"),
	Friend("02","����"),
	Schoolmate("03","ͬѧ"),
	Colleague("04","ͬ��"),
	Cognate("05","����"),
	Other("06","����");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
