/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.task.outstorage
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-27       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.outstorage;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.intf.entity.outstorage.pub.CheckOutDeductionLog;

/**
 * <p>出库单抵减记录</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-27
 */

public class CheckOutDeductionLogTask extends SimpleTask {

	private CheckOutDeductionLog log;

	public void setLog(CheckOutDeductionLog log) {
		this.log = log;
	}

	public CheckOutDeductionLog getLog() {
		return log;
	}
}
