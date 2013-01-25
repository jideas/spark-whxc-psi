package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.sales2.SalesReturnItem2;
import com.spark.psi.publish.order.entity.SalesReturnGoodsItem;

/**
 * 
 * <p>销售退货单商品明细</p>
 *


 *
 
 * @version 2012-3-1
 */
public class SalesReturnGoodsItemImpl2 extends OrderGoodsItemImpl2<SalesReturnItem2> implements SalesReturnGoodsItem{

	public SalesReturnGoodsItemImpl2(Context context, SalesReturnItem2 t) {
		super(context, t);
	}

	public GUID getStoreId() {
		return getEntity().getStoreId();
	}

	public String getStoreName() {
		return getEntity().getStoreName();
	}
}
