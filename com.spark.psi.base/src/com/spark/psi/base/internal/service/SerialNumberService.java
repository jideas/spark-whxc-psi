package com.spark.psi.base.internal.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.entity.SerialNumberEntity;
import com.spark.psi.base.internal.service.orm.Orm_SerialNumber;
import com.spark.psi.base.internal.service.orm.Orm_SerialNumberByTenantAndType;
import com.spark.psi.base.task.resource.GenerateSerialNumberTask;

public class SerialNumberService extends ResourceService<Object, SerialNumberEntity, SerialNumberEntity> {

	private Orm_SerialNumber ormSerialNumber;
	private Orm_SerialNumberByTenantAndType ormSerialNumberByTenantAndType;

	protected SerialNumberService(Orm_SerialNumber ormSerialNumber,
			Orm_SerialNumberByTenantAndType ormSerialNumberByTenantAndType) {
		super("序列号服务");
		this.ormSerialNumber = ormSerialNumber;
		this.ormSerialNumberByTenantAndType = ormSerialNumberByTenantAndType;
	}

	@Override
	protected void initResources(Context context,
			ResourceInserter<Object, SerialNumberEntity, SerialNumberEntity> initializer) throws Throwable {
		List<SerialNumberEntity> entityList = context.newORMAccessor(ormSerialNumber).fetch();
		for (SerialNumberEntity entity : entityList) {
			initializer.putResource(entity);
		}
	}

	@Publish
	final class SerialNumberEntityProvider extends TwoKeyResourceProvider<String, String> {
		@Override
		protected String getKey1(SerialNumberEntity keysHolder) {
			return keysHolder.getType();
		}

		@Override
		protected String getKey2(SerialNumberEntity keysHolder) {
			return keysHolder.getPrefix();
		}
	}

	@Publish
	protected class GenerateSerialNumberTaskHandler extends SimpleTaskMethodHandler<GenerateSerialNumberTask> {

		@Override
		protected void handle(ResourceContext<Object, SerialNumberEntity, SerialNumberEntity> context,
				GenerateSerialNumberTask task) throws Throwable {
			SerialNumberEntity entity = null;
			synchronized (this) {
				try {
					entity = context.modifyResource(task.getType(), task.getPrefix());
				} catch (Throwable t) {
					// 清除历史数据
					ORMAccessor<SerialNumberEntity> ormAccessor = context
							.newORMAccessor(ormSerialNumberByTenantAndType);
					List<SerialNumberEntity> historyEntityList = ormAccessor.fetch(task.getType());
					for (SerialNumberEntity historyEntity : historyEntityList) {
						ormAccessor.delete(historyEntity);
						context.removeResource(historyEntity.getType(), historyEntity.getPrefix());
					}
					// 生成新的数据
					entity = new SerialNumberEntity();
					entity.setId(GUID.randomID());
					entity.setPrefix(task.getPrefix());
					entity.setType(task.getType());
					entity.setSerial(0);
					entity.setCreateTime(System.currentTimeMillis());
					context.newORMAccessor(ormSerialNumber).insert(entity);
					context.putResource(entity);
					entity = context.modifyResource(task.getType(), task.getPrefix());
				}
			}
			entity.setSerial(entity.getSerial() + 1);
			entity.setCreateTime(System.currentTimeMillis());
			context.newORMAccessor(ormSerialNumber).update(entity);
			context.putResource(entity);
			task.setResult(entity.getSerial());
		}
	}

}
