/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.finance.receipt.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       ������        
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.finance.receipt.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       ������        
 * ============================================================*/

package com.spark.psi.account.intf.task.receipt;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.account.intf.entity.receipt.ReceiptEntity;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ������
 * @version 2011-11-10
 */

public class ReceiptRecordTask extends Task<ReceiptRecordTask.Method>{

	public ReceiptEntity entity;
	public GUID recid;

	/**
	     * <p>ö��</p>
	     *
	     * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	    
	     *
	     * @author ������
	     * @version 2011-11-10
	     */

	public enum Method{
		ADD,
		MODIFY,
		DELETE,
		/**
		 * ���۽���
		 */
		Retail_account

	}

}
