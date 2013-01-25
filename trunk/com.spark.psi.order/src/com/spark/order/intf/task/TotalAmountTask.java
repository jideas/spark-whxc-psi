/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-15       莫迪        
 * ============================================================*/

package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.key.SelectMainKey;

/**
 * <p>主列表数据统计Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 王天才
 * @version 2011-11-15
 */

public class TotalAmountTask extends SimpleTask {

	/**
	 * 主列表数据查询KEY
	 */
	private SelectMainKey key;
	/**
	 * 单据数量
	 */
	private int totalCount;
	/**
	 * 采购金额
	 */
	private double totalOrderAmount;
	/**
	 * 退货金额
	 */
	private double totalCancelAmount;
	
	public SelectMainKey getKey() {
		return key;
	}
	public void setKey(SelectMainKey key) {
		this.key = key;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public double getTotalOrderAmount() {
		return totalOrderAmount;
	}
	public void setTotalOrderAmount(double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}
	public double getTotalCancelAmount() {
		return totalCancelAmount;
	}
	public void setTotalCancelAmount(double totalCancelAmount) {
		this.totalCancelAmount = totalCancelAmount;
	}
}
