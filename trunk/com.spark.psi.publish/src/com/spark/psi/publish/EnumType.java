package com.spark.psi.publish;

public enum EnumType {
	SupplierType("SupplierType","供应商类型"),
	IndustyType("IndustyType", "所属行业"), //
	CustomerDevelopeType("CustomerDevelopeType", "客户来源"), //
	SupplierDevelopeType("SupplierDevelopeType", "供应商来源"), //
	ScaleType("ScaleType", "企业规模"), //
	Area("Area", "行政区划"), //
	@Deprecated
	Province("Province", "省"), //
	@Deprecated
	City("City", "市"), //
	@Deprecated
	District("District", "区县"), //
	QueryTerm("QueryTerm", "查询时期范围"), //
	InvoiceType("InvoiceType", "发票类型"), //
	ReceiptType("ReceiptType", "收款类型"), //
	PaymentType("PaymentType", "付款类型"), //
	Sex("Sex", "性别"), //
	DealingsType("DealingsType", "往来类型"), //
	ReceiptCause("ReceiptCause", "付款原因"),
	PaymentCause("PaymentCause", "收款原因"),
	DealingsWay("DealingsWay", "收付款方式"),
	StoreStrategy ("StoreStrategy","库存策略");
	
	

	/**
	 * 是否允许个性化扩展
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
