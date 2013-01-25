/**
 * 
 */
package com.spark.psi.report.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 删除队列中的一个任务
 */
public class QueueDeleteTask extends SimpleTask {

	private GUID recid;
	
	private int count ;
	
	public QueueDeleteTask(GUID recid) {
		this.recid = recid;
	}
	

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the recid
	 */
	public GUID getRecid() {
		return recid;
	}

	/**
	 * @param recid the recid to set
	 */
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	
}
