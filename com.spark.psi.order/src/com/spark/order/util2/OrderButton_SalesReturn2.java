package com.spark.order.util2;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.entity.EntityFather;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.sales2.SalesReturnInfo2;
import com.spark.psi.publish.OrderAction;

public class OrderButton_SalesReturn2 extends OrderButtonImpl2<OrderAction>{
	protected OrderButton_SalesReturn2(Context context){
		super(context);
	}
	@Override
	protected void initButton(EntityFather entity) {
		SalesReturnInfo2 orderInfo = getEntityFatherImpl(SalesReturnInfo2.class);
		if(orderInfo.isStoped()){
			if(TypeEnum.getType(orderInfo.getType()).isInType(TypeEnum.CANCEL)){
				this.addOrderAction(OrderAction.Execut);
			}
			return;
		}
		StatusEnum status = StatusEnum.getstatus(orderInfo.getStatus());
		status = status == StatusEnum.Approveing?StatusEnum.Approve:status;
		switch (status) {
		case Submit:
			this.addOrderAction(OrderAction.Submit).addOrderAction(OrderAction.Delete);
			break;
		case Approve:
			UserAuthEnum auth = BillsConstant.getUserAuth(getContext(), BillsEnum.SALE_CANCEL);
			if(UserAuthEnum.EMPLOYEE != auth && !orderInfo.getCreatorId().equals(BillsConstant.getUserGuid(getContext()))){
				this.addOrderAction(OrderAction.Approval);
			}
			break;
		case Return:
			this.addOrderAction(OrderAction.Submit).addOrderAction(OrderAction.Delete);
			break;
		case Store_N0:
			this.addOrderAction(OrderAction.Stop);
			break;
		case Store_Part:
			this.addOrderAction(OrderAction.Stop);
			break;
		}
	}
	@Override
	protected OrderAction[] getActions(List<OrderAction> orderActions) {
		return orderActions.toArray(new OrderAction[orderActions.size()]);
	}


}
