package com.spark.oms.publish;

public enum ReceiptRecordTypeEnum {
	ServerReceipt("01","服务收款"),
	ServerRefund("02","服务退款"),
	ServerSMS("03","通讯充值");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private ReceiptRecordTypeEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static ReceiptRecordTypeEnum getReceiptRecordTypeEnum(String code){
		if(ServerReceipt.code.equals(code)){
			return ServerReceipt;
		}else if(ServerRefund.code.equals(code)){
			return ServerRefund;
		}else if(ServerSMS.code.equals(code)){
			return ServerSMS;
		}else{
			return null;
		}
	}
	
}
