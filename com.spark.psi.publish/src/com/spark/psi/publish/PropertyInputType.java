package com.spark.psi.publish;

/**
 * 值的输入类型枚举
 * 
 */
public enum PropertyInputType {

	INPUT("01", "手工输入"), //
	SELECT("02", "下拉选择");

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
