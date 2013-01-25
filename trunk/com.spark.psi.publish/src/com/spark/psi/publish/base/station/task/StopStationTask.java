package com.spark.psi.publish.base.station.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class StopStationTask extends SimpleTask{

	private GUID id;
	private boolean isStop;
	public GUID getId() {
		return id;
	}

	public StopStationTask(GUID id,boolean isStop) {
		this.id = id;
		this.isStop = isStop;
	}

	public boolean isStop() {
		return isStop;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
	
}
