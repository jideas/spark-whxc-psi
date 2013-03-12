package com.spark.psi.inventory.service.pub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.ComparatorUtils;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.InventoryDet;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsCategory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.Store;
import com.spark.psi.base.key.GetGoodsInventoryByStoreIdKey;
import com.spark.psi.base.key.GetInventoryByStockIdKey;
import com.spark.psi.base.key.GetPurchaseOrderGoodsCountByGoodsIdKey;
import com.spark.psi.base.task.goods.InventoryLimitTask;
import com.spark.psi.base.utils.MaterialsProperyUtil;
import com.spark.psi.inventory.internal.entity.AllocateInventory;
import com.spark.psi.inventory.internal.entity.AllocateInventoryItem;
import com.spark.psi.inventory.internal.entity.AllocateItemDet;
import com.spark.psi.inventory.internal.entity.CheckInventory;
import com.spark.psi.inventory.internal.entity.CheckInventoryItem;
import com.spark.psi.inventory.internal.entity.Dismounting;
import com.spark.psi.inventory.internal.entity.DismountingItem;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.internal.entity.OtherGoods;
import com.spark.psi.inventory.internal.entity.OthersInventory;
import com.spark.psi.inventory.internal.entity.ReportLoss;
import com.spark.psi.inventory.internal.entity.ReportLossItem;
import com.spark.psi.inventory.internal.entity.ReportLossItemDet;
import com.spark.psi.inventory.internal.key.GetReportLossListKey;
import com.spark.psi.inventory.internal.key.GetSheltItemBySheetIdKey;
import com.spark.psi.inventory.internal.task.ChangeReportLossStatusTask;
import com.spark.psi.inventory.internal.task.CreateAllocateItemDetTask;
import com.spark.psi.inventory.internal.task.CreateReportLossItemDetTask;
import com.spark.psi.inventory.internal.task.DeleteReportLossItemDetTask;
import com.spark.psi.inventory.internal.task.DeleteReportLossItemTask;
import com.spark.psi.inventory.internal.task.DeleteReportLossTask;
import com.spark.psi.inventory.internal.task.ReportLossItemTask;
import com.spark.psi.inventory.internal.task.ReportLossTask;
import com.spark.psi.inventory.intf.entity.instorage.CheckInLog;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.inventory.intf.entity.inventory.InventoryLog;
import com.spark.psi.inventory.intf.entity.inventory.ShelfLifeWarningMaterialsItemImpl;
import com.spark.psi.inventory.intf.entity.outstorage.CheckOutLog;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.inventory.intf.event.InventoryAllocateApprovalEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateDenyEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateSubmitted;
import com.spark.psi.inventory.intf.inventoryenum.RefactorGoodsItemType;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.allocateinventory.AllocateItemKey;
import com.spark.psi.inventory.intf.key.allocateinventory.AllocateKey;
import com.spark.psi.inventory.intf.key.checkinventory.CheckInventoryItemKey;
import com.spark.psi.inventory.intf.key.checkinventory.CheckInventoryKey;
import com.spark.psi.inventory.intf.key.instorage.CheckInLogKey;
import com.spark.psi.inventory.intf.key.instorage.InstorageKey;
import com.spark.psi.inventory.intf.key.instorage.mod.GetRelationCheckInSheetKey;
import com.spark.psi.inventory.intf.key.inventory.AverageInventoryCostKey;
import com.spark.psi.inventory.intf.key.inventory.GetExceptionInventoryKey;
import com.spark.psi.inventory.intf.key.inventory.GetInitedInventoryEntityKey;
import com.spark.psi.inventory.intf.key.inventory.QueryKitInventoryKey;
import com.spark.psi.inventory.intf.key.inventory.StoStreamKey;
import com.spark.psi.inventory.intf.key.outstorage.CheckOutLogKey;
import com.spark.psi.inventory.intf.key.outstorage.OutstorageKey;
import com.spark.psi.inventory.intf.key.outstorage.mod.GetRelationCheckOutSheetKey;
import com.spark.psi.inventory.intf.publish.entity.CheckingInInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.CheckingOutInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.InventoryItemImpl;
import com.spark.psi.inventory.intf.publish.entity.KitInventoryItemImpl;
import com.spark.psi.inventory.intf.publish.entity.ReportLossInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.ReportLossInfoItemImpl;
import com.spark.psi.inventory.intf.task.allocateinventory.AllocateInventoryTask;
import com.spark.psi.inventory.intf.task.allocateinventory.UpdateAllocateStatusTask;
import com.spark.psi.inventory.intf.task.checkinventory.CheckInventoryTask;
import com.spark.psi.inventory.intf.task.dismounting.DismountingTask;
import com.spark.psi.inventory.intf.task.inventory.AddOtherGoodsTask;
import com.spark.psi.inventory.intf.task.inventory.AdjustInventoryAmountTask;
import com.spark.psi.inventory.intf.task.inventory.DeleteOtherGoodsTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryInitTask;
import com.spark.psi.inventory.intf.task.inventory.StoStreamTask;
import com.spark.psi.inventory.service.resource.InventoryDetEntity;
import com.spark.psi.inventory.service.resource.InventoryEntity;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.ReportLossStatus;
import com.spark.psi.publish.ShelfLifeWarningType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.InitInventoryDetItem;
import com.spark.psi.publish.base.store.entity.InitInventoryItem;
import com.spark.psi.publish.base.store.entity.StoreInitKitItem;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.base.store.task.ChangeStoreStatusTask;
import com.spark.psi.publish.inventory.entity.AllocateItemDetInfo;
import com.spark.psi.publish.inventory.entity.CheckingInInfo;
import com.spark.psi.publish.inventory.entity.CheckingInItem;
import com.spark.psi.publish.inventory.entity.CheckingOutInfo;
import com.spark.psi.publish.inventory.entity.CheckingOutItem;
import com.spark.psi.publish.inventory.entity.GoodsOrKitInventorySummary;
import com.spark.psi.publish.inventory.entity.InventoryAllocateItemDet;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetItem;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetInfo;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetItem;
import com.spark.psi.publish.inventory.entity.InventoryInfo;
import com.spark.psi.publish.inventory.entity.InventoryItem;
import com.spark.psi.publish.inventory.entity.InventoryLogItem;
import com.spark.psi.publish.inventory.entity.KitInventoryDetail;
import com.spark.psi.publish.inventory.entity.KitInventoryItem;
import com.spark.psi.publish.inventory.entity.OrderCheckSheetItem;
import com.spark.psi.publish.inventory.entity.ReportLossInfo;
import com.spark.psi.publish.inventory.entity.ReportLossInfoItemDet;
import com.spark.psi.publish.inventory.entity.ShelfLifeWarningMaterialsItem;
import com.spark.psi.publish.inventory.key.GetAllocateItemDetBySheetIdKey;
import com.spark.psi.publish.inventory.key.GetCheckingInListKey;
import com.spark.psi.publish.inventory.key.GetCheckingOutListKey;
import com.spark.psi.publish.inventory.key.GetInventoryAllocateSheetListKey;
import com.spark.psi.publish.inventory.key.GetInventoryCountSheetListKey;
import com.spark.psi.publish.inventory.key.GetInventoryInfoListKey;
import com.spark.psi.publish.inventory.key.GetInventoryItemDetsByItemIdsKey;
import com.spark.psi.publish.inventory.key.GetInventoryItemKey;
import com.spark.psi.publish.inventory.key.GetInventoryLogKey;
import com.spark.psi.publish.inventory.key.GetInventorySummaryKey;
import com.spark.psi.publish.inventory.key.GetKitInventoryDetailListKey;
import com.spark.psi.publish.inventory.key.GetKitInventorySummaryKey;
import com.spark.psi.publish.inventory.key.GetOrderCheckInSheetItemKey;
import com.spark.psi.publish.inventory.key.GetOrderCheckOutSheetItemKey;
import com.spark.psi.publish.inventory.key.GetReportLossInfoListKey;
import com.spark.psi.publish.inventory.key.GetShelfLifeWarningMaterialsKey;
import com.spark.psi.publish.inventory.key.GetWarningGoodsItemListKey;
import com.spark.psi.publish.inventory.key.GetReportLossInfoListKey.ViewStatus;
import com.spark.psi.publish.inventory.task.AdjustGoodsItemCostTask;
import com.spark.psi.publish.inventory.task.ChangReportLossInfoStautsTask;
import com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask;
import com.spark.psi.publish.inventory.task.DeleteReportLossInfoTask;
import com.spark.psi.publish.inventory.task.GoodsRefactorTask;
import com.spark.psi.publish.inventory.task.InventoryAllocateTask;
import com.spark.psi.publish.inventory.task.InventoryCountTask;
import com.spark.psi.publish.inventory.task.ReportLossInfoTask;
import com.spark.psi.publish.inventory.task.SaveInitInventoryTask;
import com.spark.psi.publish.inventory.task.SaveInitKitItemsTask;
import com.spark.psi.publish.inventory.task.StartInventoryCountTask;
import com.spark.psi.publish.inventory.task.UpdateGoodsItemInventoryLimit;
import com.spark.psi.publish.inventory.task.UpdateInventoryAllocateSheetTask;
import com.spark.psi.publish.inventory.task.CreateInventoryAllocateSheetTask.AllocateStockItem;
import com.spark.psi.publish.inventory.task.GoodsRefactorTask.DestinationItem;
import com.spark.psi.publish.inventory.task.GoodsRefactorTask.SourceItem;
import com.spark.psi.publish.inventory.task.InventoryCountTask.TaskGoodsCountItem;
import com.spark.psi.publish.inventory.task.InventoryCountTask.TaskKitCountItem;
import com.spark.psi.publish.inventory.task.UpdateGoodsItemInventoryLimit.Item;

/**
 * 对外service
 * 
 */
public class InventoryPublishService extends Service {

	protected InventoryPublishService() {
		super("InventoryPublishService");
	}

	/**
	 * 
	 * 通过入库单ID查询入库单（CheckingInInfo）对象
	 * 
	 */
	@Publish
	protected class GetCheckingInInfo extends OneKeyResultProvider<CheckingInInfo, GUID> {

		@Override
		protected CheckingInInfo provide(Context context, GUID sheetId) throws Throwable {
			Instorage instorage = context.find(Instorage.class, sheetId);
			List<InstorageItem> itemList = context.getList(InstorageItem.class, sheetId);
			CheckInLogKey lKey = new CheckInLogKey();
			lKey.setCheckInSheetId(sheetId);
			List<CheckInLog> logList = null;
			if (CheckingInType.Kit.getCode().equals(instorage.getSheetType())) {
				logList = new ArrayList<CheckInLog>();
				CheckInLog log = new CheckInLog();
				log.setCheckInDate(instorage.getCreateDate());
				log.setKeeper(instorage.getCreator());
				logList.add(log);
			} else {
				// logList = context.getList(CheckInLog.class, lKey);
			}
			CheckingInInfoImpl impl = InventoryServiceUtil.getCheckingInInfoImpl(instorage, itemList, logList);
			return impl;
		}

	}

	/**
	 * 
	 * 查询入库单列表项目
	 * 
	 */
	@Publish
	protected class GetCheckingInItem extends OneKeyResultListProvider<CheckingInItem,

	GetCheckingInListKey> {

		@Override
		protected void provide(Context context, GetCheckingInListKey key, List<CheckingInItem> resultList)
				throws Throwable {
			// InventoryServiceUtil util = new InventoryServiceUtil(context);
			InstorageKey iKey = new InstorageKey();
			iKey.setCount(key.getCount());
			iKey.setOffset(key.getOffset());
			iKey.setSearchKey(key.getSearchText());
			if (null != key.getQueryTerm()) {
				iKey.setBeginTime(key.getQueryTerm().getStartTime());
				iKey.setEndTime(key.getQueryTerm().getEndTime());
			}
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				iKey.setSortColumnName(key.getSortField().getFieldName());
			}
			if (CheckIsNull.isNotEmpty(key.getSortType())) {
				iKey.setSortType(InventoryServiceUtil.getSortTypeString(key.getSortType()));
			}
			if (CheckIsNull.isNotEmpty(key.getType())) {
				iKey.setInstoType(key.getType().getCode());
			}

			List<Instorage> instorageList = context.getList(Instorage.class, iKey);
			for (Instorage instorage : instorageList) {
				resultList.add(InventoryServiceUtil.getCheckingInItemImpl(instorage));
			}

		}

	}

	/**
	 * 通过出库单ID查询出库单（CheckingOutInfo）对象
	 * 
	 * 
	 */
	@Publish
	protected class GetCheckingOutInfo extends OneKeyResultProvider<CheckingOutInfo, GUID> {

		@Override
		protected CheckingOutInfo provide(Context context, GUID sheetId) throws Throwable {
			Outstorage outstorage = context.find(Outstorage.class, sheetId);
			List<OutstorageItem> itemList = context.getList(OutstorageItem.class, sheetId);
			CheckOutLogKey oKey = new CheckOutLogKey(outstorage.getRelaBillsId()); 
			List<CheckOutLog> logList;

			logList = context.getList(CheckOutLog.class, oKey);

			CheckingOutInfoImpl impl = InventoryServiceUtil.getCheckingOutInfoImpl(context, outstorage,

			itemList, logList);
			return impl;
		}

	}

	/**
	 * 查询出库单列表项目
	 * 
	 * 
	 */
	@Publish
	protected class GetCheckingOutItem extends OneKeyResultListProvider<CheckingOutItem,

	GetCheckingOutListKey> {

		@Override
		protected void provide(Context context, GetCheckingOutListKey key, List<CheckingOutItem> resultList)
				throws Throwable {
			OutstorageKey iKey = new OutstorageKey();
			iKey.setCount(key.getCount());
			iKey.setOffset(key.getOffset());
			iKey.setSearchKey(key.getSearchText());
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				iKey.setSortColumnName(key.getSortField().getFieldName());
				iKey.setSortType(InventoryServiceUtil.getSortTypeString(key.getSortType()));
			}
			if (null != key.getQueryTerm()) {
				iKey.setBeginTime(key.getQueryTerm().getStartTime());
				iKey.setEndTime(key.getQueryTerm().getEndTime());
			}
			iKey.setOutstoType(key.getType());
			List<Outstorage> outstorageList = context.getList(Outstorage.class, iKey);
			for (Outstorage outstorage : outstorageList) {
				resultList.add(InventoryServiceUtil.getCheckingOutItemImpl(outstorage));
			}

		}

	}

	/**
	 * 获得仓库的初始化商品条目列表
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetStoreInitGoodsItemListByStoreIdProvider extends
			OneKeyResultListProvider<InitInventoryItem, GUID> {

		@Override
		protected void provide(Context context, GUID storeId, List<InitInventoryItem> list) throws Throwable {
			GetInitedInventoryEntityKey key = new

			GetInitedInventoryEntityKey(storeId);
			List<InventoryEntity> entityList = context.getList(InventoryEntity.class, key);
			if (CheckIsNull.isEmpty(entityList)) {
				return;
			}

			for (InventoryEntity goodsInventory : entityList) {
				InitInventoryItem item = new InitInventoryItem();
				item.setAmount(goodsInventory.getInitAmount());
				item.setCount(goodsInventory.getInitCount());
				item.setStockId(goodsInventory.getStockId());
				item.setAverageCost(goodsInventory.getInitCost());

				if (InventoryType.Materials.getCode().equals(goodsInventory.getInventoryType())) {
					MaterialsItem m = context.find(MaterialsItem.class, goodsInventory.getStockId());
					if (null != m) {
						item.setShelfLife(m.getShelfLife());
						item.setCode(m.getMaterialCode());
						item.setName(m.getMaterialName());
						item.setSpec(m.getSpec());
						item.setUnit(m.getMaterialUnit());
						item
								.setProperties(MaterialsProperyUtil.subMaterialsPropertyToString(m
										.getMaterialProperties()));
						item.setStockNo(m.getMaterialNo());
						item.setScale(m.getScale());
					}
				} else if (InventoryType.Goods.getCode().equals(goodsInventory.getInventoryType())) {
					GoodsItem g = context.find(GoodsItem.class, goodsInventory.getStockId());
					if (null != g) {
						item.setShelfLife(g.getShelfLife());
						item.setCode(g.getGoodsCode());
						item.setName(g.getGoodsName());
						item.setSpec(g.getSpec());
						item.setUnit(g.getGoodsUnit());
						item.setProperties(MaterialsProperyUtil.subMaterialsPropertyToString(g.getGoodsProperties()));
						item.setStockNo(g.getGoodsNo());
						item.setScale(g.getScale());
					}
				}

				list.add(item);

			}

		}

	}

	/**
	 * 获得仓库的初始化其他物品列表
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetStoreInitKitListByStoreIdProvider extends OneKeyResultListProvider<StoreInitKitItem, GUID> {

		@Override
		protected void provide(Context context, GUID storeId, List<StoreInitKitItem> list) throws Throwable {

			QueryKitInventoryKey key = new QueryKitInventoryKey();
			key.setStoreId(storeId);
			key.setIsInit(true);

			List<OthersInventory> othersInventoryList = context.getList(OthersInventory.class, key);
			if (null == othersInventoryList) {
				return;
			}
			for (OthersInventory othersInventory : othersInventoryList) {
				StoreInitKitItem item = new StoreInitKitItem();
				item.setCount(othersInventory.getInitCount());
				item.setKitDescription(othersInventory.getGoodsProperty());
				item.setKitName(othersInventory.getGoodsName());
				item.setUnit(othersInventory.getGoodsUnit());

				list.add(item);
			}

		}

	}

	/**
	 * 获得仓库的其他物品列表
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetStoreKitListByStoreIdProvider extends OneKeyResultListProvider<KitInventoryItem, GUID> {

		@Override
		protected void provide(Context context, GUID storeId, List<KitInventoryItem> list) throws Throwable {

			QueryKitInventoryKey key = new QueryKitInventoryKey();
			key.setStoreId(storeId);
			key.setIsInit(false);

			List<OthersInventory> othersInventoryList = context.getList(OthersInventory.class, key);
			if (null == othersInventoryList) {
				return;
			}
			for (OthersInventory othersInventory : othersInventoryList) {
				KitInventoryItemImpl item = new KitInventoryItemImpl();
				item.setCount(othersInventory.getGoodsCount());
				item.setKitDescription(othersInventory.getGoodsProperty());
				item.setKitName(othersInventory.getGoodsName());
				item.setUnit(othersInventory.getGoodsUnit());

				list.add(item);
			}

		}

	}

	/**
	 * 查询商品库存列表数据
	 */
	@Publish
	protected class GetGoodsInventoryItemList extends OneKeyResultListProvider<InventoryItem, GetInventoryItemKey> {

		@Override
		protected void provide(final Context context, GetInventoryItemKey key, List<InventoryItem> resultList)
				throws Throwable {
			String searchText = null == key.getSearchText() ? "" : key.getSearchText().trim();
			List<InventoryItemImpl> sList = new ArrayList<InventoryItemImpl>();
			if (CheckIsNull.isEmpty(key.getStoreId())) {
				if (CheckIsNull.isEmpty(key.getGoodsCategoryId())) {

					List<Inventory> list = context.getList(Inventory.class);
					if (CheckIsNull.isEmpty(list)) {
						return;
					}
					List<Inventory> rList = new ArrayList<Inventory>();
					for (Inventory goodsInventory : list) {
						if (!key.getInventoryType().getCode().equals(goodsInventory.getInventoryType())) {
							continue;
						}
						Store store = context.find(Store.class, goodsInventory.getStoreId());
						if (InventoryType.Goods.getCode().equals(goodsInventory.getInventoryType())) {
							GoodsItem goods = context.find(GoodsItem.class, goodsInventory.getStockId());
							if (null == goods || null == store || storeIsEnableOrCounting(context, goodsInventory)) {
								continue;
							}
							if (CheckIsNull.isEmpty(searchText)) {
								rList.add(goodsInventory);
							} else {
								if (goods.getGoodsCode().indexOf(searchText) != -1
										|| goods.getGoodsName().indexOf(searchText) != -1
										|| goods.getPropertiesWithoutUnit().indexOf(searchText) != -1) {
									rList.add(goodsInventory);
								}
							}
						} else if (InventoryType.Materials.getCode().equals(goodsInventory.getInventoryType())) {
							MaterialsItem material = context.find(MaterialsItem.class, goodsInventory.getStockId());
							if (null == material || null == store || storeIsEnableOrCounting(context, goodsInventory)) {
								continue;
							}
							if (CheckIsNull.isEmpty(searchText)) {
								rList.add(goodsInventory);
							} else {
								if (material.getMaterialCode().indexOf(searchText) != -1
										|| material.getMaterialName().indexOf(searchText) != -1
										|| MaterialsProperyUtil.subMaterialsPropertyToString(
												material.getMaterialProperties()).indexOf(searchText) != -1) {
									rList.add(goodsInventory);
								}
							}
						}
					}
					Map<GUID, InventoryEntity> map = new HashMap<GUID, InventoryEntity>();
					for (Inventory goodsInventory : rList) {
						if (map.containsKey(goodsInventory.getStockId())) {
							InventoryEntity old = map.get(goodsInventory.getStockId());
							double oldCount = old.getCount();
							double newCount = goodsInventory.getCount();
							double count = DoubleUtil.sum(oldCount, newCount);
							old.setCount(count);
						} else {
							InventoryEntity newEntity = new InventoryEntity();
							newEntity.setCount(goodsInventory.getCount());
							newEntity.setStockId(goodsInventory.getStockId());
							newEntity.setToDeliverCount(goodsInventory.getToDeliverCount());
							newEntity.setLockedCount(goodsInventory.getLockedCount());
							newEntity.setAmount(goodsInventory.getAmount());
							newEntity.setLowerLimitCount(goodsInventory.getLowerLimitCount());
							newEntity.setOnWayCount(goodsInventory.getOnWayCount());
							newEntity.setStoreId(goodsInventory.getStoreId());
							newEntity.setUpperLimitAmount(goodsInventory.getUpperLimitAmount());
							newEntity.setUpperLimitCount(goodsInventory.getUpperLimitCount());
							newEntity.setInventoryType(goodsInventory.getInventoryType());
							newEntity.setUnit(goodsInventory.getUnit());
							map.put(goodsInventory.getStockId(), newEntity);
						}
					}
					for (Entry<GUID, InventoryEntity> entry : map.entrySet()) {
						InventoryEntity item = (InventoryEntity) entry.getValue();
						InventoryItemImpl impl = new InventoryItemImpl();
						impl.setStockId(item.getStockId());
						impl.setCount(item.getCount());
						impl.setUnit(item.getUnit());
						if (InventoryType.Goods.getCode().equals(item.getInventoryType())) {
							GoodsItem goods = context.find(GoodsItem.class, item.getStockId());
							if (CheckIsNull.isNotEmpty(goods)) {
								impl.setCode(goods.getGoodsCode());
								impl.setName(goods.getGoodsName());
								impl.setProperties(goods.getPropertiesWithoutUnit());
								// impl.setUnit(goods.getGoodsUnit());
								impl.setScale(goods.getScale());
								impl.setNumber(goods.getGoodsNo());
								impl.setShelfLife(goods.getShelfLife());
								impl.setSpec(goods.getSpec());
							}
						} else if (InventoryType.Materials.getCode().equals(item.getInventoryType())) {
							MaterialsItem materials = context.find(MaterialsItem.class, item.getStockId());
							if (CheckIsNull.isNotEmpty(materials)) {
								impl.setCode(materials.getMaterialCode());
								impl.setName(materials.getMaterialName());
								// impl.setProperties(materials.getPropertiesWithoutUnit());
								// impl.setUnit(materials.getMaterialUnit());
								impl.setScale(materials.getScale());
								impl.setNumber(materials.getMaterialNo());
								impl.setShelfLife(materials.getShelfLife());
								impl.setSpec(materials.getSpec());
							}
						}
						if(impl.getCount()>0)

						sList.add(impl);
					}
				} else {
					if (key.getInventoryType().equals(InventoryType.Goods)) {
						GoodsCategory gc = context.find(GoodsCategory.class, key.getGoodsCategoryId());
						if (CheckIsNull.isEmpty(gc)) {
							return;
						}
						GoodsItem[] items = gc.getGoodsItems(context);
						if (CheckIsNull.isEmpty(items)) {
							return;
						}
						for (GoodsItem item : items) {
							GetInventoryByStockIdKey gKey = new GetInventoryByStockIdKey(item.getId(),
									InventoryType.Materials);
							List<Inventory> list = context.getList(Inventory.class, gKey);
							if (CheckIsNull.isEmpty(list)) {
								continue;
							}
							double count = 0;
							for (Inventory goodsInventory : list) {
								count += goodsInventory.getCount();
							}
							InventoryItemImpl impl = new

							InventoryItemImpl();
							impl.setCode(item.getGoodsCode());
							impl.setStockId(item.getId());
							impl.setName(item.getGoodsName());
							impl.setProperties(item.getPropertiesWithoutUnit());
							impl.setUnit(item.getGoodsUnit());
							impl.setCount(count);
							impl.setScale(item.getScale());
							impl.setNumber(item.getGoodsNo());
							impl.setShelfLife(item.getShelfLife());
							impl.setSpec(item.getSpec());
							if(impl.getCount()>0)
							sList.add(impl);
						}
					} else if (key.getInventoryType().equals(InventoryType.Materials)) {
						MaterialsCategory gc = context.find(MaterialsCategory.class, key.getGoodsCategoryId());
						if (CheckIsNull.isEmpty(gc)) {
							return;
						}
						MaterialsItem[] items = gc.getMaterialsItems(context);
						if (CheckIsNull.isEmpty(items)) {
							return;
						}
						for (MaterialsItem item : items) {
							GetInventoryByStockIdKey gKey = new GetInventoryByStockIdKey(item.getId(),
									InventoryType.Materials);
							List<Inventory> list = context.getList(Inventory.class, gKey);
							if (CheckIsNull.isEmpty(list)) {
								continue;
							}
							double count = 0;
							for (Inventory goodsInventory : list) {
								count += goodsInventory.getCount();
							}
							InventoryItemImpl impl = new

							InventoryItemImpl();
							impl.setCode(item.getMaterialCode());
							impl.setStockId(item.getId());
							impl.setName(item.getMaterialName());
							impl.setProperties(MaterialsProperyUtil.getPropertyWithOutUnit(item));
							impl.setUnit(item.getMaterialUnit());
							impl.setCount(count);
							impl.setScale(item.getScale());
							impl.setNumber(item.getMaterialNo());
							impl.setShelfLife(item.getShelfLife());
							impl.setSpec(item.getSpec());
							if(impl.getCount()>0)
							sList.add(impl);
						}
					}

				}
			} else {
				if (CheckIsNull.isEmpty(key.getGoodsCategoryId())) {
					GetGoodsInventoryByStoreIdKey sKey = new GetGoodsInventoryByStoreIdKey(key.getStoreId());
					List<Inventory> list = context.getList(Inventory.class, sKey);
					if (CheckIsNull.isEmpty(list)) {
						return;
					}
					List<Inventory> rList = new ArrayList<Inventory>();
					for (Inventory goodsInventory : list) {
						if (!key.getInventoryType().getCode().equals(goodsInventory.getInventoryType())) {
							continue;
						}
						if (InventoryType.Goods.getCode().equals(goodsInventory.getInventoryType())) {
							GoodsItem goods = context.find(GoodsItem.class, goodsInventory.getStockId());
							if (null == goods) {
								continue;
							}
							if (CheckIsNull.isEmpty(searchText)) {
								rList.add(goodsInventory);
							} else {
								if (goods.getGoodsCode().indexOf(searchText) != -1
										|| goods.getGoodsName().indexOf(searchText) != -1
										|| goods.getPropertiesWithoutUnit().indexOf(searchText) != -1) {
									rList.add(goodsInventory);
								}
							}
						} else if (InventoryType.Materials.getCode().equals(goodsInventory.getInventoryType())) {
							MaterialsItem material = context.find(MaterialsItem.class, goodsInventory.getStockId());
							if (null == material) {
								continue;
							}
							if (CheckIsNull.isEmpty(searchText)) {
								rList.add(goodsInventory);
							} else {
								if (material.getMaterialCode().indexOf(searchText) != -1
										|| material.getMaterialName().indexOf(searchText) != -1
										|| MaterialsProperyUtil.subMaterialsPropertyToString(
												material.getMaterialProperties()).indexOf(searchText) != -1) {
									rList.add(goodsInventory);
								}
							}
						}
					}

					for (Inventory item : rList) {
						InventoryItemImpl impl = new InventoryItemImpl();
						impl.setStockId(item.getStockId());
						impl.setCount(item.getCount());
						impl.setUnit(item.getUnit());
						if (InventoryType.Goods.getCode().equals(item.getInventoryType())) {
							GoodsItem goods = context.find(GoodsItem.class, item.getStockId());
							if (CheckIsNull.isNotEmpty(goods)) {
								impl.setCode(goods.getGoodsCode());
								impl.setName(goods.getGoodsName());
								impl.setProperties(goods.getPropertiesWithoutUnit());
								// impl.setUnit(goods.getGoodsUnit());
								impl.setScale(goods.getScale());
								impl.setNumber(goods.getGoodsNo());
								impl.setShelfLife(goods.getShelfLife());
								impl.setSpec(goods.getSpec());
							}
						} else if (InventoryType.Materials.getCode().equals(item.getInventoryType())) {
							MaterialsItem materials = context.find(MaterialsItem.class, item.getStockId());
							if (CheckIsNull.isNotEmpty(materials)) {
								impl.setCode(materials.getMaterialCode());
								impl.setName(materials.getMaterialName());
								// impl.setProperties(materials.getPropertiesWithoutUnit());
								// impl.setUnit(materials.getMaterialUnit());
								impl.setScale(materials.getScale());
								impl.setNumber(materials.getMaterialNo());
								impl.setShelfLife(materials.getShelfLife());
								impl.setSpec(materials.getSpec());
							}
						}
						if(impl.getCount()>0)
						sList.add(impl);
					}
				} else {
					if (key.getInventoryType().equals(InventoryType.Goods)) {
						GoodsCategory gc = context.find(GoodsCategory.class, key.getGoodsCategoryId());
						if (CheckIsNull.isEmpty(gc)) {
							return;
						}
						GoodsItem[] items = gc.getGoodsItems(context);
						if (CheckIsNull.isEmpty(items)) {
							return;
						}
						List<GoodsItem> list = new ArrayList<GoodsItem>();
						for (GoodsItem item : items) {
							if (CheckIsNull.isEmpty(searchText)) {
								list.add(item);
							} else {
								if (CheckIsNull.isNotEmpty(searchText)
										&& (item.getGoodsCode().indexOf(searchText) != -1
												|| item.getGoodsName().indexOf(searchText) != -1 || item
												.getPropertiesWithoutUnit().indexOf(searchText) != -1)) {
									list.add(item);
								}
							}

						}
						for (GoodsItem item : list) {
							ResourceToken<Inventory> token = context.findResourceToken(Inventory.class, key
									.getStoreId(), item.getId());
							if (CheckIsNull.isEmpty(token)) {
								continue;
							}
							Inventory goodsInventory = token.getFacade();
							InventoryItemImpl impl = new InventoryItemImpl();
							impl.setCode(item.getGoodsCode());
							impl.setStockId(item.getId());
							impl.setName(item.getGoodsName());
							impl.setProperties(item.getPropertiesWithoutUnit());
							impl.setUnit(item.getGoodsUnit());
							// impl.setCount(getAvailableCount(goodsInventory));
							impl.setCount(goodsInventory.getCount());
							impl.setScale(item.getScale());
							impl.setNumber(item.getGoodsNo());
							impl.setShelfLife(item.getShelfLife());
							impl.setSpec(item.getSpec());
							if(impl.getCount()>0)
							sList.add(impl);
						}
					} else if (key.getInventoryType().equals(InventoryType.Materials)) {
						MaterialsCategory gc = context.find(MaterialsCategory.class, key.getGoodsCategoryId());
						if (CheckIsNull.isEmpty(gc)) {
							return;
						}
						MaterialsItem[] items = gc.getMaterialsItems(context);
						if (CheckIsNull.isEmpty(items)) {
							return;
						}
						List<MaterialsItem> list = new ArrayList<MaterialsItem>();
						for (MaterialsItem item : items) {
							if (CheckIsNull.isEmpty(searchText)) {
								list.add(item);
							} else {
								if (CheckIsNull.isNotEmpty(searchText)
										&& (item.getMaterialCode().indexOf(searchText) != -1
												|| item.getMaterialName().indexOf(searchText) != -1 || MaterialsProperyUtil
												.subMaterialsPropertyToString(item.getMaterialProperties()).indexOf(
														searchText) != -1)) {
									list.add(item);
								}
							}

						}
						for (MaterialsItem item : list) {
							ResourceToken<Inventory> token = context.findResourceToken(Inventory.class, key
									.getStoreId(), item.getId());
							if (CheckIsNull.isEmpty(token)) {
								continue;
							}
							Inventory goodsInventory = token.getFacade();
							InventoryItemImpl impl = new InventoryItemImpl();
							impl.setCode(item.getMaterialCode());
							impl.setStockId(item.getId());
							impl.setName(item.getMaterialName());
							impl.setProperties(MaterialsProperyUtil.subMaterialsPropertyToString(item
									.getMaterialProperties()));
							impl.setUnit(item.getMaterialUnit());
							// impl.setCount(getAvailableCount(goodsInventory));
							impl.setCount(goodsInventory.getCount());
							impl.setScale(item.getScale());
							impl.setNumber(item.getMaterialNo());
							impl.setShelfLife(item.getShelfLife());
							impl.setSpec(item.getSpec());
							if(impl.getCount()>0)
							sList.add(impl);
						}
					}

				}
			}
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				ComparatorUtils.sort(sList, key.getSortField().getFieldName(), SortType.Desc.equals(key.getSortType()));
			}
			//int start = key.getOffset() * key.getCount();
			int start = key.getOffset();
			//int end = (key.getOffset() + 1) * key.getCount();
			int end = start + key.getCount();
			if (sList.size() < end) {
				end = sList.size();
			}
			if (start < end) { 
				resultList.addAll(sList.subList(start, end));
			}
		}

	}

	/**
	 * 查询商品库存详细数据列表
	 * 
	 */
	@Publish
	protected class GetGoodsInventoryDetailList extends OneKeyResultListProvider<InventoryInfo,

	GetInventoryInfoListKey> {

		@Override
		protected void provide(Context context, GetInventoryInfoListKey key, List<InventoryInfo> resultList)
				throws Throwable {
			if (CheckIsNull.isEmpty(key.getGoodsItemIds())) {
				return;
			}
			List<GUID> storeIdList = new ArrayList<GUID>();
			if (CheckIsNull.isEmpty(key.getStoreIds())) {
				// ResourceToken<Tenant> token =
				// context.findResourceToken(Tenant.class,
				// context.find(Login.class).getTenantId());
				List<Store> storeList = context.getList(Store.class);
				if (CheckIsNull.isNotEmpty(storeList)) {
					for (Store store : storeList) {
						storeIdList.add(store.getId());
					}
				}
			} else {
				storeIdList = Arrays.asList(key.getStoreIds());
			}
			for (GUID storeId : storeIdList) {
				for (GUID goodsId : key.getGoodsItemIds()) {
					ResourceToken<Inventory> token = context.findResourceToken

					(Inventory.class, storeId, goodsId);
					if (CheckIsNull.isEmpty(token)) {
						continue;
					}
					resultList.add(InventoryServiceUtil
							.getGoodsInventoryDetailImpl(context, token.getFacade(), 0, null));
				}
			}
		}
	}

	/**
	 * 查询预警商品库存详细数据列表
	 * 
	 */
	@Publish
	protected class GetWarningGoodsInventoryDetailList extends OneKeyResultListProvider<InventoryInfo,

	GetWarningGoodsItemListKey> {

		@Override
		protected void provide(Context context, GetWarningGoodsItemListKey key, List<InventoryInfo> resultList)
				throws Throwable {

			// List<GoodsInventory> sList =
			// context.getList(GoodsInventory.class);
			// GetGoodsInventoryByTenantIdKey tk = new
			// GetGoodsInventoryByTenantIdKey(context.find(Login.class).getTenantId());
			List<Inventory> sList = context.getList(Inventory.class);
			for (Inventory goodsInventory : sList) {
				double adviseCount = getAdviseCount(context, goodsInventory);
				if (adviseCount > 0) {
					MaterialsItem goods = context.find(MaterialsItem.class, goodsInventory.getStockId());
					if (null == key.getJointVenture()) {
						resultList.add(InventoryServiceUtil.getGoodsInventoryDetailImpl(context, goodsInventory,
								adviseCount, goods));
					} else if (goods.getMaterial().isJointVenture() && !key.getJointVenture()) {
						continue;
					} else if (!goods.getMaterial().isJointVenture() && key.getJointVenture()) {
						continue;
					} else {
						resultList.add(InventoryServiceUtil.getGoodsInventoryDetailImpl(context, goodsInventory,
								adviseCount, goods));
					}
				}
			}
		}
	}
	
	/**
	 * 查询保质期预警材料列表
	 */
	@Publish
	protected class GetShelfLifeWarningMatrialsItems extends OneKeyResultListProvider<ShelfLifeWarningMaterialsItem, GetShelfLifeWarningMaterialsKey>
	{

		@Override
		protected void provide(Context context,
				GetShelfLifeWarningMaterialsKey key,
				List<ShelfLifeWarningMaterialsItem> resultList)
				throws Throwable {
			List<InventoryDet> dets = context.getList(InventoryDet.class);
			for(InventoryDet d:dets)
			{
				Inventory inventory = context.find(Inventory.class,d.getInventoryId());
				if(!(d.getCount()>0)||InventoryType.Goods.getCode().equals(inventory.getInventoryType()))
				{
					continue;
				}
				MaterialsItem mi = context.find(MaterialsItem.class, d.getStockId());
				int shelfLife = mi.getShelfLife();
				int warningDay = mi.getWarningDay();
				Date produceDay = new Date(DateUtil.truncDay(d.getProduceDate()));
				Date today = new Date(DateUtil.getToday());
				long days = DateUtil.getDaysBetween(produceDay, today);
				ShelfLifeWarningMaterialsItemImpl wmi = new ShelfLifeWarningMaterialsItemImpl();
				wmi.setId(d.getId());
				wmi.setCount(d.getCount());
				wmi.setMaterialId(d.getStockId());
				wmi.setMaterialName(mi.getMaterialName());
				wmi.setMaterialCode(mi.getMaterialCode());
				wmi.setMaterialNo(mi.getMaterialNo());
				wmi.setMaterialSpec(mi.getSpec());
				wmi.setMaterialUnit(mi.getMaterialUnit());
				wmi.setProduceDate(d.getProduceDate());
				wmi.setShelfLife(mi.getShelfLife());
				wmi.setStoreId(d.getStoreId());
				wmi.setShelfNo(d.getShelfNo());
				wmi.setTiersNo(d.getTiersNo());
				Store store = context.find(Store.class, d.getStoreId());
				wmi.setStoreName(store.getName());
				wmi.setWarningDay(mi.getWarningDay());
				if(days>shelfLife)
				{
					wmi.setShelfLifeWarningType(ShelfLifeWarningType.Overdue);
					resultList.add(wmi);
				}
				else
				{
					if((days+warningDay)>=shelfLife)
					{
						wmi.setShelfLifeWarningType(ShelfLifeWarningType.Closeto);
						resultList.add(wmi);
					}
				}
				
			}
		}
		
	}
	
	/**
	 * 保质期预警材料分页查询
	 */
	@Publish
	protected class GetShelfLifeWarningMaterialsListEntity extends OneKeyResultProvider<ListEntity<ShelfLifeWarningMaterialsItem>, GetShelfLifeWarningMaterialsKey>
	{

		@Override
		protected ListEntity<ShelfLifeWarningMaterialsItem> provide(
				Context context, GetShelfLifeWarningMaterialsKey key)
				throws Throwable {
			List<ShelfLifeWarningMaterialsItem> list = context.getList(ShelfLifeWarningMaterialsItem.class, key);
			List<ShelfLifeWarningMaterialsItem> rList = new ArrayList<ShelfLifeWarningMaterialsItem>();
			List<ShelfLifeWarningMaterialsItem> l = new ArrayList<ShelfLifeWarningMaterialsItem>();
			for(ShelfLifeWarningMaterialsItem item:list)
			{
				if(null!=key.getStoreId()&&!item.getStoreId().equals(key.getStoreId()))
				{
					continue;
				}
				if(null!=key.getShelfLifeWarningType()&&!(item.getShelfLifeWarningType()==key.getShelfLifeWarningType()))
				{
					continue;
				}
				rList.add(item);
			}
			if(key.getCount()>0)
			{
				int toIndex = key.getOffset()+key.getCount();
				if(toIndex>rList.size())
					toIndex = rList.size();
				if(key.getOffset()<=rList.size())
				l = rList.subList(key.getOffset(), toIndex);
			}
			ListEntity<ShelfLifeWarningMaterialsItem> entity = new ListEntity<ShelfLifeWarningMaterialsItem>(l, rList.size());
			return entity;
		}
		
	}
	/**
	 * 查询库存异常商品详细数据
	 * 
	 * 
	 */
	@Publish
	protected class GetExceptionGoodsItem extends OneKeyResultListProvider<InventoryInfo,

	GetExceptionInventoryKey> {

		@Override
		protected void provide(Context context, GetExceptionInventoryKey key, List<InventoryInfo> resultList)
				throws Throwable {
			Map<GUID, Double> inventoryMap = new HashMap<GUID, Double>();
			List<Inventory> exceptionList = new ArrayList<Inventory>();
			List<Inventory> inventoryList = context.getList(Inventory.class);
			/**
			 * 分库存异常
			 */
			for (Inventory inventory : inventoryList) {
				if (inventoryMap.containsKey(inventory.getStockId())) {
					double sum = inventoryMap.get(inventory.getStockId()) + inventory.getAmount();
					inventoryMap.remove(inventory.getStockId());
					inventoryMap.put(inventory.getStockId(), sum);
				} else {
					inventoryMap.put(inventory.getStockId(), inventory.getAmount());
				}
				if (isExceptionInventory(context, inventory)) {

					exceptionList.add(inventory);
				}
			}
			/**
			 * 总库存金额超上限
			 */
			// for (Iterator iterator = goodsMap.entrySet().iterator(); iterator
			// .hasNext();) {
			// Entry entry = (Entry) iterator.next();
			// GUID goodsItemId = (GUID) entry.getKey();
			// Double sumAmount = (Double) entry.getValue();
			// GoodsItem goodsItem = context
			// .find(GoodsItem.class, goodsItemId);
			// if (null != goodsItem
			// && sumAmount >
			//
			// goodsItem.get()
			// && InventoryWarningType.ALL_Amount == goodsItem
			// .getGoodsWarnningType()) {
			// List<Inventory> list = context.getList(
			// Inventory.class, new
			//
			// GetInventoryByStockIdKey(goodsItem
			//
			// .getId()));
			// if (null == list) {
			// continue;
			// }
			// for (Inventory goodsInventory : list) {
			//
			// if (-1 == exceptionList.indexOf
			//
			// (goodsInventory)) {
			// exceptionList.add(goodsInventory);
			// }
			// }
			// }
			// }

			for (Inventory goodsInventory : exceptionList) {
				resultList.add(InventoryServiceUtil.getGoodsInventoryDetailImpl(context, goodsInventory, 0, null));
			}
		}

		/**
		 * 是否分库存异常
		 * 
		 * @param context
		 * 
		 * @param inventory
		 * @return boolean
		 */
		private boolean isExceptionInventory(Context context, Inventory inventory) {

			if (InventoryType.Goods.getCode().equals(inventory.getInventoryType())) {
				// GoodsItem goods = context.find(GoodsItem.class,
				// inventory.getStockId());
				// return (inventory.getCount() < inventory.getLowerLimitCount()
				// && InventoryWarningType.Store_Count == goods
				// .get())
				// || (inventory.getCount() > inventory.getUpperLimitCount() &&
				// InventoryWarningType.Store_Count == goods
				// .getGoodsWarnningType())
				// || (inventory.getAmount() > inventory.getUpperLimitAmount()
				// && InventoryWarningType.Store_Amount == goods
				// .getGoodsWarnningType());
				return false;
			} else if (InventoryType.Materials.equals(inventory.getInventoryType())) {
				MaterialsItem item = context.find(MaterialsItem.class, inventory.getStockId());
				return (inventory.getCount() < inventory.getLowerLimitCount() && InventoryWarningType.Store_Count == item
						.getWarningType())
						|| (inventory.getCount() > inventory.getUpperLimitCount() && InventoryWarningType.Store_Count == item
								.getWarningType())
						|| (inventory.getAmount() > inventory.getUpperLimitAmount() && InventoryWarningType.Store_Amount == item
								.getWarningType());
			} else {
				return false;
			}
		}
	}

	/**
	 * 查询商品在各个仓库的分布情况
	 * 
	 */
	@Publish
	protected class GetGoodsInventorySummary extends OneKeyResultProvider<GoodsOrKitInventorySummary,

	GetInventorySummaryKey> {

		@Override
		protected GoodsOrKitInventorySummary provide(Context context, GetInventorySummaryKey key) throws Throwable {
			List<Inventory> goodsInventoryList = context.getList(Inventory.class, new GetInventoryByStockIdKey(key
					.getGoodsItemId(), InventoryType.Materials));
			ComparatorUtils.sort(goodsInventoryList, "storeId", true);
			GoodsOrKitInventorySummary summary = InventoryServiceUtil.getGoodsInventorySummary(context,
					goodsInventoryList);
			return summary;
		}
	}

	/**
	 * 查询其他物品在各个仓库的分布情况
	 * 
	 * 
	 */
	@Publish
	protected class GetKitInventorySummary extends OneKeyResultProvider<GoodsOrKitInventorySummary,

	GetKitInventorySummaryKey> {

		@Override
		protected GoodsOrKitInventorySummary provide(Context context, GetKitInventorySummaryKey key) throws Throwable {
			QueryKitInventoryKey ogKey = new QueryKitInventoryKey(key.getKitName(), key.getUnit(), key.getKitDesc());
			ogKey.setStoreId(key.getStoreId());
			ogKey.setQuerySummary(true);
			ogKey.setIsInit(false);
			List<OthersInventory> list = context.getList(OthersInventory.class, ogKey);
			ComparatorUtils.sort(list, "storeGuid", true);
			GoodsOrKitInventorySummary summary = InventoryServiceUtil.getKitInventorySummary(context, list);
			return summary;

		}

	}

	/**
	 * 库存调拨单详细信息
	 * 
	 */
	@Publish
	protected class GetInventoryAllocateSheetInfo extends OneKeyResultProvider<InventoryAllocateSheetInfo, GUID> {

		@Override
		protected InventoryAllocateSheetInfo provide(Context context, GUID sheetId) throws Throwable {
			AllocateInventory allocateInventory = context.find(AllocateInventory.class, sheetId);
			AllocateItemKey iKey = new AllocateItemKey();
			iKey.setAllocateOrdGuid(sheetId);
			List<AllocateInventoryItem> ItemList = context.getList(AllocateInventoryItem.class, iKey);
			InventoryAllocateSheetInfo info = InventoryServiceUtil.getInventoryAllocateSheetInfo(context,
					allocateInventory, ItemList);
			return info;
		}

	}

	/**
	 * 
	 * 库存调拨单列表项目
	 */
	@Publish
	protected class GetInventoryAllocateSheet extends OneKeyResultListProvider<InventoryAllocateSheetItem,

	GetInventoryAllocateSheetListKey> {

		@Override
		protected void provide(Context context, GetInventoryAllocateSheetListKey key,
				List<InventoryAllocateSheetItem> resultList) throws

		Throwable {
			AllocateKey aKey = new AllocateKey();
			aKey.setCount(key.getCount());
			aKey.setOffset(key.getOffset());
			aKey.setSearchString(key.getSearchText());
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				aKey.setSortColumnName(key.getSortField().getFieldName());
				aKey.setSortType(InventoryServiceUtil.getSortTypeString(key.getSortType()));
			}

			if (null != key.getQueryTerm()) {
				aKey.setBeginTime(key.getQueryTerm().getStartTime());
				aKey.setEndTime(key.getQueryTerm().getEndTime());
			}
			aKey.setdealState(key.getStatus());
			List<AllocateInventory> list = context.getList(AllocateInventory.class, aKey);
			for (AllocateInventory allocateInventory : list) {
				resultList.add(InventoryServiceUtil.getAllocateInventory(allocateInventory));
			}

		}

	}

	/**
	 * 查询盘点单详细信息<br>
	 * 
	 */
	@Publish
	protected class GetInventoryCountSheetInfo extends OneKeyResultProvider<InventoryCountSheetInfo, GUID> {

		@Override
		protected InventoryCountSheetInfo provide(Context context, GUID sheetId) throws Throwable {
			CheckInventory checkInventory = context.find(CheckInventory.class, sheetId);
			if (null == checkInventory) {
				return null;
			}
			CheckInventoryItemKey iKey = new CheckInventoryItemKey();
			iKey.setCheckOrdGuid(sheetId);
			List<CheckInventoryItem> list = context.getList(CheckInventoryItem.class, iKey);
			InventoryCountSheetInfo info = InventoryServiceUtil.getInventoryCountSheetInfo(context, checkInventory,
					list);
			return info;
		}

	}

	/**
	 * 查询库存盘点单条目<br>
	 * 
	 */
	@Publish
	protected class GetInventoryCountSheetItem extends OneKeyResultListProvider<InventoryCountSheetItem,

	GetInventoryCountSheetListKey> {

		@Override
		protected void provide(Context context, GetInventoryCountSheetListKey key,
				List<InventoryCountSheetItem> resultList) throws Throwable {
			CheckInventoryKey iKey = new CheckInventoryKey();
			iKey.setCount(key.getCount());
			iKey.setOffset(key.getOffset());
			iKey.setSearceText(key.getSearchText());
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				iKey.setSortField(key.getSortField().getFieldName());
				iKey.setSortType(InventoryServiceUtil.getSortTypeString(key.getSortType()));
			}

			List<CheckInventory> list = context.getList(CheckInventory.class, iKey);
			for (CheckInventory checkInventory : list) {
				resultList.add(InventoryServiceUtil.getInventoryCountSheetItem

				(checkInventory));
			}

		}

	}

	/**
	 * 查询库存流水项<br>
	 * 
	 */
	@Publish
	protected class GetInventoryLogItem extends OneKeyResultListProvider<InventoryLogItem, GetInventoryLogKey> {

		@Override
		protected void provide(Context context, GetInventoryLogKey key, List<InventoryLogItem> resultList)
				throws Throwable {

			StoStreamKey sKey = new StoStreamKey();
			sKey.setCount(key.getCount());
			sKey.setOffset(key.getOffset());
			sKey.setGoodsCategoryId(key.getGoodsCategoryId());
			sKey.setSearchText(key.getSearchText());
			sKey.setStoreId(key.getStoreId());
			sKey.setInventoryType(key.getInventoryType().getCode());
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				sKey.setSortField(key.getSortField().getFieldName());
				sKey.setSortType(InventoryServiceUtil.getSortTypeString(key.getSortType()));
			}
			sKey.setDateBegin(DateUtil.getDayStartTime(key.getDateBegin()));
			sKey.setDateEnd(DateUtil.getDayEndTime(key.getDateEnd()));
			List<InventoryLogEntity> list = context.getList(InventoryLogEntity.class, sKey);
			if (null == list) {
				return;
			}
			for (InventoryLogEntity stoStream : list) {
				resultList.add(InventoryServiceUtil.getInventoryLogItem(stoStream));
			}

		}

	}

	/**
	 * 查询1条库存流水项<br>
	 * 
	 */
	@Publish
	protected class GetInventoryLog extends OneKeyResultProvider<InventoryLog, GUID> {

		@Override
		protected InventoryLog provide(Context context, GUID id) throws Throwable {
			InventoryLogEntity stream = context.find(InventoryLogEntity.class, id);
			if (null == stream) {
				return null;
			}
			InventoryLog log = new InventoryLog();
			log.setRECID(stream.getId());
			log.setCreatedDate(stream.getCreatedDate());
			log.setCreatePerson(stream.getCreatePerson());
			log.setCurrDate(stream.getCreatedDate());
			log.setGoodsGuid(stream.getStockId());
			log.setInstoAmount(stream.getInstoAmount());
			log.setInstoCount(stream.getInstoCount());
			log.setOrderGuid(stream.getOrderId());
			log.setStoreGuid(stream.getStoreId());
			log.setStreamType(stream.getLogType());
			log.setOutstoAmount(stream.getOutstoAmount());
			log.setOutstoCount(stream.getOutstoCount());
			log.setGoodsAttr(stream.getProperties());
			log.setGoodsNo(stream.getStockNo());
			log.setGoodsName(stream.getName());
			log.setGoodsScale(stream.getScale());
			log.setGoodsUnit(stream.getUnit());
			log.setGoodsTypeGuid(stream.getCategoryId());
			log.setInventoryType(InventoryType.getEnum(stream.getInventoryType()));
			return log;
		}

	}

	/**
	 * 查询物品库存list<br>
	 * 
	 */
	@Publish
	protected class GetKitInventoryDetail extends OneKeyResultListProvider<KitInventoryDetail,

	GetKitInventoryDetailListKey> {

		@Override
		protected void provide(Context context, GetKitInventoryDetailListKey key, List<KitInventoryDetail> resultList)
				throws Throwable {
			QueryKitInventoryKey oKey = new QueryKitInventoryKey();
			oKey.setIsInit(false);
			oKey.setCount(key.getCount());
			oKey.setOffset(key.getOffset());
			if (null != key.getSortField()) {
				oKey.setSortColumn(key.getSortField().getFieldName());
				oKey.setSortType(InventoryServiceUtil.getSortTypeString(key.getSortType()));
			}
			oKey.setSearchText(key.getSearchText());
			oKey.setStoreId(key.getStoreId());
			List<OthersInventory> list = context.getList(OthersInventory.class, oKey);
			for (OthersInventory othersInventory : list) {
				if(othersInventory.getGoodsCount()<=0)
				{
					continue;
				}
				resultList.add(InventoryServiceUtil.getKitInventoryDetail(othersInventory));
			}

		}

	}

	/**
	 * 查询订单相关出库单带明细列表
	 */
	@Publish
	protected class GetOrderCheckOutSheetItems extends
			OneKeyResultListProvider<OrderCheckSheetItem, GetOrderCheckOutSheetItemKey> {

		@Override
		protected void provide(Context context, GetOrderCheckOutSheetItemKey key, List<OrderCheckSheetItem> resultList)
				throws Throwable {
			if (null == key.getOrderId()) {
				return;
			}
			GetRelationCheckOutSheetKey ik = new GetRelationCheckOutSheetKey();
			ik.setRelationOrderId(key.getOrderId());
			List<Outstorage> list = context.getList(Outstorage.class, ik);
			if (CheckIsNull.isNotEmpty(list)) {
				for (Outstorage outstorage : list) {
					resultList.add(InventoryServiceUtil.getOrderCheckOutSheetItem(context, outstorage));
				}
			}

		}

	}

	/**
	 * 查询订单相关入库单带明细列表
	 */
	@Publish
	protected class GetOrderCheckInSheetItems extends
			OneKeyResultListProvider<OrderCheckSheetItem, GetOrderCheckInSheetItemKey> {

		@Override
		protected void provide(Context context, GetOrderCheckInSheetItemKey key, List<OrderCheckSheetItem> resultList)
				throws Throwable {
			if (null == key.getOrderId()) {
				return;
			}
			GetRelationCheckInSheetKey ik = new GetRelationCheckInSheetKey();
			ik.setRelationOrdId(key.getOrderId());
			List<Instorage> list = context.getList(Instorage.class, ik);
			if (CheckIsNull.isNotEmpty(list)) {
				for (Instorage instorage : list) {
					resultList.add(InventoryServiceUtil.getOrderCheckInSheetItem(context, instorage));
				}
			}
		}

	}

	/**
	 * 处理调整商品库存成本
	 * 
	 */
	@Publish
	protected class AdjustGoodsItemCostHandle extends SimpleTaskMethodHandler<AdjustGoodsItemCostTask> {

		@Override
		protected void handle(Context context, AdjustGoodsItemCostTask task) throws Throwable {

			InventoryBusTask bTask = new InventoryBusTask(task.getStoreId(), task.getGoodsItemId());
			bTask.setNewCost(task.getNewCost());
			bTask.setUpdateAvgPrice(true);
			InventoryServiceUtil.insertInventoryLog(context, task);
			context.handle(bTask);
			// 更新所有改商品的库存金额
			Inventory inventory = context.find(Inventory.class, task.getStoreId(), task.getGoodsItemId());
			AverageInventoryCostKey key = new AverageInventoryCostKey(task.getGoodsItemId(),InventoryType.getEnum(inventory.getInventoryType()));
			Double avgInventoryCost = context.find(Double.class, key);
			GetInventoryByStockIdKey gk = new GetInventoryByStockIdKey(task.getGoodsItemId(), InventoryType.Materials);
			List<Inventory> list = context.getList(Inventory.class, gk);
			if (null == avgInventoryCost && null == list) {
				return;
			}
			GUID[] storeIds = new GUID[list.size()];
			for (int i = 0; i < list.size(); i++) {
				storeIds[i] = list.get(i).getStoreId();
			}
			AdjustInventoryAmountTask aTask = new AdjustInventoryAmountTask(task.getGoodsItemId(), storeIds,
					avgInventoryCost);
			context.handle(aTask);
		}

	}

	/**
	 * 处理创建调拨单
	 * 
	 * 
	 */
	@Publish
	protected class CreateAllocateSheetHandle extends SimpleTaskMethodHandler<CreateInventoryAllocateSheetTask> {

		@Override
		protected void handle(Context context, CreateInventoryAllocateSheetTask task) throws Throwable {
			AllocateInventory allocateInventory = new AllocateInventory();
			GUID allocateInventoryId = context.newRECID();
			allocateInventory.setId(allocateInventoryId);
			allocateInventory.setAllocateReason(task.getCause());
			allocateInventory.setAllocateOutStoreId(task.getSourceStoreId());
			allocateInventory.setAllocateOutStoreName(context.find(Store.class, task.getSourceStoreId()).getName());
			allocateInventory.setAllocateInStoreId(task.getDestinationStoreId());
			allocateInventory.setAllocateInStoreName(context.find(Store.class, task.getDestinationStoreId()).getName());
			allocateInventory.setStatus(InventoryAllocateStatus.Submitting.getCode());
			allocateInventory.setCreateDate(new Date().getTime());
			Employee employee = context.find(Employee.class);
			allocateInventory.setCreatorId(employee.getId());
			allocateInventory.setCreator(employee.getName());
			allocateInventory.setDeptId(employee.getDepartmentId());
			if (CheckIsNull.isEmpty(task.getItems())) {
				return;
			}
			List<AllocateStockItem> list = Arrays.asList(task.getItems());
			List<AllocateInventoryItem> itemList = new ArrayList<AllocateInventoryItem>();
			for (AllocateStockItem allocateGoodsItem : list) {
				AllocateInventoryItem item = new AllocateInventoryItem();
				item.setId(context.newRECID());
				item.setAllocateId(allocateInventoryId);
				item.setAllocateCount(allocateGoodsItem.getAllocateCount());
				item.setStockId(allocateGoodsItem.getGoodsItemId());
				MaterialsItem materialItem = context.find(MaterialsItem.class, allocateGoodsItem.getGoodsItemId());
				if (null != materialItem) {
					item.setStockCode(materialItem.getMaterialCode());
					item.setStockNo(materialItem.getMaterialNo());
					item.setStockName(materialItem.getMaterialName());
					item.setStockSpec(materialItem.getSpec());
					item.setUnit(materialItem.getMaterialUnit());
					item.setStockScale(materialItem.getScale());
				}
				item.setCreateDate(new Date().getTime());
				item.setCreatorId(employee.getId());
				itemList.add(item);
			}
			AllocateInventoryTask iTask = new AllocateInventoryTask();
			iTask.setAllocateEntity(allocateInventory);
			iTask.setList(itemList);
			context.handle(iTask, Method.INSERT);
			task.setSheetId(allocateInventoryId);
		}

	}
	/**
	 * 根据调拔明细ID查询对应的货位信息
	 * @author Administrator
	 *
	 */
	@Publish
	protected class GetAllocateItemDetByItemIds extends OneKeyResultListProvider<AllocateItemDetInfo, GetInventoryItemDetsByItemIdsKey> {

		@Override
		protected void provide(Context context,
				GetInventoryItemDetsByItemIdsKey key,
				List<AllocateItemDetInfo> resultList) throws Throwable {
			if (key.getItemIds() == null || key.getItemIds().length < 1) return;
			AllocateItemDetInfo itemDetInfo = null;
			for (GUID id : key.getItemIds()) {
				itemDetInfo = new AllocateItemDetInfo(); 
				List<AllocateItemDet> detList = context.getList(AllocateItemDet.class, id);
				itemDetInfo.setId(id);
				InventoryAllocateItemDet[] fItemDets = new InventoryAllocateItemDet[detList.size()];
				int index = 0;
				for (AllocateItemDet itemDet : detList) {
					fItemDets[index] = new InventoryAllocateItemDet();
					BeanUtils.copyObject(itemDet, fItemDets[index]);
					index++;
				}
				itemDetInfo.setItems(fItemDets);
				resultList.add(itemDetInfo);
			}
		}
		
	}
	/**
	 * 取得指定调拔单所有的货位信息（只有调出库的货位信息）
	 * @author Administrator
	 *
	 */
	@Publish
	protected class GetAllocateItemDetBySheetId extends OneKeyResultListProvider<InventoryAllocateItemDet, GetAllocateItemDetBySheetIdKey> {

		@Override
		protected void provide(Context context,
				GetAllocateItemDetBySheetIdKey key,
				List<InventoryAllocateItemDet> resultList) throws Throwable {
			List<AllocateItemDet> detList = context.getList(AllocateItemDet.class, new GetSheltItemBySheetIdKey(key.getSheetId()));
			InventoryAllocateItemDet iItemDet = null;
			for (AllocateItemDet det : detList) {
				iItemDet = new InventoryAllocateItemDet();
				BeanUtils.copyObject(det, iItemDet);
				resultList.add(iItemDet);
			}
		}
		
	}
	/**
	 * 编辑调拨单
	 * 
	 * 
	 */
	@Publish
	protected class UpdateAllocateSheetHandle extends SimpleTaskMethodHandler<UpdateInventoryAllocateSheetTask> {

		@Override
		protected void handle(Context context, UpdateInventoryAllocateSheetTask task) throws Throwable {
			AllocateInventory allocateInventory = new AllocateInventory();
			GUID allocateInventoryId = task.getSheetId();
			allocateInventory.setId(allocateInventoryId);
			allocateInventory.setAllocateReason(task.getCause());
			allocateInventory.setAllocateOutStoreId(task.getSourceStoreId());
			allocateInventory.setAllocateOutStoreName(context.find(Store.class, task.getSourceStoreId()).getName());
			allocateInventory.setAllocateInStoreId(task.getDestinationStoreId());
			allocateInventory.setAllocateInStoreName(context.find(Store.class, task.getDestinationStoreId()).getName());
			allocateInventory.setAllocateReason(task.getCause());
			Employee employee = context.find(Employee.class);
			if (CheckIsNull.isEmpty(task.getItems())) {
				return;
			}
			List<AllocateStockItem> list = Arrays.asList(task.getItems());
			List<AllocateInventoryItem> itemList = new ArrayList<AllocateInventoryItem>();
			for (AllocateStockItem allocateGoodsItem : list) {
				AllocateInventoryItem item = new AllocateInventoryItem();
				item.setId(context.newRECID());
				item.setAllocateId(allocateInventoryId);
				item.setAllocateCount(allocateGoodsItem.getAllocateCount());
				item.setStockId(allocateGoodsItem.getGoodsItemId());
				MaterialsItem materialItem = context.find(MaterialsItem.class, allocateGoodsItem.getGoodsItemId());
				if (null != materialItem) {
					item.setStockCode(materialItem.getMaterialCode());
					item.setStockNo(materialItem.getMaterialNo());
					item.setStockName(materialItem.getMaterialName());
					item.setStockSpec(materialItem.getSpec());
					item.setUnit(materialItem.getMaterialUnit());
					item.setStockScale(materialItem.getScale());
				}
				item.setCreateDate(new Date().getTime());
				item.setCreatorId(employee.getId());
				itemList.add(item);
			}
			AllocateInventoryTask iTask = new AllocateInventoryTask();
			iTask.setAllocateEntity(allocateInventory);
			iTask.setList(itemList);

			context.handle(iTask, Method.MODIFY);

			if (!(iTask.getCount() > 0)) {
				task.setSuccess(false);
				UpdateInventoryAllocateSheetTask.Error[] errors = new UpdateInventoryAllocateSheetTask.Error[] { UpdateInventoryAllocateSheetTask.Error.ConcurrentError };
				task.setErrors(errors);
			} else {
				task.setSuccess(true);
			}
		}
	}

	/**
	 * 处理商品拆装
	 * 
	 * 
	 */
	@Publish
	protected class GoodsRefactorHandle extends SimpleTaskMethodHandler<GoodsRefactorTask> {

		@Override
		protected void handle(Context context, GoodsRefactorTask task) throws Throwable {
			List<GoodsRefactorTask.Error> errorList = new ArrayList<GoodsRefactorTask.Error>();
			boolean isSuccess = true;
			Store store = context.find(Store.class, task.getStoreId());
			if (null == store || !StoreStatus.ENABLE.equals(store.getStatus())) {
				isSuccess = false;
				errorList.add(GoodsRefactorTask.Error.StoreError);
			}
			task.setSuccess(isSuccess);
			if (!isSuccess) {
				if (CheckIsNull.isNotEmpty(errorList)) {
					GoodsRefactorTask.Error[] errors = new GoodsRefactorTask.Error[errorList.size()];
					for (int i = 0; i < errorList.size(); i++) {
						errors[i] = errorList.get(i);
					}
					task.setErrors(errors);
				}
				return;
			}
			DismountingTask dTask = new DismountingTask();
			Dismounting dismounting = new Dismounting();
			GUID dismountingId = context.newRECID();
			dismounting.setRECID(dismountingId);
			dismounting.setTenantsGuid(context.find(Login.class).getTenantId());
			dismounting.setCreateDate(new Date().getTime());
			dismounting.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId())
					.getName());
			dismounting.setDeptGuid(context.find(Employee.class, context.find(Login.class).getEmployeeId())
					.getDepartmentId());
			dismounting.setMarkPerson(context.find(Login.class).getEmployeeId());
			dismounting.setDismDate(new Date().getTime());
			dismounting.setStoreGuid(task.getStoreId());
			if (null != store) {
				dismounting.setStoreName(store.getName());
				dismounting.setStorePY(PinyinHelper.getLetter(store.getName()));
			}
			List<DismountingItem> itemList = new ArrayList<DismountingItem>();
			itemList.addAll(getSourceItemList(context, dismountingId, task));
			itemList.addAll(getDestItemList(context, dismountingId, task));
			dTask.setDismounting(dismounting);
			dTask.setList(itemList);
			context.handle(dTask, Method.INSERT);
		}

		private Collection<? extends DismountingItem> getDestItemList(Context context, GUID dismountingId,
				GoodsRefactorTask task) {
			if (CheckIsNull.isEmpty(task.getDestinationItems())) {
				return null;
			}
			List<DestinationItem> list = Arrays.asList(task.getDestinationItems());
			List<DismountingItem> itemList = new ArrayList<DismountingItem>();
			for (DestinationItem destItem : list) {
				DismountingItem item = new DismountingItem();
				item.setRECID(context.newRECID());
				item.setTenantsGuid(context.find(Login.class).getTenantId());
				item.setCreateDate(new Date().getTime());
				item.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId()).getName());
				item.setDismFlag(RefactorGoodsItemType.Destination.getCode());
				item.setDismCount(destItem.getCount());
				item.setDismGuid(dismountingId);
				item.setGoodsGuid(destItem.getGoodsItemId());
				GoodsItem goodsItem = context.find(GoodsItem.class, destItem.getGoodsItemId());
				if (null != goodsItem) {
					item.setGoodsName(goodsItem.getGoodsName());
					item.setGoodsNo(goodsItem.getGoodsCode());
					item.setGoodsAttr(goodsItem.getPropertiesWithoutUnit());
					item.setUnit(goodsItem.getGoodsUnit());
					item.setGoodsScale(goodsItem.getScale());
					item.setStoreCost(DoubleUtil.div(destItem.getPrice(), destItem.getCount()));
				}
				item.setMoney(destItem.getPrice());
				itemList.add(item);
			}
			return itemList;
		}

		private Collection<? extends DismountingItem> getSourceItemList(Context context, GUID dismountingId,
				GoodsRefactorTask

				task) {
			if (CheckIsNull.isEmpty(task.getSourceItems())) {
				return null;
			}
			List<SourceItem> list = Arrays.asList(task.getSourceItems());
			List<DismountingItem> itemList = new ArrayList<DismountingItem>();
			for (SourceItem sourceItem : list) {
				DismountingItem item = new DismountingItem();
				item.setRECID(context.newRECID());
				item.setTenantsGuid(context.find(Login.class).getTenantId());
				item.setCreateDate(new Date().getTime());
				item.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId()).getName());
				item.setDismFlag(RefactorGoodsItemType.Source.getCode());
				item.setDismCount(sourceItem.getCount());
				item.setDismGuid(dismountingId);
				item.setGoodsGuid(sourceItem.getGoodsItemId());
				item.setStoreCost(sourceItem.getAverageCost());
				GoodsItem goodsItem = context.find(GoodsItem.class, sourceItem.getGoodsItemId());
				if (null != goodsItem) {
					item.setGoodsName(goodsItem.getGoodsName());
					item.setGoodsNo(goodsItem.getGoodsCode());
					item.setGoodsAttr(goodsItem.getPropertiesWithoutUnit());
					item.setUnit(goodsItem.getGoodsUnit());
					item.setGoodsScale(goodsItem.getScale());
					item.setMoney(DoubleUtil.mul(sourceItem.getAverageCost(), sourceItem.getCount(), 2));
				}
				itemList.add(item);
			}
			return itemList;
		}

	}

	/**
	 * 处理调拨单删除
	 * 
	 * 
	 */
	@Publish
	protected class DeleteAllocateHandle extends TaskMethodHandler<InventoryAllocateTask,

	InventoryAllocateTask.Method> {

		protected DeleteAllocateHandle() {
			super(InventoryAllocateTask.Method.Delete);
		}

		@Override
		protected void handle(Context context, InventoryAllocateTask task) throws Throwable {
			AllocateInventoryTask aTask = new AllocateInventoryTask();
			aTask.setAllocateInfoGuid(task.getSheetId());
			context.handle(aTask, Method.DELETE);
		}

	}

	/**
	 * 处理调拨单提交
	 * 
	 * 
	 */
	@Publish
	protected class SubmitAllocateHandle extends TaskMethodHandler<InventoryAllocateTask,

	InventoryAllocateTask.Method> {

		protected SubmitAllocateHandle() {
			super(InventoryAllocateTask.Method.Submit);
		}

		@Override
		protected void handle(Context context, InventoryAllocateTask task) throws Throwable {
			AllocateInventory allocateInventory = context.find(AllocateInventory.class, task.getSheetId());
			List<InventoryAllocateTask.Error> errorList = new ArrayList<InventoryAllocateTask.Error>();
			boolean success = true;
			if (null == allocateInventory) {
				errorList.add(InventoryAllocateTask.Error.EntityError);
				success = false;
			}
			Store store1 = context.find(Store.class, allocateInventory.getAllocateOutStoreId());
			Store store2 = context.find(Store.class, allocateInventory.getAllocateInStoreId());
			if (null == store1
					|| (!StoreStatus.ENABLE.equals(store1.getStatus()) && !StoreStatus.ONCOUNTING.equals(store1
							.getStatus()))
					|| null == store2
					|| (!StoreStatus.ENABLE.equals(store2.getStatus()) && !StoreStatus.ONCOUNTING.equals(store2
							.getStatus()))) {
				errorList.add(InventoryAllocateTask.Error.StoreStopError);
				success = false;
			}

			task.setSuccess(success);
			if (!success) {
				if (CheckIsNull.isNotEmpty(errorList)) {
					InventoryAllocateTask.Error[] errors = new InventoryAllocateTask.Error[errorList.size()];
					for (int i = 0; i < errorList.size(); i++) {
						errors[i] = errorList.get(i);
					}
					task.setErrors(errors);
				}
				return;
			}
			UpdateAllocateStatusTask uTask = new UpdateAllocateStatusTask(task.getSheetId());
			uTask.setUpTostatus(InventoryAllocateStatus.Submitted);
			context.handle(uTask, UpdateAllocateStatusTask.Method.SUBMIT);
			if (!(uTask.getUpdateResult() > 0)) {
				task.setSuccess(false);
				InventoryAllocateTask.Error[] errors = new InventoryAllocateTask.Error[] { InventoryAllocateTask.Error.ConcurrentError };
				task.setErrors(errors);
			} else {
				if (InventoryAllocateStatus.Submitted.equals(uTask.getUpTostatus())) {

					InventoryAllocateSubmitted event = new InventoryAllocateSubmitted();
					event.setInventoryAllocateSheetId(task.getSheetId());
					context.dispatch(event);
				}
			}

		}
	}

	/**
	 * 处理批准调拨单
	 * 
	 * 
	 */
	@Publish
	protected class ApprovalAllocateHandle extends TaskMethodHandler<InventoryAllocateTask,

	InventoryAllocateTask.Method> {

		protected ApprovalAllocateHandle() {
			super(InventoryAllocateTask.Method.Approval);
		}

		@Override
		protected void handle(Context context, InventoryAllocateTask task) throws Throwable {
			UpdateAllocateStatusTask uTask = new UpdateAllocateStatusTask(task.getSheetId());
			uTask.setPrestatus(InventoryAllocateStatus.Submitted);
			uTask.setUpTostatus(InventoryAllocateStatus.Allocating);
			context.handle(uTask, UpdateAllocateStatusTask.Method.EXMAINE);
			if (!(uTask.getUpdateResult() > 0)) {
				task.setSuccess(false);
				InventoryAllocateTask.Error[] errors = new InventoryAllocateTask.Error[] { InventoryAllocateTask.Error.ConcurrentError };
				task.setErrors(errors);
			} else {
				InventoryAllocateApprovalEvent event = new InventoryAllocateApprovalEvent(task.getSheetId());
				context.dispatch(event);
			}
		}

	}

	/**
	 * 处理驳回调拨单
	 * 
	 * 
	 */
	@Publish
	protected class DenyAllocateHandle extends TaskMethodHandler<InventoryAllocateTask,

	InventoryAllocateTask.Method> {

		protected DenyAllocateHandle() {
			super(InventoryAllocateTask.Method.Deny);
		}

		@Override
		protected void handle(Context context, InventoryAllocateTask task) throws Throwable {
			UpdateAllocateStatusTask uTask = new UpdateAllocateStatusTask(task.getSheetId());
			// AllocateInventory allocateInventory =
			// context.find(AllocateInventory.class, task.getSheetId());
			// if (checkSourceStore(context, allocateInventory, true) &&
			// checkDestStore(context, allocateInventory, true)) {
			// uTask.setUpdateBothExamine(true);
			// } else {
			// if (checkSourceStore(context, allocateInventory, true)) {
			// uTask.setIsOutExamine(true);
			// } else if (checkDestStore(context, allocateInventory, true)) {
			// uTask.setIsInExamine(true);
			// }
			// }
			uTask.setRejectReason(task.getDenyReason());
			uTask.setPrestatus(InventoryAllocateStatus.Submitted);
			uTask.setUpTostatus(InventoryAllocateStatus.Rejected);
			context.handle(uTask, UpdateAllocateStatusTask.Method.REJECT);

			if (!(uTask.getUpdateResult() > 0)) {
				task.setSuccess(false);
				InventoryAllocateTask.Error[] errors = new InventoryAllocateTask.Error[] { InventoryAllocateTask.Error.ConcurrentError };
				task.setErrors(errors);
			} else {
				InventoryAllocateDenyEvent event = new InventoryAllocateDenyEvent(task.getSheetId());
				context.dispatch(event);
			}

		}

	}

	/**
	 * 处理确认调出调拨单
	 * 
	 * 
	 */
	@Publish
	protected class AllocateOutAllocateHandle extends TaskMethodHandler<InventoryAllocateTask,

	InventoryAllocateTask.Method> {

		protected AllocateOutAllocateHandle() {
			super(InventoryAllocateTask.Method.AllocateOut);
		}

		@Override
		protected void handle(Context context, InventoryAllocateTask task) throws Throwable {
			List<InventoryAllocateTask.Error> errorList = new ArrayList<InventoryAllocateTask.Error>();
			boolean success = true;
			InventoryAllocateSheetInfo info = context.find(InventoryAllocateSheetInfo.class, task.getSheetId());
			Store store = context.find(Store.class, info.getSourceStoreId());
			if (null == store || StoreStatus.DISABLED.equals(store.getStatus())
					|| StoreStatus.STOP.equals(store.getStatus()) || StoreStatus.ONCOUNTING.equals(store.getStatus())) {
				success = false;
				errorList.add(InventoryAllocateTask.Error.StoreError);
			}
			task.setSuccess(success);
			if (!success) {
				if (CheckIsNull.isNotEmpty(errorList)) {
					InventoryAllocateTask.Error[] errors = new InventoryAllocateTask.Error[errorList.size()];
					for (int i = 0; i < errorList.size(); i++) {
						errors[i] = errorList.get(i);
					}
					task.setErrors(errors);
				}

				return;
			}

			AllocateItemDet[] iDets = new AllocateItemDet[task.getItems().length];
			AllocateItemDet iDet = null;
			for (int index = 0; index < task.getItems().length; index++) {
				InventoryAllocateTask.Item item = task.getItems()[index];
				iDet = new AllocateItemDet();
				iDet.setAllocateItemId(item.getAllocateItemId());
				iDet.setCount(item.getCount());
				iDet.setId(GUID.randomID());
				iDet.setProduceDate(item.getProduceDate());
				iDet.setSheetId(item.getSheetId());
				iDet.setShelfNo(item.getShelfNo());
				iDet.setStockId(item.getStockId());
				iDet.setTiersNo(item.getTiersNo());
				iDet.setShelfId(item.getShelfId());
				MaterialsItem m = context.find(MaterialsItem.class, item.getStockId());
				iDet.setStockName(m.getMaterialName());
				iDet.setStoreId(item.getStoreId());
				iDets[index] = iDet;
			}
			CreateAllocateItemDetTask detTask = new CreateAllocateItemDetTask(iDets);
			context.handle(detTask);

			UpdateAllocateStatusTask uTask = new UpdateAllocateStatusTask(task.getSheetId());
			uTask.setItems(iDets);
			uTask.setPrestatus(InventoryAllocateStatus.Allocating);
			uTask.setUpTostatus(InventoryAllocateStatus.AllocateOut);
			context.handle(uTask, UpdateAllocateStatusTask.Method.CONFIRM);

			if (!(uTask.getUpdateResult() > 0)) {
				task.setSuccess(false);
				InventoryAllocateTask.Error[] errors = new InventoryAllocateTask.Error[] { InventoryAllocateTask.Error.ConcurrentError };
				task.setErrors(errors);
			}

		}

	}

	/**
	 * 处理确认调入调拨单
	 * 
	 * 
	 */
	@Publish
	protected class AllocateInAllocateHandle extends TaskMethodHandler<InventoryAllocateTask,

	InventoryAllocateTask.Method> {

		protected AllocateInAllocateHandle() {
			super(InventoryAllocateTask.Method.AllocateIn);
		}

		@Override
		protected void handle(Context context, InventoryAllocateTask task) throws Throwable {
			
			List<InventoryAllocateTask.Error> errorList = new ArrayList<InventoryAllocateTask.Error>();
			boolean success = true;
			InventoryAllocateSheetInfo info = context.find(InventoryAllocateSheetInfo.class, task.getSheetId());
			Store store = context.find(Store.class, info.getDestinationStoreId());
			if (null == store || StoreStatus.DISABLED.equals(store.getStatus())
					|| StoreStatus.STOP.equals(store.getStatus()) || StoreStatus.ONCOUNTING.equals(store.getStatus())) {
				success = false;
				errorList.add(InventoryAllocateTask.Error.StoreError);
			}
			task.setSuccess(success);
			if (!success) {
				if (CheckIsNull.isNotEmpty(errorList)) {
					InventoryAllocateTask.Error[] errors = new InventoryAllocateTask.Error[errorList.size()];
					for (int i = 0; i < errorList.size(); i++) {
						errors[i] = errorList.get(i);
					}
					task.setErrors(errors);
				}

				return;
			}
			
			AllocateItemDet[] iDets = new AllocateItemDet[task.getItems().length];
			AllocateItemDet iDet = null;
			for (int index = 0; index < task.getItems().length; index++) {
				InventoryAllocateTask.Item item = task.getItems()[index];
				iDet = new AllocateItemDet();
				iDet.setCount(item.getCount());
				iDet.setId(GUID.randomID());
				iDet.setProduceDate(item.getProduceDate());
				iDet.setSheetId(item.getSheetId());
				iDet.setShelfNo(item.getShelfNo());
				iDet.setStockId(item.getStockId());
				iDet.setTiersNo(item.getTiersNo());
				iDet.setShelfId(item.getShelfId());
				iDet.setStockName(item.getStockName());
				iDet.setStoreId(item.getStoreId());
				iDets[index] = iDet;
			}
			
			UpdateAllocateStatusTask uTask = new UpdateAllocateStatusTask(task.getSheetId());
			uTask.setItems(iDets);
			uTask.setPrestatus(InventoryAllocateStatus.AllocateOut);
			uTask.setUpTostatus(InventoryAllocateStatus.AllocateIn);
			context.handle(uTask, UpdateAllocateStatusTask.Method.CONFIRM);

			if (!(uTask.getUpdateResult() > 0)) {
				task.setSuccess(false);
				InventoryAllocateTask.Error[] errors = new InventoryAllocateTask.Error[] { InventoryAllocateTask.Error.ConcurrentError };
				task.setErrors(errors);
			}

		}

	}

	
	/***
	 * 新增盘点单
	 * 
	 * 
	 */
	@Publish
	protected class InsertInventoryCountHandle extends TaskMethodHandler<InventoryCountTask, InventoryCountTask.Method> {

		protected InsertInventoryCountHandle() {
			super(InventoryCountTask.Method.Insert);
		}

		@Override
		protected void handle(Context context, InventoryCountTask task) throws Throwable {
			CheckInventoryTask cTask = new CheckInventoryTask();
			CheckInventory checkInventory = new CheckInventory();
			GUID checkInventoryId = context.newRECID();

			checkInventory.setRecid(checkInventoryId);
			checkInventory.setTenantsGuid(context.find(Login.class).getTenantId());
			checkInventory.setCheckOrdNo(context.find(String.class, SheetNumberType.InventoryCount));
			checkInventory.setCreateDate(new Date().getTime());
			checkInventory.setStartDate(new Date().getTime());
			checkInventory.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId())
					.getName

					());
			checkInventory.setStoreGuid(task.getStoreId());
			checkInventory.setCheckOrdState(InventoryCountStatus.Counting.getCode());
			Store store = context.find(Store.class, task.getStoreId());
			if (null == store || StoreStatus.DISABLED.equals(store.getStatus())
					|| StoreStatus.ONCOUNTING.equals(store.getStatus())) {
				return;
			}
			checkInventory.setStoreName(store.getName());
			checkInventory.setStorePY(PinyinHelper.getLetter(store.getName()));

			checkInventory.setCheckObj(task.getType().getCode());
			checkInventory.setCheckPerson(task.getName());
			cTask.setCheckInventoryEntity(checkInventory);
			List<CheckInventoryItem> itemList = new

			ArrayList<CheckInventoryItem>();
			int checkProfit = 0;
			int checkDeficient = 0;

			if (InventoryCountType.Materials.equals(task.getType())) {
				if (CheckIsNull.isNotEmpty(task.getTaskGoodsCountItems())) {

					List<TaskGoodsCountItem> list = Arrays.asList(task.getTaskGoodsCountItems());
					for (TaskGoodsCountItem goodsCountItem : list) {

						CheckInventoryItem item = new

						CheckInventoryItem();
						item.setRecid(context.newRECID());
						item.setTenantsGuid(context.find

						(Login.class).getTenantId());
						item.setCheckOrdGuid(checkInventoryId);
						item.setRealCount(goodsCountItem.getActualCount());
						item.setCreateDate(new Date().getTime());
						item.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId())
								.getName());
						item.setGoodsGuid(goodsCountItem.getGoodsItemId());
						GoodsItem goodsItem = context.find(GoodsItem.class, goodsCountItem.getGoodsItemId());
						if (null != goodsItem) {
							item.setGoodsAttr(goodsItem.getPropertiesWithoutUnit());
							item.setGoodsName(goodsItem.getGoodsName());
							item.setGoodsItemNo(goodsItem.getGoodsNo());
							item.setGoodsItemCode(goodsItem.getGoodsCode());
							item.setGoodsScale(goodsItem.getScale());
							item.setUnit(goodsItem.getGoodsUnit());
						}

						ResourceToken<Inventory> token =

						context.findResourceToken

						(Inventory.class, task.getStoreId

						(), goodsCountItem

						.getGoodsItemId());
						if (null != token) {
							item.setCarryCount(token.getFacade

							().getCount());
						}
						if (DoubleUtil.sub(item.getCarryCount(),

						item.getRealCount()) > 0) {
							checkProfit += 1;
						} else if (DoubleUtil.sub

						(item.getCarryCount(), item.getRealCount()) < 0) {
							checkDeficient += 1;
						}

						itemList.add(item);
					}
				}

			} else {
				if (CheckIsNull.isNotEmpty(task.getTaskKitCountItems())) {

					List<TaskKitCountItem> list = Arrays.asList(task.getTaskKitCountItems());
					for (TaskKitCountItem kitCountItem : list) {
						CheckInventoryItem item = new

						CheckInventoryItem();
						item.setRecid(context.newRECID());
						item.setTenantsGuid(context.find

						(Login.class).getTenantId());
						item.setCheckOrdGuid(checkInventoryId);
						item.setRealCount

						(kitCountItem.getActualCount());
						item.setCreateDate(new Date().getTime());
						item.setCreatePerson(context.find

						(Employee.class, context.find

						(Login.class).getEmployeeId()).getName());

						item.setGoodsAttr(kitCountItem.getKitDesc

						());
						item.setGoodsName(kitCountItem.getKitName

						());
						item.setUnit(kitCountItem.getKitUnit());
						item.setRemark(kitCountItem.getExplain());

						QueryKitInventoryKey key = new

						QueryKitInventoryKey(kitCountItem.getKitName(),

						kitCountItem.getKitUnit

						(), kitCountItem.getKitDesc

						());
						key.setStoreId(task.getStoreId());
						key.setIsInit(false);
						List<OthersInventory> goodsInventoryList =

						context.getList

						(OthersInventory.class, key);
						if (null != goodsInventoryList) {
							item.setCarryCount

							(goodsInventoryList.get(0).getGoodsCount());
						}
						if (DoubleUtil.sub(item.getRealCount(),

						item.getCarryCount()) > 0) {
							checkProfit += 1;
						} else if (DoubleUtil.sub

						(item.getCarryCount(), item.getRealCount()) < 0) {
							checkDeficient += 1;
						}
						itemList.add(item);
					}
				}

			}
			cTask.setList(itemList);
			checkInventory.setCheckProfit(checkProfit);
			checkInventory.setCheckDeficient(checkDeficient);
			context.handle(cTask, Method.INSERT);
			task.setSheetId(checkInventoryId);

			StoreStatus status = StoreStatus.ONCOUNTING;
			if (StoreStatus.STOP.equals(store.getStatus())) {
				status = StoreStatus.STOPANDONCOUNTING;
			}
			ChangeStoreStatusTask sTask = new ChangeStoreStatusTask(checkInventory.getStoreGuid(), status);
			context.handle(sTask);

		}

	}

	/***
	 * 编辑盘点单
	 * 
	 * 
	 */
	@Publish
	protected class SaveInventoryCountHandle extends TaskMethodHandler<InventoryCountTask, InventoryCountTask.Method> {

		protected SaveInventoryCountHandle() {
			super(InventoryCountTask.Method.Modify);
		}

		@Override
		protected void handle(Context context, InventoryCountTask task) throws Throwable {
			CheckInventoryTask cTask = new CheckInventoryTask();
			CheckInventory checkInventoryEntity = context.find(CheckInventory.class, task.getSheetId());
			// checkInventoryEntity.setRecid(task.getSheetId());
			checkInventoryEntity.setRemark(task.getRemark());

			List<CheckInventoryItem> itemList = new

			ArrayList<CheckInventoryItem>();
			int checkProfit = 0;
			int checkDeficient = 0;
			if (InventoryCountType.Materials.equals(task.getType())) {
				if (CheckIsNull.isNotEmpty(task.getTaskGoodsCountItems())) {

					for (TaskGoodsCountItem goodsCountItem : task.getTaskGoodsCountItems()) {
						CheckInventoryItem item = new CheckInventoryItem();
						item.setRecid(context.newRECID());
						item.setTenantsGuid(context.find(Login.class).getTenantId());
						item.setCheckOrdGuid(task.getSheetId());
						item.setRealCount(goodsCountItem.getActualCount());
						item.setCreateDate(new Date().getTime());
						item.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId())
								.getName());
						item.setGoodsGuid(goodsCountItem.getGoodsItemId());
						item.setRemark(goodsCountItem.getRemark());
						MaterialsItem goodsItem = context.find(MaterialsItem.class, goodsCountItem.getGoodsItemId());
						if (null != goodsItem) {
							item.setGoodsAttr(goodsItem.getSpec());
							item.setGoodsName(goodsItem.getMaterialName());
							item.setGoodsItemNo(goodsItem.getMaterialNo());
							item.setGoodsItemCode(goodsItem.getMaterialCode());
							item.setGoodsScale(goodsItem.getScale());
							item.setUnit(goodsItem.getMaterialUnit());
							item.setGoodsTypeGuid(goodsItem.getCategoryId());
						}
						ResourceToken<Inventory> token = context.findResourceToken(Inventory.class,
								checkInventoryEntity.getStoreGuid(), goodsCountItem.getGoodsItemId());
						if (null != token) {
							item.setCarryCount(token.getFacade().getCount());
						}
						if (DoubleUtil.sub(item.getRealCount(), item.getCarryCount()) > 0) {
							checkProfit += 1;
						} else if (DoubleUtil.sub(item.getRealCount(), item.getCarryCount()) < 0) {
							checkDeficient += 1;
						}
						itemList.add(item);
					}
				}
			} else {
				if (CheckIsNull.isNotEmpty(task.getTaskKitCountItems())) {
					for (TaskKitCountItem kitCountItem : task.getTaskKitCountItems()) {
						CheckInventoryItem item = new CheckInventoryItem();
						item.setRecid(context.newRECID());
						item.setTenantsGuid(context.find(Login.class).getTenantId());
						item.setCheckOrdGuid(task.getSheetId());
						item.setRealCount(kitCountItem.getActualCount());
						item.setCreateDate(new Date().getTime());
						item.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId())
								.getName());
						item.setGoodsAttr(kitCountItem.getKitDesc());
						item.setGoodsName(kitCountItem.getKitName());
						item.setUnit(kitCountItem.getKitUnit());
						item.setRemark(kitCountItem.getExplain());
						QueryKitInventoryKey key = new QueryKitInventoryKey(kitCountItem.getKitName(), kitCountItem
								.getKitUnit(), kitCountItem.getKitDesc());
						key.setIsInit(false);
						key.setStoreId(checkInventoryEntity.getStoreGuid());
						List<OthersInventory> goodsInventoryList = context.getList(OthersInventory.class, key);
						if (CheckIsNull.isNotEmpty(goodsInventoryList)) {
							item.setCarryCount(goodsInventoryList.get(0).getGoodsCount());
						}
						if (DoubleUtil.sub(item.getRealCount(), item.getCarryCount()) > 0) {
							checkProfit += 1;
						} else if (DoubleUtil.sub(item.getRealCount(), item.getCarryCount()) < 0) {
							checkDeficient += 1;
						}
						itemList.add(item);
					}
				}
			}
			checkInventoryEntity.setCheckProfit(checkProfit);
			checkInventoryEntity.setCheckDeficient(checkDeficient);
			cTask.setCheckInventoryEntity(checkInventoryEntity);
			cTask.setList(itemList);
			context.handle(cTask, Method.MODIFY);
			if (cTask.getCount() > 0) {
				task.setSuccess(true);
			} else {
				task.setSuccess(false);
				InventoryCountTask.Error[] errors = new InventoryCountTask.Error[] { InventoryCountTask.Error.ConcurrentError };
				task.setErrors(errors);
			}
		}
	}

	/***
	 * 完成盘点单
	 * 
	 * 
	 */
	@Publish
	protected class FinishInventoryCountHandle extends TaskMethodHandler<InventoryCountTask, InventoryCountTask.Method> {

		protected FinishInventoryCountHandle() {
			super(InventoryCountTask.Method.Finish);
		}

		@Override
		protected void handle(Context context, InventoryCountTask task) throws Throwable {
			CheckInventoryTask iTask = new CheckInventoryTask();
			iTask.setSheetId(task.getSheetId());
			iTask.setInventorysAdd(task.getInventorysAdd());
			iTask.setInventorysSub(task.getInventorysSub());
			context.handle(iTask, Method.MODIFYstatus);
			task.setSuccess(true);
			if (!(iTask.getCount() > 0)) {
				task.setSuccess(false);
				InventoryCountTask.Error[] errors = new InventoryCountTask.Error[] { InventoryCountTask.Error.ConcurrentError };
				task.setErrors(errors);
				return;
			}

			CheckInventory checkInventory = context.find(CheckInventory.class, task.getSheetId());
			if (null == checkInventory) {
				return;
			}

			Store store = context.find(Store.class, checkInventory.getStoreGuid());
			StoreStatus status = StoreStatus.ENABLE;
			if (StoreStatus.STOPANDONCOUNTING.equals(store.getStatus())) {
				status = StoreStatus.STOP;
			}
			ChangeStoreStatusTask sTask = new ChangeStoreStatusTask(checkInventory.getStoreGuid(), status);
			context.handle(sTask);
		}

	}

	/**
	 * 保存指定仓库的初始化商品列表
	 * 
	 * 
	 */
	@Publish
	protected class SaveInitGoodsItemHandle extends SimpleTaskMethodHandler<SaveInitInventoryTask> {

		@Override
		protected void handle(Context context, SaveInitInventoryTask task) throws Throwable {
			if (CheckIsNull.isEmpty(task.getStoreId())) {
				return;
			}
			Store store = context.find(Store.class, task.getStoreId());
			List<SaveInitInventoryTask.Error> errorList = new ArrayList<SaveInitInventoryTask.Error>();
			boolean success = true;
			if (null == store || !StoreStatus.DISABLED.equals(store.getStatus()))

			{
				throw new Throwable("您操作的数据已发生改变，请检查！");
				// errorList.add(SaveInitInventoryTask.Error.StoreError);
				// success = false;
			}
			InventoryInitTask gTask = new InventoryInitTask(task.getStoreId());
			if (CheckIsNull.isEmpty(task.getItems())) {
				return;
			}
			InitInventoryItem[] items = task.getItems();

			List<InventoryEntity> goodsInventoryList = new ArrayList<InventoryEntity>();
			List<InventoryDetEntity> inventoryDetList = new ArrayList<InventoryDetEntity>();
			for (InitInventoryItem item : items) {
				InventoryEntity goodsInventory = new InventoryEntity();
				GUID inventoryId = context.newRECID();
				goodsInventory.setId(inventoryId);
				goodsInventory.setStoreId(task.getStoreId());
				goodsInventory.setInitAmount(item.getAmount());
				goodsInventory.setInitCount(item.getCount());
				goodsInventory.setInitCost(item.getAverageCost());
				goodsInventory.setStockId(item.getStockId());
				goodsInventory.setName(item.getName());
				goodsInventory.setCode(item.getsCode());
				goodsInventory.setStockNo(item.getStockNo());
				goodsInventory.setSpec(item.getSpec());
				goodsInventory.setUnit(item.getUnit());
				goodsInventory.setInventoryType(item.getInventoryType().getCode());
				goodsInventory.setProperties(item.getProperties());
				goodsInventoryList.add(goodsInventory);

				if (task.isEnable() && null != item.getInventoryDetItems() && item.getInventoryDetItems().length > 0) {
					for (InitInventoryDetItem det : item.getInventoryDetItems()) {
						InventoryDetEntity entity = new InventoryDetEntity();
						entity.setId(context.newRECID());
						entity.setCount(det.getCount());
						entity.setInventoryId(inventoryId);
						entity.setProduceDate(det.getProduceDate());
						entity.setShelfId(det.getShelfId());
						entity.setShelfNo(det.getShelfNo());
						entity.setStockId(item.getStockId());
						entity.setStoreId(task.getStoreId());
						entity.setTiersNo(det.getTiersNo());

						inventoryDetList.add(entity);
					}
				}
			}
			task.setSuccess(success);
			if (!success) {
				if (CheckIsNull.isNotEmpty(errorList)) {
					SaveInitInventoryTask.Error[] errors = new SaveInitInventoryTask.Error[errorList.size()];
					for (int i = 0; i < errorList.size(); i++) {
						errors[i] = errorList.get(i);
					}
					task.setErrors(errors);
				}
			}
			gTask.setInventoryList(goodsInventoryList);
			gTask.setType(InventoryType.Goods);
			context.handle(gTask, InventoryInitTask.Method.SAVE);

			if (task.isEnable() && task.getItems() != null && task.getItems().length > 0) {
				gTask.setInventoryDetList(inventoryDetList);
				context.handle(gTask, InventoryInitTask.Method.ENABLE);
			}
		}

	}

	/**
	 * 保存指定仓库的初始化其他物品列表
	 * 
	 * 
	 */
	@Publish
	protected class SaveInitKitItemsHandle extends SimpleTaskMethodHandler<SaveInitKitItemsTask> {

		@Override
		protected void handle(Context context, SaveInitKitItemsTask task) throws Throwable {

			if (CheckIsNull.isEmpty(task.getItems())) {
				return;
			}
			List<StoreInitKitItem> items = Arrays.asList(task.getItems());

			DeleteOtherGoodsTask dTask = new DeleteOtherGoodsTask(task.getStoreId());
			context.handle(dTask);

			List<OtherGoods> otherGoodsList = new ArrayList<OtherGoods>();
			for (StoreInitKitItem item : items) {
				OtherGoods otherGoods = new OtherGoods();
				otherGoods.setRecid(context.newRECID());
				otherGoods.setTenantsId(context.find

				(Login.class).getTenantId());
				otherGoods.setNumber(item.getCount());
				otherGoods.setInit(true);
				otherGoods.setName(item.getKitName());
				otherGoods.setDescription(item.getKitDescription());
				otherGoods.setUnit(item.getUnit());

				otherGoodsList.add(otherGoods);

			}

			AddOtherGoodsTask oTask = new AddOtherGoodsTask(task.getStoreId(), otherGoodsList);
			oTask.setInit(true);
			context.handle(oTask);

		}

	}

	/**
	 * 开始盘点
	 * 
	 * 
	 */
	@Publish
	protected class StartInventoryCountHandle extends SimpleTaskMethodHandler<StartInventoryCountTask> {

		@Override
		protected void handle(Context context, StartInventoryCountTask task) throws Throwable {
			CheckInventoryTask iTask = new CheckInventoryTask();
			CheckInventory checkInventory = new CheckInventory();
			checkInventory.setStoreGuid(task.getStoreId());
			checkInventory.setCheckObj(task.getCountType().getCode());
			checkInventory.setCheckPerson(task.getCounter());
			checkInventory.setRecid(context.newRECID());
			checkInventory.setTenantsGuid(context.find(Login.class).getTenantId());
			checkInventory.setCreateDate(new Date().getTime());
			checkInventory.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId())
					.getName

					());
			checkInventory.setDeptGuid(context.find(Employee.class, context.find(Login.class).getEmployeeId())
					.getDepartmentId());
			iTask.setCheckInventoryEntity(checkInventory);

			context.handle(iTask, Method.INSERT);

			ChangeStoreStatusTask sTask = new ChangeStoreStatusTask(checkInventory.getStoreGuid(),

			StoreStatus.ONCOUNTING);
			context.handle(sTask);

		}

	}

	/**
	 * 更新指定商品在指定仓库的库存上下限
	 * 
	 * 
	 */
	@Publish
	protected class UpdateGoodsItemInventoryLimitHandle extends SimpleTaskMethodHandler<UpdateGoodsItemInventoryLimit> {

		@Override
		protected void handle(Context context, UpdateGoodsItemInventoryLimit task) throws Throwable {

			if (CheckIsNull.isEmpty(task.getItems())) {
				return;
			}
			List<Item> list = Arrays.asList(task.getItems());

			for (Item item : list) {
				InventoryLimitTask lTask = new InventoryLimitTask(item.getStoreId(), task.getGoodsItemId(), item
						.getCountUpperLimit(), item.getCountLowerLimit(), item.getAmountUpperLimit());
				context.handle(lTask);
			}

		}

	}

	/**
	 * 创建库存报损单
	 * 
	 * @author Administrator
	 * 
	 */
	@Publish
	protected class CreateReportLossInfoHandler extends
			TaskMethodHandler<ReportLossInfoTask, ReportLossInfoTask.Method> {

		protected CreateReportLossInfoHandler() {
			super(ReportLossInfoTask.Method.Create);
		}

		@Override
		protected void handle(Context context, ReportLossInfoTask task) throws Throwable {
			ReportLossTask iTask = new ReportLossTask();
			iTask.setId(task.getId());
			iTask.setCreateDate(task.getCreateDate());
			iTask.setCreator(task.getCreator());
			iTask.setCreatorId(task.getCreatorId());
			iTask.setRemark(task.getRemark());
			iTask.setStoreId(task.getStoreId());
			iTask.setStatus(ReportLossStatus.Submitting.getCode());
			iTask.setSheetNo(context.find(String.class, SheetNumberType.ReportLoss));

			Store store = context.find(Store.class, task.getStoreId());
			iTask.setStoreName(store.getName());

			ReportLossItem[] reportLossItems = new ReportLossItem[task.getItems().length];
			ReportLossItem reportLossItem = null;
			int itemIndex = 0;
			for (ReportLossInfoTask.Item item : task.getItems()) {
				if (null == item)
					continue;
				reportLossItem = new ReportLossItem();
				reportLossItem.setGoodsCode(item.getGoodsCode());
				reportLossItem.setGoodsId(item.getGoodsId());
				reportLossItem.setGoodsName(item.getGoodsName());
				reportLossItem.setGoodsNumber(item.getGoodsNumber());
				reportLossItem.setGoodsSpec(item.getGoodsSpec());
				reportLossItem.setGoodsUnit(item.getGoodsUnit());
				reportLossItem.setId(item.getId());
				reportLossItem.setReportCount(item.getReportCount());
				reportLossItem.setReportReason(item.getReportReason());
				reportLossItem.setReportLossSheetId(task.getId());
				reportLossItem.setScale(item.getScale());

				reportLossItems[itemIndex] = reportLossItem;
				itemIndex++;
			}
			ReportLossItemTask iItemTask = new ReportLossItemTask();
			iItemTask.setItems(reportLossItems);

			context.handle(iItemTask, ReportLossItemTask.Method.Create);
			context.handle(iTask, ReportLossTask.Method.Create);
		}

	}

	/**
	 * 修改报损单
	 * 
	 * @author Administrator
	 * 
	 */
	@Publish
	protected class ModifyReportLossInfoHandler extends
			TaskMethodHandler<ReportLossInfoTask, ReportLossInfoTask.Method> {

		protected ModifyReportLossInfoHandler() {
			super(ReportLossInfoTask.Method.Modify);
		}

		@Override
		protected void handle(Context context, ReportLossInfoTask task) throws Throwable {
			ReportLossTask iTask = new ReportLossTask();
			iTask.setId(task.getId());
			iTask.setCreateDate(task.getCreateDate());
			iTask.setCreator(task.getCreator());
			iTask.setCreatorId(task.getCreatorId());
			iTask.setRemark(task.getRemark());
			iTask.setStoreId(task.getStoreId());
			iTask.setStatus(task.getStatus().getCode());
			iTask.setSheetNo(task.getSheetNo());

			Store store = context.find(Store.class, task.getStoreId());
			iTask.setStoreName(store.getName());

			ReportLossItem[] reportLossItems = new ReportLossItem[task.getItems().length];
			ReportLossItem reportLossItem = null;
			int itemIndex = 0;
			for (ReportLossInfoTask.Item item : task.getItems()) {
				if (null == item)
					continue;
				reportLossItem = new ReportLossItem();
				reportLossItem.setGoodsCode(item.getGoodsCode());
				reportLossItem.setGoodsId(item.getGoodsId());
				reportLossItem.setGoodsName(item.getGoodsName());
				reportLossItem.setGoodsNumber(item.getGoodsNumber());
				reportLossItem.setGoodsSpec(item.getGoodsSpec());
				reportLossItem.setGoodsUnit(item.getGoodsUnit());
				reportLossItem.setId(item.getId());
				reportLossItem.setReportCount(item.getReportCount());
				reportLossItem.setReportReason(item.getReportReason());
				reportLossItem.setReportLossSheetId(task.getId());
				reportLossItem.setScale(item.getScale());

				reportLossItems[itemIndex] = reportLossItem;
				itemIndex++;
			}
			ReportLossItemTask iItemTask = new ReportLossItemTask();
			iItemTask.setItems(reportLossItems);

			// 先删除所有明细
			List<ReportLossItem> itemList = context.getList(ReportLossItem.class, task.getId());
			GUID[] itemIds = new GUID[itemList.size()];
			itemIndex = 0;
			for (ReportLossItem item : itemList) {
				itemIds[itemIndex] = item.getId();
				itemIndex++;
			}
			context.handle(new DeleteReportLossItemTask(itemIds));

			// 保存明细
			context.handle(iItemTask, ReportLossItemTask.Method.Create);
			// 保存主表信息
			context.handle(iTask, ReportLossTask.Method.Modify);
		}

	}

	/**
	 * 删除指定的库存报损单
	 * 
	 * @author Administrator
	 * 
	 */
	@Publish
	protected class DeleteReportLossInfo extends SimpleTaskMethodHandler<DeleteReportLossInfoTask> {

		@Override
		protected void handle(Context context, DeleteReportLossInfoTask task) throws Throwable {
			List<ReportLossItem> itemList = context.getList(ReportLossItem.class, task.getId());
			GUID[] itemIds = new GUID[itemList.size()];
			int itemIndex = 0;
			for (ReportLossItem item : itemList) {
				itemIds[itemIndex] = item.getId();
				itemIndex++;
			}
			context.handle(new DeleteReportLossItemTask(itemIds));
			context.handle(new DeleteReportLossTask(task.getId()));
		}
	}

	/**
	 * 根据ID取得ReportLossInfo
	 * 
	 * @author Administrator
	 * 
	 */
	@Publish
	protected class GetReportLossInfoById extends OneKeyResultProvider<ReportLossInfo, GUID> {

		@Override
		protected ReportLossInfo provide(Context context, GUID key) throws Throwable {
			ReportLoss reportLoss = context.find(ReportLoss.class, key);
			ReportLossInfoImpl reportLossInfo = new ReportLossInfoImpl();
			reportLossInfo.setApplyDate(reportLoss.getApplyDate());
			reportLossInfo.setApprovalDate(reportLoss.getApprovalDate());
			reportLossInfo.setApprovalPersonId(reportLoss.getApprovalPersonId());
			reportLossInfo.setApprovalPersonName(reportLoss.getApprovalPersonName());
			reportLossInfo.setCreateDate(reportLoss.getCreateDate());
			reportLossInfo.setCreateor(reportLoss.getCreator());
			reportLossInfo.setCreatorId(reportLoss.getCreatorId());
			reportLossInfo.setId(reportLoss.getId());
			reportLossInfo.setRemark(reportLoss.getRemark());
			reportLossInfo.setRejectReason(reportLoss.getRejectReason());
			reportLossInfo.setSheetNo(reportLoss.getSheetNo());
			reportLossInfo.setStoreId(reportLoss.getStoreId());
			reportLossInfo.setStoreName(reportLoss.getStoreName());
			reportLossInfo.setStatus(ReportLossStatus.getStatusByCode(reportLoss.getStatus()));

			List<ReportLossItem> itemList = context.getList(ReportLossItem.class, key);
			ReportLossInfoItemImpl[] infoItems = new ReportLossInfoItemImpl[itemList.size()];
			ReportLossInfoItemImpl infoItem = null;
			int index = 0;
			for (ReportLossItem iItem : itemList) {
				infoItem = new ReportLossInfoItemImpl();
				infoItem.setGoodsCode(iItem.getGoodsCode());
				infoItem.setGoodsId(iItem.getGoodsId());
				infoItem.setGoodsName(iItem.getGoodsName());
				infoItem.setGoodsSpec(iItem.getGoodsSpec());
				infoItem.setGoodsUnit(iItem.getGoodsUnit());
				infoItem.setId(iItem.getId());
				infoItem.setReportCount(iItem.getReportCount());
				infoItem.setReportLossSheetId(iItem.getReportLossSheetId());
				infoItem.setReportReason(iItem.getReportReason());
				infoItem.setScale(iItem.getScale());

				infoItem.setGoodsNo(iItem.getGoodsNumber());

				infoItems[index] = infoItem;
				index++;
			}
			reportLossInfo.setItems(infoItems);
			return reportLossInfo;
		}

	}

	/**
	 * 修改指定报损单的状态
	 * 
	 * @author Administrator
	 * 
	 */
	@Publish
	protected class ChangeReportLossInfoStatus extends SimpleTaskMethodHandler<ChangReportLossInfoStautsTask> {

		private boolean isStoreManager(Context context, Login login, GUID storeId) {
			GetStoreListKey sKey = new GetStoreListKey(StoreStatus.ALL);
			ListEntity<StoreItem> listEntity = context.find(ListEntity.class, sKey);
			if (null == listEntity) return false;
			boolean isContainStore = false;
			for (StoreItem store : listEntity.getItemList()) {
				if (store.getId().equals(storeId)) {
					isContainStore = true;
					break;
				}
			}
			return isContainStore;
		}
		
		@Override
		protected void handle(Context context, ChangReportLossInfoStautsTask task) throws Throwable {
			// 修改报损单的状态
			String nextStatus = null;
			Login login = context.find(Login.class);
			Employee employee = context.find(Employee.class, login.getEmployeeId());
			ChangeReportLossStatusTask iTask = new ChangeReportLossStatusTask(task.getSheetId());
			iTask.setStatus(task.getStatus().getCode());
			ReportLoss reportLoss = context.get(ReportLoss.class, task.getSheetId());
			if (ChangReportLossInfoStautsTask.Operation.submit.equals(task.getOperation())) {
				iTask.setApplyDate(new Date().getTime());
				if (login.hasAuth(Auth.Boss)) {
					nextStatus = ReportLossStatus.Finished.getCode();
					iTask.setApprovalDate(new Date().getTime());
					iTask.setApprovalPersonId(employee.getId());
					iTask.setApprovalPersonName(employee.getName());
				} else if(login.hasAuth(Auth.StoreKeeperManager) && isStoreManager(context, login, reportLoss.getStoreId())) {
					nextStatus = ReportLossStatus.AccountApprovling.getCode();
					iTask.setApprovalDate(new Date().getTime());
					iTask.setApprovalPersonId(employee.getId());
					iTask.setApprovalPersonName(employee.getName());
				} else {
					nextStatus = ReportLossStatus.Approvling.getCode();
				}
			} else if (ChangReportLossInfoStautsTask.Operation.approval.equals(task.getOperation())) {
				if (login.hasAuth(Auth.AccountManager)
						|| login.hasAuth(Auth.Boss)) {
					nextStatus = ReportLossStatus.Finished.getCode();
					iTask.setApprovalDate(new Date().getTime());
					iTask.setApprovalPersonId(employee.getId());
					iTask.setApprovalPersonName(employee.getName());
				} else {
					nextStatus = ReportLossStatus.AccountApprovling.getCode();
					iTask.setApprovalDate(new Date().getTime());
					iTask.setApprovalPersonId(employee.getId());
					iTask.setApprovalPersonName(employee.getName());
				}

			} else if (ChangReportLossInfoStautsTask.Operation.reject.equals(task.getOperation())) {
				if (org.apache.cxf.common.util.StringUtils.isEmpty(task.getRejectReason())) {
					throw new Throwable("退回原因不能为空！");
				}
				nextStatus = ReportLossStatus.Deied.getCode();
				iTask.setApprovalDate(new Date().getTime());
				iTask.setApprovalPersonId(employee.getId());
				iTask.setApprovalPersonName(employee.getName());
				iTask.setRejectReason(task.getRejectReason());
				
			}

			iTask.setNextStatus(nextStatus);
			context.handle(iTask);

			if (ReportLossStatus.Finished.getCode().equals(nextStatus)) {
				writeInventoryLog(context, task.getSheetId(), employee);
			}

			if (ChangReportLossInfoStautsTask.Operation.submit.equals(task.getOperation())) {
				// 存储货位信息
				handleShelfInfoSave(context, task.getSheetId(), task.getItems());

				// 减少库存
				handleInventoryChange(context, reportLoss.getStoreId(), task.getItems());
			} else if (ChangReportLossInfoStautsTask.Operation.reject.equals(task.getOperation())) {
				List<ReportLossItemDet> allShetItemDetList = new ArrayList<ReportLossItemDet>();
				// 恢复库存
				List<ReportLossItem> itemList = context.getList(ReportLossItem.class, task.getSheetId());
				for (ReportLossItem item : itemList) {
					List<ReportLossItemDet> itemDetList = context.getList(ReportLossItemDet.class, item.getId());
					handleResetInventory(context, reportLoss.getStoreId(), item, itemDetList);

					allShetItemDetList.addAll(itemDetList);
				}
				// 删除货位信息
				GUID[] itemDetIds = new GUID[allShetItemDetList.size()];
				for (int itemDetIndex = 0; itemDetIndex < allShetItemDetList.size(); itemDetIndex++) {
					itemDetIds[itemDetIndex] = allShetItemDetList.get(itemDetIndex).getId();
				}
				context.handle(new DeleteReportLossItemDetTask(itemDetIds));
			}
		}

		/**
		 * 写库存流水
		 * 
		 * @param context
		 * @param reportLossId
		 */
		private void writeInventoryLog(Context context, GUID reportLossId, Employee employee) {
			ReportLoss reportLoss = context.find(ReportLoss.class, reportLossId);
			List<ReportLossItem> itemList = context.getList(ReportLossItem.class, reportLossId);
			List<InventoryLogEntity> list = new ArrayList<InventoryLogEntity>();
			InventoryLogEntity logEntity = null;
			for (ReportLossItem rItem : itemList) {
				logEntity = new InventoryLogEntity();
				logEntity.setId(GUID.randomID());
				logEntity.setStockId(rItem.getGoodsId());
				logEntity.setStoreId(reportLoss.getStoreId());
				logEntity.setName(rItem.getGoodsName());
				logEntity.setProperties(rItem.getGoodsSpec());
				logEntity.setUnit(rItem.getGoodsUnit());
				MaterialsItem mi = context.find(MaterialsItem.class, rItem.getGoodsId());
				logEntity.setCategoryId(mi.getCategoryId());
				logEntity.setCode(rItem.getGoodsCode());
				logEntity.setStockNo(rItem.getGoodsNumber());
				logEntity.setOrderId(reportLoss.getId());
				logEntity.setOrderNo(reportLoss.getSheetNo());
				logEntity.setLogType(InventoryLogType.ReportLoss.getCode());
				logEntity.setScale(mi.getScale());
				logEntity.setOutstoCount(rItem.getReportCount());
				logEntity.setOutstoAmount(rItem.getReportCount() * mi.getAvgBuyPrice());
				logEntity.setInventoryType(InventoryType.Materials.getCode());
				logEntity.setCreatedDate(new Date().getTime());
				logEntity.setCreatePerson(employee.getName());
				list.add(logEntity);
			}
			StoStreamTask task = new StoStreamTask();
			task.setList(list);
			context.handle(task, StoStreamTask.Task.add);
		}

		/**
		 * 存储货位信息
		 * 
		 * @param context
		 * @param items
		 */
		private void handleShelfInfoSave(Context context, GUID sheetId, ReportLossInfoTask.Item[] items) {
			List<ReportLossItemDet> itemDetList = new ArrayList<ReportLossItemDet>();
			for (ReportLossInfoTask.Item lossInfoItem : items) {
				ReportLossItemDet itemDet = null;
				for (ReportLossInfoItemDet lossInfoItemDet : lossInfoItem.getItemDets()) {
					itemDet = new ReportLossItemDet();
					itemDet.setCount(lossInfoItemDet.getCount());
					itemDet.setId(lossInfoItemDet.getId());
					itemDet.setProduceDate(lossInfoItemDet.getProduceDate());
					itemDet.setReportLossItemId(lossInfoItem.getId());
					itemDet.setShelfId(lossInfoItemDet.getShelfId());
					itemDet.setStockId(lossInfoItemDet.getStockId());
					itemDet.setTiersNo(lossInfoItemDet.getTiersNo());
					itemDet.setSheetId(sheetId);
					itemDet.setShelfNo(lossInfoItemDet.getShelfNo());

					itemDetList.add(itemDet);
				}
			}
			context.handle(new CreateReportLossItemDetTask(itemDetList.toArray(new ReportLossItemDet[0])));
		}

		/**
		 * 减少库存
		 * 
		 * @param context
		 * @param storeId
		 * @param items
		 */
		private void handleInventoryChange(Context context, GUID storeId, ReportLossInfoTask.Item[] items) {
			for (ReportLossInfoTask.Item item : items) {
				InventoryBusTask task = new InventoryBusTask(storeId, item.getGoodsId());
				InventoryBusTask.DetItem[] detItems = new InventoryBusTask.DetItem[item.getItemDets().length];
				InventoryBusTask.DetItem detItem = null;
				int itemDetIndex = 0;
				for (ReportLossInfoItemDet lossInfoItemDet : item.getItemDets()) {
					detItem = task.new DetItem(lossInfoItemDet.getShelfId(), lossInfoItemDet.getShelfNo(),
							lossInfoItemDet.getTiersNo(), lossInfoItemDet.getStockId(), -lossInfoItemDet.getCount(),
							lossInfoItemDet.getProduceDate(), storeId);
					detItems[itemDetIndex] = detItem;
					itemDetIndex++;
				}
				task.setDets(detItems);
				double changeCount = -item.getReportCount();
				task.setChangeCount(changeCount);

				context.handle(task);
			}

		}

		/**
		 * 恢复库存
		 * 
		 * @param context
		 * @param storeId
		 * @param item
		 * @param detList
		 */
		private void handleResetInventory(Context context, GUID storeId, ReportLossItem item,
				List<ReportLossItemDet> detList) {
			InventoryBusTask task = new InventoryBusTask(storeId, item.getGoodsId());
			InventoryBusTask.DetItem[] detItems = new InventoryBusTask.DetItem[detList.size()];
			InventoryBusTask.DetItem detItem = null;
			int itemDetIndex = 0;
			for (ReportLossItemDet itemDet : detList) {
				detItem = task.new DetItem(itemDet.getShelfId(), itemDet.getShelfNo(), itemDet.getTiersNo(), itemDet
						.getStockId(), itemDet.getCount(), itemDet.getProduceDate(), storeId);
				detItems[itemDetIndex] = detItem;
				itemDetIndex++;
			}
			task.setDets(detItems);

			double changeCount = item.getReportCount();
			task.setChangeCount(changeCount);
			context.handle(task);
		}
	}

	/**
	 * 查询报损单列表
	 * 
	 * @author Administrator
	 * 
	 */
	@Publish
	protected class QueryReportLossInfoList extends OneKeyResultListProvider<ReportLossInfo, GetReportLossInfoListKey> {

		@Override
		protected void provide(Context context, GetReportLossInfoListKey key, List<ReportLossInfo> resultList)
				throws Throwable {
			GetReportLossListKey iKey = new GetReportLossListKey();
			if (null != key.getQueryTerm()) {
				iKey.setBeginTime(key.getQueryTerm().getStartTime());
				iKey.setEndTime(key.getQueryTerm().getEndTime());
			}
			iKey.setSearchKey(key.getSearchKey());
			if (key.getSortField() != null) {
				iKey.setSortColumn(key.getSortField().getFieldName());
			} else {
				iKey.setSortColumn(GetReportLossInfoListKey.SortField.None.getFieldName());
			}
			if (key.getSortType() != null) {
				iKey.setSortType(key.getSortType().name());
			} else {
				iKey.setSortType(SortType.Desc.name());
			}
			iKey.setViewStatus(GetReportLossListKey.ViewStatus.valueOf(key.getViewStatus().name()));
			
			List<ReportLoss> iRepotLossList = context.getList(ReportLoss.class, iKey);
			ReportLossInfoImpl reportLossInfo = null;
			for (ReportLoss reportLoss : iRepotLossList) {
				reportLossInfo = new ReportLossInfoImpl();
				reportLossInfo.setApplyDate(reportLoss.getApplyDate());
				reportLossInfo.setApprovalDate(reportLoss.getApprovalDate());
				reportLossInfo.setApprovalPersonId(reportLoss.getApprovalPersonId());
				reportLossInfo.setApprovalPersonName(reportLoss.getApprovalPersonName());
				reportLossInfo.setCreateDate(reportLoss.getCreateDate());
				reportLossInfo.setCreateor(reportLoss.getCreator());
				reportLossInfo.setCreatorId(reportLoss.getCreatorId());
				reportLossInfo.setId(reportLoss.getId());
				reportLossInfo.setRemark(reportLoss.getRemark());
				reportLossInfo.setSheetNo(reportLoss.getSheetNo());
				reportLossInfo.setStoreId(reportLoss.getStoreId());
				reportLossInfo.setStoreName(reportLoss.getStoreName());
				reportLossInfo.setStatus(ReportLossStatus.getStatusByCode(reportLoss.getStatus()));

				List<ReportLossItem> iReportLossItemList = context.getList(ReportLossItem.class, reportLoss.getId());
				ReportLossInfoItemImpl[] infoItems = new ReportLossInfoItemImpl[iReportLossItemList.size()];
				ReportLossInfoItemImpl infoItem = null;
				int index = 0;
				for (ReportLossItem iItem : iReportLossItemList) {
					infoItem = new ReportLossInfoItemImpl();
					infoItem.setGoodsCode(iItem.getGoodsCode());
					infoItem.setGoodsId(iItem.getGoodsId());
					infoItem.setGoodsName(iItem.getGoodsName());
					infoItem.setGoodsSpec(iItem.getGoodsSpec());
					infoItem.setGoodsUnit(iItem.getGoodsUnit());
					infoItem.setId(iItem.getId());
					infoItem.setReportCount(iItem.getReportCount());
					infoItem.setReportLossSheetId(iItem.getReportLossSheetId());
					infoItem.setReportReason(iItem.getReportReason());
					infoItem.setScale(iItem.getScale());
					infoItem.setGoodsNo(iItem.getGoodsNumber());

					infoItems[index] = infoItem;
					index++;
				}
				reportLossInfo.setItems(infoItems);
				resultList.add(reportLossInfo);
			}

		}

	}

	/**
	 * 计算可用库存
	 * 
	 * @param goodsInventory
	 * @return double
	 */
	public double getAvailableCount(Inventory goodsInventory) {

		return DoubleUtil.sub(goodsInventory.getCount(), DoubleUtil.sum(goodsInventory.getToDeliverCount(),
				goodsInventory.getLockedCount()));
	}

	@SuppressWarnings("unchecked")
	public boolean checkDestStore(Context context, AllocateInventory allocateInventory, boolean onCountingstatus) {
		GUID destinationStoreId = allocateInventory.getAllocateInStoreId();
		if (context.find(Boolean.class, Auth.Boss)) {
			return true;
		}
		StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE };
		if (onCountingstatus) {
			statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING };
		}
		GetStoreListKey key = new GetStoreListKey(statuss);
		ListEntity<StoreItem> listEntity = context.find(ListEntity.class, key);
		if (null == listEntity || CheckIsNull.isEmpty(listEntity.getItemList())) {
			return false;
		}
		boolean hasStore = false;
		for (StoreItem item : listEntity.getItemList()) {
			if (item.getId().equals(destinationStoreId) || item.getId().equals(destinationStoreId)) {
				hasStore = true;
				break;
			}
		}
		return hasStore;
	}

	@SuppressWarnings("unchecked")
	public boolean checkSourceStore(Context context, AllocateInventory allocateInventory, boolean onCountingstatus) {
		GUID destinationStoreId = allocateInventory.getAllocateOutStoreId();
		if (context.find(Boolean.class, Auth.Boss)) {
			return true;
		}
		StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE };
		if (onCountingstatus) {
			statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING };
		}

		GetStoreListKey key = new GetStoreListKey(statuss);
		ListEntity<StoreItem> listEntity = context.find(ListEntity.class, key);
		if (null == listEntity || CheckIsNull.isEmpty(listEntity.getItemList())) {
			return false;
		}
		boolean hasStore = false;
		for (StoreItem item : listEntity.getItemList()) {
			if (item.getId().equals(destinationStoreId) || item.getId().equals(destinationStoreId)) {
				hasStore = true;
				break;
			}
		}
		return hasStore;
	}

	@SuppressWarnings("unchecked")
	public boolean storeIsEnableOrCounting(Context context, Inventory goodsInventory) {
		GetStoreListKey key = new GetStoreListKey(StoreStatus.ENABLE, StoreStatus.ONCOUNTING);
		ListEntity<StoreItem> listEntity = context.find(ListEntity.class, key);
		if (CheckIsNull.isEmpty(listEntity.getItemList())) {
			return true;
		}
		for (StoreItem item : listEntity.getItemList()) {
			if (item.getId().equals(goodsInventory.getStoreId())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取可出库数量（库存数量-锁定数量）
	 * 
	 * @param facade
	 * @return double
	 */
	public double getRealCount(Inventory facade) {

		return DoubleUtil.sub(facade.getCount(), facade.getLockedCount());
	}

	/**
	 * 采购建议
	 * 
	 * @param context
	 * @param goodsInventory
	 * @return double
	 */
	public double getAdviseCount(Context context, Inventory goodsInventory) {
		double adviseCount = goodsInventory.getToDeliverCount() - goodsInventory.getOnWayCount()
				- goodsInventory.getCount() + goodsInventory.getLockedCount()
				+ (-1 == goodsInventory.getLowerLimitCount() ? 0 : goodsInventory.getLowerLimitCount());
		if (adviseCount > 0) {
			GetPurchaseOrderGoodsCountByGoodsIdKey oKey = new GetPurchaseOrderGoodsCountByGoodsIdKey(goodsInventory
					.getStockId(), goodsInventory.getStoreId());
			Double orderCount = context.find(Double.class, oKey);
			if (null != orderCount) {
				adviseCount = DoubleUtil.sub(adviseCount, orderCount);
			}
		}
		return adviseCount;
	}

}
