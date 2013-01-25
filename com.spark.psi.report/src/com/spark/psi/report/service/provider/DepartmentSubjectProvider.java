/**
 * 
 */
package com.spark.psi.report.service.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.ComparatorUtils;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.BalanceAmount;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.key.GetBalanceAmountByPartnerKey;
import com.spark.psi.base.key.GetChildrenDeptEmployeeListByAuthKey;
import com.spark.psi.base.key.GetCustomerListByDepartmentIdKey;
import com.spark.psi.base.key.OverPeriodAmountKey;
import com.spark.psi.publish.Auth;
import com.spark.psi.report.constant.TargetEnum.CompanyEnum;
import com.spark.psi.report.constant.TargetEnum.DateTimeEnum;
import com.spark.psi.report.constant.TargetEnum.DepartmentEnum;
import com.spark.psi.report.entity.MonitorTargetEntity;
import com.spark.psi.report.entity.ReportResult;
import com.spark.psi.report.key.MonitorTargetKey;
import com.spark.psi.report.key.ReportCommonKey;
import com.spark.psi.report.utils.Condition;
import com.spark.psi.report.utils.QuerySqlBuilder;
import com.spark.psi.report.utils.ReportDateUtils;
import com.spark.psi.report.utils.ReportUtils;

/**
 *
 */
public abstract class DepartmentSubjectProvider {

	/**
	 * 部门报表数据查询
	 * 
	 * @param context
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	protected static void provide(Context context, ReportCommonKey key,
			List<ReportResult> list) {
		Tenant tenant = context.find(Tenant.class);
		Login login = context.find(Login.class);
		Map<String, ReportResult> rrmap = new HashMap<String, ReportResult>();
		List<Enum> targets = key.getTargets();
		Condition depCon = ReportUtils.findCondition("DepartmentId", key
				.getConditions());
		if (targets.indexOf(DepartmentEnum.TotalSalesAmount) >= 0
				|| targets.indexOf(DepartmentEnum.TotalSalesReturnAmount) >= 0
				|| targets.indexOf(DepartmentEnum.TotalReceiptedAmount) >= 0) {
			// 销售金额退货金额收款金额
			departmentDataProvide1(context, key, tenant, targets, rrmap);
		}
		for (String keystr : rrmap.keySet()) {
			ReportResult rr = rrmap.get(keystr);
			GUID deptId = null;
			if (null == depCon) {
				deptId = rr.getTargetValue(GUID.class, DepartmentEnum.Id);
			} else {
				deptId = (GUID) depCon.getValue();
			}
			if (targets.indexOf(DepartmentEnum.TotalReceiptingBanlance) >= 0) {
				// 应收总金额
				rr.setTargetValue(DepartmentEnum.TotalReceiptingBanlance,
						getTatolReceiptingAmount(context, deptId));
			}
			if (targets.indexOf(DepartmentEnum.OverCreditDayAmount) >= 0) {
				// 超账期欠款金额
				List<Partner> plist = context.getList(Partner.class,
						new GetCustomerListByDepartmentIdKey(deptId));
				double value = 0;
				for (Partner p : plist) {
					OverPeriodAmountKey key2 = new OverPeriodAmountKey();
					key2.setPartnerId(p.getId());
					key2.setAccountPeriod(p.getAccountPeriod());
					key2.setQueryAll(true);
					key2.setTenantId(login.getTenantId());
					Double overValue = context.find(Double.class, key2);
					value += overValue;
				}
				rr.setTargetValue(DepartmentEnum.OverCreditDayAmount, value);
			}
			if (targets.indexOf(DepartmentEnum.SalesTargetAmount) >= 0
					|| targets
							.indexOf(DepartmentEnum.TotalReceiptedTargetAmount) >= 0) {
				GUID id = null;
				if (null == depCon) {
					Employee emp = context.find(Employee.class, login
							.getEmployeeId());
					id = emp.getDepartmentId();
				} else {
					id = (GUID) depCon.getValue();
				}
				int max = 6;
				DateTimeEnum datetarget = DateTimeEnum.getTarget(key
						.getOrderTarget());
				if (key.isDateColumn()) {
					if (datetarget == DateTimeEnum.Day) {
						max = 1;
					}
				}
				MonitorTargetKey mtKey = new MonitorTargetKey(id, max);
				List<MonitorTargetEntity> mtlist = context.getList(
						MonitorTargetEntity.class, mtKey);
				Map<String, ReportResult> map = new HashMap<String, ReportResult>();
				for (ReportResult rr1 : list) {
					map.put(rr1.getTargetValue(datetarget) + "", rr);
				}
				if (max == 6) {
					for (MonitorTargetEntity e : mtlist) {
						ReportResult rr1 = map.get(e.getDateNo());
						if (rr1 == null) {
							rr1 = new ReportResult();
							rr1.setTargetValue(CompanyEnum.SalesAmountTarget, e
									.getSalesAmount());
							rr1.setTargetValue(
									CompanyEnum.SalesReceiptAmountTarget, e
											.getReceiptAmount());
							rr1.setTargetValue(datetarget, e.getDateNo());
							list.add(rr1);
						} else {
							rr1.setTargetValue(CompanyEnum.SalesAmountTarget, e
									.getSalesAmount());
							rr1.setTargetValue(
									CompanyEnum.SalesReceiptAmountTarget, e
											.getReceiptAmount());
						}
					}
				} else if (max == 1) {
					if (mtlist.size() == 0) {
						return;
					}
					MonitorTargetEntity mt = mtlist.get(0);
					for (int i = 1; i <= ReportDateUtils.getDateNo(new Date()) % 100; i++) {
						String str = "" + i;
						if (i < 10) {
							str = "0" + str;
						}

						ReportResult rr1 = map.get(mt.getDateNo() + str);
						if (rr1 == null) {
							rr1 = new ReportResult();
							rr1.setTargetValue(CompanyEnum.SalesAmountTarget,
									mt.getSalesAmount());
							rr1.setTargetValue(
									CompanyEnum.SalesReceiptAmountTarget, mt
											.getReceiptAmount());
							rr1.setTargetValue(datetarget, Integer.parseInt(mt
									.getDateNo()
									+ str));
							list.add(rr1);
						} else {
							rr1.setTargetValue(CompanyEnum.SalesAmountTarget,
									mt.getSalesAmount());
							rr1.setTargetValue(
									CompanyEnum.SalesReceiptAmountTarget, mt
											.getReceiptAmount());
						}
					}
				}
			}
		}
		Enum target = null;
		if (CheckIsNull.isNotEmpty(key.getOrderTarget())) {
			if (key.isDateColumn()) {
				target = DateTimeEnum.getTarget(key.getOrderTarget());
			} else {
				target = DepartmentEnum.getTarget(key.getOrderTarget());
			}
		}
		for (ReportResult rr : rrmap.values()) {
			rr.setOrderObj(target);
			list.add(rr);
		}
		if (null != target) {
			ComparatorUtils.sort(list, "orderObj", key.isOrderDesc());
		}
	}

	/**
	 * 得到本部门应收总额
	 * 
	 * @param deptId
	 * @return
	 */
	private static Double getTatolReceiptingAmount(Context context, GUID deptId) {
		double amount = 0;
		List<Partner> plist = context.getList(Partner.class,
				new GetCustomerListByDepartmentIdKey(deptId));
		for (Partner p : plist) {
			BalanceAmount ba = context.find(BalanceAmount.class,
					new GetBalanceAmountByPartnerKey(p.getId()));
			amount += ba.getDueAmount();
		}
		return amount;
	}

	/**
	 * 部门的销售分析数据
	 * 
	 * @param context
	 * @param key
	 * @param tenant
	 * @param targets
	 * @param rrmap
	 */
	@SuppressWarnings("unchecked")
	private static void departmentDataProvide1(Context context,
			ReportCommonKey key, Tenant tenant, List<Enum> targets,
			Map<String, ReportResult> rrmap) {
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_DEPT_SALES_DATE", "t");

		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");
		qb.addColumn("t.deptGuid", "deptGuid");

		qb.addNotEquals("t.deptGuid", "@comNo");

		Login login = context.find(Login.class);
		Condition depCon = ReportUtils.findCondition("DepartmentId", key
				.getConditions());
		if (depCon == null) {
			depCon = ReportUtils.findCondition("ParentDepartmentId", key
					.getConditions());
		}
		GUID deptId1 = null;
		if (null == depCon) {
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			deptId1 = emp.getDepartmentId();
		} else {
			deptId1 = (GUID) depCon.getValue();
		}
		Department dep = context.find(Department.class, deptId1);
		Department[] ds = dep.getChildren(context, Auth.Sales,
				Auth.SalesManager);
		// if (key.getMaxCount() == 0) {
		// qb.addArgs("deptIdthis", "guid", deptId1);
		// qb.addEquals("t.deptGuid", "@deptIdthis");
		// } else if (ds != null && ds.length > 0) {
		List<String> argss = new ArrayList<String>();
		int i = 0;
		for (Department d : ds) {
			String strvalue = "deptIdstr" + i++;
			qb.addArgs(strvalue, qb.guid, d.getId());
			argss.add("@" + strvalue);
		}
		qb.addIn("t.deptGuid", argss);
		// }

		if (targets.indexOf(DepartmentEnum.DayNo) >= 0) {
			qb.addColumn("t.dateNo", "dateNo");
			qb.addGroupBy("t.dateNo");
		}
		if (targets.indexOf(DepartmentEnum.MonthNo) >= 0) {
			qb.addColumn("t.monthNo", "monthNo");
			qb.addGroupBy("t.monthNo");
		}
		if (targets.indexOf(DepartmentEnum.Quarter) >= 0) {
			qb.addColumn("t.quarter", "quarter");
			qb.addGroupBy("t.quarter");
		}
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.receiptAmount)", "receiptAmount");
		qb.addColumn("sum(t.rtnAmount)", "rtnAmount");
		Map<GUID, ReportResult> ymap = null;
		if (targets.indexOf(DepartmentEnum.TotalSalesAmount_OfYear) >= 0
				|| targets
						.indexOf(DepartmentEnum.TotalSalesReturnAmount_OfYear) >= 0) {
			ymap = getYearDoubleMap(context, key, tenant, ds);
		}
		if (null != key.getConditions())
			for (Condition con : key.getConditions()) {
				if ("month".toUpperCase().equals(
						con.getConditionColumn().toUpperCase())) {
					qb.addArgs("month", qb.INT, con.getValue());
					qb.addEquals("t.monthNo", "@month");
				} else if ("season".toUpperCase().equals(
						con.getConditionColumn().toUpperCase())) {
					qb.addArgs("season", qb.INT, con.getValue());
					qb.addEquals("t.quarter", "@season");
				} else if ("ThisMonth".toUpperCase().equals(
						con.getConditionColumn().toUpperCase())) {
					qb.addArgs("month", qb.INT, ReportDateUtils
							.getMonthNo(new Date()));
					qb.addEquals("t.monthNo", "@month");
				}
			}
		String orderStr = null;
		if (CheckIsNull.isNotEmpty(key.getOrderTarget())) {
			if (key.isDateColumn()) {
				DateTimeEnum datetarget = DateTimeEnum.getTarget(key
						.getOrderTarget());
				orderStr = datetarget.getColumnName();
				qb.addColumn("t." + orderStr, orderStr);
				qb.addGroupBy("t." + orderStr);
			} else if (DepartmentEnum.TotalSalesAmount == DepartmentEnum
					.getTarget(key.getOrderTarget())) {
				orderStr = "ordAmount";
			} else if (DepartmentEnum.TotalSalesReturnAmount == DepartmentEnum
					.getTarget(key.getOrderTarget())) {
				orderStr = "rtnAmount";
			} else if (DepartmentEnum.TotalReceiptedAmount == DepartmentEnum
					.getTarget(key.getOrderTarget())) {
				orderStr = "deductionAmount";
			}
			if (key.isOrderDesc()) {
				orderStr += " desc ";
			}
		}
		if (CheckIsNull.isNotEmpty(orderStr)) {
			qb.addOrderBy("t." + orderStr);
		}
		RecordSet rs = null;
		if (key.getMaxCount() == 0) {
			rs = qb.getRecord();
		} else {
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		List<GUID> glist = new ArrayList<GUID>();
		while (rs.next()) {
			String keystr = "key";
			ReportResult rr = new ReportResult();
			int index = 0;
			GUID deptId = rs.getFields().get(index++).getGUID();
			if (null == deptId) {
				continue;
			}
			keystr += deptId;
			Department depart = context.find(Department.class, deptId);
			rr.setTargetValue(DepartmentEnum.Name, depart.getName());
			rr.setTargetValue(DepartmentEnum.Id, deptId);
			glist.add(deptId);
			if (targets.indexOf(DepartmentEnum.EmployeesCount) >= 0) {
				List<Employee> elist = context.getList(Employee.class,
						new GetChildrenDeptEmployeeListByAuthKey(
								depart.getId(), Auth.Sales));
				rr.setTargetValue(DepartmentEnum.EmployeesCount, elist.size());
			}
			if (targets.indexOf(DepartmentEnum.DayNo) >= 0) {
				Integer value = rs.getFields().get(index++).getInt();
				keystr += value;
				rr.setTargetValue(DepartmentEnum.DayNo, value);
			}
			if (targets.indexOf(DepartmentEnum.MonthNo) >= 0) {
				Integer value = rs.getFields().get(index++).getInt();
				keystr += value;
				rr.setTargetValue(DepartmentEnum.MonthNo, value);
			}
			if (targets.indexOf(DepartmentEnum.Quarter) >= 0) {
				Integer value = rs.getFields().get(index++).getInt();
				keystr += value;
				rr.setTargetValue(DepartmentEnum.Quarter, value);
			}
			rr.setTargetValue(DepartmentEnum.TotalSalesAmount, rs.getFields()
					.get(index++).getDouble());
			rr.setTargetValue(DepartmentEnum.TotalReceiptedAmount, rs
					.getFields().get(index++).getDouble());
			rr.setTargetValue(DepartmentEnum.TotalSalesReturnAmount, rs
					.getFields().get(index++).getDouble());
			if (targets.indexOf(DepartmentEnum.TotalSalesAmount_OfYear) >= 0
					|| targets
							.indexOf(DepartmentEnum.TotalSalesReturnAmount_OfYear) >= 0) {
				ReportResult tt = ymap.get(deptId);
				if (tt != null) {
					rr
							.setTargetValue(
									DepartmentEnum.TotalSalesAmount_OfYear,
									tt
											.getTargetValue(DepartmentEnum.TotalSalesAmount_OfYear));

					rr
							.setTargetValue(
									DepartmentEnum.TotalSalesReturnAmount_OfYear,
									tt
											.getTargetValue(DepartmentEnum.TotalSalesReturnAmount_OfYear));
				}
			}
			if (CheckIsNull.isNotEmpty(key.getOrderTarget())
					&& key.isDateColumn()) {
				rr.setTargetValue(DateTimeEnum.getTarget(key.getOrderTarget()),
						rs.getFields().get(index++).getInt());
			}
			rrmap.put(keystr, rr);
		}
		for (Department d : ds) {
			if (glist.contains(d.getId())) {
				continue;
			}
			ReportResult rr = new ReportResult();
			String keystr = "key";
			keystr += d.getId();
			rr.setTargetValue(DepartmentEnum.Name, d.getName());
			rr.setTargetValue(DepartmentEnum.Id, d.getId());

			rr.setTargetValue(DepartmentEnum.TotalSalesAmount, 0);
			rr.setTargetValue(DepartmentEnum.TotalReceiptedAmount, 0);
			rr.setTargetValue(DepartmentEnum.TotalSalesReturnAmount, 0);

			if (ymap != null && ymap.get(d.getId()) != null) {
				ReportResult tt = ymap.get(d.getId());
				rr
						.setTargetValue(
								DepartmentEnum.TotalSalesAmount_OfYear,
								tt
										.getTargetValue(DepartmentEnum.TotalSalesAmount_OfYear));
				rr
						.setTargetValue(
								DepartmentEnum.TotalSalesReturnAmount_OfYear,
								tt
										.getTargetValue(DepartmentEnum.TotalSalesReturnAmount_OfYear));
			}
			if (targets.indexOf(DepartmentEnum.EmployeesCount) >= 0) {
				List<Employee> elist = context.getList(Employee.class,
						new GetChildrenDeptEmployeeListByAuthKey(d.getId(),
								Auth.Sales));
				rr.setTargetValue(DepartmentEnum.EmployeesCount, elist.size());
			}
			if (key.getMaxCount() == 0
					|| key.getMaxCount() >= rrmap.keySet().size()) {
				rrmap.put(keystr, rr);
			} else {
				break;
			}
		}
	}

	/**
	 * @param context
	 * @param key
	 * @param tenant
	 * @param ds
	 * @return
	 */
	private static Map<GUID, ReportResult> getYearDoubleMap(Context context,
			ReportCommonKey key, Tenant tenant, Department[] ds) {
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_DEPT_SALES_DATE", "t");
		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");
		String dateKey = null, dateValue = null;
		int year = 0;
		for (Condition con : key.getConditions()) {
			if ("month".toUpperCase().equals(
					con.getConditionColumn().toUpperCase())) {
				qb.addArgs("month", qb.INT, con.getValue());
				year = Integer.parseInt((con.getValue() + "").substring(0, 4));
				dateKey = "t.monthNo";
				dateValue = "@month";
			} else if ("season".toUpperCase().equals(
					con.getConditionColumn().toUpperCase())) {
				qb.addArgs("season", qb.INT, con.getValue());
				year = Integer.parseInt((con.getValue() + "").substring(0, 4));
				dateKey = "t.quarter";
				dateValue = "@season";
			}
		}
		qb.addLessThanOrEquals(dateKey, dateValue);
		qb.addArgs("year", qb.INT, year);
		qb.addEquals("t.yearNo", "@year");

		List<String> params = new ArrayList<String>();
		for (int i = 0; i < ds.length; i++) {
			Department d = ds[i];
			qb.addArgs("deptId" + i, qb.guid, d.getId());
			params.add("@deptId" + i);
		}
		qb.addIn("t.deptGuid", params);
		qb.addGroupBy("t.deptGuid");

		qb.addColumn("t.deptGuid", "id");
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.rtnAmount)", "rtnAmount");
		qb.addGroupBy("t.deptGuid");
		RecordSet rs = qb.getRecord();
		Map<GUID, ReportResult> map = new HashMap<GUID, ReportResult>();
		while (rs.next()) {
			GUID id = rs.getFields().get(0).getGUID();
			ReportResult rr = new ReportResult();
			rr.setTargetValue(DepartmentEnum.TotalSalesAmount_OfYear, rs
					.getFields().get(1).getDouble());
			rr.setTargetValue(DepartmentEnum.TotalSalesReturnAmount_OfYear, rs
					.getFields().get(2).getDouble());
			map.put(id, rr);
		}
		return map;
	}
}
