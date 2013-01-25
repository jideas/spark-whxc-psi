package com.spark.psi.publish;

/**
 * ����̵�״̬
 * 
 */
public enum InventoryCountStatus {

	Counting("01", "�̵���"), //
	Counted("02", "�����"); //

	private String code;
	private String name;

	private InventoryCountStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public static InventoryCountStatus getStatusByCode(String code) {
		if (Counting.code.equals(code)) {
			return Counting;
		} else if (Counted.code.equals(code)) {
			return Counted;
		}
		return null;
	}
}
