/**
 * 
 */
package com.spark.psi.report.queue;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.order.intf.event.PromotionOrderChangedEvent;
import com.spark.order.intf.event.PurchaseOrderChangedEvent;
import com.spark.order.intf.event.PurchaseReturnChangedEvent;
import com.spark.order.intf.event.SalesOrderChangedEvent;
import com.spark.order.intf.event.SalesReturnChangedEvent;
import com.spark.psi.account.intf.event.DealingAmountChanageEvent;
import com.spark.psi.base.event.ApprovalConfigChangedEvent;
import com.spark.psi.base.event.ApprovalLogEvent;
import com.spark.psi.base.event.CustomerCreateEvent;
import com.spark.psi.base.event.CustomerDataChangeEvent;
import com.spark.psi.base.event.CustomerTurnOfficalEvent;
import com.spark.psi.base.event.EmployeeDepartmentChangeEvent;
import com.spark.psi.base.event.EmployeeStatusChangeEvent;
import com.spark.psi.base.event.GoodsCategoryStoreLimitUpperChangeEvent;
import com.spark.psi.base.event.GoodsStatusChangeEvent;
import com.spark.psi.base.event.MaterialsItemThresholdChangeEvent;
import com.spark.psi.base.event.NoticeReadEvent;
import com.spark.psi.base.event.NoticeStatusChangeEvent;
import com.spark.psi.base.event.StoreStatusChangeEvent;
import com.spark.psi.inventory.intf.event.CheckInEvent;
import com.spark.psi.inventory.intf.event.CheckInSheetStatusChanageEvent;
import com.spark.psi.inventory.intf.event.CheckOutEvent;
import com.spark.psi.inventory.intf.event.CheckOutSheetStatusChanageEvent;
import com.spark.psi.inventory.intf.event.CheckingInEvent;
import com.spark.psi.inventory.intf.event.CheckingOutEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateApprovalEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateDenyEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateSubmitted;
import com.spark.psi.inventory.intf.event.InventoryLogEvent;
import com.spark.psi.publish.deliver.Event.DeliverExceptionCreateEvent;
import com.spark.psi.publish.deliver.Event.DeliverExceptionOverEvent;
import com.spark.psi.report.entity.ReportQueue;
import com.spark.psi.report.task.QueueDeleteTask;
import com.spark.psi.report.task.QueueRunTask;
import com.spark.psi.report.task.QueueStartTask;
import com.spark.psi.report.utils.AttrXmlUtils;

/**
 *
 */
public class QueueRunService extends Service {

	/**
	 * @param title
	 */
	protected QueueRunService() {
		super("QueueRunService");
	}

	public static boolean isRunning = false;

	/*
	 * 创建定时器，每分钟扫描任务队列，若有线程正在执行，则等待
	 */
	@Override
	protected void init(Context context) throws Throwable {
		context.asyncHandle(new QueueStartTask());
	}

	@Publish
	protected class DoInitThings extends SimpleTaskMethodHandler<QueueStartTask> {

		@Override
		protected void handle(Context context, QueueStartTask task) throws Throwable {
			while (true) {
				Thread.sleep(30000);
				if (isRunning) {
					continue;
				}
				context.asyncHandle(new QueueRunTask());
			}
		}
	}

	@Publish
	protected class QueueRunHandle extends SimpleTaskMethodHandler<QueueRunTask> {

		@Override
		protected void handle(Context context, QueueRunTask arg1) throws Throwable {
			isRunning = true;
			List<ReportQueue> rqList = context.getList(ReportQueue.class);
			// 这里可以设置每当执行几次任务就返回重新开始(控制提交时间)
			for (int i = 0; i < rqList.size(); i++) {
				try {
					ReportQueue rq = rqList.get(i);
					doThisTask(context, rq);
					// 删除数据库中持久化的任务
					context.handle(new QueueDeleteTask(rq.getRECID()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			isRunning = false;
		}

		/**
		 * 执行抽取
		 * 
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		private void doThisTask(Context context, ReportQueue rq) throws Exception {
			Class clazz = null;
			try {
				clazz = Class.forName(rq.getEventClass());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			doThisRealTask(context, AttrXmlUtils.parseXml(rq.getAttributeXml(), clazz), rq);
		}
	}

	/** 
	 * @throws Exception
	 */
	private void doThisRealTask(Context context, Object event, ReportQueue rq) throws Exception {
		/**
		 * 财务模块
		 */
		// 往来变化时间（初始化、实收 、调整往来、应收）（初始化、实付、调整往来、应付）
		if (DealingAmountChanageEvent.class.getName().equals(rq.getEventClass())) {
			AccountTaskHandle.handle(context, (DealingAmountChanageEvent) event, rq);
		}

		/**
		 * 基础模块
		 */
		if (NoticeReadEvent.class.getName().endsWith(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (NoticeReadEvent) event, rq);
		}
		if (NoticeStatusChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (NoticeStatusChangeEvent) event, rq);
		}
		// 库存上限下限变化
		if (MaterialsItemThresholdChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (MaterialsItemThresholdChangeEvent) event, rq);
		}
		// 商品分类金额上限变化
		if (GoodsCategoryStoreLimitUpperChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (GoodsCategoryStoreLimitUpperChangeEvent) event, rq);
		}
		// 员工离职、复职(员工ID)
		if (EmployeeStatusChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (EmployeeStatusChangeEvent) event, rq);
		}

		// 员工部门变化（员工ID）
		if (EmployeeDepartmentChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (EmployeeDepartmentChangeEvent) event, rq);
		}

		// 审核配置发生变化（改动范围）
		if (ApprovalConfigChangedEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (ApprovalConfigChangedEvent) event, rq);
		}

		// 商品状态变化（商品ID）
		if (GoodsStatusChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (GoodsStatusChangeEvent) event, rq);
		}

		// 仓库状态变化（仓库ID）
		if (StoreStatusChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (StoreStatusChangeEvent) event, rq);
		}

		// 审核记录生成时间（审批记录ID）
		if (ApprovalLogEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (ApprovalLogEvent) event, rq);
		}

		// 客户信息改变时间事件
		if (CustomerDataChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (CustomerDataChangeEvent) event, rq);
		}

		// 客户创建事件
		if (CustomerCreateEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (CustomerCreateEvent) event, rq);
		}

		// 客户转正事件
		if (CustomerTurnOfficalEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (CustomerTurnOfficalEvent) event, rq);
		}

		/**
		 * 库存模块
		 */
		// 调拨单审批通过事件
		if (InventoryAllocateApprovalEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (InventoryAllocateApprovalEvent) event, rq);
		}
		// 调拨单审批退回事件
		if (InventoryAllocateDenyEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (InventoryAllocateDenyEvent) event, rq);
		}
		// 调拨单待审批事件
		if (InventoryAllocateSubmitted.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (InventoryAllocateSubmitted) event, rq);
		}
		// 入库单事件： 中止、重新执行（入库单ID）
		if (CheckInSheetStatusChanageEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckInSheetStatusChanageEvent) event, rq);
		}

		// 出库单事件： 中止、重新执行（出库单ID）
		if (CheckOutSheetStatusChanageEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckOutSheetStatusChanageEvent) event, rq);
		}

		// 库存流水事件（流水ID）
		if (InventoryLogEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (InventoryLogEvent) event, rq);
		}

		// 入库单事件：创建
		if (CheckingInEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckingInEvent) event, rq);
		}

		// 出库单事件：创建
		if (CheckingOutEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckingOutEvent) event, rq);
		}

		// 确认入库
		if (CheckInEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckInEvent) event, rq);
		}

		// 确认出库
		if (CheckOutEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckOutEvent) event, rq);
		}

		/**
		 * 订单模块
		 */
		if (PromotionOrderChangedEvent.class.getName().equals(rq.getEventClass())) {
			OrderTaskHandle.handle(context, (PromotionOrderChangedEvent) event, rq);
		}
		if (SalesReturnChangedEvent.class.getName().equals(rq.getEventClass())) {
			OrderTaskHandle.handle(context, (SalesReturnChangedEvent) event, rq);
		}

		if (SalesOrderChangedEvent.class.getName().equals(rq.getEventClass())) {
			OrderTaskHandle.handle(context, (SalesOrderChangedEvent) event, rq);
		}

		if (PurchaseReturnChangedEvent.class.getName().equals(rq.getEventClass())) {
			OrderTaskHandle.handle(context, (PurchaseReturnChangedEvent) event, rq);
		}

		if (PurchaseOrderChangedEvent.class.getName().equals(rq.getEventClass())) {
			OrderTaskHandle.handle(context, (PurchaseOrderChangedEvent) event, rq);
		}

		if (DeliverExceptionCreateEvent.class.getName().equals(rq.getEventClass())) {
			OrderTaskHandle.handle(context, (DeliverExceptionCreateEvent) event, rq);
		}
		if (DeliverExceptionOverEvent.class.getName().equals(rq.getEventClass())) {
			OrderTaskHandle.handle(context, (DeliverExceptionOverEvent) event, rq);
		}
	}
}
