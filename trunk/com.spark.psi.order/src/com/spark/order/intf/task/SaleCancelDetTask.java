/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       Ī��        
 * ============================================================*/

package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.sales.intf.entity.SaleCancelItem;

/**
 * <p>�����˻���ϸTask</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author �����
 * @version 2011-11-10
 */

public class SaleCancelDetTask extends Task<TaskEnum> {

	private SaleCancelItem entity;
	
	private GUID billsGuid;

	public void setEntity(SaleCancelItem entity) {
		this.entity = entity;
	}

	public SaleCancelItem getEntity() {
		return entity;
	}

	public void setBillsGuid(GUID billsGuid) {
		this.billsGuid = billsGuid;
	}

	public GUID getBillsGuid() {
		return billsGuid;
	}
	
}
