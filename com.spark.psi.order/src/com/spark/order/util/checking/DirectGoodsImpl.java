package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.purchase.intf.PurchaseGoodsDirect2;
import com.spark.order.purchase.intf.task.PurchaseGoodsDirectTask2;

/**
 * <p>商品已经直供采购(包括使用中)</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-28
 */
class DirectGoodsImpl extends ServiceCheckImpl{
	
	public DirectGoodsImpl(Context context, CheckParam param) {
		super(context, param);
	}

//	public DirectGoodsImpl(Context context, CheckEnum checkEnum) {
//		super(context, checkEnum);
//	}

	@Override
	protected boolean doCheck(CheckParam param) {
		GUID id = param.getId();
		if(BillsConstant.useingDirectGoods.contains(id) || !isHave(id)){
			return true;
		}
		BillsConstant.useingDirectGoods.add(id);
		PurchaseGoodsDirectTask2 task = new PurchaseGoodsDirectTask2();
		task.recid = id;
		context.handle(task, PurchaseGoodsDirectTask2.Method.DELETE);
		return false;
	}
	
	private boolean isHave(GUID id){
		return null != context.find(PurchaseGoodsDirect2.class, id);
	}

}
