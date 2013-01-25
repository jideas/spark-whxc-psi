package com.spark.psi.publish.constant;

public enum SupplierType {
	Materials("01", "���Ϲ�Ӧ��"), RealGoods("02", "��Ʒ��Ӧ��"), Packing("03", "��װ��Ӧ��"), Common("04", "�ۺϹ�Ӧ��");

	private String code;
	private String title;

	private SupplierType(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public static SupplierType getType(String code) {
		if (Materials.getCode().equals(code)) {
			return Materials;
		} else if (RealGoods.getCode().equals(code)) {
			return RealGoods;
		} else if (Packing.getCode().equals(code)) {
			return Packing;
		} else if (Common.getCode().equals(code)) {
			return Common;
		}
		return null;
	}
}
