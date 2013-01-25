package com.spark.order.intf.publish.entity;


/**
 * 
 * <p>采购订单商品明细</p>
 *


 *
 
 * @version 2012-3-1
 */
public class PurchaseOrderGoodsItemImpl extends OrderGoodsItemImpl implements com.spark.psi.publish.order.entity.PurchaseOrderGoodsItem{
	
	private String purchaseCause; //采购原因

	public String getPurchaseCause(){
    	return purchaseCause;
    }

	/**
	 * @param purchaseCause the purchaseCause to set
	 */
	public void setPurchaseCause(String purchaseCause) {
		this.purchaseCause = purchaseCause;
	}

	
}
