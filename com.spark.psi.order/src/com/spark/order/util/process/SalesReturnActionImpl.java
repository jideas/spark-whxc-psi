package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.internal.service.MeToModuleUtil;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.task.FlowTask;
import com.spark.order.intf.task.RebutTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleCancelItem;
import com.spark.order.service.util.OrderUtil;
import com.spark.psi.inventory.intf.entity.instorage.mod.RelationCheckInSheet;

public class SalesReturnActionImpl extends OrderActionImpl{

	public SalesReturnActionImpl(Context context) {
		super(context, BillsEnum.SALE_CANCEL);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected boolean approval() throws Throwable {
		//执行
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
			new MeToModuleUtil(context).createInStore((SaleCancel)this.orderInfo, context.getList(SaleCancelItem.class, this.id));
			//减少客户已用信用额度
			OrderUtil.subAlreadyUseCredit(context, this.orderInfo.getPartnerId(), this.orderInfo.getTotalAmount());
		}
		return task.isSucceed();
	}

	@Override
	protected boolean consignment() {
		return false;
	} 
	@SuppressWarnings("deprecation")
	@Override
	protected boolean exect() throws Throwable {
		//减少客户已用信用额度
		RelationCheckInSheet rcos = OrderUtil.getOrderInSheet(context, this.id);
		OrderUtil.subAlreadyUseCredit(context, this.orderInfo.getPartnerId(), this.orderInfo.getTotalAmount() -rcos.getInStoreAmount());//rcos.getInStoreAmount() + rcos.getCheckedInAmount());
		return super.exect();
	}

	@Override
	protected void initOrderInfo() {
		if(!(CheckIsNull.isNotEmpty(this.orderInfo) && this.orderInfo instanceof SaleCancel)){
			orderInfo = context.find(SaleCancel.class, id);
		}
	} 
	
	@SuppressWarnings("deprecation")
	@Override
	protected boolean stop() throws Throwable {
		//增加客户已用信用额度
		RelationCheckInSheet rcos = OrderUtil.getOrderInSheet(context, this.id);
		OrderUtil.addAlreadyUseCredit(context, this.orderInfo.getPartnerId(), this.orderInfo.getTotalAmount() -rcos.getInStoreAmount());//rcos.getInStoreAmount() + rcos.getCheckedInAmount());
		return super.stop();
	}

	@SuppressWarnings("deprecation")
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
			new MeToModuleUtil(context).createInStore((SaleCancel)this.orderInfo, context.getList(SaleCancelItem.class, this.id));
			//减少客户已用信用额度
			OrderUtil.subAlreadyUseCredit(context, this.orderInfo.getPartnerId(), this.orderInfo.getTotalAmount());
		}
		return task.isSucceed();
	}
}
