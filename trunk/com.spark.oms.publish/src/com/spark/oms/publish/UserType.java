package com.spark.oms.publish;

/**
 * �û�����
 */
public enum UserType {

	ManagerUsers("00", "ϵͳ�����û�"), FunctionUsers("01", "ְ���û�"), SaleUsers("02",
			"�����û�");

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
		throw new IllegalArgumentException(code + "����һ����ȷ���û�����ö��");
	}
}
