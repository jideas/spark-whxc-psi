package com.spark.oms.publish;


public enum EnumType {
	
	IndustyType("IndustyType", "������ҵ"), //
	ScaleType("ScaleType", "��ҵ��ģ"), //
	Area("Area", "��������"), //
	Sex("Sex", "�Ա�"), //
	QueryTerm("QueryTerm", "��ѯʱ�ڷ�Χ"),
	ConnectType("ConnectType", "��������"),
	ServiceProviderType("ServiceProviderType", "����������");
	

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
