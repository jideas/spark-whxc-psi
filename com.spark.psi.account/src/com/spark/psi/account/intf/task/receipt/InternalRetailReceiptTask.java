/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.finance.receipt.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       向中秋        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.finance.receipt.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       向中秋        
 * ============================================================*/

package com.spark.psi.account.intf.task.receipt;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.account.intf.entity.receipt.RetailReceipt;

/**
 * <p>零售收款task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 向中秋
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
