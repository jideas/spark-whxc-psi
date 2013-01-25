package com.spark.psi.order.browser.internal;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;



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

	public PurchaseReturnInfoImpl(PartnerInfo partner){
		this.partnerInfo = partner;
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
