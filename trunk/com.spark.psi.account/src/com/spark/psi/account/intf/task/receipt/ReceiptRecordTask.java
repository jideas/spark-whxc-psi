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
import com.spark.psi.account.intf.entity.receipt.ReceiptEntity;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 向中秋
 * @version 2011-11-10
 */

public class ReceiptRecordTask extends Task<ReceiptRecordTask.Method>{

	public ReceiptEntity entity;
	public GUID recid;

	/**
	     * <p>枚举</p>
	     *
	     * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	    
	     *
	     * @author 向中秋
	     * @version 2011-11-10
	     */

	public enum Method{
		ADD,
		MODIFY,
		DELETE,
		/**
		 * 零售交款
		 */
		Retail_account

	}

}
