package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.internal.service.MeToModuleUtil;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.task.FlowTask;
import com.spark.order.intf.task.RebutTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;

public class PurchaseReturnActionImpl extends OrderActionImpl{

	public PurchaseReturnActionImpl(Context context) {
		super(context, BillsEnum.PURCHASE_CANCEL);
	}

	@Override
	protected boolean approval() throws Throwable {
		//Ö´ÐÐ
		IProcessManage pm = OrderFactory.getProcessManage(context, this.billsEnum);
		pm.setOrderInfo(this.orderInfo);
		next = pm.next(this.id);
		RebutTask task = new RebutTask(billsEnum);
		task.billsRECID = id;
		task.oldstatus = StatusEnum.getstatus(this.orderInfo.getStatus()).getKey();
		task.newstatus = next.getKey();
		task.info = this.orderInfo;
		context.handle(task);
		if(task.isSucceed()){
			new MeToModuleUtil(context).createOutStore((PurchaseCancel)this.orderInfo, context.getList(PurchaseCancelItem.class, this.id));
		}
		return task.isSucceed();
	}

	@Override
	protected boolean consignment() {
		return false;
	}

//	@Override
//	protected boolean deny() {
//		//Ö´ÐÐ
//		IProcessManage pm = OrderFactory.getProcessManage(context, this.billsEnum);
//		pm.setOrderInfo(this.orderInfo);
//		StatusEnum next = pm.prov(this.id);
//		RebutTask task = new RebutTask(billsEnum);
//		task.billsRECID = id;
//		task.oldstatus = StatusEnum.getstatus(this.orderInfo.getStatus()).getKey();
//		task.newstatus = next.getKey();
//		task.info = this.orderInfo;
//		task.cause = this.cause;
//		context.handle(task);
//		return task.isSucceed();
//	}

//	@Override
//	protected boolean exect() {
//		StopTask task = new StopTask(billsEnum);
//		task.billsRECID = id;
//		task.oldstatus = StatusEnum.getstatus(this.orderInfo.getStatus()).getKey();
//		task.cause = this.cause;
//		task.isStoped = false;
//		context.handle(task);
//		return task.isSucceed();
//	}

	@Override
	protected void initOrderInfo() {
		if(!(CheckIsNull.isNotEmpty(this.orderInfo) && this.orderInfo instanceof PurchaseCancel)){
			orderInfo = context.find(PurchaseCancel.class, id);
		}
	}

//	@Override
//	protected boolean stop() {
//		StopTask task = new StopTask(billsEnum);
//		task.billsRECID = id;
//		task.oldstatus = StatusEnum.getstatus(this.orderInfo.getStatus()).getKey();
//		task.cause = this.cause;
//		task.isStoped = true;
//		context.handle(task);
//		return task.isSucceed();
//	}

	@Override
	protected boolean submit() throws Throwable {
		IProcessManage pm = OrderFactory.getProcessManage(context, this.billsEnum);
		pm.setOrderInfo(this.orderInfo);
		next = pm.next(this.id);
		FlowTask task = new FlowTask(billsEnum);
		task.billsRECID = id;
		task.oldstatus = StatusEnum.getstatus(this.orderInfo.getStatus()).getKey();
		task.newstatus = next.getKey();
		task.setDeptGuid(BillsConstant.getUser(context).getDepartmentId());
		context.handle(task);
		if(StatusEnum.Store_N0 == next && task.isSucceed()){
			new MeToModuleUtil(context).createOutStore((PurchaseCancel)this.orderInfo, context.getList(PurchaseCancelItem.class, this.id));
		}
		return task.isSucceed();
	}

}
