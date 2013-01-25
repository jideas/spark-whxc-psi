package com.spark.order.service;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.OrderSimpleTaskFather;
import com.spark.order.intf.OrderEnum;

/**
 * <p>订单类型修改Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-4
 */
public class OrderStatusTask2 extends OrderSimpleTaskFather {
	private final OrderEnum orderEnum;
	private final GUID id;
	private final String oldstatus;
	private final String newstatus;
	private final String cause;
	private GUID deptId;
	private GUID examineDeptId;
	/**
	 * @param orderEnum
	 * @param id
	 * @param oldstatus
	 * @param newstatus
	 */
	public OrderStatusTask2(OrderEnum orderEnum, GUID id, String oldstatus,
			String newstatus) {
		super();
		this.orderEnum = orderEnum;
		this.id = id;
		this.oldstatus = oldstatus;
		this.newstatus = newstatus;
		this.cause = null;
	}
	
	/**
	 * @param orderEnum
	 * @param id
	 * @param oldstatus
	 * @param newstatus
	 * @param cause
	 */
	public OrderStatusTask2(OrderEnum orderEnum, GUID id, String oldstatus,
			String newstatus, String cause) {
		super();
		this.orderEnum = orderEnum;
		this.id = id;
		this.oldstatus = oldstatus;
		this.newstatus = newstatus;
		this.cause = cause;
	}
	
	public GUID getDeptId() {
		return deptId;
	}

	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
	}

	public GUID getExamineDeptId() {
		return examineDeptId;
	}

	public void setExamineDeptId(GUID examineDeptId) {
		this.examineDeptId = examineDeptId;
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
	public String getOldstatus() {
		return oldstatus;
	}
	public String getNewstatus() {
		return newstatus;
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
