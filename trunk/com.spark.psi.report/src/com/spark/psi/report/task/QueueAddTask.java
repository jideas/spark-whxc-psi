/**
 * 
 */
package com.spark.psi.report.task;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * 向任务队列中新增一个任务
 */
public class QueueAddTask extends SimpleTask {

	public QueueAddTask(Event event) {
		this.event = event;
	}

	private Event event;

	private int count;

	/**
	 * @return 响应条数
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
}
