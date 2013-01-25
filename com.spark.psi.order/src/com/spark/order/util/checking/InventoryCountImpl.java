package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.spark.order.service.util.OrderUtil;
import com.spark.psi.base.GoodsItem;

/**
 * <p>
 * 商品超库存上限
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author modi
 * @version 2012-3-29
 */
class InventoryCountImpl extends ServiceCheckImpl {

	public InventoryCountImpl(Context context, CheckParam param) {
		super(context, param);
	}

	@Override
	protected boolean doCheck(CheckParam param) {
		// this.goodsItem = context.find(GoodsItem.class, param.getId());
		// if(null == this.goodsItem.getGoodsWarnningType()){
		// return false;
		// }
		// switch (this.goodsItem.getGoodsWarnningType()) {
		// case ALL_Count:
		// return OrderUtil.getGoodsInventorySum(context,
		// this.getGoodsItem().getId()).getTotalCount() + param.getCount()
		// >this.goodsItem.getTotalStoreUpperLimit();
		// case Store_Count:
		// // 2、 超上限校验规则：（本次采购数量+采购中数量+库存数量-交付需求）大于库存上限
		// this.inventory = OrderUtil.getInventory(context, param.getId2(),
		// param.getId());
		// if(null == this.inventory){
		// return false;
		// }
		// double d =
		// this.inventory.getOnWayCount()+OrderUtil.getPurchaseingCount(context,
		// param.getId2(), param.getId());
		// return
		// param.getCount()+d+this.inventory.getCount()-this.inventory.getDeliveryingCount()>this.inventory.getUpperLimitCount();
		// default:
		return false;
		// }
	}

}
