/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.finance.pay.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       ������        
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.finance.pay.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       ������        
 * ============================================================*/

package com.spark.psi.account.intf.task.pay;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.account.intf.entity.pay.PaymentEntity;

/**
 * <p>�����¼task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ������
 * @version 2011-11-10
 */

public class PayRecordTask extends Task<PayRecordTask.Method>{

	public PaymentEntity entity;
	public GUID recid;

	/**
	     * <p>TODO ������</p>
	     *
	     * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	    
	     *
	     * @author ������
	     * @version 2011-11-10
	     */

	public enum Method{
		ADD,
		MODIFY,
		DELETE

	}

}
