package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderItemFather;

/**
 * 
 * <p>�ɹ��˻���Ʒ��ϸ</p>
 *


 *
 
 * @version 2012-3-1
 */
public class PurchaseReturnGoodsItemImpl2 extends OrderGoodsItemImpl2 implements com.spark.psi.publish.order.entity.PurchaseReturnGoodsItem{	
	


	public PurchaseReturnGoodsItemImpl2(Context context, OrderItemFather t) {
		super(context, t);
	}


	private String storeName; //�˻��ֿ�����
	
	private GUID storeId; //�˻��ֿ�id

	public String getStoreName(){
    	return storeName;
    }


	public GUID getStoreId(){
    	return storeId;
    }


	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}


}
