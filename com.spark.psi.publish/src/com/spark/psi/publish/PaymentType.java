package com.spark.psi.publish;

/**
 * 
 * <p>
 * ��������
 * </p>
 */
public enum PaymentType {
	/**
	 * �ɹ�����
	 */
	PAY_CGFK("01", "�ɹ�����"),
	/**
	 * �����˿�
	 */
	PAY_XSTK("02", "�����˿�"),
	/**
	 * �����˻�
	 */
	PAY_LSTK("03", "�����˿�"),
	/**
	 * ��ɸ���
	 */
	PAY_LCFK("04", "��ɸ���"),
	/**
	 * ��Ӫ����
	 */
	PAY_JOINTVENTRUE("05", "��Ӫ����");

	private String code, name;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private PaymentType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static PaymentType getPaymentType(String code) {
		if (PaymentType.PAY_CGFK.getCode().equals(code)) {
			return PaymentType.PAY_CGFK;
		} else if (PaymentType.PAY_XSTK.getCode().equals(code)) {
			return PaymentType.PAY_XSTK;
		} else if (PaymentType.PAY_LCFK.getCode().equals(code)) {
			return PaymentType.PAY_LCFK;
		} else if (PaymentType.PAY_LSTK.getCode().equals(code)) {
			return PaymentType.PAY_LSTK;
		}
		return null;
	}
}
