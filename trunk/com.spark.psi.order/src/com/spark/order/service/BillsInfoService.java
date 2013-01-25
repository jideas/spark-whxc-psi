package com.spark.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.entity.PageEntity;
import com.spark.order.intf.entity.SearchStatusEntity;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.key.SelectMainKey;
import com.spark.order.intf.key.SelectOrderItemGoodsTotalCountKey;
import com.spark.order.intf.task.BillsExamineChangeTask;
import com.spark.order.intf.task.FlowTask;
import com.spark.order.intf.task.MainTabDataValiTask;
import com.spark.order.intf.task.RebutTask;
import com.spark.order.intf.task.StopTask;
import com.spark.order.intf.task.TotalAmountTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.PageEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.key.SelectPurchaseSubRebutKey;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.sales.intf.key.SelectSaleSubRebutKey;
import com.spark.order.service.dao.sql.ISimpleTaskSql;
import com.spark.order.service.dao.sql.impl.modify.BillsExamChangeSql;
import com.spark.order.service.dao.sql.impl.modify.FlowStatusSql;
import com.spark.order.service.dao.sql.impl.modify.RebutSql;
import com.spark.order.service.dao.sql.impl.modify.StopSql;
import com.spark.order.service.util.OrderUtil;
import com.spark.order.util.dnaSql.IDnaSql;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;

public class BillsInfoService extends Service {

	protected BillsInfoService() {
		super("com.spark.bills.service.BillsInfoService");
	}

	/**
	 * <p>
	 * 主列表数据加载
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-11
	 */
	@Publish
	protected class BillsMainProvide extends OneKeyResultProvider<PageEntity<Object>, SelectMainKey> {

		@SuppressWarnings("unchecked")
		@Override
		protected PageEntity<Object> provide(Context context, SelectMainKey key) throws Throwable {
			if (PageEnum.NEW_CANCEL.equals(key.getTabEnum())) {
				if (BillsEnum.PURCHASE_CANCEL == key.getBillsEnum()) {
					List<PurchaseCancel> cancels = context.getList(PurchaseCancel.class, key);
					return new PageEntity(cancels, cancels.size());
				} else {
					List<SaleCancel> cancels = context.getList(SaleCancel.class, key);
					return new PageEntity(cancels, cancels.size());
				}
			}
			if (PageEnum.NEW.equals(key.getTabEnum())) {
				if (BillsEnum.PURCHASE == key.getBillsEnum()) {
					List<PurchaseOrderInfo> buys = null;// context.getList(BuyOrdInfo.class);
					buys = context.getList(PurchaseOrderInfo.class, new SelectPurchaseSubRebutKey(key));
					return new PageEntity(buys, buys.size());
				} else {
					List<SaleOrderInfo> sales = context.getList(SaleOrderInfo.class, new SelectSaleSubRebutKey(key));
					return new PageEntity(sales, sales.size());
				}
			} else {
				List<OrderInfo> bills = context.getList(OrderInfo.class, key);
				PageEntity pageEntity = new PageEntity(bills, bills.size());
				if (PageEnum.FOLLOW.equals(key.getTabEnum()) || PageEnum.LOG.equals(key.getTabEnum())) {
					TotalAmountTask task = new TotalAmountTask();
					task.setKey(key);
					context.handle(task);
					pageEntity.setBillsCount(task.getTotalCount());
					pageEntity.setOrderAmount(task.getTotalOrderAmount());
					pageEntity.setCancelAmount(task.getTotalCancelAmount());
				}
				return pageEntity;
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 最近单据日期查询
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 王天才
	 * @version 2011-11-15
	 */
	@Publish
	protected class LastCreateDateProvider extends OneKeyResultProvider<Long, SelectMainKey> {

		@Override
		protected Long provide(Context context, SelectMainKey key) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			StringBuilder saleExamSql = new StringBuilder();// 仅仅在销售订单审批时候使用
			List<Object> paramValueList = new ArrayList<Object>();
			dnaSql.append("define query QueryMainList(\n");
			if (BillsEnum.SALE == key.getBillsEnum() && (PageEnum.EXAMINE == key.getTabEnum()
			// || PageEnum.FOLLOW == key.getTabEnum()
					)) {
				addSaleParamAndCondtion(context, key, saleExamSql);
			}
			addParamAndCondtion(context, key, dnaSql, conditionSql, paramValueList);
			dnaSql.append(") begin \n");

			dnaSql.append(" select t.createDate as createDate ");

			dnaSql.append(" from( ");

			dnaSql.append(" select t.createDate as createDate ");

			if (BillsEnum.PURCHASE == key.getBillsEnum()) {
				addFrom(dnaSql, BillsEnum.PURCHASE_CANCEL.getDb_table());
			} else {
				addFrom(dnaSql, BillsEnum.SALE_CANCEL.getDb_table());
			}

			dnaSql.append(conditionSql);
			// 排序
			// 分页
			dnaSql.append(" union all ");

			dnaSql.append(" select t.createDate as createDate ");

			addFrom(dnaSql, key.getBillsEnum().getDb_table());
			if (BillsEnum.SALE == key.getBillsEnum() && (PageEnum.EXAMINE == key.getTabEnum()
			// || PageEnum.FOLLOW == key.getTabEnum()
					)) {
				dnaSql.append(saleExamSql);
			} else {
				dnaSql.append(conditionSql);
			}
			// 排序
			// 分页
			dnaSql.append(" ) as t ");
			// 排序
			dnaSql.append(" order by t.createDate desc ");

			dnaSql.append(" end");
			DBCommand db = context.prepareStatement(dnaSql);

			for (int i = 0; i < paramValueList.size(); i++) {
				db.setArgumentValue(i, paramValueList.get(i));
			}

			try {
				RecordSet rs = db.executeQueryLimit(0, 1);
				while (rs.next()) {
					return rs.getFields().get(0).getDate();
				}
			} finally {
				db.unuse();
			}
			return null;
		}

	}

	/**
	 * 
	 * <p>
	 * 主列表审批、跟踪、记录页签数据查询
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 王天才
	 * @version 2011-11-15
	 */
	@Publish
	protected class BillsInfoListProvider extends OneKeyResultListProvider<OrderInfo, SelectMainKey> {

		@Override
		protected void provide(Context context, SelectMainKey key, List<OrderInfo> list) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			StringBuilder saleExamSql = new StringBuilder();// 仅仅在销售订单审批时候使用
			List<Object> paramValueList = new ArrayList<Object>();
			dnaSql.append("define query QueryMainList(\n");
			if (BillsEnum.SALE == key.getBillsEnum() && (PageEnum.EXAMINE == key.getTabEnum())) {
				addSaleParamAndCondtion(context, key, saleExamSql);
			}
			addParamAndCondtion(context, key, dnaSql, conditionSql, paramValueList);
			
			dnaSql.append(") begin \n");

			addSelect(dnaSql, key.getBillsEnum());
			dnaSql.append(" from( ");
			addSelect(dnaSql, key.getBillsEnum());
			if (BillsEnum.PURCHASE == key.getBillsEnum()) {
				addFrom(dnaSql, BillsEnum.PURCHASE_CANCEL.getDb_table());
			} else {
				addFrom(dnaSql, BillsEnum.SALE_CANCEL.getDb_table());
			}

			dnaSql.append(conditionSql);
			// 排序
			// 分页
			dnaSql.append(" union all ");
			addSelect(dnaSql, key.getBillsEnum());
			addFrom(dnaSql, key.getBillsEnum().getDb_table());
			if (BillsEnum.SALE == key.getBillsEnum() && (PageEnum.EXAMINE == key.getTabEnum())) {
				dnaSql.append(saleExamSql);
			} else {
				dnaSql.append(conditionSql);
			}
			// 排序
			// 分页
			dnaSql.append(" ) as t ");
			// 排序
			dnaSql.append(" order by ");
			dnaSql.append(key.getSortField());
			dnaSql.append("  ");
			dnaSql.append(key.getSortType());
			dnaSql.append(" end");
 			DBCommand db = context.prepareStatement(dnaSql);

			for (int i = 0; i < paramValueList.size(); i++) {
				db.setArgumentValue(i, paramValueList.get(i));
			}

			try {
				RecordSet rs = db.executeQueryLimit(key.getOffset(), 100);
				while (rs.next()) {
					list.add(setData(rs, key.getBillsEnum()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.unuse();
			}

		}
	}

	/**
	 * <p>
	 * 校验当前页签是否有数据
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-21
	 */
	@Publish
	protected class MainTabDataValiHandle extends SimpleTaskMethodHandler<MainTabDataValiTask> {

		@SuppressWarnings("unused")
		@Override
		protected void handle(Context context, MainTabDataValiTask task) throws Throwable {

			SelectMainKey key = task.getKey();
			StringBuilder cancelSql = new StringBuilder();
			StringBuilder cancelParamDefineSql = new StringBuilder();
			StringBuilder orderSql = new StringBuilder();
			StringBuilder cancelConditionSql = new StringBuilder();
			StringBuilder orderConditionSql = new StringBuilder();
			int totalCount = 0;
			double totalCancelAmount = 0;
			double totalOrderAmount = 0;
			List<Object> paramValueList = new ArrayList<Object>();

			cancelSql.append("define query QueryCancelTotal(\n");
			addParamAndCondtion(context, key, cancelParamDefineSql, cancelConditionSql, paramValueList);
			cancelSql.append(cancelParamDefineSql);
			cancelSql.append(") begin \n");

			cancelSql.append(" select count(t.recid) as c,sum(t.totalAmount) as totalAmount \n");
			if (BillsEnum.PURCHASE == key.getBillsEnum()) {
				addFrom(cancelSql, BillsEnum.PURCHASE_CANCEL.getDb_table());
			} else {
				addFrom(cancelSql, BillsEnum.SALE_CANCEL.getDb_table());
			}
			cancelSql.append(cancelConditionSql);

			cancelSql.append(" end");

			orderSql.append("define query QueryOrderTotal(\n");

			StringBuilder saleExamSql = new StringBuilder();// 仅仅在销售订单审批时候使用
			if (BillsEnum.SALE == key.getBillsEnum()
					&& (PageEnum.EXAMINE == key.getTabEnum() || PageEnum.FOLLOW == key.getTabEnum())) {
				orderSql.append(cancelParamDefineSql);
				addSaleParamAndCondtion(context, key, saleExamSql);
			} else {
				paramValueList.clear();
				addParamAndCondtion(context, key, orderSql, orderConditionSql, paramValueList);
			}

			orderSql.append(") begin \n");

			orderSql.append(" select count(t.recid) as c,sum(t.totalAmount) as totalAmount \n");
			addFrom(orderSql, key.getBillsEnum().getDb_table());
			if (BillsEnum.SALE == key.getBillsEnum()
					&& (PageEnum.EXAMINE == key.getTabEnum() || PageEnum.FOLLOW == key.getTabEnum())) {
				orderSql.append(saleExamSql);
			} else {
				orderSql.append(orderConditionSql);
			}

			orderSql.append(" end");

			DBCommand db = context.prepareStatement(cancelSql);
			DBCommand db1 = context.prepareStatement(orderSql);

			for (int i = 0; i < paramValueList.size(); i++) {
				db.setArgumentValue(i, paramValueList.get(i));
				db1.setArgumentValue(i, paramValueList.get(i));
			}

			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					totalCount = rs.getFields().get(0).getInt();
				}
				RecordSet rs1 = db1.executeQuery();
				while (rs1.next()) {
					totalCount += rs1.getFields().get(0).getInt();
				}
				// task.setTotalCount(totalCount);
				// task.setTotalCancelAmount(totalCancelAmount);
				// task.setTotalOrderAmount(totalOrderAmount);
				task.setLenght(totalCount);
				if (totalCount > 0) {
					task.setHaveData(true);
				}
			} finally {
				db.unuse();
				db1.unuse();
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 获取主列表数据各项统计值
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 王天才
	 * @version 2011-11-15
	 */
	@Publish
	protected class TotalAmount extends SimpleTaskMethodHandler<TotalAmountTask> {

		@Override
		protected void handle(Context context, TotalAmountTask task) throws Throwable {
			SelectMainKey key = task.getKey();
			StringBuilder cancelSql = new StringBuilder();
			StringBuilder cancelParamDefineSql = new StringBuilder();
			StringBuilder orderSql = new StringBuilder();
			StringBuilder cancelConditionSql = new StringBuilder();
			StringBuilder orderConditionSql = new StringBuilder();
			int totalCount = 0;
			double totalCancelAmount = 0;
			double totalOrderAmount = 0;
			List<Object> paramValueList = new ArrayList<Object>();

			cancelSql.append("define query QueryCancelTotal(\n");
			addParamAndCondtion(context, key, cancelParamDefineSql, cancelConditionSql, paramValueList);
			cancelSql.append(cancelParamDefineSql);
			cancelSql.append(") begin \n");

			cancelSql.append(" select count(t.recid) as c,sum(t.totalAmount) as totalAmount \n");
			if (BillsEnum.PURCHASE == key.getBillsEnum()) {
				addFrom(cancelSql, BillsEnum.PURCHASE_CANCEL.getDb_table());
			} else {
				addFrom(cancelSql, BillsEnum.SALE_CANCEL.getDb_table());
			}
			cancelSql.append(cancelConditionSql);

			cancelSql.append(" end");

			orderSql.append("define query QueryOrderTotal(\n");

			StringBuilder saleExamSql = new StringBuilder();// 仅仅在销售订单审批时候使用
			if (BillsEnum.SALE == key.getBillsEnum() && (PageEnum.EXAMINE == key.getTabEnum()
			// || PageEnum.FOLLOW == key.getTabEnum()
					)) {
				orderSql.append(cancelParamDefineSql);
				addSaleParamAndCondtion(context, key, saleExamSql);
			} else {
				paramValueList.clear();
				addParamAndCondtion(context, key, orderSql, orderConditionSql, paramValueList);
			}

			orderSql.append(") begin \n");

			orderSql.append(" select count(t.recid) as c,sum(t.totalAmount) as totalAmount \n");
			addFrom(orderSql, key.getBillsEnum().getDb_table());
			if (BillsEnum.SALE == key.getBillsEnum() && (PageEnum.EXAMINE == key.getTabEnum()
			// || PageEnum.FOLLOW == key.getTabEnum()
					)) {
				orderSql.append(saleExamSql);
			} else {
				orderSql.append(orderConditionSql);
			}

			orderSql.append(" end");

			DBCommand db = context.prepareStatement(cancelSql);
			DBCommand db1 = context.prepareStatement(orderSql);

			for (int i = 0; i < paramValueList.size(); i++) {
				db.setArgumentValue(i, paramValueList.get(i));
				db1.setArgumentValue(i, paramValueList.get(i));
			}

			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					totalCount = rs.getFields().get(0).getInt();
					totalCancelAmount = rs.getFields().get(1).getDouble();
				}
				RecordSet rs1 = db1.executeQuery();
				while (rs1.next()) {
					totalCount += rs1.getFields().get(0).getInt();
					totalOrderAmount = rs1.getFields().get(1).getDouble();
				}
				task.setTotalCount(totalCount);
				task.setTotalCancelAmount(totalCancelAmount);
				task.setTotalOrderAmount(totalOrderAmount);
			} finally {
				db.unuse();
				db1.unuse();
			}

		}

	}

	// ----------------------------------更新-------------------------------
	/**
	 * <p>
	 * 订单状态更新
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-11
	 */
	@Publish
	protected class ModifystatusTask extends SimpleTaskMethodHandler<FlowTask> {

		@Override
		protected void handle(Context context, FlowTask task) throws Throwable {
			ISimpleTaskSql sql = new FlowStatusSql(context);
			DBCommand db = sql.getDB(task);
			sql.getSql(task);
			String examin = null;
			db.setArgumentValues(task.billsRECID, task.newstatus, task.oldstatus, task.getDeptGuid(), new Date(), task
					.getExamDept(), examin, 0l);
			task.setLenght(db.executeUpdate());
		}
	}

	/**
	 * <p>
	 * 订单状态更新(审批)
	 * </p> 
	 */
	@Publish
	protected class ModifyExamineCauseTask extends SimpleTaskMethodHandler<RebutTask> {

		@Override
		protected void handle(Context context, RebutTask task) throws Throwable {
			ISimpleTaskSql sql = new RebutSql(context);
			DBCommand db = sql.getDB(task);
			// @recid guid, @status guid, @oldstatus guid, @cause string @examin
			// string
			String examine;
			examine = OrderUtil.spell(BillsConstant.getUserName(context), new Date().getTime());
			if (BillsEnum.SALE == task.billsEnum && null != task.info && !StatusEnum.Return.getKey().equals(task.oldstatus)) {
				examine = examine + (task.info.getApproveStr() == null ? "" : ";" + task.info.getApproveStr());
			}
			db.setArgumentValues(task.billsRECID, task.newstatus, task.oldstatus, task.cause, examine);
			task.setLenght(db.executeUpdate());
			// // 写时间预警消息提醒
			// OrderUtil.modifyDateMsg(context, task.billsRECID,
			// task.billsEnum);
		}
	}

	/**
	 * <p>
	 * 订单状态更新（中止）
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-11
	 */
	@Publish
	protected class ModifyStopCauseTask extends SimpleTaskMethodHandler<StopTask> {

		@Override
		protected void handle(Context context, StopTask task) throws Throwable {
			ISimpleTaskSql sql = new StopSql(context);
			DBCommand db = sql.getDB(task);
			// return
			// "@recid guid, @stop boolean, @oldstatus guid, @cause string";
			db.setArgumentValues(task.billsRECID, task.isStoped, task.oldstatus, task.cause);
			task.setLenght(db.executeUpdate());
			// // 写时间预警消息提醒
			// OrderUtil.modifyDateMsg(context, task.billsRECID,
			// task.billsEnum);
		}
	}

	/**
	 * 销售订单审核流程专用
	 * 
	 * @param context
	 * @param key
	 * @param dnaSql
	 */
	public void addSaleParamAndCondtion(Context context, SelectMainKey key, StringBuilder conditionSql) {

		Employee user = BillsConstant.getUser(context);
		GUID authdeptId = user.getDepartmentId();
		List<GUID> subdeptIdList = getSubdeptIdList(context, authdeptId);
		List<GUID> deptIdList = null;
		conditionSql.append(" where 1=1 ");
		/**
		 * 审批
		 */
		if (PageEnum.EXAMINE.equals(key.getTabEnum())) {
			conditionSql.append(" and t.status=@status\n");
			conditionSql.append(" and t.isStoped=false \n");
			// 审核部门为自己所在部门
		}
		/**
		 * 跟踪
		 */
		if (PageEnum.FOLLOW.equals(key.getTabEnum())) {
			if (null != key.getDeptGuid()) {
				deptIdList = getSubdeptIdList(context, key.getDeptGuid());
			}
			/**
			 * 采购员工或者我的单据
			 */
			if (key.isEmployee() || key.isMine()) {
				conditionSql.append(" and t.creatorId=@creatorId ");
				conditionSql.append(" and (t.status=@examstatus or \n");
			}
			/**
			 * 有权限的单据
			 */
			else {
				int index = 0;
				/**
				 * 指定部门
				 */
				if (null != deptIdList) {
					conditionSql.append(" and ");
					if (deptIdList.size() > 1) {
						conditionSql.append("(");
					}
					for (int i = 0; i < deptIdList.size(); i++) {
						if (i != 0) {
							conditionSql.append(" or ");
						}
						conditionSql.append(" t.deptId=@deptId" + index + " \n");
						index++;
					}

					if (deptIdList.size() > 1) {
						conditionSql.append(") ");
					}
				}
				conditionSql.append(" and ");
				/**
				 * 下属部门
				 */
				if (subdeptIdList.size() > 1) {
					conditionSql.append("(");
				}
				for (int i = 0; i < subdeptIdList.size(); i++) {
					if (i != 0) {
						conditionSql.append(" or ");
					}
					conditionSql.append(" t.deptId=@deptId" + index + " \n");
					index++;
				}
				if (subdeptIdList.size() > 1) {
					conditionSql.append(") ");
				}
				conditionSql.append(" and (");
			}

			conditionSql.append(" t.status=@noStoreStatus\n");
			conditionSql.append(" or t.status=@partStoreStatus\n");
			/**
			 * 指定部门查询时，加上自己创建的待审批单据
			 */
			if (null != key.getDeptGuid() && !key.isMine()) {
				conditionSql.append(" or (t.creatorId=@creatorId ");
				conditionSql.append(" and t.status=@examstatus) \n");
			}
			conditionSql.append(" or t.status=@allStoreStatus) \n");
			conditionSql.append(" and t.isStoped=false \n");

			// 已审核过的下属单据
			if (!(key.isEmployee() || key.isMine())) {
				int index = 0;
				/**
				 * 指定部门
				 */
				if (null != deptIdList) {
					conditionSql.append(" or ");
					if (deptIdList.size() > 1) {
						conditionSql.append("(");
					}
					for (int i = 0; i < deptIdList.size(); i++) {
						if (i != 0) {
							conditionSql.append(" or ");
						}
						conditionSql.append(" ( t.examdeptId<>@deptId" + index + " ) \n");
						index++;
					}
					if (deptIdList.size() > 1) {
						conditionSql.append(") ");
					}
				}
				conditionSql.append(" or ");
				/**
				 * 下属部门
				 */
				if (subdeptIdList.size() > 1) {
					conditionSql.append("(");
				}
				for (int i = 0; i < subdeptIdList.size(); i++) {
					if (i != 0) {
						conditionSql.append(" and ");
					}
					conditionSql.append(" ( t.examdeptId<>@deptId" + index + " ) \n");
					index++;
				}
				if (subdeptIdList.size() > 1) {
					conditionSql.append(") ");
				}
				conditionSql.append(" and (");
				conditionSql.append(" t.status=@examstatus) \n");
			}

			conditionSql.append(" and t.isStoped=false \n");
			/**
			 * 日期区间
			 */
			if (key.getStartDate() != 0) {
				conditionSql.append(" and t.createDate>= @startDate");
				conditionSql.append(" \n");
			}
			if (null != key.getEndDate()) {
				conditionSql.append(" and t.createDate<= @endDate");
				conditionSql.append(" \n");
			}
		}
		// conditionSql.append(" ) ");
		/**
		 * 搜索
		 */
		if (null != key.getSearch()) {
			String searchText = key.getSearch();
			List<String> typeList = OrderUtil.getBillsType(key.getBillsEnum(), searchText);
			conditionSql.append(" and (t.billsNo like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");
			conditionSql.append(" or t.cuspName like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");
			conditionSql.append(" or t.cuspFullName like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");

			conditionSql.append(" or t.createPerson like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");

			for (String type : typeList) {
				conditionSql.append(" or t.billType='");
				conditionSql.append(type);
				conditionSql.append("' ");
			}

			List<SearchStatusEntity> statusList = OrderUtil.getBillsstatus(key.getBillsEnum(), searchText);
			if (!statusList.isEmpty()) {
				boolean isStoped = false;
				for (int i = 0; i < statusList.size(); i++) {
					SearchStatusEntity e = statusList.get(i);
					if (null != e.getStatus()) {
						conditionSql.append(" or (t.billType='" + e.getType() + "' and t.status=@status" + i + ") ");
					}
					if (e.isStop()) {
						isStoped = true;
					}
				}
				if (isStoped) {
					conditionSql.append(" or t.isStoped=true ");
				}
			}

			conditionSql.append(") ");

		}

	}

	public void addParamAndCondtion(Context context, SelectMainKey key, StringBuilder dnaSql, StringBuilder conditionSql,
			List<Object> paramValueList) {

		Employee user = BillsConstant.getUser(context);
		GUID authdeptId = user.getDepartmentId();
		List<GUID> subdeptIdList = getSubdeptIdList(context, authdeptId);
		List<GUID> deptIdList = null;
		String examstatus = StatusEnum.Approve.getKey();
		String noStoreStatus = StatusEnum.Store_N0.getKey();
		String partStoreStatus = StatusEnum.Store_Part.getKey();
		String allStoreStatus = StatusEnum.Store_All.getKey();
		String finishstatus = StatusEnum.Finished.getKey();
		conditionSql.append(" where ");
		conditionSql.append(" 1=1\n");
		dnaSql.append("@examstatus string");
		paramValueList.add(examstatus);
		/**
		 * 审批
		 */
		if (PageEnum.EXAMINE.equals(key.getTabEnum())) {
			dnaSql.append(",@status string");
			conditionSql.append(" and t.status=@status\n");
			paramValueList.add(examstatus);
			if (BillsEnum.SALE == key.getBillsEnum() || BillsEnum.SALE_CANCEL == key.getBillsEnum()) {
				dnaSql.append(",@creatorId guid");
				conditionSql.append(" and t.creatorId<>@creatorId ");
				paramValueList.add(user.getId());
			}
			conditionSql.append(" and t.isStoped=false \n");
			if (UserAuthEnum.BOSS != BillsConstant.getUserAuth(context, key.getBillsEnum())) {
				conditionSql.append(" and ");
				if (subdeptIdList.size() > 1) {
					conditionSql.append("(");
				}
				for (int i = 0; i < subdeptIdList.size(); i++) {
					dnaSql.append(",@deptId" + i + " guid");
					if (i != 0) {
						conditionSql.append(" or ");
					}
					conditionSql.append(" t.deptId=@deptId" + i + " \n");
					paramValueList.add(subdeptIdList.get(i));
				}
				if (subdeptIdList.size() > 1) {
					conditionSql.append(")");
				}
			}
		}
		if (null != key.getDeptGuid()) {
			deptIdList = getSubdeptIdList(context, key.getDeptGuid());
		}
		/**
		 * 跟踪
		 */
		if (PageEnum.FOLLOW.equals(key.getTabEnum())) {
			/**
			 * 采购员工或者我的单据
			 */
			if (key.isEmployee() || key.isMine()) {
				dnaSql.append(",@creatorId guid");
				conditionSql.append(" and t.creatorId=@creatorId ");
				paramValueList.add(user.getId());
				conditionSql.append(" and (t.status=@examstatus or \n");
			} else {
				int index = 0;
				Set<GUID> deptSet = new HashSet<GUID>();
				if (null == deptIdList) {
					deptSet.addAll(subdeptIdList);
				} else {
					for (GUID id : deptIdList) {
						if (subdeptIdList.contains(id)) {
							deptSet.add(id);
						}
					}
				}
				conditionSql.append(" and ");
				if (deptSet.size() > 1) {
					conditionSql.append("(");
				}
				int i = 0;
				for (GUID id : deptSet) {
					dnaSql.append(",@deptId" + index + " guid");
					if (i != 0) {
						conditionSql.append(" or ");
					}
					conditionSql.append(" t.deptId=@deptId" + index + " \n");
					index++;
					i++;
					paramValueList.add(id);
				}

				if (deptSet.size() > 1) {
					conditionSql.append(") ");
				}
				conditionSql.append(" and (");
				conditionSql.append(" t.status=@examstatus or \n");
			}

			dnaSql.append(",@noStoreStatus string");
			conditionSql.append(" t.status=@noStoreStatus\n");
			paramValueList.add(noStoreStatus);
			dnaSql.append(",@partStoreStatus string");
			conditionSql.append(" or t.status=@partStoreStatus\n");
			paramValueList.add(partStoreStatus);

			/**
			 * 指定部门查询时，加上自己创建的待审批单据
			 */
			if (null != key.getDeptGuid() && !(key.isEmployee() || key.isMine())) {
				dnaSql.append(",@creatorId guid");
				conditionSql.append(" or (t.creatorId=@creatorId ");
				paramValueList.add(user.getId());
				conditionSql.append(" and t.status=@examstatus) \n");
			}

			dnaSql.append(",@allStoreStatus string");
			conditionSql.append(" or t.status=@allStoreStatus)\n");
			paramValueList.add(allStoreStatus);

			conditionSql.append(" and t.isStoped=false \n");
			/**
			 * 日期区间
			 */
			if (key.getStartDate() != 0) {
				conditionSql.append(" and t.createDate>= @startDate");
				dnaSql.append(",@startDate date ");
				paramValueList.add(key.getStartDate());
				conditionSql.append(" \n");
			}
			if (null != key.getEndDate()) {
				conditionSql.append(" and t.createDate<= @endDate");
				dnaSql.append(", @endDate date ");
				paramValueList.add(key.getEndDate());
				conditionSql.append(" \n");
			}
		}
		/**
		 * 记录
		 */
		if (PageEnum.LOG.equals(key.getTabEnum())) {
			/**
			 * 采购员工或者我的单据
			 */
			if (key.isEmployee() || key.isMine()) {
				dnaSql.append(",@creatorId guid");
				conditionSql.append(" and t.creatorId=@creatorId ");
				paramValueList.add(user.getId());
			}
			/**
			 * 有权限的单据
			 */
			else {
				int index = 0;
				if (null != deptIdList) {
					conditionSql.append(" and ");
					if (deptIdList.size() > 1) {
						conditionSql.append("(");
					}
					for (int i = 0; i < deptIdList.size(); i++) {
						dnaSql.append(",@deptId" + index + " guid");
						if (i != 0) {
							conditionSql.append(" or ");
						}
						conditionSql.append(" t.deptId=@deptId" + index + " \n");
						index++;
						paramValueList.add(deptIdList.get(i));
					}
					if (deptIdList.size() > 1) {
						conditionSql.append(") ");
					}
				}
				conditionSql.append(" and ");
				if (subdeptIdList.size() > 1) {
					conditionSql.append("(");
				}
				for (int i = 0; i < subdeptIdList.size(); i++) {
					dnaSql.append(",@deptId" + index + " guid");
					if (i != 0) {
						conditionSql.append(" or ");
					}
					conditionSql.append(" t.deptId=@deptId" + index + " \n");
					index++;
					paramValueList.add(subdeptIdList.get(i));
				}
				if (subdeptIdList.size() > 1) {
					conditionSql.append(") ");
				}
			}
			conditionSql.append(" and ");
			dnaSql.append(",@finishstatus string");
			conditionSql.append(" (t.status=@finishstatus\n");
			paramValueList.add(finishstatus);
			conditionSql.append(" or t.isStoped=true)\n");
			/**
			 * 日期区间
			 */
			if (key.getStartDate() != 0) {
				conditionSql.append(" and t.createDate>= @startDate");
				dnaSql.append(",@startDate date ");
				paramValueList.add(key.getStartDate());
				// conditionSql.append(key.getStartDate());
				conditionSql.append(" \n");
			}
			if (null != key.getEndDate()) {
				conditionSql.append(" and t.createDate<= @endDate");
				dnaSql.append(", @endDate date ");
				paramValueList.add(key.getEndDate());
				// conditionSql.append(key.getEndDate());
				conditionSql.append(" \n");
			}
		}
		/**
		 * 搜索
		 */
		if (null != key.getSearch()) {
			String searchText = key.getSearch();
			List<String> typeList = OrderUtil.getBillsType(key.getBillsEnum(), searchText);
			conditionSql.append(" and (t.billsNo like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");
			conditionSql.append(" or t.partnerName like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");
			conditionSql.append(" or t.shortName like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");

			conditionSql.append(" or t.creator like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");

			for (String type : typeList) {
				conditionSql.append(" or t.billType='");
				conditionSql.append(type);
				conditionSql.append("' ");
			}
			conditionSql.append(") ");
		}

	}

	/**
	 * 填充数据
	 * 
	 * @param rs
	 * @return OrderInfo
	 */
	public OrderInfo setData(RecordSet rs, BillsEnum billType) {

		OrderInfo billsInfo = new OrderInfo();
		int index = 0;
		billsInfo.setRECID(rs.getFields().get(index++).getGUID());
		billsInfo.setBillsNo(rs.getFields().get(index++).getString());
		billsInfo.setPartnerId(rs.getFields().get(index++).getGUID());
		billsInfo.setPartnerName(rs.getFields().get(index++).getString());
		billsInfo.setPartnerNamePY(rs.getFields().get(index++).getString());
		billsInfo.setPartnerShortName(rs.getFields().get(index++).getString());
		billsInfo.setBillType(rs.getFields().get(index++).getString());
		billsInfo.setLinkman(rs.getFields().get(index++).getString());
		billsInfo.setRejectReason(rs.getFields().get(index++).getString());
		billsInfo.setStopReason(rs.getFields().get(index++).getString());
		billsInfo.setRemark(rs.getFields().get(index++).getString());
		billsInfo.setTotalAmount(rs.getFields().get(index++).getDouble());
		billsInfo.setCreatorId(rs.getFields().get(index++).getGUID());
		billsInfo.setCreator(rs.getFields().get(index++).getString());
		billsInfo.setCreateDate(rs.getFields().get(index++).getDate());
		billsInfo.setStatus(rs.getFields().get(index++).getString());
		billsInfo.setDeptId(rs.getFields().get(index++).getGUID());
		billsInfo.setStoped(rs.getFields().get(index++).getBoolean());
		billsInfo.setApproveStr(rs.getFields().get(index++).getString());
		billsInfo.setFinishedDate(rs.getFields().get(index++).getDate());
		if (billType != BillsEnum.SALE) {
			if (billType != BillsEnum.SALE_CANCEL) {
				billsInfo.setAddress(rs.getFields().get(index++).getString());
			}
			// storeId
			index++;
			// storeName
			index++;
		}
		return billsInfo;
	}

	/**
	 * 获取下级部门GUID
	 * 
	 * @param context
	 * 
	 * @param authdeptId
	 * @return List<GUID>
	 */
	private List<GUID> getSubdeptIdList(Context context, GUID authdeptId) {
		List<GUID> list = new ArrayList<GUID>();
		list.add(authdeptId);

		// GetDeptAllChildrenListForParentKey getSubDeptkey = new
		// GetDeptAllChildrenListForParentKey(
		// authdeptId);
		Department depart = context.find(Department.class, authdeptId);
		Department[] subDeptlist = depart.getDescendants(context);// ,
		// getSubDeptkey);

		if (subDeptlist != null) {
			for (Department dept : subDeptlist) {
				list.add(dept.getId());
			}
		}
		return list;
	}

	public void addFrom(StringBuilder dnaSql, String table) {
		dnaSql.append(" from \n");
		dnaSql.append(table);
		dnaSql.append(" as t \n");

	}

	public void addSelect(StringBuilder dnaSql, BillsEnum billType) {
		dnaSql.append(" select \n");
		dnaSql.append(" t.RECID as RECID, \n");
		dnaSql.append(" t.billsNo as billsNo, \n");
		dnaSql.append(" t.partnerId      as       partnerId     ,\n");
		dnaSql.append(" t.partnerName    as       partnerName   ,\n");
		dnaSql.append(" t.partnerNamePY  as       partnerNamePY ,\n");
		dnaSql.append(" t.shortName       as       shortName      ,\n");
		dnaSql.append(" t.billType        as       billType       ,\n");
		dnaSql.append(" t.linkman         as       linkman        ,\n");
		dnaSql.append(" t.rejectReason    as       rejectReason   ,\n");
		dnaSql.append(" t.stopReason      as       stopReason     ,\n");
		dnaSql.append(" t.remark          as       remark         ,\n");
		dnaSql.append(" t.totalAmount     as       totalAmount    ,\n");
		dnaSql.append(" t.creatorId       as       creatorId      ,\n");
		dnaSql.append(" t.creator         as       creator        ,\n");
		dnaSql.append(" t.createDate      as       createDate     ,\n");
		dnaSql.append(" t.status          as       status         ,\n");
		dnaSql.append(" t.deptId          as       deptId         ,\n");
		dnaSql.append(" t.isStoped        as       isStoped       ,\n");
		dnaSql.append(" t.approveStr      as       approveStr     ,\n");
		dnaSql.append(" t.finishedDate    as       finishedDate \n");
		if (billType != BillsEnum.SALE) {
			if (billType != BillsEnum.SALE_CANCEL) {
				dnaSql.append(" ,t.address         as       address \n");
			}
			dnaSql.append(" ,t.storeId         as       storeId \n");
			dnaSql.append(" ,t.storeName       as       storeName \n");
		}
		dnaSql.append(",t.deliveryDate as deliveryDate");

	}

	// --------------------业务审核变化，单据修改--------------------
	/**
	 * 业务审核变化，单据修改
	 */
	@Publish
	protected class ChangeExamineBillsHandle extends SimpleTaskMethodHandler<BillsExamineChangeTask> {

		@Override
		protected void handle(Context context, BillsExamineChangeTask task) throws Throwable {
			BillsExamChangeSql sql = new BillsExamChangeSql(context);
			DBCommand db = sql.getDB(task);
			// "@tenants guid, @oldstatus guid, @newstatus guid, @cause string";
			db.setArgumentValues(task.getTenantsGuid(), StatusEnum.Approve.getKey(), StatusEnum.Return.getKey(), task.getCause());
			task.setLenght(db.executeUpdate());
		}
	}

	// =========================订单第二版需求===================
	/**
	 * 查询订单明细商品数量总和
	 */
	@Publish
	protected class SelectOrderItemGoodsTotalCountProvder extends OneKeyResultProvider<Double, SelectOrderItemGoodsTotalCountKey> {

		@Override
		protected Double provide(Context context, SelectOrderItemGoodsTotalCountKey key) throws Throwable {
			IDnaSql sql = new SelectOrderItemGoodsTotalCountSql(context, key);
			RecordSet rs = sql.executeQuery();
			if (rs.next()) {
				return rs.getFields().get(0).getDouble();
			}
			return 0d;
		}
	}
}
