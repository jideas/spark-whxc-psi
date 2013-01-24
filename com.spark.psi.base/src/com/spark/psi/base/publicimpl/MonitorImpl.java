package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.index.entity.Monitor;

public class MonitorImpl implements Monitor {
	
	private GUID recid;
	private String monitors;
	
	public GUID getRecid() {
		return recid;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public String getMonitors() {
		return monitors;
	}
	public void setMonitors(String monitors) {
		this.monitors = monitors;
	}
	
	

}
