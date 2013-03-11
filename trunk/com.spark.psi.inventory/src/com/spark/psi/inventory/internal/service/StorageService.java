package com.spark.psi.inventory.internal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.cxf.tools.util.PropertyUtil;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Store;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.key.GetInventoryByStockIdKey;
import com.spark.psi.base.task.Materials.UpdateMaterialsItemAveragePurchasePriceTask;
import com.spark.psi.base.task.goods.InventoryLimitTask;
import com.spark.psi.base.task.goods.UpdateGoodsItemAveragePurchasePriceTask;
import com.spark.psi.base.utils.GoodsProperyUtil;
import com.spark.psi.base.utils.MaterialsProperyUtil;
import com.spark.psi.inventory.internal.entity.OtherGoods;
import com.spark.psi.inventory.internal.entity.OthersInventory;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.internal.key.GetInventoryDetEntityFromDBKey;
import com.spark.psi.inventory.internal.key.GetInventoryEntityByStoreIdAndGoodsIdKey;
import com.spark.psi.inventory.intf.entity.inventory.InventoryLog;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.inventory.AverageInventoryCostKey;
import com.spark.psi.inventory.intf.key.inventory.GetInitedInventoryEntityKey;
import com.spark.psi.inventory.intf.key.inventory.GetTenantsStoreAmountKey;
import com.spark.psi.inventory.intf.key.inventory.QueryKitInventoryKey;
import com.spark.psi.inventory.intf.key.resource.InventoryResourceKey;
import com.spark.psi.inventory.intf.task.inventory.AddOtherGoodsTask;
import com.spark.psi.inventory.intf.task.inventory.AdjustInventoryAmountTask;
import com.spark.psi.inventory.intf.task.inventory.DeleteOtherGoodsTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryDeliveringTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryInitTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryLockTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryOnWayTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryTask;
import com.spark.psi.inventory.intf.task.inventory.StoStreamTask;
import com.spark.psi.inventory.intf.task.inventory.UpdateOtherGoodsTask;
import com.spark.psi.inventory.intf.task.inventory.UpdateOtherStorageTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask.DetItem;
import com.spark.psi.inventory.intf.task.resource.InventoryDetResourceTask;
import com.spark.psi.inventory.intf.util.inventory.SqlBuildHelper;
import com.spark.psi.inventory.service.orm.Orm_Inventory;
import com.spark.psi.inventory.service.orm.Orm_InventoryDet;
import com.spark.psi.inventory.service.pub.InventoryServiceUtil;
import com.spark.psi.inventory.service.resource.InventoryDetEntity;
import com.spark.psi.inventory.service.resource.InventoryEntity;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;

/**
 * 库存各种业务数据操作 库存商品（物品）的规则： 库存品初始化和其他库存要显示初始的商品（物品）属性，其他业务要显示实时的商品属性，具体规则如下：
 * 在“库存商品初始化”和“其他库存”的新增，除了加入商品（物品）的GUID外，
 * 还要加入商品（物品）的相关属性：名称，商品编号（物品没有），属性（物品描述），单位 其他业务修改或新增商品库存时，不能插入或修改商品的相关属性
 * 
 * 库存表字段 初始化和其他库存业务（包括其他初始化）涉及字段: GUID recid; GUID tenantsGuid; GUID storeGuid;
 * GUID goodsGuid;
 * 
 * String goodsName; String goodsNo; String goodsProperty; String goodsUnit;
 * 
 * double initCount; double initAmount; double initUnitCost; String storeType;
 * boolean isInit; 库存商品业务涉及字段: GUID recid; GUID tenantsGuid; GUID storeGuid;
 * GUID goodsGuid;
 * 
 * double goodsCount; double goodsUnitCost; double goodsStoreAmount;
 * 
 * double onWayCount; double toDeliverCount;
 * 
 * double storeUpper; double storeFloor; double storeAmountUpper; String
 * storeType;
 * 
 * @author zhongxin
 * 
 */
public class StorageService extends Service {
	private Orm_Inventory orm_storage;
	private static String inventoryTable = ERPTableNames.Inventory.Inventory.getTableName();
	private static String inventoryDetTable = ERPTableNames.Inventory.Inventory_Det.getTableName();

	protected StorageService(Orm_Inventory orm_storage) {
		super("com.spark.psi.inventory.service.inventory.StorageService");
		this.orm_storage = orm_storage;
	}

	/*****************************************************************/
	/************************ 通用 ****************************/
	/*****************************************************************/
	/**
	 * 新增一条库存记录
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class AddGoodsStorage extends TaskMethodHandler<InventoryTask, InventoryTask.Method> {

		protected AddGoodsStorage() {
			super(InventoryTask.Method.ADD);
		}

		@Override
		protected void handle(Context context, InventoryTask task) throws Throwable {
			ORMAccessor<InventoryEntity> accessor = context.newORMAccessor(orm_storage);
			try {
				InventoryEntity entity = task.getStorageEntity();
				if (entity.getCount() < 0) {
					entity.setCount(0);
				}
				if (entity.getInventoryType().equals(InventoryType.Goods.getCode())) {
					GoodsItem goods = context.find(GoodsItem.class, entity.getStockId());
					if (null != goods) {
						setStockValues(entity, goods);
					}
				} else {
					MaterialsItem materials = context.find(MaterialsItem.class, entity.getStockId());
					if (null != materials) {
						setStockValues(entity, materials);
					}
				}
				accessor.insert(task.getStorageEntity());

				InventoryServiceUtil.updateResource(context, task.getStorageEntity(), true);
			} finally {
				accessor.unuse();
			}
		}
	}

	/*****************************************************************/
	/******************** 商品库存基本操作 *****************/
	/*****************************************************************/

	/************************* 初始化 *****************************/
	/**
	 * 修改（新增） 修改库存初始化信息，如果没有该录则新增一条，新增时同时加入初始化数据以及商品的相关属性
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ModifyStorageInitInfo extends TaskMethodHandler<InventoryInitTask, InventoryInitTask.Method> {

		protected ModifyStorageInitInfo() {
			super(InventoryInitTask.Method.SAVE);
		}

		@Override
		protected void handle(Context context, InventoryInitTask task) throws Throwable {

			Store store = context.find(Store.class, task.getStoreId());
			if (CheckIsNull.isEmpty(store) || !StoreStatus.DISABLED.equals(store.getStatus())) {
				return;
			}
			deleteInitedList(context, task);
			if (CheckIsNull.isEmpty(task.getStoreId()) || CheckIsNull.isEmpty(task.getInventoryList())) {
				return;
			}
			ORMAccessor<InventoryEntity> orm = context.newORMAccessor(orm_storage);
			for (InventoryEntity entity : task.getInventoryList()) {
				orm.insert(entity);
			}

		}

		private void deleteInitedList(Context context, InventoryInitTask task) {
			StringBuilder dnaSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();
			dnaSql.append("define delete deleteInitedLlist(@storeId guid,@inventoryType string)\n");
			paramList.add(task.getStoreId());
			paramList.add(InventoryType.Others.getCode());
			dnaSql.append("begin \n");
			dnaSql.append("delete from ");
			dnaSql.append(ERPTableNames.Inventory.Inventory.getTableName());
			dnaSql.append(" as t\n");
			dnaSql.append(" where t.storeId=@storeId \n");
			dnaSql.append("and t.inventoryType<>@inventoryType\n");
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);
			for (int index = 0; index < paramList.size(); index++) {
				db.setArgumentValue(index, paramList.get(index));
			}
			try {
				db.executeUpdate();
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 仓库启用时将指定商品在指定库存的库存初始值写入库存
	 * 
	 * 
	 */
	@Publish
	protected class AddInitCountToStorageByStore extends TaskMethodHandler<InventoryInitTask, InventoryInitTask.Method> {

		protected AddInitCountToStorageByStore() {
			super(InventoryInitTask.Method.ENABLE);
		}

		@Override
		protected void handle(Context context, InventoryInitTask task) throws Throwable {
			if (CheckIsNull.isEmpty(task.getInventoryList()) || CheckIsNull.isEmpty(task.getInventoryDetList())) {
				throw new Throwable("库存||货位信息不能为空！");
			}
			for (InventoryEntity ie : task.getInventoryList()) {
				// double totalCount = ie.getCount();
				double totalCount = ie.getInitCount();
				double count = 0;
				for (InventoryDetEntity det : task.getInventoryDetList()) {
					if (det.getStoreId().equals(ie.getStoreId()) && det.getStockId().equals(ie.getStockId())) {
						count = DoubleUtil.sum(count, det.getCount());
					}
				}
				if (totalCount != count) {
					throw new Throwable(ie.getName() + "[" + ie.getSpec() + "," + ie.getUnit() + "]" + "库存总数与货位分配总数不一致！");
				}
			}
			GetInitedInventoryEntityKey key = new GetInitedInventoryEntityKey(task.getStoreId());
			List<InventoryEntity> entityList = context.getList(InventoryEntity.class, key);
			if (CheckIsNull.isEmpty(entityList)) {
				return;
			}
			StringBuffer scriptBuffer = new StringBuffer();// 存放SQL脚本
			scriptBuffer.append("define update Up_goodsCount(@storeId guid) \n");
			scriptBuffer.append(" begin \n");
			scriptBuffer.append(" update ");
			scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
			scriptBuffer.append(" as t \n");
			scriptBuffer.append(" set \n");
			scriptBuffer.append(" \"count\" = (t.\"count\" + t.initCount), ");
			scriptBuffer.append(" amount = (t.amount + t.initAmount)");
			scriptBuffer.append(" where t.storeId = @storeId ");
			scriptBuffer.append(" end");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			try {
				db.setArgumentValues(task.getStoreId());

				if (db.executeUpdate() > 0) {

					insertInventoryLog(context, task, entityList);
					/**
					 * 更新商品平均库存成本
					 */
					for (InventoryEntity entity : entityList) {
						GetInventoryEntityByStoreIdAndGoodsIdKey sKey = new GetInventoryEntityByStoreIdAndGoodsIdKey(entity.getStoreId(), entity
								.getStockId());
						InventoryEntity nEntity = context.find(InventoryEntity.class, sKey);
						if (CheckIsNull.isNotEmpty(nEntity)) {
							InventoryServiceUtil.updateResource(context, nEntity, true);
						}
						AverageInventoryCostKey priceKey = new AverageInventoryCostKey(entity.getStockId(), InventoryType.getEnum(entity
								.getInventoryType()));
						Double price = context.find(Double.class, priceKey);
						if (CheckIsNull.isNotEmpty(price)) {
							if (InventoryType.Goods.getCode().equals(entity.getInventoryType())) {
								UpdateGoodsItemAveragePurchasePriceTask avgTask = new UpdateGoodsItemAveragePurchasePriceTask(entity.getStockId(),
										price);
								context.handle(avgTask);
							} else if (InventoryType.Materials.getCode().equals(entity.getInventoryType())) {
								UpdateMaterialsItemAveragePurchasePriceTask avgTask = new UpdateMaterialsItemAveragePurchasePriceTask(entity
										.getStockId(), price);
								context.handle(avgTask);
							}
						}
					}
					if (CheckIsNull.isNotEmpty(task.getInventoryDetList())) {
						for (InventoryDetEntity det : task.getInventoryDetList()) {
							InventoryDetResourceTask detTask = new InventoryDetResourceTask(det);
							context.handle(detTask, InventoryDetResourceTask.Type.INSERT);
						}
					}
				}
			} finally {

				db.unuse();
			}
		}
	}

	/**
	 * 获得指定租户当前的库存金额总和
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class QueryTotalAmountByTenants extends OneKeyResultProvider<Double, GetTenantsStoreAmountKey> {

		@Override
		protected Double provide(Context context, GetTenantsStoreAmountKey key) throws Throwable {
			StringBuffer scriptBuffer = new StringBuffer();
			scriptBuffer.append("define query query_store() \n");
			scriptBuffer.append("begin \n");
			scriptBuffer.append("select ");
			scriptBuffer.append("SUM(storage.\"goodsStoreAmount\") as \"goodsStoreAmount\" ");
			scriptBuffer.append(" from ");
			scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
			scriptBuffer.append(" as storage \n");
			scriptBuffer.append(" end");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			RecordSet rs;
			double tenantsStoreAmount = 0.0;
			try {
				rs = db.executeQuery();
				if (rs.next()) {
					tenantsStoreAmount = rs.getFields().get(0).getDouble();
				}
			} finally {
				db.unuse();
			}
			return tenantsStoreAmount;
		}

	}

	/**
	 * 查询初始化商品库存列表
	 */
	@Publish
	protected class GetInitedGoodsInventoryEntity extends OneKeyResultListProvider<InventoryEntity, GetInitedInventoryEntityKey> {

		@Override
		protected void provide(Context context, GetInitedInventoryEntityKey key, List<InventoryEntity> resultList) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define query queryInitedGoodsIdList( @storeId guid,@storeType string)\n");
			dnaSql.append("begin\n");
			dnaSql.append("select \n");
			dnaSql.append(getColums());

			dnaSql.append("  from \n");
			dnaSql.append(ERPTableNames.Inventory.Inventory.getTableName());
			dnaSql.append(" as t \n");
			dnaSql.append("where ");
			// dnaSql.append(" t.isInit=true\n");
			dnaSql.append(" t.storeId=@storeId\n");
			dnaSql.append("and t.inventoryType=@storeType\n");
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(key.getStoreId(), InventoryType.Materials.getCode());
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					resultList.add(fillEntity(rs));
				}
			} finally {
				db.unuse();
			}
		}

	}

	/************************* 修改业务数据 **************************/

	/**
	 * 修改库存上、下限， 金额上限 修改指定商品在指定仓库的库存上、下限、库存金额上限，如果没有该商品在该仓库的库存记录，则新增一条，并加入上、下限数据
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ModifyGoodsStoreBasic extends SimpleTaskMethodHandler<InventoryLimitTask> {

		@Override
		protected void handle(Context context, InventoryLimitTask task) throws Throwable {
			if (-1 == task.getUpperLimitAmount() && -1 == task.getLowerLimitCount() && -1 == task.getUpperLimitCount()) {
				return;
			}
			StringBuffer scriptBuffer = new StringBuffer();// 存放SQL脚本
			scriptBuffer.append("define update Up_goodsCount(@storeId guid, @stockId guid,"
					+ " @upperLimitCount double, @lowerLimitCount double, @upperLimitAmount double,@inventoryType string) \n");

			StringBuffer setColumns = new StringBuffer();
			setColumns.append(" upperLimitCount = @upperLimitCount");
			setColumns.append(",lowerLimitCount = @lowerLimitCount");
			setColumns.append(",upperLimitAmount = @upperLimitAmount");

			scriptBuffer.append(" begin \n");
			scriptBuffer.append(" update \n");
			scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
			scriptBuffer.append(" as t \n");
			scriptBuffer.append(" set \n");
			scriptBuffer.append(setColumns.toString());
			scriptBuffer.append("\n where t.storeId = @storeId and t.stockId = @stockId");
			scriptBuffer.append(" and t.inventoryType=@inventoryType\n");
			scriptBuffer.append(" end");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			try {
				int index = 0;
				db.setArgumentValue(index++, task.getStorId());
				db.setArgumentValue(index++, task.getStockId());
				db.setArgumentValue(index++, task.getUpperLimitCount());
				db.setArgumentValue(index++, task.getLowerLimitCount());
				db.setArgumentValue(index++, task.getUpperLimitAmount());
				db.setArgumentValue(index++, task.getInventoryType().getCode());
				if (1 > db.executeUpdate()) {
					InventoryEntity entity = new InventoryEntity();
					entity.setId(GUID.randomID());
					entity.setStoreId(task.getStorId());
					entity.setStockId(task.getStockId());
					if (InventoryType.Goods.equals(task.getInventoryType())) {
						GoodsItem goods = context.find(GoodsItem.class, task.getStockId());
						if (CheckIsNull.isNotEmpty(goods)) {
							setStockValues(entity, goods);
						}
					} else if (InventoryType.Materials.equals(task.getInventoryType())) {
						MaterialsItem material = context.find(MaterialsItem.class, task.getStockId());
						if (CheckIsNull.isNotEmpty(material)) {
							setStockValues(entity, material);
						}
					}

					entity.setUpperLimitCount(task.getUpperLimitCount());
					entity.setLowerLimitCount(task.getLowerLimitCount());
					entity.setUpperLimitAmount(task.getUpperLimitAmount());
					entity.setInventoryType(task.getInventoryType().getCode());

					InventoryTask addTask = new InventoryTask();
					addTask.setStorageEntity(entity);
					context.handle(addTask, InventoryTask.Method.ADD);

				} else {
					GetInventoryEntityByStoreIdAndGoodsIdKey sKey = new GetInventoryEntityByStoreIdAndGoodsIdKey(task.getStorId(), task.getStockId());
					InventoryEntity nEntity = context.find(InventoryEntity.class, sKey);
					if (CheckIsNull.isNotEmpty(nEntity)) {
						InventoryServiceUtil.updateResource(context, nEntity, false);
					}
				}

			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 修改业务数据,包括: 库存数量、库存金额 注：库存数量和库存金额是同时发生变化的，并且在发生变化的同时要更新商品的平均库存成本
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ModifyStorageBusInfo extends SimpleTaskMethodHandler<InventoryBusTask> {

		@Override
		protected void handle(Context context, InventoryBusTask task) throws Throwable {
			if (InventoryType.Materials.equals(task.getInventoryType())&&null!=task.getChangeCount()) {
				if (0 == task.getChangeCount() || null == task.getDets() || task.getDets().length < 1) {
					throw new Throwable("数量||货位分配信息不能为空！");
				}
				double count = 0;
				for (InventoryBusTask.DetItem di : task.getDets()) {
					count = DoubleUtil.sum(count, di.getChangeCount());
				}
				if (count != task.getChangeCount()) {
					throw new Throwable("总数量与货位分配数量总和不一致！");
				}
			}

			SqlBuildHelper sqlHelper = new SqlBuildHelper();
			// 如果即没有传入单价也没有传入改变的金额，就默认为平均库存成本乘以改变数量
			if (null == task.getChangeAmount() && null == task.getNewCost() && !task.isCount()) {

				Double cost = null;
				if (InventoryType.Goods.equals(task.getInventoryType())) {
					GoodsItem goods = context.find(GoodsItem.class, task.getStockId());
					cost = goods.getAvgCost();
				} else if (InventoryType.Materials.equals(task.getInventoryType())) {
					MaterialsItem materials = context.find(MaterialsItem.class, task.getStockId());
					cost = materials.getAvgBuyPrice();
				}
				if (null != task.getChangeCount()) {
					task.setChangeCountAndAmount(task.getChangeCount(), DoubleUtil.mul((null == cost ? 0 : cost), task.getChangeCount(), 2));
				}
			}
			sqlHelper.buildUpdateStorageBusSql(task);
			DBCommand db = context.prepareStatement(sqlHelper.getQuerySql());
			try {
				List<Object> parameterList = sqlHelper.getParameterList();
				for (int parameterIndex = 0; parameterIndex < parameterList.size(); parameterIndex++) {
					db.setArgumentValue(parameterIndex, parameterList.get(parameterIndex));
				}
				if (1 > db.executeUpdate()) {
					InventoryEntity entity = new InventoryEntity();
					GUID inventoryId = GUID.randomID();
					entity.setId(inventoryId);
					entity.setStoreId(task.getStoreId());
					entity.setStockId(task.getStockId());

					if (InventoryType.Goods.equals(task.getInventoryType())) {
						GoodsItem goods = context.find(GoodsItem.class, task.getStockId());
						if (CheckIsNull.isNotEmpty(goods)) {
							setStockValues(entity, goods);
						}
					} else if (InventoryType.Materials.equals(task.getInventoryType())) {
						MaterialsItem material = context.find(MaterialsItem.class, task.getStockId());
						if (CheckIsNull.isNotEmpty(material)) {
							setStockValues(entity, material);
						}
					}
					if (null != task.getChangeAmount()) {
						entity.setAmount(task.getChangeAmount());
					}
					if (null != task.getChangeCount()) {
						entity.setCount(task.getChangeCount());
						if (task.getChangeCount() < 0 && !task.isRetail()) {
							if (InventoryType.Goods.equals(task.getInventoryType()))
								throw new Throwable("商品：" + entity.getName() + "，库存数量不够，请检查！");
							else if (InventoryType.Materials.equals(task.getInventoryType()))
								throw new Throwable("材料：" + entity.getName() + "，库存数量不够，请检查！");
						}
					}
					entity.setInventoryType(task.getInventoryType().getCode());
					// entity.setInit(false);

					InventoryTask addTask = new InventoryTask();
					addTask.setStorageEntity(entity);
					context.handle(addTask, InventoryTask.Method.ADD);

					if (null != task.getChangeCount() && 0 != task.getChangeCount() && null != task.getDets() && task.getDets().length > 0) {
						for (InventoryBusTask.DetItem det : task.getDets()) {
							det.setInventoryId(inventoryId);
							saveDet(context, det);
						}
					}

				} else {
					GetInventoryEntityByStoreIdAndGoodsIdKey sKey = new GetInventoryEntityByStoreIdAndGoodsIdKey(task.getStoreId(), task.getStockId());
					InventoryEntity nEntity = context.find(InventoryEntity.class, sKey);
					if (CheckIsNull.isNotEmpty(nEntity)) {
						InventoryServiceUtil.updateResource(context, nEntity, false);
					}
					if (null != task.getChangeCount() && 0 != task.getChangeCount() && null != task.getDets() && task.getDets().length > 0) {
						for (InventoryBusTask.DetItem det : task.getDets()) {
							det.setInventoryId(nEntity.getId());
							saveDet(context, det);
						}
					}
				}
				// 更新平均库存成本
				if (task.isUpdateAvgPrice()) {
					AverageInventoryCostKey priceKey = new AverageInventoryCostKey(task.getStockId(),task.getInventoryType());
					Double avgPrice = context.find(Double.class, priceKey);
					if (CheckIsNull.isNotEmpty(avgPrice)) {

						if (InventoryType.Goods.equals(task.getInventoryType())) {
							UpdateGoodsItemAveragePurchasePriceTask avgTask = new UpdateGoodsItemAveragePurchasePriceTask(task.getStockId(), avgPrice);
							context.handle(avgTask);
						} else if (InventoryType.Materials.equals(task.getInventoryType())) {
							UpdateMaterialsItemAveragePurchasePriceTask avgTask = new UpdateMaterialsItemAveragePurchasePriceTask(task.getStockId(),
									avgPrice);
							context.handle(avgTask);
						}
					}
				}

			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 调整库存金额
	 */
	@Publish
	protected class AdjustInventoryAmount extends SimpleTaskMethodHandler<AdjustInventoryAmountTask> {

		@Override
		protected void handle(Context context, AdjustInventoryAmountTask task) throws Throwable {
			if (CheckIsNull.isEmpty(task.getStockId()) || null == task.getStoreIds() || task.getStoreIds().length <= 0) {
				return;
			}
			StringBuffer dnaSql = new StringBuffer();
			dnaSql.append("define update updateInventoryAmount(");
			dnaSql.append("@storeId guid,@stockId guid");
			dnaSql.append(",@avgInventoryCost double)\n");
			dnaSql.append("begin\n");
			dnaSql.append("update ");
			dnaSql.append(ERPTableNames.Inventory.Inventory.getTableName());
			dnaSql.append(" as t\n");
			dnaSql.append("set amount=t.\"count\"*@avgInventoryCost\n");
			dnaSql.append("where \n");
			dnaSql.append("t.storeId=@storeId\n");
			dnaSql.append("and t.stockId=@stockId\n");
			dnaSql.append("end");

			for (GUID storeId : task.getStoreIds()) {

				DBCommand db = context.prepareStatement(dnaSql);
				db.setArgumentValues(storeId, task.getStockId(), task.getAvgInventoryCost());
				if (db.executeUpdate() > 0) {
					GetInventoryEntityByStoreIdAndGoodsIdKey sKey = new GetInventoryEntityByStoreIdAndGoodsIdKey(storeId, task.getStockId());
					InventoryEntity nEntity = context.find(InventoryEntity.class, sKey);
					if (CheckIsNull.isNotEmpty(nEntity)) {
						InventoryServiceUtil.updateResource(context, nEntity, false);
					}
				}
			}
		}

	}

	/**
	 * 修改业务数据,采购在途数量
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ModifyStorageOnWayInfo extends SimpleTaskMethodHandler<InventoryOnWayTask> {

		@Override
		protected void handle(Context context, InventoryOnWayTask task) throws Throwable {
			SqlBuildHelper sqlHelper = new SqlBuildHelper();

			sqlHelper.buildUpdateStorageOnWaySql(task);
			DBCommand db = context.prepareStatement(sqlHelper.getQuerySql());
			try {
				List<Object> parameterList = sqlHelper.getParameterList();
				for (int parameterIndex = 0; parameterIndex < parameterList.size(); parameterIndex++) {
					db.setArgumentValue(parameterIndex, parameterList.get(parameterIndex));
				}
				if (1 > db.executeUpdate()) {
					InventoryEntity entity = new InventoryEntity();
					entity.setId(GUID.randomID());
					entity.setStoreId(task.getStoreId());
					entity.setStockId(task.getStockId());

					if (InventoryType.Goods.equals(task.getInventoryType())) {
						GoodsItem goods = context.find(GoodsItem.class, task.getStockId());
						if (CheckIsNull.isNotEmpty(goods)) {
							setStockValues(entity, goods);
						}
					} else if (InventoryType.Materials.equals(task.getInventoryType())) {
						MaterialsItem material = context.find(MaterialsItem.class, task.getStockId());
						if (CheckIsNull.isNotEmpty(material)) {
							setStockValues(entity, material);
						}
					}

					if (null != task.getOnWayCount()) {
						entity.setOnWayCount(task.getOnWayCount());
					}

					entity.setInventoryType(task.getInventoryType().getCode());

					InventoryTask addTask = new InventoryTask();
					addTask.setStorageEntity(entity);
					context.handle(addTask, InventoryTask.Method.ADD);

				} else {
					GetInventoryEntityByStoreIdAndGoodsIdKey sKey = new GetInventoryEntityByStoreIdAndGoodsIdKey(task.getStoreId(), task.getStockId());
					InventoryEntity nEntity = context.find(InventoryEntity.class, sKey);
					if (CheckIsNull.isNotEmpty(nEntity)) {
						InventoryServiceUtil.updateResource(context, nEntity, false);
					}
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 修改业务数据,发货需求数量 注：库存数量和库存金额是同时发生变化的，并且在发生变化的同时要更新商品的平均库存成本
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ModifyStorageDeliveringInfo extends SimpleTaskMethodHandler<InventoryDeliveringTask> {

		@Override
		protected void handle(Context context, InventoryDeliveringTask task) throws Throwable {
			SqlBuildHelper sqlHelper = new SqlBuildHelper();

			sqlHelper.buildUpdateStorageDeliveringSql(task);
			DBCommand db = context.prepareStatement(sqlHelper.getQuerySql());
			try {
				List<Object> parameterList = sqlHelper.getParameterList();
				for (int parameterIndex = 0; parameterIndex < parameterList.size(); parameterIndex++) {
					db.setArgumentValue(parameterIndex, parameterList.get(parameterIndex));
				}
				if (1 > db.executeUpdate()) {
					InventoryEntity entity = new InventoryEntity();
					entity.setId(GUID.randomID());
					entity.setStoreId(task.getStoreId());
					entity.setStockId(task.getStockId());

					if (InventoryType.Goods.equals(task.getInventoryType())) {
						GoodsItem goods = context.find(GoodsItem.class, task.getStockId());
						if (CheckIsNull.isNotEmpty(goods)) {
							setStockValues(entity, goods);
						}
					} else if (InventoryType.Materials.equals(task.getInventoryType())) {
						MaterialsItem material = context.find(MaterialsItem.class, task.getStockId());
						if (CheckIsNull.isNotEmpty(material)) {
							setStockValues(entity, material);
						}
					}
					if (null != task.getToDeliverCount()) {
						entity.setToDeliverCount(task.getToDeliverCount());
					}

					entity.setInventoryType(task.getInventoryType().getCode());

					InventoryTask addTask = new InventoryTask();
					addTask.setStorageEntity(entity);
					context.handle(addTask, InventoryTask.Method.ADD);

				} else {
					GetInventoryEntityByStoreIdAndGoodsIdKey sKey = new GetInventoryEntityByStoreIdAndGoodsIdKey(task.getStoreId(), task.getStockId());
					InventoryEntity nEntity = context.find(InventoryEntity.class, sKey);
					if (CheckIsNull.isNotEmpty(nEntity)) {
						InventoryServiceUtil.updateResource(context, nEntity, false);
					}
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 修改业务数据,锁定数量
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ModifyStorageLockInfo extends SimpleTaskMethodHandler<InventoryLockTask> {

		@Override
		protected void handle(Context context, InventoryLockTask task) throws Throwable {
			SqlBuildHelper sqlHelper = new SqlBuildHelper();

			sqlHelper.buildUpdateStorageLockSql(task);
			DBCommand db = context.prepareStatement(sqlHelper.getQuerySql());
			try {
				List<Object> parameterList = sqlHelper.getParameterList();
				for (int parameterIndex = 0; parameterIndex < parameterList.size(); parameterIndex++) {
					db.setArgumentValue(parameterIndex, parameterList.get(parameterIndex));
				}
				if (1 > db.executeUpdate()) {
					InventoryEntity entity = new InventoryEntity();
					entity.setId(GUID.randomID());
					entity.setStoreId(task.getStorId());
					entity.setStockId(task.getStockId());
					if (InventoryType.Goods.equals(task.getInventoryType())) {
						GoodsItem goods = context.find(GoodsItem.class, task.getStockId());
						if (CheckIsNull.isNotEmpty(goods)) {
							setStockValues(entity, goods);
						}
					} else if (InventoryType.Materials.equals(task.getInventoryType())) {
						MaterialsItem material = context.find(MaterialsItem.class, task.getStockId());
						if (CheckIsNull.isNotEmpty(material)) {
							setStockValues(entity, material);
						}
					}

					if (null != task.getLockedCount()) {
						entity.setLockedCount(task.getLockedCount());
						if(InventoryType.Goods.equals(task.getInventoryType()))
						{
							if(task.getLockedCount()>0)
							{
								throw new Throwable("信息已过期，请重试！");
							}
							else
							{
								return;
							}
						}
					}

					entity.setInventoryType(task.getInventoryType().getCode());

					InventoryTask addTask = new InventoryTask();
					addTask.setStorageEntity(entity);
					context.handle(addTask, InventoryTask.Method.ADD);

				} else {
					if (InventoryType.Goods.equals(task.getInventoryType())) {
						throw new Throwable("库存数量已发生改变，请重试！");
					}
					GetInventoryEntityByStoreIdAndGoodsIdKey sKey = new GetInventoryEntityByStoreIdAndGoodsIdKey(task.getStorId(), task.getStockId());
					InventoryEntity nEntity = context.find(InventoryEntity.class, sKey);
					if (CheckIsNull.isNotEmpty(nEntity)) {
						InventoryServiceUtil.updateResource(context, nEntity, false);
					}
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 查询商品平均库存成本
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class CalculateAvgBuyPrice extends OneKeyResultProvider<Double, AverageInventoryCostKey> {
		@Override
		protected Double provide(Context context, AverageInventoryCostKey key) throws Throwable {
			GetInventoryByStockIdKey iKey = new GetInventoryByStockIdKey(key.getGoodsItemId(),key.getInventoryType());
			List<Inventory> list = context.getList(Inventory.class, iKey);
			double avgPrice = 0;
			double amount = 0;
			double count = 0;
			if (CheckIsNull.isNotEmpty(list)) {
				for (Inventory inventory : list) {
					amount = DoubleUtil.sum(inventory.getAmount(), amount);
					count = DoubleUtil.sum(inventory.getCount(), count);
				}
			}
			if (0 != count) {
				avgPrice = DoubleUtil.div(amount, count);
			}
			return avgPrice;
		}

	}

	/*****************************************************************/
	/******************** 其他库存基本操作 *****************/
	/*****************************************************************/
	/**
	 * ---------------------------------------------------------------
	 * 其他库存中的物品，没有专门的表存储，直接存放在库存表中。（物品是依附仓库存在的）
	 * ---------------------------------------------------------------
	 * 物品的名称和物品描述确定了唯一的一个物品，在保存新物品的时候，
	 * ---------------------------------------------------------------
	 * 根据名称和描述为其创建一个GUID
	 * ---------------------------------------------------------------
	 */
	/**
	 * 直接修改库存初始数量 修改物品库存的数量
	 */
	@Publish
	protected class ModifyOtherGoodsStoreCount extends TaskMethodHandler<UpdateOtherGoodsTask, Method> {

		protected ModifyOtherGoodsStoreCount() {
			super(Method.MODIFY);
		}

		@Override
		protected void handle(Context context, UpdateOtherGoodsTask task) throws Throwable {
			StringBuffer scriptBuffer = new StringBuffer();// 存放SQL脚本
			scriptBuffer.append("define update Up_otherGoodsInit(@storeId guid, @name string,"
					+ " @description string, @unit string, @number double) \n");
			scriptBuffer.append(" begin \n");
			scriptBuffer.append(" update ");
			scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
			scriptBuffer.append(" as t \n");
			if (task.isInit()) {
				scriptBuffer.append(" set initCount = " + task.getOtherGoods().getNumber() + " \n");
			} else {
				scriptBuffer.append(" set \"count\" = t.\"count\"+" + task.getOtherGoods().getNumber() + " \n");
			}
			scriptBuffer.append(" where t.storeId = @storeId \n");
			if (null == task.getOtherGoods().getRecid()) {
				scriptBuffer.append(" and t.name = @name and t.unit = @unit ");
				if (null == task.getOtherGoods().getDescription()) {
					scriptBuffer.append(" and t.properties is null \n");
				} else {
					scriptBuffer.append(" and t.properties = @description \n");
				}
			} else {
				scriptBuffer.append(" and t.stockId = @stockId \n");
			}
			if (!task.isInit() && task.getOtherGoods().getNumber() < 0) {
				double changeCount = DoubleUtil.abs(task.getOtherGoods().getNumber());
				scriptBuffer.append(" and (t.\"count\">").append(changeCount).append(" or t.\"count\"=").append(changeCount).append(")");
			}
			scriptBuffer.append(" end");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			try {
				int index = 0;
				db.setArgumentValue(index++, task.getStoreId());
				db.setArgumentValue(index++, task.getOtherGoods().getName());
				db.setArgumentValue(index++, task.getOtherGoods().getDescription());
				db.setArgumentValue(index++, task.getOtherGoods().getUnit());
				db.setArgumentValue(index++, task.getOtherGoods().getNumber());
				if (1 > db.executeUpdate()) {
					if (!task.isInit() && task.getOtherGoods().getNumber() < 0) {
						throw new Throwable("物品：" + task.getOtherGoods().getName() + "，库存数量不足，请检查！");
					}
					AddOtherGoodsTask addTask = new AddOtherGoodsTask(task.getStoreId(), task.getOtherGoods());
					addTask.setInit(task.isInit());
					context.handle(addTask);
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 增加物品库存的数量
	 * 
	 * 
	 */
	@Publish
	protected class AddOtherGoodsStoreCount extends TaskMethodHandler<UpdateOtherGoodsTask, Method> {

		protected AddOtherGoodsStoreCount() {
			super(Method.ADD);
		}

		@Override
		protected void handle(Context context, UpdateOtherGoodsTask task) throws Throwable {
			Login login = context.find(Login.class);
			StringBuffer scriptBuffer = new StringBuffer();// 存放SQL脚本
			scriptBuffer.append("define update Up_otherGoodsInit(@storeGuid guid, @goodsName string,"
					+ " @goodsDescription string, @number double) \n");
			scriptBuffer.append(" begin \n");
			scriptBuffer.append(" update ");
			scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
			scriptBuffer.append(" as t set \n");
			if (task.isInit()) {
				scriptBuffer.append(" set initCount = (t.initCount + " + task.getOtherGoods().getNumber() + ") \n");
			} else {
				scriptBuffer.append(" set \"count\" = (t.\"count\" + " + task.getOtherGoods().getNumber() + ") \n");
			}
			scriptBuffer.append(" where t.storeId = @storeGuid \n");
			if (null == task.getOtherGoods().getRecid()) {
				scriptBuffer.append(" and t.name = @goodsName and t.properties = @goodsDescription \n");
			} else {
				scriptBuffer.append(" and t.stockId = @stockId \n");
			}
			scriptBuffer.append(" end");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			try {
				int index = 0;
				db.setArgumentValue(index++, login.getTenantId());
				db.setArgumentValue(index, task.getStoreId());
				db.setArgumentValue(index, task.getOtherGoods().getName());
				db.setArgumentValue(index, task.getOtherGoods().getDescription());
				db.setArgumentValue(index, task.getOtherGoods().getNumber());
				if (1 > db.executeUpdate()) {
					AddOtherGoodsTask addTask = new AddOtherGoodsTask(task.getStoreId(), task.getOtherGoods());
					addTask.setInit(task.isInit());
					context.handle(addTask);
				}
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 减少物品库存的数量
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ReduceOtherGoodsStoreCount extends TaskMethodHandler<UpdateOtherGoodsTask, Method> {

		protected ReduceOtherGoodsStoreCount() {
			super(Method.REDUCE);
		}

		@Override
		protected void handle(Context context, UpdateOtherGoodsTask task) throws Throwable {
			StringBuffer scriptBuffer = new StringBuffer();// 存放SQL脚本
			scriptBuffer.append("define update Up_otherGoodsInit(@storeGuid guid, @goodsGuid guid, @goodsName string,"
					+ " @goodsDescription string, @number double) \n");
			scriptBuffer.append(" begin \n");
			scriptBuffer.append(" update ");
			scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
			scriptBuffer.append(" as t set \n");
			if (task.isInit()) {
				scriptBuffer.append(" set initCount = (t.initCount - " + task.getOtherGoods().getNumber() + ") \n");
			} else {
				scriptBuffer.append(" set \"count\" = (t.\"count\" - " + task.getOtherGoods().getNumber() + ") \n");
			}
			scriptBuffer.append(" where t.storeId = @storeGuid \n");
			if (null == task.getOtherGoods().getRecid()) {
				scriptBuffer.append(" and t.name = @goodsName and t.properties = @goodsDescription \n");
			} else {
				scriptBuffer.append(" and t.stockId = @stockId \n");
			}
			scriptBuffer.append(" end");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			try {
				int index = 0;
				db.setArgumentValue(index++, task.getStoreId());
				db.setArgumentValue(index, task.getOtherGoods().getRecid());
				db.setArgumentValue(index, task.getOtherGoods().getName());
				db.setArgumentValue(index, task.getOtherGoods().getDescription());
				db.setArgumentValue(index, task.getOtherGoods().getNumber());
				if (1 > db.executeUpdate()) {
					AddOtherGoodsTask addTask = new AddOtherGoodsTask(task.getStoreId(), task.getOtherGoods());
					addTask.setInit(task.isInit());
					context.handle(addTask);
				}
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 新建一个该物品的库存
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class AddOtherGoods extends SimpleTaskMethodHandler<AddOtherGoodsTask> {

		@Override
		protected void handle(Context context, AddOtherGoodsTask task) throws Throwable {
			Login login = context.find(Login.class);
			if (CheckIsNull.isEmpty(task.getOtherGoodsList())) {
				InventoryEntity entity = new InventoryEntity();
				entity.setId(GUID.randomID());
				entity.setStoreId(task.getStoreGuid());
				entity.setStockId(GUID.randomID());
				if (task.isInit()) {
					entity.setInitCount(task.getOtherGoods().getNumber());
				} else {
					entity.setCount(task.getOtherGoods().getNumber());
				}
				entity.setName(task.getOtherGoods().getName());
				entity.setProperties(task.getOtherGoods().getDescription());
				entity.setUnit(task.getOtherGoods().getUnit());
				entity.setInventoryType(InventoryType.Others.getCode());
				// entity.setInit(true);
				InventoryTask addTask = new InventoryTask();
				addTask.setStorageEntity(entity);
				context.handle(addTask, InventoryTask.Method.ADD);
			} else {
				for (OtherGoods otherGoods : task.getOtherGoodsList()) {
					InventoryEntity entity = new InventoryEntity();
					entity.setId(GUID.randomID());
					entity.setStoreId(task.getStoreGuid());
					entity.setStockId(GUID.randomID());
					if (task.isInit()) {
						entity.setInitCount(otherGoods.getNumber());
					} else {
						entity.setCount(otherGoods.getNumber());
					}
					entity.setName(otherGoods.getName());
					entity.setProperties(otherGoods.getDescription());
					entity.setUnit(otherGoods.getUnit());
					entity.setInventoryType(InventoryType.Others.getCode());
					InventoryTask addTask = new InventoryTask();
					addTask.setStorageEntity(entity);
					context.handle(addTask, InventoryTask.Method.ADD);
				}
			}
		}
	}

	/**
	 * 删除一个仓库所有初始化物品信息
	 */
	@Publish
	protected class DeleteOtherGoodsByStoreId extends SimpleTaskMethodHandler<DeleteOtherGoodsTask> {

		@Override
		protected void handle(Context context, DeleteOtherGoodsTask task) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define delete deleteOtherGoodsByStoreId(@storeId guid,@inventoryType string)\n");
			dnaSql.append("begin\n");
			dnaSql.append("delete from ");
			dnaSql.append(ERPTableNames.Inventory.Inventory.getTableName());
			dnaSql.append(" as t \n");
			dnaSql.append("where \n");
			dnaSql.append(" t.storeId=@storeId\n");
			dnaSql.append("and t.inventoryType=@inventoryType\n");
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(task.getStoreId(), InventoryType.Others.getCode());
			try {
				db.executeUpdate();
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 删除指定物品的库存
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class DelOtherGoods extends TaskMethodHandler<UpdateOtherGoodsTask, Method> {

		protected DelOtherGoods() {
			super(Method.DELETE);
		}

		@Override
		protected void handle(Context context, UpdateOtherGoodsTask task) throws Throwable {
			ORMAccessor<InventoryEntity> accessor = context.newORMAccessor(orm_storage);
			try {
				accessor.delete(task.getOtherStorage().getRecid());
			} finally {
				accessor.unuse();
			}
		}

	}

	/**
	 * 将指定仓库的物品初始数量写入其库存数量
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class UpdateOtherGoodsStorageByStore extends SimpleTaskMethodHandler<UpdateOtherStorageTask> {

		@Override
		protected void handle(Context context, UpdateOtherStorageTask task) throws Throwable {
			StringBuffer scriptBuffer = new StringBuffer();// 存放SQL脚本
			scriptBuffer.append("define update Up_otherGoodsInit(@storeId guid) \n");
			scriptBuffer.append("begin \n");
			scriptBuffer.append(" update ");
			scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
			scriptBuffer.append(" as t set \n");
			scriptBuffer.append(" set \"count\" = (t.\"count\" + t.initCount) \n");
			scriptBuffer.append(" where t.storeId = @storeId and t.inventoryType = '" + InventoryType.Others.getCode() + "'\n");
			scriptBuffer.append(" end");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			try {
				db.setArgumentValue(0, task.getStoreGuid());
				db.executeUpdate();
			} finally {
				db.unuse();
			}
		}

	}

	/************************* 查询其他物品库存 **************************/

	@Publish
	protected class QueryOtherStorageByStoreId extends OneKeyResultListProvider<OthersInventory, QueryKitInventoryKey> {

		@Override
		protected void provide(Context context, QueryKitInventoryKey key, List<OthersInventory> resultList) throws Throwable {
			Login login = context.find(Login.class);
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();
			dnaSql.append("define query queryKitInventoryByStoreId(");
			dnaSql.append("@storeType string");
			conditionSql.append(" where t.inventoryType=@storeType \n");
			paramList.add(InventoryType.Others.getCode());
			if (null != key.getGoodsName() && null != key.getGoodsUnit() && null != key.getGoodsDescription()) {
				dnaSql.append(",@goodsName string");
				conditionSql.append(" and t.name=@goodsName \n");
				paramList.add(key.getGoodsName());

				dnaSql.append(",@goodsUnit string");
				conditionSql.append(" and t.unit=@goodsUnit \n");
				paramList.add(key.getGoodsUnit());

				dnaSql.append(",@goodsProperty string");
				conditionSql.append(" and t.properties=@goodsProperty \n");
				paramList.add(key.getGoodsDescription());
			}
			if (CheckIsNull.isNotEmpty(key.getSearchText())) {
				dnaSql.append(",@searchText string");
				conditionSql.append(" and (t.name like '%'+@searchText+'%' ");
				conditionSql.append(" or t.unit like '%'+@searchText+'%' ");
				conditionSql.append(" or t.properties like '%'+@searchText+'%')\n ");
				paramList.add(key.getSearchText());
			}
			// if (key.getIsInit()) {
			// conditionSql.append(" and t.isInit=true ");
			// }
			if (null == key.getStoreId()) {
				StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING };
				if (key.isQuerySummary()) {
					statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING, StoreStatus.STOP, StoreStatus.STOPANDONCOUNTING };
				}
				GetStoreListKey sKey = new GetStoreListKey(statuss);
				ListEntity<StoreItem> listEntity = context.find(ListEntity.class, sKey);
				if (CheckIsNull.isNotEmpty(listEntity.getItemList())) {
					conditionSql.append(" and (");
					for (int index = 0; index < listEntity.getItemList().size(); index++) {
						dnaSql.append(",@storeId" + index + " guid");
						if (0 != index) {
							conditionSql.append(" or ");
						}
						conditionSql.append(" t.storeId=@storeId" + index + " \n");
						paramList.add(listEntity.getItemList().get(index).getId());
					}
					conditionSql.append(")\n");
				}

			} else {
				dnaSql.append(",@storeId guid");
				conditionSql.append(" and t.storeId=@storeId \n");
				paramList.add(key.getStoreId());
			}
			dnaSql.append(") \n");
			dnaSql.append("begin \n");
			dnaSql.append(" select \n");
			if (null == key.getStoreId() && !key.isQuerySummary()) {
				dnaSql.append(getSumColums());
			} else {
				dnaSql.append(getColums());
			}

			dnaSql.append(" from ");
			dnaSql.append(ERPTableNames.Inventory.Inventory.getTableName());
			dnaSql.append(" as t \n");

			dnaSql.append(conditionSql);
			if (null == key.getStoreId() && !key.isQuerySummary()) {
				dnaSql.append("\n group by t.name,t.properties,t.unit");
			}
			if (CheckIsNull.isNotEmpty(key.getSortColumn())) {
				dnaSql.append(" order by t.").append(key.getSortColumn()).append(" ").append(key.getSortType()).append("\n");
			} else {
				dnaSql.append(" order by t.name desc\n");
			}
			dnaSql.append(" end");

			DBCommand db = context.prepareStatement(dnaSql);
			for (int index = 0; index < paramList.size(); index++) {
				db.setArgumentValue(index, paramList.get(index));
			}
			try {
				RecordSet rs;
				if (0 == key.getCount()) {
					rs = db.executeQuery();
				} else {
					rs = db.executeQueryLimit(key.getOffset(), key.getCount());
				}
				while (rs.next()) {
					InventoryEntity gi = fillEntity(rs);
					OthersInventory oi = new OthersInventory();
					oi.setGoodsCount(gi.getCount());
					oi.setGoodsGuid(gi.getStockId());
					oi.setGoodsName(gi.getName());
					oi.setGoodsProperty(gi.getProperties());
					oi.setGoodsUnit(gi.getUnit());
					oi.setInitCount(gi.getInitCount());
					oi.setRecid(gi.getId());
					oi.setStoreGuid(gi.getStoreId());

					resultList.add(oi);
				}
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 从数据库根据仓库id+商品条目id查询一条记录
	 */
	@Publish
	protected class GetGoodsInventoryEntityByStoreIdAndGoodsId extends
			OneKeyResultProvider<InventoryEntity, GetInventoryEntityByStoreIdAndGoodsIdKey> {

		@Override
		protected InventoryEntity provide(Context context, GetInventoryEntityByStoreIdAndGoodsIdKey key) throws Throwable {
			InventoryEntity entity = null;
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define query queryGoodsInventory(@storeId guid,@stockId guid) \n");
			dnaSql.append(" begin \n");
			dnaSql.append(" select \n");
			dnaSql.append(getColums());

			dnaSql.append(" from ");
			dnaSql.append(ERPTableNames.Inventory.Inventory.getTableName());
			dnaSql.append(" as t \n");

			dnaSql.append(" where \n");
			dnaSql.append("t.storeId=@storeId\n");
			dnaSql.append("and t.stockId=@stockId\n");

			dnaSql.append(" end");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(key.getStoreId(), key.getStockId());
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					entity = fillEntity(rs);
				}
			} finally {
				db.unuse();
			}
			return entity;
		}

	}

	/*************************** common methods ***************************/
	// protected void sendMessageOnStorageChange(Context context, GUID
	// tenantsGuid, GUID storeGuid, GUID goodsGuid, double changeAmount, double
	// changeCount) {
	// MessageTipUtil.GoodsStorageWaringMessageTipUtil.sendMessageTip(context,
	// tenantsGuid, storeGuid, goodsGuid, changeAmount, changeCount);
	// }
	/**
	 * 更新商品类型库存金额
	 * 
	 * @param context
	 * @param tenatsGuid
	 * @param goodsPropertyGuid
	 * @param changeAmount
	 */
	// protected void updateGoodsTypeStoreAmount(Context context, GUID
	// tenatsGuid, GUID goodsPropertyGuid, double changeAmount) {
	// //更新分类金额
	// GoodsProperty goods = context.find(GoodsProperty.class, new
	// GetPropertyByPropertyGuidKey(goodsPropertyGuid));
	// if(null != goods && null != goods.getGoodsTypeGuid()) {
	// UpdateGoodsTypeAmountTask updateTypeTask = new
	// UpdateGoodsTypeAmountTask(tenatsGuid, goods.getGoodsTypeGuid(),
	// changeAmount);
	// context.handle(updateTypeTask);
	// }
	// }
	/************************* 特殊需求 **************************/

	/************************
	 * 库存资源*****************************
	 * 
	 */
	@Publish
	protected class GoodsInventoryResourceList extends OneKeyResultListProvider<InventoryEntity, InventoryResourceKey> {

		@Override
		protected void provide(Context context, InventoryResourceKey key, List<InventoryEntity> list) throws Throwable {

			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define query queryGoodsInventoryForResource(@storeType string) \n");
			dnaSql.append(" begin \n");
			dnaSql.append(" select \n");
			dnaSql.append(getColums());

			dnaSql.append(" from ");
			dnaSql.append(ERPTableNames.Inventory.Inventory.getTableName());
			dnaSql.append(" as t \n");

			dnaSql.append(" where t.inventoryType<>@storeType \n");

			dnaSql.append(" end");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(InventoryType.Others.getCode());
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					list.add(fillEntity(rs));
				}
			} finally {
				db.unuse();
			}
		}

	}

	public InventoryEntity fillEntity(RecordSet rs) {
		InventoryEntity inventory = new InventoryEntity();
		int index = 0;

		inventory.setId(rs.getFields().get(index++).getGUID());
		inventory.setStoreId(rs.getFields().get(index++).getGUID());
		inventory.setStockId(rs.getFields().get(index++).getGUID());
		inventory.setInitCount(rs.getFields().get(index++).getDouble());
		inventory.setInitAmount(rs.getFields().get(index++).getDouble());
		inventory.setInitCost(rs.getFields().get(index++).getDouble());
		inventory.setName(rs.getFields().get(index++).getString());
		inventory.setCode(rs.getFields().get(index++).getString());
		inventory.setStockNo(rs.getFields().get(index++).getString());
		inventory.setCount(rs.getFields().get(index++).getDouble());
		inventory.setAmount(rs.getFields().get(index++).getDouble());
		inventory.setUnit(rs.getFields().get(index++).getString());
		inventory.setSpec(rs.getFields().get(index++).getString());
		inventory.setOnWayCount(rs.getFields().get(index++).getDouble());
		inventory.setToDeliverCount(rs.getFields().get(index++).getDouble());
		inventory.setUpperLimitCount(rs.getFields().get(index++).getDouble());
		inventory.setLowerLimitCount(rs.getFields().get(index++).getDouble());
		inventory.setUpperLimitAmount(rs.getFields().get(index++).getDouble());
		inventory.setInventoryType(rs.getFields().get(index++).getString());
		inventory.setLockedCount(rs.getFields().get(index++).getDouble());
		inventory.setProperties(rs.getFields().get(index++).getString());

		return inventory;
	}

	public void saveDet(Context context, DetItem det) throws Throwable {
		StringBuffer sql = new StringBuffer();
		sql.append("define update updateDet(@stockId guid,@storeId guid,@shelfId guid,@tiersNo int,@produceDate date,@changeCount double)\n");
		sql.append("begin\n");
		sql.append("update\n");
		sql.append(inventoryDetTable);
		sql.append(" as t\n");
		sql.append(" set \"count\"=t.\"count\"+@changeCount");
		sql.append(" where ");
		sql.append(" t.stockId=@stockId\n");
		sql.append(" and t.storeId=@storeId\n");
		sql.append(" and t.shelfId=@shelfId\n");
		sql.append(" and t.tiersNo=@tiersNo\n");
		sql.append(" and t.produceDate=@produceDate\n");
		if (det.getChangeCount() < 0) {
			sql.append(" and (t.\"count\">");
			sql.append(DoubleUtil.abs(det.getChangeCount()));
			sql.append(" or t.\"count\"=");
			sql.append(DoubleUtil.abs(det.getChangeCount()));
			sql.append(") \n");
		}
		sql.append("end\n");

		DBCommand db = context.prepareStatement(sql);
		db.setArgumentValues(det.getStockId(), det.getStoreId(), det.getShelfId(), det.getTiersNo(), det.getProduceDate(), det.getChangeCount());
		if (db.executeUpdate() > 0) {
			GetInventoryDetEntityFromDBKey key = new GetInventoryDetEntityFromDBKey(det.getShelfId(), det.getTiersNo(), det.getStockId(), det
					.getProduceDate(), det.getStoreId());
			InventoryDetEntity inventoryDetEntity = context.find(InventoryDetEntity.class, key);
			InventoryDetResourceTask task = new InventoryDetResourceTask(inventoryDetEntity);
			context.handle(task, InventoryDetResourceTask.Type.UPDATE);
		} else {
			if (det.getChangeCount() < 0) {
				Store store = context.find(Store.class, det.getStoreId());
				throw new Throwable("仓库" + store.getName() + "：货位" + det.getShelfNo() + "，第" + det.getTiersNo() + "层，库存数量不够，请检查！");
			}
			InventoryDetEntity entity = new InventoryDetEntity();
			entity.setCount(det.getChangeCount());
			entity.setId(GUID.randomID());
			entity.setInventoryId(det.getInventoryId());
			entity.setProduceDate(det.getProduceDate());
			entity.setShelfId(det.getShelfId());
			entity.setShelfNo(det.getShelfNo());
			entity.setStockId(det.getStockId());
			entity.setStoreId(det.getStoreId());
			entity.setTiersNo(det.getTiersNo());

			InventoryDetResourceTask task = new InventoryDetResourceTask(entity);
			context.handle(task, InventoryDetResourceTask.Type.INSERT);
		}

	}

	public void setStockValues(InventoryEntity entity, Object stock) {
		if (stock instanceof GoodsItem) {

			entity.setName(((GoodsItem) stock).getGoodsName());
			entity.setCode(((GoodsItem) stock).getGoodsCode());
			entity.setSpec(((GoodsItem) stock).getSpec());
			entity.setUnit(((GoodsItem) stock).getGoodsUnit());
			entity.setStockNo(((GoodsItem) stock).getGoodsNo());
			entity.setScale(((GoodsItem) stock).getScale());
			entity.setProperties(GoodsProperyUtil.subGoodsPropertyToString(((GoodsItem) stock).getGoodsProperties()));
		} else if (stock instanceof MaterialsItem) {
			entity.setName(((MaterialsItem) stock).getMaterialName());
			entity.setCode(((MaterialsItem) stock).getMaterialCode());
			entity.setSpec(((MaterialsItem) stock).getSpec());
			entity.setUnit(((MaterialsItem) stock).getMaterialUnit());
			entity.setStockNo(((MaterialsItem) stock).getMaterialNo());
			entity.setScale(((MaterialsItem) stock).getScale());
			entity.setProperties(MaterialsProperyUtil.subMaterialsPropertyToString(((MaterialsItem) stock).getMaterialProperties()));
		}
	}

	public Object getSumColums() {
		StringBuilder dnaSql = new StringBuilder();

		// dnaSql.append(" t.RECID as RECID");
		// dnaSql.append(" ,t.storeId as storeId");
		// dnaSql.append(" ,t.stockId as stockId");
		// dnaSql.append(" ,sum(t.initCount) as initCount");
		// dnaSql.append(" ,sum(t.initAmount) as initAmount");
		// dnaSql.append(" ,t.initCost as initCost");
		// dnaSql.append(" ,t.name as name");
		// dnaSql.append(" ,t.code as code");
		// dnaSql.append(" ,t.stockNo as stockNo");
		// dnaSql.append(" ,sum(t.\"count\") as \"count\"");
		// dnaSql.append(" ,sum(t.amount) as amount");
		// dnaSql.append(" ,t.unit as unit");
		// dnaSql.append(" ,t.spec as spec");
		// dnaSql.append(" ,sum(t.onWayCount) as onWayCount");
		// dnaSql.append(" ,sum(t.toDeliverCount) as toDeliverCount");
		// dnaSql.append(" ,sum(t.upperLimitCount) as upperLimitCount");
		// dnaSql.append(" ,sum(t.lowerLimitCount) as lowerLimitCount");
		// dnaSql.append(" ,sum(t.upperLimitAmount) as upperLimitAmount");
		// dnaSql.append(" ,t.inventoryType as inventoryType");
		// dnaSql.append(" ,sum(t.lockedCount) as lockedCount \n");

		dnaSql.append(" t.RECID as RECID");
		dnaSql.append(" ,t.storeId as storeId");
		dnaSql.append(" ,t.stockId as stockId");
		dnaSql.append(" ,sum(t.initCount) as initCount");
		dnaSql.append(" ,sum(t.initAmount) as initAmount");
		dnaSql.append(" ,t.initCost as initCost");
		dnaSql.append(" ,t.\"name\" as \"name\"");
		dnaSql.append(" ,t.code as code");
		dnaSql.append(" ,t.stockNo as stockNo");
		dnaSql.append(" ,sum(t.\"count\") as \"count\"");
		dnaSql.append(" ,sum(t.amount) as amount");
		dnaSql.append(" ,t.unit as unit");
		dnaSql.append(" ,t.spec as spec");
		dnaSql.append(" ,sum(t.onWayCount) as onWayCount");
		dnaSql.append(" ,sum(t.toDeliverCount) as toDeliverCount");
		dnaSql.append(" ,sum(t.upperLimitCount) as upperLimitCount");
		dnaSql.append(" ,sum(t.lowerLimitCount) as lowerLimitCount");
		dnaSql.append(" ,sum(t.upperLimitAmount) as upperLimitAmount");
		dnaSql.append(" ,t.inventoryType as inventoryType");
		dnaSql.append(" ,sum(t.lockedCount) as lockedCount \n");
		dnaSql.append(" ,t.properties as properties \n");

		return dnaSql;
	}

	/**
	 * 仓库启用时插入初始化库存流水
	 * 
	 * @param context
	 * @param task
	 * @param entityList
	 *            void
	 */
	public void insertInventoryLog(Context context, InventoryInitTask task, List<InventoryEntity> entityList) {
		for (InventoryEntity entity : entityList) {
			InventoryLogEntity log = new InventoryLogEntity();
			log.setId(context.newRECID());
			log.setStoreId(task.getStoreId());
			log.setCreatedDate(new Date().getTime());
			log.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId()).getName());
			log.setCreatedDate(new Date().getTime());
			log.setStockId(entity.getStockId());
			log.setInventoryType(entity.getInventoryType());
			if (InventoryType.Materials.getCode().equals(entity.getInventoryType())) {
				MaterialsItem materials = context.find(MaterialsItem.class, entity.getStockId());
				if (null != materials) {
					log.setProperties(MaterialsProperyUtil.subMaterialsPropertyToString(materials.getMaterialProperties()));
					log.setName(materials.getMaterialName());
					log.setStockNo(materials.getMaterialNo());
					log.setCategoryId(materials.getCategoryId());
					log.setUnit(materials.getMaterialUnit());
					log.setCode(materials.getMaterialCode());
				}
			}

			log.setInstoCount(entity.getInitCount());
			log.setInstoAmount(entity.getInitAmount() < 0 ? 0 : entity.getInitAmount());
			log.setLogType(InventoryLogType.INITVALUE.getCode());

			StoStreamTask sTask = new StoStreamTask();
			sTask.setStoStream(log);
			context.handle(sTask, StoStreamTask.Task.add);
		}
	}

	public Object getColums() {
		StringBuilder dnaSql = new StringBuilder();

		dnaSql.append(" t.RECID as RECID");
		dnaSql.append(" ,t.storeId as storeId");
		dnaSql.append(" ,t.stockId as stockId");
		dnaSql.append(" ,t.initCount as initCount");
		dnaSql.append(" ,t.initAmount as initAmount");
		dnaSql.append(" ,t.initCost as initCost");
		dnaSql.append(" ,t.\"name\" as \"name\"");
		dnaSql.append(" ,t.code as code");
		dnaSql.append(" ,t.stockNo as stockNo");
		dnaSql.append(" ,t.\"count\" as \"count\"");
		dnaSql.append(" ,t.amount as amount");
		dnaSql.append(" ,t.unit as unit");
		dnaSql.append(" ,t.spec as spec");
		dnaSql.append(" ,t.onWayCount as onWayCount");
		dnaSql.append(" ,t.toDeliverCount as toDeliverCount");
		dnaSql.append(" ,t.upperLimitCount as upperLimitCount");
		dnaSql.append(" ,t.lowerLimitCount as lowerLimitCount");
		dnaSql.append(" ,t.upperLimitAmount as upperLimitAmount");
		dnaSql.append(" ,t.inventoryType as inventoryType");
		dnaSql.append(" ,t.lockedCount as lockedCount \n");
		dnaSql.append(" ,t.properties as properties \n");

		return dnaSql;
	}
}
