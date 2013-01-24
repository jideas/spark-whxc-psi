package com.spark.common.components;

public enum SendMessageType {
	UserActive("01", "�û�����"), ArrivalNotice("02", "����֪ͨ"),

	;
	private String code;
	private String title;

	private SendMessageType(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public static SendMessageType getSendMessageType(String code) {
		if (SendMessageType.UserActive.getCode().equals(code)) {
			return SendMessageType.UserActive;
		} else if (SendMessageType.ArrivalNotice.getCode().equals(code)) {
			return SendMessageType.ArrivalNotice;
		}
		return null;
	}
}
