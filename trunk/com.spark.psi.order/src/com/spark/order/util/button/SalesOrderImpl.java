package com.spark.order.util.button;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.psi.publish.OrderAction;

class SalesOrderImpl extends OrderButtonImpl{

	public SalesOrderImpl(Context context, OrderInfo orderInfo) {
		super(context, orderInfo);
	}

	private boolean isNotHave(){
		SaleOrderInfo sale = getOrderInfo();
		BillsConstant.getTenantsGuid(context);
		if(null == (sale).getExamDeptGuid() || BillsConstant.getUser(context).getDepartmentId().equals((sale).getExamDeptGuid()))
			return true;
		return false;
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
		case Submit:
			this.addOrderAction(OrderAction.Submit, OrderAction.Delete);
			break;
		case Approve:
			UserAuthEnum auth = BillsConstant.getUserAuth(context, BillsEnum.SALE);
			if(UserAuthEnum.EMPLOYEE != auth && 
					!this.orderInfo.getCreatorId().equals(BillsConstant.getUserGuid(context))
					&& isNotHave()){
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
	protected SaleOrderInfo getOrderInfo() {
		return context.find(SaleOrderInfo.class, this.id);
	}

}
