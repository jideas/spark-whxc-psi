package com.spark.psi.publish.constant;

public enum CustomerType {

	Normal("01", "ÆÕÍ¨¿Í»§");

	private String code;
	private String title;

	private CustomerType(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public static CustomerType getType(String code) {
		if (Normal.getCode().equals(code)) {
			return Normal;
		}
		return null;
	}
}
