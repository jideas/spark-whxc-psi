package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.spark.order.service.util.OrderUtil;
import com.spark.psi.base.GoodsItem;

/**
 * <p>
 * 库存金额上限检查
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author modi
 * @version 2012-3-29
 */
class InventoryAmountImpl extends ServiceCheckImpl {

	public InventoryAmountImpl(Context context, CheckParam param) {
		super(context, param);
	}

	@Override
	protected boolean doCheck(CheckParam param) {
		// this.goodsItem = context.find(GoodsItem.class, param.getId());
		// if(null == this.goodsItem.getGoodsWarnningType()){
		// return false;
		// }
		// switch (this.goodsItem.getGoodsWarnningType()) {
		// case ALL_Amount:
		// return
		// this.goodsItem.getInventoryAmountUpperLimit()-OrderUtil.getGoodsInventorySum(context,
		// this.getGoodsItem().getId()).getTotalAmount()
		// - param.getMoney() < 0;
		// case Store_Amount:
		// this.inventory = OrderUtil.getInventory(context, param.getId2(),
		// param.getId());
		// return
		// inventory.getUpperLimitAmount()<inventory.getAmount()+param.getMoney();
		// default:
		return false;
		// }
	}

}
