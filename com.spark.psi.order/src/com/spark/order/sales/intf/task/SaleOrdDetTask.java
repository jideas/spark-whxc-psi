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
import com.spark.order.intf.type.TaskEnum;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-19
 */

public class SaleOrdDetTask extends Task<TaskEnum> {

	public com.spark.order.sales.intf.entity.SaleOrderItem entity;
	public GUID recid;
	public GUID billsGuid;//���ݶ����������ϸר��
}
