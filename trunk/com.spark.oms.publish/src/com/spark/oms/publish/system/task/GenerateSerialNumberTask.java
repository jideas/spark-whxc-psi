package com.spark.oms.publish.system.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * 生成指定的序列号
 * 
 */
public class GenerateSerialNumberTask extends SimpleTask {

	private String type;

	private String prefix;

	private long result;

	public GenerateSerialNumberTask(String type, String prefix) {
		super();
		this.type = type;
		this.prefix = prefix;
	}

	public String getType() {
		return type;
	}

	public String getPrefix() {
		return prefix;
	}

	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}
}