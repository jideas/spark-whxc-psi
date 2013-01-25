package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>采购订单详情</p>
 *


 *
 
 * @version 2012-3-1
 */
@StructClass
public interface PurchaseOrderInfo extends OrderInfo{

//	@StructField
//	private GUID storeId; //仓库id
//	@StructField
//	private String storeName; //仓库名称
//
//	@StructField
//	private PurchaseOrderGoodsItem[] goodsItems; //商品明细


	
	
	public GUID getSourceId();

	public String getSourceName();

	public PurchaseOrderGoodsItem[] getGoodsItems();
}

