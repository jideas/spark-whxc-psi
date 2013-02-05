package com.spark.psi.publish.split.constant;

public enum GoodsSplitStatus {
	Submiting("01", "待提交"),

	Rejected("02", "已驳回"),

	Approvaling("03", "待审批"),

	Approvaled("04", "待分配"),

	Distributed("05", "待入库"),

	Checkingin("06", "部分入库"),

	Finished("07", "已完成");
	private String code;
	private String title;

	private GoodsSplitStatus(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public static GoodsSplitStatus getStatus(String code) {
		if (Submiting.getCode().equals(code)) {
			return Submiting;
		} else if (Rejected.getCode().equals(code)) {
			return Rejected;
		} else if (Approvaled.getCode().equals(code)) {
			return Approvaled;
		} else if (Distributed.getCode().equals(code)) {
			return Distributed;
		} else if (Checkingin.getCode().equals(code)) {
			return Checkingin;
		} else if (Finished.getCode().equals(code)) {
			return Finished;
		} else if (Approvaling.getCode().equals(code)) {
			return Approvaling;
		}
		return null;
	}
}
