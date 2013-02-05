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
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.psi.inventory.split.publish.GoodsSplitDet_GoodsImpl;
import com.spark.psi.inventory.split.publish.GoodsSplitDet_MaterialImpl;
import com.spark.psi.inventory.split.publish.GoodsSplitInfoImpl;
import com.spark.psi.inventory.split.publish.GoodsSplitItemImpl;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Goods;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Material;
import com.spark.psi.publish.split.entity.GoodsSplitInfo;
import com.spark.psi.publish.split.entity.GoodsSplitItem;
import com.spark.psi.publish.split.key.GetGoodsSplitBillListKey;
import com.spark.psi.publish.split.task.DeleteGoodsSplitBillTask;
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
			qb.addColumn("t.mcount", "mcount");
			qb.addColumn("t.mname", "mname");
			qb.addColumn("t.munit", "munit");
			qb.addColumn("t.mspec", "mspec");
			qb.addArgs("id", qb.guid, item.getRECID());
			qb.addEquals("t.RECID", "@id");
			RecordSet rs = qb.getRecord();
			List<GoodsSplitDet_Material> list = new ArrayList<GoodsSplitDet_Material>();
			while (rs.next()) {
				int index = 0;
				GoodsSplitDet_MaterialImpl det = new GoodsSplitDet_MaterialImpl();
				det.setRECID(rs.getFields().get(index++).getGUID());
				det.setBillId(rs.getFields().get(index++).getGUID());
				det.setMaterialId(rs.getFields().get(index++).getGUID());
				det.setMcount(rs.getFields().get(index++).getDouble());
				det.setMname(rs.getFields().get(index++).getString());
				det.setMunit(rs.getFields().get(index++).getString());
				det.setMspec(rs.getFields().get(index++).getString());
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

			qb.addArgs("id", qb.guid, item.getRECID());
			qb.addEquals("t.RECID", "@id");
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
				list.add(det);
			}
			item.setGoodsDets(list);
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
					list.add("status" + index++);
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
				qb.addGreaterThanOrEquals("t.createDate", "@endTime");
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
				item.setStatus(rs.getFields().get(index++).getString());
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
	 * 新增
	 */
	@Publish
	protected class UpdateGoodsSplitBill extends
			SimpleTaskMethodHandler<UpdateGoodsSplitBillTask> {

		@Override
		protected void handle(Context context, UpdateGoodsSplitBillTask task)
				throws Throwable {

		}

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

		}

	}

}
