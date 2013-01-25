/**
 * 
 */
package com.spark.psi.message.handles;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Login;
import com.spark.psi.message.entity.SMessageInfo;
import com.spark.psi.message.entity.SMessageMapping;
import com.spark.psi.message.orm.Orm_Message_Info;
import com.spark.psi.message.orm.Orm_Message_Mapping;
import com.spark.psi.message.task.SMessageDelTask;
import com.spark.psi.message.task.SMessageInfoTask;
import com.spark.psi.message.task.SMessageMappingTask;
import com.spark.psi.publish.smessage.task.SMessageBubbledTask;
import com.spark.psi.publish.smessage.task.SMessageBuddleTask;
import com.spark.psi.publish.smessage.task.SMessageMonitorTask;
import com.spark.psi.report.constant.ReportConstants.SMessageSet;
import com.spark.psi.report.utils.DeleteSqlBuilder;
import com.spark.psi.report.utils.InsertSqlBuilder;
import com.spark.psi.report.utils.QuerySqlBuilder;
import com.spark.psi.report.utils.UpdateSqlBuilder;

/**
 *
 */
public class MessageService extends Service{
	/**
	 * @param title
	 */
	protected MessageService(Orm_Message_Info orm_info, Orm_Message_Mapping orm_map){
		super("com.spark.psi.message.handles.MessageService");
		this.orm_info = orm_info;
		this.orm_map = orm_map;
	}

	private Orm_Message_Info orm_info;
	private Orm_Message_Mapping orm_map;

	@Publish
	protected class InsertMessageInfoHandle extends SimpleTaskMethodHandler<SMessageInfoTask>{

		@Override
		protected void handle(Context context, SMessageInfoTask task) throws Throwable{
			ORMAccessor<SMessageInfo> orm = context.newORMAccessor(orm_info);
			try{
				orm.insert(task.getInfo());
			}
			finally{
				orm.unuse();
			}
		}
	}

	@Publish
	protected class InsertMessageMappingHandle extends SimpleTaskMethodHandler<SMessageMappingTask>{
		@Override
		protected void handle(Context context, SMessageMappingTask task) throws Throwable{
			ORMAccessor<SMessageMapping> orm = context.newORMAccessor(orm_map);
			try{
				orm.insert(task.getMapping());
			}
			finally{
				orm.unuse();
			}
		}
	}

	@Publish
	protected class DeleteMessageInfoHandle extends SimpleTaskMethodHandler<SMessageDelTask>{

		@Override
		protected void handle(Context context, SMessageDelTask task) throws Throwable{
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable("SA_MESSAGE_INFO", "t");
			qb.addColumn("t.RECID", "RECID");
			if(null != task.getObjId()){
				qb.addArgs("id", qb.guid, task.getObjId());
				qb.addEquals("t.relaObjId", "@id");
			}
			if(null != task.getTemp()){
				qb.addArgs("temp", qb.STRING, task.getTemp().getCode());
				qb.addEquals("t.templateCode", "@temp");
			}
			if(task.getType() != null){
				qb.addArgs("type", qb.STRING, task.getType().getCode());
				qb.addEquals("t.messageType", "@type");
			} 
			RecordSet rs = qb.getRecord();
			if(task.getUserId() != null){
				while(rs.next()){
					DeleteSqlBuilder db = new DeleteSqlBuilder(context);
					GUID id = rs.getFields().get(0).getGUID();
					db.setTable("SA_MESSAGE_MAPPING");
					db.addEquals("userId", db.guid, task.getUserId());
					db.addEquals("messageType", db.STRING, task.getType().getCode());
					db.addEquals("messageId", db.guid, id);
					db.execute();
				}
				return;
			}
			while(rs.next()){
				GUID id = rs.getFields().get(0).getGUID();
				DeleteSqlBuilder db = new DeleteSqlBuilder(context);
				db.setTable("SA_MESSAGE_MAPPING");
				db.addEquals("messageId", db.guid, id);
				db.execute();
			}

			DeleteSqlBuilder db = new DeleteSqlBuilder(context);
			db.setTable("SA_MESSAGE_INFO");
			if(null != task.getObjId()){
				db.addEquals("relaObjId", db.guid, task.getObjId());
			}
			if(task.getType() != null){
				db.addEquals("messageType", db.STRING, task.getType().getCode());
			}
			if(null != task.getTemp()){
				db.addEquals("templateCode", db.STRING, task.getTemp().getCode());
			}
			db.execute();
		}
	}

	@Publish
	protected class BuddleSetHandle extends SimpleTaskMethodHandler<SMessageBuddleTask>{

		@Override
		protected void handle(Context context, SMessageBuddleTask task) throws Throwable{
			Login login = context.find(Login.class);
			if(task.isBubbling()){
				DeleteSqlBuilder dsb = new DeleteSqlBuilder(context);
				dsb.setTable("SA_MESSAGE_SET");
				dsb.addEquals("setType", dsb.STRING, SMessageSet.Bubbling.getCode());
				dsb.addEquals("messageType", dsb.STRING, task.getType().getCode());
				dsb.addEquals("userId", dsb.guid, login.getEmployeeId());
				dsb.execute();
				return;
			}
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable("SA_MESSAGE_SET");
			ib.addColumn("setType", ib.STRING, SMessageSet.Bubbling.getCode());
			ib.addColumn("RECID", ib.guid, context.newRECID()); 
			ib.addColumn("userId", ib.guid, login.getEmployeeId());
			ib.addColumn("messageType", ib.STRING, task.getType().getCode());
			ib.addColumn("bvalue", ib.BOOLEAN, false);
			ib.execute();
		}

	}

	@Publish
	protected class MonitorSetHandle extends SimpleTaskMethodHandler<SMessageMonitorTask>{

		@Override
		protected void handle(Context context, SMessageMonitorTask task) throws Throwable{
			Login login = context.find(Login.class);

			DeleteSqlBuilder dsb = new DeleteSqlBuilder(context);
			dsb.setTable("SA_MESSAGE_SET");
			dsb.addEquals("setType", dsb.STRING, SMessageSet.Monitor.getCode());
			dsb.addEquals("messageType", dsb.STRING, task.getType().getCode());
			dsb.addEquals("userId", dsb.guid, login.getEmployeeId());
			dsb.execute();

			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable("SA_MESSAGE_SET");
			ib.addColumn("RECID", ib.guid, context.newRECID());
			ib.addColumn("setType", ib.STRING, SMessageSet.Monitor.getCode()); 
			ib.addColumn("userId", ib.guid, login.getEmployeeId());
			ib.addColumn("messageType", ib.STRING, task.getType().getCode());
			ib.addColumn("bvalue", ib.BOOLEAN, task.isShowMonitor());
			ib.execute();
		}

	}

	@Publish
	protected class MonitorBubbledHandle extends SimpleTaskMethodHandler<SMessageBubbledTask>{

		@Override
		protected void handle(Context context, SMessageBubbledTask task) throws Throwable{
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable("SA_MESSAGE_MAPPING");
			ub.addColumn("showFlag", ub.BOOLEAN, true);
			ub.addCondition("ID", ub.guid, task.getId(), "t.RECID=@ID");
			ub.execute();
		}
	}
}
