/**
 * 
 */
/**
 * 
 */
package com.spark.psi.base.internal.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.service.Publish.Mode;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Store;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.event.EmployeeAuthChangeEvent;
import com.spark.psi.base.event.EmployeeDepartmentChangeEvent;
import com.spark.psi.base.event.EmployeeStatusChangeEvent;
import com.spark.psi.base.internal.entity.helper.EmployeeHelper;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.internal.entity.ormentity.EmployeeOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_Employee;
import com.spark.psi.base.key.GetStoreBySaleManKey;
import com.spark.psi.base.key.organization.GetDescendantDepartmentListKey;
import com.spark.psi.base.publicimpl.EmployeePublishImpl;
import com.spark.psi.base.task.CreateBossTask;
import com.spark.psi.base.task.resource.ResetDepartmentAuthTask;
import com.spark.psi.base.task.resource.UpdateEmployeeResourceTask;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.EmployeeStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.config.task.UpdateUserConfigTask;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.entity.RoleInfo;
import com.spark.psi.publish.base.organization.key.CheckUserPwdIsValidKey;
import com.spark.psi.publish.base.organization.key.FindEmployeeCountKey;
import com.spark.psi.publish.base.organization.key.FindEmployeeHadRolesCountKey;
import com.spark.psi.publish.base.organization.key.GetEmployeeListByAuthKey;
import com.spark.psi.publish.base.organization.key.GetEmployeeListKey;
import com.spark.psi.publish.base.organization.key.ValidationMoblieNumberIsOnlyKey;
import com.spark.psi.publish.base.organization.task.ChangeEmployeeStatusTask;
import com.spark.psi.publish.base.organization.task.DeleteEmployeeTask;
import com.spark.psi.publish.base.organization.task.EnabledAllUserTask;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeInfoTask;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeMobileTask;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeStyleTask;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeInfoTask.Item;
import com.spark.psi.publish.order.key.ValidationRetailerIsValidTask;
import com.spark.uac.publish.TenantConfigFormat;
import com.spark.uac.publish.key.CheckEmployeeNameIsValidKey;
import com.spark.uac.publish.key.HasUserKey;
import com.spark.uac.publish.task.ChangeUserEnabledTask;
import com.spark.uac.publish.task.ChangeUserMobileNoTask;
import com.spark.uac.publish.task.CreateUserTask;
import com.spark.uac.publish.task.RemoveUserTask;
import com.spark.uac.publish.task.UpdateUserPasswordTask;

/**
 * 员工管理 外部接口服务
 * 
 * 
 * 
 */
public class EmployeePublishService extends Service {

	final Orm_Employee orm_Employee;

	protected EmployeePublishService(final Orm_Employee orm_Employee) {
		super("com.spark.psi.base.internal.service.EmployeePublishService");
		this.orm_Employee = orm_Employee;
	}

	/**
	 * 获得用户维护对象
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetEmployeeInfoByIdProvider extends OneKeyResultProvider<EmployeeInfo, GUID> {

		@Override
		protected EmployeeInfo provide(Context context, GUID key) throws Throwable {
			return EmployeeHelper.employeeToInfo(context.find(Employee.class, key));
		}

	}

	/**
	 * 获得员工列表
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetEmployeeListProvider extends
			OneKeyResultProvider<ListEntity<EmployeePublishImpl>, GetEmployeeListKey> {

		@Override
		protected ListEntity<EmployeePublishImpl> provide(Context context, final GetEmployeeListKey key)
				throws Throwable {
			List<EmployeePublishImpl> resultList = new ArrayList<EmployeePublishImpl>(); // 存储返回值
			final List<GUID> depts = new ArrayList<GUID>(); // 部门筛选

			final Tenant tr = context.find(Tenant.class);
			if (key.getQueryScope() != null && !key.isQueryAll()) { // 如果不是查全部，则查询出需要查询的部门GUID列表
				if (key.getQueryScope().getType() == QueryScope.ScopeType.Mine) {
					depts.add(tr.getId()); // 如果是查询公司直属
				} else {
					Department dept = context.find(Department.class, key.getQueryScope().getDepartmentId());
					depts.add(dept.getId());
					List<Department> descendants = context.getList(Department.class,
							new GetDescendantDepartmentListKey(dept.getId()));
					for (Department child : descendants) {
						depts.add(child.getId());
					}
				}
			}
			ResourceToken<Tenant> token = context.findResourceToken(Tenant.class, context.find(Login.class)
					.getTenantId());
			List<Employee> emps = // 获得当前租户的所有员工并按创建日期倒序排序
			context.getResourceReferences(Employee.class, token, new Comparator<Employee>() {
				public int compare(Employee arg0, Employee arg1) {
					return arg0.getCreateDate() > arg1.getCreateDate() ? 1 : -1;
				}
			});
			for (Employee item : emps) {
				if (accept(item, depts, key)) // 过滤员工
				{
					EmployeePublishImpl impl = EmployeeHelper.employeeToItem(item);
					if (item.getTenantId().equals(item.getDepartmentId())) {
						impl.setDepartmentName("公司直属");
					} else {
						impl.setDepartmentName(context.find(Department.class, impl.getDepartmentId()).getName());
					}
					if (impl.getStatus() == EmployeeStatus.Normal) {
						impl.addActions(Action.DepartmentConfig, Action.AuthConfig, Action.Resign);
					} else if (impl.getStatus() == EmployeeStatus.Departure) {
						impl.addActions(Action.Reinstatus);
					}

					impl.setRolesInfo(getRolesInfo(context, item));
					resultList.add(impl);
				}
			}
			return new ListEntity<EmployeePublishImpl>(resultList, resultList.size());
		}

		private boolean accept(Employee item, List<GUID> depts, final GetEmployeeListKey key) {
			if (key.getQueryScope() != null && !key.isQueryAll()) { // 如果不是查全部，则通过部门id过滤
				if (!depts.contains(item.getDepartmentId())) { // 如果不属于查询范围内的部门，返回false
					return false;
				}
			}
			if (key.getStatus() != null) {
				if (key.getStatus() == EmployeeStatus.Normal) { // 如果查询离职员工，则显示所有离职员工
					if (item.getStatus().equals(EmployeeStatus.Departure.getCode())) {
						return false;
					}
				}
			}
			if (key.getRoleScope() > 0) {
				if (key.getRoleScope() == 1) {
					if (item.getRoles() == 0)
						return false;
				}
				if (key.getRoleScope() == 2) {
					if (item.getRoles() != 0)
						return false;
				}
			}
			return true;
		}

	}

	/**
	 * 获得员工的角色名称
	 * 
	 * @param context
	 * @param item
	 * @return String
	 */
	private String getRolesInfo(Context context, Employee item) {
		List<RoleInfo> roleList = context.getList(RoleInfo.class); // 部门列表
		StringBuilder roleTitle = new StringBuilder();
		if (item.getRoles() > 0) {
			for (int i = 0; i < roleList.size(); i++) {
				RoleInfo roleInfo = roleList.get(i);
				if (((1 << roleInfo.getCode()) & item.getRoles()) != 0) {
					if (roleTitle.length() > 0) {
						roleTitle.append(",");
					}
					roleTitle.append(roleInfo.getName());
				}

			}
		}
		return roleTitle.toString();
	}

	/**
	 * 
	 * <p>
	 * 通过职能和部门获得员工列表
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2012<br>
	 * Company: 久其
	 * </p>
	 * 
	 * 
	 * @version 2012-3-13
	 */
	@Publish
	protected class GetEmoloyeeListByAuthProvider extends
			OneKeyResultProvider<ListEntity<EmployeeItem>, GetEmployeeListByAuthKey> {

		@Override
		protected ListEntity<EmployeeItem> provide(Context context, GetEmployeeListByAuthKey key) throws Throwable {
			List<Employee> list = context.getList(Employee.class, new com.spark.psi.base.key.GetEmployeeListByAuthKey(
					key.getAuths(), key.getSearchText()));
			List<EmployeeItem> resultList = new ArrayList<EmployeeItem>();
			final List<GUID> depts = new ArrayList<GUID>(); // 部门筛选
			Department dept;
			if (key.getDepartmentId() == null) {
				dept = context.find(Department.class, context.find(Login.class).getTenantId());
			} else {
				dept = context.find(Department.class, key.getDepartmentId());
			}
			depts.add(dept.getId());
			List<Department> descendants = context.getList(Department.class, new GetDescendantDepartmentListKey(dept
					.getId()));
			for (Department child : descendants) {
				depts.add(child.getId());
			}

			for (Employee employee : list) {
				if (!depts.contains(employee.getDepartmentId()))
					continue;
				EmployeePublishImpl item = EmployeeHelper.employeeToItem(employee);
				dept = context.find(Department.class, item.getDepartmentId());
				item.setDepartmentName(dept.getName());
				item.setRolesInfo(getRolesInfo(context, employee));
				resultList.add(item);
			}
			return new ListEntity<EmployeeItem>(resultList, resultList.size());
		}

	}

	/**
	 * 设置员工离职/复职
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class ChanageEmployeeStatusHandler extends SimpleTaskMethodHandler<ChangeEmployeeStatusTask> {

		@Override
		protected void handle(Context context, ChangeEmployeeStatusTask task) throws Throwable {
			ORMAccessor<EmployeeOrmEntity> acc = context.newORMAccessor(orm_Employee);
			for (GUID guid : task.getIds()) {
				EmployeeOrmEntity entity = acc.findByRECID(guid);
				entity.setEmpState(task.getUserStatus().getCode());
				if (task.getUserStatus() == EmployeeStatus.Departure) {
					entity.setRoles(RoleInfo.Empty_Role);
				}
				try {
					acc.update(entity);
					context.handle(new UpdateEmployeeResourceTask(guid), UpdateEmployeeResourceTask.Method.Modify);
					ChangeUserEnabledTask changeUserEnabledTask = new ChangeUserEnabledTask(
							task.getUserStatus() == EmployeeStatus.Normal, guid);
					context.handle(changeUserEnabledTask); // 更新认证中心用户状态
					// 发送员工状态改变事件
					context.dispatch(new EmployeeStatusChangeEvent(entity.getId(),
							task.getUserStatus() == EmployeeStatus.Normal ? EmployeeStatusChangeEvent.Action.Reinstatus
									: EmployeeStatusChangeEvent.Action.Resign));
				} finally {
					acc.unuse();
				}
			}
		}

	}

	@Publish
	protected final class GetEmployeeByMobileProvider extends OneKeyResultProvider<Employee, String> {

		@Override
		protected Employee provide(Context context, String key) throws Throwable {
			return context.find(Employee.class, context.find(Login.class).getTenantId(), key);
		}

	}

	/**
	 * 编辑员工信息
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class UpdateEmployeeInfoHandler extends SimpleTaskMethodHandler<UpdateEmployeeInfoTask> {

		@Override
		protected void handle(Context context, UpdateEmployeeInfoTask task) throws Throwable {
			for (Item item : task.getEmployeeItems()) {
				Employee e = context.find(Employee.class, item.getMobileNo());
				if (e == null)
					continue;
				if (!e.getId().equals(item.getId()))
					throw new IllegalArgumentException("手机号码重复！");
			}
			Tenant tenant = TenantHelper.getTenant(context);
			for (UpdateEmployeeInfoTask.Item emp : task.getEmployeeItems()) {
				saveEmployee(context, emp);
			}
			// 重置当前租户所有部门的职能
			context.handle(new ResetDepartmentAuthTask(context.find(Login.class).getTenantId()));
			System.out.println();
		}

		void saveEmployee(Context context, UpdateEmployeeInfoTask.Item emp) {
			if (!validationEmpAuthIsValid(context, emp)) {
				throw new IllegalArgumentException("员工：" + emp.getName() + "无效，经理角色不能设置在公司直属下面。");
			}
			boolean enabled = emp.getRoles() > 0 && emp.isValid(); // 员工在认证中心的有效性
			// 如果员工没有设置角色，有效性为false
			boolean deptIsChange = false; // 部门是否发生变化
			boolean roleIsChange = false; // 权限是否发生变化
			ORMAccessor<EmployeeOrmEntity> acc = context.newORMAccessor(orm_Employee);
			Employee user = context.find(Employee.class); // 当前登录用户
			boolean isCreate = emp.isCreate(); // 是否是新建用户
			GUID oldDept = null; // 修改前的部门
			String oldMobileNo = null; // 修改前的手机号码
			EmployeeOrmEntity entity = null;
			if (isCreate) {
				entity = new EmployeeOrmEntity();
				entity.setId(context.newRECID());
				entity.setCreateDate(System.currentTimeMillis());
				entity.setEmpState(EmployeeStatus.Normal.getCode());
				if (user != null) {
					entity.setCreatePerson(user.getName());
				}
			} else {
				entity = acc.findByRECID(emp.getId());
				deptIsChange = entity.getDepartmentId() != emp.getDepartmentId();
				roleIsChange = entity.getRoles() != emp.getRoles();
				oldDept = entity.getDepartmentId();
				oldMobileNo = entity.getMobileNo();
			}
			entity.setBirthday(getBirthDay(emp.getIdNumber()));
			entity.setIdNumber(emp.getIdNumber());
			entity.setEmail(emp.getEmail());
			entity.setMobileNo(emp.getMobileNo());
			entity.setName(emp.getName());
			entity.setPosition(emp.getPosition());
			entity.setRoles(emp.getRoles());
			entity.setDepartmentId(emp.getDepartmentId());
			entity.setTenantId(user.getTenantId());
			entity.setPyDuty(PinyinHelper.getLetter(entity.getPosition()));
			entity.setPyName(PinyinHelper.getLetter(entity.getName()));

			try {
				if (isCreate) {
					if (emp.getRoles() == 0)
						emp.setRoles(-1);// -1 从未设置过角色
					try {
						acc.insert(entity);
					} catch (Exception e) {
						throw new IllegalArgumentException(e.getMessage());
					}
					context.handle(new UpdateEmployeeResourceTask(entity.getId()),
							UpdateEmployeeResourceTask.Method.Put);
					// 认证中心创建用户
					if (emp.getRoles() < 1) {
						enabled = false;
					}
					CreateUserTask createUacUser = new CreateUserTask(user.getTenantId(), entity.getId(), emp
							.getMobileNo(), enabled);
					context.handle(createUacUser);
				} else {
					try {
						acc.update(entity);
					} catch (Exception e) {
						throw new IllegalArgumentException(e.getMessage());
					}
					context.handle(new UpdateEmployeeResourceTask(entity.getId()),
							UpdateEmployeeResourceTask.Method.Modify);
					// //修改认证中心用户的有效性 如果用户没有设置角色，则有效性为false，反之应该设回true
					if (context.find(Boolean.class, new HasUserKey(entity.getId()))) { // 检查认证中心是否有当前用户
						ChangeUserEnabledTask changeUserEnabledTask = new ChangeUserEnabledTask(enabled, entity.getId());
						context.handle(changeUserEnabledTask);
						if (!emp.getMobileNo().equals(oldMobileNo)) { // 如果手机号码发生了变化，需要更新认证中心的手机号码
							context.handle(new ChangeUserMobileNoTask(entity.getId(), entity.getMobileNo()));
						}
					} else {
						CreateUserTask createUacUser = new CreateUserTask(user.getTenantId(), entity.getId(), emp
								.getMobileNo(), enabled);
						context.handle(createUacUser);
					}
					if (deptIsChange) { // 如果部门发生变化 需要出发部门发生变化事件
						context.dispatch(new EmployeeDepartmentChangeEvent(emp.getId(), oldDept));
					}
					if (roleIsChange) { // 如果权限发生变化 触发权限变化事件
						context.dispatch(new EmployeeAuthChangeEvent(emp.getId()));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				acc.unuse();
			}

		}

		/**
		 * 判断员工是否有效 具体判断内容为，员工不能即是经理，部门又是公司直属。满足以上条件则判定为无效
		 * 
		 * @param context
		 * @param emp
		 * @return boolean
		 */
		private boolean validationEmpAuthIsValid(Context context, Item emp) {
			GUID tenantId = context.find(Login.class).getTenantId();
			List<Auth> list = context.getList(Auth.class, tenantId, emp.getRoles());
			if (list.contains(Auth.Boss) || list.contains(Auth.Assistant)) { // 如果是总经理或这助理
				// 返回false
				return true;
			}
			if (list.contains(Auth.AccountManager) || list.contains(Auth.SalesManager)
					|| list.contains(Auth.PurchaseManager) || list.contains(Auth.StoreKeeperManager)) { // 如果是经理
				// 那么判断是不是在公司直属，如果是则为无效
				if (emp.getDepartmentId().equals(tenantId))
					return false;
			}
			return true;
		}

		/**
		 * 根据身份证号码获得生日（弱验证），只要不符合要求就返回0
		 * 
		 * @param idNum
		 * @return long
		 */
		private long getBirthDay(String idNum) {
			if (idNum == null || idNum.length() < 14)
				return 0;
			idNum = idNum.substring(6, 14);
			System.out.println(idNum);
			try {
				long d = new SimpleDateFormat("yyyyMMdd").parse(idNum).getTime();
				if (d > System.currentTimeMillis())
					return 0;
				return d;
			} catch (ParseException e) {
				return 0;
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 创建总经理账号
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2012<br>
	 * Company: 久其
	 * </p>
	 * 
	 * 
	 * @version 2012-3-27
	 */
	@Publish
	protected final class CreateBossHandler extends SimpleTaskMethodHandler<CreateBossTask> {

		@Override
		protected void handle(Context context, CreateBossTask task) throws Throwable {
			SXElementBuilder seb;
			try {
				seb = new SXElementBuilder();
				SXElement element = seb.build(task.getConfig()).firstChild();
				String boss = element.getAttribute(TenantConfigFormat.A.BossName);
				String mobile = element.getAttribute(TenantConfigFormat.A.BossMobile);
				Employee employee = context.find(Employee.class, task.getTenantId());
				if (employee == null) {
					EmployeeOrmEntity entity = new EmployeeOrmEntity();
					entity.setId(task.getTenantId());
					entity.setCreateDate(System.currentTimeMillis());
					entity.setMobileNo(mobile);
					entity.setName(boss);
					entity.setDepartmentId(task.getTenantId());
					entity.setTenantId(task.getTenantId());
					entity.setEmpState(EmployeeStatus.Supper.getCode());
					entity.setRoles(1);
					ORMAccessor<EmployeeOrmEntity> acc = context.newORMAccessor(orm_Employee);
					try {
						acc.insert(entity);
						context.handle(new UpdateEmployeeResourceTask(entity.getId()),
								UpdateEmployeeResourceTask.Method.Put);
					} finally {
						acc.unuse();
					}
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}

		}

	}

	/**
	 * 
	 * <p>
	 * 检查指定租户里有不有对应姓名的用户
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-4-11
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class CheckEmployeeNameIsValidProvider extends
			OneKeyResultProvider<Boolean, CheckEmployeeNameIsValidKey> {

		@Override
		protected Boolean provide(Context context, CheckEmployeeNameIsValidKey key) throws Throwable {
			ResourceToken<Tenant> token = context.findResourceToken(Tenant.class, key.getTenantID());
			for (Employee emp : context.getResourceReferences(Employee.class, token)) {
				if (emp.getName().equals(key.getName()) && emp.getMobileNo().equals(key.getMobilePhone())) {
					return true;
				}
			}
			return false;
		}

	}

	/**
	 * 
	 * <p>
	 * 保存个人设置
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-4-20
	 */
	@Publish
	protected final class UpdateUserConfigHandler extends SimpleTaskMethodHandler<UpdateUserConfigTask> {

		@Override
		protected void handle(Context context, UpdateUserConfigTask task) throws Throwable {
			ORMAccessor<EmployeeOrmEntity> acc = context.newORMAccessor(orm_Employee);
			EmployeeOrmEntity user = acc.findByRECID(task.getId());
			user.setLogo(task.getLogo());
			user.setLandLineNumber(task.getLandLineNumber());
			user.setEmail(task.getMail());
			acc.update(user);
			context.handle(new UpdateEmployeeResourceTask(user.getId()), UpdateEmployeeResourceTask.Method.Modify);
			if (task.getPwd() != null) {
				com.spark.uac.publish.key.CheckUserPwdIsValidKey key = new com.spark.uac.publish.key.CheckUserPwdIsValidKey(
						user.getId(), task.getOldPwd());
				if (!context.find(Boolean.class, key)) {
					throw new IllegalArgumentException("旧密码错误");
				}
				UpdateUserPasswordTask updateUserPwdTask = new UpdateUserPasswordTask(user.getId(), task.getPwd(), task
						.getOldPwd());
				context.handle(updateUserPwdTask);
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 验证密码是否正确
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-7-5
	 */
	@Publish
	protected final class CheckUserPwdIsValidProvider extends OneKeyResultProvider<Boolean, CheckUserPwdIsValidKey> {

		@Override
		protected Boolean provide(Context context, CheckUserPwdIsValidKey key) throws Throwable {
			return context.find(Boolean.class, new com.spark.uac.publish.key.CheckUserPwdIsValidKey(key.getUid(), key
					.getPwd()));
		}

	}

	/**
	 * 
	 * <p>
	 * 修改用户手机号码
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-4-23
	 */
	@Publish
	protected final class UpdateEmployeeMobileHandler extends SimpleTaskMethodHandler<UpdateEmployeeMobileTask> {

		@Override
		protected void handle(Context context, UpdateEmployeeMobileTask task) throws Throwable {
			ORMAccessor<EmployeeOrmEntity> acc = context.newORMAccessor(orm_Employee);
			EmployeeOrmEntity entity = acc.findByRECID(task.getId());
			entity.setMobileNo(task.getMobileNo());
			acc.update(entity);
			context.handle(new UpdateEmployeeResourceTask(task.getId()), UpdateEmployeeResourceTask.Method.Modify);
			context.handle(new ChangeUserMobileNoTask(entity.getId(), entity.getMobileNo()));

		}

	}

	/**
	 * 
	 * <p>
	 * 修改用户界面风格
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-4-25
	 */
	@Publish
	protected final class UpdateEmployeeStyleHandler extends SimpleTaskMethodHandler<UpdateEmployeeStyleTask> {

		@Override
		protected void handle(Context context, UpdateEmployeeStyleTask task) throws Throwable {
			ORMAccessor<EmployeeOrmEntity> acc = context.newORMAccessor(orm_Employee);
			EmployeeOrmEntity entity = acc.findByRECID(task.getId());
			entity.setStyle(task.getStyle());
			acc.update(entity);
			context.handle(new UpdateEmployeeResourceTask(task.getId()), UpdateEmployeeResourceTask.Method.Modify);
		}

	}

	/**
	 * 
	 * <p>
	 * 验证零售员工是否有效
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-5-7
	 */
	@Publish
	protected final class ValidationRetailerIsValidHendler extends
			SimpleTaskMethodHandler<ValidationRetailerIsValidTask> {

		@Override
		protected void handle(Context context, ValidationRetailerIsValidTask task) throws Throwable {
			GUID empid = task.getId();
			if (task.getId() == null) {
				empid = context.find(Login.class).getEmployeeId();
			}
			Store store = context.find(Store.class, new GetStoreBySaleManKey(empid, StoreStatus.ALL));
			if (store == null) {
				task.setValid(false);
				task.setMsg("请先设置零售商品出库仓库！");
			} else {
				if (store.getStatus() == StoreStatus.ONCOUNTING) {
					task.setValid(false);
					task.setMsg("该仓库处于盘点中状态，不能进行零售！");
				} else if (store.getStatus() == StoreStatus.STOP) {
					task.setValid(false);
					task.setMsg("该仓库处于停用状态，不能进行零售！");
				} else if (store.getStatus() == StoreStatus.DISABLED) {
					task.setValid(false);
					task.setMsg("该仓库处于未启用状态，不能进行零售！");
				} else if (store.getStatus() == StoreStatus.STOPANDONCOUNTING) {
					task.setValid(false);
					task.setMsg("该仓库处于盘点中状态，不能进行零售！");
				} else {
					task.setValid(true);
				}
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 验证手机号码是否唯一
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-6-15
	 */
	@Publish
	protected final class ValidationMobileNoIsOnlyProvider extends
			OneKeyResultProvider<Boolean, ValidationMoblieNumberIsOnlyKey> {

		@Override
		protected Boolean provide(Context context, final ValidationMoblieNumberIsOnlyKey key) throws Throwable {
			ResourceToken<Tenant> tenant = TenantHelper.getTenantToken(context);
			for (Employee emp : context.getResourceReferences(Employee.class, tenant)) {
				if (emp.getMobileNo().equals(key.getMobileNo()) && !emp.getId().equals(key.getId())) {
					return false;
				}
			}
			return true;
		}

	}

	/**
	 * 查询所有员工数量
	 */
	@Publish
	protected final class FindEmployeeCountProvider extends OneKeyResultProvider<Integer, FindEmployeeCountKey> {

		/**
		 * 查询员工数量的SQL
		 */
		private String getEmployeeCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_EmployeeCount(@tenantGuid guid) ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from sa_em_employee as t ");
			buffer.append(" where t.tenantsGuid=@tenantGuid");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context, FindEmployeeCountKey key) throws Throwable {
			DBCommand dbCommand = context.prepareStatement(getEmployeeCountSql());
			try {
				dbCommand.setArgumentValue(0, context.get(Login.class).getTenantId());
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

	/**
	 * 查询拥有用户权限的员工数量
	 */
	@Publish
	protected final class FindEmployeeHadRolesCountProvider extends
			OneKeyResultProvider<Integer, FindEmployeeHadRolesCountKey> {

		/**
		 * 查询拥有用户权限的员工数量的SQL
		 */
		private String getEmployeeHadRolesCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_EmployeeCount(@tenantGuid guid) ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from sa_em_employee as t ");
			buffer.append(" where t.tenantsGuid=@tenantGuid and t.roles>0 ");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context, FindEmployeeHadRolesCountKey key) throws Throwable {
			DBCommand dbCommand = context.prepareStatement(getEmployeeHadRolesCountSql());
			try {
				dbCommand.setArgumentValue(0, context.get(Login.class).getTenantId());
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

	/**
	 * 启用所有的用户
	 */
	@Publish
	protected final class EnabledAllUserHandler extends SimpleTaskMethodHandler<EnabledAllUserTask> {

		@Override
		protected void handle(Context context, EnabledAllUserTask task) throws Throwable {
			ResourceToken<Tenant> token = TenantHelper.getTenantToken(context);
			List<Employee> list = context.getResourceReferences(Employee.class, token);
			if (CheckIsNull.isNotEmpty(list)) {
				for (Employee item : list) {
					if (context.find(Boolean.class, new HasUserKey(item.getId()))) { // 检查认证中心是否有当前用户
						ChangeUserEnabledTask changeUserEnabledTask = new ChangeUserEnabledTask(Boolean.TRUE, item
								.getId());
						context.handle(changeUserEnabledTask);
					}
				}
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 删除员工
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-7-13
	 */
	@Publish
	protected final class DeleteEmployeeHandler extends SimpleTaskMethodHandler<DeleteEmployeeTask> {

		@Override
		protected void handle(Context context, DeleteEmployeeTask task) throws Throwable {
			ORMAccessor<EmployeeOrmEntity> acc = context.newORMAccessor(orm_Employee);
			for (GUID id : task.getEmpId()) {
				acc.delete(id);
				context.handle(new UpdateEmployeeResourceTask(id), UpdateEmployeeResourceTask.Method.Remove);
				context.handle(new RemoveUserTask(id));
			}
		}

	}
}
