package com.spark.psi.inventory.internal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.MaterialsCategory;
import com.spark.psi.base.key.goods.GetGoodsCategoryLeafNodesKey;
import com.spark.psi.base.key.materials.GetMaterialsCategoryLeafNodesKey;
import com.spark.psi.base.key.store.GetUserStoreGuidsKey;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.intf.event.InventoryLogEvent;
import com.spark.psi.inventory.intf.key.inventory.StoStreamKey;
import com.spark.psi.inventory.intf.task.inventory.StoStreamTask;
import com.spark.psi.inventory.service.orm.Orm_InventoryLog;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.inventory.key.GetInventoryLogKey;

/**
 * 
 */
public class StoStreamService extends Service {

	protected final Orm_InventoryLog orm_InventoryLog;
	private static String logTable = ERPTableNames.Inventory.Inventory_Log.getTableName();

	protected StoStreamService(Orm_InventoryLog orm_InventoryLog) {
		super("com.spark.store.stockquery.service.SStoStreamService");
		this.orm_InventoryLog = orm_InventoryLog;
	}

	/**
	 * 新增
	 * 
	 * 
	 */
	@Publish
	protected class insertTask extends TaskMethodHandler<StoStreamTask, StoStreamTask.Task> {

		protected insertTask() {
			super(StoStreamTask.Task.add);
		}

		@Override
		protected void handle(Context context, StoStreamTask stoStreamTask) throws Throwable {
			ORMAccessor<InventoryLogEntity> accessor = context.newORMAccessor(orm_InventoryLog);
			String day = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			String year = new SimpleDateFormat("yyyy").format(new Date());
			int yearNo = Integer.parseInt(year);
			int monthNo = DateUtil.getMonth(day);
			int quarter = DateUtil.getCurrQuarter(day);
			int dayNo = Integer.parseInt(day);
			try {
				if (stoStreamTask.getList().size() > 0) {
					for (InventoryLogEntity stoStream : stoStreamTask.getList()) {
						// stoStream.setMonthNo(monthNo);
						// stoStream.setQuarter(quarter);
						// stoStream.setDayNo(dayNo);
						// stoStream.setYearNo(yearNo);
						// GoodsItem goods = context.find(GoodsItem.class,
						// stoStream.getGoodsGuid());
						// if (null != goods) {
						// stoStream.setGoodsAttr(goods
						// .getPropertiesWithoutUnit());
						// stoStream.setGoodsName(goods.getGoodsName());
						// stoStream.setGoodsNo(goods.getGoodsCode());
						// stoStream.setGoodsTypeGuid(goods.getCategoryId());
						// stoStream.setGoodsUnit(goods.getGoodsUnit());
						// stoStream.setGoodsScale(goods.getScale());
						// }

						accessor.insert(stoStream);

						InventoryLogEvent event = new InventoryLogEvent();
						event.setInventoryLogId(stoStream.getId());
						context.dispatch(event);
					}
				} else {
					InventoryLogEntity stoStream = stoStreamTask.getStoStream();
					// stoStream.setMonthNo(monthNo);
					// stoStream.setQuarter(quarter);
					// stoStream.setDayNo(dayNo);
					// stoStream.setYearNo(yearNo);
					// GoodsItem goods = context.find(GoodsItem.class, stoStream
					// .getGoodsGuid());
					// if (null != goods) {
					// stoStream
					// .setGoodsAttr(goods.getPropertiesWithoutUnit());
					// stoStream.setGoodsName(goods.getGoodsName());
					// stoStream.setGoodsNo(goods.getGoodsCode());
					// stoStream.setGoodsTypeGuid(goods.getCategoryId());
					// stoStream.setGoodsUnit(goods.getGoodsUnit());
					// stoStream.setGoodsScale(goods.getScale());
					// }
					accessor.insert(stoStream);

					InventoryLogEvent event = new InventoryLogEvent();
					event.setInventoryLogId(stoStream.getId());
					context.dispatch(event);
				}

			} finally {
				accessor.unuse();
			}
		}
	}

	/**
	 * 主键查询库存流水
	 */
	@Publish
	protected class GetInventoryLogById extends OneKeyResultProvider<InventoryLogEntity, GUID> {

		@Override
		protected InventoryLogEntity provide(Context context, GUID id) throws Throwable {
			ORMAccessor<InventoryLogEntity> orm = context.newORMAccessor(orm_InventoryLog);
			try {
				return orm.findByRECID(id);
			} finally {
				orm.unuse();
			}
		}
	}

	/**
	 * 查询库存流水列表
	 */
	@Publish
	protected class GetInventoryLogList extends OneKeyResultListProvider<InventoryLogEntity, StoStreamKey> {

		@Override
		protected void provide(Context context, StoStreamKey key, List<InventoryLogEntity> list) throws Throwable {

			StringBuilder dnaSql = new StringBuilder();
			StringBuilder condtionSql = new StringBuilder();

			List<Object> paramList = new ArrayList<Object>();
			dnaSql.append("define query queryInventoryLogList(@inventoryType string");
			condtionSql.append(" where t.inventoryType=@inventoryType \n");
			paramList.add(key.getInventoryType());
			List<GUID> storeIdList = getStoreIdList(context, key);
			for (int index = 0; index < storeIdList.size(); index++) {
				dnaSql.append(",@storeGuid").append(index).append(" guid");
				paramList.add(storeIdList.get(index));
				if (0 == index) {
					condtionSql.append(" and t.storeId in (");
				} else {
					condtionSql.append(",");
				}
				condtionSql.append("@storeGuid").append(index);
				if (storeIdList.size() - 1 == index) {
					condtionSql.append(") \n");
				}
			}

			List<GUID> categoryIdList = getCategoryIdList(context, key);
			for (int index = 0; index < categoryIdList.size(); index++) {
				dnaSql.append(",@goodsTypeGuid").append(index).append(" guid");
				paramList.add(categoryIdList.get(index));
				if (0 == index) {
					condtionSql.append(" and t.categoryId in (");
					condtionSql.append("@goodsTypeGuid").append(index);
				} else {
					condtionSql.append(",@goodsTypeGuid").append(index);
				}
				if (categoryIdList.size() - 1 == index) {
					condtionSql.append(") \n");
				}
			}
			if (CheckIsNull.isNotEmpty(key.getSearchText())) {
				dnaSql.append(",@searchText string");
				paramList.add(key.getSearchText().trim());
				condtionSql.append(" and ( ");
				condtionSql.append(" t.stockNo like '%'+@searchText+'%' ");
				condtionSql.append(" or t.name like '%'+@searchText+'%' ");
				condtionSql.append(" or t.properties like '%'+@searchText+'%' ");
				condtionSql.append(" ) \n");
			}
			dnaSql.append(",@startTime date,@endTime date");
			condtionSql.append(" and t.createdDate between @startTime and @endTime \n");
			paramList.add(key.getDateBegin());
			paramList.add(key.getDateEnd());

			dnaSql.append(") \n");
			dnaSql.append("begin \n");
			dnaSql.append(" select \n");
			dnaSql.append(getColums());
			dnaSql.append("\n from ");
			dnaSql.append(logTable);
			dnaSql.append(" as t \n");
			dnaSql.append(condtionSql);
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				if (GetInventoryLogKey.SortField.CheckedInCount.getFieldName().equals(key.getSortField())) {
					dnaSql.append(" order by t.instoCount ").append(key.getSortType()).append(",t.outstoCount ")
							.append(key.getSortType());
				} else {
					dnaSql.append(" order by t.").append(key.getSortField()).append(" ").append(key.getSortType());
				}
			}
			dnaSql.append("\n end");

			DBCommand db = context.prepareStatement(dnaSql);

			for (int index = 0; index < paramList.size(); index++) {
				db.setArgumentValue(index, paramList.get(index));
			}
			try {
				RecordSet rs = db.executeQueryLimit(key.getOffset(), key.getCount());
				while (rs.next()) {
					list.add(fillEntity(rs));
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 获取仓库IDList
	 * 
	 * @param context
	 * @param key
	 * @return List<GUID>
	 */
	public List<GUID> getStoreIdList(Context context, StoStreamKey key) {
		List<GUID> storeIdList = new ArrayList<GUID>();
		if (null == key.getStoreId()) {
			GetUserStoreGuidsKey lKey = new GetUserStoreGuidsKey();
			GUID[] storeIds = context.find(GUID[].class, lKey);
			if (null != storeIds) {
				for (GUID storeId : storeIds) {
					storeIdList.add(storeId);
				}
			}
		} else {
			storeIdList.add(key.getStoreId());
		}
		return storeIdList;
	}

	public InventoryLogEntity fillEntity(RecordSet rs) {
		InventoryLogEntity s = new InventoryLogEntity();
		int index = 0;

		s.setId(rs.getFields().get(index++).getGUID());
		s.setStoreId(rs.getFields().get(index++).getGUID());
		s.setStockId(rs.getFields().get(index++).getGUID());
		s.setName(rs.getFields().get(index++).getString());
		s.setProperties(rs.getFields().get(index++).getString());
		s.setUnit(rs.getFields().get(index++).getString());
		s.setCategoryId(rs.getFields().get(index++).getGUID());
		s.setCode(rs.getFields().get(index++).getString());
		s.setStockNo(rs.getFields().get(index++).getString());
		s.setOrderId(rs.getFields().get(index++).getGUID());
		s.setOrderNo(rs.getFields().get(index++).getString());
		s.setLogType(rs.getFields().get(index++).getString());
		s.setInstoCount(rs.getFields().get(index++).getDouble());
		s.setInstoAmount(rs.getFields().get(index++).getDouble());
		s.setScale(rs.getFields().get(index++).getInt());
		s.setOutstoCount(rs.getFields().get(index++).getDouble());
		s.setOutstoAmount(rs.getFields().get(index++).getDouble());
		s.setCreatePerson(rs.getFields().get(index++).getString());
		s.setCreatedDate(rs.getFields().get(index++).getDate());
		s.setInventoryType(rs.getFields().get(index++).getString());

		return s;
	}

	public Object getColums() {
		StringBuilder sql = new StringBuilder();

		sql.append("t.RECID as id,");
		sql.append("t.storeId as storeId,");
		sql.append("t.stockId as stockId,");
		sql.append("t.name as name,");
		sql.append("t.properties as properties,");
		sql.append("t.unit as unit,");
		sql.append("t.categoryId as categoryId,");
		sql.append("t.code as code,");
		sql.append("t.stockNo as stockNo, ");
		sql.append("t.orderId as orderId, ");
		sql.append("t.orderNo as orderNo, ");
		sql.append("t.logType as logType,");
		sql.append("t.instoCount as instoCount, ");
		sql.append("t.instoAmount as instoAmount, ");
		sql.append("t.\"scale\" as \"scale\",");
		sql.append("t.outstoCount as outstoCount, ");
		sql.append("t.outstoAmount as outstoAmount,");
		sql.append("t.createPerson as createPerson, ");
		sql.append("t.createdDate as createdDate, ");
		sql.append("t.inventoryType as inventoryType ");

		return sql;
	}

	/**
	 * 获取叶子节点分类IDList
	 * 
	 * @param context
	 * @param key
	 * @return List<GUID>
	 */
	public List<GUID> getCategoryIdList(Context context, StoStreamKey key) {
		List<GUID> idList = new ArrayList<GUID>();
		if (null == key.getGoodsCategoryId()) {
			if (InventoryType.Goods.getCode().equals(key.getInventoryType())) {
				GetGoodsCategoryLeafNodesKey nKey = new GetGoodsCategoryLeafNodesKey();
				List<GoodsCategory> list = context.getList(GoodsCategory.class, nKey);
				if (null != list) {
					for (GoodsCategory leaf : list) {
						idList.add(leaf.getId());
					}
				}
			} else if (InventoryType.Materials.getCode().equals(key.getInventoryType())) {
				GetMaterialsCategoryLeafNodesKey nKey = new GetMaterialsCategoryLeafNodesKey();
				List<MaterialsCategory> list = context.getList(MaterialsCategory.class, nKey);
				if (null != list) {
					for (MaterialsCategory leaf : list) {
						idList.add(leaf.getId());
					}
				}
			}
		} else {
			if (InventoryType.Goods.getCode().equals(key.getInventoryType())) {
				GoodsCategory goodsCategory = context.find(GoodsCategory.class, key.getGoodsCategoryId());
				GoodsCategory[] leafNodes = goodsCategory.getLeafNodes(context);
				if (null == leafNodes || 0 == leafNodes.length) {
					idList.add(key.getGoodsCategoryId());
				} else {
					for (int index = 0; index < leafNodes.length; index++) {
						GoodsCategory leaf = leafNodes[index];
						idList.add(leaf.getId());
					}
				}
			} else if (InventoryType.Materials.getCode().equals(key.getInventoryType())) {
				MaterialsCategory goodsCategory = context.find(MaterialsCategory.class, key.getGoodsCategoryId());
				MaterialsCategory[] leafNodes = goodsCategory.getLeafNodes(context);
				if (null == leafNodes || 0 == leafNodes.length) {
					idList.add(key.getGoodsCategoryId());
				} else {
					for (int index = 0; index < leafNodes.length; index++) {
						MaterialsCategory leaf = leafNodes[index];
						idList.add(leaf.getId());
					}
				}
			}
		}
		return idList;
	}

}
