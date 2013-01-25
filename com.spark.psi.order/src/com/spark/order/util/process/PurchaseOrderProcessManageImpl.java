package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;

/**
 * <p>
 * 采购订单流程管理
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * 
 * @version 2012-3-13
 */
class PurchaseOrderProcessManageImpl extends ProcessManageImpl {

	public PurchaseOrderProcessManageImpl(Context context) {
		super(context, BillsEnum.PURCHASE);
	}

	public StatusEnum next(GUID orderId) {
		StatusEnum status = null;
		PurchaseOrderInfo info = getOrderInfo(orderId);
		TypeEnum type = TypeEnum.getType(info.getBillType());
		StatusEnum oldstatus = StatusEnum.getstatus(info.getStatus());
		oldstatus = oldstatus == StatusEnum.Approveing ? StatusEnum.Approve : oldstatus;
		switch (oldstatus) {
		case Submit:
			this.orderDepartment = getMyDeptGuid();
			status = getSubmit_RebutNext(type, info);
			break;
		case Approve:
			status = getStatusEnumByDirect(type);
			break;
		case Approveing:
			status = getStatusEnumByDirect(type);
			break;
		case Consignment_No:
			status = StatusEnum.Consignment_Yes;
			break;
		case Return:
			this.orderDepartment = getMyDeptGuid();
			status = getSubmit_RebutNext(type, info);
			break;
		}
		return status;
	}

	private StatusEnum getSubmit_RebutNext(TypeEnum type, PurchaseOrderInfo orderInfo) {
		StatusEnum status;
		if (UserAuthEnum.BOSS == BillsConstant.getUserAuth(context, BillsEnum.PURCHASE)) {
			status = getStatusEnumByDirect(type);
		} else {
			if (isHaveExam(orderInfo)) {
				status = StatusEnum.Approve;
			} else {
				status = getStatusEnumByDirect(type);
			}
		}
		return status;
	}

	/**
	 * 订单生效状态根据类型判断方法
	 * 
	 * @param type
	 * @return StatusEnum
	 */
	private StatusEnum getStatusEnumByDirect(TypeEnum type) {

		return StatusEnum.Store_N0;

	}

	// public StatusEnum prov(GUID orderId) {
	// StatusEnum status = null;
	// PurchaseOrderInfo info = getOrderInfo(orderId);
	// StatusEnum oldstatus = StatusEnum.getstatus(info.getStatus());
	// switch (oldstatus) {
	// case EXAMINE:
	// status = StatusEnum.REBUT;
	// break;
	// }
	// return status;
	// }

	// /**
	// * 获得采购订单
	// * @param id
	// * @return PurchaseOrderInfo
	// */
	// private PurchaseOrderInfo getPurchaseOrderInfo(GUID id){
	// if(CheckIsNull.isNotEmpty(this.orderInfo) && orderInfo instanceof
	// PurchaseOrderInfo){
	// return (PurchaseOrderInfo) orderInfo;
	// }
	// return context.find(PurchaseOrderInfo.class, id);
	// }

	@Override
	protected PurchaseOrderInfo getOrderInfo(GUID orderId) {
		if (CheckIsNull.isNotEmpty(this.orderInfo) && orderInfo instanceof PurchaseOrderInfo) {
			return (PurchaseOrderInfo) orderInfo;
		}
		return context.find(PurchaseOrderInfo.class, orderId);
	}
}
