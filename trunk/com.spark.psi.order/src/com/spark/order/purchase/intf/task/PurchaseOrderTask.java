/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       Ī��        
 * ============================================================*/

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
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;

/**
 * <p>TODO ������</p>
 *<link>com.spark.bills.service.BuyOrderService</link>
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-10
 */

public class PurchaseOrderTask extends Task<TaskEnum> implements ITaskResult{
	public int lenght;
	public com.spark.order.purchase.intf.entity.PurchaseOrderInfo entity;
	/**
	 * �ɹ�������ϸ
	 */
	public List<PurchaseOrderItem> dets;
	public GUID recid;
	public boolean isSucceed() {
		return 1 == lenght;
	}
	public int lenght() {
		return lenght;
	}
}
