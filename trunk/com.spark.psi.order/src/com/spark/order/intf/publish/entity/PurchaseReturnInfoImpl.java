package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;



/**
 * 
 * <p>采购退货详情</p>
 *


 *
 
 * @version 2012-3-1
 */
public final class PurchaseReturnInfoImpl extends OrderInfoImpl implements com.spark.psi.publish.order.entity.PurchaseReturnInfo{

	//退货商品明细
	private PurchaseReturnGoodsItemImpl[] goodsItems;
	private GUID storeId;
	private String storeName;
	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public PurchaseReturnGoodsItemImpl[] getGoodsItems(){
    	return goodsItems;
    }

	/**
	 * @param goodsItems the goodsItems to set
	 */
	public void setGoodsItems(PurchaseReturnGoodsItemImpl[] goodsItems) {
		this.goodsItems = goodsItems;
	}

	
}
