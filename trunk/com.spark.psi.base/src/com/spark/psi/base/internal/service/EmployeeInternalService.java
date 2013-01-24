/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-19    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-19    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.def.obja.StructClass;
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
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.ormentity.TenantOrmEntity;
import com.spark.psi.base.internal.entity.resource.GetAllEmployeeByDaServerKey;
import com.spark.psi.base.internal.service.EmployeeInternalService.EmployeeResourceImpl;
import com.spark.psi.base.internal.service.query.QD_GetAllEmployeeList;
import com.spark.psi.base.internal.service.query.QD_GetAllTenant;
import com.spark.psi.base.internal.service.query.QD_GetEmployeeById;
import com.spark.psi.base.task.CreateBossTask;
import com.spark.psi.base.task.resource.ResetDepartmentAuthTask;
import com.spark.psi.base.task.resource.UpdateEmployeeResourceTask;
import com.spark.psi.publish.Auth;
import com.spark.uac.publish.TenantConfigFormat;

/**
 * <p>Ա����Դ����</p>
 *


 *
 
 * @version 2012-3-19
 */

public class EmployeeInternalService extends
        ResourceService<Employee, EmployeeResourceImpl, EmployeeResourceImpl>
{

	@StructClass
	public final static class EmployeeResourceImpl implements Employee{

		/**
		 * �û�ID
		 */
		protected GUID id;

		/**
		 * �û�����
		 */
		protected String name = "";

		/**
		 * �ֻ�����
		 */
		protected String mobileNumber = "";

		/**
		 * ���֤����
		 */
		protected String idNumber = "";

		/**
		 * ����
		 */
		protected long birthday;

		/**
		 * email��ַ
		 */
		protected String email = "";

		/**
		 * ְλ
		 */
		protected String position = "";

		/**
		 * ��ɫ
		 */
		protected int roles;

		/**
		 * �û�״̬
		 */
		protected String status;

		private GUID tenantId;

		private GUID departmentId;

		private GUID logo;

		private long createDate;

		private String landLineNumber;

		private String style;

		public String getStyle(){
			return style;
		}

		public void setStyle(String style){
			this.style = style;
		}

		public String getLandLineNumber(){
			return landLineNumber;
		}

		public void setLandLineNumber(String landLineNumber){
			this.landLineNumber = landLineNumber;
		}

		/**
		 * 
		 */
		private List<Auth> acls;

		/**
		 * @param acls
		 *            the acls to set
		 */
		public void setAcls(List<Auth> acls){
			//			this.acls = new HashSet<Auth>();
			//			this.acls.addAll(acls);
			this.acls = acls;
		}

		public List<Auth> getAclsSet(){
			return acls;
		}

		/**
		 * 
		 */
		public boolean hasAuth(Auth... auths){
			if(acls == null){
				return false;
			}
			for(Auth auth : auths){
				if(acls.contains(auth)){
					return true;
				}
			}
			return false;
		}

		public boolean hasAllAuth(Auth... auths){
			if(acls == null){
				return false;
			}
			for(Auth auth : auths){
				if(!acls.contains(auth)){
					return false;
				}
			}
			return true;
		}

		/**
		 * 
		 * @return
		 */
		public Auth[] getAcls(){
			return this.acls.toArray(new Auth[0]);
		}

		public GUID getId(){
			return id;
		}

		public String getName(){
			return name;
		}

		public String getMobileNo(){
			return mobileNumber;
		}

		public String getIdNumber(){
			return idNumber;
		}

		public long getBirthday(){
			return birthday;
		}

		public String getEmail(){
			return email;
		}

		public String getPosition(){
			return position;
		}

		public int getRoles(){
			return roles;
		}

		public String getStatus(){
			return status;
		}

		public GUID getTenantId(){
			return tenantId;
		}

		public GUID getDepartmentId(){
			return departmentId;
		}

		public void setId(GUID id){
			this.id = id;
		}

		public void setName(String name){
			this.name = name;
		}

		public void setMobileNo(String mobileNumber){
			this.mobileNumber = mobileNumber;
		}

		public void setIdNumber(String idNumber){
			this.idNumber = idNumber;
		}

		public void setBirthday(long birthday){
			this.birthday = birthday;
		}

		public void setEmail(String email){
			this.email = email;
		}

		public void setPosition(String position){
			this.position = position;
		}

		public void setRoles(int roles){
			this.roles = roles;
		}

		public void setStatus(String status){
			this.status = status;
		}

		public void setTenantId(GUID tenantId){
			this.tenantId = tenantId;
		}

		public void setDepartmentId(GUID departmentId){
			this.departmentId = departmentId;
		}

		public long getCreateDate(){
			return createDate;
		}

		public void setCreateDate(long createDate){
			this.createDate = createDate;
		}

		public GUID getLogo(){
			return logo;
		}

		public void setLogo(GUID logo){
			this.logo = logo;
		}

	}

	final QD_GetAllEmployeeList qD_AllEmployeeList;

	final QD_GetEmployeeById qD_GetEmployeeById;

	final QD_GetAllTenant qD_GetAllTenant;

	protected EmployeeInternalService(
	        final QD_GetAllEmployeeList qD_AllEmployeeList,
	        final QD_GetEmployeeById qD_GetEmployeeById,
	        final QD_GetAllTenant qD_GetAllTenant)
	{
		super(
		        "com.spark.psi.base.internal.service.EmployeeResourceService",
		        ResourceKind.SINGLETON_IN_CLUSTER);
		this.qD_AllEmployeeList = qD_AllEmployeeList;
		this.qD_GetEmployeeById = qD_GetEmployeeById;
		this.qD_GetAllTenant = qD_GetAllTenant;
	}

	@Override
	protected void init(Context context){
		createBossEmployee(context);
	}

	@Override
	protected void initResources(
	        Context context,
	        ResourceInserter<Employee, EmployeeResourceImpl, EmployeeResourceImpl> initializer)
	{
		List<Employee> list =
		        context.getList(Employee.class,
		                new GetAllEmployeeByDaServerKey());
		for(Employee employeeImpl : list){
			ResourceToken<Employee> token =
			        initializer.putResource((EmployeeResourceImpl)employeeImpl);
			if(employeeImpl.getTenantId() == null) continue;
			try{
				ResourceToken<Tenant> parentToken =
				        context.findResourceToken(Tenant.class, employeeImpl
				                .getTenantId());
				initializer.putResourceReference(parentToken, token);
				ResourceToken<Department> deptToken =
				        context.findResourceToken(Department.class,
				                employeeImpl.getDepartmentId());
				initializer.putResourceReference(deptToken, token);
			}
			catch(Throwable e){
				continue;
			}
		}
		context.handle(new ResetDepartmentAuthTask(false));//���ò���ְ��
	}

	/**
	 * �����ܾ����û�
	 * 
	 * @param context void
	 */
	private void createBossEmployee(Context context){
		ORMAccessor<TenantOrmEntity> acc =
		        context.newORMAccessor(qD_GetAllTenant);
		for(TenantOrmEntity entity : acc.fetch()){
			context.handle(new CreateBossTask(entity.getId(), entity
			        .getConfig()));
		}
	}

	@Publish
	protected final class GetAllEmployeeByDBServerProvider extends
	        OneKeyResultListProvider<Employee, GetAllEmployeeByDaServerKey>
	{
		{
		}

		@Override
		protected void provide(
		        ResourceContext<Employee, EmployeeResourceImpl, EmployeeResourceImpl> context,
		        GetAllEmployeeByDaServerKey key, List<Employee> resultList)
		        throws Throwable
		{
			RecordSet rs = context.openQuery(qD_AllEmployeeList);
			while(rs.next()){
				try{
					resultList.add(fullData(context, rs));
				}
				catch(Throwable e){
					System.err.println("��ʼ��Ա��ʧ�� id:"+rs.getFields().get(0).getGUID());
					continue;
				}
			}
		}
	}

	private EmployeeResourceImpl getEmployeeById(Context context, GUID id){
		RecordSet rs = context.openQuery(qD_GetEmployeeById, id);
		if(rs.next()){
			return fullData(context, rs);
		}
		return null;
	}

	private EmployeeResourceImpl fullData(Context context, RecordSet rs){
		EmployeeResourceImpl impl = new EmployeeResourceImpl();
		int i = 0;
		impl.setId(rs.getFields().get(i++).getGUID());
		impl.setName(rs.getFields().get(i++).getString());
		impl.setBirthday(rs.getFields().get(i++).getDate());
		impl.setDepartmentId(rs.getFields().get(i++).getGUID());
		impl.setEmail(rs.getFields().get(i++).getString());
		impl.setIdNumber(rs.getFields().get(i++).getString());
		impl.setMobileNo(rs.getFields().get(i++).getString());
		impl.setPosition(rs.getFields().get(i++).getString());
		impl.setRoles(rs.getFields().get(i++).getInt()); //�˴�Ӧ���Ա���Ľ�ɫ�б�
		impl.setStatus(rs.getFields().get(i++).getString());
		impl.setTenantId(rs.getFields().get(i++).getGUID());
		impl.setCreateDate(rs.getFields().get(i++).getLong());
		impl.setLogo(rs.getFields().get(i++).getGUID());
		impl.setLandLineNumber(rs.getFields().get(i++).getString());
		impl.setStyle(rs.getFields().get(i++).getString());
		List<Auth> acls = new ArrayList<Auth>();
		acls.addAll(context.getList(Auth.class, impl.getTenantId(), impl
		        .getTenantId().equals(impl.getId()) ? 1 : impl.getRoles())); //ProductService
		impl.setAcls(acls);
		return impl;
	}

	final class GetEmployeeResourceByIdProvider extends
	        OneKeyResourceProvider<GUID>
	{

		@Override
		protected GUID getKey1(EmployeeResourceImpl keysHolder){
			return keysHolder.getId();
		}
	}
	
	
	final class GetEmployeeResourceByMobileProvider extends TwoKeyResourceProvider<GUID, String>{

		@Override
        protected GUID getKey1(EmployeeResourceImpl keysHolder){
	        return keysHolder.getTenantId();
        }

		@Override
        protected String getKey2(EmployeeResourceImpl keysHolder){
	        return keysHolder.getMobileNo();
        }		
	}

	@Publish
	protected final class PutEmployeeResourceHandler
	        extends
	        TaskMethodHandler<UpdateEmployeeResourceTask, UpdateEmployeeResourceTask.Method>
	{

		protected PutEmployeeResourceHandler(){
			super(UpdateEmployeeResourceTask.Method.Put);
		}

		@Override
		protected void handle(
		        ResourceContext<Employee, EmployeeResourceImpl, EmployeeResourceImpl> context,
		        UpdateEmployeeResourceTask task) throws Throwable
		{
			EmployeeResourceImpl emp = getEmployeeById(context, task.id);
			ResourceToken<Employee> token = context.putResource(emp);
			ResourceToken<Tenant> parentToken =
			        context.findResourceToken(Tenant.class, emp.getTenantId());
			context.putResourceReference(parentToken, token);
			ResourceToken<Department> deptToken =
			        context.findResourceToken(Department.class, emp
			                .getDepartmentId());
			context.putResourceReference(deptToken, token);
			//			setDepartmentAuth(context, emp);
		}

	}

	@Publish
	protected final class ModifyEmployeeResourceHandler
	        extends
	        TaskMethodHandler<UpdateEmployeeResourceTask, UpdateEmployeeResourceTask.Method>
	{

		protected ModifyEmployeeResourceHandler(){
			super(UpdateEmployeeResourceTask.Method.Modify);
		}

		@Override
		protected void handle(
		        ResourceContext<Employee, EmployeeResourceImpl, EmployeeResourceImpl> context,
		        UpdateEmployeeResourceTask task) throws Throwable
		{
			EmployeeResourceImpl emp =
			        (EmployeeResourceImpl)context.find(Employee.class, task.id);
			//��ȡ�޸�ǰԱ���Ĳ�����Դ
			ResourceToken<Department> deptToken =
			        context.findResourceToken(Department.class, emp
			                .getDepartmentId());
			ResourceToken<Employee> token =
			        context.findResourceToken(Employee.class, task.id);
			context.removeResourceReference(deptToken, token); //��Ա����֮ǰ�Ĳ����������ù�ϵ
			EmployeeResourceImpl old = context.modifyResource(task.id);
			EmployeeResourceImpl _new = getEmployeeById(context, task.id); //�����ݿ�ȡ�����µ�Ա����Ϣ
			old.setName(_new.getName());
			old.setBirthday(_new.getBirthday());
			old.setDepartmentId(_new.getDepartmentId());
			old.setEmail(_new.getEmail());
			old.setIdNumber(_new.getIdNumber());
			old.setMobileNo(_new.getMobileNo());
			old.setPosition(_new.getPosition());
			old.setRoles(_new.getRoles()); //�˴�Ӧ���Ա���Ľ�ɫ�б�
			old.setStatus(_new.getStatus());
			old.setTenantId(_new.getTenantId());
			old.setBirthday(_new.getBirthday());
			old.setAcls(_new.getAclsSet());
			old.setLogo(_new.getLogo());
			old.setLandLineNumber(_new.getLandLineNumber());
			old.setStyle(_new.getStyle());
			context.postModifiedResource(old);

			context.putResourceReference(deptToken, token);
			deptToken =
			        context.findResourceToken(Department.class, _new
			                .getDepartmentId());//���Ա���²��ŵ���Դtoken
			context.putResourceReference(deptToken, token);//��Ա�����µĲ�����Դ����������ϵ
		}
	}

	@Publish
	protected final class RemoveEmployeeResourceHandler
	        extends
	        TaskMethodHandler<UpdateEmployeeResourceTask, UpdateEmployeeResourceTask.Method>
	{

		protected RemoveEmployeeResourceHandler(){
			super(UpdateEmployeeResourceTask.Method.Remove);
		}

		@Override
		protected void handle(
		        ResourceContext<Employee, EmployeeResourceImpl, EmployeeResourceImpl> context,
		        UpdateEmployeeResourceTask task) throws Throwable
		{
			ResourceToken<Employee> token =
			        context.findResourceToken(Employee.class, task.id);
			ResourceToken<Tenant> parentToken =
			        context.findResourceToken(Tenant.class, token.getFacade()
			                .getTenantId());
			context.removeResourceReference(parentToken, token);//��Ա�����⻧�������ù�ϵ
			ResourceToken<Department> deptToken =
			        context.findResourceToken(Department.class, token
			                .getFacade().getDepartmentId());
			context.removeResourceReference(deptToken, token); //��Ա���벿���������ù�ϵ
			context.removeResource(task.id);
		}

	}

	/**
	 * 
	 * <p>��ѯ��ǰ��¼�Ự��Ա������</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-19
	 */
	@Publish
	final protected class GetEmployeeProvider extends ResultProvider<Employee>{

		@Override
		protected Employee provide(
		        ResourceContext<Employee, EmployeeResourceImpl, EmployeeResourceImpl> context)
		        throws Throwable
		{
			return context.find(Employee.class, context.find(Login.class)
			        .getEmployeeId());
		}
	}

}
