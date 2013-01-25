package com.spark.psi.publish;

/**
 * 出库状态
 */
public enum CheckingOutStatus {

	None("01", "未出库"), //
	Part("02", "部分出库"), //
	Finish("03", "全部出库"), //
	Stop("04", "中止");

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
	private CheckingOutStatus(String code, String name) {
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
	public final static CheckingOutStatus getCheckingInType(String code) {
		if (None.code.equals(code)) {
			return None;
		} else if (Part.code.equals(code)) {
			return Part;
		} else if (Finish.code.equals(code)) {
			return Finish;
		} else if (Stop.code.equals(code)) {
			return Stop;
		}
		return null;
	}

}
