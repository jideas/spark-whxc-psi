package com.spark.oms.publish;

/**
 * 
 * ����ģ�����ͣ�ģ�顢����
 * 
 */
public enum FunctionType {
	
	Module("01", "ģ��"), Function("02", "����");

	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;

	private FunctionType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static FunctionType getFunctionType(String code) {
		if (Module.code.equals(code)) {
			return Module;
		} else if (Function.code.equals(code)) {
			return Function;
		} else {
			return null;
		}
	}
}
