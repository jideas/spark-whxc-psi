package com.spark.psi.publish.constant;

public enum OnlineReturnStatus {

	Submitting("01", "���ύ"), Rejected("02", "�Ѳ���"), Approving("03", "������"), Stopped("04", "����ֹ"), Processing(
			"05", "������"), Finished("06", "�����"),

	;
	private String code;
	private String title;

	private OnlineReturnStatus(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return this.code;
	}

	public String getTitle() {
		return this.title;
	}

	public static OnlineReturnStatus getStatus(String code) {
		if (code == null || code.trim().length() == 0) {
			return null;
		}
		if (code.equals(Submitting.getCode())) {
			return Submitting;
		}
		if (code.equals(Rejected.getCode())) {
			return Rejected;
		}
		if (code.equals(Approving.getCode())) {
			return Approving;
		}
		if (code.equals(Stopped.getCode())) {
			return Stopped;
		}
		if (code.equals(Processing.getCode())) {
			return Processing;
		}
		if (code.equals(Finished.getCode())) {
			return Finished;
		}
		return null;
	}
}
