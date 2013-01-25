package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;

/**
 * <p>促销管理流程控制</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-9
 */
class SalesPromotionProcessManageImpl extends ProcessManageImpl{

	public SalesPromotionProcessManageImpl(Context context) {
		super(context, BillsEnum.SALE_PROMOTION);
	}

	@Override
	protected OrderInfo getOrderInfo(GUID orderId) {
		
		return null;
	}

	public StatusEnum next(GUID orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
