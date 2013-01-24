package com.spark.common.components.table.edit;

/**
 * 名称/值对象
 */
public final class SNameValue {

	private String name;

	private String value;

	public SNameValue(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
