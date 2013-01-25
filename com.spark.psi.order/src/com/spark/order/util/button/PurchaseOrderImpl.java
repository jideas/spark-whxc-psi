package com.spark.order.util.button;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.psi.publish.OrderAction;

class PurchaseOrderImpl extends OrderButtonImpl{

	public PurchaseOrderImpl(Context context, OrderInfo orderInfo) {
		super(context, orderInfo);
	}

	@Override
	protected void doInit() {
		if(this.orderInfo.isStoped()){
			if(TypeEnum.getType(this.orderInfo.getBillType()).isInType(TypeEnum.PLAIN, TypeEnum.ON_LINE)){
				this.addOrderAction(OrderAction.Execut);
			}
			return;
		}
		StatusEnum status = StatusEnum.getstatus(this.orderInfo.getStatus());
		status = status == StatusEnum.Approveing?StatusEnum.Approve:status;
		switch (status) {
		case Approve:
			UserAuthEnum auth = BillsConstant.getUserAuth(context, BillsEnum.PURCHASE);
			if(UserAuthEnum.EMPLOYEE != auth){// && !this.orderInfo.getCreateGuid().equals(BillsConstant.getUserGuid(context))){
				this.addOrderAction(OrderAction.Approval);
			}
			break;
		case Return:
			this.addOrderAction(OrderAction.Submit);
			if(TypeEnum.getType(this.orderInfo.getBillType()).isInType(TypeEnum.PLAIN, TypeEnum.ON_LINE)){
				this.addOrderAction(OrderAction.Delete);
			}
			break;
		case Store_N0:
			this.addOrderAction(OrderAction.Stop);
			break;
		case Store_Part:
			this.addOrderAction(OrderAction.Stop);
			break;
		case Consignment_No:
			this.addOrderAction(OrderAction.consignment);
			break;
		}
	}

	@Override
	protected PurchaseOrderInfo getOrderInfo() {
		return context.find(PurchaseOrderInfo.class, this.id);
	}

}
