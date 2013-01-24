package com.spark.oms.publish;


public enum PayKind {
	Refund("02","�˿�"),
	Receipt("01","�տ�");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private PayKind(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static PayKind getPayKind(String code){
		if(Refund.code.equals(code)){
			return Refund;
		}else if(Receipt.code.equals(code)){
			return Receipt;
		}else{
			return null;
		}
	}
}
