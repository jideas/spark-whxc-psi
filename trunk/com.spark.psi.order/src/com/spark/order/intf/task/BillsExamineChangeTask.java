package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.facade.BillsConstant;

/**
 * <p>�������̱仯���ݴ���</p>
 *<link>com.spark.bills.service.BillsInfoService</link>
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-12-1
 */
public class BillsExamineChangeTask extends SimpleTask implements ITaskResult{
	private final OrderEnum billsEnum;//���ֶ�������
	private final GUID tenantsGuid;//�⻧���
	private int lenght;//��������
	private String cause = BillsConstant.FLOW_CAUSE;//ԭ��

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
