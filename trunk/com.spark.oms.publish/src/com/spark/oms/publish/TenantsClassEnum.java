package com.spark.oms.publish;

public enum TenantsClassEnum {
	Potential("01","Ǳ�ڿͻ�"),
	Intention("02","����ͻ�"),
	Official("03","��ʽ�ͻ�"),
	Protected("04","���ſͻ�"),
	Lossed("05","��ʧ�ͻ�"),
	History("06","�����ͻ�");
	;
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
