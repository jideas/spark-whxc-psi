package com.spark.psi.publish;

/**
 * ������
 */
public enum ShelfLifeWarningType {

	/**
	 * �ٽ�
	 */
	Closeto("01", "�ٽ�"), //
	/**
	 * ����
	 */
	Overdue("02", "����");

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
	private ShelfLifeWarningType(String code, String name) {
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
	public final static ShelfLifeWarningType getShelfLifeWarningType(String code) {
		if (Closeto.code.equals(code)) {
			return Closeto;
		} else if ( Overdue.code.equals(code)) {
			return Overdue;
		} 
		return null;
	}

}
