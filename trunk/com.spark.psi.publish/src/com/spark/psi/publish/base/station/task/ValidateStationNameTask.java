package com.spark.psi.publish.base.station.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

public class ValidateStationNameTask extends Task<ValidateStationNameTask.Method> {

	public enum Method {
		ShortName, FullName;
	}

	/**
	 * Ãû³Æ
	 */
	private String name;

	// id
	private GUID id;

	/**
	 * ÊÇ·ñ´æÔÚ
	 */
	private boolean exist;

	public ValidateStationNameTask(GUID id, String text) {
		this.name = text;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}
}
