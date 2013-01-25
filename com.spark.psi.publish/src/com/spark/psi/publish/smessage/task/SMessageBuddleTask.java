package com.spark.psi.publish.smessage.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.SMessageType;

public class SMessageBuddleTask extends SimpleTask{

	public SMessageBuddleTask(SMessageType type, boolean isBubbling){
		this.type = type;
		this.isBubbling = isBubbling;
	}

	private SMessageType type;
	private boolean isBubbling;

	/**
	 * @return the type
	 */
	public SMessageType getType(){
		return type;
	}

	/**
	 * @return the isBubbling
	 */
	public boolean isBubbling(){
		return isBubbling;
	}
}
