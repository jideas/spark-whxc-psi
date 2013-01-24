package com.spark.psi.inventory.service.resource;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Store;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.key.GetInventoryByStockIdKey;
import com.spark.psi.base.key.GetGoodsInventoryByStoreIdKey;
import com.spark.psi.inventory.intf.key.resource.InventoryResourceKey;
import com.spark.psi.inventory.intf.task.resource.InventoryResourceTask;
import com.spark.psi.publish.InventoryType;

/**
 * <p>
 * 库存资源服务
 * </p>
 * 
 */

public class InventoryResourceService extends ResourceService<Inventory, InventoryEntity, InventoryEntity> {

	protected InventoryResourceService() {
		super("com.spark.psi.inventory.service.resource.InventoryResourceService", ResourceKind.SINGLETON_IN_CLUSTER);

	}

	@Override
	protected void init(Context context) {
		context.getList(Inventory.class);
	}

	@Override
	protected void initResources(Context context, ResourceInserter<Inventory, InventoryEntity, InventoryEntity> initializer)
			throws Throwable {
		InventoryResourceKey key = new InventoryResourceKey();
		List<InventoryEntity> list = context.getList(InventoryEntity.class, key);
		if (null != list) {
			for (InventoryEntity inventory : list) {
				ResourceToken<Inventory> inventoryToken = initializer.putResource(inventory);
				ResourceToken<Store> storeToken = context.findResourceToken(Store.class, inventory.getStoreId());
				initializer.putResourceReference(storeToken, inventoryToken);
				if (inventory.getInventoryType().equals(InventoryType.Goods.getCode())) {
					ResourceToken<GoodsItem> goodsItemToken = context.findResourceToken(GoodsItem.class, inventory.getStockId());
					initializer.putResourceReference(goodsItemToken, inventoryToken);
				} else if (inventory.getInventoryType().equals(InventoryType.Materials.getCode())) {
					ResourceToken<MaterialsItem> materialsItemToken = context.findResourceToken(MaterialsItem.class, inventory.getStockId());
					initializer.putResourceReference(materialsItemToken, inventoryToken);
				}

			}
		}

	}

	protected class TenantResourcesReference extends ReferredByResource<Tenant> {
	}

	protected class GoodsItemResourceReference extends ReferredByResource<GoodsItem> {
	}

	protected class StoreResourceReference extends ReferredByResource<Store> {
	}
	
	protected class MaterialsItemResourceReference extends ReferredByResource<MaterialsItem> {
	}

	/**
	 * 
	 * <p>
	 * 通过RECID查询一条库存
	 * </p>
	 * 
	 */
	@Publish
	protected class GetGoodsInventoryResourceById extends OneKeyResourceProvider<GUID> {

		@Override
		protected GUID getKey1(InventoryEntity keysHolder) {
			return keysHolder.getId();
		}

	}

	/**
	 * 
	 * <p>
	 * 通过仓库ID+存货ID查询一条库存
	 * </p>
	 * 
	 */
	@Publish
	protected class GetGoodsInventoryResource extends TwoKeyResourceProvider<GUID, GUID> {

		@Override
		protected GUID getKey1(InventoryEntity keysHolder) {
			return keysHolder.getStoreId();
		}

		@Override
		protected GUID getKey2(InventoryEntity keysHolder) {
			return keysHolder.getStockId();
		}
	}

	/**
	 * 
	 * <p>
	 * 查询租户下所有库存
	 * </p>
	 * 
	 */
	@Publish
	final protected class GetTenantGoodsInventory extends ResultListProvider<Inventory> {

		@Override
		protected void provide(ResourceContext<Inventory, InventoryEntity, InventoryEntity> context, List<Inventory> resultList)
				throws Throwable {
			try {
				resultList.addAll(context.getResourceReferences(Inventory.class, context.findResourceToken(Tenant.class)));
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 根据租户ID查询所有库存信息
	 * </p>
	 * 
	 */
//	@Publish
//	final protected class GetGoodsInventoryListByTenantId extends OneKeyResultListProvider<Inventory, GetGoodsInventoryByTenantIdKey> {
//
//		@Override
//		protected void provide(ResourceContext<Inventory, InventoryEntity, InventoryEntity> context,
//				GetGoodsInventoryByTenantIdKey key, List<Inventory> resultList) throws Throwable {
//
//			if (CheckIsNull.isNotEmpty(key.getTenantId())) {
//				try {
//					resultList.addAll(context.getResourceReferences(Inventory.class, context.findResourceToken(Tenant.class, key.getTenantId())));
//				} catch (Exception e) {
//				}
//			}
//
//		}
//
//	}

	/**
	 * 
	 * <p>
	 * 根据存货ID查询所有库存信息
	 * </p>
	 * 
	 */
	@Publish
	final protected class GetGoodsInventoryListByGoodsId extends OneKeyResultListProvider<Inventory, GetInventoryByStockIdKey> {

		@Override
		protected void provide(ResourceContext<Inventory, InventoryEntity, InventoryEntity> context,
				GetInventoryByStockIdKey key, List<Inventory> resultList) throws Throwable {

			if (CheckIsNull.isNotEmpty(key.getStockId())) {
				try {
					if(InventoryType.Goods.equals(key.getInventoryType()))
					resultList.addAll(context.getResourceReferences(Inventory.class, context.findResourceToken(GoodsItem.class, key
							.getStockId())));
					else if(InventoryType.Materials.equals(key.getInventoryType()))
						resultList.addAll(context.getResourceReferences(Inventory.class, context.findResourceToken(MaterialsItem.class, key
								.getStockId())));
				} catch (Exception e) {
				}
			}

		}

	}

	/**
	 * 
	 * <p>
	 * 根据仓库ID查询所有库存信息
	 * </p>
	 */
	@Publish
	final protected class GetGoodsInventoryListByStoreId extends OneKeyResultListProvider<Inventory, GetGoodsInventoryByStoreIdKey> {

		@Override
		protected void provide(ResourceContext<Inventory, InventoryEntity, InventoryEntity> context,
				GetGoodsInventoryByStoreIdKey key, List<Inventory> resultList) throws Throwable {

			if (CheckIsNull.isNotEmpty(key.getStoreId())) {
				try {
					ResourceToken<Store> storeToken = context.findResourceToken(Store.class, key.getStoreId());
					resultList.addAll(context.getResourceReferences(Inventory.class, storeToken));
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
	protected class InsertResourceHandler extends TaskMethodHandler<InventoryResourceTask, InventoryResourceTask.Type> {

		protected InsertResourceHandler() {
			super(InventoryResourceTask.Type.INSERT);
		}

		@Override
		protected void handle(ResourceContext<Inventory, InventoryEntity, InventoryEntity> context, InventoryResourceTask task)
				throws Throwable {

			InventoryEntity goodsInventoryEntity = task.getInventoryEntity();
			if (null == goodsInventoryEntity) {
				return;
			}

			ResourceToken<Inventory> inventoryToken = context.putResource(goodsInventoryEntity);
			ResourceToken<Store> storeToken = context.findResourceToken(Store.class, goodsInventoryEntity.getStoreId());
			context.putResourceReference(storeToken, inventoryToken);
			if (goodsInventoryEntity.getInventoryType().equals(InventoryType.Goods.getCode())) {
				ResourceToken<GoodsItem> goodsItemToken = context.findResourceToken(GoodsItem.class, goodsInventoryEntity.getStockId());
				context.putResourceReference(goodsItemToken, inventoryToken);
			} else if (goodsInventoryEntity.getInventoryType().equals(InventoryType.Materials.getCode())) {
				ResourceToken<MaterialsItem> materialsItemToken = context.findResourceToken(MaterialsItem.class, goodsInventoryEntity.getStockId());
				context.putResourceReference(materialsItemToken, inventoryToken);
			}

		}

	}

	/**
	 * 
	 * <p>
	 * 更新资源
	 * </p>
	 */
	@Publish
	protected class UpdateResourceHandler extends TaskMethodHandler<InventoryResourceTask, InventoryResourceTask.Type> {

		protected UpdateResourceHandler() {
			super(InventoryResourceTask.Type.UPDATE);
		}

		@Override
		protected void handle(ResourceContext<Inventory, InventoryEntity, InventoryEntity> context, InventoryResourceTask task)
				throws Throwable {

			InventoryEntity inventoryEntity = task.getInventoryEntity();
			if (null == inventoryEntity) {
				return;
			}
			InventoryEntity entity = context.modifyResource(inventoryEntity.getId());

			entity.setCount(inventoryEntity.getCount());
			entity.setAmount(inventoryEntity.getAmount());
			entity.setLockedCount(inventoryEntity.getLockedCount());
			entity.setOnWayCount(inventoryEntity.getOnWayCount());
			entity.setUpperLimitAmount(inventoryEntity.getUpperLimitAmount());
			entity.setLowerLimitCount(inventoryEntity.getLowerLimitCount());
			entity.setUpperLimitCount(inventoryEntity.getUpperLimitCount());
			entity.setToDeliverCount(inventoryEntity.getToDeliverCount());
			context.postModifiedResource(entity);
		}

	}

}
