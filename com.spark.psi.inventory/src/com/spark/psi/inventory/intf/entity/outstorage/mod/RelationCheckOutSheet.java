/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.entity.outstorage.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-12       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.outstorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>相关单据出库情款</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-12
 */

public class RelationCheckOutSheet {

	/**
	 * 相关订单ID
	 */
	private GUID relaOrderGuid;
	/**
	 * 相关订单总数量
	 */
	private double orderGoodsTotalCount;
	/**
	 * 相关订单总金额
	 */
	private double orderTotalAmount;
	/**
	 * 已出库数量
	 */
	private double checkedOutCount;
	/**
	 * 已收款金额
	 */
	private double checkedOutAmount;
	/**
	 * 已出库金额
	 */
	private double outStoreAmount;
	
	public double getOutStoreAmount() {
		return outStoreAmount;
	}
	public void setOutStoreAmount(double outStoreAmount) {
		this.outStoreAmount = outStoreAmount;
	}
	public GUID getRelaOrderGuid() {
		return relaOrderGuid;
	}
	public void setRelaOrderGuid(GUID relaOrderGuid) {
		this.relaOrderGuid = relaOrderGuid;
	}
	public double getOrderGoodsTotalCount() {
		return orderGoodsTotalCount;
	}
	public void setOrderGoodsTotalCount(double orderGoodsTotalCount) {
		this.orderGoodsTotalCount = orderGoodsTotalCount;
	}
	public double getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public double getCheckedOutCount() {
		return checkedOutCount;
	}
	public void setCheckedOutCount(double checkedOutCount) {
		this.checkedOutCount = checkedOutCount;
	}
	public double getCheckedOutAmount() {
		return checkedOutAmount;
	}
	public void setCheckedOutAmount(double checkedOutAmount) {
		this.checkedOutAmount = checkedOutAmount;
	}
	
}
