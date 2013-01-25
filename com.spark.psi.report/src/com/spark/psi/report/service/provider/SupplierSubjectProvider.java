/**
 * 
 */
package com.spark.psi.report.service.provider;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.ComparatorUtils;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.BalanceAmount;
import com.spark.psi.base.Partner;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.key.GetBalanceAmountByPartnerKey;
import com.spark.psi.publish.RetailPartner;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.report.constant.ConditionEnum.SupplierConditionEnum;
import com.spark.psi.report.constant.TargetEnum.DateTimeEnum;
import com.spark.psi.report.constant.TargetEnum.SupplierEnum;
import com.spark.psi.report.entity.ReportResult;
import com.spark.psi.report.key.ReportCommonKey;
import com.spark.psi.report.utils.Condition;
import com.spark.psi.report.utils.ConditionUtil;
import com.spark.psi.report.utils.QuerySqlBuilder;

/**
 *
 */
public abstract class SupplierSubjectProvider{
	/**
	 * 查询供应商数据
	 */
	@SuppressWarnings( {"unchecked"})
	protected static void provide(Context context, ReportCommonKey key, List<ReportResult> list){
		Tenant tenant = context.find(Tenant.class);
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_PURCHASE_DATE", "t");

		qb.addArgs("emptyID", qb.guid, GUID.emptyID);
		qb.addNotEquals("t.cusProGuid", "@emptyID");
		
		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");
		List<Enum> targets = key.getTargets();
		Map<GUID, ReportResult> ymap = null;
		if(targets.indexOf(SupplierEnum.PurchaseAmount_OfYear) >= 0
		        || targets.indexOf(SupplierEnum.TotalPaidAmountOfYear) >= 0
		        || targets.indexOf(SupplierEnum.PayAmount_OfYear) >= 0
		        || targets.indexOf(SupplierEnum.PurchaseReturnAmount_OfYear) >= 0)
		{
			ymap = getYearDoubleMap(context, key, tenant);
		}

		if(targets.indexOf(SupplierEnum.NotOrderDays) >= 0){
			StringBuilder ss = new StringBuilder();
			ss.append("(");
			ss.append("select max(t1.createDate) as createDate1 ");
			ss.append(" ,t1.cusProGuid as cusProGuid1");
			ss.append(" from SA_REPORT_PURCHASE_DATE as t1 where t1.tenantId=@comNo ");
			ss.append("GROUP BY t1.CUSPROGUID");
			ss.append(")");
			qb.addTable(ss.toString(), "m");
		}

		qb.addColumn("t.cusProGuid", "cusProGuid");

		PartnerInfo pi = context.find(PartnerInfo.class, RetailPartner.Supplier);
		qb.addArgs("retailId", qb.guid, pi.getId());
		qb.addNotEquals("t.cusProGuid", "@retailId");

		if(targets.indexOf(SupplierEnum.PurchaseAmount) >= 0){
			qb.addColumn("sum(t.ordAmount)", "ordAmounts");
		}
		if(targets.indexOf(SupplierEnum.InstoAmount) >= 0){
			qb.addColumn("sum(t.outstoAmount)", "outstoAmounts");
		}
		if(targets.indexOf(SupplierEnum.PayAmount) >= 0){
			qb.addColumn("sum(t.receiptAmount)", "deductionAmounts");
		}
		if(targets.indexOf(SupplierEnum.PurchaseReturnAmount) >= 0){
			qb.addColumn("sum(t.rtnAmount)", "rtnAmounts");
		}

		if(targets.indexOf(SupplierEnum.NotOrderDays) >= 0){
			qb.addColumn("m.createDate1", "createDate1");
			qb.addEquals("m.cusProGuid1", "t.cusProGuid");
		}
		if(CheckIsNull.isNotEmpty(key.getConditions())
		        && key.getConditions().indexOf(SupplierConditionEnum.SupplierId) >= 0)
		{
			ConditionUtil.fillConditionToSql(qb, key.getConditions().get(
			        key.getConditions().indexOf(SupplierConditionEnum.SupplierId)), "t.cusProGuid");
		}
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
		qb.addGroupBy("t.cusProGuid ");
		if(CheckIsNull.isNotEmpty(key.getOrderTarget())
		        && !SupplierEnum.DuePayingAmount.toString().toUpperCase().equals(key.getOrderTarget().toUpperCase()))
		{
			String orderStr = null;
			if(key.isDateColumn()){
				orderStr = DateTimeEnum.getTarget(key.getOrderTarget()).getColumnName();
				if(key.isOrderDesc()){
					orderStr += " desc ";
				}
				qb.addOrderBy("t." + orderStr);
			}
			else{
				orderStr = SupplierEnum.getTarget(key.getOrderTarget()).getfield() + "s";
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
			rr.setTargetValue(SupplierEnum.Id, cusId);
			Partner cus = context.find(Partner.class, cusId);
			rr.setTargetValue(SupplierEnum.ShortName, cus.getShortName());
			rr.setTargetValue(SupplierEnum.Name, cus.getName());
			if(targets.indexOf(SupplierEnum.PurchaseAmount) >= 0){
				rr.setTargetValue(SupplierEnum.PurchaseAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(SupplierEnum.InstoAmount) >= 0){
				rr.setTargetValue(SupplierEnum.InstoAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(SupplierEnum.PayAmount) >= 0){
				rr.setTargetValue(SupplierEnum.PayAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(SupplierEnum.PurchaseReturnAmount) >= 0){
				rr.setTargetValue(SupplierEnum.PurchaseReturnAmount, rs.getFields().get(index++).getObject());
			}
			if(targets.indexOf(SupplierEnum.TotalPaidAmountOfYear) >= 0
			        || targets.indexOf(SupplierEnum.PurchaseAmount_OfYear) >= 0
			        || targets.indexOf(SupplierEnum.PayAmount_OfYear) >= 0
			        || targets.indexOf(SupplierEnum.PurchaseReturnAmount_OfYear) >= 0)
			{
				ReportResult tt = ymap.get(cusId);
				if(tt != null){
					rr.setTargetValue(SupplierEnum.TotalPaidAmountOfYear, tt
					        .getTargetValue(SupplierEnum.TotalPaidAmountOfYear));
					rr.setTargetValue(SupplierEnum.PayAmount_OfYear, tt.getTargetValue(SupplierEnum.PayAmount_OfYear));

					rr.setTargetValue(SupplierEnum.PurchaseAmount_OfYear, tt
					        .getTargetValue(SupplierEnum.PurchaseAmount_OfYear));
					rr.setTargetValue(SupplierEnum.PurchaseReturnAmount_OfYear, tt
					        .getTargetValue(SupplierEnum.PurchaseReturnAmount_OfYear));
				}
			}
			if(targets.indexOf(SupplierEnum.NotOrderDays) >= 0){
				long obj = rs.getFields().get(index++).getDate();
				Date d = new Date(obj);
				Date today = new Date();
				long value = DateUtil.getDaysBetween(d, today);
				if(value < 0){
					value = 0;
				}
				rr.setTargetValue(SupplierEnum.NotOrderDays, value);
			}
			if(targets.indexOf(SupplierEnum.DuePayingAmount) >= 0){
				BalanceAmount ba = context.find(BalanceAmount.class, new GetBalanceAmountByPartnerKey(cusId));
				if(null!=ba){
					rr.setTargetValue(SupplierEnum.DuePayingAmount, ba.getDueAmount());
				}else{
					rr.setTargetValue(SupplierEnum.DuePayingAmount,0);
				}
			}
			if(targets.indexOf(SupplierEnum.CreditAmount) >= 0){
				rr.setTargetValue(SupplierEnum.CreditAmount, cus.getCreditAmount());
			}
			if(targets.indexOf(SupplierEnum.CreditDay) >= 0){
				rr.setTargetValue(SupplierEnum.CreditDay, cus.getAccountPeriod());
			}
			list.add(rr);
		}
		if(SupplierEnum.DuePayingAmount.toString().toUpperCase().equals(key.getOrderTarget().toUpperCase())){
			for(ReportResult rr : list){
				rr.setOrderObj(SupplierEnum.DuePayingAmount);
			}
			ComparatorUtils.sort(list, "orderObj", key.isOrderDesc());
		}
	}

	/**
	 * @param context
	 * @param key
	 * @return
	 */
	private static Map<GUID, ReportResult> getYearDoubleMap(Context context, ReportCommonKey key, Tenant tenant){
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_PURCHASE_DATE", "t");
		qb.addArgs("comNo", qb.guid, tenant.getId());
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
			rr.setTargetValue(SupplierEnum.PurchaseAmount_OfYear, rs.getFields().get(1).getDouble());
			Double value = rs.getFields().get(2).getDouble();
			rr.setTargetValue(SupplierEnum.TotalPaidAmountOfYear, value);
			rr.setTargetValue(SupplierEnum.PayAmount_OfYear, value);
			rr.setTargetValue(SupplierEnum.PurchaseReturnAmount_OfYear, rs.getFields().get(3).getDouble());
			map.put(id, rr);
		}
		return map;
	}
}
