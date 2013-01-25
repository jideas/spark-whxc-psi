package com.spark.order.service;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.OrderTaskFather;
import com.spark.order.intf.OrderEnum;

/**
 * <p>��ֹ������ִ�ж���(������ֹ״̬�������־��ʾ����)</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-4
 */
public class StopOrderTask2 extends OrderTaskFather<StopOrderTask2.Method> {
	public enum Method{
		Stop,
		Execut;
	}
	private final OrderEnum orderEnum;
	private final GUID id;
	private final String cause;

	/**
	 * @param orderEnum
	 * @param id
	 * @param oldstatus
	 * @param isStop true ��ʾ��ֹ
	 */
	public StopOrderTask2(OrderEnum orderEnum, GUID id, String oldstatus) {
		super();
		this.orderEnum = orderEnum;
		this.id = id;
		this.cause = null;
	}
	
	/**
	 * @param orderEnum
	 * @param id
	 * @param oldstatus
	 * @param isStop true ��ʾ��ֹ
	 * @param cause
	 */
	public StopOrderTask2(OrderEnum orderEnum, GUID id, String oldstatus,
			String cause) {
		super();
		this.orderEnum = orderEnum;
		this.id = id;
		this.cause = cause;
	}

	public String getCause() {
		return cause;
	}
	public OrderEnum getOrderEnum() {
		return orderEnum;
	}
	public GUID getId() {
		return id;
	}
	@Override
	protected void setLenght(int lenght) {
		this.lenght = lenght;
	}

	@Override
	protected void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
}
