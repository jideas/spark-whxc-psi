package com.spark.order.intf.publish.entity;


/**
 * 
 * <p>�ɹ�������Ʒ��ϸ</p>
 *


 *
 
 * @version 2012-3-1
 */
public class PurchaseOrderGoodsItemImpl extends OrderGoodsItemImpl implements com.spark.psi.publish.order.entity.PurchaseOrderGoodsItem{
	
	private String purchaseCause; //�ɹ�ԭ��

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
