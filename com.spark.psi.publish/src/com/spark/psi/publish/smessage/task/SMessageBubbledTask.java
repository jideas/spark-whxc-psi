/**
 * 
 */
package com.spark.psi.publish.smessage.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 消息已浮出后更改标志
 */
public class SMessageBubbledTask extends SimpleTask{
	private GUID id;

	/**
     * @return the id
     */
    public GUID getId(){
    	return id;
    }

	/**
     * @param id the id to set
     */
    public void setId(GUID id){
    	this.id = id;
    }
	
}
