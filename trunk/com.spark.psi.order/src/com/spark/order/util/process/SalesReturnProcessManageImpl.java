package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.sales.intf.entity.SaleCancel;

/**
 * <p>销售退货流程控制</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-9
 */
class SalesReturnProcessManageImpl extends ProcessManageImpl{

	public SalesReturnProcessManageImpl(Context context) {
		super(context, BillsEnum.SALE_CANCEL);
	}

	@Override
	protected SaleCancel getOrderInfo(GUID orderId) {
		if(CheckIsNull.isNotEmpty(this.orderInfo) && orderInfo instanceof SaleCancel){
			return (SaleCancel) orderInfo;
		}
		return context.find(SaleCancel.class, orderId);
	}

	public StatusEnum next(GUID orderId) {
		StatusEnum status = null;
		SaleCancel info = getOrderInfo(orderId);
		StatusEnum oldstatus = StatusEnum.getstatus(info.getStatus());
		oldstatus = oldstatus == StatusEnum.Approveing?StatusEnum.Approve:oldstatus;
		switch (oldstatus) {
		case Submit:
			this.orderDepartment = getMyDeptGuid();
			if(UserAuthEnum.BOSS == BillsConstant.getUserAuth(context, BillsEnum.SALE_CANCEL) || !isHaveExam(info)){
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
			if(UserAuthEnum.BOSS == BillsConstant.getUserAuth(context, BillsEnum.SALE_CANCEL) || !isHaveExam(info)){
				status = StatusEnum.Store_N0;
			}
			else{
				status = StatusEnum.Approve;
			}
			break;
		}
		return status;
	}

}
