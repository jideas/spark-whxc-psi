package com.spark.psi.publish.base.index.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

public class MonitorTask extends Task<MonitorTask.Method>{
	
	public enum Method {
		ADD, MODIFY
	}
	
	private GUID recid;
	private String monitors;
	
	public MonitorTask() {
	}
	
	
	public MonitorTask(GUID recid, String monitors) {
		this.recid = recid;
		this.monitors = monitors;
	}
	
	public GUID getRecid() {
		return recid;
	}
	public String getMonitors() {
		return monitors;
	}
}
