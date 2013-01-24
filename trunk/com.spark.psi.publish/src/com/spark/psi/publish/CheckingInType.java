package com.spark.psi.publish;

/**
 * 入库类型
 */
public enum CheckingInType {

	/**
	 * 采购入库
	 */
	Purchase("01", "采购入库"), //
	/**
	 * 零星采购
	 */
	Irregular("02", "零星采购"), //
	/**
	 * 销售退货
	 */
	Return("03", "销售退货"), //
	/**
	 * 零售退货
	 */
	Adjustment("04", "调整入库"),
	/**
	 * 其他入库
	 */
	Kit("05", "其他入库"),
	/**
	 * 成品入库
	 */
	RealGoods("06", "成品入库"),
	/**
	 * 联营入库
	 */
	Joint("07", "联营入库"),
	//
	Gift("08", "赠品入库");

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
	private CheckingInType(String code, String name) {
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
	public final static CheckingInType getCheckingInType(String code) {
		if (Purchase.code.equals(code)) {
			return Purchase;
		} else if (Return.code.equals(code)) {
			return Return;
		} else if (Irregular.code.equals(code)) {
			return Irregular;
		} else if (Kit.code.equals(code)) {
			return Kit;
		} else if (Adjustment.code.equals(code)) {
			return Adjustment;
		} else if (RealGoods.code.equals(code)) {
			return RealGoods;
		} else if (Joint.code.equals(code)) {
			return Joint;
		} else if (Gift.code.equals(code)) {
			return Gift;
		}
		return null;
	}

}
