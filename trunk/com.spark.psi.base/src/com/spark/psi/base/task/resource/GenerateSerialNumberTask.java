package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 生成指定的序列号
 * 
 */
public final class GenerateSerialNumberTask extends SimpleTask {

	private String type;

	private String prefix;

	private long result;

	public GenerateSerialNumberTask( String type, String prefix) {
		super();
		this.type = type;
		this.prefix = prefix;
	}

	public String getType() {
		return this.type;
	}

	/**
	 * @return the result
	 */
	public long getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(long result) {
		this.result = result;
	} 

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

}
