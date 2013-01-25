package com.spark.order.util.process;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.exceptions.DataStatusExpireException;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.task.RebutTask;
import com.spark.order.intf.task.StopTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.util.checking.IServiceCheck;
import com.spark.psi.inventory.intf.task.pub.ExcuteCheckingInTask;
import com.spark.psi.inventory.intf.task.pub.ExcuteCheckingOutTask;
import com.spark.psi.inventory.intf.task.pub.StopCheckingInTask;
import com.spark.psi.inventory.intf.task.pub.StopCheckingOutTask;
import com.spark.psi.publish.OrderAction;

abstract class OrderActionImpl implements IOrderAction {
	protected Context context;
	protected OrderInfo orderInfo;
	protected BillsEnum billsEnum;
	protected GUID id;
	protected String cause;
	protected Long planOutDate;
	protected boolean isIgnoredWarning;// 忽略预警
	protected StatusEnum next;
	private List<IServiceCheck> checks = new ArrayList<IServiceCheck>();

	/**
	 * @param context
	 * @param billsEnum
	 */
	public OrderActionImpl(Context context, BillsEnum billsEnum) {
		super();
		this.context = context;
		this.billsEnum = billsEnum;
	}

	public IServiceCheck[] getServiceCheck() {
		return checks.toArray(new IServiceCheck[checks.size()]);
	}

	/**
	 * 添加校验结果
	 * 
	 * @param check
	 *            void
	 */
	public void addCheck(IServiceCheck check) {
		checks.add(check);
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public void setPlanOutDate(Long l) {
		this.planOutDate = l;
	}

	public StatusEnum getNewstatus() {
		return next;
	}

	public boolean action(GUID orderId, OrderAction orderAction, boolean ignoredWarning) throws Throwable {
		this.isIgnoredWarning = ignoredWarning;
		return action(orderId, orderAction);
	}

	public boolean action(GUID id, OrderAction orderAction) throws Throwable {
		this.id = id;
		initOrderInfo();
		if (null == id) {
			return false;
		}
		switch (orderAction) {
		case Submit:
			if (!StatusEnum.getstatus(this.orderInfo.getStatus()).isIn(StatusEnum.Submit, StatusEnum.Return)) {
				throw new DataStatusExpireException();
			}
			return submit();
		case Approval:
			if (!StatusEnum.Approve.getKey().equals(this.orderInfo.getStatus())) {
				throw new DataStatusExpireException();
			}
			return approval();
		case Stop:
			if (!StatusEnum.getstatus(this.orderInfo.getStatus()).isIn(StatusEnum.Store_N0, StatusEnum.Store_Part)) {
				throw new DataStatusExpireException();
			}
			return stop();
		case Execut:
			if (!this.orderInfo.isStoped()) {
				throw new DataStatusExpireException();
			}
			return exect() ;
		case consignment:
			if (!StatusEnum.Consignment_No.getKey().equals(this.orderInfo.getStatus())) {
				throw new DataStatusExpireException();
			}
			return consignment();
		case Deny:
			if (!StatusEnum.Approve.getKey().equals(this.orderInfo.getStatus())) {
				throw new DataStatusExpireException();
			}
			return deny();
		default:
			return false;
		}
	} 

	/**
	 * 提交
	 * 
	 * @return boolean
	 */
	protected abstract boolean submit() throws Throwable;

	/**
	 * 批准
	 * 
	 * @return boolean
	 */
	protected abstract boolean approval() throws Throwable;

	/**
	 * 中止
	 * 
	 * @param b
	 * @return boolean 是否中止关联的（直供需求用）
	 */
	protected boolean stop() throws Throwable {
		StopTask task = new StopTask(billsEnum);
		task.billsRECID = id;
		next = StatusEnum.getstatus(this.orderInfo.getStatus());
		task.oldstatus = next.getKey();
		task.cause = this.cause;
		task.isStoped = true;
		context.handle(task);
		return task.isSucceed() && stopStore();
	}

	/**
	 * 中止出入库单
	 * 
	 * @return boolean
	 */
	private boolean stopStore() {
		if (billsEnum.isInEnum(BillsEnum.PURCHASE, BillsEnum.SALE_CANCEL)) {
			StopCheckingInTask task = new StopCheckingInTask();
			task.setRelationOrderId(this.id);
			task.setStopReason(cause);// this.orderInfo.getStopCause()==null?cause:this.orderInfo.getStopCause());
			context.handle(task);
		} else {
			StopCheckingOutTask task = new StopCheckingOutTask();
			task.setRelationOrderId(this.id);
			task.setStopReason(cause);// this.orderInfo.getStopCause()==null?cause:this.orderInfo.getStopCause());
			context.handle(task);
		}
		return true;
	}

	/**
	 * 重新执行
	 * 
	 * @return boolean
	 */
	protected boolean exect() throws Throwable {
		StopTask task = new StopTask(billsEnum);
		task.billsRECID = id;
		next = StatusEnum.getstatus(this.orderInfo.getStatus());
		task.oldstatus = next.getKey();
		task.cause = this.cause;
		task.isStoped = false;
		context.handle(task);
		return task.isSucceed() && exectStore();
	}

	/**
	 * 重新执行出入库
	 * 
	 * @return boolean
	 */
	private boolean exectStore() {
		if (billsEnum.isInEnum(BillsEnum.PURCHASE, BillsEnum.SALE_CANCEL)) {
			ExcuteCheckingInTask task = new ExcuteCheckingInTask();
			task.setRelationOrderId(this.id);
			context.handle(task);
		} else {
			ExcuteCheckingOutTask task = new ExcuteCheckingOutTask();
			task.setRelationOrderId(this.id);
			context.handle(task);
		}
		return true;
	}

	/**
	 * 确认发货
	 * 
	 * @return boolean
	 */
	protected abstract boolean consignment() throws Throwable;

	/**
	 * 驳回
	 * 
	 * @return boolean
	 */
	protected boolean deny() throws Throwable {
		// 执行
		IProcessManage pm = OrderFactory.getProcessManage(context, this.billsEnum);
		pm.setOrderInfo(this.orderInfo);
		next = pm.prov(this.id);
		RebutTask task = new RebutTask(billsEnum);
		task.billsRECID = id;
		task.oldstatus = this.orderInfo.getStatus();
		task.newstatus = next.getKey();
		task.info = this.orderInfo;
		task.cause = this.cause;
		context.handle(task);
		return task.isSucceed();
	}

	// public OrderInfo getOrderInfo() {
	// return orderInfo;
	// }

	/**
	 * 获取订单信息
	 * 
	 * @return OrderInfo
	 */
	protected abstract void initOrderInfo();

	public void setOrderInfo(OrderInfo order) {
		this.orderInfo = order;
	}
}
