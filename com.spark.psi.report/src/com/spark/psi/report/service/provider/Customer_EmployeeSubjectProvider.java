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
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.BalanceAmount;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.key.GetBalanceAmountByPartnerKey;
import com.spark.psi.publish.RetailPartner;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.report.constant.ConditionEnum.CustomerConditionEnum;
import com.spark.psi.report.constant.TargetEnum.Customer_EmployeeEnum;
import com.spark.psi.report.constant.TargetEnum.DateTimeEnum;
import com.spark.psi.report.entity.ReportResult;
import com.spark.psi.report.key.ReportCommonKey;
import com.spark.psi.report.utils.AuthUtils;
import com.spark.psi.report.utils.Condition;
import com.spark.psi.report.utils.ConditionUtil;
import com.spark.psi.report.utils.QuerySqlBuilder;
import com.spark.psi.report.utils.ReportUtils;

/**
 *
 */
public abstract class Customer_EmployeeSubjectProvider{

	/**
	 * 查询客户并销售人员的数据
	 */
	@SuppressWarnings( {"unchecked"})
	protected static void provide(Context context, ReportCommonKey key, List<ReportResult> list){
		Tenant tenant = context.find(Tenant.class);
		Login login = context.find(Login.class);
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_SALES_DATE", "t");
		qb.addArgs("emptyID", qb.guid, GUID.emptyID);
		qb.addNotEquals("t.cusProGuid", "@emptyID");
		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");
		List<Enum> targets = key.getTargets();
		Map<GUID, ReportResult> ymap = null;
		if(targets.indexOf(Customer_EmployeeEnum.SalesAmount_OfYear) >= 0
		        || targets.indexOf(Customer_EmployeeEnum.SalesReceiptAmount_OfYear) >= 0
		        || targets.indexOf(Customer_EmployeeEnum.SalesReturnAmount_OfYear) >= 0)
		{
			ymap = getYearDoubleMap(context, key, login);
		}

		if(targets.indexOf(Customer_EmployeeEnum.NotOrderDays) >= 0){
			StringBuilder ss = new StringBuilder();
			ss.append("(");
			ss.append("select max(t1.createDate) as createDate1 ");
			ss.append(" ,t1.cusProGuid as cusProGuid1 ");
			ss.append(" from SA_REPORT_SALES_DATE as t1 where t1.tenantId=@comNo ");
			ss.append("GROUP BY t1.CUSPROGUID ");
			ss.append(")");
			qb.addTable(ss.toString(), "m");
		}
		qb.addColumn("t.cusProGuid", "cusProGuid");

		PartnerInfo pi = context.find(PartnerInfo.class, RetailPartner.Customer);
		qb.addArgs("retailId", qb.guid, pi.getId());
		qb.addNotEquals("t.cusProGuid", "@retailId");

		if(targets.indexOf(Customer_EmployeeEnum.SalesAmount) >= 0){
			qb.addColumn("sum(t.ordAmount)", "ordAmounts");
		}
		if(targets.indexOf(Customer_EmployeeEnum.OutstoAmount) >= 0){
			qb.addColumn("sum(t.outstoAmount)", "outstoAmounts");
		}
		if(targets.indexOf(Customer_EmployeeEnum.SalesReceiptAmount) >= 0){
			qb.addColumn("sum(t.receiptAmount)", "deductionAmounts");
		}
		if(targets.indexOf(Customer_EmployeeEnum.SalesReturnAmount) >= 0){
			if(targets.indexOf(Customer_EmployeeEnum.SalesAmount) < 0){
				qb.addArgs("zero", qb.INT, 0);
				qb.addGreaterThan("t.rtnAmount", "@zero");
			}
			qb.addColumn("sum(t.rtnAmount)", "rtnAmounts");

		}
		if(targets.indexOf(Customer_EmployeeEnum.NotOrderDays) >= 0){
			qb.addColumn("m.createDate1", "createDate1");
			qb.addEquals("m.cusProGuid1", "t.cusProGuid");
		}
		if(CheckIsNull.isNotEmpty(key.getConditions())){
			for(Condition con : key.getConditions()){
				if(CustomerConditionEnum.CustomerId.equals(con.getConditionColumn())){
					ConditionUtil.fillConditionToSql(qb, con, "t.cusProGuid");
				}
				else if(CustomerConditionEnum.OrderPerson.equals(con.getConditionColumn())){
					ConditionUtil.fillConditionToSql(qb, con, "t.orderPerson");
				}
			}
		}
		qb.addCondition("t.orderPerson is not null ");
		for(Condition con : key.getConditions()){
			if("month".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
				qb.addArgs("month", qb.INT, con.getValue());
				qb.addEquals("t.monthNo", "@month");
			}
			else if("season".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
				qb.addArgs("season", qb.INT, con.getValue());
				qb.addEquals("t.quarter", "@season");
			}
			else{

			}
		}
		Condition depCon = ReportUtils.findCondition("DepartmentId", key.getConditions());
		if(AuthUtils.isBoss(context, login.getEmployeeId()) && null == depCon){
		}
		else if(AuthUtils.isManager(context, login.getEmployeeId()) || null != depCon){
			// 经理权限
			GUID deptId = null;
			if(null == depCon){
				Employee emp = context.find(Employee.class, login.getEmployeeId());
				deptId = emp.getDepartmentId();
			}
			else{
				deptId = (GUID)depCon.getValue();
			}
			List<GUID> dlist = ReportUtils.getChildrenDeptList(context, deptId);
			List<String> args = new ArrayList<String>();
			int i = 0;
			for(GUID id : dlist){
				qb.addArgs("dept" + i, qb.guid, id);
				args.add("@dept" + i++);
			}
			qb.addIn("t.deptGuid", args);
		}
		else if(AuthUtils.isSales(context, login.getEmployeeId())){
			// 员工权限
			qb.addArgs("userId", qb.guid, login.getEmployeeId());
			qb.addEquals("t.orderPerson", "@userId");
		}
		qb.addGroupBy("t.cusProGuid  ");
		if(CheckIsNull.isNotEmpty(key.getOrderTarget())){
			String orderStr = null;
			if(key.isDateColumn()){
				orderStr = DateTimeEnum.getTarget(key.getOrderTarget()).getColumnName();
				if(key.isOrderDesc()){
					orderStr += " desc ";
				}
				qb.addOrderBy("t." + orderStr);
			}
			else{
				orderStr = Customer_EmployeeEnum.getTarget(key.getOrderTarget()).getfield() + "s";
				if(key.isOrderDesc()){
					orderStr += " desc ";
				}
				qb.addOrderBy(orderStr);
			}

		}
		RecordSet rs = null;
		if(key.getMaxCount() == 0){
			rs = qb.getRecord();
		}
		else{
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		while(rs.next()){
			ReportResult rr = new ReportResult();
			int index = 0;
			GUID cusId = rs.getFields().get(index++).getGUID();
			Partner cus = context.find(Partner.class, cusId);
			rr.setTargetValue(Customer_EmployeeEnum.CustemerName, cus.getName());
			rr.setTargetValue(Customer_EmployeeEnum.CustemerShortName, cus.getShortName());
			rr.setTargetValue(Customer_EmployeeEnum.Id, cusId);
			if(null != cus.getBusinessPerson()){
				EmployeeInfo emp = cus.getBusinessPerson();
				rr.setTargetValue(Customer_EmployeeEnum.SalesName, emp.getName());
			}
			else{
				rr.setTargetValue(Customer_EmployeeEnum.SalesName, "共享");
			}
			if(targets.indexOf(Customer_EmployeeEnum.SalesAmount) >= 0){
				rr.setTargetValue(Customer_EmployeeEnum.SalesAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(Customer_EmployeeEnum.OutstoAmount) >= 0){
				rr.setTargetValue(Customer_EmployeeEnum.OutstoAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(Customer_EmployeeEnum.SalesReceiptAmount) >= 0){
				rr.setTargetValue(Customer_EmployeeEnum.SalesReceiptAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(Customer_EmployeeEnum.SalesReturnAmount) >= 0){
				rr.setTargetValue(Customer_EmployeeEnum.SalesReturnAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(Customer_EmployeeEnum.SalesAmount_OfYear) >= 0
			        || targets.indexOf(Customer_EmployeeEnum.SalesReceiptAmount_OfYear) >= 0
			        || targets.indexOf(Customer_EmployeeEnum.SalesReturnAmount_OfYear) >= 0)
			{
				ReportResult tt = ymap.get(cusId);
				if(tt != null){
					rr.setTargetValue(Customer_EmployeeEnum.SalesAmount_OfYear, tt
					        .getTargetValue(Customer_EmployeeEnum.SalesAmount_OfYear));
					rr.setTargetValue(Customer_EmployeeEnum.SalesReceiptAmount_OfYear, tt
					        .getTargetValue(Customer_EmployeeEnum.SalesReceiptAmount_OfYear));
					rr.setTargetValue(Customer_EmployeeEnum.SalesReturnAmount_OfYear, tt
					        .getTargetValue(Customer_EmployeeEnum.SalesReturnAmount_OfYear));
				}
			}
			if(targets.indexOf(Customer_EmployeeEnum.NotOrderDays) >= 0){
				long obj = rs.getFields().get(index++).getDate();
				Date d = new Date(obj);
				Date today = new Date();
				long count = DateUtil.getDaysBetween(d, today);
				if(count < 0){
					count = 0;
				}
				rr.setTargetValue(Customer_EmployeeEnum.NotOrderDays, count);
			}
			if(targets.indexOf(Customer_EmployeeEnum.DueReceiptingAmount) >= 0){
				BalanceAmount ba = context.find(BalanceAmount.class, new GetBalanceAmountByPartnerKey(cusId));
				if(null != ba){
					rr.setTargetValue(Customer_EmployeeEnum.DueReceiptingAmount, ba.getDueAmount());
				}
				else{
					rr.setTargetValue(Customer_EmployeeEnum.DueReceiptingAmount, 0);
				}
			}
			list.add(rr);
		}
	}

	/**
	 * @param context
	 * @param key
	 * @param tenant
	 * @return
	 */
	private static Map<GUID, ReportResult> getYearDoubleMap(Context context, ReportCommonKey key, Login login){
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_SALES_DATE", "t");
		qb.addArgs("comNo", qb.guid, login.getEmployeeId());
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
		Condition depCon = ReportUtils.findCondition("DepartmentId", key.getConditions());
		if(AuthUtils.isBoss(context, login.getEmployeeId()) && null == depCon){
		}
		else if(AuthUtils.isManager(context, login.getEmployeeId()) || null != depCon){
			// 经理权限
			GUID deptId = null;
			if(null == depCon){
				Employee emp = context.find(Employee.class, login.getEmployeeId());
				deptId = emp.getDepartmentId();
			}
			else{
				deptId = (GUID)depCon.getValue();
			}
			List<GUID> dlist = ReportUtils.getChildrenDeptList(context, deptId);
			List<String> args = new ArrayList<String>();
			int i = 0;
			for(GUID id : dlist){
				qb.addArgs("dept" + i, qb.guid, id);
				args.add("@dept" + i++);
			}
			qb.addIn("t.deptGuid", args);
		}
		else if(AuthUtils.isSales(context, login.getEmployeeId())){
			// 员工权限
			qb.addArgs("userId", qb.guid, login.getEmployeeId());
			qb.addEquals("t.orderPerson", "@userId");
		}
		qb.addLessThanOrEquals(dateKey, dateValue);
		qb.addArgs("year", qb.INT, year);
		qb.addEquals("t.yearNo", "@year");

		qb.addColumn("t.cusProGuid", "id");
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.receiptAmount)", "receiptAmount");
		qb.addColumn("sum(t.rtnAmount)", "rtnAmount");
		qb.addGroupBy("t.cusProGuid");
		RecordSet rs = qb.getRecord();
		Map<GUID, ReportResult> map = new HashMap<GUID, ReportResult>();
		while(rs.next()){
			GUID id = rs.getFields().get(0).getGUID();
			ReportResult rr = new ReportResult();
			rr.setTargetValue(Customer_EmployeeEnum.SalesAmount_OfYear, rs.getFields().get(1).getDouble());
			Double value = rs.getFields().get(2).getDouble();
			rr.setTargetValue(Customer_EmployeeEnum.SalesReceiptAmount_OfYear, value);
			rr.setTargetValue(Customer_EmployeeEnum.SalesReturnAmount_OfYear, rs.getFields().get(3).getDouble());
			map.put(id, rr);
		}
		return map;
	}

}
