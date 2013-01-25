package com.spark.psi.publish;

/**
 * 
 * <p>
 * 付款类型
 * </p>
 */
public enum PaymentType {
	/**
	 * 采购货款
	 */
	PAY_CGFK("01", "采购货款"),
	/**
	 * 销售退款
	 */
	PAY_XSTK("02", "销售退款"),
	/**
	 * 零售退货
	 */
	PAY_LSTK("03", "零售退款"),
	/**
	 * 零采付款
	 */
	PAY_LCFK("04", "零采付款"),
	/**
	 * 联营结算
	 */
	PAY_JOINTVENTRUE("05", "联营结算");

	private String code, name;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private PaymentType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static PaymentType getPaymentType(String code) {
		if (PaymentType.PAY_CGFK.getCode().equals(code)) {
			return PaymentType.PAY_CGFK;
		} else if (PaymentType.PAY_XSTK.getCode().equals(code)) {
			return PaymentType.PAY_XSTK;
		} else if (PaymentType.PAY_LCFK.getCode().equals(code)) {
			return PaymentType.PAY_LCFK;
		} else if (PaymentType.PAY_LSTK.getCode().equals(code)) {
			return PaymentType.PAY_LSTK;
		}
		return null;
	}
}
