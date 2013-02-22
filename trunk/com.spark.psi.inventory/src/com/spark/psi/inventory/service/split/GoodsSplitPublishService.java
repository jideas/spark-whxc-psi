package com.spark.psi.inventory.service.split;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.dnasql.DeleteSqlBuilder;
import com.spark.common.utils.dnasql.InsertSqlBuilder;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.inventory.intf.task.instorage.InstoAddTask;
import com.spark.psi.inventory.split.publish.GoodsSplitDet_GoodsImpl;
import com.spark.psi.inventory.split.publish.GoodsSplitDet_MaterialImpl;
import com.spark.psi.inventory.split.publish.GoodsSplitInfoImpl;
import com.spark.psi.inventory.split.publish.GoodsSplitItemImpl;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.deliver.entity.DeliverInfoItem;
import com.spark.psi.publish.inventory.checkout.task.RealGoodsCheckOutTask;
import com.spark.psi.publish.inventory.checkout.task.RealGoodsCheckOutTaskItem;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Goods;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Material;
import com.spark.psi.publish.split.entity.GoodsSplitInfo;
import com.spark.psi.publish.split.entity.GoodsSplitItem;
import com.spark.psi.publish.split.key.GetGoodsSplitBillListKey;
import com.spark.psi.publish.split.task.DeleteGoodsSplitBillTask;
import com.spark.psi.publish.split.task.GoodsSplitDistributTask;
import com.spark.psi.publish.split.task.GoodsSplitDistributeEntity;
import com.spark.psi.publish.split.task.GoodsSplitTaskDet;
import com.spark.psi.publish.split.task.UpdateGoodsSplitBillTask;
import com.spark.psi.publish.split.task.UpdateGoodsSplitStatusTask;

public class GoodsSplitPublishService extends Service {

	protected GoodsSplitPublishService() {
		super("com.spark.psi.inventory.service.split.GoodsSplitPublishService");
	}

	/**
	 * 详情
	 */
	@Publish
	protected class FindGoodsSplitBill extends
			OneKeyResultProvider<GoodsSplitInfo, GUID> {

		@Override
		protected GoodsSplitInfo provide(Context context, GUID key)
				throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.GoodsSplitSheet.getTableName(),
					"t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.billNo", "billNo");
			qb.addColumn("t.creator", "creator");
			qb.addColumn("t.creatorId", "creatorId");
			qb.addColumn("t.createDate", "createDate");
			qb.addColumn("t.approvalPerson", "approvalPerson");
			qb.addColumn("t.approvalPersonId", "approvalPersonId");
			qb.addColumn("t.approvalDate", "approvalDate");
			qb.addColumn("t.distributPerson", "distributPerson");
			qb.addColumn("t.distributPersonId", "distributPersonId");
			qb.addColumn("t.distributDate", "distributDate");
			qb.addColumn("t.status", "status");
			qb.addColumn("t.rejectReason", "rejectReason");
			qb.addColumn("t.remark", "remark");
			qb.addColumn("t.storeId", "storeId");
			qb.addColumn("t.finishDate", "finishDate");
			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.RECID", "@id");

			RecordSet rs = qb.getRecord();
			GoodsSplitInfoImpl item = new GoodsSplitInfoImpl();
			if (rs.next()) {
				int index = 0;
				item.setRECID(rs.getFields().get(index++).getGUID());
				item.setBillNo(rs.getFields().get(index++).getString());
				item.setCreator(rs.getFields().get(index++).getString());
				item.setCreatorId(rs.getFields().get(index++).getGUID());
				item.setCreateDate(rs.getFields().get(index++).getDate());
				item.setApprovalPerson(rs.getFields().get(index++).getString());
				item.setApprovalPersonId(rs.getFields().get(index++).getGUID());
				item.setApprovalDate(rs.getFields().get(index++).getDate());
				item
						.setDistributPerson(rs.getFields().get(index++)
								.getString());
				item
						.setDistributPersonId(rs.getFields().get(index++)
								.getGUID());
				item.setDistributDate(rs.getFields().get(index++).getDate());
				item.setStatus(rs.getFields().get(index++).getString());
				item.setRejectReason(rs.getFields().get(index++).getString());
				item.setRemark(rs.getFields().get(index++).getString());
				item.setStoreId(rs.getFields().get(index++).getGUID());
				item.setFinishDate(rs.getFields().get(index++).getDate());
			}
			if (item.getRECID() == null) {
				return null;
			}
			fillGoodsDetails(context, item);
			fillMaterialDetails(context, item);

			return item;
		}

		private void fillMaterialDetails(Context context,
				GoodsSplitInfoImpl item) {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.GoodsSplitDet_Material
					.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.billId", "billId");
			qb.addColumn("t.materialId", "materialId");
			qb.addColumn("t.standardCount", "standardCount");
			qb.addColumn("t.mcount", "mcount");
			qb.addColumn("t.mname", "mname");
			qb.addColumn("t.munit", "munit");
			qb.addColumn("t.mspec", "mspec");
			qb.addColumn("t.mcode", "mcode");
			qb.addColumn("t.mnumber", "mnumber");
			qb.addArgs("id", qb.guid, item.getRECID());
			qb.addEquals("t.billId", "@id");
			RecordSet rs = qb.getRecord();
			List<GoodsSplitDet_Material> list = new ArrayList<GoodsSplitDet_Material>();
			while (rs.next()) {
				int index = 0;
				GoodsSplitDet_MaterialImpl det = new GoodsSplitDet_MaterialImpl();
				det.setRECID(rs.getFields().get(index++).getGUID());
				det.setBillId(rs.getFields().get(index++).getGUID());
				det.setMaterialId(rs.getFields().get(index++).getGUID());
				det.setScount(rs.getFields().get(index++).getDouble());
				det.setMcount(rs.getFields().get(index++).getDouble());
				det.setMname(rs.getFields().get(index++).getString());
				det.setMunit(rs.getFields().get(index++).getString());
				det.setMspec(rs.getFields().get(index++).getString());
				det.setMcode(rs.getFields().get(index++).getString());
				det.setMnumber(rs.getFields().get(index++).getString());
				list.add(det);
			}
			item.setMaterialDets(list);
		}

		private void fillGoodsDetails(Context context, GoodsSplitInfoImpl item) {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.GoodsSplitDet_Goods
					.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.goodsId", "goodsId");
			qb.addColumn("t.billId", "billId");
			qb.addColumn("t.gcount", "gcount");
			qb.addColumn("t.reason", "reason");
			qb.addColumn("t.goodsName", "goodsName");
			qb.addColumn("t.goodsSpec", "goodsSpec");
			qb.addColumn("t.goodsUnit", "goodsUnit");
			qb.addColumn("t.goodsCode", "goodsCode");
			qb.addColumn("t.goodsNo", "goodsNo");

			qb.addArgs("id", qb.guid, item.getRECID());
			qb.addEquals("t.billId", "@id");
			RecordSet rs = qb.getRecord();
			List<GoodsSplitDet_Goods> list = new ArrayList<GoodsSplitDet_Goods>();
			while (rs.next()) {
				GoodsSplitDet_GoodsImpl det = new GoodsSplitDet_GoodsImpl();
				int index = 0;
				det.setRECID(rs.getFields().get(index++).getGUID());
				det.setGoodsId(rs.getFields().get(index++).getGUID());
				det.setBillId(rs.getFields().get(index++).getGUID());
				det.setGcount(rs.getFields().get(index++).getDouble());
				det.setReason(rs.getFields().get(index++).getString());
				det.setGoodsName(rs.getFields().get(index++).getString());
				det.setGoodsSpec(rs.getFields().get(index++).getString());
				det.setGoodsUnit(rs.getFields().get(index++).getString());
				det.setGoodsCode(rs.getFields().get(index++).getString());
				det.setGoodsNo(rs.getFields().get(index++).getString());
				list.add(det);
			}
			item.setGoodsDets(list);
		}
	}

	/**
	 * 主表详情
	 */
	@Publish
	protected class FindGoodsSplitBill2 extends
			TwoKeyResultProvider<GoodsSplitInfo, GUID, Boolean> {

		@Override
		protected GoodsSplitInfo provide(Context context, GUID key, Boolean b)
				throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.GoodsSplitSheet.getTableName(),
					"t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.billNo", "billNo");
			qb.addColumn("t.creator", "creator");
			qb.addColumn("t.creatorId", "creatorId");
			qb.addColumn("t.createDate", "createDate");
			qb.addColumn("t.approvalPerson", "approvalPerson");
			qb.addColumn("t.approvalPersonId", "approvalPersonId");
			qb.addColumn("t.approvalDate", "approvalDate");
			qb.addColumn("t.distributPerson", "distributPerson");
			qb.addColumn("t.distributPersonId", "distributPersonId");
			qb.addColumn("t.distributDate", "distributDate");
			qb.addColumn("t.status", "status");
			qb.addColumn("t.rejectReason", "rejectReason");
			qb.addColumn("t.remark", "remark");
			qb.addColumn("t.storeId", "storeId");
			qb.addColumn("t.finishDate", "finishDate");
			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.RECID", "@id");

			RecordSet rs = qb.getRecord();
			GoodsSplitInfoImpl item = new GoodsSplitInfoImpl();
			if (rs.next()) {
				int index = 0;
				item.setRECID(rs.getFields().get(index++).getGUID());
				item.setBillNo(rs.getFields().get(index++).getString());
				item.setCreator(rs.getFields().get(index++).getString());
				item.setCreatorId(rs.getFields().get(index++).getGUID());
				item.setCreateDate(rs.getFields().get(index++).getDate());
				item.setApprovalPerson(rs.getFields().get(index++).getString());
				item.setApprovalPersonId(rs.getFields().get(index++).getGUID());
				item.setApprovalDate(rs.getFields().get(index++).getDate());
				item
						.setDistributPerson(rs.getFields().get(index++)
								.getString());
				item
						.setDistributPersonId(rs.getFields().get(index++)
								.getGUID());
				item.setDistributDate(rs.getFields().get(index++).getDate());
				item.setStatus(rs.getFields().get(index++).getString());
				item.setRejectReason(rs.getFields().get(index++).getString());
				item.setRemark(rs.getFields().get(index++).getString());
				item.setStoreId(rs.getFields().get(index++).getGUID());
				item.setFinishDate(rs.getFields().get(index++).getDate());
			}
			return item;
		}
	}

	/**
	 * 分页查询列表
	 */
	@Publish
	protected class GetGoodsSplitBillList
			extends
			OneKeyResultProvider<ListEntity<GoodsSplitItem>, GetGoodsSplitBillListKey> {

		@Override
		protected ListEntity<GoodsSplitItem> provide(Context context,
				GetGoodsSplitBillListKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.GoodsSplitSheet.getTableName(),
					"t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.billNo", "billNo");
			qb.addColumn("t.creator", "creator");
			qb.addColumn("t.creatorId", "creatorId");
			qb.addColumn("t.createDate", "createDate");
			qb.addColumn("t.approvalPerson", "approvalPerson");
			qb.addColumn("t.approvalPersonId", "approvalPersonId");
			qb.addColumn("t.approvalDate", "approvalDate");
			qb.addColumn("t.distributPerson", "distributPerson");
			qb.addColumn("t.distributPersonId", "distributPersonId");
			qb.addColumn("t.distributDate", "distributDate");
			qb.addColumn("t.status", "status");
			qb.addColumn("t.rejectReason", "rejectReason");
			qb.addColumn("t.remark", "remark");
			qb.addColumn("t.storeId", "storeId");
			qb.addColumn("t.finishDate", "finishDate");
			if (key.getStatuses() != null) {
				List<String> list = new ArrayList<String>();
				int index = 0;
				for (GoodsSplitStatus status : key.getStatuses()) {
					qb.addArgs("status" + index, qb.STRING, status.getCode());
					list.add("@status" + index++);
				}
				qb.addIn("t.status", list);
			}
			if (CheckIsNull.isNotEmpty(key.getSearchText())) {
				StringBuilder sb = new StringBuilder();
				qb.addArgs("searchKey", qb.STRING, key.getSearchText());
				sb.append(" (t.billNo like '%'+@searchKey+'%' ");
				sb.append(" or t.creator like '%'+@searchKey+'%' ");
				sb.append(" or t.approvalPerson like '%'+@searchKey+'%' ");
				sb.append(" or t.distributPerson like '%'+@searchKey+'%' ");
				sb.append(")");
				qb.addCondition(sb.toString());
			}
			if (key.getBeginTime() > 0) {
				qb.addArgs("beginTime", qb.DATE, key.getBeginTime());
				qb.addGreaterThanOrEquals("t.createDate", "@beginTime");
			}
			if (key.getEndTime() > 0) {
				qb.addArgs("endTime", qb.DATE, key.getEndTime());
				qb.addLessThan("t.createDate", "@endTime");
			}
			// 排序
			if (key.getSortField() != null) {
				if (key.getSortType() == SortType.Desc) {
					qb.addOrderBy(key.getSortField().getFieldName() + " desc ");
				} else {
					qb.addOrderBy(key.getSortField().getFieldName() + " asc ");
				}
			} else {
				qb.addOrderBy("t.checkinDate desc");
			}
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			List<GoodsSplitItem> list = new ArrayList<GoodsSplitItem>();
			while (rs.next()) {
				GoodsSplitItemImpl item = new GoodsSplitItemImpl();
				int index = 0;
				item.setRECID(rs.getFields().get(index++).getGUID());
				item.setBillNo(rs.getFields().get(index++).getString());
				item.setCreator(rs.getFields().get(index++).getString());
				item.setCreatorId(rs.getFields().get(index++).getGUID());
				item.setCreateDate(rs.getFields().get(index++).getDate());
				item.setApprovalPerson(rs.getFields().get(index++).getString());
				item.setApprovalPersonId(rs.getFields().get(index++).getGUID());
				item.setApprovalDate(rs.getFields().get(index++).getDate());
				item
						.setDistributPerson(rs.getFields().get(index++)
								.getString());
				item
						.setDistributPersonId(rs.getFields().get(index++)
								.getGUID());
				item.setDistributDate(rs.getFields().get(index++).getDate());
				item.setStatus(GoodsSplitStatus.getStatus(
						rs.getFields().get(index++).getString()).getTitle());
				item.setRejectReason(rs.getFields().get(index++).getString());
				item.setRemark(rs.getFields().get(index++).getString());
				item.setStoreId(rs.getFields().get(index++).getGUID());
				item.setFinishDate(rs.getFields().get(index++).getDate());
				list.add(item);
			}
			return new ListEntity<GoodsSplitItem>(list, list.size());
		}
	}

	/**
	 * 新增或者修改
	 */
	@Publish
	protected class UpdateGoodsSplitBill extends
			SimpleTaskMethodHandler<UpdateGoodsSplitBillTask> {

		@Override
		protected void handle(Context context, UpdateGoodsSplitBillTask task)
				throws Throwable {
			if (task.getRECID() == null) {
				task.setRECID(context.newRECID());
				boolean b = createBill(context, task);
				if (b) {
					insertGoodsDetail(context, task);
					insertMaterialDetail(context, task);
				}
			} else {
				boolean b = updateBill(context, task);
				if (b) {
					clearGoodsDetail(context, task.getRECID());
					clearMaterialDetail(context, task.getRECID());
					insertGoodsDetail(context, task);
					insertMaterialDetail(context, task);
				}
			}
		}

		private void insertMaterialDetail(Context context,
				UpdateGoodsSplitBillTask task) {
			if (task.getGoodsDets() == null || task.getGoodsDets().isEmpty()) {
				return;
			}
			for (GoodsSplitTaskDet det : task.getMaterialDets()) {
				InsertSqlBuilder ib = new InsertSqlBuilder(context);
				ib.setTable(ERPTableNames.Inventory.GoodsSplitDet_Material
						.getTableName());
				MaterialsItem material = context.find(MaterialsItem.class, det
						.getId());
				ib.addColumn("RECID", ib.guid, context.newRECID());
				ib.addColumn("billId", ib.guid, task.getRECID());
				ib.addColumn("materialId", ib.guid, det.getId());
				ib.addColumn("standardCount", ib.DOUBLE, det.getsCount());
				ib.addColumn("mcount", ib.DOUBLE, det.getCount());
				ib.addColumn("mname", ib.STRING, material.getMaterialName());
				ib.addColumn("munit", ib.STRING, material.getMaterialUnit());
				ib.addColumn("mspec", ib.STRING, material.getSpec());
				ib.addColumn("mcode", ib.STRING, material.getMaterialCode());
				ib.addColumn("mnumber", ib.STRING, material.getMaterialNo());
				ib.execute();
			}
		}

		private void insertGoodsDetail(Context context,
				UpdateGoodsSplitBillTask task) {
			if (task.getMaterialDets() == null
					|| task.getMaterialDets().isEmpty()) {
				return;
			}
			for (GoodsSplitTaskDet det : task.getGoodsDets()) {
				InsertSqlBuilder ib = new InsertSqlBuilder(context);
				ib.setTable(ERPTableNames.Inventory.GoodsSplitDet_Goods
						.getTableName());
				GoodsItem goods = context.find(GoodsItem.class, det.getId());
				ib.addColumn("RECID", ib.guid, context.newRECID());
				ib.addColumn("goodsId", ib.guid, det.getId());
				ib.addColumn("billId", ib.guid, task.getRECID());
				ib.addColumn("gcount", ib.DOUBLE, det.getCount());
				ib.addColumn("reason", ib.STRING, det.getReason());
				ib.addColumn("goodsName", ib.STRING, goods.getGoodsName());
				ib.addColumn("goodsSpec", ib.STRING, goods.getSpec());
				ib.addColumn("goodsUnit", ib.STRING, goods.getGoodsUnit());
				ib.addColumn("goodsCode", ib.STRING, goods.getGoodsCode());
				ib.addColumn("goodsNo", ib.STRING, goods.getGoodsNo());
				ib.execute();
			}
		}

		private boolean updateBill(Context context,
				UpdateGoodsSplitBillTask task) {
			UpdateSqlBuilder ib = new UpdateSqlBuilder(context);
			ib.setTable(ERPTableNames.Inventory.GoodsSplitSheet.getTableName());
			ib.addColumn("remark", ib.STRING, task.getRemark());
			ib.addColumn("storeId", ib.guid, task.getStoreId());
			ib.addColumn("status", ib.STRING, task.getStatus().getCode());
			ib.addCondition("id", ib.guid, task.getRECID(), "t.RECID = @id");
			ib.addCondition(" t.status='"
					+ GoodsSplitStatus.Submiting.getCode() + "' or t.status='"
					+ GoodsSplitStatus.Rejected.getCode() + "' ");
			int i = ib.execute();
			return i == 1;
		}

		private boolean createBill(Context context,
				UpdateGoodsSplitBillTask task) {
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Inventory.GoodsSplitSheet.getTableName());
			ib.addColumn("RECID", ib.guid, task.getRECID());
			String billNo = context.find(String.class,
					SheetNumberType.GoodsSplit);
			ib.addColumn("billNo", ib.STRING, billNo);
			ib.addColumn("creator", ib.STRING, user.getName());
			ib.addColumn("creatorId", ib.guid, user.getId());
			ib.addColumn("createDate", ib.DATE, System.currentTimeMillis());
			ib.addColumn("status", ib.STRING, task.getStatus().getCode());
			ib.addColumn("remark", ib.STRING, task.getRemark());
			ib.addColumn("storeId", ib.guid, task.getStoreId());
			int i = ib.execute();
			return i == 1;
		}
	}

	private void clearMaterialDetail(Context context, GUID id) {
		DeleteSqlBuilder db = new DeleteSqlBuilder(context);
		db.setTable(ERPTableNames.Inventory.GoodsSplitDet_Material
				.getTableName());
		db.addEquals("billId", db.guid, id);
		db.execute();
	}

	private void clearGoodsDetail(Context context, GUID id) {
		DeleteSqlBuilder db = new DeleteSqlBuilder(context);
		db.setTable(ERPTableNames.Inventory.GoodsSplitDet_Goods.getTableName());
		db.addEquals("billId", db.guid, id);
		db.execute();
	}

	/**
	 * 删除
	 */
	@Publish
	protected class DeleteGoodsSplitBill extends
			SimpleTaskMethodHandler<DeleteGoodsSplitBillTask> {

		@Override
		protected void handle(Context context, DeleteGoodsSplitBillTask task)
				throws Throwable {
			DeleteSqlBuilder db = new DeleteSqlBuilder(context);
			db.setTable(ERPTableNames.Inventory.GoodsSplitSheet.getTableName());
			db.addEquals("RECID", db.guid, task.getRecid());
			int i = db.execute();
			if (i == 1) {
				clearGoodsDetail(context, task.getRecid());
				clearMaterialDetail(context, task.getRecid());
			}
		}
	}

	/**
	 * 状态变更
	 */
	@Publish
	protected class UpdateGoodsSplitStatus extends
			SimpleTaskMethodHandler<UpdateGoodsSplitStatusTask> {

		@Override
		protected void handle(Context context, UpdateGoodsSplitStatusTask task)
				throws Throwable {
			GoodsSplitStatus status = task.getStatus();
			if (null == status) {
				return;
			}
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Inventory.GoodsSplitSheet.getTableName());
			ub.addColumn("status", ub.STRING, task.getStatus().getCode());
			ub.addCondition("id", ub.guid, task.getId(), "t.RECID = @id");
			Employee user = context.find(Employee.class, context.find(
					Login.class).getEmployeeId());
			switch (status) {
			case Rejected:
				ub.addColumn("rejectReason", ub.STRING, task.getReason());
				ub.addColumn("approvalPerson", ub.STRING, user.getName());
				ub.addColumn("approvalPersonId", ub.guid, user.getId());
				ub.addColumn("approvalDate", ub.DATE, System
						.currentTimeMillis());
				ub.addCondition(" t.status='"
						+ GoodsSplitStatus.Approvaling.getCode() + "' ");
				break;
			case Approvaling:
				ub.addCondition(" (t.status='"
						+ GoodsSplitStatus.Submiting.getCode()
						+ "' or  t.status='"
						+ GoodsSplitStatus.Rejected.getCode() + "') ");
				break;
			case Approvaled:
				ub.addColumn("approvalPerson", ub.STRING, user.getName());
				ub.addColumn("approvalPersonId", ub.guid, user.getId());
				ub.addColumn("approvalDate", ub.DATE, System
						.currentTimeMillis());
				ub.addCondition(" t.status='"
						+ GoodsSplitStatus.Approvaling.getCode() + "' ");
				break;
			case Checkingin:
				break;
			case Distributed:
				ub.addColumn("distributPerson", ub.STRING, user.getName());
				ub.addColumn("distributPersonId", ub.guid, user.getId());
				ub.addColumn("distributDate", ub.DATE, System
						.currentTimeMillis());
				ub.addCondition(" t.status='"
						+ GoodsSplitStatus.Approvaled.getCode() + "' ");
				break;
			case Finished:
				break;
			}
			int count = ub.execute();
			if (1 != count) {
				throw new Throwable("更新单据状态失败，请重试！");
			}
			if (GoodsSplitStatus.Approvaled == task.getStatus()) {
				insertCheckOutSheet(context, task);
			}
		}
	}

	/**
	 * 生成入库需求
	 */
	@Publish
	protected class CreateCheckinSheetHandle extends
			SimpleTaskMethodHandler<GoodsSplitDistributTask> {

		@Override
		protected void handle(Context context, GoodsSplitDistributTask task)
				throws Throwable {
			if (CheckIsNull.isEmpty(task.getList())) {
				return;
			}
			UpdateGoodsSplitStatusTask st = new UpdateGoodsSplitStatusTask(task
					.getBillId(), GoodsSplitStatus.Distributed);
			context.handle(st);
			for (GoodsSplitDistributeEntity ss : task.getList()) {
				Instorage info = new Instorage();
				info.setRelaBillsId(task.getBillId());
				info.setRelaBillsNo(task.getBillNo());
				info.setStoreId(ss.getStoreId());
				Store store = context.find(Store.class, ss.getStoreId());
				info.setStoreName(store.getName());
				info.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
				info.setRemark(task.getRemark());
				info.setCheckinDate(System.currentTimeMillis());
				info.setPurchaseDate(System.currentTimeMillis());
				Double totalCount = 0d, totalAmount = 0d;
				List<InstorageItem> dets = getInstoDets(context, ss,
						totalCount, totalAmount);
				info.setBillsCount(totalCount);
				info.setBillsAmount(totalAmount);
				InstoAddTask add = new InstoAddTask(info, dets);
				context.handle(add, CheckingInType.GoodsSplit);
			}
		}

		private List<InstorageItem> getInstoDets(Context context,
				GoodsSplitDistributeEntity ss, Double totalCount,
				Double totalAmount) {
			List<InstorageItem> list = new ArrayList<InstorageItem>();
			for (GoodsSplitTaskDet d : ss.getDets()) {
				InstorageItem det = new InstorageItem();
				det = new InstorageItem();
				det.setGoodsId(d.getId());
				MaterialsItem m = context.find(MaterialsItem.class, d.getId());
				det.setGoodsCode(m.getMaterialCode());
				det.setGoodsNo(m.getMaterialNo());
				det.setGoodsName(m.getMaterialName());
				det.setGoodsSpec(m.getSpec());
				det.setUnit(m.getMaterialUnit());
				det.setPrice(m.getAvgBuyPrice());
				det.setCount(d.getCount());
				det.setScale(2);
				det.setAmount(DoubleUtil.mul(d.getCount(), m.getAvgBuyPrice()));
				list.add(det);
				totalAmount += det.getAmount();
				totalCount += d.getCount();
			}
			return list;
		}
	}

	public void insertCheckOutSheet(Context context,
			UpdateGoodsSplitStatusTask task) {
		GoodsSplitInfo info = context.find(GoodsSplitInfo.class, task.getId());
		RealGoodsCheckOutTask ot = new RealGoodsCheckOutTask();
		ot.setRelaBillsId(info.getRECID());
		ot.setRelaBillsNo(info.getBillNo());
		ot.setRemark(info.getRemark());
		ot.setStoreId(Store.GoodsStoreId);
		List<RealGoodsCheckOutTaskItem> items = new ArrayList<RealGoodsCheckOutTaskItem>();
		for (GoodsSplitDet_Goods i : info.getGoodsDets()) {

			RealGoodsCheckOutTaskItem ri = new RealGoodsCheckOutTaskItem();

			ri.setCount(i.getGcount());
			ri.setGoodsId(i.getGoodsId());
			ri.setGoodsName(i.getGoodsName());
			ri.setGoodsSpec(i.getGoodsSpec());
			GoodsItem gi = context.find(GoodsItem.class, i.getGoodsId());
			ri.setGoodsScale(gi.getScale());
			ri.setGoodsNo(gi.getGoodsNo());
			ri.setGoodsUnit(gi.getGoodsUnit());
			ri.setPrice(gi.getStandardCost());
			ri.setAmount(ri.getCount() * ri.getPrice());

			items.add(ri);

		}
		ot.setItems(items);
		context.handle(ot);

	}
}
