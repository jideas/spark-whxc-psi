/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-23       Ī��        
 * ============================================================*/

package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.sales.intf.entity.SaleCancel;

/**
 * <p>�����˻�����״̬�޸�TASK</p>
 */

public class ModifySaleCancelStatusTask extends Task<TaskEnum> {

	private SaleCancel entity;
	private boolean done;

	public void setEntity(SaleCancel entity) {
		this.entity = entity;
	}

	public SaleCancel getEntity() {
		return entity;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isDone() {
		return done;
	}
}
