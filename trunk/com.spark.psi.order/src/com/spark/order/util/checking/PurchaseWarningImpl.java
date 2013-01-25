package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.spark.order.service.util.OrderUtil;

/**
 * <p>采购预警商品检查(不满足交货需求)</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-29
 */
class PurchaseWarningImpl extends ServiceCheckImpl{

	public PurchaseWarningImpl(Context context, CheckParam param) {
		super(context, param);
	}

	@Override
	protected boolean doCheck(CheckParam param) {
		//本次采购<（交货需求-采购中数量-库存数量）
		this.inventory = OrderUtil.getInventory(context, param.getId2(), param.getId());
		return param.getCount()<0 || 
			param.getCount()<this.inventory.getAmount()
			-this.inventory.getOnWayCount()
			-OrderUtil.getGoodsBuyingCount(context, param.getId(), param.getId2())
			-this.inventory.getCount();
	}

}
