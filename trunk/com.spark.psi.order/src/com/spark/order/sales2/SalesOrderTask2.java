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

public class SalesOrderTask2 extends OrderTaskFather<SalesOrderTask2.Method> {

	public com.spark.order.sales2.SalesOrderInfo2 entity;
	public GUID recid;

	/**
	 * <p>TODO ������</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-4-5
	 */

	public enum Method {
		ADD, MODIFY, DELETE
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
