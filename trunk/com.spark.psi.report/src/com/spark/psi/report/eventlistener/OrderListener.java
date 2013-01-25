package com.spark.psi.report.eventlistener;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.order.intf.event.PromotionOrderChangedEvent;
import com.spark.order.intf.event.PurchaseOrderChangedEvent;
import com.spark.order.intf.event.PurchaseReturnChangedEvent;
import com.spark.order.intf.event.RetailOrderChangedEvent;
import com.spark.order.intf.event.RetailReturnChangedEvent;
import com.spark.order.intf.event.SalesOrderChangedEvent;
import com.spark.order.intf.event.SalesReturnChangedEvent;
import com.spark.psi.publish.deliver.Event.DeliverExceptionCreateEvent;
import com.spark.psi.publish.deliver.Event.DeliverExceptionOverEvent;
import com.spark.psi.report.task.QueueAddTask;

/**
 * ��������ģ���¼�
 */
public class OrderListener extends Service {

	protected OrderListener() {
		super("OrderListener");
	}

	@Publish
	class DeliveryListener1 extends EventListener<DeliverExceptionCreateEvent> {
		@Override
		protected void occur(Context context, DeliverExceptionCreateEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	}

	@Publish
	class DeliveryListener2 extends EventListener<DeliverExceptionOverEvent> {
		@Override
		protected void occur(Context context, DeliverExceptionOverEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	}

	/**
	 * �ɹ�����ģ�鷢���ı��¼�
	 */
	@Publish
	class PPa extends EventListener<PurchaseOrderChangedEvent> {

		@Override
		protected void occur(Context context, PurchaseOrderChangedEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	}

	/**
	 * �ɹ��˻������ı��¼�
	 */
	@Publish
	class PPb extends EventListener<PurchaseReturnChangedEvent> {

		@Override
		protected void occur(Context context, PurchaseReturnChangedEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	}

	/**
	 * ���۶���״̬�����ı��¼�
	 */
	@Publish
	class PPc extends EventListener<SalesOrderChangedEvent> {

		@Override
		protected void occur(Context context, SalesOrderChangedEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	}

	/**
	 * ���۸ı��¼�
	 */
	@Publish
	class PPd extends EventListener<RetailOrderChangedEvent> {

		@Override
		protected void occur(Context context, RetailOrderChangedEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	}

	/**
	 * �����ı��¼�
	 */
	@Publish
	class PPe extends EventListener<PromotionOrderChangedEvent> {

		@Override
		protected void occur(Context context, PromotionOrderChangedEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	}

	/**
	 * �����˻��ı��¼�
	 */
	@Publish
	class PPf extends EventListener<SalesReturnChangedEvent> {

		@Override
		protected void occur(Context context, SalesReturnChangedEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	}

	/**
	 * �����˻��¼�
	 */
	@Publish
	class PPg extends EventListener<RetailReturnChangedEvent> {

		@Override
		protected void occur(Context context, RetailReturnChangedEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	}
}
