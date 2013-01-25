package com.spark.psi.publish;

/**
 * ֵ����������ö��
 * 
 */
public enum PropertyInputType {

	INPUT("01", "�ֹ�����"), //
	SELECT("02", "����ѡ��");

	private String code;
	private String name;

	/**
	 * 
	 * @param value
	 * @param key
	 */
	PropertyInputType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static PropertyInputType getPropertyInputType(String code) {
		if (INPUT.code.equals(code)) {
			return INPUT;
		} else if (SELECT.code.equals(code)) {
			return SELECT;
		}
		return null;
	}
}
