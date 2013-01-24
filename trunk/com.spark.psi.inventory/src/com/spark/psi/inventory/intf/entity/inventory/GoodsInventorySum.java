/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.entity.inventory
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-29       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.inventory;

/**
 * <p>库存总金额、数量等</p>
 * key:GoodsInventorySumKey
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-29
 */

public class GoodsInventorySum {

	private double totalCount;
	private double totalAmount;
	private double totalToDeliverCount;
	/**
	 * 总数量
	 * 
	 * @return double
	 */
	public double getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(double totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * 总金额
	 * 
	 * @return double
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setTotalToDeliverCount(double totalToDeliverCount) {
		this.totalToDeliverCount = totalToDeliverCount;
	}
	/**
	 * 交货需求总数
	 * 
	 * @return double
	 */
	public double getTotalToDeliverCount() {
		return totalToDeliverCount;
	}
	
}
