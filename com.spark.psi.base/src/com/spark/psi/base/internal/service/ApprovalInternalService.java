/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-20    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-20    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.ApprovalConfig;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.ApprovalConfig.Mode;
import com.spark.psi.base.event.ApprovalConfigChangedEvent;
import com.spark.psi.base.internal.entity.ApprovalConfigImpl;
import com.spark.psi.base.internal.entity.CfgExam;
import com.spark.psi.base.internal.entity.CfgExamTask;
import com.spark.psi.base.internal.service.orm.Orm_CfgExam;
import com.spark.psi.base.internal.service.query.QD_GetAllCfgExam;
import com.spark.psi.base.task.resource.UpdateApprovalConfigTask;

/**
 * <p>审批配置内部服务</p>
 *


 *
 
 * @version 2012-3-20
 */

public class ApprovalInternalService extends ResourceService<CfgExam, CfgExam, CfgExam>{
	
	final QD_GetAllCfgExam qD_GetAllCfgExam;
	
	final Orm_CfgExam orm_CfgExam;

	protected ApprovalInternalService(final QD_GetAllCfgExam qD_GetAllCfgExam,
			final Orm_CfgExam orm_CfgExam){
	    super("com.spark.psi.base.internal.service.ApprovalInternalService",ResourceKind.SINGLETON_IN_CLUSTER);
	    this.qD_GetAllCfgExam = qD_GetAllCfgExam;
	    this.orm_CfgExam = orm_CfgExam;
    }
	
	@Override
	protected void init(Context context){
		context.getList(CfgExam.class);
	}
	@Override
	protected void initResources(
	        Context context,
	        ResourceInserter<CfgExam, CfgExam, CfgExam> initializer)
	{
			RecordSet rs = context.openQuery(qD_GetAllCfgExam);
			while(rs.next()){
				CfgExam entity = new CfgExam();
				entity.setOpenExam(rs.getFields().get(0).getBoolean());
				entity.setMaxAmount(rs.getFields().get(1).getDouble());
				entity.setModeid(rs.getFields().get(2).getString());
				entity.setRecid(rs.getFields().get(3).getGUID());
				entity.setTenantsGuid(rs.getFields().get(4).getGUID());
				entity.setUpdatePerson(rs.getFields().get(5).getString());
				entity.setUpdateDate(rs.getFields().get(6).getLong());
				initializer.putResource(entity);
				createApprovalObject(context,entity);
			}
	}
	
	private void createApprovalObject(Context context, CfgExam cfgExam){
	    ApprovalConfigImpl entity = getApprovalConfigImpl(context.find(Tenant.class,cfgExam.getTenantsGuid()).getApprovalConfig());
	    if(Mode.BUY_ORDER.getId().equals(cfgExam.getModeid())){
	    	entity.setPurchaseOrderNeedApproval(cfgExam.isOpenExam());
	    	entity.setPurchaseApprovalLimit(cfgExam.getMaxAmount());	    	
	    }
	    if(Mode.BUY_RETURN.getId().equals(cfgExam.getModeid())){
			entity.setPurchaseReturnNeedApproval(cfgExam.isOpenExam());
			entity.setPurchaseReturnApprovalLimit(cfgExam.getMaxAmount());
	    }
	    if(Mode.SALES_BILLS.getId().equals(cfgExam.getModeid())){
			entity.setSalesOrderNeedApproval(cfgExam.isOpenExam());
			entity.setSalesApprovalLimit(cfgExam.getMaxAmount());
	    }
	    if(Mode.SALES_RETURN.getId().equals(cfgExam.getModeid())){
			entity.setSalesReturnNeedApproval(cfgExam.isOpenExam());
			entity.setSalesReturnApprovalLimit(cfgExam.getMaxAmount());
	    }
	    if(Mode.BLENDING.getId().equals(cfgExam.getModeid())){
			entity.setAllocateNeedApproval(cfgExam.isOpenExam());
	    } 	
	    context.handle(new UpdateApprovalConfigTask(entity, cfgExam.getTenantsGuid()));
    }
	
	private ApprovalConfigImpl getApprovalConfigImpl(ApprovalConfig config){
		ApprovalConfigImpl result = new ApprovalConfigImpl();
		result.setAllocateNeedApproval(config.isAllocateNeedApproval());
		result.setPurchaseApprovalLimit(config.getPurchaseApprovalLimit());
		result.setPurchaseOrderNeedApproval(config.isPurchaseOrderNeedApproval());
		result.setPurchaseReturnApprovalLimit(config.getPurchaseReturnApprovalLimit());
		result.setPurchaseReturnNeedApproval(config.isPurchaseReturnNeedApproval());
		result.setSalesApprovalLimit(config.getSalesApprovalLimit());
		result.setSalesOrderNeedApproval(config.isSalesOrderNeedApproval());
		result.setSalesReturnApprovalLimit(config.getSalesReturnApprovalLimit());
		result.setSalesReturnNeedApproval(config.isSalesReturnNeedApproval());
		return result;
	}
	
	final class GetCfgExamResourceProvider extends TwoKeyResourceProvider<GUID, String>{

		@Override
        protected GUID getKey1(CfgExam keysHolder){
	        return keysHolder.getTenantsGuid();
        }

		@Override
        protected String getKey2(CfgExam keysHolder){
	        return keysHolder.getModeid();
        }
		
	}

	/**
	 * 
	 * <p>修改租户审批配置</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-22
	 */
	@Publish
	protected class UpdateCfgExamHandler extends SimpleTaskMethodHandler<CfgExamTask>{

		@Override
        protected void handle(
                ResourceContext<CfgExam, CfgExam, CfgExam> context,
                CfgExamTask task) throws Throwable
        {
			ORMAccessor<CfgExam> acc = context.newORMAccessor(orm_CfgExam);
			CfgExam param = acc.findByPKey(task.getTenantsGuid(),task.getModeid().getId());
			
			if(param==null){
				param = new CfgExam();
				boolean isChange = task.isOpenExam()!=param.isOpenExam()||task.getMaxAmount()!=param.getMaxAmount();
				param.setTenantsGuid(task.getTenantsGuid());
				param.setRecid(context.newRECID());
				param.setModeid(task.getModeid().getId());
				param.setOpenExam(task.isOpenExam());
				param.setMaxAmount(task.getMaxAmount());
				param.setUpdatePerson(context.find(Employee.class).getName());
				param.setUpdateDate(System.currentTimeMillis());
				acc.insert(param);
				context.putResource(param);
				if(isChange)
					context.dispatch(new ApprovalConfigChangedEvent(task.getModeid()));
			}else{
				param = context.modifyResource(task.getTenantsGuid(), task.getModeid().getId());
				boolean isChange = task.isOpenExam()!=param.isOpenExam()||task.getMaxAmount()!=param.getMaxAmount();
				param.setMaxAmount(task.getMaxAmount());
				param.setOpenExam(task.isOpenExam());
				param.setUpdatePerson(context.find(Employee.class).getName());
				param.setUpdateDate(System.currentTimeMillis());
				param.setModeid(task.getModeid().getId());
				acc.update(param);
				context.postModifiedResource(param);
				if(isChange)
					context.dispatch(new ApprovalConfigChangedEvent(task.getModeid()));
			}	 
			createApprovalObject(context,param);
        }

		
	}

}
