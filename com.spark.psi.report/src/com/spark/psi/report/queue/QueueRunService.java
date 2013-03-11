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
	 * ������ʱ����ÿ����ɨ��������У������߳�����ִ�У���ȴ�
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
			// �����������ÿ��ִ�м�������ͷ������¿�ʼ(�����ύʱ��)
			for (int i = 0; i < rqList.size(); i++) {
				try {
					ReportQueue rq = rqList.get(i);
					doThisTask(context, rq);
					// ɾ�����ݿ��г־û�������
					context.handle(new QueueDeleteTask(rq.getRECID()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			isRunning = false;
		}

		/**
		 * ִ�г�ȡ
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
		 * ����ģ��
		 */
		// �����仯ʱ�䣨��ʼ����ʵ�� ������������Ӧ�գ�����ʼ����ʵ��������������Ӧ����
		if (DealingAmountChanageEvent.class.getName().equals(rq.getEventClass())) {
			AccountTaskHandle.handle(context, (DealingAmountChanageEvent) event, rq);
		}

		/**
		 * ����ģ��
		 */
		if (NoticeReadEvent.class.getName().endsWith(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (NoticeReadEvent) event, rq);
		}
		if (NoticeStatusChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (NoticeStatusChangeEvent) event, rq);
		}
		// ����������ޱ仯
		if (MaterialsItemThresholdChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (MaterialsItemThresholdChangeEvent) event, rq);
		}
		// ��Ʒ���������ޱ仯
		if (GoodsCategoryStoreLimitUpperChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (GoodsCategoryStoreLimitUpperChangeEvent) event, rq);
		}
		// Ա����ְ����ְ(Ա��ID)
		if (EmployeeStatusChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (EmployeeStatusChangeEvent) event, rq);
		}

		// Ա�����ű仯��Ա��ID��
		if (EmployeeDepartmentChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (EmployeeDepartmentChangeEvent) event, rq);
		}

		// ������÷����仯���Ķ���Χ��
		if (ApprovalConfigChangedEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (ApprovalConfigChangedEvent) event, rq);
		}

		// ��Ʒ״̬�仯����ƷID��
		if (GoodsStatusChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (GoodsStatusChangeEvent) event, rq);
		}

		// �ֿ�״̬�仯���ֿ�ID��
		if (StoreStatusChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (StoreStatusChangeEvent) event, rq);
		}

		// ��˼�¼����ʱ�䣨������¼ID��
		if (ApprovalLogEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (ApprovalLogEvent) event, rq);
		}

		// �ͻ���Ϣ�ı�ʱ���¼�
		if (CustomerDataChangeEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (CustomerDataChangeEvent) event, rq);
		}

		// �ͻ������¼�
		if (CustomerCreateEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (CustomerCreateEvent) event, rq);
		}

		// �ͻ�ת���¼�
		if (CustomerTurnOfficalEvent.class.getName().equals(rq.getEventClass())) {
			BaseTaskHandle.handle(context, (CustomerTurnOfficalEvent) event, rq);
		}

		/**
		 * ���ģ��
		 */
		// ����������ͨ���¼�
		if (InventoryAllocateApprovalEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (InventoryAllocateApprovalEvent) event, rq);
		}
		// �����������˻��¼�
		if (InventoryAllocateDenyEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (InventoryAllocateDenyEvent) event, rq);
		}
		// �������������¼�
		if (InventoryAllocateSubmitted.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (InventoryAllocateSubmitted) event, rq);
		}
		// ��ⵥ�¼��� ��ֹ������ִ�У���ⵥID��
		if (CheckInSheetStatusChanageEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckInSheetStatusChanageEvent) event, rq);
		}

		// ���ⵥ�¼��� ��ֹ������ִ�У����ⵥID��
		if (CheckOutSheetStatusChanageEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckOutSheetStatusChanageEvent) event, rq);
		}

		// �����ˮ�¼�����ˮID��
		if (InventoryLogEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (InventoryLogEvent) event, rq);
		}

		// ��ⵥ�¼�������
		if (CheckingInEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckingInEvent) event, rq);
		}

		// ���ⵥ�¼�������
		if (CheckingOutEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckingOutEvent) event, rq);
		}

		// ȷ�����
		if (CheckInEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckInEvent) event, rq);
		}

		// ȷ�ϳ���
		if (CheckOutEvent.class.getName().equals(rq.getEventClass())) {
			InventoryTaskHandle.handle(context, (CheckOutEvent) event, rq);
		}

		/**
		 * ����ģ��
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
