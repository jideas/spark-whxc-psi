/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.order.purchase.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-1     modi 
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.order.purchase.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-1     modi 
 * ============================================================*/

package com.spark.order.purchase.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-1
 */

public class PurchaseGoodsTask2 extends Task<PurchaseGoodsTask2.Method> {

	public com.spark.order.purchase.intf.PurchaseGoods2 entity;
	public GUID recid;

	/**
	 * @param recid
	 */
	public PurchaseGoodsTask2(GUID recid) {
		super();
		this.recid = recid;
	}
	

	/**
	 * 
	 */
	public PurchaseGoodsTask2() {
		super();
	}


	/**
	 * <p>TODO ������</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-4-1
	 */

	public enum Method {
		ADD, MODIFY, DELETE

	}

}
