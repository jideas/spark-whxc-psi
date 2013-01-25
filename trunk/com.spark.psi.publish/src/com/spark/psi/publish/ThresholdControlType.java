package com.spark.psi.publish;

/**
 * 商品库存阈值控制类型
 * 
 */
public enum ThresholdControlType {

	COUNT("01", "商品数量"), AMOUNT("02", "商品金额");

	private String code;
	private String name;

	/**
	 * 
	 * @param value
	 * @param key
	 */
	ThresholdControlType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static ThresholdControlType getThresholdControlType(String code) {
		if (COUNT.code.equals(code)) {
			return COUNT;
		} else if (AMOUNT.code.equals(code)) {
			return AMOUNT;
		}
		return null;
	}

}
