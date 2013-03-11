package com.spark.psi.publish;

/**
 * 保质期
 */
public enum ShelfLifeWarningType {

	/**
	 * 临近
	 */
	Closeto("01", "临近"), //
	/**
	 * 过期
	 */
	Overdue("02", "过期");

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 
	 * @param code
	 * @param name
	 */
	private ShelfLifeWarningType(String code, String name) {
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
	 * 根据代码获取枚举对象
	 * 
	 * @param code
	 * @return
	 */
	public final static ShelfLifeWarningType getShelfLifeWarningType(String code) {
		if (Closeto.code.equals(code)) {
			return Closeto;
		} else if ( Overdue.code.equals(code)) {
			return Overdue;
		} 
		return null;
	}

}
