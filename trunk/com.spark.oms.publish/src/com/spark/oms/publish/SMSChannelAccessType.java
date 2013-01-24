package com.spark.oms.publish;
/**
 * ����ͨ�����뷽ʽ���ƶ�����ͨ�����š���ͨ
 */
public enum SMSChannelAccessType {
	Mobile("01","�ƶ�"),
	UniCom("02","��ͨ"),
	TelCom("03","����"),
	NetCom("04","��ͨ");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
