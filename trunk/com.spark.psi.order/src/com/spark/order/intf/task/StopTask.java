package com.spark.order.intf.task;

import com.spark.order.intf.type.BillsEnum;

/**
 * <p>��ֹTask</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-11
 */
public class StopTask extends FlowTask{
	public StopTask(BillsEnum b) {
		super(b);
	}
	
	/**
	 * ��ֹԭ��
	 */
	public String cause;
	
	/**
	 * �Ƿ���ֹ
	 */
	public boolean isStoped;
}
