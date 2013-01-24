package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;

@StructClass
public class EnumEntityImpl implements EnumEntity {

	protected String code;

	protected String name;

	protected EnumType type;

	public EnumEntityImpl(EnumType type, String code, String name) {
		super();
		this.code = code;
		this.name = name;
		this.type = type;
	}

	public EnumEntityImpl(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public EnumType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(EnumType type) {
		this.type = type;
	}

}
