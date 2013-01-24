package com.spark.oms.publish;

/**
 * 
 * 功能模块类型：模块、功能
 * 
 */
public enum FunctionType {
	
	Module("01", "模块"), Function("02", "功能");

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
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
