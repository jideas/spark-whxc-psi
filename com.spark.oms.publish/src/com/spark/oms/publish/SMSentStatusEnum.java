package com.spark.oms.publish;

public enum SMSentStatusEnum {
	Succeed("1","成功"),
	Failed("0","失败");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private SMSentStatusEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static SMSentStatusEnum getSMSentStatusEnum(String code){
		if(Succeed.code.equals(code)){
			return Succeed;
		}else if(Failed.code.equals(code)){
			return Failed;
		}else{
			return null;
		}
	}
}
