/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.outstorage.dao
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.key.store.GetUserStoreGuidsKey;
import com.spark.psi.inventory.intf.entity.outstorage.CheckOutLog;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.inventory.intf.entity.outstorage.mod.RelationCheckOutSheet;
import com.spark.psi.inventory.intf.event.CheckOutSheetStatusChanageEvent;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.outstorage.CheckOutLogKey;
import com.spark.psi.inventory.intf.key.outstorage.OutstorageKey;
import com.spark.psi.inventory.intf.key.outstorage.mod.GetRelationCheckOutSheetKey;
import com.spark.psi.inventory.intf.task.inventory.InventoryDeliveringTask;
import com.spark.psi.inventory.intf.task.outstorage.OutstorageTask;
import com.spark.psi.inventory.intf.task.outstorage.StopOutTask;
import com.spark.psi.inventory.intf.util.outstorage.QueryBuilder;
import com.spark.psi.inventory.intf.util.outstorage.UpdateBuilder;
import com.spark.psi.inventory.intf.util.outstorage.dbox.OutstoProcess;
import com.spark.psi.inventory.service.orm.Orm_Outstorage;
import com.spark.psi.inventory.service.pub.InventoryServiceUtil;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.StoreStatus;

/**
 * 出库单主表数据库操作类
 */

public class OutstorageDao extends Service {

	protected OutstorageDao(Orm_Outstorage orm) {
		super("OutstorageDao");
		this.orm = orm;

	}

	private Orm_Outstorage orm;

	/**
	 * 查询最后一条完成的单据的
	 */
	@Publish
	protected class LoadLastBillTimeProvider extends OneKeyResultProvider<Long, OutstorageKey> {

		@Override
		protected Long provide(Context context, OutstorageKey key) throws Throwable {
			QueryBuilder qb = new QueryBuilder(context);
			qb.addTable("SA_STORE_OUTSTORAGE", "t");
			qb.addColumn("max(t.createDate)", "createDate");
			fillConditions(qb, key, false);
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				return rs.getFields().get(0).getDate();
			}
			return null;
		}

	}

	/**
	 * load单个出库单记录 (根据出库单recid或者相关单据recid)
	 */
	@Publish
	protected class LoadFOutstorageProvider extends OneKeyResultProvider<Outstorage, GUID> {

		@Override
		protected Outstorage provide(Context context, GUID id) throws Throwable {
			QueryBuilder qb = new QueryBuilder(context);
			addColumnsToQuery(qb);
			if (null != id) {
				qb.addArgs("recid", "guid", id);
				qb.addEquals("t.RECID", "@recid");
			}
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				return fillEntityToQuery(rs);
			}
			return null;
		}

	}

	/**
	 * 根据相关单据查询出库单总情况
	 */
	@Publish
	protected class FindRelaFInstorageProvider extends OneKeyResultProvider<RelationCheckOutSheet, GUID> {

		@Override
		protected RelationCheckOutSheet provide(Context context, GUID relationOrderId) throws Throwable {

			Login login = context.find(Login.class);
			if (null == relationOrderId) {
				return null;
			}
			RelationCheckOutSheet relationCheckOutSheet = null;
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define query queryRelationInSheet(@relaOrderGuid guid)\n");
			dnaSql.append(" begin \n");
			dnaSql.append(" SELECT a.billsCount AS orderGoodsTotalCount,a.billsAmount AS orderTotalAmount");
			dnaSql.append(",SUM(c.realCount) AS checkedOutCount,SUM(b.receiptedAmount) AS checkedOutAmount \n");
			//
			dnaSql.append(", SUM(b.amount) as planAmount \n");
			dnaSql.append(" FROM " + ERPTableNames.Inventory.Checkingout.getTableName() + " as a , "
					+ ERPTableNames.Inventory.CheckoutSheet.getTableName() + " AS b ,\n");
			dnaSql.append(" " + ERPTableNames.Inventory.CheckoutSheet_Det.getTableName() + " as c ");
			dnaSql
					.append(" where  a.relaBillsId=b.relaBillsId and  a.relaBillsId=@relaOrderGuid and b.relaBillsId=@relaOrderGuid and c.sheetId = b.RECID \n");
			dnaSql.append(" end");
			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(login.getTenantId(), relationOrderId);
			try {
				RecordSet rs = db.executeQuery();
				if (rs.next()) {

					relationCheckOutSheet = new RelationCheckOutSheet();
					int index = 0;
					relationCheckOutSheet.setRelaOrderGuid(relationOrderId);
					relationCheckOutSheet.setOrderGoodsTotalCount(rs.getFields().get(index++).getDouble());
					relationCheckOutSheet.setOrderTotalAmount(rs.getFields().get(index++).getDouble());
					relationCheckOutSheet.setCheckedOutCount(rs.getFields().get(index++).getDouble());
					relationCheckOutSheet.setCheckedOutAmount(rs.getFields().get(index++).getDouble());
					relationCheckOutSheet.setOutStoreAmount(rs.getFields().get(index++).getDouble());
				}
			} finally {
				db.unuse();
			}

			return relationCheckOutSheet;
		}

	}

	/**
	 * 根据订单ID查询相关出库单List
	 */
	@Publish
	protected class GetRelationCheckOutSheetList extends
			OneKeyResultListProvider<Outstorage, GetRelationCheckOutSheetKey> {

		@Override
		protected void provide(Context context, GetRelationCheckOutSheetKey key, List<Outstorage> list)
				throws Throwable {
			// InventoryServiceUtil util = new InventoryServiceUtil(context);
			QueryBuilder qb = new QueryBuilder(context);
			addColumnsToQuery(qb);
			if (null != key.getRelationOrderId()) {
				qb.addArgs("relaBillsId", "guid", key.getRelationOrderId());
				qb.addEquals("t.relaBillsId", "@relaBillsId");
			}
			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				list.add(fillEntityToQuery(rs));
			}
		}

	}

	/**
	 * insert一条出库单数据
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	@Publish
	protected class InsertOutstorage extends TaskMethodHandler<OutstorageTask, Method> {

		protected InsertOutstorage() {
			super(Method.INSERT);
		}

		@Override
		protected void handle(Context context, OutstorageTask task) throws Throwable {
			Outstorage entity = task.getEntity();
			if (null != entity.getPartnerId()) {
				Partner p = context.find(Partner.class, entity.getPartnerId());
				entity.setPartnerCode(p.getCode());
			}
			if (null != entity.getCreatorId()) {
				Department dep = context.find(Department.class, context.find(Employee.class, entity.getCreatorId())
						.getDepartmentId());
				entity.setCreatorDeptId(dep.getId());
				entity.setDeptName(dep.getName());
			}
			ORMAccessor<Outstorage> orma = context.newORMAccessor(orm);
			try {
				orma.insert(entity);
			} finally {
				orma.unuse();
			}
		}
	}

	/**
	 * 变更一条出库单记录的处理状态
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	@Publish
	protected class ChangeProcessOutstorage extends TaskMethodHandler<OutstorageTask, Method> {

		protected ChangeProcessOutstorage() {
			super(Method.MODIFYstatus);
		}

		@Override
		protected void handle(Context context, OutstorageTask task) throws Throwable {
			// 构建update语句
			OutstoProcess process = task.getProcess();
			UpdateBuilder ub = new UpdateBuilder(context);
			ub.setTable("SA_STORE_OUTSTORAGE");
			ub.addExpression("outstoState", ub.STRING, process.getOutstoState(), "outstoState = @outstoState");
			ub.addExpression("allOutstoDate", ub.DATE, process.getAllOutstoDate(), "allOutstoDate = @allOutstoDate");
			ub.addExpression("sureOutsto", ub.STRING, process.getSureOutsto(),
					"sureOutsto = t.sureOutsto + @sureOutsto");
			ub.addExpression("takePerson", ub.STRING, process.getTakePerson(), "takePerson=@takePerson");
			ub.addExpression("takeUnit", ub.STRING, process.getTakeUnit(), "takeUnit=@takeUnit");
			ub.addExpression("vouchersNo", ub.STRING, process.getVouchersNo(), "vouchersNo=@vouchersNo");
			ub.addCondition("recid", ub.guid, process.getRecid(), "t.RECID = @recid");
			ub.addCondition("prestatus", ub.STRING, process.getPrestatus(), "t.outstoState=@prestatus");
			ub.addCondition("t.isStoped = false");
			// 执行并返回执行条数
			task.count = ub.execute();
			if (task.count > 0) {
				CheckOutSheetStatusChanageEvent event = new CheckOutSheetStatusChanageEvent();
				event.setCheckOutSheetId(process.getRecid());
				context.dispatch(event);
			} else {
				throw new Throwable("有其他人在操作该单据，请检查！");
			}
		}

	}

	/**
	 * 变更一条出库单记录的中止状态----中止中止中止中止中止
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	@Publish
	protected class StopOutstorageHandle extends SimpleTaskMethodHandler<StopOutTask> {

		@Override
		protected void handle(Context context, StopOutTask task) throws Throwable {
			// 构建update语句
			UpdateBuilder ub = new UpdateBuilder(context);
			ub.setTable(ERPTableNames.Inventory.Checkingout.getTableName());
			ub.addExpression("stopReason", ub.STRING, task.getStopReason(), "stopReason = @stopReason");
			ub.addExpression("isStoped", ub.BOOLEAN, task.isStop(), "isStoped = @isStoped");
			ub.addCondition("relaBillsId", ub.guid, task.getRelaOrdGuid(), "t.relaBillsId= @relaBillsId");
			ub.addCondition("notStoped", ub.BOOLEAN, !task.isStop(), "t.isStoped=@notStoped");
			ub.addCondition("all", ub.STRING, CheckingOutStatus.Finish.getCode(), "t.status <> @all");
			// 执行并返回执行条数
			task.count = ub.execute();
			if (task.count > 0) {
				GetRelationCheckOutSheetKey key = new GetRelationCheckOutSheetKey();
				key.setRelationOrderId(task.getRelaOrdGuid());
				List<Outstorage> list = context.getList(Outstorage.class, key);
				for (Outstorage entity : list) {
					updateDeliveringCount(context, task, entity);
					CheckOutSheetStatusChanageEvent event = new CheckOutSheetStatusChanageEvent();
					event.setCheckOutSheetId(entity.getRECID());
					context.dispatch(event);
				}
			}
		}

	}

	/**
	 * 查询页签下总条数
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	@Publish
	protected class SearchTableCount extends OneKeyResultProvider<Integer, OutstorageKey> {

		@Override
		protected Integer provide(Context context, OutstorageKey key) throws Throwable {
			QueryBuilder qb = new QueryBuilder(context);
			Integer count = 0;
			qb.addTable("SA_STORE_OUTSTORAGE", "t");
			qb.addColumn("count(1)", "ordCount");
			StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING, StoreStatus.STOP,
					StoreStatus.STOPANDONCOUNTING };
			key.addActionStores(context.find(GUID[].class, new GetUserStoreGuidsKey(statuss)));
			fillConditions(qb, key, true);
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				count = rs.getFields().get(0).getInt();
			}
			return count;
		}

	}

	/**
	 * 查询页签下显示的记录
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	@Publish
	protected class SearchTableListProvider extends OneKeyResultListProvider<Outstorage, OutstorageKey> {

		@Override
		protected void provide(Context context, OutstorageKey key, List<Outstorage> list) throws Throwable {
			QueryBuilder qb = new QueryBuilder(context);
			addColumnsToQuery(qb);
			// 用户只能对自己有权限的仓库进行操作 edit 周利均

			StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING, StoreStatus.STOP,
					StoreStatus.STOPANDONCOUNTING };
			key.addActionStores(context.find(GUID[].class, new GetUserStoreGuidsKey(statuss)));
			fillConditions(qb, key, true);
			addOrderBy(qb, key);
			RecordSet rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			while (rs.next()) {
				list.add(fillEntityToQuery(rs));
			}
			return;
		}
	}

	/**
	 * 将查询结果集的数据，填充入实体，并返回
	 * 
	 * @param rs
	 * @return Outstorage
	 */
	private Outstorage fillEntityToQuery(RecordSet rs) {
		Outstorage entity = new Outstorage();
		int index = 0;
		entity.setRECID(rs.getFields().get(index++).getGUID());
		entity.setSheetType(rs.getFields().get(index++).getString());
		entity.setPartnerId(rs.getFields().get(index++).getGUID());
		entity.setPartnerName(rs.getFields().get(index++).getString());
		entity.setNamePY(rs.getFields().get(index++).getString());
		entity.setPartnerShortName(rs.getFields().get(index++).getString());
		entity.setRelaBillsId(rs.getFields().get(index++).getGUID());
		entity.setRelaBillsNo(rs.getFields().get(index++).getString());
		entity.setStoreId(rs.getFields().get(index++).getGUID());
		entity.setStoped(rs.getFields().get(index++).getBoolean());
		entity.setStopReason(rs.getFields().get(index++).getString());
		entity.setStoreName(rs.getFields().get(index++).getString());
		entity.setStoreNamePY(rs.getFields().get(index++).getString());
		entity.setRemark(rs.getFields().get(index++).getString());
		entity.setBillsAmount(rs.getFields().get(index++).getDouble());
		entity.setBillsCount(rs.getFields().get(index++).getDouble());
		entity.setCheckoutDate(rs.getFields().get(index++).getLong());
		entity.setCheckoutString(rs.getFields().get(index++).getString());
		entity.setStatus(rs.getFields().get(index++).getString());
		entity.setCreateDate(rs.getFields().get(index++).getLong());
		entity.setCreatorId(rs.getFields().get(index++).getGUID());
		entity.setCreator(rs.getFields().get(index++).getString());

		return entity;
	}

	/**
	 * 中止时扣除交货需求数量，重新执行时增加交货需求数量
	 * 
	 * @param context
	 * @param task
	 * @param entity
	 *            void
	 */
	public void updateDeliveringCount(Context context, StopOutTask task, Outstorage entity) {
		List<OutstorageItem> list = context.getList(OutstorageItem.class, entity.getRECID());
		if (CheckIsNull.isEmpty(list)) {
			return;
		}
		for (OutstorageItem item : list) {
			InventoryDeliveringTask dTask = new InventoryDeliveringTask(entity.getStoreId(), item.getGoodsId());
			if (task.isStop()) {
				dTask.setToDeliverCount(DoubleUtil
						.mul(-1, DoubleUtil.sub(item.getPlanCount(), item.getCheckoutCount())));
			} else {
				dTask.setToDeliverCount(DoubleUtil.sub(item.getPlanCount(), item.getCheckoutCount()));
			}
			context.handle(dTask);
		}

	}

	/**
	 * 为查询出库单的查询器添加查询列
	 * 
	 * @param qb
	 *            void
	 */
	private void addColumnsToQuery(QueryBuilder qb) {
		qb.addTable(ERPTableNames.Inventory.Checkingout.getTableName(), "t");
		qb.addColumn("t.RECID", "RECID");
		qb.addColumn("t.sheetType", "sheetType");
		qb.addColumn("t.partnerId", "partnerId");
		qb.addColumn("t.partnerName", "partnerName");
		qb.addColumn("t.namePY", "namePY");
		qb.addColumn("t.partnerShortName", "partnerShortName");
		qb.addColumn("t.relaBillsId", "relaBillsId");
		qb.addColumn("t.relaBillsNo", "relaBillsNo");
		qb.addColumn("t.storeId", "storeId");
		qb.addColumn("t.isStoped", "isStoped");
		qb.addColumn("t.stopReason", "stopReason");
		qb.addColumn("t.storeName", "storeName");
		qb.addColumn("t.storeNamePY", "storeNamePY");
		qb.addColumn("t.remark", "remark");
		qb.addColumn("t.billsAmount", "billsAmount");
		qb.addColumn("t.billsCount", "billsCount");
		qb.addColumn("t.checkoutDate", "checkoutDate");
		qb.addColumn("t.checkoutString", "checkoutString");
		qb.addColumn("t.status", "status");
		qb.addColumn("t.createDate", "createDate");
		qb.addColumn("t.creatorId", "creatorId");
		qb.addColumn("t.creator", "creator");
	}

	/**
	 * 为列表查询添加过滤条件
	 * 
	 * @param qb
	 * @param key
	 * @param hasTime
	 */
	private void fillConditions(QueryBuilder qb, OutstorageKey key, boolean hasTime) {
		if (null != key.getOutstoType()) {
			/**
			 * 销售出库或者 采购退货页签
			 */
			if (key.getOutstoType().isMaterialTakeOrReturn()) {
				qb.addArgs("sheetType0", qb.STRING, CheckingOutType.Mateiral_Return.getCode());
				qb.addArgs("sheetType1", qb.STRING, CheckingOutType.Mateiral_Take.getCode());
				qb.addCondition("t.sheetType in (@sheetType0,@sheetType1)");
			} else {
				qb.addArgs("sheetType", qb.STRING, key.getOutstoType().getCode());
				qb.addEquals("t.sheetType", "@sheetType");
			}
			qb.addArgs("status1", qb.STRING, CheckingOutStatus.Part.getCode());
			qb.addArgs("status2", qb.STRING, CheckingOutStatus.None.getCode());
			qb.addCondition("(t.status = @status1 or t.status  = @status2)");
			qb.addCondition("t.isStoped = false");
		} else {
			/**
			 * 历史记录页签
			 */
			qb.addArgs("status1", qb.STRING, CheckingOutStatus.Finish.getCode());
			qb.addCondition("(t.status  = @status1 or t.isStoped = true)");
			/**
			 * 时间区间条件
			 */
			if (null != key.getBeginTime() && null != key.getEndTime() && hasTime) {
				qb.addArgs("beginTime", qb.DATE, key.getBeginTime());
				qb.addArgs("endTime", qb.DATE, key.getEndTime());
				qb.addCondition(" t.createDate between @beginTime and @endTime");
			} else if (null != key.getBeginTime() && hasTime) {
				qb.addArgs("beginTime", qb.DATE, key.getBeginTime());
				qb.addCondition("t.createDate >= @beginTime");
			} else if (null != key.getEndTime() && hasTime) {
				qb.addArgs("endTime", qb.DATE, key.getEndTime());
				qb.addCondition("t.createDate <= @endTime");
			}
		}
		/**
		 * in 所有有权限的仓库
		 */
		if (null == key.getActionStores() || key.getActionStores().isEmpty()) {
			qb.addCondition("t.storeId is null");
		} else {
			int i = 0;
			List<String> params1 = new ArrayList<String>();
			for (GUID g : key.getActionStores()) {
				i++;
				qb.addArgs("storeId" + i, "guid", g);
				params1.add("@storeId" + i);
			}
			qb.addIn("t.storeId", params1);
		}
		/**
		 * 搜索的时候
		 */
		if (null != key.getSearchKey()) {
			StringBuilder sb = new StringBuilder();
			qb.addArgs("searchKey", qb.STRING, key.getSearchKey());
			sb.append("( t.relaBillsNo like '%'+@searchKey+'%' ");
			sb.append(" or t.storeName like '%'+@searchKey+'%' ");
			sb.append(" or t.partnerName like '%'+@searchKey+'%' ");
			if (null == key.getOutstoType()) {
				sb.append(getTypeSql(key.getSearchKey().trim()));
			}
			sb.append(")");
			qb.addCondition(sb.toString());
		}
	}

	private Object getTypeSql(String trim) {
		StringBuffer sb = new StringBuffer();
		if (CheckingOutType.Kit.getName().indexOf(trim) != -1) {
			sb.append(" or t.sheetType='").append(CheckingOutType.Kit.getCode()).append("'");
		}
		if (CheckingOutType.Mateiral_Take.getName().indexOf(trim) != -1) {
			sb.append(" or t.sheetType='").append(CheckingOutType.Mateiral_Take.getCode()).append("'");
			sb.append(" or t.sheetType='").append(CheckingOutType.Mateiral_Return.getCode()).append("'");
		}
		if (CheckingOutType.Return.getName().indexOf(trim) != -1) {
			sb.append(" or t.sheetType='").append(CheckingOutType.Return.getCode()).append("'");
		}
		if (CheckingOutType.Sales.getName().indexOf(trim) != -1) {
			sb.append(" or t.sheetType='").append(CheckingOutType.Sales.getCode()).append("'");
		}
		sb.append(" or t.checkoutString like '%'+@searchKey+'%'");
		return sb;
	}

	/**
	 * 为列表查询添加排序
	 * 
	 * @param qb
	 * @param key
	 *            void
	 */
	private void addOrderBy(QueryBuilder qb, OutstorageKey key) {
		if (CheckIsNull.isEmpty(key.getSortColumnName())) {
			if (CheckingOutType.Sales.equals(key.getOutstoType())) {
				qb.addOrderBy(" t.checkoutDate asc ");
			} else {
				qb.addOrderBy(" t.createDate asc ");

			}
		} else {
			qb.addOrderBy(key.getSortColumnName() + " " + key.getSortType());
		}
	}

	/**
	 * 根据出库单ID查询出库记录
	 */
	@Publish
	protected class GetCheckOutLogList extends OneKeyResultListProvider<CheckOutLog, CheckOutLogKey> {

		@Override
		protected void provide(Context context, CheckOutLogKey key, List<CheckOutLog> list) throws Throwable {
			// InventoryServiceUtil util = new InventoryServiceUtil(context);
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define query queryCheckOutLogList(@checkOutSheetId guid) \n");
			dnaSql.append("begin \n");
			dnaSql.append(" select \n");
			dnaSql.append(" t.RECID as RECID \n");
			dnaSql.append(" ,t.checkoutPersonName as checkoutPersonName \n");
			dnaSql.append(" ,t.checkoutDate as checkOutDate \n");
			dnaSql.append(" ,t.amount as amount \n");
			dnaSql.append(" ,t.takePerson as taker \n");
			dnaSql.append(" ,t.takeUnit as takerUnit \n");
			dnaSql.append(" ,t.vouchersNo as vouchersNumber \n");

			dnaSql.append(" from " + ERPTableNames.Inventory.CheckoutSheet.getTableName() + " as t \n");
			dnaSql.append(" where t.relaBillsId = @checkOutSheetId \n");
			dnaSql.append(" end");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(key.getRelaBillsId());

			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					list.add(InventoryServiceUtil.fillCheckOutLog(rs));
				}
			} finally {
				db.unuse();
			}

		}

	}

}
