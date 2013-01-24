/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.instorage.dao
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       ��־��      
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
import com.spark.psi.base.Partner;
import com.spark.psi.base.key.store.GetUserStoreGuidsKey;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.inventory.intf.entity.instorage.mod.RelationCheckInSheet;
import com.spark.psi.inventory.intf.event.CheckInSheetStatusChanageEvent;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.instorage.InstorageKey;
import com.spark.psi.inventory.intf.key.instorage.QueryRelationCheckedInCountKey;
import com.spark.psi.inventory.intf.key.instorage.mod.GetRelationCheckInSheetKey;
import com.spark.psi.inventory.intf.task.instorage.InstorageTask;
import com.spark.psi.inventory.intf.task.instorage.StopInTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryOnWayTask;
import com.spark.psi.inventory.intf.util.instorage.QueryBuilder;
import com.spark.psi.inventory.intf.util.instorage.UpdateBuilder;
import com.spark.psi.inventory.intf.util.instorage.dbox.InstoProcess;
import com.spark.psi.inventory.service.orm.Orm_Instorage;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.StoreStatus;

/**
 * <p>
 * ��ⵥ�������ݿ������
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
 * Company: ����
 * </p>
 * 
 * @author ��־��
 * @version 2011-11-10
 */

public class InstorageDao extends Service {

	protected InstorageDao(Orm_Instorage orm) {
		super("InstorageDao");
		this.orm = orm;

	}

	private Orm_Instorage orm;

	/**
	 * ��ѯ���һ����ɵĵ��ݵ�
	 */
	@Publish
	protected class LoadLastBillTimeProvider extends OneKeyResultProvider<Long, InstorageKey> {

		@Override
		protected Long provide(Context context, InstorageKey key) throws Throwable {
			QueryBuilder qb = new QueryBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckinSheet.getTableName(), "t");
			qb.addColumn("t.checkinDate", "checkinDate");
			fillConditions(qb, key, false, false);
			qb.addOrderBy(" t.checkinDate desc");
			RecordSet rs = qb.getRecordLimit(0, 1);
			if (rs.next()) {
				return rs.getFields().get(0).getDate();
			}
			return null;
		}

	}

	/**
	 * load������ⵥ��¼ (������ⵥrecid������ص���recid)
	 */
	@Publish
	protected class LoadFInstorageProvider extends OneKeyResultProvider<Instorage, GUID> {

		@Override
		protected Instorage provide(Context context, GUID id) throws Throwable {
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
	 * ������ص��ݲ�ѯ��ⵥ�����
	 */
	@Publish
	protected class FindRelaFInstorageProvider extends OneKeyResultProvider<RelationCheckInSheet, GUID> {

		@Override
		protected RelationCheckInSheet provide(Context context, GUID relationOrderId) throws Throwable {

			if (null == relationOrderId) {
				return null;
			}
			RelationCheckInSheet relationCheckInSheet = null;
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define query queryRelationInSheet(@relaOrderGuid guid)\n");
			dnaSql.append(" begin \n");
			dnaSql.append(" SELECT ");
			dnaSql
					.append("SUM(b.realCount) AS checkedInCount,SUM(a.PAIDAMOUNT) AS checkedInAmount,SUM(a.DISAMOUNT) AS checkedInAmount2 \n");
			//
			dnaSql.append(", SUM(b.amount) as planAmount \n");
			dnaSql.append(" FROM " + ERPTableNames.Inventory.CheckinSheet.getTableName() + " as a JOIN "
					+ ERPTableNames.Inventory.CheckinSheet_Det.getTableName() + " AS b \n");
			dnaSql.append(" ON a.recid=b.sheetId AND a.relaBillsId=@relaOrderGuid \n");
			dnaSql.append(" end");
			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(relationOrderId);

			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					relationCheckInSheet = new RelationCheckInSheet();
					int index = 0;
					relationCheckInSheet.setRelationOrderId(relationOrderId);
					relationCheckInSheet.setCheckedInCount(rs.getFields().get(index++).getDouble());
					relationCheckInSheet.setCheckedInAmount(rs.getFields().get(index++).getDouble());
					relationCheckInSheet.setCheckedInAmount(DoubleUtil.sum(relationCheckInSheet.getCheckedInAmount(),
							rs.getFields().get(index++).getDouble()));
					relationCheckInSheet.setInStoreAmount(rs.getFields().get(index++).getDouble());
				}
			} finally {
				db.unuse();
			}
			List<Instorage> infos = context.getList(Instorage.class, new GetRelationCheckInSheetKey(relationOrderId));
			relationCheckInSheet.setOrderGoodsTotalCount(infos.get(0).getBillsCount());
			relationCheckInSheet.setOrderTotalAmount(infos.get(0).getBillsAmount());
			return relationCheckInSheet;
		}

	}

	/**
	 * ���ݶ���ID��ѯ�����ⵥList
	 */
	@Publish
	protected class GetRelationCheckInSheetList extends OneKeyResultListProvider<Instorage, GetRelationCheckInSheetKey> {

		@Override
		protected void provide(Context context, GetRelationCheckInSheetKey key, List<Instorage> list) throws Throwable {

			QueryBuilder qb = new QueryBuilder(context);
			addColumnsToQuery(qb);
			if (null != key.getRelationOrdId()) {
				qb.addArgs("relaBillsId", "guid", key.getRelationOrdId());
				qb.addEquals("t.relaBillsId", "@relaBillsId");
			}
			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				list.add(fillEntityToQuery(rs));
			}
		}

	}

	/**
	 * insertһ����ⵥ����
	 * 
	 * @author ��־��
	 * @version 2011-11-10
	 */
	@Publish
	protected class InsertInstorage extends TaskMethodHandler<InstorageTask, Method> {

		protected InsertInstorage() {
			super(Method.INSERT);
		}

		@Override
		protected void handle(Context context, InstorageTask task) throws Throwable {
			Instorage entity = task.getInstorageEntity();
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
			ORMAccessor<Instorage> orma = context.newORMAccessor(orm);
			try {
				orma.insert(entity);
			} finally {
				orma.unuse();
			}
		}
	}

	/**
	 * ���һ����ⵥ��¼�Ĵ���״̬
	 * 
	 * @author ��־��
	 * @version 2011-11-10
	 */
	@Publish
	protected class ChangeProcessInstorage extends TaskMethodHandler<InstorageTask, Method> {

		protected ChangeProcessInstorage() {
			super(Method.MODIFYstatus);
		}

		@Override
		protected void handle(Context context, InstorageTask task) throws Throwable {
			// ����update���
			InstoProcess process = task.getProcess();
			UpdateBuilder ub = new UpdateBuilder(context);
			ub.setTable("" + ERPTableNames.Inventory.CheckinSheet.getTableName() + "");
			ub.addExpression("instoState", ub.STRING, process.getInstoState(), "instoState = @instoState");
			ub.addExpression("allInstoDate", ub.DATE, process.getAllInstoDate(), "allInstoDate = @allInstoDate");
			ub.addExpression("sureInsto", ub.STRING, process.getSureInsto(), "sureInsto = t.sureInsto + @sureInsto");
			ub.addCondition("recid", ub.guid, process.getRecid(), "t.RECID = @recid");
			ub.addCondition("prestatus", ub.STRING, process.getPrestatus(), "t.status=@prestatus");
			ub.addCondition("t.isStoped = false");
			// ִ�в�����ִ������
			task.count = ub.execute();

			if (task.count > 0) {
				CheckInSheetStatusChanageEvent event = new CheckInSheetStatusChanageEvent();
				event.setCheckInSheetId(process.getRecid());
				context.dispatch(event);
			} else {
				throw new Throwable("���������ڲ����õ��ݣ����飡");
			}

		}

	}

	/**
	 * ���һ����ⵥ��¼����ֹ״̬----��ֹ��ֹ��ֹ��ֹ��ֹ
	 * 
	 * @author ��־��
	 * @version 2011-11-10
	 */
	@Publish
	protected class StopInstorageHandle extends SimpleTaskMethodHandler<StopInTask> {

		@Override
		protected void handle(Context context, StopInTask task) throws Throwable {
			// ����update���
			UpdateBuilder ub = new UpdateBuilder(context);
			ub.setTable("" + ERPTableNames.Inventory.Checkingin.getTableName() + "");
			ub.addExpression("stopReason", ub.STRING, task.getStopReason(), "stopReason = @stopReason");
			ub.addExpression("isStoped", ub.BOOLEAN, task.isStop(), "isStoped = @isStoped");
			ub.addCondition("relaBillsId", ub.guid, task.getRelaOrdGuid(), "t.relaBillsId= @relaBillsId");
			ub.addCondition("notStoped", ub.BOOLEAN, !task.isStop(), "t.isStoped=@notStoped");
			ub.addCondition("all", ub.STRING, CheckingInStatus.Finish.getCode(), "t.status <> @all");
			// ִ�в�����ִ������
			task.count = ub.execute();
			if (task.count > 0) {

				GetRelationCheckInSheetKey key = new GetRelationCheckInSheetKey();
				key.setRelationOrdId(task.getRelaOrdGuid());
				List<Instorage> list = context.getList(Instorage.class, key);
				for (Instorage entity : list) {
					updateOnWayCount(context, task, entity);
					CheckInSheetStatusChanageEvent event = new CheckInSheetStatusChanageEvent();
					event.setCheckInSheetId(entity.getRECID());
					context.dispatch(event);
				}
			}
		}
	}

	/**
	 * ��ֹʱ�۳��ɹ���;����������ִ��ʱ���Ӳɹ���;����
	 * 
	 * @param context
	 * @param task
	 * @param entity
	 *            void
	 */
	public void updateOnWayCount(Context context, StopInTask task, Instorage entity) {
		List<InstorageItem> list = context.getList(InstorageItem.class, entity.getRECID());
		if (CheckIsNull.isEmpty(list)) {
			return;
		}
		for (InstorageItem item : list) {
			InventoryOnWayTask dTask = new InventoryOnWayTask(entity.getStoreId(), item.getGoodsId());
			if (task.isStop()) {
				dTask.setOnWayCount(DoubleUtil.mul(-1, DoubleUtil.sub(item.getCount(), item.getCheckinCount())));
			} else {
				dTask.setOnWayCount(DoubleUtil.sub(item.getCount(), item.getCount()));
			}
			context.handle(dTask);
		}

	}

	/**
	 * ��ѯҳǩ��������
	 * 
	 * @author ��־��
	 * @version 2011-11-10
	 */
	@Publish
	protected class SearchTableCount extends OneKeyResultProvider<Integer, InstorageKey> {

		@Override
		protected Integer provide(Context context, InstorageKey key) throws Throwable {
			QueryBuilder qb = new QueryBuilder(context);
			Integer count = 0;
			qb.addTable("" + ERPTableNames.Inventory.CheckinSheet.getTableName() + "", "t");
			qb.addColumn("count(1)", "ordCount");
			// �û���Ȩ�޲����Ĳֿ� edit ������
			StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING, StoreStatus.STOP,
					StoreStatus.STOPANDONCOUNTING };
			key.addActionStores(context.find(GUID[].class, new GetUserStoreGuidsKey(statuss)));
			fillConditions(qb, key, true, true);
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				count = rs.getFields().get(0).getInt();
			}
			return count;
		}

	}

	/**
	 * ��ѯҳǩ����ʾ�ļ�¼
	 * 
	 * @author ��־��
	 * @version 2011-11-10
	 */
	@Publish
	protected class SearchTableListProvider extends OneKeyResultListProvider<Instorage, InstorageKey> {

		@Override
		protected void provide(Context context, InstorageKey key, List<Instorage> list) throws Throwable {
			QueryBuilder qb = new QueryBuilder(context);
			addColumnsToQuery(qb);
			// �û���Ȩ�޲����Ĳֿ� edit ������
			StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING, StoreStatus.STOP,
					StoreStatus.STOPANDONCOUNTING };
			key.addActionStores(context.find(GUID[].class, new GetUserStoreGuidsKey(statuss)));
			fillConditions(qb, key, true, true);
			addOrderBy(qb, key);
			RecordSet rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			while (rs.next()) {
				list.add(fillEntityToQuery(rs));
			}
			return;
		}
	}

	/**
	 * ���������ⵥ���������
	 */
	@Publish
	protected class queryOkCount extends OneKeyResultProvider<Double, QueryRelationCheckedInCountKey> {

		@Override
		protected Double provide(Context context, QueryRelationCheckedInCountKey key) throws Throwable {
			double okCount = 0;
			// StringBuilder dnaSql = new StringBuilder();
			// dnaSql.append(" define query queryOkCount(@relaOrderGuid guid)\n");
			// dnaSql.append(" begin \n");
			// dnaSql.append(" select sum(sureinsto.instoCount) as s from \n");
			// dnaSql.append(" "+ERPTableNames.Inventory.CheckinSheet.getTableName()+" as instorage \n");
			// dnaSql.append(" join "+ERPTableNames.Inventory.CheckinSheet_Det.getTableName()+" as sureinsto \n");
			// dnaSql.append(" on sureinsto.instoGuid=instorage.recid \n");
			// dnaSql.append(" and instorage.relaOrderGuid=@relaOrderGuid \n");
			// dnaSql.append(" end");
			//
			// DBCommand db = context.prepareStatement(dnaSql);
			// db.setArgumentValues(key.getRelationOrderId());
			//
			// try {
			// RecordSet rs = db.executeQuery();
			// while (rs.next()) {
			// okCount = rs.getFields().get(0).getDouble();
			// }
			// } finally {
			// db.unuse();
			// }
			return okCount;
		}

	}

	/**
	 * ����ѯ����������ݣ������ʵ�壬������
	 * 
	 * @param rs
	 * @return Instorage
	 */
	private Instorage fillEntityToQuery(RecordSet rs) {
		Instorage entity = new Instorage();
		int i = 0;
		entity.setRECID(rs.getFields().get(i++).getGUID());
		entity.setBillsAmount(rs.getFields().get(i++).getDouble());
		entity.setBillsCount(rs.getFields().get(i++).getDouble());
		entity.setCheckinDate(rs.getFields().get(i++).getDate());
		entity.setCheckinString(rs.getFields().get(i++).getString());
		entity.setCreateDate(rs.getFields().get(i++).getDate());
		entity.setCreator(rs.getFields().get(i++).getString());
		entity.setCreatorId(rs.getFields().get(i++).getGUID());
		entity.setStoped(rs.getFields().get(i++).getBoolean());
		entity.setPartnerId(rs.getFields().get(i++).getGUID());
		entity.setPartnerName(rs.getFields().get(i++).getString());
		entity.setPartnerShortName(rs.getFields().get(i++).getString());
		entity.setRelaBillsId(rs.getFields().get(i++).getGUID());
		entity.setRelaBillsNo(rs.getFields().get(i++).getString());
		entity.setRemark(rs.getFields().get(i++).getString());
		entity.setSheetType(rs.getFields().get(i++).getString());
		entity.setStatus(rs.getFields().get(i++).getString());
		entity.setStopReason(rs.getFields().get(i++).getString());
		entity.setStoreId(rs.getFields().get(i++).getGUID());
		entity.setStoreName(rs.getFields().get(i++).getString());
		return entity;
	}

	/**
	 * Ϊ��ѯ��ⵥ�Ĳ�ѯ����Ӳ�ѯ��
	 * 
	 * @param qb
	 *            void
	 */
	private void addColumnsToQuery(QueryBuilder qb) {
		qb.addTable(ERPTableNames.Inventory.Checkingin.getTableName(), "t");
		qb.addColumn("t.RECID", "RECID");
		qb.addColumn("t.billsAmount", "billsAmount");
		qb.addColumn("t.billsCount", "billsCount");
		qb.addColumn("t.checkinDate", "checkinDate");
		qb.addColumn("t.checkinString", "checkinString");
		qb.addColumn("t.createDate", "createDate");
		qb.addColumn("t.creator", "creator");
		qb.addColumn("t.creatorId", "creatorId");
		qb.addColumn("t.isStoped", "isStoped");

		qb.addColumn("t.partnerId", "partnerId");
		qb.addColumn("t.partnerName", "partnerName");
		qb.addColumn("t.partnerShortName", "partnerShortName");
		qb.addColumn("t.relaBillsId", "relaBillsId");
		qb.addColumn("t.relaBillsNo", "relaBillsNo");
		qb.addColumn("t.remark", "remark");
		qb.addColumn("t.sheetType", "sheetType");
		qb.addColumn("t.status", "status");
		qb.addColumn("t.stopReason", "stopReason");
		qb.addColumn("t.storeId", "storeId");
		qb.addColumn("t.storeName", "storeName");
	}

	/**
	 * Ϊ�б��ѯ��ӹ�������
	 * 
	 * @param qb
	 * @param key
	 */
	private void fillConditions(QueryBuilder qb, InstorageKey key, boolean hasTime, boolean isIng) {
		if (null != key.getInstoType()) {
			/**
			 * �ɹ������� �����˻�ҳǩ
			 */
			qb.addArgs("sheetType", qb.STRING, key.getInstoType());
			qb.addEquals("t.sheetType", "@sheetType");
			if (isIng) {
				qb.addArgs("status1", qb.STRING, CheckingInStatus.Part.getCode());
				qb.addArgs("status2", qb.STRING, CheckingInStatus.None.getCode());
				qb.addCondition("(t.status = @status1 or t.status = @status2)");
				qb.addCondition("t.isStoped = false");
			}
		} else {
			/**
			 * ʱ����������
			 */
			// if (null != key.getBeginTime() && null != key.getEndTime() &&
			// hasTime) {
			// qb.addArgs("beginTime", qb.DATE, key.getBeginTime());
			// qb.addArgs("endTime", qb.DATE, key.getEndTime());
			// StringBuffer condition = new StringBuffer();
			// condition.append(" (t.allInstoDate between @beginTime and @endTime");
			// condition.append(" or ");
			// condition.append(" (t.stopedDate between @beginTime ");
			// condition.append(" and @endTime and t.status='");
			// condition.append(CheckingInStatus.None.getCode()).append("')) ");
			// qb.addCondition(condition.toString());
			// } else if (null != key.getBeginTime() && hasTime) {
			// qb.addArgs("beginTime", qb.DATE, key.getBeginTime());
			// StringBuffer condition = new StringBuffer();
			// condition.append(" (t.allInstoDate >= @beginTime ");
			// condition.append(" or ");
			// condition.append(" (t.stopedDate >= @beginTime ");
			// condition.append(" and t.status='");
			// condition.append(CheckingInStatus.None.getCode()).append("')) ");
			// qb.addCondition(condition.toString());
			// } else if (null != key.getEndTime() && hasTime) {
			// qb.addArgs("endTime", qb.DATE, key.getEndTime());
			// StringBuffer condition = new StringBuffer();
			// condition.append(" (t.allInstoDate <= @endTime ");
			// condition.append(" or ");
			// condition.append(" (t.stopedDate <= @endTime ");
			// condition.append(" and t.status='");
			// condition.append(CheckingInStatus.None.getCode()).append("')) ");
			// qb.addCondition(condition.toString());
			// }
		}
		/**
		 * in ������Ȩ�޵Ĳֿ�
		 */
		if (key.getActionStores() == null || key.getActionStores().isEmpty()) {
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
		 * ������ʱ��
		 */
		if (null != key.getSearchKey()) {
			StringBuilder sb = new StringBuilder();
			qb.addArgs("searchKey", qb.STRING, key.getSearchKey());
			sb.append(" ( t.relaBillsNo like '%'+@searchKey+'%' ");
			sb.append(" or t.storeName like '%'+@searchKey+'%' ");
			sb.append(" or t.storeNamePY like '%'+@searchKey+'%' ");
			if (null == key.getInstoType()) {
				sb.append(getTypeSql(key.getSearchKey().trim()));
				sb.append(" or t.checkinString like '%'+@searchKey+'%' ");
			}
			sb.append(")");
			qb.addCondition(sb.toString());
		}

	}

	private StringBuffer getTypeSql(String trim) {
		StringBuffer sb = new StringBuffer();
		if (CheckingInType.Irregular.getName().indexOf(trim) != -1) {
			sb.append(" or t.sheetType='").append(CheckingInType.Irregular.getCode()).append("'");
		}
		if (CheckingInType.Purchase.getName().indexOf(trim) != -1) {
			sb.append(" or t.sheetType='").append(CheckingInType.Purchase.getCode()).append("'");
		}
		if (CheckingInType.Adjustment.getName().indexOf(trim) != -1) {
			sb.append(" or t.sheetType='").append(CheckingInType.Adjustment.getCode()).append("'");
		}
		if (CheckingInType.Return.getName().indexOf(trim) != -1) {
			sb.append(" or t.sheetType='").append(CheckingInType.Return.getCode()).append("'");
		}
		if (CheckingInType.Kit.getName().indexOf(trim) != -1) {
			sb.append(" or t.sheetType='").append(CheckingInType.Kit.getCode()).append("'");
		}
		return sb;
	}

	/**
	 * Ϊ�б��ѯ�������
	 * 
	 * @param qb
	 * @param key
	 *            void
	 */
	private void addOrderBy(QueryBuilder qb, InstorageKey key) {
		if (CheckIsNull.isEmpty(key.getSortColumnName())) {
			if (CheckingInType.Purchase.getCode().equals(key.getInstoType())) {
				qb.addOrderBy(" t.checkinDate asc");
			} else if (CheckingInType.Return.getCode().equals(key.getInstoType())) {
				qb.addOrderBy(" t.createDate asc");
			}
		} else {
			qb.addOrderBy(key.getSortColumnName() + " " + key.getSortType());
		}
	}

}
