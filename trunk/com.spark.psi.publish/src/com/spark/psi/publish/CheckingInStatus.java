package com.spark.psi.publish;

/**
 * ���״̬
 */
public enum CheckingInStatus {

	None("01", "δ���"), //
	Part("02", "�������"), //
	Finish("03", "ȫ�����"), //
	Stop("04", "����ֹ");

	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;

	/**
	 * 
	 * @param code
	 * @param name
	 */
	private CheckingInStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * ���ݴ����ȡö�ٶ���
	 * 
	 * @param code
	 * @return
	 */
	public final static CheckingInStatus getCheckingInType(String code) {
		if (None.code.equals(code)) {
			return None;
		} else if (Part.code.equals(code)) {
			return Part;
		} else if (Finish.code.equals(code)) {
			return Finish;
		} else if (Stop.code.equals(code)) {
			return Stop;
		}
		return null;
	}

}
