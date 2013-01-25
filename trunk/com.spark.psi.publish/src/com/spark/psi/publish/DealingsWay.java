package com.spark.psi.publish;

public enum DealingsWay {
	/**
	 * �ֽ�
	 */
	Cash("01", "�ֽ�"),
	/**
	 * ˢ��
	 */
	ChargePay("02", "ˢ��"),
	/**
	 * ����ת��
	 */
	Account("03", "����ת��"),
	/**
	 * ֧Ʊ
	 */
	Check("04", "֧Ʊ");

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
