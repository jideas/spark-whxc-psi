package com.spark.psi.base.internal.service.bom;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.dnasql.DeleteSqlBuilder;
import com.spark.common.utils.dnasql.InsertSqlBuilder;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.publicimpl.BomHistoryItemImpl;
import com.spark.psi.base.publicimpl.BomInfoImpl;
import com.spark.psi.base.publicimpl.BomInfoItemImpl;
import com.spark.psi.base.publicimpl.BomItemImpl;
import com.spark.psi.base.publicimpl.ShortGoodsItemImpl;
import com.spark.psi.base.task.UpdateGoodsItemBomIdTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;
import com.spark.psi.publish.base.bom.entity.BomHistoryItem;
import com.spark.psi.publish.base.bom.entity.BomInfo;
import com.spark.psi.publish.base.bom.entity.BomInfoItem;
import com.spark.psi.publish.base.bom.entity.BomItem;
import com.spark.psi.publish.base.bom.key.GetBomItemListByGoodsIdKey;
import com.spark.psi.publish.base.bom.key.GetBomItemListKey;
import com.spark.psi.publish.base.bom.key.GetNewBomNoKey;
import com.spark.psi.publish.base.bom.task.BomApproveTask;
import com.spark.psi.publish.base.bom.task.BomInEffectTask;
import com.spark.psi.publish.base.bom.task.BomInfoTask;
import com.spark.psi.publish.base.bom.task.BomInfoTaskItem;
import com.spark.psi.publish.base.bom.task.DeleteBomTask;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;
import com.spark.psi.publish.base.goods.entity.ShortGoodsItem;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey;
import com.spark.psi.publish.base.goods.key.GetShortGoodsItemListKey;

public class BomPublishService extends Service {

	protected BomPublishService() {
		super("com.spark.psi.base.internal.service.bom.BomPublishService");
	}

	protected final static String LockFlag = "locked";

	/**
	 * 获取商品条目列表
	 */
	@Publish
	protected class GetShortGoodsItemListProvider extends
			OneKeyResultProvider<ListEntity<ShortGoodsItem>, GetShortGoodsItemListKey> {

		@SuppressWarnings("unchecked")
		@Override
		protected ListEntity<ShortGoodsItem> provide(Context context, GetShortGoodsItemListKey oldkey) throws Throwable {
			List<ShortGoodsItem> list = new ArrayList<ShortGoodsItem>();
			GetGoodsInfoListKey key = new GetGoodsInfoListKey(oldkey.getOffset(), oldkey.getCount(), true);
			key.setSearchText(oldkey.getSearchText());
			key.setCateoryId(oldkey.getGategoryId());
			key.setStatus(GoodsStatus.ON_SALE);
			key.setQueryAll(true);
			key.setSortType(oldkey.getSortType());
			key.setSortField(oldkey.getSortField());
			ListEntity<GoodsInfo> listEntity = (ListEntity<GoodsInfo>) context.find(ListEntity.class, key);
			List<GoodsInfo> goodsList = listEntity.getItemList();
			for (GoodsInfo goodsInfo : goodsList) {
				for (GoodsItemData itemData : goodsInfo.getItems()) {
					list.add(new ShortGoodsItemImpl(goodsInfo, itemData));
				}
			}
			return new ListEntity<ShortGoodsItem>(list, list.size());
		}
	}

	/**
	 * 获得bom列表
	 */
	@Publish
	protected class BomItemListProvider extends OneKeyResultProvider<ListEntity<BomItem>, GetBomItemListKey> {

		@Override
		protected ListEntity<BomItem> provide(Context context, GetBomItemListKey key) throws Throwable {
			Login login = context.find(Login.class);
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Bom.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.bomNo", "bomNo");
			qb.addColumn("t.goodsItemId", "goodsItemId");
			qb.addColumn("t.goodsNo", "goodsNo");
			qb.addColumn("t.createDate", "createDate");
			qb.addColumn("t.creatorId", "creatorId");
			qb.addColumn("t.creator", "creator");
			qb.addColumn("t.approvePerson", "approvePerson");
			qb.addColumn("t.approvePersonName", "approvePersonName");
			qb.addColumn("t.status", "status");
			if (CheckIsNull.isNotEmpty(key.getSearchText())) {
				qb.addArgs("text", qb.STRING, key.getSearchText());
				StringBuilder ss = new StringBuilder("( t.bomNo like '%'+@text+'%' or ");
				ss.append(" t.goodsNo like '%'+@text+'%' or ");
				ss.append(" t.creator like '%'+@text+'%' or ");
				ss.append(" t.approvePersonName like '%'+@text+'%' ");
				ss.append(")");
				qb.addCondition(ss.toString());
			}
			qb.addArgs("login", qb.guid, login.getEmployeeId());
			if (key.getStatus() != null) {
				StringBuilder ss = new StringBuilder("(1=2");
				int i = 0;
				for (BOM_STATUS status : key.getStatus()) {
					if (BOM_STATUS.Approveing == status || BOM_STATUS.InEffect == status) {
						qb.addArgs("status" + i, qb.STRING, status.getCode());
						ss.append(" or t.status = @status" + i++);
					} else {
						qb.addArgs("status" + i, qb.STRING, status.getCode());
						ss.append(" or( t.status = @status" + (i++) + " and t.creatorId=@login) ");
					}
				}
				ss.append(")");
				qb.addCondition(ss.toString());
			}
			if (key.getSortField() == GetBomItemListKey.SortField.None) {
				if (key.getSortType() == SortType.Desc) {
					qb.addOrderBy("t." + key.getSortField().getFieldName() + " desc ");
				} else {
					qb.addOrderBy("t." + key.getSortField().getFieldName() + " asc ");
				}
			}
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			List<BomItem> list = new ArrayList<BomItem>();
			while (rs.next()) {
				BomItemImpl item = new BomItemImpl();
				item.setId(rs.getFields().get(0).getGUID());
				item.setBomNo(rs.getFields().get(1).getString());
				GUID gid = rs.getFields().get(2).getGUID();
				item.setGoodsItemId(gid);
				GoodsItem gi = context.find(GoodsItem.class, gid);
				item.setGoodsNo(rs.getFields().get(3).getString());
				item.setGoodsCode(gi.getGoodsCode());
				item.setGoodsName(gi.getGoodsName());
				item.setGoodsSpec(gi.getSpec());
				item.setGoodsUnit(gi.getGoodsUnit());
				item.setCreateDate(rs.getFields().get(4).getDate());
				item.setCreatorId(rs.getFields().get(5).getGUID());

				item.setCreator(rs.getFields().get(6).getString());
				item.setApprovePerson(rs.getFields().get(7).getGUID());
				item.setApprovePersonName(rs.getFields().get(8).getString());
				item.setStatus(BOM_STATUS.getStatus(rs.getFields().get(9).getString()));
				list.add(item);
			}
			return new ListEntity<BomItem>(list, list.size());
		}

	}

	/**
	 * 根据商品id查询所有bomitem
	 */
	@Publish
	protected class BomItemListByGoodsId extends OneKeyResultListProvider<BomItem, GetBomItemListByGoodsIdKey> {

		@Override
		protected void provide(Context context, GetBomItemListByGoodsIdKey key, List<BomItem> list) throws Throwable {
			Login login = context.find(Login.class);
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Bom.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.bomNo", "bomNo");
			qb.addColumn("t.goodsItemId", "goodsItemId");
			qb.addColumn("t.goodsNo", "goodsNo");
			qb.addColumn("t.createDate", "createDate");
			qb.addColumn("t.creatorId", "creatorId");
			qb.addColumn("t.creator", "creator");
			qb.addColumn("t.approvePerson", "approvePerson");
			qb.addColumn("t.approvePersonName", "approvePersonName");
			qb.addColumn("t.status", "status");
			qb.addColumn("t.remark", "remark");
			qb.addArgs("login", qb.guid, login.getEmployeeId());
			qb.addArgs("status0", qb.STRING, BOM_STATUS.Submit.getCode());
			qb.addArgs("status1", qb.STRING, BOM_STATUS.Rejected.getCode());
			StringBuilder ss = new StringBuilder("(t.status <> @status0 or t.status <>@status1 ");
			ss.append(" or( t.status = @status0 and t.creatorId=@login) ");
			ss.append(" or( t.status = @status1 and t.creatorId=@login) ");
			ss.append(")");
			// qb.addArgs("appStatus", qb.STRING, 
			// BOM_STATUS.Approveing.getCode());
			// qb.addNotEquals("t.status", "@appStatus");
			qb.addCondition(ss.toString());
			qb.addArgs("gid", qb.guid, key.getId());
			qb.addEquals("t.goodsItemId", "@gid");
			qb.addOrderBy("t.bomNo" + " desc ");
			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				BomItemImpl item = new BomItemImpl();
				item.setId(rs.getFields().get(0).getGUID());
				item.setBomNo(rs.getFields().get(1).getString());
				GUID gid = rs.getFields().get(2).getGUID();
				item.setGoodsItemId(gid);
				GoodsItem gi = context.find(GoodsItem.class, gid);
				item.setGoodsNo(rs.getFields().get(3).getString());
				item.setGoodsCode(gi.getGoodsCode());
				item.setGoodsName(gi.getGoodsName());
				item.setGoodsSpec(gi.getSpec());
				item.setGoodsUnit(gi.getGoodsUnit());
				item.setCreateDate(rs.getFields().get(4).getDate());
				item.setCreatorId(rs.getFields().get(5).getGUID());

				item.setCreator(rs.getFields().get(6).getString());
				item.setApprovePerson(rs.getFields().get(7).getGUID());
				item.setApprovePersonName(rs.getFields().get(8).getString());
				item.setStatus(BOM_STATUS.getStatus(rs.getFields().get(9).getString()));
				item.setRemark(rs.getFields().get(10).getString());
				list.add(item);
			}
		}

	}

	/**
	 * 根据商品id查询所有boms记录
	 */
	@Publish
	protected class BomHistoryProvider extends OneKeyResultListProvider<BomHistoryItem, GetBomItemListByGoodsIdKey> {

		@Override
		protected void provide(Context context, GetBomItemListByGoodsIdKey key, List<BomHistoryItem> list)
				throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.BOMHistory.getTableName(), "t");
			qb.addColumn("t.bomNo", "bomNo");
			qb.addColumn("t.bomCreator", "bomCreator");
			qb.addColumn("t.bomApprovor", "bomApprovor");
			qb.addColumn("t.ineffector", "ineffector");
			qb.addColumn("t.outeffector", "outeffector");
			qb.addColumn("t.ineffectDate", "ineffectDate");
			qb.addColumn("t.outeffectDate", "outeffectDate");
			qb.addColumn("t.RECID", "ID");
			qb.addArgs("goodsId1", qb.guid, key.getId());
			qb.addEquals("t.goodsId", "@goodsId1");

			qb.addOrderBy("t.ineffectDate desc ");

			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				BomHistoryItemImpl item = new BomHistoryItemImpl();
				item.setBomNo(rs.getFields().get(0).getString());
				item.setCreator(rs.getFields().get(1).getString());
				item.setApprovor(rs.getFields().get(2).getString());
				item.setIneffector(rs.getFields().get(3).getString());
				item.setOuteffector(rs.getFields().get(4).getString());
				item.setIneffectDate(DateUtil.dateFromat(rs.getFields().get(5).getDate()));
				item.setOuteffectDate(DateUtil.dateFromat(rs.getFields().get(6).getDate()));
				item.setId(rs.getFields().get(7).getGUID());
				list.add(item);
			}
		}
	}

	/**
	 * 保存或更新bom信息
	 */
	@Publish
	protected class SaveOrUpdateBomHandle extends SimpleTaskMethodHandler<BomInfoTask> {

		@Override
		protected void handle(Context context, BomInfoTask task) throws Throwable {
			if (null == task.getId()) {
				doSave(context, task);
			} else {
				doUpdate(context, task);
			}
		}

		private void doUpdate(Context context, BomInfoTask task) {
			Login login = context.find(Login.class);
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Base.Bom.getTableName());
			ub.addColumn("remark", ub.STRING, task.getRemark());
			ub.addCondition("id", ub.guid, task.getId(), "t.RECID=@id");
			if (task.getStatus() == BOM_STATUS.Approveing) {
				if (login.hasAuth(Auth.Boss)) {
					ub.addColumn("approvePerson", ub.guid, login.getEmployeeId());
					ub.addColumn("approvePersonName", ub.STRING, emp.getName());
					ub.addColumn("approveDate", ub.DATE, System.currentTimeMillis());
					ub.addColumn("status", ub.STRING, BOM_STATUS.InEffect.getCode());
				} else {
					ub.addColumn("status", ub.STRING, task.getStatus().getCode());
				}
			}
			ub.addIn("status", ub.STRING, BOM_STATUS.Submit.getCode(), BOM_STATUS.Rejected.getCode());
			int count = ub.execute();
			if (count == 1) {
				DeleteSqlBuilder db = new DeleteSqlBuilder(context);
				db.setTable(ERPTableNames.Base.BomDetail.getTableName());
				db.addEquals("bomId", db.guid, task.getId());
				db.execute();
				for (int i = 0; i < task.getItems().size(); i++) {
					doSaveItem(context, task.getItems().get(i), task.getId());
				}
			}
		}

		private void doSave(Context context, BomInfoTask task) {
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Base.Bom.getTableName());
			Login login = context.find(Login.class);
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			task.setId(context.newRECID());
			ib.addColumn("RECID", ib.guid, task.getId());
			ib.addColumn("goodsItemId", ib.guid, task.getGoodsItemId());
			ib.addColumn("goodsNo", ib.STRING, task.getGoodsNo());
			ib.addColumn("creatorId", ib.guid, login.getEmployeeId());
			ib.addColumn("createDate", ib.DATE, System.currentTimeMillis());
			ib.addColumn("creator", ib.STRING, emp.getName());
			ib.addColumn("remark", ib.STRING, task.getRemark());
			if (task.getStatus() == BOM_STATUS.Approveing && login.hasAuth(Auth.Boss)) {
				ib.addColumn("approvePerson", ib.guid, login.getEmployeeId());
				ib.addColumn("approvePersonName", ib.STRING, emp.getName());
				ib.addColumn("approveDate", ib.DATE, System.currentTimeMillis());
				ib.addColumn("status", ib.STRING, BOM_STATUS.InEffect.getCode());
			} else {
				ib.addColumn("status", ib.STRING, task.getStatus().getCode());
			}

			synchronized (LockFlag) {
				String number = context
						.find(String.class, new GetNewBomNoKey(task.getGoodsItemId(), task.getGoodsNo()));
				ib.addColumn("bomNo", ib.STRING, number);
				int count = ib.execute();
				if (count == 1) {
					for (int i = 0; i < task.getItems().size(); i++) {
						doSaveItem(context, task.getItems().get(i), task.getId());
					}
				}
			}
		}

		private void doSaveItem(Context context, BomInfoTaskItem item, GUID bomId) {
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Base.BomDetail.getTableName());
			ib.addColumn("RECID", ib.guid, context.newRECID());
			ib.addColumn("bomId", ib.guid, bomId);
			ib.addColumn("materialId", ib.guid, item.getMaterialId());
			ib.addColumn("baseCount", ib.DOUBLE, item.getCount());
			ib.addColumn("lossRate", ib.DOUBLE, item.getLossRate());
			ib.addColumn("realCount", ib.DOUBLE, item.getRealCount());
			ib.execute();
		}
	}

	/**
	 * 得到新的bom编号
	 */
	@Publish
	protected class NewBomNoProvider extends OneKeyResultProvider<String, GetNewBomNoKey> {

		@Override
		protected String provide(Context context, GetNewBomNoKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			String number = null;
			qb.addTable(ERPTableNames.Base.Bom.getTableName(), "t");
			qb.addArgs("goodsId", qb.guid, key.getGoodsItemId());
			qb.addColumn("t.bomNo", "ccc");
			qb.addEquals("t.goodsItemId", "@goodsId");
			qb.addOrderBy("t.createDate desc ");
			RecordSet rs = qb.getRecordLimit(0, 1);
			if (rs.next()) {
				String count = rs.getFields().get(0).getString();
				if (CheckIsNull.isEmpty(count)) {
					number = key.getGoodsNo() + "0001";
				} else {
					number = com.spark.common.utils.character.StringHelper.addOneInt(count);
				}
			} else {
				number = key.getGoodsNo() + "0001";
			}
			return number;
		}
	}

	/**
	 * bom批准或退回
	 */
	@Publish
	protected class ApproveBomHandle extends SimpleTaskMethodHandler<BomApproveTask> {

		@Override
		protected void handle(Context context, BomApproveTask task) throws Throwable {
			Login login = context.find(Login.class);
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Base.Bom.getTableName());
			if (task.isApproved()) {
				ub.addColumn("status", ub.STRING, BOM_STATUS.InEffect.getCode());
			} else {
				ub.addColumn("status", ub.STRING, BOM_STATUS.Rejected.getCode());
			}
			ub.addColumn("approvePerson", ub.guid, login.getEmployeeId());
			ub.addColumn("approvePersonName", ub.STRING, emp.getName());
			ub.addColumn("approveDate", ub.DATE, System.currentTimeMillis());

			ub.addCondition("id", ub.guid, task.getBomId(), "t.RECID = @id");
			ub.execute();
		}
	}

	/**
	 * 启用bom
	 */
	@Publish
	protected class BomInEffectHandle extends SimpleTaskMethodHandler<BomInEffectTask> {

		@Override
		protected void handle(Context context, BomInEffectTask task) throws Throwable {

			BomInfo info = context.find(BomInfo.class, task.getBomId(), false);
			Login login = context.find(Login.class);
			Employee emp = context.find(Employee.class, login.getEmployeeId());

			GoodsItem gi = context.find(GoodsItem.class, task.getGoodsItemId());
			// 先调用商品接口更改商品的bomId
			context.handle(new UpdateGoodsItemBomIdTask(task.getGoodsItemId(), task.getBomId()));

			// 成功后，对bom记录表插入生效数据
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Base.BOMHistory.getTableName());
			ib.addColumn("RECID", ib.guid, context.newRECID());
			ib.addColumn("goodsId", ib.guid, task.getGoodsItemId());
			ib.addColumn("bomId", ib.guid, task.getBomId());
			ib.addColumn("bomNo", ib.STRING, info.getBomNo());
			ib.addColumn("bomCreator", ib.STRING, info.getCreator());
			ib.addColumn("bomApprovor", ib.STRING, info.getApprovePersonName());
			ib.addColumn("ineffector", ib.STRING, emp.getName());
			ib.addColumn("ineffectDate", ib.DATE, System.currentTimeMillis());
			ib.execute();
			// 成功后，对bom记录表写入失效数据
			if (null == gi.getBomId()) {
				return;
			}
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Base.BOMHistory.getTableName());

			ub.addColumn("outeffector", ub.STRING, emp.getName());
			ub.addColumn("outeffectDate", ub.DATE, System.currentTimeMillis());

			ub.addCondition("bomId", ub.guid, gi.getBomId(), "t.bomId=@bomId");
			ub.addCondition("t.outeffector is null");
			ub.execute();
		}
	}

	/**
	 * 根据RECID得到info信息
	 */
	@Publish
	protected class BomInfoProvider extends TwoKeyResultProvider<BomInfo, GUID, Boolean> {

		@Override
		protected BomInfo provide(Context context, GUID key1, Boolean key2) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Bom.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.bomNo", "bomNo");
			qb.addColumn("t.goodsItemId", "goodsItemId");
			qb.addColumn("t.goodsNo", "goodsNo");
			qb.addColumn("t.createDate", "createDate");
			qb.addColumn("t.creatorId", "creatorId");
			qb.addColumn("t.creator", "creator");
			qb.addColumn("t.approvePerson", "approvePerson");
			qb.addColumn("t.approvePersonName", "approvePersonName");
			qb.addColumn("t.approveDate", "approveDate");
			qb.addColumn("t.status", "status");
			qb.addColumn("t.remark", "remark");

			qb.addArgs("id", qb.guid, key1);
			qb.addEquals("t.RECID", "@id");

			RecordSet rs = qb.getRecord();
			BomInfoImpl info = new BomInfoImpl();
			if (rs.next()) {
				int i = 0;
				info.setId(rs.getFields().get(i++).getGUID());
				info.setBomNo(rs.getFields().get(i++).getString());
				info.setGoodsItemId(rs.getFields().get(i++).getGUID());
				info.setGoodsNo(rs.getFields().get(i++).getString());
				info.setCreateDate(rs.getFields().get(i++).getDate());
				info.setCreatorId(rs.getFields().get(i++).getGUID());
				info.setCreator(rs.getFields().get(i++).getString());
				info.setApprovePerson(rs.getFields().get(i++).getGUID());
				info.setApprovePersonName(rs.getFields().get(i++).getString());
				info.setApproveDate(rs.getFields().get(i++).getDate());
				info.setStatus(BOM_STATUS.getStatus(rs.getFields().get(i++).getString()));
				info.setRemark(rs.getFields().get(i++).getString());
			}
			if (!key2) {
				return info;
			}
			info.setBomInfoItems(getBomInfoItems(context, key1));
			return info;
		}

		private List<BomInfoItem> getBomInfoItems(Context context, GUID id) {
			List<BomInfoItem> list = new ArrayList<BomInfoItem>();
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.BomDetail.getTableName(), "t");
			qb.addColumn("t.materialId", "materialId");
			qb.addColumn("t.baseCount", "baseCount");
			qb.addColumn("t.lossRate", "lossRate");
			qb.addColumn("t.realCount", "realCount");

			qb.addArgs("bid", qb.guid, id);
			qb.addEquals("t.bomId", "@bid");

			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				BomInfoItemImpl item = new BomInfoItemImpl();
				item.setMaterialId(rs.getFields().get(0).getGUID());
				item.setCount(rs.getFields().get(1).getDouble());
				item.setLossRate(rs.getFields().get(2).getDouble());
				item.setRealCount(rs.getFields().get(3).getDouble());
				MaterialsItem minfo = context.find(MaterialsItem.class, item.getMaterialId());
				item.setMaterialCode(minfo.getMaterialCode());
				item.setMaterialNo(minfo.getMaterialNo());
				item.setMaterialName(minfo.getMaterialName());
				item.setMaterialSpec(minfo.getSpec());
				item.setMaterialUnit(minfo.getMaterialUnit());
				list.add(item);
			}
			return list;
		}

	}

	/**
	 * 根据RECID得到info信息
	 */
	@Publish
	protected class RealBomInfoProvider extends OneKeyResultProvider<BomInfo, GUID> {
		@Override
		protected BomInfo provide(Context context, GUID key) throws Throwable {
			return context.find(BomInfo.class, key, true);
		}

	}

	@Publish
	protected class DeleteBomProvider extends SimpleTaskMethodHandler<DeleteBomTask> {

		@Override
		protected void handle(Context context, DeleteBomTask task) throws Throwable {
			DeleteSqlBuilder db = new DeleteSqlBuilder(context);
			db.setTable(ERPTableNames.Base.Bom.getTableName());
			db.addEquals("RECID", db.guid, task.getId());
			int i = db.execute();
			if (i == 1) {
				doDelete(context, task);
			}
		}

		private void doDelete(Context context, DeleteBomTask task) {
			DeleteSqlBuilder db = new DeleteSqlBuilder(context);
			db.setTable(ERPTableNames.Base.BomDetail.getTableName());
			db.addEquals("bomId", db.guid, task.getId());
			db.execute();
		}
	}
}
