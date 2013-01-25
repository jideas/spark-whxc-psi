/**
 * 
 */
package com.spark.psi.message.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.message.entity.SMessageMapping;

/**
 *
 */
public class SMessageMappingTask extends SimpleTask{

	private SMessageMapping mapping;

	public SMessageMappingTask(SMessageMapping mapping){
		this.mapping = mapping;
	}

	/**
	 * @return
	 */
	public SMessageMapping getMapping(){
		return this.mapping;
	}

}
