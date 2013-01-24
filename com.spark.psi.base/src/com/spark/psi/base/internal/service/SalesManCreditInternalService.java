/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-24    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-24    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Login;
import com.spark.psi.base.SalesmanCredit;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.SalesmanCreditImpl;
import com.spark.psi.base.internal.entity.ormentity.SalesManCreditOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_SalesManCredit;
import com.spark.psi.base.internal.service.orm.Orm_SalesManCreditByUserId;
import com.spark.psi.base.task.resource.UpdateSalesCreditResourceTask;


/**
 * <p>销售人员信用配置服务</p>
 *  RECID就是用户id


 *
 
 * @version 2012-4-24
 */

public class SalesManCreditInternalService extends ResourceService<SalesmanCredit,SalesmanCreditImpl,SalesmanCreditImpl>{

	final Orm_SalesManCredit orm_SalesManCredit;
	final Orm_SalesManCreditByUserId orm_SalesManCreditByUserId;
	
	protected SalesManCreditInternalService(Orm_SalesManCredit orm_SalesManCredit,Orm_SalesManCreditByUserId orm_SalesManCreditByUserId){
	    super("销售人员信用配置服务",ResourceKind.SINGLETON_IN_CLUSTER);
	    this.orm_SalesManCredit = orm_SalesManCredit;
	    this.orm_SalesManCreditByUserId = orm_SalesManCreditByUserId;
    }
	
	@Override
	protected void initResources(Context context,
			ResourceInserter<SalesmanCredit,SalesmanCreditImpl,SalesmanCreditImpl> initializer){
		List<SalesManCreditOrmEntity> list = context.newORMAccessor(orm_SalesManCredit).fetch();
		for(SalesManCreditOrmEntity c : list){
	        SalesmanCreditImpl entity = new SalesmanCreditImpl();
	        entity.setId(c.getId());
	        entity.setCustomerCreditLimit(c.getCustomerCreditLimit());
	        entity.setAvailableTotalCreditLimit(c.getAvailableTotalCreditLimit());
	        entity.setCustomerCountUsed(c.getCustomerCountUsed());
	        entity.setCustomerCreditUsed(c.getCustomerCreditUsed());
	        entity.setCustomerCreditDayLimit(c.getCustomerCreditDayLimit());
	        entity.setOrderApprovalLimit(c.getOrderApprovalLimit());
	        ResourceToken<Tenant> parentToken = context.findResourceToken(Tenant.class,c.getTenantId());
	        ResourceToken<SalesmanCredit> token = initializer.putResource(entity);
	        initializer.putResourceReference(parentToken, token);
        }
	}
	
	/**
	 * 
	 * <p>根据用户id获得配置信息</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-24
	 */
	protected final class GetSalesManCreditByUserId extends OneKeyResourceProvider<GUID>{

		@Override
        protected GUID getKey1(SalesmanCreditImpl keysHolder){
	        return keysHolder.getId();
        }		
	}
	
	@Publish
	protected final class GetSalesManCreditByLoginProvider extends	ResultProvider<SalesmanCredit>{

		@Override
        protected SalesmanCredit provide(
                ResourceContext<SalesmanCredit, SalesmanCreditImpl, SalesmanCreditImpl> context)
                throws Throwable
        {
	        return context.find(SalesmanCredit.class,context.find(Login.class).getEmployeeId());
        }
		
	}
	
	@Publish
	protected final class CreateSalesManCreditHandler extends TaskMethodHandler<UpdateSalesCreditResourceTask, UpdateSalesCreditResourceTask.Method>{

		protected CreateSalesManCreditHandler(){
	        super(UpdateSalesCreditResourceTask.Method.Put);
        }

		@Override
        protected void handle(
                ResourceContext<SalesmanCredit, SalesmanCreditImpl, SalesmanCreditImpl> context,
                UpdateSalesCreditResourceTask task) throws Throwable
        {
			SalesManCreditOrmEntity c = context.newORMAccessor(orm_SalesManCreditByUserId).findByRECID(task.getId());
	        SalesmanCreditImpl entity = new SalesmanCreditImpl();
	        entity.setId(c.getId());
	        entity.setCustomerCreditLimit(c.getCustomerCreditLimit());
	        entity.setAvailableTotalCreditLimit(c.getAvailableTotalCreditLimit());
	        entity.setCustomerCountUsed(c.getCustomerCountUsed());
	        entity.setCustomerCreditUsed(c.getCustomerCreditUsed());
	        entity.setCustomerCreditDayLimit(c.getCustomerCreditDayLimit());
	        entity.setOrderApprovalLimit(c.getOrderApprovalLimit());
	        ResourceToken<Tenant> parentToken = context.findResourceToken(Tenant.class,c.getTenantId());
	        context.putResourceReference(parentToken, context.putResource(entity));
			
        }
		
	}
	
	@Publish
	protected final class ModifySalesManCreditHandler extends TaskMethodHandler<UpdateSalesCreditResourceTask, UpdateSalesCreditResourceTask.Method>{

		protected ModifySalesManCreditHandler(){
	        super(UpdateSalesCreditResourceTask.Method.Modify);
        }

		@Override
        protected void handle(
                ResourceContext<SalesmanCredit, SalesmanCreditImpl, SalesmanCreditImpl> context,
                UpdateSalesCreditResourceTask task) throws Throwable
        {
			SalesManCreditOrmEntity c = context.newORMAccessor(orm_SalesManCreditByUserId).findByRECID(task.getId());
	        SalesmanCreditImpl entity = context.modifyResource(task.getId());
	        entity.setId(c.getId());
	        entity.setCustomerCreditLimit(c.getCustomerCreditLimit());
	        entity.setAvailableTotalCreditLimit(c.getAvailableTotalCreditLimit());
	        entity.setCustomerCountUsed(c.getCustomerCountUsed());
	        entity.setCustomerCreditUsed(c.getCustomerCreditUsed());
	        entity.setCustomerCreditDayLimit(c.getCustomerCreditDayLimit());
	        entity.setOrderApprovalLimit(c.getOrderApprovalLimit());
	        context.postModifiedResource(entity);      
        }
		
	}
	
	@Publish
	protected final class RemoveSalesManCreditHandler extends TaskMethodHandler<UpdateSalesCreditResourceTask, UpdateSalesCreditResourceTask.Method>{

		protected RemoveSalesManCreditHandler(){
	        super(UpdateSalesCreditResourceTask.Method.Remove);
        }

		@Override
        protected void handle(
                ResourceContext<SalesmanCredit, SalesmanCreditImpl, SalesmanCreditImpl> context,
                UpdateSalesCreditResourceTask task) throws Throwable
        {
			ResourceToken<SalesmanCredit> token = context.findResourceToken(SalesmanCredit.class,task.getId());
	        ResourceToken<Tenant> parentToken = context.findResourceToken(Tenant.class,context.find(Login.class).getTenantId());
	        context.removeResourceReference(parentToken, token);
	        context.removeResource(task.getId());
        }
		
	}
	
}
