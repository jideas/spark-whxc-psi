package com.spark.oms.publish;

/**
 * 用户在状态
 * 
 */
public enum UserStatus {

	onUser("01", "在职"), unUser("02", "离职");

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	private UserStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static UserStatus getUserStatus(String code) {
		if (onUser.code.equals(code)) {
			return onUser;
		} else if (unUser.code.equals(code)) {
			return unUser;
		} else {
			return null;
		}
	}
}