package com.spark.oms.publish;


public enum EnumType {
	
	IndustyType("IndustyType", "所属行业"), //
	ScaleType("ScaleType", "企业规模"), //
	Area("Area", "行政区划"), //
	Sex("Sex", "性别"), //
	QueryTerm("QueryTerm", "查询时期范围"),
	ConnectType("ConnectType", "连接类型"),
	ServiceProviderType("ServiceProviderType", "服务商类型");
	

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
