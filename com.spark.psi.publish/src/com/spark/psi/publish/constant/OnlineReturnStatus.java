package com.spark.psi.publish.constant;

public enum OnlineReturnStatus {

	Submitting("01", "待提交"), Rejected("02", "已驳回"), Approving("03", "待审批"), Stopped("04", "已中止"), Processing(
			"05", "进行中"), Finished("06", "已完成"),

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
