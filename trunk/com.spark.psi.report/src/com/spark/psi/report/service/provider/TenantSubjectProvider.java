/**
 * 
 */
package com.spark.psi.report.service.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.BalanceAmount;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.key.GetBalanceAmountByPartnerKey;
import com.spark.psi.base.key.GetCustomerListByDepartmentIdKey;
import com.spark.psi.base.key.GetCustomerListByEmployeeIdKey;
import com.spark.psi.base.key.OverPeriodAmountKey;
import com.spark.psi.inventory.intf.entity.inventory.GoodsInventorySum;
import com.spark.psi.inventory.intf.key.instorage.GetExceedTimeLimitNotDeliveredAmountKey;
import com.spark.psi.inventory.intf.key.inventory.GetExceptionInventoryKey;
import com.spark.psi.inventory.intf.key.inventory.GoodsInventorySumKey;
import com.spark.psi.publish.MonitorDataType;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.inventory.entity.InventoryInfo;
import com.spark.psi.publish.report.entity.SalesTargetItem;
import com.spark.psi.report.constant.SubjectEnum;
import com.spark.psi.report.constant.TargetEnum.CompanyEnum;
import com.spark.psi.report.constant.TargetEnum.DateTimeEnum;
import com.spark.psi.report.entity.MonitorTargetEntity;
import com.spark.psi.report.entity.MonitorTargetEntityTemp;
import com.spark.psi.report.entity.ReportResult;
import com.spark.psi.report.key.MonitorTargetKey;
import com.spark.psi.report.key.ReportCommonKey;
import com.spark.psi.report.utils.AuthUtils;
import com.spark.psi.report.utils.Condition;
import com.spark.psi.report.utils.QuerySqlBuilder;
import com.spark.psi.report.utils.ReportDateUtils;
import com.spark.psi.report.utils.ReportUtils;

/**
 *
 */
public abstract class TenantSubjectProvider{

	/**
	 * 租户报表数据查询
	 */
	@SuppressWarnings("unchecked")
	protected static void provide(Context context, ReportCommonKey key, List<ReportResult> list){
		Login login = context.find(Login.class);
		Tenant tenant = context.find(Tenant.class);
		List<Enum> targets = key.getTargets();
		Condition depCon = ReportUtils.findCondition("DepartmentId", key.getConditions());
		if(targets.indexOf(CompanyEnum.TotalSalesAmount) >= 0
		        || targets.indexOf(CompanyEnum.TotalSalesReceiptAmount) >= 0
		        || targets.indexOf(CompanyEnum.TotalSalesReturnAmount) >= 0
		        || targets.indexOf(CompanyEnum.TotalSalesCheckOutAmount) >= 0)
		{
			// 销售数据
			companyDataProvide_Sales(context, key, tenant, targets, list);
		}
		if(targets.indexOf(CompanyEnum.TotalPurchaseAmount) >= 0
		        || targets.indexOf(CompanyEnum.TotalPurchasePayAmount) >= 0
		        || targets.indexOf(CompanyEnum.TotalPurchaseCheckInAmount) >= 0)
		{
			// 采购数据
			companyDataProvide_Purchase(context, key, tenant, targets, list);
		}
		if(targets.indexOf(CompanyEnum.SuppliersCount) >= 0){
			// 当前供应商数量
			List<Partner> plist = context.getList(Partner.class, PartnerType.Supplier);
			if(list.isEmpty()){
				ReportResult rr = new ReportResult();
				rr.setTargetValue(CompanyEnum.SuppliersCount, plist.size());
				list.add(rr);
			}
			else{
				for(ReportResult rr : list){
					rr.setTargetValue(CompanyEnum.SuppliersCount, plist.size());
				}
			}
		}
		if(targets.indexOf(CompanyEnum.CustomersCount) >= 0){
			// 当前客户数量
			List<Partner> plist = null;
			if(null == depCon){
				plist = context.getList(Partner.class, new GetCustomerListByEmployeeIdKey(login.getEmployeeId()));
			}
			else{
				plist = context.getList(Partner.class, new GetCustomerListByDepartmentIdKey((GUID)depCon.getValue()));
			}
			if(list.isEmpty()){
				ReportResult rr = new ReportResult();
				rr.setTargetValue(CompanyEnum.CustomersCount, plist.size());
				list.add(rr);
			}
			else{
				for(ReportResult rr : list){
					rr.setTargetValue(CompanyEnum.CustomersCount, plist.size());
				}
			}
		}
		GUID objid = null;
		if(AuthUtils.isBoss(context, login.getEmployeeId())){
			objid = GUID.emptyID;
		}
		else if(AuthUtils.isBoss(context, login.getEmployeeId())){
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			objid = emp.getDepartmentId();
		}
		else{
			objid = login.getEmployeeId();
		}
		MonitorTargetEntityTemp temp = getTargetTemp(context, objid);

		if(targets.indexOf(CompanyEnum.SalesSuccessAmountOfThisMonth) >= 0
		        || targets.indexOf(CompanyEnum.ReceiptedAmountOfThisMonth) >= 0
		        || targets.indexOf(CompanyEnum.SalesReturnAmountOfThisMonth) >= 0
		        || targets.indexOf(CompanyEnum.ReceiptedSuccessAmountOfThisMonth) >= 0)
		{
			// 本月销售数据
			companyDataProvide_SalesThisMonth(context, key, login, targets, list, temp.getSalesTarget(), temp
			        .getReceiptTarget());
		}
		if(targets.indexOf(CompanyEnum.TotalPurchaseAmountOfThisMonth) >= 0
		        || targets.indexOf(CompanyEnum.PaidAmountOfThisMonth) >= 0)
		{
			// 本月采购数据
			companyDataProvide_PurchaseThisMonth(context, key, tenant, targets, list);
		}
		if(targets.indexOf(CompanyEnum.SalesSuccessAmountOfThisYear) >= 0
		        || targets.indexOf(CompanyEnum.ReceiptedAmountOfThisYear) >= 0
		        || targets.indexOf(CompanyEnum.ReceiptedSuccessAmountOfThisYear) >= 0)
		{
			// 本年销售数据
			companyDataProvide_SalesThisYear(context, key, tenant, targets, list, temp.getTotalSalesTarget(), temp
			        .getTotalReceiptTarget());
		}
		if(targets.indexOf(CompanyEnum.PaidAmountOfThisYear) >= 0){
			companyDataProvide_PurchaseThisYear(context, key, tenant, targets, list);
		}
		if(targets.indexOf(CompanyEnum.InventoryDefferenceGoodsItemCount) >= 0){
			// 异常商品条目
			List<InventoryInfo> gglist =
			        context.getList(InventoryInfo.class, new GetExceptionInventoryKey());
			Set<GUID> glist = new HashSet<GUID>();
			for(InventoryInfo det : gglist){
				glist.add(det.getGoodsItemId());
			}
			if(list.isEmpty()){
				ReportResult rr = new ReportResult();
				rr.setTargetValue(CompanyEnum.InventoryDefferenceGoodsItemCount, glist.size());
				list.add(rr);
			}
			else{
				for(ReportResult rr : list){
					rr.setTargetValue(CompanyEnum.InventoryDefferenceGoodsItemCount, glist.size());
				}
			}
		}
		if(targets.indexOf(CompanyEnum.InventoryBalance) >= 0){
			// 库存余额
			GoodsInventorySum sum = context.find(GoodsInventorySum.class, new GoodsInventorySumKey());
			if(list.isEmpty()){
				ReportResult rr = new ReportResult();
				rr.setTargetValue(CompanyEnum.InventoryBalance, sum.getTotalAmount());
				list.add(rr);
			}
			else{
				for(ReportResult rr : list){
					rr.setTargetValue(CompanyEnum.InventoryBalance, sum.getTotalAmount());
				}
			}
		}
		if(targets.indexOf(CompanyEnum.TotalReceiptingBanlance) >= 0){
			// 应收余额总数
			List<Partner> plist = null;
			if(null == depCon){
				plist = context.getList(Partner.class, new GetCustomerListByEmployeeIdKey(login.getEmployeeId()));
			}
			else{
				plist = context.getList(Partner.class, new GetCustomerListByDepartmentIdKey((GUID)depCon.getValue()));
			}
			double amount = 0;
			for(Partner p : plist){
				amount += getReceiptingAmount(context, p.getId());
			}

			if(list.isEmpty()){
				ReportResult rr = new ReportResult();
				rr.setTargetValue(CompanyEnum.TotalReceiptingBanlance, amount);
				list.add(rr);
			}
			else{
				for(ReportResult rr : list){
					rr.setTargetValue(CompanyEnum.TotalReceiptingBanlance, amount);
				}
			}
		}
		if(targets.indexOf(CompanyEnum.TotalPayingBanlance) >= 0){
			// 应付余额总数
			List<Partner> plist = context.getList(Partner.class, PartnerType.Supplier);
			double amount = 0;
			for(Partner p : plist){
				amount += getPayingAmount(context, p.getId());
			}

			if(list.isEmpty()){
				ReportResult rr = new ReportResult();
				rr.setTargetValue(CompanyEnum.TotalPayingBanlance, amount);
				list.add(rr);
			}
			else{
				for(ReportResult rr : list){
					rr.setTargetValue(CompanyEnum.TotalPayingBanlance, amount);
				}
			}
		}
		if(targets.indexOf(CompanyEnum.ExceedTimeLimitNotDeliveredAmount) >= 0){
			// 逾期未到货金额
			Double value = context.find(Double.class, new GetExceedTimeLimitNotDeliveredAmountKey());
			if(null == value){
				value = 0d;
			}
			if(list.isEmpty()){
				ReportResult rr = new ReportResult();
				rr.setTargetValue(CompanyEnum.ExceedTimeLimitNotDeliveredAmount, value);
				list.add(rr);
			}
			else{
				for(ReportResult rr : list){
					rr.setTargetValue(CompanyEnum.ExceedTimeLimitNotDeliveredAmount, value);
				}
			}
		}
		if(targets.indexOf(CompanyEnum.OverCreditDayAmount) >= 0){
			Set<Partner> pset = new HashSet<Partner>();
			if(AuthUtils.isBoss(context, login.getEmployeeId()) && null == depCon){
				pset.addAll(context.getList(Partner.class, PartnerType.Customer));
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
				pset.addAll(context.getList(Partner.class, new GetCustomerListByDepartmentIdKey(deptId)));
			}
			else{
				pset.addAll(context.getList(Partner.class, new GetCustomerListByEmployeeIdKey(login.getEmployeeId())));
			}
			double value = 0;
			for(Partner p : pset){
				OverPeriodAmountKey key2 = new OverPeriodAmountKey();
				key2.setPartnerId(p.getId());
				key2.setAccountPeriod(p.getAccountPeriod());
				key2.setQueryAll(true);
				key2.setTenantId(login.getTenantId());
				Double overValue = context.find(Double.class, key2);
				value += overValue;
			}
			// 超账期欠款金额  
			if(list.isEmpty()){
				ReportResult rr = new ReportResult();
				rr.setTargetValue(CompanyEnum.OverCreditDayAmount, value);
				list.add(rr);
			}
			else{
				for(ReportResult rr : list){
					rr.setTargetValue(CompanyEnum.OverCreditDayAmount, value);
				}
			}
		}
		// 正式客户数量  & 潜在客户数量
		if(targets.indexOf(CompanyEnum.PotentialCustomersCount) >= 0
		        || targets.indexOf(CompanyEnum.OfficialCustomersCount) >= 0)
		{
			companyDataProvide_CustomerCountByDate(context, key, login, targets, list);
		}
		//销售进度监控
		if(targets.indexOf(CompanyEnum.SalesAmountTarget) >= 0
		        || targets.indexOf(CompanyEnum.SalesReceiptAmountTarget) >= 0)
		{
			GUID id = null;
			if(AuthUtils.isBoss(context, login.getEmployeeId()) && null == depCon){
				id = GUID.emptyID;
			}
			else if(AuthUtils.isSalesManager(context, login.getEmployeeId()) || null != depCon){
				if(null == depCon){
					Employee emp = context.find(Employee.class, login.getEmployeeId());
					id = emp.getDepartmentId();
				}
				else{
					id = (GUID)depCon.getValue();
				}
			}
			else{
				id = login.getEmployeeId();
			}
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
	}

	/**
	 * @param context
	 * @param objid
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static MonitorTargetEntityTemp getTargetTemp(Context context, GUID objid){
		MonitorTargetEntityTemp temp = new MonitorTargetEntityTemp();
		Date date = new Date();
		int yearNo = ReportDateUtils.getYearNo(date);
		List<SalesTargetItem> slist =
		        context.getList(SalesTargetItem.class, new GUID[] {objid}, MonitorDataType.SalesAmount.getCode(),
		                yearNo + "");
		List<SalesTargetItem> rlist =
		        context.getList(SalesTargetItem.class, new GUID[] {objid}, MonitorDataType.ReceiptAmount.getCode(),
		                yearNo + "");
		SalesTargetItem item = null;
		SalesTargetItem item2 = null;
		if(slist.size() > 0){
			item = slist.get(0);
		}
		if(rlist.size() > 0){
			item2 = rlist.get(0);
		}
		temp.setTotalSalesTarget(item.getValue01() + item.getValue02() + item.getValue03() + item.getValue04()
		        + item.getValue05() + item.getValue06() + item.getValue07() + item.getValue08() + item.getValue09()
		        + item.getValue10() + item.getValue11() + item.getValue12());
		temp.setTotalReceiptTarget(item2.getValue01() + item2.getValue02() + item2.getValue03() + item2.getValue04()
		        + item2.getValue05() + item2.getValue06() + item2.getValue07() + item2.getValue08()
		        + item2.getValue09() + item2.getValue10() + item2.getValue11() + item2.getValue12());
		int m = date.getMonth();
		switch(m){
			case 0:
				temp.setSalesTarget(item.getValue01());
				temp.setReceiptTarget(item2.getValue01());
				break;
			case 1:
				temp.setSalesTarget(item.getValue02());
				temp.setReceiptTarget(item2.getValue02());
				break;
			case 2:
				temp.setSalesTarget(item.getValue03());
				temp.setReceiptTarget(item2.getValue03());
				break;
			case 3:
				temp.setSalesTarget(item.getValue04());
				temp.setReceiptTarget(item2.getValue04());
				break;
			case 4:
				temp.setSalesTarget(item.getValue05());
				temp.setReceiptTarget(item2.getValue05());
				break;
			case 5:
				temp.setSalesTarget(item.getValue06());
				temp.setReceiptTarget(item2.getValue06());
				break;
			case 6:
				temp.setSalesTarget(item.getValue07());
				temp.setReceiptTarget(item2.getValue07());
				break;
			case 7:
				temp.setSalesTarget(item.getValue08());
				temp.setReceiptTarget(item2.getValue08());
				break;
			case 8:
				temp.setSalesTarget(item.getValue09());
				temp.setReceiptTarget(item2.getValue09());
				break;
			case 9:
				temp.setSalesTarget(item.getValue10());
				temp.setReceiptTarget(item2.getValue10());
				break;
			case 10:
				temp.setSalesTarget(item.getValue11());
				temp.setReceiptTarget(item2.getValue11());
				break;
			case 11:
				temp.setSalesTarget(item.getValue12());
				temp.setReceiptTarget(item2.getValue12());
				break;
		}
		return temp;
	}

	/** 
	 * 客户发展情况
	 */
	@SuppressWarnings("unchecked")
	private static void companyDataProvide_CustomerCountByDate(Context context, ReportCommonKey key, Login login,
	        List<Enum> targets, List<ReportResult> list)
	{
		if(!key.isDateColumn()){
			return;
		}
		DateTimeEnum datetarget = DateTimeEnum.getTarget(key.getOrderTarget());
		ReportCommonKey dk = new ReportCommonKey(SubjectEnum.DateTime);
		dk.addTarget(datetarget);
		dk.setMaxCount(key.getMaxCount());
		List<ReportResult> rrlist = context.getList(ReportResult.class, dk);
		int small = 0;
		if(!rrlist.isEmpty()){
			small = rrlist.get(0).getIntegerValue(datetarget);
		}
		String smallColumn = datetarget.getColumnName();
		ReportResult minrr = getSmallDateCount(context, login, small, smallColumn, key);
		int minocount = minrr.getIntegerValue(CompanyEnum.OfficialCustomersCount);
		int minpcount = minrr.getIntegerValue(CompanyEnum.PotentialCustomersCount);

		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		QuerySqlBuilder c1 = columnQueryBuilder(context, qb, login, false, key);
		QuerySqlBuilder c2 = columnQueryBuilder(context, qb, login, true, key);
		qb.addTable("(SELECT t1." + smallColumn + " AS " + smallColumn
		        + " FROM SA_REPORT_CUSTOMER_COUNT AS t1 UNION ALL (SELECT t2." + smallColumn + "2 AS " + smallColumn
		        + " FROM SA_REPORT_CUSTOMER_COUNT AS t2 WHERE t2." + smallColumn + "2 >0))", "m");
		c1.addEquals("t." + smallColumn, "m." + smallColumn);
		c2.addEquals("t." + smallColumn + "2", "m." + smallColumn);

		qb.addColumn("(" + c1.getRealDnaSql() + ")", "ccc1");
		qb.addColumn("(" + c2.getRealDnaSql() + ")", "ccc2");
		qb.addColumn("m." + smallColumn, smallColumn);

		qb.addGroupBy("m." + smallColumn);
		if(key.isOrderDesc()){
			smallColumn += " desc ";
		}
		qb.addOrderBy("m." + smallColumn);

		RecordSet rs = null;
		if(0 == key.getMaxCount()){
			rs = qb.getRecord();
		}
		else{
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		int index = 0;
		while(rs.next()){
			ReportResult rr = null;
			boolean b = false;
			if(index < list.size()){
				rr = list.get(index++);
			}
			else{
				b = true;
				index++;
				rr = new ReportResult();
			}
			rr.setTargetValue(CompanyEnum.PotentialCustomersCount, rs.getFields().get(0).getInt());
			rr.setTargetValue(CompanyEnum.OfficialCustomersCount, rs.getFields().get(1).getInt());
			rr.setTargetValue(datetarget, rs.getFields().get(2).getObject());
			if(b){
				list.add(rr);
			}
		}
		for(ReportResult rr : rrlist){
			int intkey = rr.getIntegerValue(datetarget);
			boolean b = true;
			for(ReportResult e : list){
				if(intkey == e.getIntegerValue(datetarget)){
					Integer ocount = e.getIntegerValue(CompanyEnum.OfficialCustomersCount);
					Integer pcount = e.getIntegerValue(CompanyEnum.PotentialCustomersCount);
					if(null == ocount){
						ocount = 0;
					}
					if(null == pcount){
						pcount = 0;
					}
					minocount += ocount;
					minpcount += pcount;
					e.setTargetValue(CompanyEnum.PotentialCustomersCount, minpcount);
					e.setTargetValue(CompanyEnum.OfficialCustomersCount, minocount);
					b = false;
					continue;
				}
			}
			if(b){
				ReportResult e = new ReportResult();
				e.setTargetValue(CompanyEnum.PotentialCustomersCount, minpcount);
				e.setTargetValue(CompanyEnum.OfficialCustomersCount, minocount);
				e.setTargetValue(datetarget, intkey);
				list.add(e);
			}
		}
	}

	private static ReportResult getSmallDateCount(Context context, Login login, int small, String column,
	        ReportCommonKey key)
	{
		ReportResult rr = new ReportResult();
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		QuerySqlBuilder c1 = columnQueryBuilder(context, qb, login, false, key);
		QuerySqlBuilder c2 = columnQueryBuilder(context, qb, login, true, key);
		qb.addArgs("small", qb.INT, small);
		c1.addLessThan("t." + column, "@small");
		c2.addLessThan("t." + column + "2", "@small");

		qb.addTable("(" + c1.getRealDnaSql() + ")", "c1");
		qb.addTable("(" + c2.getRealDnaSql() + ")", "c2");

		qb.addColumn("c1.count1", "ccc");
		qb.addColumn("c2.count1", "cc");

		RecordSet rs = qb.getRecord();
		while(rs.next()){
			rr.setTargetValue(CompanyEnum.PotentialCustomersCount, rs.getFields().get(0).getInt());
			rr.setTargetValue(CompanyEnum.OfficialCustomersCount, rs.getFields().get(1).getInt());
		}
		return rr;
	}

	private static QuerySqlBuilder columnQueryBuilder(Context context, QuerySqlBuilder parent, Login login,
	        boolean isOffical, ReportCommonKey key)
	{
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_CUSTOMER_COUNT", "t");

		parent.addArgs("comNo", qb.guid, login.getTenantId());
		parent.addArgs("zero", qb.INT, 0);
		qb.addEquals("t.tenantId", "@comNo");
		if(!isOffical){
			qb.addEquals("t.dateNo2", "@zero");
		}
		else{
			qb.addGreaterThan("t.dateNo2", "@zero");
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
				parent.addArgs("dept" + i, qb.guid, id);
				args.add("@dept" + i++);
			}
			if(isOffical){
				qb.addIn("t.deptId2", args);
			}
			else{
				qb.addIn("t.deptId", args);
			}
		}
		else if(AuthUtils.isSales(context, login.getEmployeeId())){
			// 员工权限
			parent.addArgs("userId", qb.guid, login.getEmployeeId());
			if(isOffical){
				qb.addEquals("t.employeeId2", "@userId");
			}
			else{
				qb.addEquals("t.employeeId", "@userId");
			}
		}

		qb.addColumn("count(t.recid)", "count1");
		return qb;
	}

	/**
	 * 本年采购数据
	 * @param context
	 * @param key
	 * @param tenant
	 * @param targets
	 * @param rr
	 */
	@SuppressWarnings("unchecked")
	private static void companyDataProvide_PurchaseThisYear(Context context, ReportCommonKey key, Tenant tenant,
	        List<Enum> targets, List<ReportResult> list)
	{
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_PURCHASE_DATE", "t");

		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");
		qb.addColumn("sum(t.receiptAmount)", "receiptAmount");
		qb.addArgs("yearKey", qb.INT, ReportDateUtils.getYearNo(new Date()));
		qb.addEquals("t.yearNo", "@yearKey");
		Enum target = null;
		if(CheckIsNull.isNotEmpty(key.getOrderTarget())){
			String orderStr = null;
			if(key.isDateColumn()){
				DateTimeEnum datetarget = DateTimeEnum.getTarget(key.getOrderTarget());
				orderStr = datetarget.getColumnName();
				target = datetarget;
			}
			else{
				CompanyEnum companyTarget = CompanyEnum.getTarget(key.getOrderTarget());
				orderStr = companyTarget.getfield();
				target = companyTarget;
			}
			qb.addColumn("t." + orderStr, orderStr);
			qb.addGroupBy("t." + orderStr);
			if(key.isOrderDesc()){
				orderStr += " desc ";
			}
			qb.addOrderBy("t." + orderStr);
		}
		RecordSet rs = null;
		if(0 == key.getMaxCount()){
			rs = qb.getRecord();
		}
		else{
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		int index = 0;
		while(rs.next()){
			ReportResult rr = null;
			boolean b = false;
			if(index < list.size()){
				rr = list.get(index++);
			}
			else{
				b = true;
				index++;
				rr = new ReportResult();
			}
			rr.setTargetValue(CompanyEnum.PaidAmountOfThisYear, rs.getFields().get(0).getDouble());
			if(null != target){
				rr.setTargetValue(target, rs.getFields().get(1).getObject());
			}
			if(b){
				list.add(rr);
			}
		}
	}

	/**
	 * 客户的应收余额
	 */
	private static double getReceiptingAmount(Context context, GUID customerId){
		BalanceAmount ba = context.find(BalanceAmount.class, new GetBalanceAmountByPartnerKey(customerId));
		if(null == ba){
			return 0;
		}
		return ba.getDueAmount();
	}

	/**
	 * 应付余额
	 */
	private static double getPayingAmount(Context context, GUID supplierId){
		BalanceAmount ba = context.find(BalanceAmount.class, new GetBalanceAmountByPartnerKey(supplierId));
		if(null == ba){
			return 0;
		}
		return ba.getDueAmount();
	}

	/**
	 * 本年销售数据
	 * @param receiptTarget 
	 * @param salesTarget 
	 */
	@SuppressWarnings("unchecked")
	private static void companyDataProvide_SalesThisYear(Context context, ReportCommonKey key, Tenant tenant,
	        List<Enum> targets, List<ReportResult> list, double salesTarget, double receiptTarget)
	{
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_SALES_DATE", "t");

		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.receiptAmount)", "receiptAmount");
		qb.addArgs("yearKey", qb.INT, ReportDateUtils.getYearNo(new Date()));
		qb.addEquals("t.yearNo", "@yearKey");
		Enum target = null;
		if(CheckIsNull.isNotEmpty(key.getOrderTarget())){
			String orderStr = null;
			if(key.isDateColumn()){
				DateTimeEnum datetarget = DateTimeEnum.getTarget(key.getOrderTarget());
				orderStr = datetarget.getColumnName();
				target = datetarget;
			}
			else{
				CompanyEnum companyTarget = CompanyEnum.getTarget(key.getOrderTarget());
				orderStr = companyTarget.getfield();
				target = companyTarget;
			}
			qb.addColumn("t." + orderStr, orderStr);
			qb.addGroupBy("t." + orderStr);
			if(key.isOrderDesc()){
				orderStr += " desc ";
			}
			qb.addOrderBy("t." + orderStr);
		}
		RecordSet rs = null;
		if(0 == key.getMaxCount()){
			rs = qb.getRecord();
		}
		else{
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		int index = 0;
		while(rs.next()){
			ReportResult rr = null;
			boolean b = false;
			if(index < list.size()){
				rr = list.get(index++);
			}
			else{
				b = true;
				index++;
				rr = new ReportResult();
			}
			String vstr1 = null;
			double value1 = rs.getFields().get(0).getDouble();
			if(salesTarget > 0){
				vstr1 =
				        DoubleUtil.getRoundStr(value1) + "("
				                + DoubleUtil.getRoundStr(DoubleUtil.mul(100, DoubleUtil.div(value1, salesTarget, 4)))
				                + "%)";
			}
			else{
				vstr1 = DoubleUtil.getRoundStr(value1);
			}
			rr.setTargetValue(CompanyEnum.SalesSuccessAmountOfThisYear, vstr1);
			String vstr2 = null;
			double value2 = rs.getFields().get(1).getDouble();
			if(receiptTarget > 0){
				vstr2 =
				        DoubleUtil.getRoundStr(value2) + "("
				                + DoubleUtil.getRoundStr(DoubleUtil.mul(100, DoubleUtil.div(value2, receiptTarget, 4)))
				                + "%)";
			}
			else{
				vstr2 = DoubleUtil.getRoundStr(value2);
			}
			rr.setTargetValue(CompanyEnum.ReceiptedAmountOfThisYear, value2);
			rr.setTargetValue(CompanyEnum.ReceiptedSuccessAmountOfThisYear, vstr2);
			if(null != target){
				rr.setTargetValue(target, rs.getFields().get(2).getObject());
			}
			if(b){
				list.add(rr);
			}
		}
	}

	/**
	 * 本月采购数据
	 */
	@SuppressWarnings("unchecked")
	private static void companyDataProvide_PurchaseThisMonth(Context context, ReportCommonKey key, Tenant tenant,
	        List<Enum> targets, List<ReportResult> list)
	{
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_PURCHASE_DATE", "t");

		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.receiptAmount)", "receiptAmount");

		qb.addArgs("monthKey", qb.INT, ReportDateUtils.getMonthNo(new Date()));
		qb.addEquals("t.monthNo", "@monthKey");
		Enum target = null;
		if(CheckIsNull.isNotEmpty(key.getOrderTarget())){
			String orderStr = null;
			if(key.isDateColumn()){
				DateTimeEnum datetarget = DateTimeEnum.getTarget(key.getOrderTarget());
				orderStr = datetarget.getColumnName();
				target = datetarget;
			}
			else{
				CompanyEnum companyTarget = CompanyEnum.getTarget(key.getOrderTarget());
				orderStr = companyTarget.getfield();
				target = companyTarget;
			}
			qb.addColumn("t." + orderStr, orderStr);
			qb.addGroupBy("t." + orderStr);
			if(key.isOrderDesc()){
				orderStr += " desc ";
			}
			qb.addOrderBy("t." + orderStr);
		}
		RecordSet rs = null;
		if(0 == key.getMaxCount()){
			rs = qb.getRecord();
		}
		else{
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		int index = 0;
		while(rs.next()){
			ReportResult rr = null;
			boolean b = false;
			if(index < list.size()){
				rr = list.get(index++);
			}
			else{
				b = true;
				index++;
				rr = new ReportResult();
			}
			rr.setTargetValue(CompanyEnum.TotalPurchaseAmountOfThisMonth, rs.getFields().get(0).getDouble());
			rr.setTargetValue(CompanyEnum.PaidAmountOfThisMonth, rs.getFields().get(1).getDouble());
			if(null != target){
				rr.setTargetValue(target, rs.getFields().get(2).getObject());
			}
			if(b){
				list.add(rr);
			}
		}
	}

	/**
	 * 本月销售数据
	 * @param receiptTarget 
	 * @param salesTarget 
	 */
	@SuppressWarnings("unchecked")
	private static void companyDataProvide_SalesThisMonth(Context context, ReportCommonKey key, Login login,
	        List<Enum> targets, List<ReportResult> list, double salesTarget, double receiptTarget)
	{
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_SALES_DATE", "t");

		qb.addArgs("comNo", qb.guid, login.getTenantId());
		qb.addEquals("t.tenantId", "@comNo");
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.receiptAmount)", "receiptAmount");
		qb.addColumn("sum(t.rtnAmount)", "rtnAmount");

		qb.addArgs("monthKey", qb.INT, ReportDateUtils.getMonthNo(new Date()));
		qb.addEquals("t.monthNo", "@monthKey");
		Enum target = null;
		if(CheckIsNull.isNotEmpty(key.getOrderTarget())){
			String orderStr = null;
			if(key.isDateColumn()){
				DateTimeEnum datetarget = DateTimeEnum.getTarget(key.getOrderTarget());
				orderStr = datetarget.getColumnName();
				target = datetarget;
			}
			else{
				CompanyEnum companyTarget = CompanyEnum.getTarget(key.getOrderTarget());
				orderStr = companyTarget.getfield();
				target = companyTarget;
			}
			qb.addColumn("t." + orderStr, orderStr);
			qb.addGroupBy("t." + orderStr);
			if(key.isOrderDesc()){
				orderStr += " desc ";
			}
			qb.addOrderBy("t." + orderStr);
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
		RecordSet rs = null;
		if(0 == key.getMaxCount()){
			rs = qb.getRecord();
		}
		else{
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		int index = 0;
		while(rs.next()){
			ReportResult rr = null;
			boolean b = false;
			if(index < list.size()){
				rr = list.get(index++);
			}
			else{
				b = true;
				index++;
				rr = new ReportResult();
			}
			String vstr1 = null;
			double value1 = rs.getFields().get(0).getDouble();
			if(salesTarget > 0){
				vstr1 =
				        DoubleUtil.getRoundStr(value1) + "("
				                + DoubleUtil.getRoundStr(DoubleUtil.mul(100, DoubleUtil.div(value1, salesTarget, 4)))
				                + "%)";
			}
			else{
				vstr1 = DoubleUtil.getRoundStr(value1);
			}
			rr.setTargetValue(CompanyEnum.SalesSuccessAmountOfThisMonth, vstr1);
			String vstr2 = null;
			double value2 = rs.getFields().get(1).getDouble();
			if(receiptTarget > 0){
				vstr2 =
				        DoubleUtil.getRoundStr(value2) + "("
				                + DoubleUtil.getRoundStr(DoubleUtil.mul(100, DoubleUtil.div(value2, receiptTarget, 4)))
				                + "%)";
			}
			else{
				vstr2 = DoubleUtil.getRoundStr(value2);
			}
			rr.setTargetValue(CompanyEnum.ReceiptedAmountOfThisMonth, value2);
			rr.setTargetValue(CompanyEnum.ReceiptedSuccessAmountOfThisMonth, vstr2);
			rr.setTargetValue(CompanyEnum.SalesReturnAmountOfThisMonth, rs.getFields().get(2).getDouble());
			if(null != target){
				rr.setTargetValue(target, rs.getFields().get(3).getObject());
			}
			if(b){
				list.add(rr);
			}
		}
	}

	/**
	 * 销售数据
	 */
	@SuppressWarnings("unchecked")
	private static void companyDataProvide_Sales(Context context, ReportCommonKey key, Tenant tenant,
	        List<Enum> targets, List<ReportResult> list)
	{
		QuerySqlBuilder qb = new QuerySqlBuilder(context);

		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.outstoAmount)", "outstoAmount");
		qb.addColumn("sum(t.receiptAmount)", "receiptAmount");
		qb.addColumn("sum(t.rtnAmount)", "rtnAmount");
		Enum target = null;
		if(CheckIsNull.isNotEmpty(key.getOrderTarget())){
			String orderStr = null;
			if(key.isDateColumn()){
				DateTimeEnum datetarget = DateTimeEnum.getTarget(key.getOrderTarget());
				orderStr = datetarget.getColumnName();
				target = datetarget;
			}
			else{
				CompanyEnum companyTarget = CompanyEnum.getTarget(key.getOrderTarget());
				orderStr = companyTarget.getfield();
				target = companyTarget;
			}
			qb.addColumn("t." + orderStr, orderStr);
			qb.addGroupBy("t." + orderStr);
			if(key.isOrderDesc()){
				orderStr += " desc ";
			}
			qb.addOrderBy("t." + orderStr);
		}
		Login login = context.find(Login.class);
		Condition depCon = ReportUtils.findCondition("DepartmentId", key.getConditions());
		
		if((AuthUtils.isBoss(context, login.getEmployeeId())||AuthUtils.isAccount(context, login.getEmployeeId()) )&& null == depCon){
			qb.addTable("SA_REPORT_DEPT_SALES_DATE", "t");
			qb.addEquals("t.deptGuid", "@comNo");
		}
		else if(AuthUtils.isSalesManager(context, login.getEmployeeId()) || null != depCon){
			GUID deptId = null;
			if(null == depCon){
				Employee emp = context.find(Employee.class, login.getEmployeeId());
				deptId = emp.getDepartmentId();
			}
			else{
				deptId = (GUID)depCon.getValue();
			}
			qb.addTable("SA_REPORT_DEPT_SALES_DATE", "t");
			qb.addArgs("deptIda", qb.guid, deptId);
			qb.addEquals("t.deptGuid", "@deptIda");
		}
		else if(AuthUtils.isSales(context, login.getEmployeeId())){
			qb.addTable("SA_REPORT_SALES_DATE", "t");
			Employee user = context.find(Employee.class, login.getEmployeeId());
			qb.addArgs("userId", qb.guid, user.getId());
			qb.addEquals("t.orderPerson", "@userId");
		}
		RecordSet rs = null;
		if(0 == key.getMaxCount()){
			rs = qb.getRecord();
		}
		else{
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		while(rs.next()){
			ReportResult rr = new ReportResult();
			rr.setTargetValue(CompanyEnum.TotalSalesAmount, rs.getFields().get(0).getDouble());
			rr.setTargetValue(CompanyEnum.TotalSalesCheckOutAmount, rs.getFields().get(1).getDouble());
			rr.setTargetValue(CompanyEnum.TotalSalesReceiptAmount, rs.getFields().get(2).getDouble());
			rr.setTargetValue(CompanyEnum.TotalSalesReturnAmount, rs.getFields().get(3).getDouble());
			if(null != target){
				rr.setTargetValue(target, rs.getFields().get(4).getObject());
			}
			list.add(rr);
		}
	}

	/**
	 * 采购数据
	 */
	@SuppressWarnings("unchecked")
	private static void companyDataProvide_Purchase(Context context, ReportCommonKey key, Tenant tenant,
	        List<Enum> targets, List<ReportResult> list)
	{
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_PURCHASE_DATE", "t");

		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t.tenantId", "@comNo");

		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.outstoAmount)", "outstoAmount");
		qb.addColumn("sum(t.receiptAmount)", "receiptAmount");
		Enum target = null;
		if(CheckIsNull.isNotEmpty(key.getOrderTarget())){
			String orderStr = null;
			if(key.isDateColumn()){
				DateTimeEnum datetarget = DateTimeEnum.getTarget(key.getOrderTarget());
				orderStr = datetarget.getColumnName();
				target = datetarget;
			}
			else{
				CompanyEnum companyTarget = CompanyEnum.getTarget(key.getOrderTarget());
				orderStr = companyTarget.getfield();
				target = companyTarget;
			}
			qb.addColumn("t." + orderStr, orderStr);
			qb.addGroupBy("t." + orderStr);
			if(key.isOrderDesc()){
				orderStr += " desc ";
			}
			qb.addOrderBy("t." + orderStr);
		}
		RecordSet rs = null;
		if(0 == key.getMaxCount()){
			rs = qb.getRecord();
		}
		else{
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		int index = 0;
		while(rs.next()){
			ReportResult rr = null;
			boolean b = false;
			if(index < list.size()){
				rr = list.get(index++);
			}
			else{
				b = true;
				index++;
				rr = new ReportResult();
			}
			rr.setTargetValue(CompanyEnum.TotalPurchaseAmount, rs.getFields().get(0).getDouble());
			rr.setTargetValue(CompanyEnum.TotalPurchaseCheckInAmount, rs.getFields().get(1).getDouble());
			rr.setTargetValue(CompanyEnum.TotalPurchasePayAmount, rs.getFields().get(2).getDouble());
			if(null != target){
				rr.setTargetValue(target, rs.getFields().get(3).getObject());
			}
			if(b){
				list.add(rr);
			}
		}
	}
}
