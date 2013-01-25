package com.spark.psi.publish;

public enum EnumType {
	SupplierType("SupplierType","��Ӧ������"),
	IndustyType("IndustyType", "������ҵ"), //
	CustomerDevelopeType("CustomerDevelopeType", "�ͻ���Դ"), //
	SupplierDevelopeType("SupplierDevelopeType", "��Ӧ����Դ"), //
	ScaleType("ScaleType", "��ҵ��ģ"), //
	Area("Area", "��������"), //
	@Deprecated
	Province("Province", "ʡ"), //
	@Deprecated
	City("City", "��"), //
	@Deprecated
	District("District", "����"), //
	QueryTerm("QueryTerm", "��ѯʱ�ڷ�Χ"), //
	InvoiceType("InvoiceType", "��Ʊ����"), //
	ReceiptType("ReceiptType", "�տ�����"), //
	PaymentType("PaymentType", "��������"), //
	Sex("Sex", "�Ա�"), //
	DealingsType("DealingsType", "��������"), //
	ReceiptCause("ReceiptCause", "����ԭ��"),
	PaymentCause("PaymentCause", "�տ�ԭ��"),
	DealingsWay("DealingsWay", "�ո��ʽ"),
	StoreStrategy ("StoreStrategy","������");
	
	

	/**
	 * �Ƿ�������Ի���չ
	 */
	private boolean customizeEnabled;

	/**
	 * 
	 */
	private String typeName;

	/**
	 * 
	 */
	private String typeTitle;

	/*
	 * 
	 */
	private EnumType(String typeName, String typeTitle) {
		this(typeName, typeTitle, false);
	}

	/*
	 * 
	 */
	private EnumType(String typeName, String typeTitle, boolean customizeEnabled) {
		this.typeName = typeName;
		this.typeTitle = typeTitle;
		this.customizeEnabled = customizeEnabled;
	}

	/**
	 * 
	 * @return
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * 
	 * @return
	 */
	public String getTypeTitle() {
		return this.typeTitle;
	}

	/**
	 * @return the customizeEnabled
	 */
	public boolean isCustomizeEnabled() {
		return customizeEnabled;
	}

	public static EnumType getEnumTypeByCode(String name) {
		for (EnumType type : EnumType.values()) {
			if (type.getTypeName().equals(name)) {
				return type;
			}
		}
		return null;
	}

}
