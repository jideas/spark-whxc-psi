/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.task.sale
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-19       Ī��        
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.task.sale
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-19       Ī��        
 * ============================================================*/

package com.spark.order.sales.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.sales.intf.entity.SaleOrder;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-19
 */

public class SaleOrderTask extends Task<TaskEnum> implements ITaskResult{

	public com.spark.order.sales.intf.entity.SaleOrderInfo entity;
	public SaleOrder order;//�½����۶���ר��
	public GUID recid;
	
	private boolean succeed = true;
	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	public boolean isSucceed() {
		return succeed;
	}
	public int lenght() {
		return succeed?1:0;
	}
}
