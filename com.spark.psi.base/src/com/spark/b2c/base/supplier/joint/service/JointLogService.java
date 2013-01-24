package com.spark.b2c.base.supplier.joint.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.b2c.publish.JointVenture.entity.JointVentureLogItem;
import com.spark.b2c.publish.JointVenture.entity.JointVentureLogListEntity;
import com.spark.b2c.publish.JointVenture.key.GetJointVentureLogListKey;
import com.spark.b2c.publish.JointVenture.task.JointVentureLogTask;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.psi.base.internal.entity.ormentity.JointVentureLogOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_JointVentureLog;
/**
 * 
 * 联营材料信息记录Service
 *
 */
public class JointLogService extends Service {

	final Orm_JointVentureLog orm_JointVentureLog;
	final static String jointVentureLogTable = ERPTableNames.Joint.Materials_Joint_Log.getTableName();
	protected JointLogService(Orm_JointVentureLog orm_JointVentureLog) {
		super("com.spark.b2c.base.supplier.joint.service.JointLogService");
		this.orm_JointVentureLog = orm_JointVentureLog;
	}

	@Publish
	protected class Create extends TaskMethodHandler<JointVentureLogTask, JointVentureLogTask.Method>
	{

		protected Create() {
			super(JointVentureLogTask.Method.Create);
		}

		@Override
		protected void handle(Context context, JointVentureLogTask task) throws Throwable {
			ORMAccessor<JointVentureLogOrmEntity> orm = context.newORMAccessor(orm_JointVentureLog);
			JointVentureLogOrmEntity entity = new JointVentureLogOrmEntity();
			BeanUtils.copyProperties(task, entity);
			if (task.getPercentage() != null) {
				entity.setPercentage(task.getPercentage().doubleValue());
			}
			entity.setBeginDate(System.currentTimeMillis());
			entity.setId(context.newRECID());
			
			orm.insert(entity);
			orm.unuse();
		}
		
	}
	
	@Publish
	protected class Update extends TaskMethodHandler<JointVentureLogTask, JointVentureLogTask.Method>
	{

		protected Update() {
			super(JointVentureLogTask.Method.Update);
		}

		@Override
		protected void handle(Context context, JointVentureLogTask task) throws Throwable {
			if(CheckIsNull.isEmpty(task.getSupplierId())||CheckIsNull.isEmpty(task.getMaterialId())||CheckIsNull.isEmpty(task.getPercentage()))
			{
				throw new Throwable("供应商ID||材料ID||提成不能为空！");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define update updateJointVentureLog(@supplierId guid,@materialId guid,@percentage double,@endDate date)\n");
			sql.append("begin\n");
			sql.append("update \n");
			sql.append(jointVentureLogTable).append(" as t\n");
			sql.append(" set endDate=@endDate\n");
			sql.append("where t.supplierId=@supplierId\n");
			sql.append(" and t.materialId=@materialId\n");
			sql.append(" and t.percentage=@percentage\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			db.setArgumentValues(task.getSupplierId(),task.getMaterialId(),task.getPercentage(),System.currentTimeMillis());
			
			db.executeUpdate();
			db.unuse();
		}
		
	}
	
	@Publish
	protected class GetJointVentureLogList extends OneKeyResultProvider<JointVentureLogListEntity, GetJointVentureLogListKey>
	{

		@Override
		protected JointVentureLogListEntity provide(Context context, GetJointVentureLogListKey key) throws Throwable {
			List<JointVentureLogItem> dataList = JointSeviceUtil.getJointVentureLogItems(context,key);
			
			return new JointVentureLogListEntity(dataList, dataList.size());
		}
		
	}
}
