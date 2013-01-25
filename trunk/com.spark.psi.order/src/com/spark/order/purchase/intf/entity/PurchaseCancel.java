/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.entity
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

package com.spark.order.purchase.intf.entity;

import com.spark.order.intf.entity.OrderInfo;

/** 
 * 采购退货实体 
 */

public class PurchaseCancel extends OrderInfo {

	private String consignee; 
	
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	} 
}
