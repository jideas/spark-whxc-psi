/**
 * 
 */
package com.spark.psi.report.queue;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.event.NoticeStatusChangeEvent;
import com.spark.psi.report.entity.ReportQueue;
import com.spark.psi.report.queue.orm.Orm_ReportQueue;
import com.spark.psi.report.task.QueueAddTask;
import com.spark.psi.report.task.QueueDeleteTask;
import com.spark.psi.report.utils.AttrXmlUtils;
import com.spark.psi.report.utils.QuerySqlBuilder;

/**
 * 消息队列机制
 */
public class TaskQueueManager extends Service {

	/**
	 * @param title
	 */
	protected TaskQueueManager(Orm_ReportQueue orm_rq) {
		super("TaskQueueManager");
		this.orm_rq = orm_rq;
	}

	private Orm_ReportQueue orm_rq;

	/**
	 * 向任务队列中新增一个任务
	 */
	@Publish
	protected class InsertTaskToQueue extends SimpleTaskMethodHandler<QueueAddTask> {

		@Override
		protected void handle(Context context, QueueAddTask task) throws Throwable {
			Event event = task.getEvent();
			if (null == event) {
				return;
			}
			ReportQueue entity = new ReportQueue();
			Login login = context.find(Login.class);
			if (task.getEvent().getClass().equals(NoticeStatusChangeEvent.class)) {
				NoticeStatusChangeEvent e = (NoticeStatusChangeEvent) task.getEvent();
				entity.setTenantsGuid(e.getTenantId());
			} else {
				Tenant tenant = context.find(Tenant.class);
				entity.setTenantsGuid(tenant.getId());
			}
			entity.setRECID(context.newRECID());
			entity.setCurrTime(System.currentTimeMillis());
			entity.setUserId(login.getEmployeeId());
			entity.setEventClass(event.getClass().getName());
			entity.setAttributeXml(AttrXmlUtils.createXml(event));
			ORMAccessor<ReportQueue> orm = context.newORMAccessor(orm_rq);
			try {
				orm.insert(entity);
				task.setCount(1);
			} finally {
				orm.unuse();
			}
		}
	}

	/**
	 * 向任务队列中新增一个任务
	 */
	@Publish
	protected class DeleteTaskOfQueue extends SimpleTaskMethodHandler<QueueDeleteTask> {

		@Override
		protected void handle(Context context, QueueDeleteTask task) throws Throwable {
			if (null == task.getRecid()) {
				return;
			}
			ORMAccessor<ReportQueue> orm = context.newORMAccessor(orm_rq);
			try {
				if (orm.delete(task.getRecid())) {
					task.setCount(1);
				}
			} finally {
				orm.unuse();
			}
		}

	}

	/**
	 * 查询当前持久化的任务队列
	 */
	@Publish
	protected class QueueListProvider extends ResultListProvider<ReportQueue> {

		@Override
		protected void provide(Context context, List<ReportQueue> list) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable("SA_SAAS_REPORT_QUEUE", "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.tenantId", "tenantId");
			qb.addColumn("t.eventClass", "eventClass");
			qb.addColumn("t.attributeXml", "attributeXml");
			qb.addColumn("t.currTime", "currTime");
			qb.addColumn("t.userId", "userId");
			qb.addOrderBy("t.currTime");
			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				ReportQueue rq = new ReportQueue();
				rq.setRECID(rs.getFields().get(0).getGUID());
				rq.setTenantsGuid(rs.getFields().get(1).getGUID());
				rq.setEventClass(rs.getFields().get(2).getString());
				rq.setAttributeXml(rs.getFields().get(3).getString());
				rq.setCurrTime(rs.getFields().get(4).getLong());
				rq.setUserId(rs.getFields().get(5).getGUID());
				list.add(rq);
			}

		}

	}

}
