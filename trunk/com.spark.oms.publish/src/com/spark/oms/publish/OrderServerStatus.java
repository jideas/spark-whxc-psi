package com.spark.oms.publish;
/**
 * ����״̬��δ��Ч����Ч�����϶���
 */
public enum OrderServerStatus {
	//��������ڶ����ڽ���ǰ��״̬
	UnVaildstatus("00","δ��Ч"),
	Vaildstatus("01","��Ч"),
	Invalidstatus("99","����");
	
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private OrderServerStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public static OrderServerStatus getOrderStatus(String code){
		if(UnVaildstatus.code.equals(code)){
			return UnVaildstatus;
		}else if(Vaildstatus.code.equals(code)){
			return Vaildstatus;
		}else if(Invalidstatus.code.equals(code)){
			return Invalidstatus;
		}else{
			return null;
		}
	}
}
