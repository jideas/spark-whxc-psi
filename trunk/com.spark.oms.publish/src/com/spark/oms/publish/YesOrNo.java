package com.spark.oms.publish;

public enum YesOrNo {
	Yes("1","ÊÇ"),
	No("0","·ñ")
	;
	private String code;
	private String value;
	private YesOrNo(String code,String value){
		this.code = code;
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public String getValue() {
		return value;
	}
	public static YesOrNo getYesOrNo(String code){
		if(Yes.getCode().equals(code)){
			return Yes;
		}else if(No.getCode().equals(code)){
			return No;
		}
		return null;
		
	}
}
