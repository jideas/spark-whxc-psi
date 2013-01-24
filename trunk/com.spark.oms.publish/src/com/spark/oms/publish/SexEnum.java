package com.spark.oms.publish;

public enum SexEnum {
	Man("01","先生"),
	Woman("02","女士");
	
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private SexEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public static SexEnum getSexEnum(String code){
		if(Man.code.equals(code)){
			return Man;
		}else if(Woman.code.equals(code)){
			return Woman;
		}
		return null;
	}
}
