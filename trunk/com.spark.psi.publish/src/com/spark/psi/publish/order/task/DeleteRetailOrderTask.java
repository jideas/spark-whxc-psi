/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.order.key.GetOrderListKey.OrderType;

/**
 * <p>���������ͻ���</p>
 *


 *
 
 * @version 2012-3-6
 */

public class DeleteRetailOrderTask extends SimpleTask{
	
	private final GUID id;
	private final OrderType orderType;
	
	public DeleteRetailOrderTask(final GUID id, final OrderType orderType){
		this.id = id;
		this.orderType = orderType;
	}

	public GUID getId() {
		return id;
	}
	
	public OrderType getOrderType() {
		return orderType;
	}
}
