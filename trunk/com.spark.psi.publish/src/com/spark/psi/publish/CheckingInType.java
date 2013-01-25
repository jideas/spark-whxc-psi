package com.spark.psi.publish;

/**
 * �������
 */
public enum CheckingInType {

	/**
	 * �ɹ����
	 */
	Purchase("01", "�ɹ����"), //
	/**
	 * ���ǲɹ�
	 */
	Irregular("02", "���ǲɹ�"), //
	/**
	 * �����˻�
	 */
	Return("03", "�����˻�"), //
	/**
	 * �����˻�
	 */
	Adjustment("04", "�������"),
	/**
	 * �������
	 */
	Kit("05", "�������"),
	/**
	 * ��Ʒ���
	 */
	RealGoods("06", "��Ʒ���"),
	/**
	 * ��Ӫ���
	 */
	Joint("07", "��Ӫ���"),
	//
	Gift("08", "��Ʒ���");

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
	private CheckingInType(String code, String name) {
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
	public final static CheckingInType getCheckingInType(String code) {
		if (Purchase.code.equals(code)) {
			return Purchase;
		} else if (Return.code.equals(code)) {
			return Return;
		} else if (Irregular.code.equals(code)) {
			return Irregular;
		} else if (Kit.code.equals(code)) {
			return Kit;
		} else if (Adjustment.code.equals(code)) {
			return Adjustment;
		} else if (RealGoods.code.equals(code)) {
			return RealGoods;
		} else if (Joint.code.equals(code)) {
			return Joint;
		} else if (Gift.code.equals(code)) {
			return Gift;
		}
		return null;
	}

}
