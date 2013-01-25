package com.spark.psi.publish;

public enum DealingsWay {
	/**
	 * 现金
	 */
	Cash("01", "现金"),
	/**
	 * 刷卡
	 */
	ChargePay("02", "刷卡"),
	/**
	 * 银行转账
	 */
	Account("03", "银行转账"),
	/**
	 * 支票
	 */
	Check("04", "支票");

	private String code;
	private String name;

	/**
	 * @param code
	 */
	private DealingsWay(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static DealingsWay getDealingsWay(String code) {
		if (null == code || "".equals(code)) {
			return null;
		}
		if ("01".equals(code)) {
			return DealingsWay.Cash;
		} else if ("02".equals(code)) {
			return DealingsWay.ChargePay;
		}
		else if ("03".equals(code)) {
			return DealingsWay.Account;
		}
		else if ("04".equals(code)) {
			return DealingsWay.Check;
		}
		return null;
	}

}
