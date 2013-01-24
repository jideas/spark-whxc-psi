package com.spark.oms.publish;
/**
 * ����ͨ���Ƽ۷�ʽ���·������������ײ�
 */
public enum SMSChannelChargeType {
	Piece("01","����"),
	Package("02","ͣ��");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
