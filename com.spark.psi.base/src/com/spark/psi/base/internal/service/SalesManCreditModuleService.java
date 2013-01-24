/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-5-17    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-5-17    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.internal.entity.ormentity.SalesManCreditOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_SalesManCredit;
import com.spark.psi.base.task.AddSalesManCreditUsedTask;
import com.spark.psi.base.task.config.RemoveSalesManCreidtUesdTask;
import com.spark.psi.base.task.resource.UpdateSalesCreditResourceTask;
import com.spark.psi.publish.Auth;

/**
 * <p>销售经理业务授权服务</p>
 *


 *
 
 * @version 2012-5-17
 */

public class SalesManCreditModuleService extends Service{
	
	final Orm_SalesManCredit orm_SalesManCredit;

	protected SalesManCreditModuleService(final Orm_SalesManCredit orm_SalesManCredit){
	    super("销售经理业务授权服务");
	    this.orm_SalesManCredit = orm_SalesManCredit;	    
    }
	
	/**
	 * 
	 * <p>增加销售经理已设置的信用额度及客户数量</p>
	 *
	
	
	 *
	 
	 * @version 2012-5-17
	 */
	@Publish
	protected final class AddSalesManCreidtUsedHandler extends SimpleTaskMethodHandler<AddSalesManCreditUsedTask>{

		@Override
        protected void handle(Context context, AddSalesManCreditUsedTask task)
                throws Throwable
        {
			Employee emp = context.find(Employee.class);
			if(!emp.hasAuth(Auth.SalesManager)||emp.hasAuth(Auth.Boss))return ;//如果没有销售经理职能，不做记录			
			ORMAccessor<SalesManCreditOrmEntity> acc = context.newORMAccessor(orm_SalesManCredit);
			SalesManCreditOrmEntity entity = acc.findByRECID(context.find(Login.class).getEmployeeId());
			if(entity==null){
				return ;
//				throw new IllegalArgumentException("当前用户没有配置业务授权");
//				entity = 
			}
			entity.setCustomerCountUsed(task.getCount()+entity.getCustomerCountUsed());
			entity.setCustomerCreditUsed(task.getAmount()+entity.getCustomerCreditUsed());
			acc.update(entity);
			context.handle(new UpdateSalesCreditResourceTask(entity.getId()),UpdateSalesCreditResourceTask.Method.Modify);
        }
		
	}

	/**
	 * 
	 * <p>减少销售经理已设置的信用额度</p>
	 *
	
	
	 *
	 
	 * @version 2012-5-17
	 */
	@Publish
	protected final class RemoveSalesManCreditUeseHandler extends SimpleTaskMethodHandler<RemoveSalesManCreidtUesdTask>{

		@Override
        protected void handle(Context context, RemoveSalesManCreidtUesdTask task)
                throws Throwable
        {
			if(task.getId()==null)return ;
			Employee emp = context.find(Employee.class,task.getId());
			if(emp==null)return ;
			if(!emp.hasAuth(Auth.SalesManager)||emp.hasAuth(Auth.Boss))return ;//如果没有销售经理职能，不做记录			
			ORMAccessor<SalesManCreditOrmEntity> acc = context.newORMAccessor(orm_SalesManCredit);
			SalesManCreditOrmEntity entity = acc.findByRECID(task.getId());
			if(entity==null){
				return ;
//				throw new IllegalArgumentException("指定用户没有配置业务授权");
			}
			entity.setCustomerCountUsed(entity.getCustomerCountUsed()-1);
			entity.setCustomerCreditUsed(entity.getCustomerCreditUsed()-task.getAmount());
			acc.update(entity);
			context.handle(new UpdateSalesCreditResourceTask(entity.getId()),UpdateSalesCreditResourceTask.Method.Modify);
        }
		
	}
	
}
