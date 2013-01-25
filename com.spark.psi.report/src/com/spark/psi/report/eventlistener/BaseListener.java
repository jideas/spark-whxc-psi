package com.spark.psi.report.eventlistener;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
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
import com.spark.psi.report.task.QueueAddTask;

/**
 * ��������ģ���¼�����
 * 
 */
public class BaseListener extends Service {

	protected BaseListener() {
		super("BaseListener");
	}
	/**
	 * ��������ޱ仯
	 */
	@Publish
	class PPgs extends EventListener<MaterialsItemThresholdChangeEvent > {

		@Override
		protected void occur(Context context, MaterialsItemThresholdChangeEvent  event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * �����������ޱ仯
	 */
	@Publish
	class PPg extends EventListener<GoodsCategoryStoreLimitUpperChangeEvent> {

		@Override
		protected void occur(Context context, GoodsCategoryStoreLimitUpperChangeEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}

	}

	
	@Publish
	class Notice extends EventListener<NoticeStatusChangeEvent>{
		@Override
		protected void occur(Context context, NoticeStatusChangeEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	}
	
	@Publish
	class Notice2 extends EventListener<NoticeReadEvent>{
 
        @Override
        protected void occur(Context context, NoticeReadEvent event) throws Throwable{
        	context.handle(new QueueAddTask(event));
        }
		
	}
	/**
	 * Ա����ְ����ְ(Ա��ID)
	 */
	@Publish
	class PP extends EventListener<EmployeeStatusChangeEvent> {

		@Override
		protected void occur(Context context, EmployeeStatusChangeEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * Ա�����ű仯��Ա��ID��
	 */
	@Publish
	class PP1 extends EventListener<EmployeeDepartmentChangeEvent> {

		@Override
		protected void occur(Context context, EmployeeDepartmentChangeEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * ������÷����仯���Ķ���Χ��
	 */
	@Publish
	class PP2 extends EventListener<ApprovalConfigChangedEvent> {

		@Override
		protected void occur(Context context, ApprovalConfigChangedEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * ��Ʒ״̬�仯����ƷID��
	 */
	@Publish
	class PP3 extends EventListener<GoodsStatusChangeEvent> {

		@Override
		protected void occur(Context context, GoodsStatusChangeEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * �ֿ�״̬�仯���ֿ�ID��
	 */
	@Publish
	class PP4 extends EventListener<StoreStatusChangeEvent> {

		@Override
		protected void occur(Context context, StoreStatusChangeEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * ��˼�¼����ʱ�䣨������¼ID��
	 */
	@Publish
	class PP5 extends EventListener<ApprovalLogEvent> {

		@Override
		protected void occur(Context context, ApprovalLogEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}

	} 
	/**
	 * �ͻ���Ϣ�ı�ʱ���¼�
	 */
	@Publish
	class PP7 extends EventListener<CustomerDataChangeEvent> {

		@Override
		protected void occur(Context context, CustomerDataChangeEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	} /**
	 * �ͻ������¼�
	 */
	@Publish
	class PP8 extends EventListener<CustomerCreateEvent> {

		@Override
		protected void occur(Context context, CustomerCreateEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	} /**
	 * �ͻ�ת���¼�
	 */
	@Publish
	class PP9 extends EventListener<CustomerTurnOfficalEvent> {

		@Override
		protected void occur(Context context, CustomerTurnOfficalEvent event) throws Throwable {
			context.handle(new QueueAddTask(event));
		}
	} 
	
}
