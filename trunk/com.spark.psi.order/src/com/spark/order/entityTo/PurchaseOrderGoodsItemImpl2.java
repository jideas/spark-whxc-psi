package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.entity.OrderItemFather;
import com.spark.psi.publish.order.entity.PurchaseOrderGoodsItem;



/**
 * 
 * <p>�ɹ�������Ʒ��ϸ</p>
 *


 *
 
 * @version 2012-3-1
 */
public class PurchaseOrderGoodsItemImpl2 extends OrderGoodsItemImpl2<OrderItemFather> implements PurchaseOrderGoodsItem{
	

	public PurchaseOrderGoodsItemImpl2(Context context, OrderItemFather t) {
		super(context, t);
	}

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
