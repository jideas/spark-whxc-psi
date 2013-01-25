package com.spark.psi.publish;

/**
 * 出库类型
 */
public enum CheckingOutType {

	Sales("01", "销售出库"), //
	Return("02", "采购退货"), //
	RealGoods("03", "成品出库"), //
	Kit("04", "其他出库"), //
	Mateiral_Take("05", "领料出库"), //
	Mateiral_Return("06", "退料出库"), //
	Joint("07", "联营出库"), //
	Gift("08", "赠品出库");

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	public boolean isMaterialTakeOrReturn() {
		return this == Mateiral_Take || this == Mateiral_Return || this == RealGoods;
	}

	public boolean isRealGoods() {
		return this == RealGoods;
	}

	/**
	 * 
	 * @param code
	 * @param name
	 */
	private CheckingOutType(String code, String name) {
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
	public final static CheckingOutType getCheckingOutType(String code) {
		if (Sales.code.equals(code)) {
			return Sales;
		} else if (Return.code.equals(code)) {
			return Return;
		} else if (RealGoods.code.equals(code)) {
			return RealGoods;
		} else if (Kit.code.equals(code)) {
			return Kit;
		} else if (Mateiral_Return.code.equals(code)) {
			return Mateiral_Return;
		} else if (Mateiral_Take.code.equals(code)) {
			return Mateiral_Take;
		} else if (Joint.code.equals(code)) {
			return Joint;
		} else if (Gift.code.equals(code)) {
			return Gift;
		}
		return null;
	}

}
