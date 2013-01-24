package com.spark.oms.publish;
/**
 * 短信通道接入方式：移动、联通、电信、网通
 */
public enum SMSChannelAccessType {
	Mobile("01","移动"),
	UniCom("02","联通"),
	TelCom("03","电信"),
	NetCom("04","网通");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private SMSChannelAccessType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static SMSChannelAccessType getSMSChannelAccessType(String code){
		if(Mobile.code.equals(code)){
			return Mobile;
		}else if(UniCom.code.equals(code)){
			return UniCom;
		}else if(TelCom.code.equals(code)){
			return TelCom;
		}else if(NetCom.code.equals(code)){
			return NetCom;
		}else{
			return null;
		}
	}
}
