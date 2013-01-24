/**
 * 
 */
package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;

/**
 * 到时间事件
 */
public class TimerEvent extends Event {

	public TimerEvent(boolean isEndOfToday, boolean isEndOfThisMonth) {
		this.isEndOfToday = isEndOfToday;
		this.isEndOfThisMonth = isEndOfThisMonth;
	}
	public TimerEvent() {
	}
	private boolean isEndOfToday = false;
	private boolean isEndOfThisMonth = false;

	/**
	 * @return the isEndOfToday
	 */
	public boolean isEndOfToday() {
		return isEndOfToday;
	}

	/**
	 * @param isEndOfToday
	 *            the isEndOfToday to set
	 */
	public void setEndOfToday(boolean isEndOfToday) {
		this.isEndOfToday = isEndOfToday;
	}

	/**
	 * @return the isEndOfThisMonth
	 */
	public boolean isEndOfThisMonth() {
		return isEndOfThisMonth;
	}

	/**
	 * @param isEndOfThisMonth
	 *            the isEndOfThisMonth to set
	 */
	public void setEndOfThisMonth(boolean isEndOfThisMonth) {
		this.isEndOfThisMonth = isEndOfThisMonth;
	}
}
