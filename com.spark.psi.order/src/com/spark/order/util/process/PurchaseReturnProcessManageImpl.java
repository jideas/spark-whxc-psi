package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancel;

class PurchaseReturnProcessManageImpl extends ProcessManageImpl{

	public PurchaseReturnProcessManageImpl(Context context) {
		super(context, BillsEnum.PURCHASE_CANCEL);
	}

	public StatusEnum next(GUID orderId) {
		StatusEnum status = null;
		PurchaseCancel info = getOrderInfo(orderId);
		StatusEnum oldstatus = StatusEnum.getstatus(info.getStatus());
		oldstatus = oldstatus == StatusEnum.Approveing?StatusEnum.Approve:oldstatus;
		switch (oldstatus) {
		case Submit:
			this.orderDepartment = getMyDeptGuid();
			if((UserAuthEnum.BOSS == BillsConstant.getUserAuth(context, BillsEnum.PURCHASE_CANCEL)) || !isHaveExam(info)){
				status = StatusEnum.Store_N0;
			}
			else{
				status = StatusEnum.Approve;
			}
			break;
		case Approve:
			status = StatusEnum.Store_N0;
			break;
		case Approveing:
			status = StatusEnum.Store_N0;
			break;
		case Return:
			this.orderDepartment = getMyDeptGuid();
			if((UserAuthEnum.BOSS == BillsConstant.getUserAuth(context, BillsEnum.PURCHASE_CANCEL)) || !isHaveExam(info)){
				status = StatusEnum.Store_N0;
			}
			else{
				status = StatusEnum.Approve;
			}
			break;
		}
		return status;
	}

	@Override
	protected PurchaseCancel getOrderInfo(GUID orderId) {
		if(CheckIsNull.isNotEmpty(this.orderInfo) && orderInfo instanceof PurchaseCancel){
			return (PurchaseCancel) orderInfo;
		}
		return context.find(PurchaseCancel.class, orderId);
	}
}
