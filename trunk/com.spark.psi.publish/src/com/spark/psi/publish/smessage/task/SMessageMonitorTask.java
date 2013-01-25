package com.spark.psi.publish.smessage.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.SMessageType;

public class SMessageMonitorTask extends SimpleTask{

	public SMessageMonitorTask(SMessageType type, boolean showMonitor){
		this.type = type;
		this.showMonitor = showMonitor;
	}

	private SMessageType type;
	private boolean showMonitor;

	/**
	 * @return the showMonitor
	 */
	public boolean isShowMonitor(){
		return showMonitor;
	}

	/**
	 * @return the type
	 */
	public SMessageType getType(){
		return type;
	}
}
