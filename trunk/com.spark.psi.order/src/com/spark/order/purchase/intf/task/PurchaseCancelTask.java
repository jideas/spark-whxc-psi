/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       Ī��        
 * ============================================================*/

package com.spark.order.purchase.intf.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.order.intf.task.ITaskResult;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;

/**
 * <p>�ɹ��˻�Task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author �����
 * @version 2011-11-10
 */

public class PurchaseCancelTask extends Task<TaskEnum> implements ITaskResult{
	public int lenght;
	private PurchaseCancel entity;
	private List<PurchaseCancelItem> list;

	public void setEntity(PurchaseCancel entity) {
		this.entity = entity;
	}

	public PurchaseCancel getEntity() {
		return entity;
	}

	public void setList(List<PurchaseCancelItem> list) {
		this.list = list;
	}

	public List<PurchaseCancelItem> getList() {
		return list;
	}

	public boolean isSucceed() {
		return 1 == lenght;
	}

	public int lenght() {
		return lenght;
	}
}
