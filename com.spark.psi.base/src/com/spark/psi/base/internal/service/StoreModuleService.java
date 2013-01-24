/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-12    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-12    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Store;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.event.EmployeeAuthChangeEvent;
import com.spark.psi.base.event.EmployeeStatusChangeEvent;
import com.spark.psi.base.event.EmployeeStatusChangeEvent.Action;
import com.spark.psi.base.internal.entity.StoreEmployee;
import com.spark.psi.base.internal.entity.StoreEmployee.StoreEmployeeType;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.internal.service.orm.Orm_StoreEmployeeByEmpId;
import com.spark.psi.base.key.GetAllStoreListKey;
import com.spark.psi.base.key.GetAncestorDeptEmployeeListByAuthKey;
import com.spark.psi.base.key.GetChildrenDeptEmployeeListByAuthKey;
import com.spark.psi.base.key.GetStoreBySaleManKey;
import com.spark.psi.base.key.GetStoreListByManagerKey;
import com.spark.psi.base.key.organization.GetDescendantDepartmentListKey;
import com.spark.psi.base.key.store.GetUserStoreGuidsKey;
import com.spark.psi.base.task.resource.UpdateStoreResourceTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.StoreStatus;

/**
 * <p>仓库相关  模块内部服务</p>
 *


 *
 
 * @version 2012-3-12
 */

public class StoreModuleService extends Service{
	
	final Orm_StoreEmployeeByEmpId orm_StoreEmployeeByEmpId;
	
	protected StoreModuleService(final Orm_StoreEmployeeByEmpId orm_StoreEmployeeByEmpId){
	    super("com.spark.psi.base.internal.service.StoreModuleService");
	    this.orm_StoreEmployeeByEmpId =orm_StoreEmployeeByEmpId;
    }

	/**
	 * 
	 * <p>查询所有仓库列表</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-12
	 */
	@Publish
	protected class GetStoreListProvider extends OneKeyResultListProvider<Store, GetAllStoreListKey>{

		@Override
        protected void provide(Context context, GetAllStoreListKey key,
                List<Store> resultList) throws Throwable
        {
			Store[] list = context.find(Store[].class);
			for(Store store : list){
	            if(checkStoreStatus(key.getStoreStatuss(),store)){
	            	resultList.add(store);
	            }
            }
        }

		private boolean checkStoreStatus(StoreStatus[] StoreStatuss, Store store){
			for(StoreStatus StoreStatus : StoreStatuss){
				if(StoreStatus==StoreStatus.ALL)return true;
	            if(StoreStatus==store.getStatus()){
	            	return true;
	            }
            }
	        return false;
        }	    
    }
	
	@Publish
	protected class GetAllStoreListProvider extends ResultProvider<Store[]>{

		@Override
        protected Store[] provide(Context context)
                throws Throwable
        {
//			ResourceToken<Tenant> token = context.findResourceToken(Tenant.class,context.find(Login.class).getTenantId());
	        List<Store> list = context.getList(Store.class);
	        return list.toArray(new Store[0]);
        }
		
	}
	
	/**
	 * 
	 * <p>查询销售员工关联的仓库</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-12
	 */
	@Publish
	protected class GetStoreListBySalesManProvider extends OneKeyResultProvider<Store, GetStoreBySaleManKey>{

		@Override
        protected Store provide(Context context, GetStoreBySaleManKey key) throws Throwable
        {
//	        List<Store> list = context.getList(Store.class,new GetAllStoreListKey(key.getStoreStatus()));
//	        for(Store store : list){
//	            if(checkStoreEmployee(store.getSalesmanIds(),key.getId())){
//	            	return store;
//	            }
//            }
//	        list.clear();
//	        list = null;
	        return null;
       }
		
	}
	
	private boolean checkStoreEmployee(GUID[] salesmans, GUID targetSalesman){
		for(GUID guid : salesmans){
			if(guid.equals(targetSalesman)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <p>查询仓库管理员关联的仓库</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-12
	 */
	@Publish
	protected class GetStoreListByManagerProvider extends OneKeyResultListProvider<Store, GetStoreListByManagerKey>{

		@Override
        protected void provide(Context context, GetStoreListByManagerKey key,List<Store> resultList) throws Throwable
        {
	        List<Store> list = context.getList(Store.class,new GetAllStoreListKey(key.getStoreStatus()));
	        for(Store store : list){
	            if(checkStoreEmployee(store.getKeeperIds(),key.getId())){
	            	resultList.add(store);
	            }
            }
	        list.clear();
	        list = null;
        }		
	}
	
	/**
	 * 
	 * <p>查询当前用户拥有权限的仓库GUID列表</p>
	 *
	
	
	 *
	 
	 * @version 2012-5-10
	 */
	@Publish
	protected final class GetUserStoreGuidsProvider extends OneKeyResultProvider<GUID[], GetUserStoreGuidsKey>{

		@Override
        protected GUID[] provide(Context context, GetUserStoreGuidsKey key)
                throws Throwable
        {
			Employee emp = context.find(Employee.class);
	        List<Store> list = context.getList(Store.class);
	        List<GUID> resultList = new ArrayList<GUID>();
	        for(Store store : list){
	        	if(!isstatus(key, store))continue;
	        	if(storeIsEmp(store, emp, context)){
	        		resultList.add(store.getId());
	        	}
            }
	        return resultList.toArray(new GUID[0]);
        }
		
		//是否是指定状态
		private boolean isstatus(GetUserStoreGuidsKey key,Store store){
			for(StoreStatus s : key.getStoreStatus()){
	            if(s==store.getStatus())return true;
            }
			return false;
		}
		
	}

	/**
	 * 查询指定用户对指定仓库是否有操作权限
	 * 
	 * @param store
	 * @param emp
	 * @param context
	 * @return boolean
	 */
	 static boolean storeIsEmp(Store store, Employee emp, Context context){
		if(emp.hasAuth(Auth.Boss,Auth.Assistant)) return true; // 如果当前用户是总经理或者助理，则可以看见所有仓库
		if(emp.hasAuth(Auth.StoreKeeperManager)){ // 如果当前用户是经理，则只能看到自己和自己管理的库管员的仓库
			// 获得自己管理的库管员列表
			List<Employee> emps =
			        context.getList(Employee.class,
			                new GetChildrenDeptEmployeeListByAuthKey( emp
	                                .getDepartmentId(),
			                        new Auth[] {Auth.StoreKeeper}));
			for(Employee employee : emps){ // 遍历库管员列表，其中一个拥有指定仓库权限就返回
				if(contains(store.getKeeperIds(), employee.getId())){
					return true;
				}
			}
		}
		if(emp.hasAuth(Auth.StoreKeeper)){
			if(contains(store.getKeeperIds(), context.find(Login.class)
					.getEmployeeId())) return true;
		}
		return false;
	}

	private static boolean contains(GUID[] emps, GUID id){
		for(GUID guid : emps){
			if(guid.equals(id)) return true;
		}
		return false;
	}
	
	
/*****************************************************************
 *******************事件监听器************************************
 **************************************************************/
	/**
	 * 员工离职事件
	 */
	@Publish
	protected final class EmployeeStatusChangeListener extends EventListener<EmployeeStatusChangeEvent>{
	
		@Override
	    protected void occur(Context context, EmployeeStatusChangeEvent event)
	            throws Throwable
	    {
		    if(event.getAction()==Action.Resign){ //如果员工离职
		    	ORMAccessor<StoreEmployee> acc = context.newORMAccessor(orm_StoreEmployeeByEmpId);
		    	List<StoreEmployee> list = acc.fetch(event.getId(),StoreEmployeeType.STOREMANAGER.getCode());
		    	for(StoreEmployee emp : list){
		    		GUID storeid = emp.getStoreGuid();
		    		acc.delete(emp);
		    		context.handle(new UpdateStoreResourceTask(storeid),UpdateStoreResourceTask.Method.Modify);	                
                }
		    	list = acc.fetch(event.getId(),StoreEmployeeType.SALER.getCode());
		    	for(StoreEmployee storeEmployee : list){
		    		GUID storeid = storeEmployee.getStoreGuid();
		    		acc.delete(storeEmployee);
		    		context.handle(new UpdateStoreResourceTask(storeid),UpdateStoreResourceTask.Method.Modify);	                
                }
		    }
	    }		
	}
	
	@Publish
	protected final class EmployeeAuthChangeListener extends EventListener<EmployeeAuthChangeEvent>{

		@Override
        protected void occur(Context context, EmployeeAuthChangeEvent event)
                throws Throwable
        {
	        Employee emp = context.find(Employee.class,event.getEmpId());
	        if(emp==null)return ;
	        if(!emp.hasAuth(Auth.StoreKeeper)){
		    	ORMAccessor<StoreEmployee> acc = context.newORMAccessor(orm_StoreEmployeeByEmpId);
		    	List<StoreEmployee> list = acc.fetch(event.getEmpId(),StoreEmployeeType.STOREMANAGER.getCode());
		    	for(StoreEmployee storeEmployee : list){
		    		GUID storeid = storeEmployee.getStoreGuid(); 
		    		acc.delete(storeEmployee);
		    		context.handle(new UpdateStoreResourceTask(storeid),UpdateStoreResourceTask.Method.Modify);	                
                }
	        }
	        if(!emp.hasAuth(Auth.Sales)){
		    	ORMAccessor<StoreEmployee> acc = context.newORMAccessor(orm_StoreEmployeeByEmpId);
		    	List<StoreEmployee> list = acc.fetch(event.getEmpId(),StoreEmployeeType.SALER.getCode());
		    	for(StoreEmployee storeEmployee : list){
		    		GUID storeid = storeEmployee.getStoreGuid();
		    		acc.delete(storeEmployee);
		    		context.handle(new UpdateStoreResourceTask(storeid),UpdateStoreResourceTask.Method.Modify);	                
                }
	        }
        }
		
	}

}
