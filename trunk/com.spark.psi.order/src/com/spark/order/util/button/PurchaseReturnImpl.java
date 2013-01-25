package com.spark.order.util.button;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.psi.publish.OrderAction;

class PurchaseReturnImpl extends OrderButtonImpl{

	public PurchaseReturnImpl(Context context, OrderInfo orderInfo) {
		super(context, orderInfo);
	}

	@Override
	protected void doInit() {
		if(this.orderInfo.isStoped()){
			if(TypeEnum.getType(this.orderInfo.getBillType()).isInType(TypeEnum.CANCEL)){
				this.addOrderAction(OrderAction.Execut);
			}
			return;
		}
		StatusEnum status = StatusEnum.getstatus(this.orderInfo.getStatus());
		status = status == StatusEnum.Approveing?StatusEnum.Approve:status;
		switch (status) {
		case Submit:
			this.addOrderAction(OrderAction.Submit, OrderAction.Delete);
			break;
		case Approve:
			UserAuthEnum auth = BillsConstant.getUserAuth(context, BillsEnum.PURCHASE_CANCEL);
			if(UserAuthEnum.EMPLOYEE != auth){// && !this.orderInfo.getCreateGuid().equals(BillsConstant.getUserGuid(context))){
				this.addOrderAction(OrderAction.Approval);
			}
			break;
		case Return:
			this.addOrderAction(OrderAction.Submit, OrderAction.Delete);
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
	protected PurchaseCancel getOrderInfo() {
		return context.find(PurchaseCancel.class, this.id);
	}

}
