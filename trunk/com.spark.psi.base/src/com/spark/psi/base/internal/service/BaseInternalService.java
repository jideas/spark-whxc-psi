package com.spark.psi.base.internal.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.task.resource.GenerateSerialNumberTask;
import com.spark.psi.publish.Auth;

/**
*
*/
public class BaseInternalService extends Service {

	/**
	 * 
	 */
	protected BaseInternalService() {
		super("基础模块内部服务");
	}

	/**
	 * 查询当前用户的权限
	 */
	@Publish
	class AuthDataProvider extends OneKeyResultProvider<Boolean, Auth> {

		protected Boolean provide(Context context, Auth auth) throws Throwable {
			Login login = context.find(Login.class);
			return login.hasAuth(auth);
		}

	}

	/**
	 * 查询当前用户的权限
	 */
	@Publish
	class AuthDataProvider2 extends OneKeyResultProvider<Boolean, Auth[]> {

		protected Boolean provide(Context context, Auth[] auths) throws Throwable {
			Login login = context.find(Login.class);
			return login.hasAuth(auths);
		}

	}

	/**
	 * 查询指定用户的权限（不建议细粒度调用，如果要频繁调用，建议在员工信息中缓冲）
	 */
	@Publish
	class AuthDataProvider3 extends TwoKeyResultProvider<Boolean, Auth, GUID> {

		protected Boolean provide(Context context, Auth target, GUID employeeId) throws Throwable {
			Employee employee = context.find(Employee.class, employeeId);
			if (null == employee) {
				return false;
			}
			if (employeeId.equals(employee.getTenantId())) {
				return true; // XXX：总经理账户，但是不应该这么处理
			}
			return context.getList(Auth.class, employee.getTenantId(), employee.getRoles()).indexOf(target) != -1;
		}

	}

	/**
	 * 查询指定用户的权限（不建议细粒度调用，如果要频繁调用，建议在员工信息中缓冲）
	 */
	@Publish
	class AuthDataProvider4 extends TwoKeyResultProvider<Boolean, Auth[], GUID> {

		protected Boolean provide(Context context, Auth[] auths, GUID employeeId) throws Throwable {
			Employee employee = context.find(Employee.class, employeeId);
			if (employee.getTenantId().equals(employeeId)) {
				return true; // XXX：总经理账户，但是不应该这么处理
			}
			Set<Auth> allAcls = new HashSet<Auth>();
			allAcls.addAll(context.getList(Auth.class, employee.getTenantId(), employee.getRoles()));
			for (Auth auth : auths) {
				if (!allAcls.contains(auth)) {
					return false;
				}
			}
			return true;
		}

	}

	/**
	 * 查询指定租户下指定角色集合的权限（仅在资源初始化时避免循环依赖时调用，不建议细粒度调用，如果要频繁调用，建议在员工信息中缓冲）
	 */
	@Publish
	class AuthDataProvider5 extends ThreeKeyResultProvider<Boolean, Auth, GUID, Integer> {

		protected Boolean provide(Context context, Auth target, GUID tenantId, Integer roles) throws Throwable {
			return context.getList(Auth.class, tenantId, roles).indexOf(target) != -1;
		}

	}

	/**
	 * 
	 * 返回指定人员可以管辖的销售人员列表<br>
	 * (1)如果是总经理，则返回所有员工；<br>
	 * (2)如果是销售经理，则返回他自己以及部门、子部门的所有员工；<br>
	 * (3)如果是普通销售，则返回他自己 <br>
	 * (4)如果是其他员人员，返回空
	 */
	@Publish
	class SalesmanListProvider extends OneKeyResultListProvider<Employee, Employee> {

		@Override
		protected void provide(Context context, Employee employee, List<Employee> resultList) throws Throwable {
			if (context.find(Boolean.class, Auth.Boss, employee.getId())) {
				resultList.addAll(context.getList(Employee.class));
				// 总经理
			} else if (context.find(Boolean.class, Auth.Sales, employee.getId())) {
				// 销售经理
				if (context.find(Boolean.class, Auth.SalesManager, employee.getId())) {
					// TODO：
				} else {
					// 普通销售人员
					resultList.add(employee);
				}
			}

		}
	}

	@Publish
	class GetNextSheetNumberProvider extends OneKeyResultProvider<String, SheetNumberType> {

		@Override
		protected String provide(Context context, SheetNumberType key) throws Throwable {
			// TODO：根据租户配置生成前缀

			//
			StringBuffer buffer = new StringBuffer(key.getDefaultPrefix());
			if (!key.isOnlyOrderNo()) {
				Calendar calendar = Calendar.getInstance();
				// year
				buffer.append(calendar.get(Calendar.YEAR));
				// month
				int month = calendar.get(Calendar.MONTH) + 1;
				if (month < 10) {
					buffer.append('0');
				}
				buffer.append(month);
				// day
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				if (day < 10) {
					buffer.append('0');
				}
				buffer.append(day);
			}
			//
			int serialLength = key.getLength(); // XXX：便于测试，设置为最大长度
			StringBuffer serialBuffer = new StringBuffer(serialLength);
			GenerateSerialNumberTask task = new GenerateSerialNumberTask(key.name(), buffer.toString());
			context.handle(task);
			serialBuffer.append(task.getResult());
			if (serialBuffer.length() > serialLength) {
				throw new IllegalStateException("单据编号超出设置的最大范围");
			}
			while (serialBuffer.length() < serialLength) {
				serialBuffer.insert(0, '0');
			}

			//
			return buffer.append(serialBuffer).toString();
		}
	}

}
