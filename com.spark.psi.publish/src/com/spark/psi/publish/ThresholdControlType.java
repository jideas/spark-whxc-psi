package com.spark.psi.publish;

/**
 * ��Ʒ�����ֵ��������
 * 
 */
public enum ThresholdControlType {

	COUNT("01", "��Ʒ����"), AMOUNT("02", "��Ʒ���");

	private String code;
	private String name;

	/**
	 * 
	 * @param value
	 * @param key
	 */
	ThresholdControlType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static ThresholdControlType getThresholdControlType(String code) {
		if (COUNT.code.equals(code)) {
			return COUNT;
		} else if (AMOUNT.code.equals(code)) {
			return AMOUNT;
		}
		return null;
	}

}
