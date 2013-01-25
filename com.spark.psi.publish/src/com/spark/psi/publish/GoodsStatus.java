package com.spark.psi.publish;

/**
 * 商品状态枚举
 */
public enum GoodsStatus {

	ON_SALE("01", "在售"), //
	STOP_SALE("02", "停售"), //
	PART_SALE("03", "属性既有停售又有在售");

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
