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
 * ������ҵ�����ݲ��� �����Ʒ����Ʒ���Ĺ��� ���Ʒ��ʼ�����������Ҫ��ʾ��ʼ����Ʒ����Ʒ�����ԣ�����ҵ��Ҫ��ʾʵʱ����Ʒ���ԣ�����������£�
 * �ڡ������Ʒ��ʼ�����͡�������桱�����������˼�����Ʒ����Ʒ����GUID�⣬
 * ��Ҫ������Ʒ����Ʒ����������ԣ����ƣ���Ʒ��ţ���Ʒû�У������ԣ���Ʒ����������λ ����ҵ���޸Ļ�������Ʒ���ʱ�����ܲ�����޸���Ʒ���������
 * 
 * �����ֶ� ��ʼ�����������ҵ�񣨰���������ʼ�����漰�ֶ�: GUID recid; GUID tenantsGuid; GUID storeGuid;
 * GUID goodsGuid;
 * 
 * String goodsName; String goodsNo; String goodsProperty; String goodsUnit;
 * 
 * double initCount; double initAmount; double initUnitCost; String storeType;
 * boolean isInit; �����Ʒҵ���漰�ֶ�: GUID recid; GUID tenantsGuid; GUID storeGuid;
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
	/************************ ͨ�� ****************************/
	/*****************************************************************/
	/**
	 * ����һ������¼
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
	/******************** ��Ʒ���������� *****************/
	/*****************************************************************/

	/************************* ��ʼ�� *****************************/
	/**
	 * �޸ģ������� �޸Ŀ���ʼ����Ϣ�����û�и�¼������һ��������ʱͬʱ�����ʼ�������Լ���Ʒ���������
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
	 * �ֿ�����ʱ��ָ����Ʒ��ָ�����Ŀ���ʼֵд����
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
				throw new Throwable("���||��λ��Ϣ����Ϊ�գ�");
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
					throw new Throwable(ie.getName() + "[" + ie.getSpec() + "," + ie.getUnit() + "]" + "����������λ����������һ�£�");
				}
			}
			GetInitedInventoryEntityKey key = new GetInitedInventoryEntityKey(task.getStoreId());
			List<InventoryEntity> entityList = context.getList(InventoryEntity.class, key);
			if (CheckIsNull.isEmpty(entityList)) {
				return;
			}
			StringBuffer scriptBuffer = new StringBuffer();// ���SQL�ű�
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
					 * ������Ʒƽ�����ɱ�
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
	 * ���ָ���⻧��ǰ�Ŀ�����ܺ�
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
	 * ��ѯ��ʼ����Ʒ����б�
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

	/************************* �޸�ҵ������ **************************/

	/**
	 * �޸Ŀ���ϡ����ޣ� ������� �޸�ָ����Ʒ��ָ���ֿ�Ŀ���ϡ����ޡ���������ޣ����û�и���Ʒ�ڸòֿ�Ŀ���¼��������һ�����������ϡ���������
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
			StringBuffer scriptBuffer = new StringBuffer();// ���SQL�ű�
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
	 * �޸�ҵ������,����: �������������� ע����������Ϳ������ͬʱ�����仯�ģ������ڷ����仯��ͬʱҪ������Ʒ��ƽ�����ɱ�
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
					throw new Throwable("����||��λ������Ϣ����Ϊ�գ�");
				}
				double count = 0;
				for (InventoryBusTask.DetItem di : task.getDets()) {
					count = DoubleUtil.sum(count, di.getChangeCount());
				}
				if (count != task.getChangeCount()) {
					throw new Throwable("���������λ���������ܺͲ�һ�£�");
				}
			}

			SqlBuildHelper sqlHelper = new SqlBuildHelper();
			// �����û�д��뵥��Ҳû�д���ı�Ľ���Ĭ��Ϊƽ�����ɱ����Ըı�����
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
								throw new Throwable("��Ʒ��" + entity.getName() + "������������������飡");
							else if (InventoryType.Materials.equals(task.getInventoryType()))
								throw new Throwable("���ϣ�" + entity.getName() + "������������������飡");
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
				// ����ƽ�����ɱ�
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
	 * ���������
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
	 * �޸�ҵ������,�ɹ���;����
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
	 * �޸�ҵ������,������������ ע����������Ϳ������ͬʱ�����仯�ģ������ڷ����仯��ͬʱҪ������Ʒ��ƽ�����ɱ�
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
	 * �޸�ҵ������,��������
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
								throw new Throwable("��Ϣ�ѹ��ڣ������ԣ�");
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
						throw new Throwable("��������ѷ����ı䣬�����ԣ�");
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
	 * ��ѯ��Ʒƽ�����ɱ�
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
	/******************** �������������� *****************/
	/*****************************************************************/
	/**
	 * ---------------------------------------------------------------
	 * ��������е���Ʒ��û��ר�ŵı�洢��ֱ�Ӵ���ڿ����С�����Ʒ�������ֿ���ڵģ�
	 * ---------------------------------------------------------------
	 * ��Ʒ�����ƺ���Ʒ����ȷ����Ψһ��һ����Ʒ���ڱ�������Ʒ��ʱ��
	 * ---------------------------------------------------------------
	 * �������ƺ�����Ϊ�䴴��һ��GUID
	 * ---------------------------------------------------------------
	 */
	/**
	 * ֱ���޸Ŀ���ʼ���� �޸���Ʒ��������
	 */
	@Publish
	protected class ModifyOtherGoodsStoreCount extends TaskMethodHandler<UpdateOtherGoodsTask, Method> {

		protected ModifyOtherGoodsStoreCount() {
			super(Method.MODIFY);
		}

		@Override
		protected void handle(Context context, UpdateOtherGoodsTask task) throws Throwable {
			StringBuffer scriptBuffer = new StringBuffer();// ���SQL�ű�
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
						throw new Throwable("��Ʒ��" + task.getOtherGoods().getName() + "������������㣬���飡");
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
	 * ������Ʒ��������
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
			StringBuffer scriptBuffer = new StringBuffer();// ���SQL�ű�
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
	 * ������Ʒ��������
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
			StringBuffer scriptBuffer = new StringBuffer();// ���SQL�ű�
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
	 * �½�һ������Ʒ�Ŀ��
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
	 * ɾ��һ���ֿ����г�ʼ����Ʒ��Ϣ
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
	 * ɾ��ָ����Ʒ�Ŀ��
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
	 * ��ָ���ֿ����Ʒ��ʼ����д����������
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class UpdateOtherGoodsStorageByStore extends SimpleTaskMethodHandler<UpdateOtherStorageTask> {

		@Override
		protected void handle(Context context, UpdateOtherStorageTask task) throws Throwable {
			StringBuffer scriptBuffer = new StringBuffer();// ���SQL�ű�
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

	/************************* ��ѯ������Ʒ��� **************************/

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
	 * �����ݿ���ݲֿ�id+��Ʒ��Ŀid��ѯһ����¼
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
	 * ������Ʒ���Ϳ����
	 * 
	 * @param context
	 * @param tenatsGuid
	 * @param goodsPropertyGuid
	 * @param changeAmount
	 */
	// protected void updateGoodsTypeStoreAmount(Context context, GUID
	// tenatsGuid, GUID goodsPropertyGuid, double changeAmount) {
	// //���·�����
	// GoodsProperty goods = context.find(GoodsProperty.class, new
	// GetPropertyByPropertyGuidKey(goodsPropertyGuid));
	// if(null != goods && null != goods.getGoodsTypeGuid()) {
	// UpdateGoodsTypeAmountTask updateTypeTask = new
	// UpdateGoodsTypeAmountTask(tenatsGuid, goods.getGoodsTypeGuid(),
	// changeAmount);
	// context.handle(updateTypeTask);
	// }
	// }
	/************************* �������� **************************/

	/************************
	 * �����Դ*****************************
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
				throw new Throwable("�ֿ�" + store.getName() + "����λ" + det.getShelfNo() + "����" + det.getTiersNo() + "�㣬����������������飡");
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
	 * �ֿ�����ʱ�����ʼ�������ˮ
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
