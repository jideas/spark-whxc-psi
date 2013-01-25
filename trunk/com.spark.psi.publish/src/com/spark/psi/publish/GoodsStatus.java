package com.spark.psi.publish;

/**
 * ��Ʒ״̬ö��
 */
public enum GoodsStatus {

	ON_SALE("01", "����"), //
	STOP_SALE("02", "ͣ��"), //
	PART_SALE("03", "���Լ���ͣ����������");

	private String code;
	private String name;

	private GoodsStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return code
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

	public static GoodsStatus getGoodsStatus(String code) {
		if (ON_SALE.code.equals(code)) {
			return ON_SALE;
		} else if (STOP_SALE.code.equals(code)) {
			return STOP_SALE;
		} else if (PART_SALE.code.equals(code)) {
			return PART_SALE;
		}
		return null;
	}
}
