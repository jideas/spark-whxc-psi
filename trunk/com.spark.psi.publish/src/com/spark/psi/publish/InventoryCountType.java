package com.spark.psi.publish;

/**
 * ����̵�Ŀ������
 * 
 */
public enum InventoryCountType {

	Materials("01", "���Ͽ��"), //
	Kit("02", "�������"); //

	private String code;
	private String name;

	private InventoryCountType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public static InventoryCountType getTypeByCode(String code) {
		if (Materials.code.equals(code)) {
			return Materials;
		} else if (Kit.code.equals(code)) {
			return Kit;
		}
		return null;
	}
}
