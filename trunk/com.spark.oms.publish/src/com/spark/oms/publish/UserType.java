package com.spark.oms.publish;

/**
 * 用户类型
 */
public enum UserType {

	ManagerUsers("00", "系统管理用户"), FunctionUsers("01", "职能用户"), SaleUsers("02",
			"销售用户");

	private String code;

	private String name;

	private UserType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static UserType getUserType(String code) {

		for (UserType type : UserType.values()) {
			if (type.getCode().equals(code)) {
				return type;
			}
		}
		throw new IllegalArgumentException(code + "不是一个正确的用户类型枚举");
	}
}
