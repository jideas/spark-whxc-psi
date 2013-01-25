package com.spark.psi.publish.constant;

public enum SupplierCooperation {
	SelfSupporting("01", "自营"), JointVenture("02", "联营"), Consignment("03", "代销");

	private String code;
	private String title;

	private SupplierCooperation(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public static SupplierCooperation getType(String code) {
		if (SelfSupporting.getCode().equals(code)) {
			return SelfSupporting;
		} else if (JointVenture.getCode().equals(code)) {
			return JointVenture;
		} else if (Consignment.getCode().equals(code)) {
			return Consignment;
		}
		return null;
	}
}
