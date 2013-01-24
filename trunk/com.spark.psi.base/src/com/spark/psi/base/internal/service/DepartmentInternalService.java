/**
 * 
 */
/**
 * 
 */
package com.spark.psi.base.internal.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.exception.DeadLockException;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.DepartmentImpl;
import com.spark.psi.base.internal.entity.ormentity.TenantOrmEntity;
import com.spark.psi.base.internal.entity.resource.GetAllEmployeeByDaServerKey;
import com.spark.psi.base.internal.service.query.QD_GetAllDepartmentList;
import com.spark.psi.base.internal.service.query.QD_GetAllTenant;
import com.spark.psi.base.task.organization.CreateDepartmentTask;
import com.spark.psi.base.task.pri.DeleteLevelTreeTask;
import com.spark.psi.base.task.pri.NewLevelTreeTask;
import com.spark.psi.base.task.resource.RemoveAllDepartmentAuthTask;
import com.spark.psi.base.task.resource.ResetDepartmentAuthTask;
import com.spark.psi.publish.Auth;
import com.spark.uac.publish.ProductSerialsEnum;
import com.spark.uac.publish.TenantConfigFormat;

/**
 * 
 * <p>组织结构内部服务</p>
 *


 *
 
 * @version 2012-3-15
 */
public class DepartmentInternalService extends ResourceService<Department, DepartmentImpl, DepartmentImpl> {

	final QD_GetAllDepartmentList qD_AllDepartmentList;
	final QD_GetAllTenant qD_GetAllTenant;
	
	protected DepartmentInternalService(final QD_GetAllDepartmentList qD_AllDepartmentList,
			final QD_GetAllTenant qD_GetAllTenant) {
		super("com.spark.psi.base.internal.service.DepartmentInternalService",ResourceKind.SINGLETON_IN_CLUSTER);
		this.qD_AllDepartmentList = qD_AllDepartmentList;
		this.qD_GetAllTenant = qD_GetAllTenant;
	}
	

	protected class EmployeeGroupResourceReference extends
			ResourceReference<Employee> {

	}

	@Override
	protected void init(Context context){
//		context.handle(new DeleteLevelTreeTask());
		ORMAccessor<TenantOrmEntity> acc = context.newORMAccessor(qD_GetAllTenant);
		for(TenantOrmEntity entity : acc.fetch()){
			context.handle(new CreateDepartmentTask(entity.getId(), entity.getConfig()));
		}
	}

	@Override
	protected void initResources(Context context,
			ResourceInserter<Department, DepartmentImpl, DepartmentImpl> initializer)
			throws Throwable {
//		SXElementBuilder seb;
//		try {
//			seb = new SXElementBuilder();
//			SXElement element = seb.build(entity.getConfig()).firstChild();
//			int sortIndex=0;
//				String title = element.getAttribute(TenantConfigFormat.A.TenantName);
//				GUID tenantId = GUID.MD5Of(title);
//				ResourceToken<Tenant> parentToken = context.findResourceToken(Tenant.class,tenantId);
//				SXElement departmentNode = element.firstChild(TenantConfigFormat.Departments.Name);
//				if (departmentNode != null) {
//					DepartmentImpl dept = new DepartmentImpl();
//					dept.setId(tenantId);
//					dept.setName(title);
//					dept.setParentId(null);
//					dept.setValid(true);
//					dept.setDtype('0');
//					dept.setTenantId(tenantId);
//					dept.setSortIndex(sortIndex++);
//					NewLevelTreeTask newLevelTreetask = new NewLevelTreeTask(dept.getId(),dept.getParentId());
//					context.handle(newLevelTreetask);
//					dept.setPath(newLevelTreetask.getPath());
//					ResourceToken<Department> token = initializer.putResource(dept);
//					initializer.putResourceReference(parentToken, token);
//					processDepartmentElement(parentToken, departmentNode, dept,initializer,context);
//				}
//			}
//		} catch (Throwable t) {
//			t.printStackTrace();
//		}
	}
	
	private void processDepartmentElement(ResourceToken<Tenant> parentToken,
			SXElement departmentElement, DepartmentImpl parentDept, ResourceContext<Department, DepartmentImpl, DepartmentImpl> initializer) {
		Iterator<SXElement> it = departmentElement.getChildren(TenantConfigFormat.Departments.Department.Name)
				.iterator();
		int sortIndex = 0;;
		while (it.hasNext()) {
			SXElement element = it.next();
			String title = element.getAttribute(TenantConfigFormat.Departments.Department.A.Title);
			String code = element.getAttribute(TenantConfigFormat.Departments.Department.A.Code);
			boolean valid = !Boolean.parseBoolean(element.getAttribute(TenantConfigFormat.Departments.Department.A.RemoveFlag, "false"));
//			GUID id = GUID.valueOf(element.getAttribute(TenantConfigFormat.Departments.Department.A.id,GUID.MD5Of(parentToken.getFacade().getTitle()+title).toString()));
			GUID id = GUID.valueOf(element.getAttribute(TenantConfigFormat.Departments.Department.A.id,GUID.MD5Of(code).toString()));
			DepartmentImpl dept = new DepartmentImpl();
			dept.setId(id);
			dept.setName(title);
			dept.setParentId(parentDept.getId());
			dept.setValid(valid);
			dept.setDtype('1');
			dept.setTenantId(parentDept.getTenantId());
			dept.setSortIndex(sortIndex++);
			ResourceToken<Department> token = initializer.putResource(dept);
			initializer.putResourceReference(parentToken, token);
			if(valid){
				NewLevelTreeTask newLevelTreetask = new NewLevelTreeTask(dept.getId(),dept.getParentId(),dept.getTenantId());
				initializer.handle(newLevelTreetask);
				dept.setPath(newLevelTreetask.getPath());
			}
			processDepartmentElement(parentToken,element, dept,initializer);
		}
	}
	
	@Publish
	protected final class CreateDepartmentHandler extends SimpleTaskMethodHandler<CreateDepartmentTask>{

		@Override
        protected void handle(
                ResourceContext<Department, DepartmentImpl, DepartmentImpl> context,
                CreateDepartmentTask task) throws Throwable
        {
			removeOldDept(context,task);
			SXElementBuilder seb;
			try {
				seb = new SXElementBuilder();
				SXElement element = seb.build(task.getConfig()).firstChild();
				int sortIndex=0;
				String title = element.getAttribute(TenantConfigFormat.A.TenantName);
				GUID tenantId = task.getTenantId();
				ResourceToken<Tenant> parentToken = context.findResourceToken(Tenant.class,tenantId);
				Iterator<SXElement> services = element.firstChild(TenantConfigFormat.Services.Name).getChildren(TenantConfigFormat.Services.Service.Name).iterator();
				while(services.hasNext()){		
					SXElement service = services.next();
					if(!service.getAttribute( 
					        TenantConfigFormat.Services.Service.A.ProductSerial)
					        .equals(ProductSerialsEnum.PSI.getCode())) continue;
					SXElement departmentNode = service.firstChild().firstChild(TenantConfigFormat.Departments.Name);
					if (departmentNode != null) {
						DepartmentImpl dept = new DepartmentImpl();
						dept.setId(tenantId);
						dept.setName(title);
						dept.setParentId(null);
						dept.setValid(true);
						dept.setDtype('0');
						dept.setTenantId(tenantId);
						dept.setSortIndex(sortIndex++);
						NewLevelTreeTask newLevelTreetask = new NewLevelTreeTask(dept.getId(),dept.getParentId(),dept.getTenantId());
						context.handle(newLevelTreetask);
						dept.setPath(newLevelTreetask.getPath());
						ResourceToken<Department> token = context.putResource(dept);
						context.putResourceReference(parentToken, token);
						processDepartmentElement(parentToken, departmentNode, dept,context);
					}	    
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
        }

		private void removeOldDept(
                ResourceContext<Department, DepartmentImpl, DepartmentImpl> context,
                CreateDepartmentTask task)
        {
			context.handle(new DeleteLevelTreeTask(task.getTenantId()));
			ResourceToken<Tenant> token = context.findResourceToken(Tenant.class,task.getTenantId());
			List<Department> depts = context.getResourceReferences(Department.class, token);
			for(Department department : depts){
	            context.removeResourceReference(token, context.findResourceToken(Department.class,department.getId()));
	            context.removeResource(department.getId());
            }
        }
		
	}
	
	final class GetDepartmentResourceById extends OneKeyResourceProvider<GUID>{

		@Override
        protected GUID getKey1(DepartmentImpl keysHolder){
	        return keysHolder.getId();
        }		
	}

	/**
	 * 
	 * <p>清空所有部门的职能</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-6
	 */
	@Publish
	final protected class RemoveAllDepartmentAuthHandler extends SimpleTaskMethodHandler<RemoveAllDepartmentAuthTask>{

		@Override
        protected void handle(
                ResourceContext<Department, DepartmentImpl, DepartmentImpl> context,
                RemoveAllDepartmentAuthTask task) throws Throwable
        {

	        ResourceToken<Tenant> token = context.findResourceToken(Tenant.class,task.getTenantId());
	        List<Department> list = context.getResourceReferences(Department.class, token);
	        for(Department department : list){
	            DepartmentImpl dept = (DepartmentImpl)context.modifyResource(department.getId());
	            dept.removeAllAuth();
	            context.postModifiedResource(dept);
            }
        }
		
	}
	
	/**
	 * 
	 * <p>重置部门职能</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-18
	 */
	@Publish
	final protected class ResetDepartmentAuthHandler extends SimpleTaskMethodHandler<ResetDepartmentAuthTask>{

		@Override
        protected void handle(
                ResourceContext<Department, DepartmentImpl, DepartmentImpl> context,
                ResetDepartmentAuthTask task) throws Throwable
        {
	            List<Employee> emps ;  //通过遍历员工列表，对部门职能进行设置，每设置一个部门，还需要递归到根部门，给所有上级部门设置相应的职能
	            if(task.isFindByResource()){  //如果是系统启动时初始化资源，避免循环引用，这里只能从数据库中取员工
	            	emps = getEmployeeByResource(context,task.getTenantId()); //通过租户id从资源中直接取员工列表
	            	context.handle(new RemoveAllDepartmentAuthTask(task.getTenantId()));
	            }else{
	            	emps = getEmployeeByDBServer(context);  //从数据库中取出所有员工
	            }
	            for(Employee employee : emps){
	            	updateEmployeeDeptAuth(context,employee); 
                }
        }
		
		private List<Employee> getEmployeeByResource(ResourceContext<Department, DepartmentImpl, DepartmentImpl> context, GUID tenantId){
			ResourceToken<Tenant> token = context.findResourceToken(Tenant.class,tenantId);
			return context.getResourceReferences(Employee.class,token);
		}
		
		private List<Employee> getEmployeeByDBServer(ResourceContext<Department, DepartmentImpl, DepartmentImpl> context){
			return context.getList(Employee.class,new GetAllEmployeeByDaServerKey());
		}
		
	}
	
	private void updateEmployeeDeptAuth(ResourceContext<Department, DepartmentImpl, DepartmentImpl> context,Employee employee){
    	try{
	        DepartmentImpl entity = (DepartmentImpl)context.modifyResource(employee.getDepartmentId());
	        empAuthToDeptAuth(context,entity, employee); 
	        context.postModifiedResource(entity);
	        if(!entity.getId().equals(entity.getTenantId()))//如果当前用户不是根节点，则需要更新他的父节点
	        	updateParentDeptAuth(context,entity);
        }
        catch(Exception e){
        	System.err.println("修改部门职能时发生错误");
	        e.printStackTrace();
        }
    }
	
	/**
	 * 将指定部门的职能克隆给父级部门
	 * 
	 * @param context
	 * @param entity void
	 */
	private void updateParentDeptAuth(
			ResourceContext<Department, DepartmentImpl, DepartmentImpl> context,
			DepartmentImpl entity)
	{
		if(entity.getTenantId().equals(entity.getParentId()))return ; //如果父节点是根节点则不需要更新
		DepartmentImpl parent = (DepartmentImpl)context.modifyResource(entity.getParent());
		for(Auth auth : entity.getAuths()){
			if(!parent.hasAuth(auth)){ //如果父部门没有这个职能了，则添加
				parent.addAuth(auth);
			}
		}
		context.postModifiedResource(parent);
		updateParentDeptAuth(context, parent); //继续递归上级部门
	}
	
	/**
	 * 将员工职能设置给部门
	 * 
	 * @param context
	 * @param dept
	 * @param emp void
	 */
	private void empAuthToDeptAuth(Context context,DepartmentImpl dept,Employee emp){
		for(Auth auth : emp.getAcls()){
			if(!dept.hasAuth(auth)){
				dept.addAuth(auth);
			}
		}
	}
	
}
