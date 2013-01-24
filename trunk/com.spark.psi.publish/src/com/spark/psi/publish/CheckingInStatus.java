package com.spark.psi.publish;

/**
 * 入库状态
 */
public enum CheckingInStatus {

	None("01", "未入库"), //
	Part("02", "部分入库"), //
	Finish("03", "全部入库"), //
	Stop("04", "已中止");

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
	private CheckingInStatus(String code, String name) {
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
	public final static CheckingInStatus getCheckingInType(String code) {
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
