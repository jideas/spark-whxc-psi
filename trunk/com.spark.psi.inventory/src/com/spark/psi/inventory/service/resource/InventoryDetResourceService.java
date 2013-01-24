package com.spark.psi.inventory.service.resource;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.ComparatorUtils;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.InventoryDet;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.internal.key.GetInventoryDetEntityFromDBKey;
import com.spark.psi.inventory.intf.publish.entity.InventoryDetItemImpl;
import com.spark.psi.inventory.intf.task.resource.InventoryDetResourceTask;
import com.spark.psi.inventory.service.orm.Orm_InventoryDet;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.inventory.entity.InventoryDetItem;
import com.spark.psi.publish.inventory.key.GetInventoryDetByInventoryIdKey;
import com.spark.psi.publish.inventory.key.GetInventoryDetByStockIdKey;
import com.spark.psi.publish.inventory.key.GetInventoryDetByStoreAndStockIdKey;

/**
 * <p>
 * 库存明细资源服务
 * </p>
 * 
 */

public class InventoryDetResourceService extends ResourceService<InventoryDet, InventoryDetEntity, InventoryDetEntity> {

	final Orm_InventoryDet orm_InventoryDet;
	protected InventoryDetResourceService(Orm_InventoryDet orm_InventoryDet) {
		super("com.spark.psi.inventory.service.resource.InventoryDetResourceService", ResourceKind.SINGLETON_IN_CLUSTER);
		this.orm_InventoryDet = orm_InventoryDet;

	}

	@Override
	protected void init(Context context) {
		context.getList(InventoryDet.class);
	}

	@Override
	protected void initResources(Context context, ResourceInserter<InventoryDet, InventoryDetEntity, InventoryDetEntity> initializer)
			throws Throwable {
		List<InventoryDetEntity> list = getAllInventoryDet(context);
		if (null != list) {
			for (InventoryDetEntity det : list) {
				ResourceToken<InventoryDet> detToken = initializer.putResource(det);
				ResourceToken<Inventory> inventoryToken = context.findResourceToken(Inventory.class, det.getInventoryId());
				initializer.putResourceReference(inventoryToken, detToken);
			}
		}

	}
	
	protected class InventoryResourceReference extends ReferredByResource<Inventory> {
	}

	private List<InventoryDetEntity> getAllInventoryDet(Context context) {
		List<InventoryDetEntity> list = new ArrayList<InventoryDetEntity>();
		StringBuffer sql = new StringBuffer();
		sql.append("define query getAllInventoryDet()\n");
		sql.append("begin\n");
		sql.append("select\n");
		sql.append(getColums());
		sql.append("from\n");
		sql.append(ERPTableNames.Inventory.Inventory_Det.getTableName());
		sql.append(" as t\n");
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);
		try {
			RecordSet rs = db.executeQuery();
			while (rs.next()) {
				list.add(fillEntity(rs));
			}
		} finally {
			db.unuse();
		}
		return list;
	}

	private InventoryDetEntity fillEntity(RecordSet rs) {
		InventoryDetEntity entity = new InventoryDetEntity();
		int index = 0;
		entity.setId(rs.getFields().get(index++).getGUID());
		entity.setShelfId(rs.getFields().get(index++).getGUID());
		entity.setShelfNo(rs.getFields().get(index++).getInt());
		entity.setTiersNo(rs.getFields().get(index++).getInt());
		entity.setStockId(rs.getFields().get(index++).getGUID());
		entity.setCount(rs.getFields().get(index++).getDouble());
		entity.setProduceDate(rs.getFields().get(index++).getDate());
		entity.setInventoryId(rs.getFields().get(index++).getGUID());
		entity.setStoreId(rs.getFields().get(index++).getGUID());
		return entity;
	}

	private Object getColums() {
		StringBuffer sb = new StringBuffer();

		sb.append("t.RECID as id,");
		sb.append("t.shelfId as shelfId,");
		sb.append("t.shelfNo as shelfNo,");
		sb.append("t.tiersNo as tiersNo,");
		sb.append("t.stockId as stockId,");
		sb.append("t.\"count\" as \"count\",");
		sb.append("t.produceDate as produceDate,");
		sb.append("t.inventoryId as inventoryId,");
		sb.append("t.storeId as storeId\n");

		return sb;
	}

	/**
	 * 
	 * <p>
	 * 通过RECID查询一条库存明细
	 * </p>
	 * 
	 */
	@Publish
	protected class GetGoodsInventoryDetResourceById extends OneKeyResourceProvider<GUID> {

		@Override
		protected GUID getKey1(InventoryDetEntity keysHolder) {
			return keysHolder.getId();
		}

	}
	
	/**
	 * 通过库存ID查询库存明细
	 */
	@Publish
	protected class GetInventoryDetListByInventoryId extends OneKeyResultListProvider<InventoryDetItem, GetInventoryDetByInventoryIdKey> {

		@Override
		protected void provide(ResourceContext<InventoryDet, InventoryDetEntity, InventoryDetEntity> context, GetInventoryDetByInventoryIdKey key,
				List<InventoryDetItem> resultList) throws Throwable {
			if (CheckIsNull.isNotEmpty(key.getInventoryId())) {
				try {
					ResourceToken<Inventory> inventoryToken = context.findResourceToken(Inventory.class, key.getInventoryId());
					List<InventoryDet> list = context.getResourceReferences(InventoryDet.class, inventoryToken);
					if (CheckIsNull.isNotEmpty(list)) {
						for (InventoryDet det : list) {
							resultList.add(setValues(context, det, InventoryType.getEnum(inventoryToken.getFacade().getInventoryType())));
							
						}
					}
				} catch (Exception e) {
				}
			}
		}

	}

	/**
	 * 通过存货ID查询库存明细
	 */
	@Publish
	protected class GetInventoryDetListByStockId extends OneKeyResultListProvider<InventoryDetItem, GetInventoryDetByStockIdKey> {

		@Override
		protected void provide(ResourceContext<InventoryDet, InventoryDetEntity, InventoryDetEntity> context, GetInventoryDetByStockIdKey key,
				List<InventoryDetItem> resultList) throws Throwable {
			if (CheckIsNull.isNotEmpty(key.getStockId())) {
				try {
					List<InventoryDet> list = context.getList(InventoryDet.class);
					if (CheckIsNull.isNotEmpty(list)) {
						for (InventoryDet det : list) {
							if(det.getStockId().equals(key.getStockId())&&det.getCount()>0)
							resultList.add(setValues(context, det, key.getInventoryType()));
							ComparatorUtils.sort(resultList,"produceDate",false);
						}
					}
				} catch (Exception e) {
				}
			}
		}

	}
	
	/**
	 * 通过仓库ID+存货ID查询库存明细
	 */
	@Publish
	protected class GetInventoryDetListByStoreAndStockId extends OneKeyResultListProvider<InventoryDetItem, GetInventoryDetByStoreAndStockIdKey> {

		@Override
		protected void provide(ResourceContext<InventoryDet, InventoryDetEntity, InventoryDetEntity> context, GetInventoryDetByStoreAndStockIdKey key,
				List<InventoryDetItem> resultList) throws Throwable {
			if (CheckIsNull.isNotEmpty(key.getStockId())&&CheckIsNull.isNotEmpty(key.getStoreId())) {
				try {
					List<InventoryDet> list = context.getList(InventoryDet.class);
					if (CheckIsNull.isNotEmpty(list)) {
						for (InventoryDet det : list) {
							if(det.getStockId().equals(key.getStockId())&&det.getStoreId().equals(key.getStoreId()))
							resultList.add(setValues(context, det, key.getInventoryType()));
							
						}
					}
				} catch (Exception e) {
				}
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 新增资源
	 * </p>
	 * 
	 */
	@Publish
	protected class InsertResourceHandler extends TaskMethodHandler<InventoryDetResourceTask, InventoryDetResourceTask.Type> {

		protected InsertResourceHandler() {
			super(InventoryDetResourceTask.Type.INSERT);
		}

		@Override
		protected void handle(ResourceContext<InventoryDet, InventoryDetEntity, InventoryDetEntity> context, InventoryDetResourceTask task)
				throws Throwable {

			InventoryDetEntity inventoryDetEntity = task.getInventoryDetEntity();
			if (null == inventoryDetEntity) {
				return;
			}
			ORMAccessor<InventoryDetEntity> orm = context.newORMAccessor(orm_InventoryDet);
			orm.insert(inventoryDetEntity);
			orm.unuse();

			ResourceToken<InventoryDet> inventoryDetToken = context.putResource(inventoryDetEntity);
			ResourceToken<Inventory> inventoryToken = context.findResourceToken(Inventory.class, inventoryDetEntity.getInventoryId());
			context.putResourceReference(inventoryToken, inventoryDetToken);

		}

	}

	/**
	 * 
	 * <p>
	 * 更新资源
	 * </p>
	 */
	@Publish
	protected class UpdateResourceHandler extends TaskMethodHandler<InventoryDetResourceTask, InventoryDetResourceTask.Type> {

		protected UpdateResourceHandler() {
			super(InventoryDetResourceTask.Type.UPDATE);
		}

		@Override
		protected void handle(ResourceContext<InventoryDet, InventoryDetEntity, InventoryDetEntity> context, InventoryDetResourceTask task)
				throws Throwable {

			InventoryDetEntity inventoryDetEntity = task.getInventoryDetEntity();
			if (null == inventoryDetEntity) {
				return;
			}
			InventoryDetEntity entity = context.modifyResource(inventoryDetEntity.getId());

			entity.setCount(inventoryDetEntity.getCount());
			context.postModifiedResource(entity);
		}

	}

	public InventoryDetItem setValues(ResourceContext<InventoryDet, InventoryDetEntity, InventoryDetEntity> context, InventoryDet det,
			InventoryType type) {
		InventoryDetItemImpl impl = new InventoryDetItemImpl();
		impl.setCount(det.getCount());
		impl.setId(det.getId());
		impl.setInventoryId(det.getInventoryId());
		impl.setProduceDate(det.getProduceDate());
		impl.setShelfId(det.getShelfId());
		impl.setShelfNo(det.getShelfNo());
		impl.setStockId(det.getStockId());
		impl.setStoreId(det.getStoreId());
		impl.setTiersNo(det.getTiersNo());
		
		Store store = context.find(Store.class, det.getStoreId());
		if(null!=store)
		{
			impl.setStoreName(store.getName());
		}
		if(InventoryType.Materials.equals(type))
		{
			MaterialsItem item = context.find(MaterialsItem.class, impl.getStockId());
			if(null!=item)
			{
				impl.setCode(item.getMaterialCode());
				impl.setName(item.getMaterialName());
				impl.setSpec(item.getSpec());
				impl.setUnit(item.getMaterialUnit());
				impl.setProperties(item.getMaterialProperties());
			}
		}
		else if(InventoryType.Goods.equals(type))
		{
			GoodsItem item = context.find(GoodsItem.class, impl.getStockId());
			if(null!=item)
			{
				impl.setCode(item.getGoodsCode());
				impl.setName(item.getGoodsName());
				impl.setSpec(item.getSpec());
				impl.setUnit(item.getGoodsUnit());
				impl.setProperties(item.getGoodsProperties());
			}
		}
		return impl;
	}
	
	/**
	 * 从数据库查询一条库存明细
	 */
	@Publish
	protected class GetInventoryDetEntityFromDB extends OneKeyResultProvider<InventoryDetEntity,GetInventoryDetEntityFromDBKey>
	{

		@Override
		protected InventoryDetEntity provide(ResourceContext<InventoryDet, InventoryDetEntity, InventoryDetEntity> context,
				GetInventoryDetEntityFromDBKey key) throws Throwable {
			StringBuffer sql = new StringBuffer();
			sql.append("define query getInventoryDet(@stockId guid, @storeId guid, @shelfId guid, @tiersNo int, @produceDate date)\n");
			sql.append("begin\n");
			sql.append("select\n");
			sql.append(getColums());
			sql.append("from\n");
			sql.append(ERPTableNames.Inventory.Inventory_Det.getTableName());
			sql.append(" as t\n");
			sql.append(" where ");
			sql.append(" t.stockId=@stockId\n");
			sql.append(" and t.storeId=@storeId\n");
			sql.append(" and t.shelfId=@shelfId\n");
			sql.append(" and t.tiersNo=@tiersNo\n");
			sql.append(" and t.produceDate=@produceDate\n");
			sql.append("end");

			DBCommand db = context.prepareStatement(sql);
			db.setArgumentValue(0, key.getStockId());
			db.setArgumentValue(1, key.getStoreId());
			db.setArgumentValue(2, key.getShelfId());
			db.setArgumentValue(3, key.getTiersNo());
			db.setArgumentValue(4, key.getProduceDate());
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					 return fillEntity(rs);
				}
			} finally {
				db.unuse();
			}
			return null;
		}
		
	}

}
