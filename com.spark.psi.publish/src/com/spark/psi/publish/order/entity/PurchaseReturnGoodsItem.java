package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>采购退货商品明细</p>
 *


 *
 
 * @version 2012-3-1
 */
public interface PurchaseReturnGoodsItem extends OrderGoodsItem{	
	
//	private String storeName; //退货仓库名称
//	
//	private GUID storeId; //退货仓库id

	public String getStoreName();

	public GUID getStoreId();
}
