/**
 * 
 */
package com.spark.psi.report.task;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * ���������������һ������
 */
public class QueueAddTask extends SimpleTask {

	public QueueAddTask(Event event) {
		this.event = event;
	}

	private Event event;

	private int count;

	/**
	 * @return ��Ӧ����
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
