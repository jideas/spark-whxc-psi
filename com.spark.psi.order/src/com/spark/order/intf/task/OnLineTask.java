package com.spark.order.intf.task;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.BillsEnum;

/**
 * <p>���϶���Task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-30
 */
@StructClass
public class OnLineTask extends Task<OnLineTask.OnLineEnum>{
	public enum OnLineEnum{
		STOP
	}
	@StructField
	private final BillsEnum order;//��ǰģ��
	@StructField
	private final GUID recid;
	@StructField
	private final GUID tenantsGuid;
	@StructField
	private String cause;//��ֹԭ��
	/**
	 * @param order ��ǰģ��ö��
	 * @param recid
	 * @param tenantsGuid
	 */
	public OnLineTask(BillsEnum order, GUID recid, GUID tenantsGuid) {
		this.order = order;
		this.recid = recid;
		this.tenantsGuid = tenantsGuid;
	}
	
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	
	/**
	 * 
	 * @return BillsEnum
	 */
	public BillsEnum getOrder() {
		return order;
	}
	/**
	 * @return the recid
	 */
	public GUID getRecid() {
		return recid;
	}
	/**
	 * @return the tenantsGuid
	 */
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
}
