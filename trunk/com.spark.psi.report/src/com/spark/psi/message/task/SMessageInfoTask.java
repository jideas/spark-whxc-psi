/**
 * 
 */
package com.spark.psi.message.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.message.entity.SMessageInfo;

/**
 *
 */
public class SMessageInfoTask extends SimpleTask{

	private SMessageInfo info;

	public SMessageInfoTask(SMessageInfo info){
		this.info = info;
	}

	public SMessageInfo getInfo(){
		return this.info;
	}
}
