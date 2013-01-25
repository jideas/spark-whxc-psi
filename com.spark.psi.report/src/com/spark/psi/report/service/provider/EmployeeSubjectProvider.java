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
import com.spark.psi.base.key.GetCustomerListByEmployeeIdKey;
import com.spark.psi.base.key.GetEmployeeListByAuthKey;
import com.spark.psi.publish.Auth;
import com.spark.psi.report.constant.ConditionEnum.EmployeeConditionEnum;
import com.spark.psi.report.constant.TargetEnum.CompanyEnum;
import com.spark.psi.report.constant.TargetEnum.DateTimeEnum;
import com.spark.psi.report.constant.TargetEnum.EmployeeEnum;
import com.spark.psi.report.entity.MonitorTargetEntity;
import com.spark.psi.report.entity.ReportResult;
import com.spark.psi.report.key.MonitorTargetKey;
import com.spark.psi.report.key.ReportCommonKey;
import com.spark.psi.report.utils.AuthUtils;
import com.spark.psi.report.utils.Condition;
import com.spark.psi.report.utils.ConditionUtil;
import com.spark.psi.report.utils.QuerySqlBuilder;
import com.spark.psi.report.utils.ReportDateUtils;
import com.spark.psi.report.utils.ReportUtils;

/**
 *
 */
public abstract class EmployeeSubjectProvider{
	/**
	 * 员工报表数据查询
	 * 
	 * @param context
	 * @param key
	 * @param list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected static void provide(Context context, ReportCommonKey key, List<ReportResult> list) throws Exception{
		Tenant tenant = context.find(Tenant.class);
		Login login = context.find(Login.class);
		Map<String, ReportResult> rrmap = new HashMap<String, ReportResult>();
		List<Enum> targets = key.getTargets();
		if(targets.indexOf(EmployeeEnum.TotalSalesAmount) >= 0
		        || targets.indexOf(EmployeeEnum.TotalSalesReturnAmount) >= 0
		        || targets.indexOf(EmployeeEnum.TotalReceiptedAmount) >= 0)
		{
			// 销售金额退货金额收款金额
			employeeDataProvide2(context, key, login, targets, rrmap);
		}
		if(targets.indexOf(EmployeeEnum.TotalReceiptingBanlance) >= 0){
			// 应收总金额	
			if(rrmap.size() == 0 && CheckIsNull.isNotEmpty(key.getConditions())
			        && key.getConditions().indexOf(EmployeeConditionEnum.EmployeeId) >= 0)
			{
				Condition con = key.getConditions().get(key.getConditions().indexOf(EmployeeConditionEnum.EmployeeId));
				double amount = getTatolReceiptingAmount(context, (GUID)con.getValue());
				ReportResult rr = new ReportResult();
				rr.setTargetValue(EmployeeEnum.Id, con.getValue());
				rr.setTargetValue(EmployeeEnum.TotalReceiptingBanlance, amount);
				String keystr = "key" + con.getValue().toString();
				rrmap.put(keystr, rr);
			}
			else if(rrmap.size() > 0){
				for(String keystr : rrmap.keySet()){
					ReportResult rr = rrmap.get(keystr);
					GUID empId = rr.getTargetValue(GUID.class, EmployeeEnum.Id);
					rr.setTargetValue(EmployeeEnum.TotalReceiptingBanlance, getTatolReceiptingAmount(context, empId));
					rrmap.put(keystr, rr);
				}
			}

		}
		if(targets.indexOf(EmployeeEnum.OverCreditDayAmount) >= 0){
			// 超账期欠款金额

		}
		if(targets.indexOf(EmployeeEnum.SalesTargetAmount) >= 0
		        || targets.indexOf(EmployeeEnum.TotalReceiptedTargetAmount) >= 0)
		{
			GUID id = login.getEmployeeId();
			int max = 6;
			DateTimeEnum datetarget = DateTimeEnum.getTarget(key.getOrderTarget());
			if(key.isDateColumn()){
				if(datetarget == DateTimeEnum.Day){
					max = 1;
				}
			}
			MonitorTargetKey mtKey = new MonitorTargetKey(id, max);
			List<MonitorTargetEntity> mtlist = context.getList(MonitorTargetEntity.class, mtKey);
			Map<String, ReportResult> map = new HashMap<String, ReportResult>();
			for(ReportResult rr : list){
				map.put(rr.getTargetValue(datetarget) + "", rr);
			}
			if(max == 6){
				for(MonitorTargetEntity e : mtlist){
					ReportResult rr = map.get(e.getDateNo());
					if(rr == null){
						rr = new ReportResult();
						rr.setTargetValue(CompanyEnum.SalesAmountTarget, e.getSalesAmount());
						rr.setTargetValue(CompanyEnum.SalesReceiptAmountTarget, e.getReceiptAmount());
						rr.setTargetValue(datetarget, e.getDateNo());
						list.add(rr);
					}
					else{
						rr.setTargetValue(CompanyEnum.SalesAmountTarget, e.getSalesAmount());
						rr.setTargetValue(CompanyEnum.SalesReceiptAmountTarget, e.getReceiptAmount());
					}
				}
			}
			else if(max == 1){
				if(mtlist.size() == 0){
					return;
				}
				MonitorTargetEntity mt = mtlist.get(0);
				for(int i = 1; i <= ReportDateUtils.getDateNo(new Date()) % 100; i++){
					String str = "" + i;
					if(i < 10){
						str = "0" + str;
					}

					ReportResult rr = map.get(mt.getDateNo() + str);
					if(rr == null){
						rr = new ReportResult();
						rr.setTargetValue(CompanyEnum.SalesAmountTarget, mt.getSalesAmount());
						rr.setTargetValue(CompanyEnum.SalesReceiptAmountTarget, mt.getReceiptAmount());
						rr.setTargetValue(datetarget, Integer.parseInt(mt.getDateNo() + str));
						list.add(rr);
					}
					else{
						rr.setTargetValue(CompanyEnum.SalesAmountTarget, mt.getSalesAmount());
						rr.setTargetValue(CompanyEnum.SalesReceiptAmountTarget, mt.getReceiptAmount());
					}
				}
			}
		}
		if(targets.indexOf(EmployeeEnum.OfficialCustomersCount) >= 0
		        || targets.indexOf(EmployeeEnum.PotentialCustomersCount) >= 0)
		{

		}
		if(targets.indexOf(EmployeeEnum.CustomersIncrementCount) >= 0){
			Map<GUID, Double> map = getNewPartnerMap(context, key, tenant);
			for(String keys : rrmap.keySet()){
				ReportResult rr = rrmap.get(keys);
				GUID id = rr.getTargetValue(GUID.class, EmployeeEnum.Id);
				Double value = map.get(id);
				if(null == value){
					value = 0d;
				}
				rr.setTargetValue(EmployeeEnum.CustomersIncrementCount, value);
				rrmap.put(keys, rr);
			}
		}
		Enum target = null;
		if(CheckIsNull.isNotEmpty(key.getOrderTarget())){
			if(key.isDateColumn()){
				target = DateTimeEnum.getTarget(key.getOrderTarget());
			}
			else{
				target = EmployeeEnum.getTarget(key.getOrderTarget());
			}
		}
		for(ReportResult rr : rrmap.values()){
			rr.setOrderObj(target);
			list.add(rr);
		}
		ComparatorUtils.sort(list, "orderObj", key.isOrderDesc());

	}

	private static Map<GUID, Double> getNewPartnerMap(Context context, ReportCommonKey key, Tenant tenant){
		Map<GUID, Double> map = new HashMap<GUID, Double>();

		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_CUSTOMER_COUNT", "t");

		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");

		qb.addColumn("t.employeeId", "id");
		qb.addColumn("count(1)", "todayIncrement");
		qb.addGroupBy("t.employeeId");

		for(Condition con : key.getConditions()){
			DateTimeEnum tar = DateTimeEnum.getTarget(con.getConditionColumn());
			if(tar != null){
				qb.addArgs("dateNo", qb.INT, con.getValue());
				qb.addEquals("t." + tar.getColumnName(), "@dateNo");
			}
		}
		RecordSet rs = qb.getRecord();
		while(rs.next()){
			map.put(rs.getFields().get(0).getGUID(), rs.getFields().get(1).getDouble());
		}
		return map;
	}

	/**
	 * 得到本部门应收总额
	 * 
	 * @param deptId
	 * @return
	 */
	private static Double getTatolReceiptingAmount(Context context, GUID empId){
		double amount = 0;
		List<Partner> plist = context.getList(Partner.class, new GetCustomerListByEmployeeIdKey(empId));
		for(Partner p : plist){
			BalanceAmount ba = context.find(BalanceAmount.class, new GetBalanceAmountByPartnerKey(p.getId()));
			amount += ba.getDueAmount();
		}
		return amount;
	}

	/**
	 * 员工销售业绩的查询
	 * 
	 * @param context
	 * @param key
	 * @param tenant
	 * @param targets
	 * @param rrmap
	 */
	@SuppressWarnings("unchecked")
	private static void employeeDataProvide2(Context context, ReportCommonKey key, Login login, List<Enum> targets,
	        Map<String, ReportResult> rrmap)
	{

		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_SALES_DATE", "t");

		qb.addArgs("comNo", qb.guid, login.getTenantId());
		qb.addEquals("t.tenantId", "@comNo");

		qb.addColumn("t.orderPerson", "orderPerson");
		List<GUID> glist = new ArrayList<GUID>();
		if(key.getConditions() == null || key.getConditions().isEmpty()
		        || !"EmployeeId".toUpperCase().equals(key.getConditions().get(0).getConditionColumn().toUpperCase()))
		{
			List<Employee> bosses = context.getList(Employee.class, new GetEmployeeListByAuthKey(Auth.Boss));
			for(int index = 0; index < bosses.size(); index++){
				Employee emp = bosses.get(index);
				qb.addArgs("person" + index, qb.guid, emp.getId());
				qb.addNotEquals("t.orderPerson", "@person" + index);
				glist.add(emp.getId());
			}
		}
		qb.addGroupBy("t.orderPerson");
		if(targets.indexOf(EmployeeEnum.DayNo) >= 0){
			qb.addColumn("t.dateNo", "dateNo");
			qb.addGroupBy("t.dateNo");
		}
		if(targets.indexOf(EmployeeEnum.MonthNo) >= 0){
			qb.addColumn("t.monthNo", "monthNo");
			qb.addGroupBy("t.monthNo");
		}
		if(targets.indexOf(EmployeeEnum.Quarter) >= 0){
			qb.addColumn("t.quarter", "quarter");
			qb.addGroupBy("t.quarter");
		}
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.receiptAmount)", "receiptAmount");
		qb.addColumn("sum(t.rtnAmount)", "rtnAmount");
		Map<GUID, ReportResult> ymap = null;
		if(targets.indexOf(EmployeeEnum.TotalSalesAmount_OfYear) >= 0
		        || targets.indexOf(EmployeeEnum.TotalSalesReturnAmount_OfYear) >= 0)
		{
			ymap = getYearDoubleMap(context, key, login);
		}
		if(null != key.getConditions())
		    for(Condition con : key.getConditions()){
			    if("month".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
				    qb.addArgs("month", qb.INT, con.getValue());
				    qb.addEquals("t.monthNo", "@month");
			    }
			    else if("season".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
				    qb.addArgs("season", qb.INT, con.getValue());
				    qb.addEquals("t.quarter", "@season");
			    } 
			    else if("EmployeeId".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
				    ConditionUtil.fillConditionToSql(qb, con, "t.orderPerson");
			    }
			    else if("ThisMonth".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
				    qb.addArgs("month", qb.INT, ReportDateUtils.getMonthNo(new Date()));
				    qb.addEquals("t.monthNo", "@month");
			    }
		    }
		String orderStr = null;
		if(CheckIsNull.isNotEmpty(key.getOrderTarget())){
			if(key.isDateColumn()){
				DateTimeEnum datetarget = DateTimeEnum.getTarget(key.getOrderTarget());
				orderStr = datetarget.getColumnName();
				qb.addColumn("t." + orderStr, orderStr);
				qb.addGroupBy("t." + orderStr);
			}
			else if(EmployeeEnum.TotalSalesAmount == EmployeeEnum.getTarget(key.getOrderTarget())){
				orderStr = "ordAmount";
			}
			else if(EmployeeEnum.TotalSalesReturnAmount == EmployeeEnum.getTarget(key.getOrderTarget())){
				orderStr = "rtnAmount";
			}
			else if(EmployeeEnum.TotalReceiptedAmount == EmployeeEnum.getTarget(key.getOrderTarget())){
				orderStr = "deductionAmount";
			}
			if(key.isOrderDesc()){
				orderStr += " desc ";
			}
		}
		Condition depCon = ReportUtils.findCondition("DepartmentId", key.getConditions());
		if(AuthUtils.isBoss(context, login.getEmployeeId()) && null == depCon){
		}
		else if(AuthUtils.isManager(context, login.getEmployeeId()) || null != depCon){
			GUID deptId = null;
			if(null == depCon){
				Employee emp = context.find(Employee.class, login.getEmployeeId());
				deptId = emp.getDepartmentId();
			}
			else{
				deptId = (GUID)depCon.getValue();
			}
			Department dep = context.find(Department.class, deptId);
			List<String> argslist = new ArrayList<String>();
			int i = 0;
			for(Department d : dep.getDescendants(context)){
				argslist.add("@" + "deptId" + i);
				qb.addArgs("deptId" + i, qb.guid, d.getId());
				i++;
			}
			qb.addIn("t.deptGuid", argslist);
		}
		if(CheckIsNull.isNotEmpty(orderStr)){
			qb.addOrderBy("t." + orderStr);
		}
		RecordSet rs = null;
		if(key.getMaxCount() == 0){
			rs = qb.getRecord();
		}
		else{
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}

		while(rs.next()){
			String keystr = "key";
			ReportResult rr = new ReportResult();
			int index = 0;
			GUID empId = rs.getFields().get(index++).getGUID();
			if(null == empId){
				continue;
			}
			glist.add(empId);
			keystr += empId;
			Employee emp = context.find(Employee.class, empId);
			rr.setTargetValue(EmployeeEnum.Name, emp.getName());
			rr.setTargetValue(EmployeeEnum.Id, empId);
			Department dep = context.find(Department.class, emp.getDepartmentId());
			rr.setTargetValue(EmployeeEnum.DepartmentName, dep.getName());
			if(targets.indexOf(EmployeeEnum.DayNo) >= 0){
				Integer value = rs.getFields().get(index++).getInt();
				keystr += value;
				rr.setTargetValue(EmployeeEnum.DayNo, value);
			}
			if(targets.indexOf(EmployeeEnum.MonthNo) >= 0){
				Integer value = rs.getFields().get(index++).getInt();
				keystr += value;
				rr.setTargetValue(EmployeeEnum.MonthNo, value);
			}
			if(targets.indexOf(EmployeeEnum.Quarter) >= 0){
				Integer value = rs.getFields().get(index++).getInt();
				keystr += value;
				rr.setTargetValue(EmployeeEnum.Quarter, value);
			}
			ReportResult rr1 = rrmap.get(keystr);
			if(null != rr1){
				rr = rr1;
			}
			rr.setTargetValue(EmployeeEnum.TotalSalesAmount, rs.getFields().get(index++).getDouble());
			rr.setTargetValue(EmployeeEnum.TotalReceiptedAmount, rs.getFields().get(index++).getDouble());
			rr.setTargetValue(EmployeeEnum.TotalSalesReturnAmount, rs.getFields().get(index++).getDouble());
			if(targets.indexOf(EmployeeEnum.TotalSalesAmount_OfYear) >= 0
			        || targets.indexOf(EmployeeEnum.TotalSalesReturnAmount_OfYear) >= 0)
			{
				ReportResult tt = ymap.get(empId);
				if(tt != null){
					rr.setTargetValue(EmployeeEnum.TotalSalesAmount_OfYear, tt
					        .getTargetValue(EmployeeEnum.TotalSalesAmount_OfYear));
					rr.setTargetValue(EmployeeEnum.TotalSalesReturnAmount_OfYear, tt
					        .getTargetValue(EmployeeEnum.TotalSalesReturnAmount_OfYear));
				}
			}
			if(CheckIsNull.isNotEmpty(key.getOrderTarget()) && key.isDateColumn()){
				rr.setTargetValue(DateTimeEnum.getTarget(key.getOrderTarget()), rs.getFields().get(index++).getInt());
			}
			rrmap.put(keystr, rr);
		}
		Employee emp = context.find(Employee.class, login.getEmployeeId());
		List<Employee> emps =
		        context.getList(Employee.class, new GetChildrenDeptEmployeeListByAuthKey(emp.getDepartmentId(),
		                Auth.Sales, Auth.SalesManager));
		for(Employee e : emps){
			if(glist.contains(e.getId())){
				continue;
			}
			ReportResult rr = new ReportResult();
			String keystr = "key";
			keystr += e.getId();
			rr.setTargetValue(EmployeeEnum.Name, e.getName());
			rr.setTargetValue(EmployeeEnum.Id, e.getId());
			Department dep = context.find(Department.class, e.getDepartmentId());
			rr.setTargetValue(EmployeeEnum.DepartmentName, dep.getName());
			rr.setTargetValue(EmployeeEnum.TotalSalesAmount, 0);
			rr.setTargetValue(EmployeeEnum.TotalReceiptedAmount, 0);
			rr.setTargetValue(EmployeeEnum.TotalSalesReturnAmount, 0);
			if(ymap != null && ymap.get(e.getId()) != null){
				ReportResult tt = ymap.get(e.getId());
				rr.setTargetValue(EmployeeEnum.TotalSalesAmount_OfYear, tt
				        .getTargetValue(EmployeeEnum.TotalSalesAmount_OfYear));
				rr.setTargetValue(EmployeeEnum.TotalSalesReturnAmount_OfYear, tt
				        .getTargetValue(EmployeeEnum.TotalSalesAmount_OfYear));
			}
			if(0 == key.getMaxCount() || rrmap.keySet().size() < key.getMaxCount()){
				rrmap.put(keystr, rr);
			}
			else{
				break;
			}
		}
	}

	/**
	 * @param context
	 * @param key
	 * @param login
	 * @return
	 */
	private static Map<GUID, ReportResult> getYearDoubleMap(Context context, ReportCommonKey key, Login login){
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_SALES_DATE", "t");
		qb.addArgs("comNo", qb.guid, login.getTenantId());
		qb.addEquals("t.tenantId", "@comNo");
		String dateKey = null, dateValue = null;
		int year = 0;
		for(Condition con : key.getConditions()){
			if("month".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
				qb.addArgs("month", qb.INT, con.getValue());
				year = Integer.parseInt((con.getValue() + "").substring(0, 4));
				dateKey = "t.monthNo";
				dateValue = "@month";
			}
			else if("season".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
				qb.addArgs("season", qb.INT, con.getValue());
				year = Integer.parseInt((con.getValue() + "").substring(0, 4));
				dateKey = "t.quarter";
				dateValue = "@season";
			}
		}
		qb.addLessThanOrEquals(dateKey, dateValue);
		qb.addArgs("year", qb.INT, year);
		qb.addEquals("t.yearNo", "@year");

		qb.addColumn("t.orderPerson", "id");
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.rtnAmount)", "rtnAmount");
		qb.addGroupBy("t.orderPerson");

		if(AuthUtils.isBoss(context, login.getEmployeeId())){
		}
		else{
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			Department dep = context.find(Department.class, emp.getDepartmentId());
			List<String> argslist = new ArrayList<String>();
			int i = 0;
			for(Department d : dep.getDescendants(context)){
				argslist.add("@" + "deptId" + i);
				qb.addArgs("deptId" + i, qb.guid, d.getId());
				i++;
			}
			qb.addIn("t.deptGuid", argslist);
		}
		RecordSet rs = qb.getRecord();
		Map<GUID, ReportResult> map = new HashMap<GUID, ReportResult>();
		while(rs.next()){
			GUID id = rs.getFields().get(0).getGUID();
			ReportResult rr = new ReportResult();
			rr.setTargetValue(EmployeeEnum.TotalSalesAmount_OfYear, rs.getFields().get(1).getDouble());
			rr.setTargetValue(EmployeeEnum.TotalSalesReturnAmount_OfYear, rs.getFields().get(2).getDouble());
			map.put(id, rr);
		}
		return map;
	}
}
