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
import com.spark.psi.account.intf.entity.receipt.RetailReceipt;

/**
 * <p>�����տ�task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ������
 * @version 2011-11-10
 */

public class InternalRetailReceiptTask extends Task<InternalRetailReceiptTask.Method>{

	private RetailReceipt entity;
	private GUID recid;

	public RetailReceipt getEntity() {
		return entity;
	}

	public void setEntity(RetailReceipt entity) {
		this.entity = entity;
	}

	public GUID getRecid() {
		return recid;
	}

	public void setRecid(GUID recid) {
		this.recid = recid;
	}

	public enum Method{
		ADD,
		MODIFY,
		Confirm,
		DELETE

	}

}
