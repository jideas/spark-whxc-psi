package com.spark.psi.inventory.internal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.key.store.GetUserStoreGuidsKey;
import com.spark.psi.base.utils.MaterialsProperyUtil;
import com.spark.psi.inventory.internal.entity.CheckInventory;
import com.spark.psi.inventory.internal.entity.CheckInventoryItem;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.internal.entity.OtherGoods;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.checkinventory.CheckInventoryItemKey;
import com.spark.psi.inventory.intf.key.checkinventory.CheckInventoryKey;
import com.spark.psi.inventory.intf.task.checkinventory.CheckInventoryItemTask;
import com.spark.psi.inventory.intf.task.checkinventory.CheckInventoryTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask;
import com.spark.psi.inventory.intf.task.inventory.StoStreamTask;
import com.spark.psi.inventory.intf.task.inventory.UpdateOtherGoodsTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask.DetItem;
import com.spark.psi.inventory.intf.util.checkinventory.StoreCheckDataOperator;
import com.spark.psi.inventory.service.orm.Orm_Store_Check;
import com.spark.psi.inventory.service.orm.Orm_Store_Check_Det;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItemDet;

/**
 * @author durendong
 * 
 */
public class StoreCheckService extends Service {

	protected final Orm_Store_Check storeCheck;
	protected final Orm_Store_Check_Det storeCheckDet;

	protected StoreCheckService(Orm_Store_Check storeCheck, Orm_Store_Check_Det storeCheckDet) {
		super("StoreCheckService");
		this.storeCheck = storeCheck;
		this.storeCheckDet = storeCheckDet;
	}

	/**
	 * 查询所有仓库下的盘点单
	 * 
	 * @author durendong
	 * 
	 */
	@Publish
	protected class QueryStoreCheck extends OneKeyResultListProvider<CheckInventory, CheckInventoryKey> {

		@Override
		protected void provide(Context context, CheckInventoryKey checkKey, List<CheckInventory> list) throws Throwable {
			List<CheckInventory> storeChecks = new ArrayList<CheckInventory>();
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();
			dnaSql.append("define query queryCheckInventory(@tenantId guid\n");
			paramList.add(context.find(Login.class).getTenantId());
			StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING, StoreStatus.STOP,
					StoreStatus.STOPANDONCOUNTING };
			GetUserStoreGuidsKey key = new GetUserStoreGuidsKey(statuss);
			GUID[] storeIds = context.find(GUID[].class, key);
			if (null != storeIds && storeIds.length > 0) {
				conditionSql.append(" and t.storeGuid in(");
				for (int i = 0; i < storeIds.length; i++) {
					dnaSql.append(",@storeGuid").append(i).append(" guid\n");
					if (0 != i) {
						conditionSql.append(",");
					}
					conditionSql.append("@storeGuid").append(i);
					paramList.add(storeIds[i]);
				}
				conditionSql.append(")\n");
			} else {
				conditionSql.append(" and 1=2\n");
			}
			if (null != checkKey.getSearceText()) {
				dnaSql.append(",@searchText string");
				conditionSql.append(" and (");
				conditionSql.append("t.storeName like '%'+@searchText+'%'");
				conditionSql.append(" or t.storePY like '%'+@searchText+'%'");
				conditionSql.append(" or t.checkOrdNo like '%'+@searchText+'%'");
				conditionSql.append(")");
				paramList.add(checkKey.getSearceText());
			}
			dnaSql.append(")\n");
			dnaSql.append(StoreCheckDataOperator.defaultSql());
			dnaSql.append(conditionSql);
			if (CheckIsNull.isNotEmpty(checkKey.getSortField())) {
				dnaSql.append("\n order by t.").append(checkKey.getSortField()).append(" ").append(
						checkKey.getSortType()).append("\n");
			} else {
				dnaSql.append("\n order by t.checkOrdState asc,t.startDate desc,t.checkOrdNo desc\n");
			}
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);

			for (int i = 0; i < paramList.size(); i++) {
				db.setArgumentValue(i, paramList.get(i));
			}
			try {
				RecordSet rs = db.executeQueryLimit(checkKey.getOffset(), checkKey.getCount());// dbc1.executeQuery();
				checkKey.setCount(db.rowCountOf());
				while (rs.next()) {
					storeChecks.add(StoreCheckDataOperator.getStoreCheck(rs));
				}
				for (CheckInventory storeCheck : storeChecks) {
					list.add(storeCheck);
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 根据单据Id查询盘点单信息
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author Wangtiancai
	 * @version 2012-3-27
	 */
	@Publish
	protected class GetCheckInventoryById extends OneKeyResultProvider<CheckInventory, GUID> {

		@Override
		protected CheckInventory provide(Context context, GUID recid) throws Throwable {
			CheckInventory checkInventory;
			ORMAccessor<CheckInventory> orm = context.newORMAccessor(storeCheck);
			try {
				checkInventory = orm.findByRECID(recid);
			} finally {
				orm.unuse();
			}
			return checkInventory;
		}
	}

	/**
	 * 删除
	 * 
	 * @author durendong
	 * 
	 */
	@Publish
	protected class DelStoreCheckTask extends TaskMethodHandler<CheckInventoryTask, Method> {

		protected DelStoreCheckTask() {
			super(Method.DELETE);
		}

		@Override
		protected void handle(Context context, CheckInventoryTask storeCheckTask) throws Throwable {
			ORMAccessor<CheckInventory> orm = context.newORMAccessor(storeCheck);
			try {
				orm.delete(storeCheckTask.getCheckInventoryEntity().getRecid());
				CheckInventoryItemTask iTask = new CheckInventoryItemTask();
				iTask.setList(storeCheckTask.getList());
				context.handle(iTask, Method.DELETE);
			} finally {
				orm.unuse();
			}
		}

	}

	/**
	 * 新增操作
	 * 
	 * @author durendong
	 * 
	 */
	@Publish
	protected class InsertStoreCheck extends TaskMethodHandler<CheckInventoryTask, Method> {

		protected InsertStoreCheck() {
			super(Method.INSERT);
		}

		@Override
		protected void handle(Context context, CheckInventoryTask task) throws Throwable {
			ORMAccessor<CheckInventory> orm = context.newORMAccessor(storeCheck);
			ORMAccessor<CheckInventoryItem> detOrm = context.newORMAccessor(storeCheckDet);
			List<CheckInventoryItem> list = task.getList();
			try {
				orm.insert(task.getCheckInventoryEntity());
				if (list != null && list.size() > 0) {
					for (CheckInventoryItem storeCheck : list) {
						detOrm.insert(storeCheck);
					}
				}
			} finally {
				orm.unuse();
				detOrm.unuse();
			}

		}
	}

	/**
	 * 保存
	 * 
	 * @author durendong
	 * 
	 */
	@Publish
	protected class ModifyStoreCheck extends TaskMethodHandler<CheckInventoryTask, Method> {

		protected ModifyStoreCheck() {
			super(Method.MODIFY);
		}

		@Override
		protected void handle(Context context, CheckInventoryTask task) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			dnaSql
					.append("define update updateInventoryCount(@tenantsId guid,@sheetId guid,@checkProfit int,@checkDeficient int,@oldstatus string");
			params.add(context.find(Login.class).getTenantId());
			params.add(task.getCheckInventoryEntity().getRecid());
			params.add(task.getCheckInventoryEntity().getCheckProfit());
			params.add(task.getCheckInventoryEntity().getCheckDeficient());
			params.add(InventoryCountStatus.Counting.getCode());
			dnaSql.append(",@memo string");
			params.add(task.getCheckInventoryEntity().getRemark());
			dnaSql.append(")\n");
			dnaSql.append("begin\n");
			dnaSql.append("update SA_STORE_CHECK as t\n");
			dnaSql.append("set remark=@memo\n");
			dnaSql.append(",checkProfit=@checkProfit\n");
			dnaSql.append(",checkDeficient=@checkDeficient\n");
			dnaSql.append("where t.tenantsGuid=@tenantsId\n");
			dnaSql.append("and t.recid=@sheetId\n");
			dnaSql.append(" and t.checkOrdState=@oldstatus \n");
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);
			for (int i = 0; i < params.size(); i++) {
				db.setArgumentValue(i, params.get(i));
			}
			try {
				task.setCount(db.executeUpdate());
				if (task.getCount() > 0) {
					ORMAccessor<CheckInventoryItem> detOrm = context.newORMAccessor(storeCheckDet);
					List<CheckInventoryItem> list = task.getList();
					try {
						if (list != null && list.size() > 0) {
							deleteDetailList(context, task);
							for (CheckInventoryItem storeCheck : list) {
								detOrm.insert(storeCheck);
							}
						}
					} finally {
						detOrm.unuse();
					}
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 完成操作
	 * 
	 * @author durendong
	 * 
	 */
	@Publish
	protected class FinishStoreCheck extends TaskMethodHandler<CheckInventoryTask, Method> {

		protected FinishStoreCheck() {
			super(Method.MODIFYstatus);
		}

		@Override
		protected void handle(Context context, CheckInventoryTask task) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			dnaSql
					.append("define update updateSheetstatus(@tenantsId guid,@recid guid,@checkOrdState string,@oldstatus string,@endDate date) \n");
			dnaSql.append(" begin \n");
			dnaSql.append(" update SA_STORE_CHECK as t set checkOrdState=@checkOrdState\n");
			dnaSql.append(",endDate=@endDate\n");
			dnaSql.append(" where t.tenantsGuid=@tenantsId \n");
			dnaSql.append(" and t.recid=@recid \n");
			dnaSql.append(" and t.checkOrdState=@oldstatus \n");
			dnaSql.append(" end ");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(context.find(Login.class).getTenantId(), task.getSheetId(),
					InventoryCountStatus.Counted.getCode(), InventoryCountStatus.Counting.getCode(), new Date()
							.getTime());
			try {
				task.setCount(db.executeUpdate());
				if (task.getCount() > 0) {
					updateStorage(context, task);
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 更新库存信息
	 * 
	 * @param context
	 * @param task
	 *            void
	 */
	public void updateStorage(Context context, CheckInventoryTask task) {
		CheckInventory checkInventory = context.find(CheckInventory.class, task.getSheetId());
		CheckInventoryItemKey iKey = new CheckInventoryItemKey();
		iKey.setCheckOrdGuid(task.getSheetId());
		List<CheckInventoryItem> list = context.getList(CheckInventoryItem.class, iKey);
		if (null == checkInventory || CheckIsNull.isEmpty(list)) {
			return;
		}
		for (CheckInventoryItem item : list) {
			if (InventoryCountType.Kit.getCode().equals(checkInventory.getCheckObj())) {
				OtherGoods otherGoods = new OtherGoods();
				otherGoods.setDescription(item.getGoodsAttr());
				otherGoods.setInit(false);
				otherGoods.setName(item.getGoodsName());
				otherGoods.setNumber(DoubleUtil.sub(item.getRealCount(), item.getCarryCount()));
				otherGoods.setUnit(item.getUnit());
				UpdateOtherGoodsTask oTask = new UpdateOtherGoodsTask(checkInventory.getStoreGuid(), otherGoods);
				oTask.setInit(false);
				context.handle(oTask, Method.MODIFY);
			} else if (InventoryCountType.Materials.getCode().equals(checkInventory.getCheckObj())) {
				InventoryBusTask oTask = new InventoryBusTask(checkInventory.getStoreGuid(), item.getGoodsGuid());
				oTask.setChangeCount(DoubleUtil.round(DoubleUtil.sub(item.getRealCount(), item.getCarryCount())));
				oTask.setCount(true);
				oTask.setUpdateAvgPrice(true);
				if (null != oTask.getChangeCount() && oTask.getChangeCount() > 0) {
					setShelfItem(oTask, task.getInventorysAdd(), checkInventory.getStoreGuid(), item.getGoodsGuid(),
							false);
				}
				if (null != oTask.getChangeCount() && oTask.getChangeCount() < 0) {
					setShelfItem(oTask, task.getInventorysSub(), checkInventory.getStoreGuid(), item.getGoodsGuid(),
							true);
				}
				if ((null != oTask.getChangeCount() && oTask.getChangeCount() != 0)
						|| (null != oTask.getChangeAmount() && 0 != oTask.getChangeAmount())) {
//						System.out.println(item.getGoodsName());
					context.handle(oTask);
				}

				StoStreamTask sTask = new StoStreamTask();
				InventoryLogEntity stoStream = new InventoryLogEntity();
				stoStream.setCreatedDate(new Date().getTime());
				stoStream.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId())
						.getName());
				stoStream.setCreatedDate(new Date().getTime());
				stoStream.setStockId(item.getGoodsGuid());
				stoStream.setStoreId(checkInventory.getStoreGuid());
				stoStream.setInventoryType(InventoryType.Materials.getCode());
				MaterialsItem materials = context.find(MaterialsItem.class, item.getGoodsGuid());
				if (null != materials) {
					stoStream.setProperties(materials.getSpec());
					stoStream.setName(materials.getMaterialName());
					stoStream.setCode(materials.getMaterialCode());
					stoStream.setCategoryId(materials.getCategoryId());
					stoStream.setScale(materials.getScale());
					stoStream.setStockNo(materials.getMaterialNo());
					stoStream.setUnit(materials.getMaterialUnit());
				}

				if (0 == oTask.getChangeCount()) {
					continue;
				}
				if (oTask.getChangeCount() > 0) {
					stoStream.setInstoCount(oTask.getChangeCount());
					stoStream.setLogType(InventoryLogType.CheckProfit.getCode());
				} else {
					stoStream.setOutstoCount(DoubleUtil.mul(-1, oTask.getChangeCount()));
					stoStream.setLogType(InventoryLogType.CheckDeficient.getCode());
				}

				stoStream.setOrderNo(checkInventory.getCheckOrdNo());
				stoStream.setOrderId(checkInventory.getRecid());
				stoStream.setId(context.newRECID());
				sTask.setStoStream(stoStream);
				context.handle(sTask, StoStreamTask.Task.add);
			}
		}
	}

	private void setShelfItem(InventoryBusTask task, DistributeInventoryItem[] inventoryItems, GUID storeId,
			GUID goodsId, boolean sub) {
		List<DetItem> dets = new ArrayList<DetItem>();
		for (DistributeInventoryItem iteminfo : inventoryItems) {
			if (!iteminfo.getStockId().equals(goodsId)) {
				continue;
			}
			for (DistributeInventoryItemDet item : iteminfo.getInventoryDetItems()) {
				double count = item.getCount();
				if (sub) {
					count = DoubleUtil.sub(0, item.getDistributeCount());
				}
				DetItem det = task.new DetItem(item.getShelfId(), item.getShelfNo(), item.getTiersNo(), iteminfo
						.getStockId(), count, item.getProduceDate(), storeId);
				dets.add(det);
			}
		}
		task.setDets(dets.toArray(new DetItem[dets.size()]));
	}

	/**
	 * 删除相关明细
	 * 
	 * @param context
	 * @param task
	 *            void
	 */
	public void deleteDetailList(Context context, CheckInventoryTask task) {

		StringBuilder dnaSql = new StringBuilder();
		dnaSql.append("define delete deleteInventoryCountDet(@tenantsId guid,@sheetId guid)\n");
		dnaSql.append("begin\n");
		dnaSql.append("delete from sa_store_check_det as t\n");
		dnaSql.append("where t.tenantsGuid=@tenantsId\n");
		dnaSql.append("and t.checkOrdGuid=@sheetId\n");
		dnaSql.append("end");

		DBCommand db = context.prepareStatement(dnaSql);
		db.setArgumentValues(context.find(Login.class).getTenantId(), task.getCheckInventoryEntity().getRecid());
		try {
			db.executeUpdate();
		} finally {
			db.unuse();
		}
	}

}
