package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.service.orm.Orm_Monitor;
import com.spark.psi.base.publicimpl.MonitorImpl;
import com.spark.psi.publish.base.index.entity.Monitor;
import com.spark.psi.publish.base.index.task.MonitorTask;

public class MonitorService extends Service {
	
	private Orm_Monitor ormMonitor;

	protected MonitorService(Orm_Monitor ormMonitor) {
		super("com.spark.psi.base.internal.service.MonitorService");
		this.ormMonitor = ormMonitor;
	}
	
	@Publish
	protected class MonitorProvider extends OneKeyResultProvider<Monitor, GUID> {

		@Override
		protected Monitor provide(Context context, GUID key) throws Throwable {
			ORMAccessor<MonitorImpl> ormAccessor = context.newORMAccessor(ormMonitor);
			return ormAccessor.findByRECID(key);
		}
		
	}
	
	/**
	 * ÐÂÔö
	 * @author durendong
	 *
	 */
	@Publish
	protected class addMonitorHandler extends TaskMethodHandler<MonitorTask,MonitorTask.Method>{

		protected addMonitorHandler() {
			super(MonitorTask.Method.ADD);
		}

		@Override
		protected void handle(Context context, MonitorTask task)
				throws Throwable {
			MonitorImpl monitorImpl = new MonitorImpl();
			monitorImpl.setRecid(task.getRecid());
			monitorImpl.setMonitors(task.getMonitors());
			ORMAccessor<MonitorImpl> ormAccessor = context.newORMAccessor(ormMonitor);
			ormAccessor.insert(monitorImpl);
		}
		
	}
	
	/**
	 * ÐÞ¸Ä
	 * @author durendong
	 *
	 */
	@Publish
	protected class UpdateMonitorHandler extends TaskMethodHandler<MonitorTask, MonitorTask.Method>{

		protected UpdateMonitorHandler() {
			super(MonitorTask.Method.MODIFY);
		}

		@Override
		protected void handle(Context context, MonitorTask task)
				throws Throwable {
			ORMAccessor<MonitorImpl> ormAccessor = context.newORMAccessor(ormMonitor);
			MonitorImpl monitorImpl = new MonitorImpl();
			monitorImpl.setRecid(task.getRecid());
			monitorImpl.setMonitors(task.getMonitors());
			boolean success=ormAccessor.update(monitorImpl);
			if (!success) {
				ormAccessor.insert(monitorImpl);
			}
		}
		
	}

}
