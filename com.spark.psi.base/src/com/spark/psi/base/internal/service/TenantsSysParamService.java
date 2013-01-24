/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.tenants.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-17       周利均        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.tenants.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-17       周利均        
 * ============================================================*/

package com.spark.psi.base.internal.service;


import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.TenantSysParam;
import com.spark.psi.base.internal.service.orm.Orm_TenantsSysParam;
import com.spark.psi.base.internal.service.query.QD_GetAllSysParam;
import com.spark.psi.base.task.TenantsSysParamTask;
import com.spark.psi.base.task.resource.UpdateTenantSysParamTask;
import com.spark.psi.publish.SysParamKey;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 周利均
 * @version 2011-11-17
 */

public class TenantsSysParamService extends ResourceService<TenantSysParam, TenantSysParam, TenantSysParam> {

	protected final Orm_TenantsSysParam q_Orm_TenantsSysParam;
	
	final QD_GetAllSysParam qD_GetAllSysParam;

	protected TenantsSysParamService(Orm_TenantsSysParam qOrmTenantsSysParam,final QD_GetAllSysParam qD_GetAllSysParam){
	    super("TenantsSysParamService",ResourceKind.SINGLETON_IN_CLUSTER);
	    q_Orm_TenantsSysParam = qOrmTenantsSysParam;
	    this.qD_GetAllSysParam = qD_GetAllSysParam;
    }

	
	@Override
	protected void init(Context context){
		context.find(TenantSysParam.class);
	}
	
	@Override
	protected void initResources(Context context,
			ResourceInserter<TenantSysParam, TenantSysParam, TenantSysParam> initializer)
			throws Throwable {
		RecordSet rs = context.openQuery(qD_GetAllSysParam);
		while(rs.next()){
			TenantSysParam entity = new TenantSysParam();
			entity.setKey(rs.getFields().get(0).getString());
			entity.setYes(rs.getFields().get(1).getBoolean());
			entity.setTenantId(rs.getFields().get(2).getGUID());
			initializer.putResource(entity);	
		}
	}
    
	@Publish
	protected class UpdateTenantsSysParamHandler extends SimpleTaskMethodHandler<TenantsSysParamTask>{


		@Override
        protected void handle(
                ResourceContext<TenantSysParam, TenantSysParam, TenantSysParam> context,
                TenantsSysParamTask task) throws Throwable
        {
			ORMAccessor<TenantSysParam> acc = context.newORMAccessor(q_Orm_TenantsSysParam);
			Tenant tr = context.find(Tenant.class);
			TenantSysParam param = acc.findByPKey(tr.getId(),task.getKey().toString());
			if(param==null){
				param = new TenantSysParam();
				param.setTenantId(tr.getId());
				param.setId(context.newRECID());
				param.setKey(task.getKey().toString());
				param.setYes(task.getYes());
				acc.insert(param);
				context.putResource(param);
			}else{
				param = context.modifyResource(tr.getId(), task.getKey().toString());
				param.setYes(task.getYes());
				acc.update(param);
				context.postModifiedResource(param);
			}
			context.handle(new UpdateTenantSysParamTask(param));
         }
		
	}
	
	final class GetTenantsSysParamResourceProvider extends TwoKeyResourceProvider<GUID, String>{

		@Override
        protected GUID getKey1(TenantSysParam keysHolder){
	        return keysHolder.getTenantId();
        }

		@Override
        protected String getKey2(TenantSysParam keysHolder){
	        return keysHolder.getKey();
        }
		
	}
	
	@Publish
	protected class GetTenantsSysParamProvider extends TwoKeyResultProvider<TenantSysParam, GUID, SysParamKey>{


		@Override
        protected TenantSysParam provide(
                ResourceContext<TenantSysParam, TenantSysParam, TenantSysParam> context,
                GUID tenantsGuid, SysParamKey key) throws Throwable
        {
			ResourceToken<TenantSysParam> token = context.findResourceToken(TenantSysParam.class,tenantsGuid,key.toString());
			if(token!=null){
				return token.getFacade();
			}else{
				return new TenantSysParam(tenantsGuid,key);
			}
       }
		
	}

}
