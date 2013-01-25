package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.facade.BillsConstant;

/**
 * <p>单据流程变化数据处理</p>
 *<link>com.spark.bills.service.BillsInfoService</link>
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-12-1
 */
public class BillsExamineChangeTask extends SimpleTask implements ITaskResult{
	private final OrderEnum billsEnum;//四种订单类型
	private final GUID tenantsGuid;//租户编号
	private int lenght;//返回条数
	private String cause = BillsConstant.FLOW_CAUSE;//原因

	public BillsExamineChangeTask(OrderEnum billsEnum, GUID tenantsGuid) {
		this.billsEnum = billsEnum;
		this.tenantsGuid = tenantsGuid;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	
	public OrderEnum getBillsEnum() {
		return billsEnum;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public boolean isSucceed() {
		return lenght >= 0;
	}

	public int lenght() {
		return lenght;
	}
}
