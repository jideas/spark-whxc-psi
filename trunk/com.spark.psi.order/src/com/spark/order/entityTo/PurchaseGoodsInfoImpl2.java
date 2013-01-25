package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.spark.order.purchase.intf.PurchaseGoods2;
import com.spark.psi.publish.order.entity.PurchaseGoodsInfo;

/**
 * 
 * <p>
 * 采购清单
 * </p>
 * 获得采购需求清单 查询方法：ListEntity<PurchaseGoodsItem>+String
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-2-22
 */
public class PurchaseGoodsInfoImpl2 extends PurchaseGoodsItemImpl2 implements
		PurchaseGoodsInfo {

	public PurchaseGoodsInfoImpl2(Context context, PurchaseGoods2 t) {
		super(context, t);
	}
	
}
