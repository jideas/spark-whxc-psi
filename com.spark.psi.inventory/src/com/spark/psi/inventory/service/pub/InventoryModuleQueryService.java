package com.spark.psi.inventory.service.pub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.key.GetCustomerOverCreditDayKey;
import com.spark.psi.base.key.GetPurchaseOrderGoodsCountByGoodsIdKey;
import com.spark.psi.base.key.GetRemindDateByPartnerIdKey;
import com.spark.psi.base.key.OverPeriodAmountKey;
import com.spark.psi.base.key.RemindingAmountKey;
import com.spark.psi.inventory.intf.entity.instorage.mod.RelationCheckInSheet;
import com.spark.psi.inventory.intf.entity.instorage.mod.SureInstorage;
import com.spark.psi.inventory.intf.entity.instorage.pub.CheckInDeductionLog;
import com.spark.psi.inventory.intf.entity.inventory.GoodsInventorySum;
import com.spark.psi.inventory.intf.entity.inventory.InventoryLog;
import com.spark.psi.inventory.intf.entity.outstorage.mod.RelationCheckOutSheet;
import com.spark.psi.inventory.intf.entity.outstorage.pub.CheckOutDeductionLog;
import com.spark.psi.inventory.intf.key.instorage.GetExceedTimeLimitNotDeliveredAmountKey;
import com.spark.psi.inventory.intf.key.instorage.mod.GetRelationCheckInSheetKey;
import com.spark.psi.inventory.intf.key.instorage.mod.report.GetSureInstorageByLogIdKey;
import com.spark.psi.inventory.intf.key.inventory.GoodsInventorySumKey;
import com.spark.psi.inventory.intf.key.inventory.StoStreamKey;
import com.spark.psi.inventory.intf.key.outstorage.mod.GetRelationCheckOutSheetKey;
import com.spark.psi.inventory.intf.key.pub.GetCheckInDeductionLogKey;
import com.spark.psi.inventory.intf.key.pub.GetCheckOutDeductionLogKey;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.inventory.key.GetAvailableCountKey;
import com.spark.psi.publish.inventory.key.GetInventoryLogKey;

/**
 * <p>
 * 模块间查询专用
 * </p>
 * 
 */

public class InventoryModuleQueryService extends Service {

	protected InventoryModuleQueryService() {
		super("com.spark.psi.inventory.service.pub.InventoryReportService");
	}

	/**
	 * 查询库存流水
	 * 
	 * @author 97
	 * 
	 */
	@Publish
	protected class GetInventoryLog extends OneKeyResultListProvider<InventoryLog, GetInventoryLogKey> {

		@Override
		protected void provide(Context context, GetInventoryLogKey key, List<InventoryLog> list) throws Throwable {

			StoStreamKey sKey = new StoStreamKey();
			sKey.setGoodsCategoryId(key.getGoodsCategoryId());
			sKey.setDateBegin(key.getDateBegin());
			sKey.setDateEnd(key.getDateEnd());
			sKey.setSearchText(key.getSearchText());
			sKey.setStoreId(key.getStoreId());
			sKey.setCount(key.getCount());
			sKey.setOffset(key.getOffset());
			sKey.setInventoryType(key.getInventoryType().getCode());
			sKey.setSortType(InventoryServiceUtil.getSortTypeString(key.getSortType()));
			sKey.setQueryTotal(key.isQueryTotal());

			List<InventoryLog> lList = context.getList(InventoryLog.class, sKey);
			if (null == lList) {
				return;
			}
			list.addAll(lList);

		}

	}

	/**
	 * 查询相关单据对应入库总情况(用于订单修改状态)
	 * 
	 * @author 97
	 * 
	 */
	@Publish
	protected class GetCheckingInstatus extends OneKeyResultProvider<RelationCheckInSheet, GetRelationCheckInSheetKey> {

		@Override
		protected RelationCheckInSheet provide(Context context, GetRelationCheckInSheetKey key) throws Throwable {
			RelationCheckInSheet relationCheckInSheet = context.find(RelationCheckInSheet.class, key.getRelationOrdId());
			return relationCheckInSheet;
		}

	}

	/**
	 * 查询相关单据对应出库总情况(用于订单修改状态)
	 * 
	 * @author 97
	 * 
	 */
	@Publish
	protected class GetCheckingOutstatus extends OneKeyResultProvider<RelationCheckOutSheet, GetRelationCheckOutSheetKey> {

		@Override
		protected RelationCheckOutSheet provide(Context context, GetRelationCheckOutSheetKey key) throws Throwable {
			RelationCheckOutSheet relationCheckOutSheet = context.find(RelationCheckOutSheet.class, key.getRelationOrderId());
			return relationCheckOutSheet;
		}

	}

	/**
	 * 查询出库单抵减明细
	 * 
	 * @author 97
	 * 
	 */
	@Publish
	protected class GetCheckingOutDeduction extends OneKeyResultProvider<CheckOutDeductionLog, GetCheckOutDeductionLogKey> {

		@Override
		protected CheckOutDeductionLog provide(Context context, GetCheckOutDeductionLogKey key) throws Throwable {
			if (null == key.getId()) {
				return null;
			}
			CheckOutDeductionLog log = context.find(CheckOutDeductionLog.class, key.getId());
			return log;

		}

	}

	/**
	 * 查询入库单抵减明细
	 * 
	 * @author 97
	 * 
	 */
	@Publish
	protected class GetCheckingInDeduction extends OneKeyResultProvider<CheckInDeductionLog, GetCheckInDeductionLogKey> {

		@Override
		protected CheckInDeductionLog provide(Context context, GetCheckInDeductionLogKey key) throws Throwable {
			if (null == key.getId()) {
				return null;
			}
			CheckInDeductionLog log = context.find(CheckInDeductionLog.class, key.getId());
			return log;

		}

	}

	// 根据记录id查询确认入库明细

	@Publish
	protected class GetSureInListByLogId extends OneKeyResultListProvider<SureInstorage, GetSureInstorageByLogIdKey> {

		@Override
		protected void provide(Context context, GetSureInstorageByLogIdKey key, List<SureInstorage> list) throws Throwable {
			// InventoryServiceUtil util = new InventoryServiceUtil(context);
			// StringBuilder dnaSql = new StringBuilder();
			// dnaSql.append("define query querySureInstorageList(@tenantsId guid,@checkInLogId guid)\n");
			// dnaSql.append("begin\n");
			// dnaSql.append(" select\n");
			//
			// dnaSql.append(getColums());
			// dnaSql.append(" from "+ERPTableNames.Inventory.CheckinSheet_Det.getTableName()+" as t\n");
			// dnaSql.append(" where t.tenantsGuid=@tenantsId\n");
			// dnaSql.append(" and t.checkInLogId=@checkInLogId\n");
			// dnaSql.append("end");
			//
			// DBCommand db = context.prepareStatement(dnaSql);
			// GUID tenentsId;
			// if (null == key.getTenentsId()) {
			// tenentsId = context.find(Login.class).getTenantId();
			// } else {
			// tenentsId = key.getTenentsId();
			// }
			// db.setArgumentValues(tenentsId, key.getCheckInLogId());
			//
			// try {
			// RecordSet rs = db.executeQuery();
			// while (rs.next()) {
			// list.add(fillEntity(rs));
			// }
			// } finally {
			// db.unuse();
			// }
		}

		private SureInstorage fillEntity(RecordSet rs) {
			SureInstorage si = new SureInstorage();
			int index = 0;
			si.setRECID(rs.getFields().get(index++).getGUID());
			si.setTenantsGuid(rs.getFields().get(index++).getGUID());
			si.setOperType(rs.getFields().get(index++).getString());
			si.setInstoGuid(rs.getFields().get(index++).getGUID());
			si.setGoodsGuid(rs.getFields().get(index++).getGUID());
			si.setGoodsName(rs.getFields().get(index++).getString());
			si.setGoodsNamePY(rs.getFields().get(index++).getString());
			si.setGoodsAttr(rs.getFields().get(index++).getString());
			si.setGoodsScale(rs.getFields().get(index++).getInt());
			si.setSureInsto(rs.getFields().get(index++).getGUID());
			si.setSureInstoName(rs.getFields().get(index++).getString());
			si.setInstoDate(rs.getFields().get(index++).getDate());
			si.setInstoCount(rs.getFields().get(index++).getDouble());
			si.setPlanAmount(rs.getFields().get(index++).getDouble());
			si.setPayAmount(rs.getFields().get(index++).getDouble());
			si.setFinished(rs.getFields().get(index++).getBoolean());
			si.setCusproGuid(rs.getFields().get(index++).getGUID());
			si.setCusproName(rs.getFields().get(index++).getString());
			si.setCusproShortName(rs.getFields().get(index++).getString());
			si.setCusproNamePY(rs.getFields().get(index++).getString());
			si.setCheckInLogId(rs.getFields().get(index++).getGUID());
			return si;
		}

		private Object getColums() {
			StringBuilder sql = new StringBuilder();
			sql.append("t.RECID as RECID\n");
			sql.append(",t.tenantsGuid as tenantsGuid\n");
			sql.append(",t.operType as operType\n");
			sql.append(",t.instoGuid as instoGuid\n");
			sql.append(",t.goodsGuid as goodsGuid\n");
			sql.append(",t.goodsName as goodsName\n");
			sql.append(",t.goodsNamePY as goodsNamePY\n");
			sql.append(",t.goodsAttr as goodsAttr\n");
			sql.append(",t.goodsScale as goodsScale\n");
			sql.append(",t.sureInsto as sureInsto\n");
			sql.append(",t.sureInstoName as sureInstoName\n");
			sql.append(",t.instoDate as instoDate\n");
			sql.append(",t.instoCount as instoCount\n");
			sql.append(",t.planAmount as planAmount\n");
			sql.append(",t.payAmount as payAmount\n");
			sql.append(",t.isFinished as isFinished\n");
			sql.append(",t.cusproGuid as cusproGuid\n");
			sql.append(",t.cusproName as cusproName\n");
			sql.append(",t.cusproShortName as cusproShortName\n");
			sql.append(",t.cusproNamePY as cusproNamePY\n");
			sql.append(",t.checkInLogId as checkInLogId\n");
			return sql;

		}

	}
 
	// 查询总库存信息

	@Publish
	protected class GoodsInventorySumProvider extends OneKeyResultProvider<GoodsInventorySum, GoodsInventorySumKey> {
		@Override
		protected GoodsInventorySum provide(Context context, GoodsInventorySumKey key) throws Throwable {
			// InventoryServiceUtil util = new InventoryServiceUtil(context);
			GoodsInventorySum sum = null;

			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();
			dnaSql.append("define query queryGoodsInventorySum(");
			conditionSql.append(" where 1=1\n");
			
			if (CheckIsNull.isNotEmpty(key.getGoodsItemId())) {
				dnaSql.append("@stockId guid");
				conditionSql.append(" and t.stockId=@stockId\n");
				paramList.add(key.getGoodsItemId());
			}
			dnaSql.append(")\n");
			dnaSql.append("begin\n");
			dnaSql
					.append("select sum(t.\"count\") as totalCount,sum(t.amount) as totalAmount,sum(t.toDeliverCount) as totalToDeliverCount\n");
			dnaSql.append("from \n");
			dnaSql.append(ERPTableNames.Inventory.Inventory.getTableName() +" as t " );
			dnaSql.append(conditionSql);
			dnaSql.append("end");
			DBCommand db = context.prepareStatement(dnaSql);
			for (int index = 0; index < paramList.size(); index++) {
				db.setArgumentValue(index, paramList.get(index));
			}
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					sum = new GoodsInventorySum();
					int index = 0;
					sum.setTotalCount(rs.getFields().get(index++).getDouble());
					sum.setTotalAmount(rs.getFields().get(index++).getDouble());
					sum.setTotalToDeliverCount(rs.getFields().get(index++).getDouble());
				}
			} finally {
				db.unuse();
			}
			return sum;
		}

	}

	// 查询超账期天数
	@Publish
	protected class GetCustomerOverCreditDay extends OneKeyResultProvider<Integer, GetCustomerOverCreditDayKey> {

		@Override
		protected Integer provide(Context context, GetCustomerOverCreditDayKey key) throws Throwable {

			Integer overCreditDay = null;
			// InventoryServiceUtil util = new InventoryServiceUtil(context);
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();

			dnaSql
					.append("define query queryOverPeriodAmount(@tenantsId guid,@partnerId guid,@accountPeriod int,@nowTime date)\n");
			conditionSql.append(" where t.tenantsGuid=@tenantsId \n");
			conditionSql.append(" and t.cusproGuid=@partnerId \n");
			conditionSql.append(" and t.isFinished=false \n");
			paramList.add(context.find(Login.class).getTenantId());
			paramList.add(key.getCustomerId());
			CustomerInfo partner = context.find(CustomerInfo.class, key.getCustomerId());
			if (CheckIsNull.isEmpty(partner)) {
				return null;
			}
			paramList.add(partner.getAccountPeriod());
			paramList.add(System.currentTimeMillis());

			dnaSql.append("begin\n");
			if (CheckIsNull.isEmpty(getColumsAndTable(context, key, conditionSql, partner))) {
				return null;
			}
			dnaSql.append(getColumsAndTable(context, key, conditionSql, partner));
			dnaSql.append(conditionSql);
			dnaSql.append("\nend");

			DBCommand db = context.prepareStatement(dnaSql);
			for (int index = 0; index < paramList.size(); index++) {
				db.setArgumentValue(index, paramList.get(index));
			}
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					overCreditDay = rs.getFields().get(0).getInt();

				}
			} finally {
				db.unuse();
			}
			return overCreditDay;
		}

		private Object getColumsAndTable(Context context, GetCustomerOverCreditDayKey key, StringBuilder conditionSql,
				PartnerInfo partner) {

			StringBuilder sql = new StringBuilder();
			if (CheckIsNull.isEmpty(partner)) {
				return null;
			}
			if (partner instanceof CustomerInfo) {
				sql.append("SELECT max(daydiff(t.outstoDate,@nowTime)-@accountPeriod) as overPeriodAmount\n");
				sql.append("from SA_STORE_SUREOUTSTO as t \n");
				conditionSql.append(" and daydiff(t.outstoDate,@nowTime)>@accountPeriod \n");
			} else {
				sql.append("SELECT max(daydiff(t.instoDate,@nowTime)-@accountPeriod) as overPeriodAmount\n");
				sql.append("from " + ERPTableNames.Inventory.CheckinSheet_Det.getTableName() + " as t \n");
				conditionSql.append(" and daydiff(t.instoDate,@nowTime)>@accountPeriod \n");
			}
			return sql;

		}

	}

	// 查询最近一条未付款出库单日期
	@Publish
	protected class GetLastestSheetDate extends OneKeyResultProvider<Long, GetRemindDateByPartnerIdKey> {

		@Override
		protected Long provide(Context context, GetRemindDateByPartnerIdKey key) throws Throwable {

			Long lastestSheetDate = null;
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();

			dnaSql
					.append("define query queryOverPeriodAmount(@tenantsId guid,@partnerId guid,@accountPeriod int,@nowTime date)\n");
			conditionSql.append(" where t.tenantsGuid=@tenantsId \n");
			conditionSql.append(" and t.cusproGuid=@partnerId \n");
			conditionSql.append(" and t.isFinished=false \n");
			paramList.add(context.find(Login.class).getTenantId());
			paramList.add(key.getCustomerId());
			CustomerInfo partner = context.find(CustomerInfo.class, key.getCustomerId());
			if (CheckIsNull.isEmpty(partner)) {
				return null;
			}
			paramList.add(partner.getAccountPeriod());
			paramList.add(System.currentTimeMillis());

			dnaSql.append("begin\n");
			if (CheckIsNull.isEmpty(getColumsAndTable(context, key, conditionSql, partner))) {
				return null;
			}
			dnaSql.append(getColumsAndTable(context, key, conditionSql, partner));
			dnaSql.append(conditionSql);
			dnaSql.append("\nend");

			DBCommand db = context.prepareStatement(dnaSql);
			for (int index = 0; index < paramList.size(); index++) {
				db.setArgumentValue(index, paramList.get(index));
			}
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					lastestSheetDate = rs.getFields().get(0).getLong();
				}
			} finally {
				db.unuse();
			}
			return lastestSheetDate;

		}

		private Object getColumsAndTable(Context context, GetRemindDateByPartnerIdKey key, StringBuilder conditionSql,
				PartnerInfo partner) {

			StringBuilder sql = new StringBuilder();
			if (CheckIsNull.isEmpty(partner)) {
				return null;
			}
			if (partner instanceof CustomerInfo) {
				sql.append("SELECT max(t.outstoDate) as lastestSheetDate\n");
				sql.append("from SA_STORE_SUREOUTSTO as t \n");
			} else {
				sql.append("SELECT max(t.instoDate) as lastestSheetDate\n");
				sql.append("from " + ERPTableNames.Inventory.CheckinSheet_Det.getTableName() + " as t \n");
			}
			return sql;

		}

	}

	// 查询超账期金额

	@Publish
	protected class GetOverPeriodAmount extends OneKeyResultProvider<Double, OverPeriodAmountKey> {

		@Override
		protected Double provide(Context context, OverPeriodAmountKey key) throws Throwable {
			Double overPeriodAmount = null;
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();
			dnaSql.append("define query queryOverPeriodAmount( @partnerId guid,@accountPeriod int,@nowTime date\n");
			conditionSql.append(" where t.partnerId=@partnerId \n");
			conditionSql.append(" and t.amount>t.receiptedAmount \n");
			paramList.add(key.getPartnerId());
			paramList.add(key.getAccountPeriod());
			paramList.add(System.currentTimeMillis());
			if (!key.isQueryAll()) {
				Department dept = context.find(Department.class, context.find(Employee.class,
						context.find(Login.class).getEmployeeId()).getDepartmentId());
				if (null != dept) {
					List<GUID> departmentIds = new ArrayList<GUID>();
					departmentIds.add(dept.getId());
					conditionSql.append("and s.deptId in(\n");
					for (int i = 0; i < dept.getDescendants(context).length; i++) {
						dnaSql.append(",@deptId").append(i).append(" guid");
						paramList.add(dept.getDescendants(context)[i]);
						if (0 != i) {
							conditionSql.append(",");
						}
						conditionSql.append("@deptId").append(i);
					}
					conditionSql.append(")\n");
				}
			}

			dnaSql.append(")\n");
			dnaSql.append("begin\n");
			if (CheckIsNull.isEmpty(getColumsAndTable(context, key, conditionSql))) {
				return null;
			}
			dnaSql.append(getColumsAndTable(context, key, conditionSql));
			dnaSql.append(conditionSql);
			dnaSql.append("\nend");

			DBCommand db = context.prepareStatement(dnaSql);
			for (int index = 0; index < paramList.size(); index++) {
				db.setArgumentValue(index, paramList.get(index));
			}
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					overPeriodAmount = rs.getFields().get(0).getDouble();
				}
			} finally {
				db.unuse();
			}
			return overPeriodAmount;
		}

		private Object getColumsAndTable(Context context, OverPeriodAmountKey key, StringBuilder conditionSql) {
			StringBuilder sql = new StringBuilder();
			Partner partner = context.find(Partner.class, key.getPartnerId());
			if (CheckIsNull.isEmpty(partner)) {
				return null;
			}
			if (PartnerType.Customer.equals(partner.getPartnerType())) {
				sql.append("SELECT sum(t.amount)-sum(t.receiptedAmount) as overPeriodAmount\n");
				sql.append("from "+ERPTableNames.Inventory.CheckoutSheet.getTableName()+" as t \n");
				if (key.isQueryAll()) {
					conditionSql.append(" and (daydiff(t.checkoutDate,@nowTime)>@accountPeriod) \n");
				} else {
					sql.append(" join "+ERPTableNames.Inventory.CheckoutSheet.getTableName()+" as s \n");
					sql.append(" on t.billsId=s.recid \n");
				}
			} else {
				sql.append("SELECT sum(t.planAmount)-sum(t.payAmount) as overPeriodAmount\n");
				sql.append("from " + ERPTableNames.Inventory.CheckinSheet_Det.getTableName() + " as t \n");
				if (key.isQueryAll()) {
					conditionSql.append(" and (daydiff(t.checkinDate,@nowTime)>@accountPeriod) \n");
				} else {
					sql.append(" join " + ERPTableNames.Inventory.CheckinSheet.getTableName() + " as s \n");
					sql.append(" on t.billsIds=s.recid \n");
				}
			}
			return sql;
		}

	}

	// 查询预警金额
	@Publish
	protected class GetRemindingAmount extends OneKeyResultProvider<Double, RemindingAmountKey> {

		@Override
		protected Double provide(Context context, RemindingAmountKey key) throws Throwable {
			// 如果预警天数为0，则没有预警金额
			if (key.getRemindingDay() == 0)
				return 0.0;

			Double overPeriodAmount = null;
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder selectSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();

			dnaSql.append("define query queryOverPeriodAmount(@tenantsId guid,@partnerId guid,@beginDate date,@endDate date)\n");
			conditionSql.append(" where t.partnerId=@partnerId \n");
			if (null == key.getTenantId()) {
				paramList.add(context.find(Login.class).getTenantId());
			} else {
				paramList.add(key.getTenantId());
			}
			paramList.add(key.getPartnerId());

			// （单据时间 + 账期） > 当前时间（取最大时间） 新需求去掉"="号
			// （单据时间 + 账期 - 预警天数）最小时间，即这一天开始 < 当前时间(取最小时间)
			// begin:当前系统时间 - 账期天数
			// end:当前系统时间 - （ 账期天数 - 账期提醒天数） (取最小时间)
			long beginDate = DateUtil.getDayStartTime(new Date().getTime() + DateUtil.ONE_DATE_TIME) - key.getAccountPeriod()
					* DateUtil.ONE_DATE_TIME;
			long endDate = DateUtil.getDayEndTime(new Date().getTime()) - (key.getAccountPeriod() - key.getRemindingDay())
					* DateUtil.ONE_DATE_TIME;
			// beginDate = DateUtil.getDayEndTime(beginDate);
			// endDate = DateUtil.getDayStartTime(endDate);

			paramList.add(beginDate);
			paramList.add(endDate);

			dnaSql.append("begin\n");
			selectSql.append(getColumsAndTable(context, key, conditionSql));
			if (CheckIsNull.isEmpty(selectSql)) {
				return null;
			}
			dnaSql.append(selectSql);
			dnaSql.append(conditionSql);
			dnaSql.append("\nend");

			DBCommand db = context.prepareStatement(dnaSql);
			for (int index = 0; index < paramList.size(); index++) {
				db.setArgumentValue(index, paramList.get(index));
			}
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					overPeriodAmount = rs.getFields().get(0).getDouble();

				}
			} finally {
				db.unuse();
			}
			return overPeriodAmount;
		}

		private Object getColumsAndTable(Context context, RemindingAmountKey key, StringBuilder conditionSql) {
			StringBuilder sql = new StringBuilder();
			Partner partner = context.find(Partner.class, key.getPartnerId());
			if (CheckIsNull.isEmpty(partner)) {
				return null;
			}
			if (PartnerType.Customer.equals(partner.getPartnerType())) {
				sql.append("SELECT sum(t.amount)-sum(t.receiptedAmount) as overPeriodAmount\n");
				sql.append("from "+ERPTableNames.Inventory.CheckoutSheet.getTableName()+" as t \n");
				conditionSql.append(" and t.amount>t.receiptedAmount \n");
				conditionSql.append(" and @beginDate < t.checkoutDate \n");
				conditionSql.append(" and @endDate > t.checkoutDate  \n");

			} else {
				// 当前系统时间 - 账期天数 < 入库时间
				// 当前系统时间 - （ 账期天数 - 账期提醒天数） > 入库时间
				sql.append("SELECT sum(t.amount)-sum(t.paidAmount) as overPeriodAmount\n");
				sql.append("from " + ERPTableNames.Inventory.CheckinSheet.getTableName() + " as t \n");
				conditionSql.append(" and t.amount>t.paidAmount \n");
				conditionSql.append(" and @beginDate < t.checkinDate \n");
				conditionSql.append(" and @endDate > t.checkinDate  \n");

			}
			return sql;
		}

	}

	/**
	 * 查询商品可用库存
	 */

	@Publish
	protected class GetAvailableCount extends OneKeyResultProvider<Double, GetAvailableCountKey> {

		@Override
		protected Double provide(Context context, GetAvailableCountKey key) throws Throwable {
			if (null == key.getStoreId() || null == key.getGoodsItemId()) {
				return null;
			}
			ResourceToken<Inventory> token = context.findResourceToken(Inventory.class, key.getStoreId(), key.getGoodsItemId());
			if (null != token && null != token.getFacade()) {
				double availableCount = DoubleUtil.sub(token.getFacade().getCount(), DoubleUtil.sum(token.getFacade()
						.getToDeliverCount(), token.getFacade().getLockedCount()));
//				GetPurchaseOrderGoodsCountByGoodsIdKey oKey = new GetPurchaseOrderGoodsCountByGoodsIdKey(key.getGoodsItemId(),
//						key.getStoreId());
//				Double orderCount = context.find(Double.class, oKey);
//				if (null != orderCount) {
//					availableCount = DoubleUtil.sub(availableCount, orderCount);
//				}
				return availableCount;
			}
			return null;
		}
	}

	/**
	 * 查询逾期没到货金额
	 *<p>
	 * 数值统计范围：
	 * 交货日期早于当前系统日期（不含当天），处理状态为“未入库”的采购订单上的订单金额的累计值+处理状态为“部分入库”的采购订单上的未入库金额（订单金额
	 * -已入库金额）的累计值。 不同角色的统计范围： 总经理、采购经理：公司内的所有采购订单。
	 * </p>
	 */
	@Publish
	protected class GetOverdueAmount extends OneKeyResultProvider<Double, GetExceedTimeLimitNotDeliveredAmountKey> {

		@Override
		protected Double provide(Context context, GetExceedTimeLimitNotDeliveredAmountKey key) throws Throwable {
			GUID tenantId = context.find(Login.class).getTenantId();
			StringBuffer dnaSql = new StringBuffer();
			dnaSql.append("define query qyeryExceedAmount(@tenantId guid,@instoType string,@today date)\n");
			dnaSql.append("begin\n");
			dnaSql.append("SELECT t.recid as id,t.orderTotalAmount as totalAmount\n");
			dnaSql.append("FROM " + ERPTableNames.Inventory.CheckinSheet.getTableName() + " AS t\n");
			dnaSql.append("where t.tenantsGuid=@tenantId\n");
			dnaSql.append("and t.instotype=@instoType\n");
			dnaSql.append("and t.planInstoDate<@today\n");
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(tenantId, CheckingInType.Purchase.getCode(), DateUtil.truncDay(new Date().getTime()));
			try {
				RecordSet rs = db.executeQuery();

				StringBuffer sql = new StringBuffer();
				sql.append("define query qyerySumAmount(@tenantId guid,@instoGuid guid)\n");
				sql.append("begin\n");
				sql.append("SELECT SUM(s.planamount) AS amount\n");
				sql.append("FROM " + ERPTableNames.Inventory.CheckinSheet_Det.getTableName() + " AS s\n");
				sql.append("where s.tenantsGuid=@tenantId\n");
				sql.append("and s.instoGuid=@instoGuid\n");
				sql.append("end");
				DBCommand dbc = context.prepareStatement(sql);
				double totalAmount = 0;
				while (rs.next()) {
					GUID id = rs.getFields().get(0).getGUID();
					double orderTotalAmount = rs.getFields().get(1).getDouble();
					dbc.setArgumentValues(tenantId, id);
					RecordSet rs1 = dbc.executeQuery();
					while (rs1.next()) {
						orderTotalAmount = DoubleUtil.sub(orderTotalAmount, rs1.getFields().get(0).getDouble());
					}
					totalAmount = DoubleUtil.sum(totalAmount, orderTotalAmount);
				}
				return totalAmount;
			} finally {
				db.unuse();
			}
		}

	}
}
