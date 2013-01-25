/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       Ī��        
 * ============================================================*/

package com.spark.order.purchase.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;

/**
 * <p>�ɹ��˻���ϸTask</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author �����
 * @version 2011-11-10
 */

public class PurchaseCancelDetTask extends Task<TaskEnum> {
	
	private PurchaseCancelItem entity;
	
	private GUID billsGuid;

	public void setEntity(PurchaseCancelItem entity) {
		this.entity = entity;
	}

	public PurchaseCancelItem getEntity() {
		return entity;
	}

	public void setBillsGuid(GUID billsGuid) {
		this.billsGuid = billsGuid;
	}

	public GUID getBillsGuid() {
		return billsGuid;
	}

}
