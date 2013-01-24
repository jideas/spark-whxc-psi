/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.entity.instorage.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-12       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.instorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>相关单据入库情况</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-12
 */

public class RelationCheckInSheet {

	/**
	 * 相关订单ID
	 */
	private GUID relationOrderId;
	/**
	 * 相关订单总数量
	 */
	private double orderGoodsTotalCount;
	/**
	 * 相关订单总金额
	 */
	private double orderTotalAmount;
	/**
	 * 已入库数量
	 */
	private double checkedInCount;
	/**
	 * 已付款金额
	 */
	private double checkedInAmount;
	/**
	 * 已出库金额
	 */
	private double inStoreAmount;
	
	public double getInStoreAmount() {
		return inStoreAmount;
	}
	public void setInStoreAmount(double inStoreAmount) {
		this.inStoreAmount = inStoreAmount;
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
	public double getCheckedInCount() {
		return checkedInCount;
	}
	public void setCheckedInCount(double checkedInCount) {
		this.checkedInCount = checkedInCount;
	}
	public double getCheckedInAmount() {
		return checkedInAmount;
	}
	public void setCheckedInAmount(double checkedInAmount) {
		this.checkedInAmount = checkedInAmount;
	}
	public void setRelationOrderId(GUID relationOrderId) {
		this.relationOrderId = relationOrderId;
	}
	public GUID getRelationOrderId() {
		return relationOrderId;
	}
	
}
