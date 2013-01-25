package com.spark.order.util2;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.entity.EntityFather;
import com.spark.psi.publish.OrderAction;

public class OrderButton_Retail2 extends OrderButtonImpl2<OrderAction>{
	protected OrderButton_Retail2(Context context){
		super(context);
	}

	@Override
	protected void initButton(EntityFather entity) throws OrderButtonParamError {
		
	}

	@Override
	protected OrderAction[] getActions(List<OrderAction> orderActions) {
		return orderActions.toArray(new OrderAction[orderActions.size()]);
	}


}
