package com.spark.oms.publish;

public enum OrderServiceSuspendStatusEnum {
	unSuspend("0","无申请"),
	appSuspend("1","申请"),
	appvSuspend("2","停用"),
	cancleSuspend("3","撤销");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private OrderServiceSuspendStatusEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OrderServiceSuspendStatusEnum getOrderServiceSuspendStatusEnum(String code){
		if(unSuspend.code.equals(code)){
			return unSuspend;
		}else if(appSuspend.code.equals(code)){
			return appSuspend;
		}else if(appvSuspend.code.equals(code)){
			return appvSuspend;
		}else if(cancleSuspend.code.equals(code)){
			return cancleSuspend;
		}else{
			return null;
		}
	}
}
