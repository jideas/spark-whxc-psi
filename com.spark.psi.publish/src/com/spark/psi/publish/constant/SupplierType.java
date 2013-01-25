package com.spark.psi.publish.constant;

public enum SupplierType {
	Materials("01", "材料供应商"), RealGoods("02", "商品供应商"), Packing("03", "包装供应商"), Common("04", "综合供应商");

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
