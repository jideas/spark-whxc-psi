/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.Outstorage.dao
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.internal.service;

import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.outstorage.OutstorageKey;
import com.spark.psi.inventory.intf.task.outstorage.OutstorageItemTask;
import com.spark.psi.inventory.intf.util.outstorage.QueryBuilder;
import com.spark.psi.inventory.intf.util.outstorage.UpdateBuilder;
import com.spark.psi.inventory.intf.util.outstorage.dbox.GoodsOutstoInfo;
import com.spark.psi.inventory.intf.util.outstorage.dbox.GoodsOutstoInfoDet;
import com.spark.psi.inventory.intf.util.outstorage.dbox.OutingCount;
import com.spark.psi.inventory.service.orm.Orm_OutstoDet;
import com.spark.psi.publish.CheckingOutStatus;

/**
 * <p>
 * 出库 明细 数据库操作类
 * </p>
 * 
 * 
 * 
 * 
 * @author 王志坚
 * @version 2011-11-10
 */

public class OutstoDetailDao extends Service {

	protected OutstoDetailDao(Orm_OutstoDet orm) {
		super("OutstoDetailDao");
		this.orm = orm;
	}

	private Orm_OutstoDet orm;

	/**
	 * insert一条出库单明细数据
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	@Publish
	protected class InsertOutstoDetailDao extends TaskMethodHandler<OutstorageItemTask, Method> {
		protected InsertOutstoDetailDao() {
			super(Method.INSERT);
		}

		@Override
		protected void handle(Context context, OutstorageItemTask task) throws Throwable {
			OutstorageItem entity = task.getEntity();
			ORMAccessor<OutstorageItem> orma = context.newORMAccessor(orm);
			try {
				orma.insert(entity);
			} finally {
				orma.unuse();
			}
		}
	}

	/**
	 * update一条出库单明细数据（已出库数量）
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	@Publish
	protected class UpdateOutstoDetailDao extends TaskMethodHandler<OutstorageItemTask, Method> {
		protected UpdateOutstoDetailDao() {
			super(Method.MODIFY);
		}

		@Override
		protected void handle(Context context, OutstorageItemTask task) throws Throwable {
			OutstorageItem entity = task.getEntity();
			UpdateBuilder ub = new UpdateBuilder(context);
			ub.setTable("SA_STORE_OUTSTO_DET");
			ub.addExpression("outstoCount", ub.DOUBLE, entity.getThisTimeCount(), "outstoCount =t.outstoCount+@outstoCount");
			ub.addCondition("recid", ub.guid, entity.getRECID(), "t.RECID = @recid");
			ub.addCondition("((t.planOutstoCount-t.outstoCount)>@outstoCount OR (t.planOutstoCount-t.outstoCount)=@outstoCount)");
			task.count = ub.execute();
			if (!(task.count > 0)) {
				throw new Throwable("有其他人在操作该单据，请检查！");
			}
		}
	}

	/**
	 * 根据主表recid查询所有明细
	 */
	@Publish
	protected class GetABillsDetails extends OneKeyResultListProvider<OutstorageItem, GUID> {

		@Override
		protected void provide(Context context, GUID recid, List<OutstorageItem> list) throws Throwable {
			QueryBuilder qb = new QueryBuilder(context);
			addColumnsToQuery(qb);
			qb.addArgs("recid", qb.guid, recid);
			qb.addEquals("t.sheetId", "@recid");
			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				list.add(fillEntityToQuery(rs));
			}
		}

	}

	/**
	 * 查询的表及列
	 * 
	 * @param qb
	 *            void
	 */
	private void addColumnsToQuery(QueryBuilder qb) {
		qb.addTable(ERPTableNames.Inventory.Checkingout_Det.getTableName(), "t");
		qb.addColumn("t.RECID", "RECID");
		qb.addColumn("t.sheetId", "sheetId");
		qb.addColumn("t.goodsId", "goodsId");
		qb.addColumn("t.goodsCode", "goodsCode");
		qb.addColumn("t.goodsNo", "goodsNo");
		qb.addColumn("t.goodsName", "goodsName");
		qb.addColumn("t.goodsSpec", "goodsSpec");
		qb.addColumn("t.unit", "unit");
		qb.addColumn("t.scale", "scale");
		qb.addColumn("t.price", "price");
		qb.addColumn("t.amount", "amount");
		qb.addColumn("t.planCount", "planCount");
		qb.addColumn("t.checkoutCount", "checkinCount");
		qb.addColumn("t.creator", "creator");
		qb.addColumn("t.createDate", "createDate");
	}

	/**
	 * 从数据集中取出数据到实体
	 * 
	 * @param rs
	 * @return FOutstoDetatil
	 */
	private OutstorageItem fillEntityToQuery(RecordSet rs) {
		OutstorageItem entity = new OutstorageItem();
		int index = 0;
		entity.setRECID(rs.getFields().get(index++).getGUID());
		entity.setSheetId(rs.getFields().get(index++).getGUID());
		entity.setGoodsId(rs.getFields().get(index++).getGUID());
		entity.setGoodsCode(rs.getFields().get(index++).getString());
		entity.setGoodsNo(rs.getFields().get(index++).getString());
		entity.setGoodsName(rs.getFields().get(index++).getString());
		entity.setGoodsSpec(rs.getFields().get(index++).getString());
		entity.setUnit(rs.getFields().get(index++).getString());
		entity.setScale(rs.getFields().get(index++).getInt());
		entity.setPrice(rs.getFields().get(index++).getDouble());
		entity.setAmount(rs.getFields().get(index++).getDouble());
		entity.setPlanCount(rs.getFields().get(index++).getDouble());
		entity.setCheckoutCount(rs.getFields().get(index++).getDouble());
		entity.setCreator(rs.getFields().get(index++).getString());
		entity.setCreateDate(rs.getFields().get(index++).getDate());
		return entity;
	}

	/**
	 * 查询出库情况
	 */
	@Publish
	protected class OutInfoProvider extends OneKeyResultListProvider<GoodsOutstoInfo, OutstorageKey> {

		@Override
		protected void provide(Context context, OutstorageKey key, List<GoodsOutstoInfo> list) throws Throwable {
			// RecordSet rs =
			// context.openQuery(query, key.getRelaOrdGuid(),
			// context.find(Login.class).getTenantId());
			// Map<GUID, GoodsOutstoInfo> map = new HashMap<GUID,
			// GoodsOutstoInfo>();
			// List<GUID> glist = new ArrayList<GUID>();
			// while(rs.next()){
			// fillOutstoInfo(rs, glist, map);
			// }
			// for(int i = 0; i < glist.size(); i++){
			// list.add(map.get(glist.get(i)));
			// }
		}
	}

	/**
	 * 将查询出的数据装入商品入库情况容器
	 */
	private void fillOutstoInfo(RecordSet rs, List<GUID> list, Map<GUID, GoodsOutstoInfo> map) {
		GUID goodsGuid = rs.getFields().get(0).getGUID();
		GoodsOutstoInfo info = map.get(goodsGuid);
		if (null == info) {
			info = new GoodsOutstoInfo();
			info.setGoodsGuid(goodsGuid);
			info.setGoodsName(rs.getFields().get(1).getString());
			info.setGoodsAttr(rs.getFields().get(2).getString());
			info.setGoodsUnit(rs.getFields().get(3).getString());
			info.setGoodsScale(rs.getFields().get(4).getInt());
		}
		info.addDet(new GoodsOutstoInfoDet(rs.getFields().get(5).getDouble(), rs.getFields().get(6).getDate(), rs.getFields()
				.get(7).getString(), rs.getFields().get(8).getDouble(), rs.getFields().get(9).getDouble(), rs.getFields().get(10)
				.getString()));
		map.put(goodsGuid, info);
		if (list.indexOf(goodsGuid) < 0) {
			list.add(goodsGuid);
		}

	}

	/**
	 * 查询单个商品的未出库数量
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-30
	 */
	// @Publish
	// protected class FindGoodsOutingCountProvider extends
	// OneKeyResultProvider<Double, GoodsOutingCountKey>{
	//
	// @Override
	// protected Double provide(Context context, GoodsOutingCountKey key) throws
	// Throwable{
	// StringBuilder ss = new StringBuilder();
	// ss
	// .append(" define query TempQuery(@storeId guid not null,@goodsId guid not null,@allstatus string not null");
	// if(null != key.getOutstoType()){
	// ss.append(",@outstoType string not null");
	// }
	// ss.append(") ");
	// ss.append(" begin ");
	// ss.append("	select sum(t.planOutstoCount) as \"count1\",sum(t.outstoCount) as \"count2\" ");
	// ss.append(" 	from \"SA_STORE_OUTSTO_DET\" as \"t\" JOIN \"SA_STORE_OUTSTORAGE\" AS o on  ");
	// ss.append(" 	t.outstoGuid = o.RECID and o.isStoped = false ");
	// ss.append("  	and o.storeGuid = @storeId and t.goodsGuid = @goodsId and o.outstoState <>@allstatus ");
	// if(null != key.getOutstoType()){
	// ss.append(" and o.outstoType=@outstoType ");
	// }
	// ss.append("  end ");
	// DBCommand db = context.prepareStatement(ss.toString());
	// if(null == key.getOutstoType()){
	// db.setArgumentValues(key.getStoreGuid(), key.getGoodsGuid(),
	// SAAS.outstoState.status_ALL);
	// }
	// else{
	// db.setArgumentValues(key.getStoreGuid(), key.getGoodsGuid(),
	// SAAS.outstoState.status_ALL, key
	// .getOutstoType());
	// }
	// RecordSet rs = db.executeQuery();
	// double d = 0;
	// if(rs.next()){
	// double d1 = rs.getFields().get(0).getDouble();
	// double d2 = rs.getFields().get(1).getDouble();
	// d = d1 - d2;
	// }
	// db.unuse();
	// return d;
	// }
	//
	// }

	/**
	 * 根据相关单据查询的未出库商品数量
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-30
	 */
	@Publish
	protected class FindGoodsOutingCountProvider1 extends OneKeyResultListProvider<OutingCount, OutstorageKey> {

		@Override
		protected void provide(Context context, OutstorageKey key, List<OutingCount> list) throws Throwable {
			StringBuilder ss = new StringBuilder();
			ss.append(" define query TempQuery(@allstatus string not null,@relaId guid not null) ");
			ss.append(" begin ");
			ss.append("	select sum(t.planOutstoCount) as \"count1\",sum(t.outstoCount) as \"count2\" ");
			ss.append(",o.storeGuid as storeGuid ,t.goodsGuid as goodsGuid ");
			ss.append(" 	from \"SA_STORE_OUTSTO_DET\" as \"t\" JOIN \"SA_STORE_OUTSTORAGE\" AS o on  ");
			ss.append(" 	t.outstoGuid = o.RECID ");
			ss.append("  	and o.relaOrderGuid=@relaId and o.outstoState <>@allstatus ");
			ss.append(" group by o.storeGuid , t.goodsGuid ");
			ss.append("  end ");
			DBCommand db = context.prepareStatement(ss.toString());
			db.setArgumentValues(CheckingOutStatus.Finish.getCode(), key.getRelaOrdGuid());
			RecordSet rs = db.executeQuery();
			Double d = 0d;
			if (rs.next()) {
				double d1 = rs.getFields().get(0).getDouble();
				double d2 = rs.getFields().get(1).getDouble();
				d = d1 - d2;
				GUID sid = rs.getFields().get(2).getGUID();
				GUID gid = rs.getFields().get(3).getGUID();
				list.add(new OutingCount(gid, sid, d));
			}
		}

	}

}
