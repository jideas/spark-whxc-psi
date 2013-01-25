package com.spark.order.intf.task;

import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.type.BillsEnum;

/**
 * <p>退回Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-11
 */
public class RebutTask extends FlowTask{
	public RebutTask(BillsEnum b) {
		super(b);
	}
	/**
	 * 退回原因
	 */
	public String cause;
	/**
	 * 订单信息
	 */
	public OrderInfo info;
}
