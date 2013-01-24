package com.spark.oms.publish;

public enum TenantsRefundStatusEnum {
	No("0","无退款"),
	Wait("1","等待退款"),
	Apply("2","已申请退款"),
	Finish("3","完成退款"),
	;
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
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
