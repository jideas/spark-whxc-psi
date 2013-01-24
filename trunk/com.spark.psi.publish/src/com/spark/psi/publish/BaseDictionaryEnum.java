package com.spark.psi.publish;

public enum BaseDictionaryEnum {
	/**
	 * ��Ӧ������
	 */
	SupplierType("Config_SupplierType"),
	/**
	 * ��Ӧ�̺�����ʽ
	 */
	SupplierCooperation("Config_SupplierCooperation"),
	/**
	 * �տ�ԭ��
	 */
	ReceiptReason("Config_ReceiptReason"),
	/**
	 * ����ԭ��
	 */
	PayReason("Config_PayReason"),
	/**
	 * �ͻ����ò���
	 */
	CustomerPricePolicy("Config_CustomerPricePolicy"),
	/**
	 * �ͻ�����
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
