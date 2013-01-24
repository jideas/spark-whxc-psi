/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.finance.invoice.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.task.invoice;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.account.intf.entity.invoice.Invoice;

/**
 * <p>Invoice Task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2011-11-28
 */

public class InvoiceDaoTask extends Task<InvoiceDaoTask.Method> {
	
	public enum Method
	{
		INSERT,
		MODIFY,
		MODIFYstatus
	}

	private Invoice invoice;
	private boolean invalided;
	
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public boolean isInvalided() {
		return invalided;
	}
	public void setInvalided(boolean invalided) {
		this.invalided = invalided;
	}
}
