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

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Partner;
import com.spark.psi.base.SalesmanCredit;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.SalesmanCreditImpl;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.internal.entity.ormentity.SalesManCreditOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_SalesManCredit;
import com.spark.psi.base.key.organization.GetDepartmentFullPathNameKey;
import com.spark.psi.base.publicimpl.SalesManCreditItemImpl;
import com.spark.psi.base.task.resource.UpdateSalesCreditResourceTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.config.entity.SalesmanCreditInfo;
import com.spark.psi.publish.base.config.entity.SalesmanCreditItem;
import com.spark.psi.publish.base.config.key.GetSalesManCreditListKey;
import com.spark.psi.publish.base.config.key.GetSalesManResidualCreditAmountKey;
import com.spark.psi.publish.base.config.task.SaveSalesmanCreditTask;
import com.spark.psi.publish.base.config.task.SaveSalesmanCreditTask.CreditItem;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * <p>销售授权公共服务</p>
 *


 *
 
 * @version 2012-4-24
 */

public class SalesManCreditPublishService extends Service{

	final Orm_SalesManCredit orm_SalesManCredit;
	
	protected SalesManCreditPublishService(final Orm_SalesManCredit orm_SalesManCredit){
	    super("销售员工授权公共服务");
	    this.orm_SalesManCredit = orm_SalesManCredit;
    }
	
	/**
	 * 获
	 * <p>获得销售员工授权配置列表</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-24
	 */
	@Publish
	protected final class GetSalesManCreditListProvider extends OneKeyResultProvider<ListEntity<SalesmanCreditItem>,GetSalesManCreditListKey>{

		@Override
        protected ListEntity<SalesmanCreditItem> provide(Context context,GetSalesManCreditListKey key) throws Throwable
        {
			List<SalesmanCreditItem> list = new ArrayList<SalesmanCreditItem>();
			ResourceToken<Tenant> tenant = TenantHelper.getTenantToken(context);
			for(Employee emp : context.getResourceReferences(Employee.class, tenant)){
				if(!emp.hasAuth(Auth.SalesManager)||emp.hasAuth(Auth.Boss))continue;
				SalesmanCredit c = context.find(SalesmanCredit.class,emp.getId());
				if(c==null){
					c = new SalesmanCreditImpl();
				}
				SalesManCreditItemImpl entity = getSalesManCreditItemImpl(context, c);
		        entity.setSalesmanId(emp.getId());
		        entity.setSalesmanName(emp.getName());
		        entity.setDepartmentInfo(context.find(String.class,new GetDepartmentFullPathNameKey(emp.getDepartmentId())));
		        list.add(entity);
            }
	        return new ListEntity<SalesmanCreditItem>(list,list.size());
        }		
	}
	
	/**
	 * 
	 * <p>获得销售经理授权对象</p>
	 *
	
	
	 *
	 
	 * @version 2012-5-16
	 */
	@Publish
	protected final class GetSalesManCreditInfoProvider extends OneKeyResultProvider<SalesmanCreditInfo, GUID>{

		@Override
        protected SalesmanCreditInfo provide(Context context, GUID key)
                throws Throwable
        {
			SalesmanCredit c = context.find(SalesmanCredit.class,key);
			if(c==null)return new SalesManCreditItemImpl();
			SalesManCreditItemImpl entity = getSalesManCreditItemImpl(context, c);
			EmployeeInfo emp = context.find(EmployeeInfo.class,key);
	        entity.setSalesmanId(emp.getId());
	        entity.setSalesmanName(emp.getName());
	        entity.setDepartmentInfo(context.find(String.class,new GetDepartmentFullPathNameKey(emp.getDepartmentId())));			
	        return entity;
        }
		
	}
	
	private SalesManCreditItemImpl getSalesManCreditItemImpl(Context context,SalesmanCredit c){
		if(c==null){
			c = new SalesmanCreditImpl();
		}
		SalesManCreditItemImpl entity = new SalesManCreditItemImpl();
        entity.setCustomerCreditLimit(c.getCustomerCreditLimit());
        entity.setAvailableTotalCreditLimit(c.getAvailableTotalCreditLimit());
        entity.setCustomerCountUsed(c.getCustomerCountUsed());
        entity.setCustomerCreditUsed(c.getCustomerCreditUsed());
        entity.setCustomerCreditDayLimit(c.getCustomerCreditDayLimit());
        entity.setOrderApprovalLimit(c.getOrderApprovalLimit());
        return entity;
	}
	

	/**
	 * 
	 * <p>保存销售员工授权</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-24
	 */
	@Publish
	protected final class SaveSalesManCreditHandler extends SimpleTaskMethodHandler<SaveSalesmanCreditTask>{

		@Override
        protected void handle(Context context, SaveSalesmanCreditTask task)
                throws Throwable
        {
			ORMAccessor<SalesManCreditOrmEntity> acc = context.newORMAccessor(orm_SalesManCredit);
			Tenant tenant = context.find(Tenant.class);
	        for(CreditItem item : task.getItems()){
	           boolean isNew = false;
	           SalesManCreditOrmEntity entity = acc.findByRECID(item.getSalesmanId());
	           if(entity==null){
	        	   isNew = true;
	        	   entity = new SalesManCreditOrmEntity();
	           }
	           entity.setId(item.getSalesmanId());
	           entity.setTenantId(tenant.getId());
		       entity.setCustomerCreditLimit(item.getCustomerCreditLimit());
		       entity.setAvailableTotalCreditLimit(item.getAvailableTotalCreditLimit());
		       entity.setCustomerCreditDayLimit(item.getCustomerCreditDayLimit());
		       entity.setOrderApprovalLimit(item.getOrderApprovalLimit());
		       if(isNew){
		    	   acc.insert(entity);
		    	   context.handle(new UpdateSalesCreditResourceTask(entity.getId()),UpdateSalesCreditResourceTask.Method.Put);
		       }else{
		    	   acc.update(entity);
		    	   context.handle(new UpdateSalesCreditResourceTask(entity.getId()),UpdateSalesCreditResourceTask.Method.Modify);
		       }
            }
        }		
	}
	
	/**
	 * 
	 * <p>查询指定销售经理剩余的可设置信用额度</p>
	 *
	
	
	 *
	 
	 * @version 2012-5-18
	 */
	@Publish
	protected final class GetSalesManResidualCreditAmountProvider extends OneKeyResultProvider<Double, GetSalesManResidualCreditAmountKey>{

		@Override
        protected Double provide(Context context,
                GetSalesManResidualCreditAmountKey key) throws Throwable
        {
			
			Employee employee;
			if(key.getId()==null){
				employee = context.find(Employee.class);
			}else{
				employee = context.find(Employee.class,key.getId());
			}
			SalesmanCredit sc = context.find(SalesmanCredit.class,employee.getId());
			if(sc==null)sc = new SalesmanCreditImpl();
			double kszz =
			        sc.getAvailableTotalCreditLimit()
			                - sc.getCustomerCreditUsed();
			if(key.getCustomers()!=null){
				for(GUID customer : key.getCustomers()){
					Partner partner = context.find(Partner.class, customer);
//					if(employee.getId().equals(partner.getCreditLinePerson())){ //本人自己设置的客户，不列入累计
//						kszz += partner.getCreditAmount();
//						break;
//					}
				}
			}
	        return kszz;
        }
		
	}
	
}
