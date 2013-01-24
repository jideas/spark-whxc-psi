package com.spark.oms.publish;

public enum OrderServiceSuspendStatusEnum {
	unSuspend("0","������"),
	appSuspend("1","����"),
	appvSuspend("2","ͣ��"),
	cancleSuspend("3","����");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
