/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-8    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-8    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.Filter;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.key.GetAncestorDeptEmployeeListByAuthKey;
import com.spark.psi.base.key.GetChildrenDeptEmployeeListByAuthKey;
import com.spark.psi.base.key.GetEmployeeListByAuthKey;
import com.spark.psi.base.key.GetEmployeeListByDepartmentIdKey;
import com.spark.psi.base.key.GetSalesManagerListByDepartmentIdKey;
import com.spark.psi.base.key.GetEmployeeListByAuthKey.Level;
import com.spark.psi.base.key.organization.GetAncestorsDepartmentListKey;
import com.spark.psi.base.key.organization.GetChildrenDepartmentListKey;
import com.spark.psi.base.key.organization.GetDescendantDepartmentListKey;
import com.spark.psi.publish.Auth;

/**
 * <p>
 * ģ���ڲ�Ա�������ṩ��
 * </p>
 * 
 * 
 * 
 * 
 * 
 * @version 2012-3-8
 */

public class EmployeeModuleService extends Service {

	protected EmployeeModuleService() {
		super("com.spark.psi.base.internal.service.EmployeeModuleService");
	}

	/**
	 * 
	 * <p>
	 * ���ָ�����ŵ����۾����б�
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-3-8
	 */
	@Publish
	protected class GetSalesMangerListByDepartmentIdProvider extends OneKeyResultProvider<GUID[], GetSalesManagerListByDepartmentIdKey> {

		@Override
		protected GUID[] provide(final Context context, final GetSalesManagerListByDepartmentIdKey key) throws Throwable {
			ResourceToken<Tenant> token = TenantHelper.getTenantToken(context);
			List<Employee> list = context.getResourceReferences(Employee.class, token, new Filter<Employee>() {

				public boolean accept(Employee emp) {
					return emp.hasAuth(Auth.SalesManager) && emp.getDepartmentId().equals(key.getId());
				}
			});
			GUID[] result = new GUID[list.size()];
			for (int i = 0; i < list.size(); i++) {
				result[i] = list.get(i).getId();
			}
			return result;
		}

	}

	/**
	 * 
	 * <p>
	 * ͨ��ְ�ܻ��Ա���б�
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-3-13
	 */
	@Publish
	protected class GetEmoloyeeListByAuthProvider extends OneKeyResultListProvider<Employee, GetEmployeeListByAuthKey> {

		@Override
		protected void provide(final Context context, final GetEmployeeListByAuthKey key, List<Employee> resultList) throws Throwable {
			List<Employee> list = context.getList(Employee.class);
			for (Employee emp : list) {
				if (emp.getName().indexOf(key.getSearchText()) >= 0 && emp.hasAuth(key.getAuths())) {
					resultList.add(emp);
				}
			}
		} 
	}

	/**
	 * 
	 * <p>
	 * ��ѯ��ǰ���ż��Ӳ��ŵ�ӵ��ָ��ְ�ܵ�Ա���б�
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-3-14
	 */
	@Publish
	protected final class GetEmployeeListByAuthProvider2 extends OneKeyResultListProvider<Employee, GetChildrenDeptEmployeeListByAuthKey> {

		@Override
		protected void provide(final Context context, final GetChildrenDeptEmployeeListByAuthKey key, List<Employee> resultList)
				throws Throwable {
			List<Department> list;
			if (Level.Max == key.getLevel()) {
				list = context.getList(Department.class, new GetDescendantDepartmentListKey(key.getDepartmentId(), key.getAuths()));
			} else {
				list = context.getList(Department.class, new GetChildrenDepartmentListKey(key.getDepartmentId(), key.getAuths()));
			}
			for (Department department : list) {
				ResourceToken<Department> token = context.findResourceToken(Department.class, department.getId());
				resultList.addAll(context.getResourceReferences(Employee.class, token, new Filter<Employee>() {
					public boolean accept(Employee item) {
						return item.hasAuth(key.getAuths());
					}
				}));
			}
		}
	}

	/**
	 * 
	 * <p>
	 * ��ѯ�������Ȳ��ţ��ӱ���һֱ׷�ݵ���˾��һˮ���Ĳ��ţ�ָ��ְ�ܵ�Ա���б�����ָ�����ŵ�Ա����
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-3-15
	 */
	@Publish
	protected final class GetEmployeeListByAuthProvider3 extends OneKeyResultListProvider<Employee, GetAncestorDeptEmployeeListByAuthKey> {

		@Override
		protected void provide(final Context context, final GetAncestorDeptEmployeeListByAuthKey key, List<Employee> resultList)
				throws Throwable {
			List<Department> list = context.getList(Department.class, new GetAncestorsDepartmentListKey(key.getDepartmentId()));
			for (Department department : list) {
				ResourceToken<Department> token = context.findResourceToken(Department.class, department.getId());
				resultList.addAll(context.getResourceReferences(Employee.class, token, new Filter<Employee>() {

					public boolean accept(Employee item) {
						return item.hasAuth(key.getAuths());
					}
				}));
			}
		}
	}

	/**
	 * 
	 * <p>
	 * ��ѯָ��������������յ�Ա���б�
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-4-6
	 */
	@Publish
	final protected class GetAllBirthdayEmployeeProvider extends TwoKeyResultListProvider<Employee, Long, Long> {

		@Override
		protected void provide(Context context, Long start, Long end, List<Employee> resultList) throws Throwable {
			List<Employee> list = context.getList(Employee.class);
			for (Employee emp : list) {
				if (getBirthday(emp.getBirthday()) >= start && getBirthday(emp.getBirthday()) <= end) {
					resultList.add(emp);
				}
			}
		}

		private long getBirthday(long date) {
			Calendar c1 = Calendar.getInstance();
			c1.setTimeInMillis(date);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(new Date());
			c1.set(Calendar.YEAR, c2.get(Calendar.YEAR));
			return c1.getTimeInMillis();
		}

	}

	/**
	 * <p>
	 * ��ѯָ�����ţ��������Ӳ��� )��Ա���б�
	 * </p> 
	 */
	@Publish
	final protected class GetEmployeeListByDepartmentIdProvider extends
			OneKeyResultListProvider<Employee, GetEmployeeListByDepartmentIdKey> {

		@Override
		protected void provide(Context context, final GetEmployeeListByDepartmentIdKey key, List<Employee> resultList) throws Throwable {
			ResourceToken<Department> token = context.findResourceToken(Department.class, key.getDepartmentId());
			resultList.addAll(context.getResourceReferences(Employee.class, token, new Filter<Employee>() {
				public boolean accept(Employee emp) {
					return emp.hasAuth(key.getAuths());
				}
			}));
		}

	} 

}
