package com.spark.psi.publish;

public enum BaseDictionaryEnum {
	/**
	 * 供应商类型
	 */
	SupplierType("Config_SupplierType"),
	/**
	 * 供应商合作方式
	 */
	SupplierCooperation("Config_SupplierCooperation"),
	/**
	 * 收款原因
	 */
	ReceiptReason("Config_ReceiptReason"),
	/**
	 * 付款原因
	 */
	PayReason("Config_PayReason"),
	/**
	 * 客户信用策略
	 */
	CustomerPricePolicy("Config_CustomerPricePolicy"),
	/**
	 * 客户类型
	 */
	CustomerType("Config_CustomerType"), 
	/**
	 * 
	 */
	DeliveryHour("Config_DeliveryHour"),
	;

	private String xmlName;

	private BaseDictionaryEnum(String xmlName) {
		this.xmlName = xmlName;
	}

	public String getXmlName() {
		return this.xmlName;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}
}
