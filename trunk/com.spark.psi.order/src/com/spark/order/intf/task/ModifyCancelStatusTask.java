/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-15       Ī��        
 * ============================================================*/

package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancel;

/**
 * <p>�����˻���״̬Task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author �����
 * @version 2011-11-15
 */

public class ModifyCancelStatusTask extends Task<TaskEnum> {

	private PurchaseCancel entity;
	private boolean done;

	public void setEntity(PurchaseCancel entity) {
		this.entity = entity;
	}

	public PurchaseCancel getEntity() {
		return entity;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isDone() {
		return done;
	}
	
}
