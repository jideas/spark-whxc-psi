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
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Partner;
import com.spark.psi.base.event.CustomerDataChangeEvent;
import com.spark.psi.base.event.CustomerTurnOfficalEvent;
import com.spark.psi.base.event.EmployeeAuthChangeEvent;
import com.spark.psi.base.event.EmployeeStatusChangeEvent;
import com.spark.psi.base.event.EmployeeStatusChangeEvent.Action;
import com.spark.psi.base.key.GetCustomerListByDepartmentIdKey;
import com.spark.psi.base.key.GetCustomerListByEmployeeIdKey;
import com.spark.psi.base.task.UpdatePartnerStatusTask;
import com.spark.psi.base.task.resource.UpdatePartnerResourceTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.PartnerType;

/**
 * 客户供应商 相关内部服务
 */

public class PartnerModuleERPService extends Service {

	protected PartnerModuleERPService() {
		super("com.spark.psi.base.internal.service.PartnerModuleService");
	}

	/**
	 * 
	 * <p>
	 * 根据员工ID获得可用客户列表
	 * </p>
	 * 1.如果员工拥有财务或者总经理职能 则可以查看所有客户 <Br>
	 * 2.如果员工拥有销售和部门管理职能 则可以查看自己的客户和自己部门及所有子部门员工关联的客户 <Br>
	 * 3.如果员工只拥有销售职能 则只能查询自己关联的客户 <Br>
	 */
	@Publish
	protected class GetCustomerListByEmployeeIdProvider extends OneKeyResultListProvider<Partner, GetCustomerListByEmployeeIdKey> {

		@Override
		protected void provide(final Context context, final GetCustomerListByEmployeeIdKey key, List<Partner> resultList) throws Throwable {
			int getType = 0;
			Employee emp = context.find(Employee.class);
			if (!emp.hasAuth(Auth.Boss, Auth.Sales, Auth.Assistant)) {
				// 如果没有总经理、财务、销售的智能，没有查询客户列表的权限
				return;
			}
			if (emp.hasAuth(Auth.Boss, Auth.Assistant)) {
				// 如果职能为总经理或者财务，则可以看所有的
				getType = 1;
			} else if (emp.hasAuth(Auth.SalesManager)) {
				// 经理只能看自己及下属负责的和共享的的
				getType = 2;
			} else {
				getType = 3;
			}
			List<Partner> alllist = context.getList(Partner.class);
			switch (getType) {
			case 1:
				resultList.addAll(alllist);
				break;
			case 2:
				Department dept = context.find(Department.class, emp.getDepartmentId());
				Department[] deps = dept.getDescendants(context, Auth.Sales);
				List<GUID> glist = new ArrayList<GUID>();
				glist.add(dept.getId());
				for (Department d : deps) {
					glist.add(d.getId());
				}
				for (Partner p : alllist) {
					if (p.getPartnerType() == PartnerType.Supplier) {
						continue;
					}
					if (null == p.getBusinessPerson()) {
						resultList.add(p);
						continue;
					}
					if (glist.contains(p.getBusinessPerson().getDepartmentId())) {
						resultList.add(p);
					}
				}
				break;
			case 3:
				for (Partner p : alllist) {
					if (p.getPartnerType() == PartnerType.Supplier) {
						continue;
					}
					if (p.getBusinessPerson() != null && p.getBusinessPerson().equals(emp.getId())) {
						resultList.add(p);
					}
				}
				break;
			}
		}

	}

	/**
	 * 根据部门id查询客户列表
	 */
	@Publish
	protected class GetCustomerListByDepartmentIdProvider extends OneKeyResultListProvider<Partner, GetCustomerListByDepartmentIdKey> {

		@Override
		protected void provide(Context context, GetCustomerListByDepartmentIdKey key, List<Partner> resultList) throws Throwable {
			List<Partner> alllist = context.getList(Partner.class);
			Department dept = context.find(Department.class, key.getDepartmentId());
			Department[] deps = dept.getDescendants(context, Auth.Sales);
			List<GUID> glist = new ArrayList<GUID>();
			glist.add(dept.getId());
			for (Department d : deps) {
				glist.add(d.getId());
			}
			for (Partner p : alllist) {
				if (p.getPartnerType() == PartnerType.Supplier) {
					continue;
				}
				if (null == p.getBusinessPerson()) {
					resultList.add(p);
					continue;
				}
				if (glist.contains(p.getBusinessPerson().getDepartmentId())) {
					resultList.add(p);
				}
			}
		}
	}

	/**
	 * 查询租户的所有客户或供应商
	 */
	@Publish
	final protected class GetAllCustomerListProvider extends OneKeyResultListProvider<Partner, PartnerType> {

		@Override
		protected void provide(Context context, final PartnerType type, List<Partner> list) throws Throwable {
			List<Partner> alllist = context.getList(Partner.class);
			for (Partner p : alllist) {
				if (p.getPartnerType() == type) {
					list.add(p);
				}
			}
		}

	}

	/**
	 * 修改客户状态
	 */
	@Publish
	protected final class UpdateStatusHandler extends SimpleTaskMethodHandler<UpdatePartnerStatusTask> {

		@SuppressWarnings("static-access")
		@Override
		protected void handle(Context context, UpdatePartnerStatusTask task) throws Throwable {
			Partner p = context.find(Partner.class, task.getPartnerId());
			if (p.getStatus()==PartnerStatus.Official) {
				return;
			}
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			if (p.getPartnerType() == PartnerType.Customer) {
				ub.setTable(ERPTableNames.Base.Customer.getTableName());
			} else {
				ub.setTable(ERPTableNames.Base.Supplier.getTableName());
			}
			ub.addColumn("status", ub.STRING, task.getPartnerStatus().Official.getCode());
			ub.addColumn("canDelete", ub.BOOLEAN, false);
			ub.addCondition("id", ub.guid, task.getPartnerId(), "t.RECID = @id");
			int i = ub.execute();
			if (1 == i) {
				context.handle(new UpdatePartnerResourceTask(task.getPartnerId()), UpdatePartnerResourceTask.Method.Modify);
				context.dispatch(new CustomerTurnOfficalEvent(task.getPartnerId()));
			}
		}
	}

	/**
	 * 更改客户业务负责人，（已过渡完成）
	 */
	static void changeCustomerBusPerson(Context context, GUID customerId, GUID empid) {
		UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
		ub.setTable(ERPTableNames.Base.Customer.getTableName());
		if (empid == null) {
			ub.addColumnNull("businessPerson");
			ub.addColumnNull("deptId");
		} else {
			ub.addColumn("businessPerson", ub.guid, empid);
			Employee emp = context.find(Employee.class, empid);
			ub.addColumn("deptId", ub.guid, emp.getDepartmentId());
		}
		ub.addCondition("id", ub.guid, customerId, "t.RECID = @id");
		ub.execute();
		context.handle(new UpdatePartnerResourceTask(customerId), UpdatePartnerResourceTask.Method.Modify);
		context.dispatch(new CustomerDataChangeEvent(customerId)); // 触发客户基础信息改变事件
	}

	/*****************************************************************
	 *******************事件监听器************************************
	 **************************************************************/
	/**
	 * 员工离职事件
	 */
	@Publish
	protected final class EmployeeStatusChangeListener extends EventListener<EmployeeStatusChangeEvent> {

		@Override
		protected void occur(Context context, EmployeeStatusChangeEvent event) throws Throwable {
			if (event.getAction() == Action.Resign) { // 如果员工离职
				List<Partner> list = context.getList(Partner.class, PartnerType.Customer);
				for (Partner partner : list) {
					if (partner.getBusinessPerson() == null)
						continue;
					if (partner.getBusinessPerson().getId().equals(event.getId())) { // 将离职员工管理的客户设为共享客户
						changeCustomerBusPerson(context, partner.getId(), null);
					}
				}
			}
		}

	}

	@Publish
	protected final class EmployeeAuthChangeListener extends EventListener<EmployeeAuthChangeEvent> {

		@Override
		protected void occur(Context context, EmployeeAuthChangeEvent event) throws Throwable {
			Employee emp = context.find(Employee.class, event.getEmpId());
			if (emp.hasAuth(Auth.Sales))
				return;
			List<Partner> list = context.getList(Partner.class, PartnerType.Customer);
			for (Partner partner : list) {
				if (partner.getBusinessPerson() == null)
					continue;
				if (partner.getBusinessPerson().getId().equals(event.getEmpId())) {
					changeCustomerBusPerson(context, partner.getId(), null);
				}
			}

		}

	}

}
