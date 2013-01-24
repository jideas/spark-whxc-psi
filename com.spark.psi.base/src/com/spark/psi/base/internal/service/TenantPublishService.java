package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.service.Publish.Mode;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.internal.entity.ormentity.TenantOrmEntity;
import com.spark.psi.base.internal.service.query.QD_GetTenantById;
import com.spark.psi.base.task.CreateBossTask;
import com.spark.psi.base.task.organization.CreateDepartmentTask;
import com.spark.psi.base.task.resource.UpdateTenantResourceTask;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.uac.publish.task.UpdateSubServerTenantTask;

public class TenantPublishService extends Service {
	
	final QD_GetTenantById qD_GetTenantById;

	protected TenantPublishService(final QD_GetTenantById qD_GetTenantById) {
		super("租户数据服务");
		this.qD_GetTenantById = qD_GetTenantById;
	}
	
	/**
	 * 
	 * <p>创建租户</p>
	 *
	
	
	 *
	 
	 * @version 2012-6-8
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class CreateTenantHandler extends SimpleTaskMethodHandler<UpdateSubServerTenantTask>{

		@Override
        protected void handle(Context context, UpdateSubServerTenantTask task)
                throws Throwable
        {
			ORMAccessor<TenantOrmEntity> acc = context.newORMAccessor(qD_GetTenantById);
			TenantOrmEntity entity = new TenantOrmEntity();
			entity.setId(task.getId());
			entity.setConfig(task.getConfig());
			if(acc.findByRECID(task.getId()) == null){
				acc.insert(entity);
				context.handle(new UpdateTenantResourceTask(entity.getId()),UpdateTenantResourceTask.Method.Put);
				context.handle(new CreateDepartmentTask(entity.getId(), entity.getConfig()));
				context.handle(new CreateBossTask(entity.getId(),entity.getConfig()));
			}else{
				acc.update(entity);
				context.handle(new UpdateTenantResourceTask(entity.getId()),UpdateTenantResourceTask.Method.Modify);
				context.handle(new CreateDepartmentTask(entity.getId(), entity.getConfig()));
			}			
        }		
	}
	
//	/**
//	 * 
//	 * <p>修改租户</p>
//	 *
//	
//	
//	 *
//	 
//	 * @version 2012-6-8
//	 */
//	@Deprecated
//	@Publish(Mode.SITE_PUBLIC)
//	protected final class ModfiyTenantHandler extends SimpleTaskMethodHandler<UpdateSubServerTenantTask, UpdateSubServerTenantTask.Method>{
//
//		protected ModfiyTenantHandler(){
//	        super(UpdateSubServerTenantTask.Method.Update);
//        }
//
//		@Override
//        protected void handle(Context context, UpdateSubServerTenantTask task)
//                throws Throwable
//        {
//			ORMAccessor<TenantOrmEntity> acc = context.newORMAccessor(qD_GetTenantById);
//			TenantOrmEntity entity = acc.findByRECID(task.getId());
//			entity.setId(task.getId());
//			entity.setConfig(task.getConfig());
//			if(!acc.update(entity)){
//				throw new IllegalArgumentException("没有找到这个id的租户");
//			}
//			context.handle(new UpdateTenantResourceTask(entity.getId()),UpdateTenantResourceTask.Method.Modify);
//			context.handle(new CreateDepartmentTask(entity.getId(), entity.getConfig()));
//        }
//		
//	}

	
	/**
	 * 
	 * <p>获得租户</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-26
	 */
	@Publish
	protected final class GetTenantProvider extends ResultProvider<TenantInfo>{

		@Override
        protected TenantInfo provide(Context context) throws Throwable{
	        return TenantHelper.TenantResourceToTenantInfo(TenantHelper.getTenant(context));
        }		
	}
	/**
	 * 
	 * <p>获得租户</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-26
	 */
	@Publish
	protected final class GetTenantProvider1 extends OneKeyResultProvider<TenantInfo,GUID>{

		@Override
        protected TenantInfo provide(Context context,GUID key) throws Throwable{
	        return TenantHelper.TenantResourceToTenantInfo(context.find(Tenant.class,key));
        }		
	}

}
