package com.spark.oms.publish;

public enum TenantsRefundStatusEnum {
	No("0","���˿�"),
	Wait("1","�ȴ��˿�"),
	Apply("2","�������˿�"),
	Finish("3","����˿�"),
	;
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private TenantsRefundStatusEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static TenantsRefundStatusEnum getTenantsClass(String code){
		if(No.code.equals(code)){
			return No;
		}else if(Wait.code.equals(code)){
			return Wait;
		}else if(Apply.code.equals(code)){
			return Apply;
		}else if(Finish.code.equals(code)){
			return Finish;
		}else{
			return null;
		}
	}
}
