package com.spark.oms.publish;
/**
 * 短信通道计价方式：月发送量、月租套餐
 */
public enum SMSChannelChargeType {
	Piece("01","在售"),
	Package("02","停售");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private SMSChannelChargeType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static SMSChannelChargeType getSMSChannelChargeType(String code){
		if(Piece.code.equals(code)){
			return Piece;
		}else if(Package.code.equals(code)){
			return Package;
		}else{
			return null;
		}
	}

}
