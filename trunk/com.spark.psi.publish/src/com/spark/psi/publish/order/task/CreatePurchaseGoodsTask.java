/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �����ɹ�����
 
 *
 */
public final class CreatePurchaseGoodsTask extends SimpleTask {
	
	
	private GUID goodsItemId;//	��Ʒ��Ŀid
	
	private double PurchaseCount;//	����	NUM(10,2)
		
	private GUID storeId; //�ֿ�ID

	public GUID getGoodsItemId(){
    	return goodsItemId;
    }

	public void setGoodsItemId(GUID goodsItemId){
    	this.goodsItemId = goodsItemId;
    }

	public double getPurchaseCount(){
    	return PurchaseCount;
    }

	public void setPurchaseCount(double purchaseCount){
    	PurchaseCount = purchaseCount;
    }

	public GUID getStoreId(){
    	return storeId;
    }

	public void setStoreId(GUID storeId){
    	this.storeId = storeId;
    }

	
}
