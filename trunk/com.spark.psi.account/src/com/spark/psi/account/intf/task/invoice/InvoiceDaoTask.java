/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.finance.invoice.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.task.invoice;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.account.intf.entity.invoice.Invoice;

/**
 * <p>Invoice Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

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
