package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * 采购退货详情
 * </p>
 * 
 * 
 * 
 * 
 * 
 * @version 2012-3-1
 */
public interface PurchaseReturnInfo extends OrderInfo {
	
	// 退货商品明细
	// private PurchaseReturnGoodsItem[] goodsItems;

	public GUID getStoreId();

	public String getStoreName();

	public PurchaseReturnGoodsItem[] getGoodsItems();

}
