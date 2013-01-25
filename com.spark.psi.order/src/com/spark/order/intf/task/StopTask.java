package com.spark.order.intf.task;

import com.spark.order.intf.type.BillsEnum;

/**
 * <p>中止Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-11
 */
public class StopTask extends FlowTask{
	public StopTask(BillsEnum b) {
		super(b);
	}
	
	/**
	 * 中止原因
	 */
	public String cause;
	
	/**
	 * 是否中止
	 */
	public boolean isStoped;
}
