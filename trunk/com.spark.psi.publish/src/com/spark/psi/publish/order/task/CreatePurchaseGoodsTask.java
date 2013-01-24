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
 * 新增采购需求
 
 *
 */
public final class CreatePurchaseGoodsTask extends SimpleTask {
	
	
	private GUID goodsItemId;//	商品条目id
	
	private double PurchaseCount;//	数量	NUM(10,2)
		
	private GUID storeId; //仓库ID

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
