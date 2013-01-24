package com.spark.oms.publish;

/**
 * �û�����״̬
 * @author Administrator
 *
 */
public enum EnabledStatus {
	
	startUser("01", "����"),stopUser("02", "ͣ��");

	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;

	private EnabledStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static EnabledStatus getEnabledStatus(String code) {
		for(EnabledStatus type:EnabledStatus.values()){
			if(type.getCode().equals(code))
				return type;
		}
		return null;
	}
}
