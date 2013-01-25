package com.spark.order.intf.task;

import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.type.BillsEnum;

/**
 * <p>�˻�Task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-11
 */
public class RebutTask extends FlowTask{
	public RebutTask(BillsEnum b) {
		super(b);
	}
	/**
	 * �˻�ԭ��
	 */
	public String cause;
	/**
	 * ������Ϣ
	 */
	public OrderInfo info;
}
