package com.spark.psi.base.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spark.psi.publish.BaseDictionaryEnum;
import com.spark.psi.publish.EnumEntity;

public class BaseDictionaryConfig {

	private final String xmlName;
	protected List<EnumEntity> typeList;
	protected Map<String, EnumEntity> map;

	public BaseDictionaryConfig(BaseDictionaryEnum enumValue) {
		this.xmlName = enumValue.getXmlName();
		init();
	}

	protected void init(String xmlName) {
		map = new HashMap<String, EnumEntity>();
		typeList = new ArrayList<EnumEntity>();
		CommonConfigUtil.parseXml(typeList, map, xmlName);
	}

	public EnumEntity getType(String code) {
		if (null == map) {
			init();
		}
		return map.get(code);
	}

	private void init() {
		map = new HashMap<String, EnumEntity>();
		typeList = new ArrayList<EnumEntity>();
		CommonConfigUtil.parseXml(typeList, map, xmlName);
	}

	public List<EnumEntity> getTypeList() {
		if (null == typeList) {
			init();
		}
		return typeList;
	}
}
