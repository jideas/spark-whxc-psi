package com.spark.oms.publish;

/**
 * 用户启用状态
 * @author Administrator
 *
 */
public enum EnabledStatus {
	
	startUser("01", "启用"),stopUser("02", "停用");

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
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
