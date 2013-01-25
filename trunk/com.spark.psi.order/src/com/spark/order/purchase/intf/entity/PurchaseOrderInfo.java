package com.spark.order.purchase.intf.entity;

import com.spark.order.intf.entity.OrderInfo;

/**
 * <p>
 * 采购订单实体
 * </p>
 */
public class PurchaseOrderInfo extends OrderInfo {

	private String consignee;   
	private String purchasePersonName;
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getPurchasePersonName() {
		return purchasePersonName;
	}

	public void setPurchasePersonName(String purchasePersonName) {
		this.purchasePersonName = purchasePersonName;
	} 

}
