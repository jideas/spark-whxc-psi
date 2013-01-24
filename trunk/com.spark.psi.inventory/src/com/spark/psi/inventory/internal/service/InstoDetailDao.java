/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.instorage.dao
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.internal.service;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.spark.psi.base.Login;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.instorage.InstorageKey;
import com.spark.psi.inventory.intf.task.instorage.InstorageItemTask;
import com.spark.psi.inventory.intf.util.instorage.QueryBuilder;
import com.spark.psi.inventory.intf.util.instorage.dbox.GoodsInstoInfo;
import com.spark.psi.inventory.intf.util.instorage.dbox.GoodsInstoInfoDet;
import com.spark.psi.inventory.intf.util.outstorage.OutingCount;
import com.spark.psi.inventory.service.orm.Orm_InstoDet;
import com.spark.psi.publish.CheckingInStatus;

/**
 * <p>
 * 入库 明细 数据库操作类
 * </p>
 * 
 * 
 * 
 * 
 * @author 王志坚
 * @version 2011-11-10
 */

public class InstoDetailDao extends Service {

	protected InstoDetailDao(Orm_InstoDet orm ) {
		super("InstoDetailDao");
		this.orm = orm; 
	}

	private Orm_InstoDet orm; 

	/**
	 * insert一条入库单明细数据
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	@Publish
	protected class InsertInstoDetailDao extends TaskMethodHandler<InstorageItemTask, Method> {
		protected InsertInstoDetailDao() {
			super(Method.INSERT);
		}

		@Override
		protected void handle(Context context, InstorageItemTask task) throws Throwable {
			InstorageItem entity = task.getInstorageItemEntity();
			ORMAccessor<InstorageItem> orma = context.newORMAccessor(orm);
			try {
				orma.insert(entity);
			} finally {
				orma.unuse();
			}
		}
	}

	/**
	 * update一条入库单明细数据（已入库数量）
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	@Publish
	protected class UpdateInstoDetailDao extends TaskMethodHandler<InstorageItemTask, Method> {
		protected UpdateInstoDetailDao() {
			super(Method.MODIFY);
		}

		@Override
		protected void handle(Context context, InstorageItemTask task) throws Throwable {
			// InstorageItem entity = task.getInstorageItemEntity();
			// UpdateBuilder ub = new UpdateBuilder(context);
			// ub.setTable("SA_STORE_INSTO_DET");
			// ub
			// .addExpression("instoCount", ub.DOUBLE,
			// entity.getThisTimeCount(),
			// "instoCount =t.instoCount+@instoCount");
			// ub.addCondition("recid", ub.guid, entity.getRECID(),
			// "t.RECID = @recid");
			// ub.addCondition("((t.planInstoCount-t.instoCount)>@instoCount OR (t.planInstoCount-t.instoCount)=@instoCount)");
			// task.count = ub.execute();
			// if(!(task.count>0))
			// {
			throw new Throwable("有其他人在操作该单据，请检查！");
			// }
		}
	}

	/**
	 * 根据主表recid查询所有明细
	 */
	@Publish
	protected class GetABillsDetails extends OneKeyResultListProvider<InstorageItem, GUID> {

		@Override
		protected void provide(Context context, GUID recid, List<InstorageItem> list) throws Throwable { 
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
		qb.addTable(ERPTableNames.Inventory.Checkingin_Det.getTableName(), "t");
		qb.addColumn("t.RECID", "recid");
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
		qb.addColumn("t.planCount", "count1");
		qb.addColumn("t.checkinCount", "checkinCount");
		qb.addColumn("t.inspectCount", "inspectCount");
	}

	/**
	 * 从数据集中取出数据到实体
	 * 
	 * @param rs
	 * @return FInstoDetatil
	 */
	private InstorageItem fillEntityToQuery(RecordSet rs) {
		InstorageItem entity = new InstorageItem();
		int index = 0;
		entity.setId(rs.getFields().get(index++).getGUID());
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
		entity.setCount(rs.getFields().get(index++).getDouble());
		entity.setCheckinCount(rs.getFields().get(index++).getDouble());
		entity.setInspectCount(rs.getFields().get(index++).getDouble());
		return entity;
	}

	/**
	 * 查询入库情况
	 */
	@Publish
	protected class InInfoProvider extends OneKeyResultListProvider<GoodsInstoInfo, InstorageKey> {

		@Override
		protected void provide(Context context, InstorageKey key, List<GoodsInstoInfo> list) throws Throwable {
//			RecordSet rs = context.openQuery(query, key.getRelaOrdGuid(), context.find(Login.class).getTenantId());
//			Map<GUID, GoodsInstoInfo> map = new HashMap<GUID, GoodsInstoInfo>();
//			List<GUID> glist = new ArrayList<GUID>();
//			while (rs.next()) {
//				fillInstoInfo(rs, glist, map);
//			}
//			for (int i = 0; i < glist.size(); i++) {
//				list.add(map.get(glist.get(i)));
//			}
		}
	}

	/**
	 * 将查询出的数据装入商品入库情况容器
	 */
	private void fillInstoInfo(RecordSet rs, List<GUID> list, Map<GUID, GoodsInstoInfo> map) {
		GUID goodsGuid = rs.getFields().get(0).getGUID();
		GoodsInstoInfo info = map.get(goodsGuid);
		if (null == info) {
			info = new GoodsInstoInfo();
			info.setGoodsGuid(goodsGuid);
			info.setGoodsName(rs.getFields().get(1).getString());
			info.setGoodsAttr(rs.getFields().get(2).getString());
			info.setGoodsUnit(rs.getFields().get(3).getString());
			info.setGoodsScale(rs.getFields().get(4).getInt());
		}
		info.addDet(new GoodsInstoInfoDet(rs.getFields().get(5).getDouble(), rs.getFields().get(6).getString(), rs.getFields()
				.get(7).getDouble(), rs.getFields().get(8).getDouble(), rs.getFields().get(9).getString()));
		map.put(goodsGuid, info);
		if (list.indexOf(goodsGuid) < 0) {
			list.add(goodsGuid);
		}

	}

	/**
	 * 根据相关单据查询的未入库商品数量
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-30
	 */
	@Publish
	protected class FindGoodsOutingCountProvider1 extends OneKeyResultListProvider<OutingCount, InstorageKey> {

		@Override
		protected void provide(Context context, InstorageKey key, List<OutingCount> list) throws Throwable {
			StringBuilder ss = new StringBuilder();
			ss.append(" define query TempQuery(@allstatus string not null,@relaId guid not null) ");
			ss.append(" begin ");
			ss.append("	select sum(t.planInstoCount) as \"count1\",sum(t.instoCount) as \"count2\" ");
			ss.append(",o.storeGuid as storeGuid ,t.goodsGuid as goodsGuid ");
			ss.append(" 	from \"SA_STORE_INSTO_DET\" as \"t\" JOIN \""+ERPTableNames.Inventory.CheckinSheet.getTableName()+"\" AS o on  ");
			ss.append(" 	t.instoGuid = o.RECID ");
			ss.append("  	and o.relaOrderGuid=@relaId and o.instoState <>@allstatus ");
			ss.append(" group by o.storeGuid , t.goodsGuid ");
			ss.append("  end ");
			DBCommand db = context.prepareStatement(ss.toString());
			db.setArgumentValues(CheckingInStatus.Finish.getCode(), key.getRelaOrdGuid());
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
			db.unuse();
		}

	}
}
