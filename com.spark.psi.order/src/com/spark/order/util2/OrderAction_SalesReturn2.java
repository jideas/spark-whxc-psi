package com.spark.order.util2;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.OrderEnum;
import com.spark.order.sales2.SalesReturnInfo2;

public class OrderAction_SalesReturn2 extends OrderActionSPImpl2<SalesReturnInfo2>{

	protected OrderAction_SalesReturn2(Context context) {
		super(context, OrderEnum.Sales_Return);
	}

	@Override
	protected void effectual()
			throws com.spark.order.util2.IOrderAction2.OrderActionLoseException {
		
	}

	@Override
	protected SalesReturnInfo2 getOrder() {
		return getEntity(SalesReturnInfo2.class);
	}

}
