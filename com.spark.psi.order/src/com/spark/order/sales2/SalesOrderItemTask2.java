/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.order.sales.intf
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-5     modi 
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.order.sales.intf
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-5     modi 
 * ============================================================*/

package com.spark.order.sales2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.OrderTaskFather;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */

public class SalesOrderItemTask2 extends OrderTaskFather<SalesOrderItemTask2.Method>{

	public com.spark.order.sales2.SalesOrderItem2 entity;
	public GUID recid;
	public GUID orderId;
	public enum Method {
		/**
		 * ��� 
		 */
		ADD, 
		/**
		 * �޸�
		 */
		MODIFY, 
		/**
		 * ɾ��
		 */
		DELETE, 
		/**
		 * ͨ����������idɾ��
		 */
		DELETE_Master
	}
	@Override
	protected void setLenght(int lenght) {
		this.lenght = lenght;
	}
	@Override
	protected void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
}
