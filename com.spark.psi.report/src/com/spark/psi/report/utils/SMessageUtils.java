/**
 * 
 */
package com.spark.psi.report.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Store;
import com.spark.psi.base.key.GetAncestorDeptEmployeeListByAuthKey;
import com.spark.psi.base.key.GetChildrenDeptEmployeeListByAuthKey;
import com.spark.psi.base.key.GetEmployeeListByAuthKey;
import com.spark.psi.base.key.GetEmployeeListByDepartmentIdKey;
import com.spark.psi.publish.Auth;

/**
 *
 */
public class SMessageUtils {

	/**
	 * 获得指定角色的自己的领导
	 */
	public static Set<Employee> getMyManager(Context context, Employee emp, boolean self, Auth... auths) {
		Set<Employee> list = new HashSet<Employee>();
		if (null == emp) {
			return list;
		}
		list.addAll(context.getList(Employee.class, new GetAncestorDeptEmployeeListByAuthKey(auths, emp.getDepartmentId())));
		list.addAll(context.getList(Employee.class, new GetEmployeeListByDepartmentIdKey(emp.getDepartmentId(), auths)));
		if (self) {
			list.add(emp);
		} else {
			for (Employee e : list) {
				if (e.getId().equals(emp.getId())) {
					list.remove(e);
					break;
				}
			}
		}
		return list;
	}

	/**
	 * 获得指定角色的下属部门的员工
	 */
	public static Set<Employee> getMyEmployee(Context context, Employee emp, Auth... auths) {
		Set<Employee> list = new HashSet<Employee>();
		if (null == emp) {
			return list;
		}
		list = new HashSet<Employee>(context
				.getList(Employee.class, new GetChildrenDeptEmployeeListByAuthKey(emp.getDepartmentId(), auths)));
		return list;
	}

	/**
	 * 获得指定角色的员工
	 */
	public static Set<Employee> getEmployeeByAuths(Context context, GUID tenantId, Auth... auths) {
		GetEmployeeListByAuthKey key = new GetEmployeeListByAuthKey(auths);
		return new HashSet<Employee>(context.getList(Employee.class, key));
	}

	/**
	 * 获得指定仓库的管理人员
	 */
	public static Set<Employee> getStoreManagers(Context context, Store store) {
		GUID managers[] = store.getKeeperIds();
		Set<Employee> list = new HashSet<Employee>();

		// if(CheckIsNull.isEmpty(managers)){
		// return getEmployeeByAuths(context, store.getTenantId(), Auth.Boss);
		// }
		for (GUID m : managers) {
			Employee emp = context.find(Employee.class, m);
			list.addAll(getMyManager(context, emp, false, Auth.Boss, Auth.StoreKeeperManager));
			list.add(emp);
		}
		return list;
	}

	/**
	 * 获得指定仓库的管理人员
	 */
	public static Set<Employee> getStoreManagers(Context context, GUID id) {
		Store s = context.find(Store.class, id);
		return getStoreManagers(context, s);
	}

	/**
	 * 得到销售订单的有权限之人
	 */
	public static Set<Employee> getSalesExamor(Context context, Employee creator) {
		Set<Employee> list = new HashSet<Employee>();
		Set<Employee> set = getMyManager(context, creator, false, Auth.Boss, Auth.SalesManager);
		for (Employee e : set) {
			list.add(e);
		}
		return list;
	}
}
