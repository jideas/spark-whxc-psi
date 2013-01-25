package com.spark.order.purchase.service;

import com.jiuqi.dna.core.type.GUID;


/**
 * 查询指定商品和仓库的最近采购供应商，返回采购订单PurchaseOrderInfo
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-11
 */
public class SelectLastPurchasePartnerKey{
	private final GUID goodsItemid;
	private final GUID storeId;
	/**
	 * @param goodsItemid
	 * @param storeId
	 */
	public SelectLastPurchasePartnerKey(GUID goodsItemid, GUID storeId) {
		super();
		this.goodsItemid = goodsItemid;
		this.storeId = storeId;
	}
	GUID getGoodsItemid() {
		return goodsItemid;
	}
	GUID getStoreId() {
		return storeId;
	}
	
}
