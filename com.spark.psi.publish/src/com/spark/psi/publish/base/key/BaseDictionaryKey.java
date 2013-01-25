package com.spark.psi.publish.base.key;

import com.spark.psi.publish.BaseDictionaryEnum;

/**
 * 基础模块数据库字典查询key
 */
public class BaseDictionaryKey {

	private BaseDictionaryEnum enumValue;
	private String code;

	public BaseDictionaryKey(BaseDictionaryEnum enumValue) {
		this.enumValue = enumValue;
	}

	public BaseDictionaryKey(BaseDictionaryEnum enumValue, String code) {
		this.enumValue = enumValue;
		this.code = code;
	}

	public BaseDictionaryEnum getEnumValue() {
		return enumValue;
	}

	public String getCode() {
		return code;
	}

}
