package com.spark.psi.publish.constant;

public enum CheckoutReceiptedFlag {

	NeverReceipted("01", "未收款"), Receipting("02", "收款中"), FinishedReceipt("03", "已收款");

	private String code;
	private String title;

	private CheckoutReceiptedFlag(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public static CheckoutReceiptedFlag getFlag(String code) {
		if ("01".equals(code)) {
			return NeverReceipted;
		} else if ("02".equals(code)) {
			return Receipting;
		} else if ("03".equals(code)) {
			return FinishedReceipt;
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

}
