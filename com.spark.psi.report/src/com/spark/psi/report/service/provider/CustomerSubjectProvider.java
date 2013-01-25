/**
 * 
 */
package com.spark.psi.report.service.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.report.constant.ConditionEnum.CustomerConditionEnum;
import com.spark.psi.report.constant.TargetEnum.CustomerEnum;
import com.spark.psi.report.constant.TargetEnum.DateTimeEnum;
import com.spark.psi.report.entity.ReportResult;
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
public abstract class CustomerSubjectProvider{

	/**
	 * 查询客户数据
	 */
	@SuppressWarnings( {"unchecked", "deprecation"})
	protected static void provide(Context context, ReportCommonKey key, List<ReportResult> list){
		Tenant tenant = context.find(Tenant.class);
		Login login = context.find(Login.class);
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_SALES_DATE", "t");

		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");
		List<Enum> targets = key.getTargets();

		qb.addColumn("t.cusProGuid", "cusProGuid");

		PartnerInfo pi = context.find(PartnerInfo.class, RetailPartner.Customer);
		qb.addArgs("retailId", qb.guid, pi.getId());
		qb.addNotEquals("t.cusProGuid", "@retailId");

		if(targets.indexOf(CustomerEnum.SalesAmount) >= 0){
			qb.addColumn("sum(t.ordAmount)", "ordAmounts");
		}
		if(targets.indexOf(CustomerEnum.OutstoAmount) >= 0){
			qb.addColumn("sum(t.outstoAmount)", "outstoAmounts");
		}
		if(targets.indexOf(CustomerEnum.ReceiptAmount) >= 0){
			qb.addColumn("sum(t.receiptAmount)", "receiptAmounts");
		}
		if(targets.indexOf(CustomerEnum.SalesReturnAmount) >= 0){
			if(targets.indexOf(CustomerEnum.SalesAmount) < 0){
				qb.addArgs("zero", qb.INT, 0);
				qb.addGreaterThan("t.rtnAmount", "@zero");
			}
			qb.addColumn("sum(t.rtnAmount)", "rtnAmounts");
		}
		if(targets.indexOf(CustomerEnum.TotalReceiptedAmountOfYear) >= 0){
			String value = (new Date().getYear() + 1899) + "";
			qb.addArgs("year", qb.INT, value);
			qb
			        .addColumn(
			                "select sum(s.receiptAmount) from SA_REPORT_SALES_DATE as s where s.cusProGuid=t.cusProGuid and s.tenantId=t.tenantId and yearNo=@year",
			                "totalReceiptedAmountOfYear");
		}
		if(targets.indexOf(CustomerEnum.NotOrderDays) >= 0){
			qb
			        .addColumn(
			                "select max(m.createDate) from SA_REPORT_SALES_DATE as m where m.cusProGuid=t.cusProGuid and m.tenantId=t.tenantId ",
			                "totalReceiptedAmountOfYear");
		}
		if(CheckIsNull.isNotEmpty(key.getConditions())){
			for(Condition con : key.getConditions()){
				if(CustomerConditionEnum.CustomerId.equals(con.getConditionColumn())){
					ConditionUtil.fillConditionToSql(qb, con, "t.cusProGuid");
				}
				else if(CustomerConditionEnum.OrderPerson.equals(con.getConditionColumn())){
					ConditionUtil.fillConditionToSql(qb, con, "t.orderPerson");
				}
				else if("ThisMonth".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
					qb.addArgs("month", qb.INT, ReportDateUtils.getMonthNo(new Date()));
					qb.addEquals("t.monthNo", "@month");
				}
				else if("month".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
					qb.addArgs("month1", qb.INT, con.getValue());
					qb.addEquals("t.monthNo", "@month1");
				}
				else if("season".toUpperCase().equals(con.getConditionColumn().toUpperCase())){
					qb.addArgs("season", qb.INT, con.getValue());
					qb.addEquals("t.quarter", "@season");
				}
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
		if(0 < key.getEndDate()){
			qb.addArgs("betBeginDate", qb.DATE, key.getBeginDate());
			qb.addArgs("betEndDate", qb.DATE, key.getEndDate());
			qb.addBetween("t.createDate", "@betBeginDate", "@betEndDate");
		}
		qb.addGroupBy("t.cusProGuid ");
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
				orderStr = CustomerEnum.getTarget(key.getOrderTarget()).getfield() + "s";
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
			if(null == cus){
				continue;
			}
			rr.setTargetValue(CustomerEnum.Name, cus.getName());
			rr.setTargetValue(CustomerEnum.Id, cusId);
			rr.setTargetValue(CustomerEnum.ShortName, cus.getShortName());
			if(targets.indexOf(CustomerEnum.SalesAmount) >= 0){
				rr.setTargetValue(CustomerEnum.SalesAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(CustomerEnum.OutstoAmount) >= 0){
				rr.setTargetValue(CustomerEnum.OutstoAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(CustomerEnum.ReceiptAmount) >= 0){
				rr.setTargetValue(CustomerEnum.ReceiptAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(CustomerEnum.SalesReturnAmount) >= 0){
				rr.setTargetValue(CustomerEnum.SalesReturnAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(CustomerEnum.TotalReceiptedAmountOfYear) >= 0){
				rr.setTargetValue(CustomerEnum.TotalReceiptedAmountOfYear, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(CustomerEnum.NotOrderDays) >= 0){
				Object obj = rs.getFields().get(index++).getObject();
				if(null != obj){
					Date d = new Date(Long.parseLong(obj + ""));
					Date today = new Date();
					rr.setTargetValue(CustomerEnum.NotOrderDays, DateUtil.getDaysBetween(d, today));
				}
			}
			if(targets.indexOf(CustomerEnum.DueReceiptingAmount) >= 0){
				BalanceAmount ba = context.find(BalanceAmount.class, new GetBalanceAmountByPartnerKey(cusId));
				rr.setTargetValue(CustomerEnum.DueReceiptingAmount, ba.getDueAmount());
			}
			list.add(rr);
		}
	}
}
