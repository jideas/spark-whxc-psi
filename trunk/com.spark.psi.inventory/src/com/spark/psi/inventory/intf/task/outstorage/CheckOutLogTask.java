/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.task.instorage
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-14       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.outstorage;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.intf.entity.outstorage.CheckOutLog;

/**
 * <p>确认出库记录Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-14
 */

public class CheckOutLogTask extends SimpleTask {

	private CheckOutLog checkOutLog;
	public void setCheckOutLog(CheckOutLog checkOutLog) {
		this.checkOutLog = checkOutLog;
	}
	public CheckOutLog getCheckOutLog() {
		return checkOutLog;
	}
}
