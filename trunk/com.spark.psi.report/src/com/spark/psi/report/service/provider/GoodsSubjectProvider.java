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
import com.spark.common.utils.ComparatorUtils;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.inventory.intf.entity.inventory.GoodsInventorySum;
import com.spark.psi.inventory.intf.key.inventory.GetExceptionInventoryKey;
import com.spark.psi.inventory.intf.key.inventory.GoodsInventorySumKey;
import com.spark.psi.publish.inventory.entity.InventoryInfo;
import com.spark.psi.report.constant.TargetEnum.GoodsItemEnum;
import com.spark.psi.report.entity.ReportResult;
import com.spark.psi.report.key.ReportCommonKey;
import com.spark.psi.report.utils.AuthUtils;
import com.spark.psi.report.utils.Condition;
import com.spark.psi.report.utils.QuerySqlBuilder;
import com.spark.psi.report.utils.ReportDateUtils;
import com.spark.psi.report.utils.ReportUtils;

/**
 *
 */
public abstract class GoodsSubjectProvider {
	/**
	 * 商品报表数据查询
	 * 
	 * @param context
	 * @param key
	 * @param list
	 * @throws Exception
	 */
	protected static void provide(Context context, ReportCommonKey key,
			List<ReportResult> list) {

		if (key.getTargets().indexOf(GoodsItemEnum.SalesAmount) >= 0
				|| key.getTargets().indexOf(GoodsItemEnum.SalesReturnAmount) >= 0) {
			salesProvider(context, key, list);
		} else if (key.getTargets().indexOf(GoodsItemEnum.PurchaseAmount) >= 0) {
			purchaseProvider(context, key, list);
		} else {
			inventoryProvider(context, key, list);
		}
	}

	/**
	 * @param context
	 * @param key
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	private static void purchaseProvider(Context context, ReportCommonKey key,
			List<ReportResult> list) {
		Tenant tenant = context.find(Tenant.class);
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_GOODS_BUY_DATE", "t2");
		qb.addArgs("comNo", qb.guid, tenant.getId());
		qb.addEquals("t2.tenantId", "@comNo");
		List<Enum> targets = key.getTargets();
		qb.addColumn("t2.goodsItemId", "goodsItemId");
		if (targets.indexOf(GoodsItemEnum.PurchaseAmount) >= 0) {
			qb.addColumn("sum(t2.ordAmount)", "ordAmounts");
		}
		if (targets.indexOf(GoodsItemEnum.PurchaseCount) >= 0) {
			qb.addColumn("sum(t2.ordCount)", "ordCount2");
		}
		if (targets.indexOf(GoodsItemEnum.PurchaseReturnAmount) >= 0) {
			qb.addColumn("sum(t2.rtnAmount)", "rtnAmounts");
		}
		if (targets.indexOf(GoodsItemEnum.InStoreCount) >= 0) {
			qb.addColumn("sum(t2.outstoAmount)", "outstoAmount2");
		}
		if (targets.indexOf(GoodsItemEnum.PayAmount) >= 0) {
			qb.addColumn("sum(t2.receiptAmount)", "receiptAmount2");
		}
		Map<GUID, ReportResult> ymap = null;
		if (targets.indexOf(GoodsItemEnum.PurchaseAmount_OfYear) >= 0
				|| targets.indexOf(GoodsItemEnum.PurchaseCount_OfYear) >= 0
				|| targets.indexOf(GoodsItemEnum.PurchaseReturnAmount_OfYear) >= 0) {
			ymap = getYearDoubleMapPurchase(context, key, tenant);
		}
		for (Condition con : key.getConditions()) {
			if ("month".toUpperCase().equals(
					con.getConditionColumn().toUpperCase())) {
				qb.addArgs("month", qb.INT, con.getValue());
				qb.addEquals("t2.monthNo", "@month");
			} else if ("season".toUpperCase().equals(
					con.getConditionColumn().toUpperCase())) {
				qb.addArgs("season", qb.INT, con.getValue());
				qb.addEquals("t2.quarter", "@season");
			} else {

			}
		}
		qb.addGroupBy("t2.goodsItemId");

		if (CheckIsNull.isNotEmpty(key.getOrderTarget())
				&& GoodsItemEnum.getTarget(key.getOrderTarget()) == GoodsItemEnum.PurchaseAmount) {
			String column = "ordAmounts";
			if (key.isOrderDesc()) {
				column = column + " desc";
			}
			qb.addOrderBy(column);
		} else if (CheckIsNull.isNotEmpty(key.getOrderTarget())
				&& GoodsItemEnum.getTarget(key.getOrderTarget()) == GoodsItemEnum.PurchaseReturnAmount) {
			String column = "rtnAmounts";
			if (key.isOrderDesc()) {
				column = column + " desc";
			}
			qb.addOrderBy(column);
		}

		RecordSet rs = null;
		if (key.getMaxCount() == 0) {
			rs = qb.getRecord();
		} else {
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		while (rs.next()) {
			ReportResult rr = new ReportResult();
			int index = 0;
			GUID goodsItemId = rs.getFields().get(index++).getGUID();
			rr.setTargetValue(GoodsItemEnum.Id, goodsItemId);
			GoodsItem item = context.find(GoodsItem.class, goodsItemId);
			rr.setTargetValue(GoodsItemEnum.GoodsName, item.getGoodsName());
			rr.setTargetValue(GoodsItemEnum.GoodsAttr, item
					.getPropertiesWithoutUnit());
			rr.setTargetValue(GoodsItemEnum.GoodsUnit, item.getGoodsUnit());
			if (targets.indexOf(GoodsItemEnum.PurchaseAmount) >= 0) {
				Object value = rs.getFields().get(index++).getObject();
				rr.setTargetValue(GoodsItemEnum.PurchaseAmount, value);
			}
			if (targets.indexOf(GoodsItemEnum.PurchaseCount) >= 0) {
				Object value = rs.getFields().get(index++).getObject();
				rr.setTargetValue(GoodsItemEnum.PurchaseCount, value);
			}
			if (targets.indexOf(GoodsItemEnum.PurchaseReturnAmount) >= 0) {
				Object value = rs.getFields().get(index++).getObject();
				rr.setTargetValue(GoodsItemEnum.PurchaseReturnAmount, value);
			}
			if (targets.indexOf(GoodsItemEnum.InStoreCount) >= 0) {
				Object value = rs.getFields().get(index++).getObject();
				rr.setTargetValue(GoodsItemEnum.InStoreCount, value);
			}
			if (targets.indexOf(GoodsItemEnum.PayAmount) >= 0) {
				Object value = rs.getFields().get(index++).getObject();
				rr.setTargetValue(GoodsItemEnum.PayAmount, value);
			}
			if (targets.indexOf(GoodsItemEnum.PurchaseAmount_OfYear) >= 0
					|| targets.indexOf(GoodsItemEnum.PurchaseCount_OfYear) >= 0
					|| targets
							.indexOf(GoodsItemEnum.PurchaseReturnAmount_OfYear) >= 0) {
				ReportResult tt = ymap.get(goodsItemId);
				if (tt != null) {
					rr
							.setTargetValue(
									GoodsItemEnum.PurchaseAmount_OfYear,
									tt
											.getTargetValue(GoodsItemEnum.PurchaseAmount_OfYear));
					rr
							.setTargetValue(
									GoodsItemEnum.PurchaseCount_OfYear,
									tt
											.getTargetValue(GoodsItemEnum.PurchaseCount_OfYear));
					rr
							.setTargetValue(
									GoodsItemEnum.PurchaseReturnAmount_OfYear,
									tt
											.getTargetValue(GoodsItemEnum.PurchaseReturnAmount_OfYear));
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
	private static Map<GUID, ReportResult> getYearDoubleMapPurchase(
			Context context, ReportCommonKey key, Tenant tenant) {
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_GOODS_BUY_DATE", "t");
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

		qb.addColumn("t.goodsItemId", "id");
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.ordCount)", "receiptAmount");
		qb.addColumn("sum(t.rtnAmount)", "rtnAmount");
		qb.addGroupBy("t.goodsItemId");
		RecordSet rs = qb.getRecord();
		Map<GUID, ReportResult> map = new HashMap<GUID, ReportResult>();
		while (rs.next()) {
			GUID id = rs.getFields().get(0).getGUID();
			ReportResult rr = new ReportResult();
			rr.setTargetValue(GoodsItemEnum.PurchaseAmount_OfYear, rs
					.getFields().get(1).getDouble());
			rr.setTargetValue(GoodsItemEnum.PurchaseCount_OfYear, rs
					.getFields().get(2).getDouble());
			rr.setTargetValue(GoodsItemEnum.PurchaseReturnAmount_OfYear, rs
					.getFields().get(3).getDouble());
			map.put(id, rr);
		}
		return map;
	}

	/**
	 * @param context
	 * @param key
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	private static void salesProvider(Context context, ReportCommonKey key,
			List<ReportResult> list) {
		Login login = context.find(Login.class);
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_GOODS_SALE_DATE", "t1");
		qb.addArgs("comNo", qb.guid, login.getTenantId());
		qb.addEquals("t1.tenantId", "@comNo");
		List<Enum> targets = key.getTargets();
		qb.addColumn("t1.goodsItemId", "goodsItemId");
		if (targets.indexOf(GoodsItemEnum.SalesAmount) >= 0) {
			qb.addColumn("sum(t1.ordAmount)", "ordAmounts");
		}
		if (targets.indexOf(GoodsItemEnum.SalesCount) >= 0) {
			qb.addColumn("sum(t1.ordCount)", "ordCount1");
		}
		if (targets.indexOf(GoodsItemEnum.SalesReturnAmount) >= 0) {
			if (targets.indexOf(GoodsItemEnum.SalesAmount) < 0) {
				qb.addArgs("zero", qb.INT, 0);
				qb.addGreaterThan("t1.rtnAmount", "@zero");
			}
			qb.addColumn("sum(t1.rtnAmount)", "rtnAmounts");
		}
		if (targets.indexOf(GoodsItemEnum.OutStoreCount) >= 0) {
			qb.addColumn("sum(t1.outstoAmount)", "outstoAmount1");
		}
		if (targets.indexOf(GoodsItemEnum.ReceiptAmount) >= 0) {
			qb.addColumn("sum(t1.receiptAmount)", "receiptAmount1");
		}
		Map<GUID, ReportResult> ymap = null;
		if (targets.indexOf(GoodsItemEnum.SalesAmount_OfYear) >= 0
				|| targets.indexOf(GoodsItemEnum.SalesCount_OfYear) >= 0
				|| targets.indexOf(GoodsItemEnum.SalesReturnAmount_HuanBi) >= 0) {
			ymap = getYearDoubleMap_Sales(context, key, login);
		}
		for (Condition con : key.getConditions()) {
			if ("month".toUpperCase().equals(
					con.getConditionColumn().toUpperCase())) {
				qb.addArgs("month", qb.INT, con.getValue());
				qb.addEquals("t1.monthNo", "@month");
			} else if ("thismonth".toUpperCase().equals(
					con.getConditionColumn().toUpperCase())) {
				qb.addArgs("month", qb.INT, ReportDateUtils
						.getMonthNo(new Date()));
				qb.addEquals("t1.monthNo", "@month");
			} else if ("season".toUpperCase().equals(
					con.getConditionColumn().toUpperCase())) {
				qb.addArgs("season", qb.INT, con.getValue());
				qb.addEquals("t1.quarter", "@season");
			} else {
			}
		}
		qb.addGroupBy("t1.goodsItemId");
		if (CheckIsNull.isNotEmpty(key.getOrderTarget())
				&& GoodsItemEnum.getTarget(key.getOrderTarget()) == GoodsItemEnum.SalesAmount) {
			String column = "ordAmounts";
			if (key.isOrderDesc()) {
				column = column + " desc";
			}
			qb.addOrderBy(column);
		} else if (CheckIsNull.isNotEmpty(key.getOrderTarget())
				&& GoodsItemEnum.getTarget(key.getOrderTarget()) == GoodsItemEnum.SalesReturnAmount) {
			String column = "rtnAmounts";
			if (key.isOrderDesc()) {
				column = column + " desc";
			}
			qb.addOrderBy(column);
		}

		Condition depCon = ReportUtils.findCondition("DepartmentId", key
				.getConditions());
		if (AuthUtils.isBoss(context, login.getEmployeeId()) && null == depCon) {
		} else if (AuthUtils.isManager(context, login.getEmployeeId())
				|| null != depCon) {
			GUID deptId = null;
			if (null == depCon) {
				Employee emp = context.find(Employee.class, login
						.getEmployeeId());
				deptId = emp.getDepartmentId();
			} else {
				deptId = (GUID) depCon.getValue();
			}
			Department dep = context.find(Department.class, deptId);
			List<String> argslist = new ArrayList<String>();
			int i = 0;
			for (Department d : dep.getDescendants(context)) {
				argslist.add("@" + "deptId" + i);
				qb.addArgs("deptId" + i, qb.guid, d.getId());
				i++;
			}
			qb.addIn("t1.deptGuid", argslist);
		} else if (AuthUtils.isSales(context, login.getEmployeeId())) {
			qb.addArgs("userId", qb.guid, login.getEmployeeId());
			qb.addEquals("t1.orderPerson", "@userId");
		}
		RecordSet rs = null;
		if (key.getMaxCount() == 0) {
			rs = qb.getRecord();
		} else {
			rs = qb.getRecordLimit(0, key.getMaxCount());
		}
		while (rs.next()) {
			ReportResult rr = new ReportResult();
			int index = 0;
			GUID goodsItemId = rs.getFields().get(index++).getGUID();
			rr.setTargetValue(GoodsItemEnum.Id, goodsItemId);
			GoodsItem item = context.find(GoodsItem.class, goodsItemId);
			rr.setTargetValue(GoodsItemEnum.GoodsName, item.getGoodsName());
			rr.setTargetValue(GoodsItemEnum.GoodsAttr, item
					.getPropertiesWithoutUnit());
			rr.setTargetValue(GoodsItemEnum.GoodsUnit, item.getGoodsUnit());
			if (targets.indexOf(GoodsItemEnum.SalesAmount) >= 0) {
				Object value = rs.getFields().get(index++).getObject();
				rr.setTargetValue(GoodsItemEnum.SalesAmount, value);
			}
			if (targets.indexOf(GoodsItemEnum.SalesCount) >= 0) {
				Object value = rs.getFields().get(index++).getObject();
				rr.setTargetValue(GoodsItemEnum.SalesCount, value);
			}
			if (targets.indexOf(GoodsItemEnum.SalesReturnAmount) >= 0) {
				Object value = rs.getFields().get(index++).getObject();
				rr.setTargetValue(GoodsItemEnum.SalesReturnAmount, value);
			}
			if (targets.indexOf(GoodsItemEnum.OutStoreCount) >= 0) {
				Object value = rs.getFields().get(index++).getObject();
				rr.setTargetValue(GoodsItemEnum.OutStoreCount, value);
			}
			if (targets.indexOf(GoodsItemEnum.ReceiptAmount) >= 0) {
				Object value = rs.getFields().get(index++).getObject();
				rr.setTargetValue(GoodsItemEnum.ReceiptAmount, value);
			}
			if (targets.indexOf(GoodsItemEnum.SalesAmount_OfYear) >= 0
					|| targets.indexOf(GoodsItemEnum.SalesCount_OfYear) >= 0
					|| targets.indexOf(GoodsItemEnum.SalesReturnAmount_OfYear) >= 0) {
				ReportResult tt = ymap.get(goodsItemId);
				if (tt != null) {
					rr.setTargetValue(GoodsItemEnum.SalesAmount_OfYear, tt
							.getTargetValue(GoodsItemEnum.SalesAmount_OfYear));
					rr.setTargetValue(GoodsItemEnum.SalesCount_OfYear, tt
							.getTargetValue(GoodsItemEnum.SalesCount_OfYear));
					rr
							.setTargetValue(
									GoodsItemEnum.SalesReturnAmount_OfYear,
									tt
											.getTargetValue(GoodsItemEnum.SalesReturnAmount_OfYear));
				}
			}
			list.add(rr);
		}
	}

	/**
	 * @param context
	 * @param key
	 * @param login
	 * @return
	 */
	private static Map<GUID, ReportResult> getYearDoubleMap_Sales(
			Context context, ReportCommonKey key, Login login) {
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("SA_REPORT_GOODS_SALE_DATE", "t");
		qb.addArgs("comNo", qb.guid, login.getTenantId());
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
		qb.addColumn("t.goodsItemId", "id");
		qb.addColumn("sum(t.ordAmount)", "ordAmount");
		qb.addColumn("sum(t.ordCount)", "receiptAmount");
		qb.addColumn("sum(t.rtnAmount)", "rtnAmount");
		qb.addGroupBy("t.goodsItemId");
		Condition depCon = ReportUtils.findCondition("DepartmentId", key
				.getConditions());
		if (AuthUtils.isBoss(context, login.getEmployeeId()) && null == depCon) {
		} else if (AuthUtils.isManager(context, login.getEmployeeId())
				|| null != depCon) {
			GUID deptId = null;
			if (null == depCon) {
				Employee emp = context.find(Employee.class, login
						.getEmployeeId());
				deptId = emp.getDepartmentId();
			} else {
				deptId = (GUID) depCon.getValue();
			}
			Department dep = context.find(Department.class, deptId);
			List<String> argslist = new ArrayList<String>();
			int i = 0;
			for (Department d : dep.getDescendants(context)) {
				argslist.add("@" + "deptId" + i);
				qb.addArgs("deptId" + i, qb.guid, d.getId());
				i++;
			}
			qb.addIn("t.deptGuid", argslist);
		} else if (AuthUtils.isSales(context, login.getEmployeeId())) {
			qb.addArgs("userId", qb.guid, login.getEmployeeId());
			qb.addEquals("t.orderPerson", "@userId");
		}
		RecordSet rs = qb.getRecord();
		Map<GUID, ReportResult> map = new HashMap<GUID, ReportResult>();
		while (rs.next()) {
			GUID id = rs.getFields().get(0).getGUID();
			ReportResult rr = new ReportResult();
			rr.setTargetValue(GoodsItemEnum.SalesAmount_OfYear, rs.getFields()
					.get(1).getDouble());
			rr.setTargetValue(GoodsItemEnum.SalesCount_OfYear, rs.getFields()
					.get(2).getDouble());
			rr.setTargetValue(GoodsItemEnum.SalesReturnAmount_OfYear, rs
					.getFields().get(3).getDouble());
			map.put(id, rr);
		}
		return map;
	}

	/**
	 * @param context
	 * @param key
	 * @param list
	 */
	private static void inventoryProvider(Context context, ReportCommonKey key,
			List<ReportResult> list) {
		List<InventoryInfo> glist = context.getList(
				InventoryInfo.class, new GetExceptionInventoryKey());
		Set<GUID> set = new HashSet<GUID>();
		for (InventoryInfo gi : glist) {
			set.add(gi.getGoodsItemId());
		}
		for (GUID id : set) {
			GoodsInventorySumKey sumkey = new GoodsInventorySumKey();
			sumkey.setGoodsItemId(id);
			GoodsInventorySum gi = context
					.find(GoodsInventorySum.class, sumkey);
			ReportResult rr = new ReportResult();
			GoodsItem item = context.find(GoodsItem.class, id);
			rr.setTargetValue(GoodsItemEnum.GoodsName, item.getGoodsName());
			rr.setTargetValue(GoodsItemEnum.GoodsAttr, item
					.getPropertiesWithoutUnit());
			rr.setTargetValue(GoodsItemEnum.GoodsUnit, item.getGoodsUnit());
			double inventoryAmount = gi.getTotalAmount();
			double deliveryingCount = gi.getTotalToDeliverCount();
			double iventoryCount = gi.getTotalCount();
			double iventoryDifference = iventoryCount - deliveryingCount;
			rr.setTargetValue(GoodsItemEnum.InventoryCount, iventoryCount);
			rr.setTargetValue(GoodsItemEnum.InventoryDifference,
					iventoryDifference);
			rr.setTargetValue(GoodsItemEnum.InventoryAmount, inventoryAmount);
			rr.setOrderObj(GoodsItemEnum.InventoryCount);
			list.add(rr);
		}

		ComparatorUtils.sort(list, "orderObj", false);

	}
}
