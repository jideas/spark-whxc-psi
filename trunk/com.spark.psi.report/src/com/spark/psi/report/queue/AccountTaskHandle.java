/**
 * 
 */
package com.spark.psi.report.queue;

import com.jiuqi.dna.core.Context;
import com.spark.psi.account.intf.event.DealingAmountChanageEvent;
import com.spark.psi.report.entity.ReportQueue;

/**
 * 财务模块数据提取
 */ 
public class AccountTaskHandle {

	protected static void handle(Context context, DealingAmountChanageEvent event, ReportQueue rq) {
	}

	protected static void supplierHandle(Context context, DealingAmountChanageEvent event, ReportQueue rq) {
	}

	private static double getRealValue(double value) {
		return value < 0 ? 0 - value : value;
	}
}
