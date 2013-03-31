package com.spark.psi.inventory.service.pub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Store;
import com.spark.psi.base.key.GetGoodsInventoryByStoreIdKey;
import com.spark.psi.base.key.GetPurchaseOrderGoodsCountByGoodsIdKey;
import com.spark.psi.base.utils.MaterialsProperyUtil;
import com.spark.psi.inventory.internal.entity.AllocateInventory;
import com.spark.psi.inventory.internal.entity.AllocateInventoryItem;
import com.spark.psi.inventory.internal.entity.CheckInventory;
import com.spark.psi.inventory.internal.entity.CheckInventoryItem;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.internal.entity.OthersInventory;
import com.spark.psi.inventory.intf.entity.instorage.CheckInLog;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.inventory.intf.entity.outstorage.CheckOutLog;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.inventory.intf.publish.entity.CheckKitItemImpl;
import com.spark.psi.inventory.intf.publish.entity.CheckedOutItemImpl;
import com.spark.psi.inventory.intf.publish.entity.CheckingGoodsItemImpl;
import com.spark.psi.inventory.intf.publish.entity.CheckingInInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.CheckingInItemImpl;
import com.spark.psi.inventory.intf.publish.entity.CheckingOutInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.CheckingOutItemImpl;
import com.spark.psi.inventory.intf.publish.entity.GoodsOrKitInventorySummaryImpl;
import com.spark.psi.inventory.intf.publish.entity.InventoryAllocateSheetInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.InventoryAllocateSheetItemImpl;
import com.spark.psi.inventory.intf.publish.entity.InventoryCountSheetInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.InventoryCountSheetItemImpl;
import com.spark.psi.inventory.intf.publish.entity.InventoryInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.InventoryLogItemImpl;
import com.spark.psi.inventory.intf.publish.entity.KitInventoryDetailImpl;
import com.spark.psi.inventory.intf.publish.entity.OrderCheckSheetItemImpl;
import com.spark.psi.inventory.intf.publish.entity.GoodsOrKitInventorySummaryImpl.SummaryItemImpl;
import com.spark.psi.inventory.intf.publish.entity.InventoryAllocateSheetInfoImpl.AllocateGoodsItemImpl;
import com.spark.psi.inventory.intf.publish.entity.InventoryCountSheetInfoImpl.GoodsCountItemImpl;
import com.spark.psi.inventory.intf.publish.entity.InventoryCountSheetInfoImpl.KitCountItemImpl;
import com.spark.psi.inventory.intf.task.inventory.StoStreamTask;
import com.spark.psi.inventory.intf.task.resource.InventoryResourceTask;
import com.spark.psi.inventory.service.resource.InventoryEntity;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.inventory.entity.CheckingInItem;
import com.spark.psi.publish.inventory.entity.CheckingOutItem;
import com.spark.psi.publish.inventory.entity.GoodsOrKitInventorySummary;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetItem;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetInfo;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetItem;
import com.spark.psi.publish.inventory.entity.InventoryInfo;
import com.spark.psi.publish.inventory.entity.InventoryLogItem;
import com.spark.psi.publish.inventory.entity.KitInventoryDetail;
import com.spark.psi.publish.inventory.entity.KitInventoryItem;
import com.spark.psi.publish.inventory.entity.OrderCheckSheetItem;
import com.spark.psi.publish.inventory.key.GetKitInventoryDetailListKey;
import com.spark.psi.publish.inventory.task.AdjustGoodsItemCostTask;

/**
 * <p>
 * 工具类
 * </p>
 * 
 */

public final class InventoryServiceUtil {

	/**
	 * 入库单详情
	 * 
	 * @param instorage
	 * @param itemList
	 * @param logList
	 * @return CheckingInInfoImpl
	 */
	public static CheckingInInfoImpl getCheckingInInfoImpl(Instorage instorage, List<InstorageItem> itemList,
			List<CheckInLog> logList) {

		CheckingInInfoImpl impl = new CheckingInInfoImpl();
		impl.setSheetId(instorage.getRECID());
		impl.setCreateDate(instorage.getCreateDate());
		impl.setPlanCheckinDate(instorage.getCheckinDate());
		impl.setLastCheckinDate(getLatestCheckInDate(logList));
		impl.setRelaBillsNo(instorage.getRelaBillsNo());
		impl.setStoreId(instorage.getStoreId());
		impl.setStoreName(instorage.getStoreName());
		if (instorage.isStoped()) {
			impl.setStatus(CheckingInStatus.Stop);
			impl.setStopReason(instorage.getStopReason());
		} else {
			impl.setStatus(CheckingInStatus.getCheckingInType(instorage.getStatus()));
		}

		impl.setType(CheckingInType.getCheckingInType(instorage.getSheetType()));
		impl.setPartnerId(instorage.getPartnerId());
		impl.setPartnerName(instorage.getPartnerShortName());
		impl.setRemark(instorage.getRemark());
		impl.setLastCheckinDate(0);
		if (CheckingInType.Kit.getCode().equals(instorage.getSheetType())) {
			CheckKitItemImpl[] CheckKitItems = new CheckKitItemImpl[itemList.size()];
			for (int itemIndex = 0; itemIndex < itemList.size(); itemIndex++) {
				CheckKitItemImpl tempItem = new CheckKitItemImpl();
				tempItem.setCount((int) itemList.get(itemIndex).getCount());
				tempItem.setKitDescription(itemList.get(itemIndex).getGoodsSpec());
				tempItem.setKitName(itemList.get(itemIndex).getGoodsName());
				tempItem.setUnit(itemList.get(itemIndex).getUnit());
				CheckKitItems[itemIndex] = tempItem;
			}
			impl.setCheckKitItems(CheckKitItems);
		} else {
			CheckingGoodsItemImpl[] checkingGoodsItems = new CheckingGoodsItemImpl[itemList.size()];
			for (int itemIndex = 0; itemIndex < itemList.size(); itemIndex++) {
				CheckingGoodsItemImpl tempItem = new CheckingGoodsItemImpl();
				InstorageItem old = itemList.get(itemIndex);
				tempItem.setCheckCount(0);
				tempItem.setCheckedCount(old.getCheckinCount());
				tempItem.setCheckingCount(old.getCount());
				tempItem.setPrice(old.getPrice());
				tempItem.setGoodsItemId(old.getGoodsId());
				tempItem.setId(old.getId());
				tempItem.setCountDecimal(old.getScale());
				tempItem.setInspectCount(old.getInspectCount());
				checkingGoodsItems[itemIndex] = tempItem;
			}
			impl.setCheckingGoodsItems(checkingGoodsItems);
		}
		if (CheckingInType.Kit.getCode().equals(instorage.getSheetType())) {

			// if (CheckIsNull.isNotEmpty(instorage.getSureInsto())) {
			// CheckedInItemImpl tempLog = new CheckedInItemImpl();
			// tempLog.setCheckinDate(DateUtil.convertStringToDate(instorage.getSureInsto().substring(
			// instorage.getSureInsto().indexOf("（"),
			// instorage.getSureInsto().indexOf("）"))));
			// tempLog.setCheckedInPersonName(instorage.getSureInsto().substring(0,
			// instorage.getSureInsto().indexOf("（")));
			// CheckedInItemImpl[] checkedOutItems = new CheckedInItemImpl[] {
			// tempLog };
			// impl.setCheckedInItems(checkedOutItems);
			// }
		} else {
			// CheckedInItemImpl[] checkedOutItems = new
			// CheckedInItemImpl[logList.size()];
			// for (int logIndex = 0; logIndex < logList.size(); logIndex++) {
			// CheckedInItemImpl tempLog = new CheckedInItemImpl();
			// tempLog.setCheckinDate(logList.get(logIndex).getCheckInDate());
			// tempLog.setCheckedInPersonName(logList.get(logIndex).getKeeper());
			// checkedOutItems[logIndex] = tempLog;
			// }
			// impl.setCheckedInItems(checkedOutItems);
		}

		return impl;
	}

	/**
	 * 排序枚举解析
	 * 
	 * @param sortType
	 * @return String
	 */
	public static String getSortTypeString(SortType sortType) {
		if (SortType.Asc.equals(sortType))
			return "asc";
		else
			return "desc";
	}

	/**
	 * 入库单列表Item
	 * 
	 * @param instorage
	 * @return CheckingInItem
	 */
	public static CheckingInItem getCheckingInItemImpl(Instorage instorage) {

		CheckingInItemImpl item = new CheckingInItemImpl();
		item.setSheetId(instorage.getRECID());
		item.setCreateDate(instorage.getCreateDate());
		item.setPlanCheckinDate(instorage.getCheckinDate());
		item.setRelaBillsNo(instorage.getRelaBillsNo());
		item.setLastCheckinDate(0);
		item.setStoreId(instorage.getStoreId());
		item.setStoreName(instorage.getStoreName());
		item.setCheckinEmployees("");
		if (instorage.isStoped()) {
			item.setStatus(CheckingInStatus.Stop);
		} else {
			item.setStatus(CheckingInStatus.getCheckingInType(instorage.getStatus()));
		}
		item.setType(CheckingInType.getCheckingInType(instorage.getSheetType()));
		item.setRemark(instorage.getRemark());
		return item;
	}

	/**
	 * 出库单详情
	 * 
	 * @param outstorage
	 * @param itemList
	 * @param logList
	 * @return CheckingOutInfoImpl
	 */
	public static CheckingOutInfoImpl getCheckingOutInfoImpl(Context context, Outstorage outstorage,
			List<OutstorageItem> itemList, List<CheckOutLog> logList) {
		CheckingOutInfoImpl impl = new CheckingOutInfoImpl();
		impl.setSheetId(outstorage.getRECID());
		// impl.setSheetNumber(outstorage.getOutstoNo());
		impl.setCreateDate(outstorage.getCreateDate());
		impl.setPlanCheckoutDate(outstorage.getCheckoutDate());
		impl.setLastCheckoutDate(getLatestCheckOutDate(logList));
		impl.setRelaBillsNo(outstorage.getRelaBillsNo());
		impl.setStoreId(outstorage.getStoreId());
		impl.setStoreName(outstorage.getStoreName());
		if (outstorage.isStoped()) {
			impl.setStatus(CheckingOutStatus.Stop);
			impl.setStopReason(outstorage.getStopReason());
		} else {
			impl.setStatus(CheckingOutStatus.getCheckingInType(outstorage.getStatus()));
		}
		impl.setType(CheckingOutType.getCheckingOutType(outstorage.getSheetType()));
		impl.setPartnerId(outstorage.getPartnerId());
		impl.setPartnerName(outstorage.getPartnerShortName());
		impl.setRemark(outstorage.getRemark());
		// impl.setKitSource(outstorage.getGoodsFrom());
		// impl.setEffect(outstorage.getGoodsUse());

		if (CheckingOutType.Kit.getCode().equals(outstorage.getSheetType())) {
			CheckKitItemImpl[] CheckKitItems = new CheckKitItemImpl[itemList.size()];
			for (int itemIndex = 0; itemIndex < itemList.size(); itemIndex++) {
				CheckKitItemImpl tempItem = new CheckKitItemImpl();
				tempItem.setCount((int) itemList.get(itemIndex).getCheckoutCount());
				tempItem.setKitDescription(itemList.get(itemIndex).getGoodsSpec());
				tempItem.setKitName(itemList.get(itemIndex).getGoodsName());
				tempItem.setUnit(itemList.get(itemIndex).getUnit());
				CheckKitItems[itemIndex] = tempItem;
			}
			impl.setCheckKitItems(CheckKitItems);
		} else {
			CheckingGoodsItemImpl[] checkingGoodsItems = new CheckingGoodsItemImpl[itemList.size()];
			for (int itemIndex = 0; itemIndex < itemList.size(); itemIndex++) {
				CheckingGoodsItemImpl tempItem = new CheckingGoodsItemImpl();
				tempItem.setCheckCount(itemList.get(itemIndex).getThisTimeCount());
				tempItem.setCheckedCount(itemList.get(itemIndex).getCheckoutCount());
				tempItem.setCheckingCount(itemList.get(itemIndex).getPlanCount());
				tempItem.setPrice(itemList.get(itemIndex).getPrice());
				tempItem.setGoodsItemId(itemList.get(itemIndex).getGoodsId());
				tempItem.setId(itemList.get(itemIndex).getRECID());
				tempItem.setCountDecimal(itemList.get(itemIndex).getScale());
				checkingGoodsItems[itemIndex] = tempItem;
			}
			impl.setCheckingGoodsItems(checkingGoodsItems);
		}

		CheckedOutItemImpl[] checkedOutItems = new CheckedOutItemImpl[logList.size()];
		for (int logIndex = 0; logIndex < logList.size(); logIndex++) {
			CheckedOutItemImpl tempLog = new CheckedOutItemImpl();
			tempLog.setCheckOutDate(logList.get(logIndex).getCheckOutDate());
			// tempLog
			// .setCheckelogList.get(logIndex)
			// .getKeeper());
			tempLog.setTaker(logList.get(logIndex).getTaker());
			tempLog.setTakerUnit(logList.get(logIndex).getTakerUnit());
			tempLog.setVouchersNumber(logList.get(logIndex).getVouchersNumber());
			checkedOutItems[logIndex] = tempLog;
		}
		impl.setCheckedOutItems(checkedOutItems);
		return impl;
	}

	/**
	 * 出库单列表Item
	 * 
	 * @param outstorage
	 * @return CheckingOutItem
	 */
	public static CheckingOutItem getCheckingOutItemImpl(Outstorage outstorage) {

		CheckingOutItemImpl impl = new CheckingOutItemImpl();
		impl.setSheetId(outstorage.getRECID());
		// impl.setSheetNumber(outstorage.getOutstoNo());
		impl.setCreateDate(outstorage.getCreateDate());
		impl.setPlanCheckoutDate(outstorage.getCheckoutDate());
		impl.setRelatedNumber(outstorage.getRelaBillsNo());
		impl.setStoreId(outstorage.getStoreId());
		impl.setStoreName(outstorage.getStoreName());
		impl.setCheckoutEmployees(outstorage.getCheckoutString());
		if (outstorage.isStoped()) {
			impl.setStatus(CheckingOutStatus.Stop);
		} else {
			impl.setStatus(CheckingOutStatus.getCheckingInType(outstorage.getStatus()));
		}
		impl.setType(CheckingOutType.getCheckingOutType(outstorage.getSheetType()));
		return impl;
	}

	/**
	 * 商品库存详细数据
	 * 
	 * @param context
	 * 
	 * @param goodsInventory
	 * @param adviseCount
	 * @return GoodsInventoryDetail
	 */
	public static InventoryInfo getGoodsInventoryDetailImpl(Context context, Inventory goodsInventory,
			double adviseCount, MaterialsItem goods) {
		InventoryInfoImpl impl = new InventoryInfoImpl();
		impl.setGoodsItemId(goodsInventory.getStockId());
		impl.setStoreId(goodsInventory.getStoreId());
		impl.setCount(goodsInventory.getCount());
		impl.setAmount(goodsInventory.getAmount());
		impl.setDeliveryingCount(DoubleUtil.sum(goodsInventory.getToDeliverCount(), goodsInventory.getLockedCount()));
		impl.setUpperLimitCount(goodsInventory.getUpperLimitCount());
		impl.setUpperLimitAmount(goodsInventory.getUpperLimitAmount());
		impl.setLowerLimitCount(goodsInventory.getLowerLimitCount());
		GetPurchaseOrderGoodsCountByGoodsIdKey oKey = new GetPurchaseOrderGoodsCountByGoodsIdKey(goodsInventory
				.getStockId(), goodsInventory.getStoreId());
		Double orderCount = context.find(Double.class, oKey);
		double purchasingCount = goodsInventory.getOnWayCount();
		if (null != orderCount) {
			purchasingCount = DoubleUtil.sum(orderCount, goodsInventory.getOnWayCount());
		}
		impl.setPurchasingCount(purchasingCount);
		impl.setAdviseCount(adviseCount);
		if (null == goods) {
			goods = context.find(MaterialsItem.class, goodsInventory.getId());
		}
		if (null != goods) {
			impl.setGoodsName(goods.getMaterialName());
			impl.setGoodsProperties(goods.getSpec());
			impl.setGoodsUnit(goods.getMaterialUnit());
			impl.setCountDecimal(goods.getScale());
			impl.setGoodsCode(goods.getMaterialCode());
			impl.setGoodsNo(goods.getMaterialNo());
		}
		Store store = context.find(Store.class, goodsInventory.getStoreId());
		if (null != store) {
			impl.setStoreName(store.getName());
		}
		return impl;
	}

	/**
	 * 一个商品条目的所有库存情况
	 * 
	 * @param goodsInventoryList
	 * @return GoodsOrKitInventorySummary
	 */
	public final static GoodsOrKitInventorySummary getGoodsInventorySummary(Context context,
			List<Inventory> goodsInventoryList) {
		GoodsOrKitInventorySummaryImpl impl = new GoodsOrKitInventorySummaryImpl();
		SummaryItemImpl[] itemImpls = new SummaryItemImpl[goodsInventoryList.size()];
		for (int index = 0; index < goodsInventoryList.size(); index++) {
			Inventory goodsInventory = goodsInventoryList.get(index);
			SummaryItemImpl itemImpl = new SummaryItemImpl();
			itemImpl.setCount(goodsInventory.getCount());
			itemImpl.setStoreId(goodsInventory.getStoreId());
			itemImpl.setStoreName(context.find(Store.class, goodsInventory.getStoreId()).getName());
			itemImpls[index] = itemImpl;
		}
		impl.setItems(itemImpls);
		return impl;
	}

	/**
	 * 一个物品的所有库存情况
	 * 
	 * @param context
	 * 
	 * @param list
	 * @return GoodsOrKitInventorySummary
	 */
	public static GoodsOrKitInventorySummary getKitInventorySummary(Context context, List<OthersInventory> list) {
		GoodsOrKitInventorySummaryImpl impl = new GoodsOrKitInventorySummaryImpl();
		SummaryItemImpl[] items = new SummaryItemImpl[list.size()];
		for (int index = 0; index < list.size(); index++) {
			OthersInventory goodsInventory = list.get(index);
			SummaryItemImpl itemImpl = new SummaryItemImpl();
			itemImpl.setCount(goodsInventory.getGoodsCount());
			itemImpl.setStoreId(goodsInventory.getStoreGuid());
			Store store = null;
			if (null != goodsInventory.getStoreGuid())
				;
			store = context.find(Store.class, goodsInventory.getStoreGuid());
			if (null != store)
				itemImpl.setStoreName(store.getName());
			items[index] = itemImpl;
		}
		impl.setItems(items);
		return impl;
	}

	/**
	 * 调拨单详细信息
	 * 
	 * @param context
	 * 
	 * @param allocateInventory
	 * @param itemList
	 * @return InventoryAllocateSheetInfo
	 */
	public static InventoryAllocateSheetInfo getInventoryAllocateSheetInfo(Context context,
			AllocateInventory allocateInventory, List<AllocateInventoryItem> itemList) {
		InventoryAllocateSheetInfoImpl impl = new InventoryAllocateSheetInfoImpl();

		impl.setAllocateInDate(allocateInventory.getAllocateInDate());
		impl.setAllocateOutDate(allocateInventory.getAllocateOutDate());
		impl.setCause(allocateInventory.getAllocateReason());
		impl.setDanyCause(allocateInventory.getRejectReason());
		impl.setCreateDate(allocateInventory.getCreateDate());
		impl.setCreatorId(allocateInventory.getCreatorId());
		impl.setCreatorName(allocateInventory.getCreator());
		impl.setDestinationStoreId(allocateInventory.getAllocateInStoreId());
		impl.setDestinationStoreName(allocateInventory.getAllocateInStoreName());
		impl.setSheetId(allocateInventory.getId());
		impl.setSheetNumber(allocateInventory.getAllocSheetNo());
		impl.setSourceStoreId(allocateInventory.getAllocateOutStoreId());
		impl.setSourceStoreName(allocateInventory.getAllocateOutStoreName());
		impl.setStatus(InventoryAllocateStatus.getStatusByCode(allocateInventory.getStatus()));
		impl.setApproveDate(allocateInventory.getApplyDate());
		impl.setApprovePerson(allocateInventory.getApprovePerson());
		impl.setApprovePersonId(allocateInventory.getApprovePersonId());
		impl.setAllocateInName(allocateInventory.getAllocateInPersonName());
		impl.setAllocateOutName(allocateInventory.getAllocateOutPersonName());

		AllocateGoodsItemImpl[] items = new AllocateGoodsItemImpl[itemList.size()];
		for (int index = 0; index < itemList.size(); index++) {
			AllocateInventoryItem item = itemList.get(index);
			AllocateGoodsItemImpl itemImpl = new AllocateGoodsItemImpl();
			itemImpl.setAllocateCount(item.getAllocateCount());
			ResourceToken<Inventory> token = context.findResourceToken(Inventory.class, allocateInventory
					.getAllocateOutStoreId(), item.getStockId());
			if (null != token) {
				itemImpl
						.setAvailableCount((token.getFacade().getCount() - token.getFacade().getLockedCount()) > 0 ? (token
								.getFacade().getCount() - token.getFacade().getLockedCount())
								: 0);
			}
			// itemImpl.setAvailableCount(item.getAbleCount());
			itemImpl.setId(item.getId());
			itemImpl.setCountDecimal(item.getStockScale());
			itemImpl.setGoodsItemCode(item.getStockCode());
			itemImpl.setStockNumber(item.getStockNo());
			itemImpl.setGoodsItemId(item.getStockId());
			itemImpl.setGoodsItemName(item.getStockName());
			itemImpl.setGoodsItemProperties(item.getStockSpec());
			itemImpl.setGoodsItemUnit(item.getUnit());
			items[index] = itemImpl;
		}
		impl.setItems(items);
		return impl;
	}

	/**
	 * 调拨单列表信息
	 * 
	 * @param allocateInventory
	 * @return InventoryAllocateSheetItem
	 */
	public static InventoryAllocateSheetItem getAllocateInventory(AllocateInventory allocateInventory) {
		InventoryAllocateSheetItemImpl impl = new InventoryAllocateSheetItemImpl();
		impl.setAllocateInDate(allocateInventory.getAllocateInDate());
		impl.setAllocateOutDate(allocateInventory.getAllocateOutDate());
		impl.setCreateDate(allocateInventory.getCreateDate());
		impl.setCreatorId(allocateInventory.getCreatorId());
		impl.setCreatorName(allocateInventory.getCreator());
		impl.setDestinationStoreId(allocateInventory.getAllocateInStoreId());
		impl.setDestinationStoreName(allocateInventory.getAllocateInStoreName());
		impl.setSheetId(allocateInventory.getId());
		impl.setSheetNumber(allocateInventory.getAllocSheetNo());
		impl.setSourceStoreId(allocateInventory.getAllocateOutStoreId());
		impl.setSourceStoreName(allocateInventory.getAllocateOutStoreName());
		impl.setStatus(InventoryAllocateStatus.getStatusByCode(allocateInventory.getStatus()));
		impl.setApprovePerson(allocateInventory.getApprovePerson());
		return impl;
	}

	/**
	 * 盘点单详细信息
	 * 
	 * @param context
	 * 
	 * @param checkInventory
	 * @param list
	 * @return InventoryCountSheetInfo
	 */
	public static InventoryCountSheetInfo getInventoryCountSheetInfo(Context context, CheckInventory checkInventory,
			List<CheckInventoryItem> list) {
		InventoryCountSheetInfoImpl impl = new InventoryCountSheetInfoImpl();
		impl.setCreatorName(checkInventory.getCreatePerson());
		impl.setEndDate(checkInventory.getEndDate());
		impl.setMemo(checkInventory.getRemark());
		impl.setSheetId(checkInventory.getRecid());
		impl.setSheetNumber(checkInventory.getCheckOrdNo());
		impl.setSheetstatus(InventoryCountStatus.getStatusByCode(checkInventory.getCheckOrdState()));
		impl.setSheetType(InventoryCountType.getTypeByCode(checkInventory.getCheckObj()));
		impl.setStartDate(checkInventory.getStartDate());
		impl.setStoreId(checkInventory.getStoreGuid());
		impl.setStoreName(checkInventory.getStoreName());
		impl.setName(checkInventory.getCheckPerson());

		List<CheckInventoryItem> rList = new ArrayList<CheckInventoryItem>();
		if (InventoryCountStatus.Counted.getCode().equals(checkInventory.getCheckOrdState())) {
			for (CheckInventoryItem item : list) {
				if (!item.getCarryCount().equals(item.getRealCount())) {
					rList.add(item);
				}
			}
		} else {
			rList.addAll(list);
		}
		if (InventoryCountType.Materials.getCode().equals(checkInventory.getCheckObj())) {
			if (list.size() > 0) {
				GoodsCountItemImpl[] goodsCountItems = new GoodsCountItemImpl[rList.size()];
				for (int index = 0; index < rList.size(); index++) {
					CheckInventoryItem checkInventoryItem = rList.get(index);
					GoodsCountItemImpl itemImpl = new GoodsCountItemImpl();
					itemImpl.setActualCount(checkInventoryItem.getRealCount());
					itemImpl.setCount(checkInventoryItem.getCarryCount());
					itemImpl.setCountDecimal(checkInventoryItem.getGoodsScale());
					itemImpl.setGoodsCode(checkInventoryItem.getGoodsItemCode());
					itemImpl.setGoodsNo(checkInventoryItem.getGoodsItemNo());
					itemImpl.setGoodsItemId(checkInventoryItem.getGoodsGuid());
					itemImpl.setGoodsItemName(checkInventoryItem.getGoodsName());
					itemImpl.setGoodsItemProperties(checkInventoryItem.getGoodsAttr());
					itemImpl.setGoodsItemUnit(checkInventoryItem.getUnit());
					itemImpl.setMemo(checkInventoryItem.getRemark());

					ResourceToken<Inventory> token = context.findResourceToken(Inventory.class, checkInventory
							.getStoreGuid(), checkInventoryItem.getGoodsGuid());
					if (null != token) {
						itemImpl.setExistInventory(true);
					}
					goodsCountItems[index] = itemImpl;
				}
				impl.setGoodsCountItems(goodsCountItems);
			} else {
				GetGoodsInventoryByStoreIdKey key = new GetGoodsInventoryByStoreIdKey(checkInventory.getStoreGuid());
				List<Inventory> detList = context.getList(Inventory.class, key);
				if (null != detList && detList.size() > 0) {
					GoodsCountItemImpl[] goodsCountItems = new GoodsCountItemImpl[detList.size()];
					for (int index = 0; index < detList.size(); index++) {
						Inventory checkInventoryItem = detList.get(index);
						GoodsCountItemImpl itemImpl = new GoodsCountItemImpl();
						itemImpl.setActualCount(checkInventoryItem.getCount());
						itemImpl.setCount(checkInventoryItem.getCount());
						itemImpl.setGoodsItemId(checkInventoryItem.getStockId());
						MaterialsItemInfo goods = context
								.find(MaterialsItemInfo.class, checkInventoryItem.getStockId());
						if (null != goods) {
							itemImpl.setGoodsCode(goods.getBaseInfo().getCode());
							itemImpl.setGoodsNo(goods.getItemData().getMaterialNo());
							itemImpl.setGoodsItemName(goods.getBaseInfo().getName());
							itemImpl.setGoodsItemProperties(goods.getItemData().getMaterialSpec());
							itemImpl.setGoodsItemUnit(goods.getItemData().getUnit());
							itemImpl.setCountDecimal(goods.getItemData().getScale());
						}
						itemImpl.setExistInventory(true);
						goodsCountItems[index] = itemImpl;
					}
					impl.setGoodsCountItems(goodsCountItems);
				}
			}

		} else if (InventoryCountType.Kit.getCode().equals(checkInventory.getCheckObj())) {
			List<KitInventoryItem> kitInventoryItemList = context.getList(KitInventoryItem.class, checkInventory
					.getStoreGuid());
			if (list.size() > 0) {
				KitCountItemImpl[] kitCountItems = new KitCountItemImpl[rList.size()];
				for (int index = 0; index < rList.size(); index++) {
					CheckInventoryItem checkInventoryItem = rList.get(index);
					KitCountItemImpl itemImpl = new KitCountItemImpl();
					itemImpl.setActualCount(checkInventoryItem.getRealCount());
					itemImpl.setCount(checkInventoryItem.getCarryCount());
					itemImpl.setKitDesc(checkInventoryItem.getGoodsAttr());
					itemImpl.setKitName(checkInventoryItem.getGoodsName());
					itemImpl.setKitUnit(checkInventoryItem.getUnit());
					itemImpl.setMemo(checkInventoryItem.getRemark());
					if (CheckIsNull.isNotEmpty(kitInventoryItemList)) {
						for (KitInventoryItem kitInventoryItem : kitInventoryItemList) {
							if (isExist(kitInventoryItem, checkInventoryItem)) {
								itemImpl.setExistInventory(true);
								break;
							}
						}
					}

					kitCountItems[index] = itemImpl;
				}
				impl.setKitCountItems(kitCountItems);
			}
			// 全部库存物品
			else {
				GetKitInventoryDetailListKey key = new GetKitInventoryDetailListKey(checkInventory.getStoreGuid(), 0,
						100000000, false);
				List<KitInventoryDetail> detList = context.getList(KitInventoryDetail.class, key);
				if (detList.size() > 0) {
					KitCountItemImpl[] kitCountItems = new KitCountItemImpl[detList.size()];
					for (int index = 0; index < detList.size(); index++) {
						KitInventoryDetail checkInventoryItem = detList.get(index);
						KitCountItemImpl itemImpl = new KitCountItemImpl();
						itemImpl.setActualCount(checkInventoryItem.getCount());
						itemImpl.setCount(checkInventoryItem.getCount());
						itemImpl.setKitDesc(checkInventoryItem.getKitDesc());
						itemImpl.setKitName(checkInventoryItem.getKitName());
						itemImpl.setKitUnit(checkInventoryItem.getUnit());
						itemImpl.setExistInventory(true);

						kitCountItems[index] = itemImpl;
					}
					impl.setKitCountItems(kitCountItems);
				}
			}
		}
		return impl;
	}

	private static boolean isExist(KitInventoryItem kitInventoryItem, CheckInventoryItem checkInventoryItem) {

		return kitInventoryItem.getKitName().equals(checkInventoryItem.getGoodsName())
				&& kitInventoryItem.getKitDescription().equals(checkInventoryItem.getGoodsAttr())
				&& kitInventoryItem.getUnit().equals(checkInventoryItem.getUnit());
	}

	/**
	 * 盘点单列表信息
	 * 
	 * @param checkInventory
	 * @return InventoryCountSheetItem
	 */
	public static InventoryCountSheetItem getInventoryCountSheetItem(CheckInventory checkInventory) {
		InventoryCountSheetItemImpl impl = new InventoryCountSheetItemImpl();
		impl.setCountLoss(checkInventory.getCheckDeficient());
		impl.setCountProfit(checkInventory.getCheckProfit());
		impl.setEndDate(checkInventory.getEndDate());
		impl.setSheetId(checkInventory.getRecid());
		impl.setSheetNumber(checkInventory.getCheckOrdNo());
		impl.setSheetstatus(InventoryCountStatus.getStatusByCode(checkInventory.getCheckOrdState()));
		impl.setStartDate(checkInventory.getStartDate());
		impl.setStoreId(checkInventory.getStoreGuid());
		impl.setStoreName(checkInventory.getStoreName());
		impl.setType(InventoryCountType.getTypeByCode(checkInventory.getCheckObj()));

		return impl;
	}

	/**
	 * 指定仓库或全部仓库物品库存信息
	 * 
	 * @param othersInventory
	 * @return KitInventoryDetail
	 */
	public static KitInventoryDetail getKitInventoryDetail(OthersInventory othersInventory) {
		KitInventoryDetailImpl impl = new KitInventoryDetailImpl();
		impl.setCount(othersInventory.getGoodsCount());
		impl.setKitDesc(othersInventory.getGoodsProperty());
		impl.setKitName(othersInventory.getGoodsName());
		impl.setUnit(othersInventory.getGoodsUnit());
		return impl;
	}

	/**
	 * 获得指定入库记录列表中，最晚入库的时间
	 * 
	 * @param logList
	 * @return
	 */
	public static long getLatestCheckInDate(List<CheckInLog> logList) {
		if (null == logList) {
			return 0;
		}
		long latestTime = 0;
		for (CheckInLog checkInLog : logList) {
			if (checkInLog.getCheckInDate() > latestTime) {
				latestTime = checkInLog.getCheckInDate();
			}
		}
		return latestTime;
	}

	/**
	 * 获得指定出库记录列表中，最晚出库的时间
	 * 
	 * @param logList
	 * @return
	 */
	public static long getLatestCheckOutDate(List<CheckOutLog> logList) {
		long latestTime = 0;
		for (CheckOutLog checkOutLog : logList) {
			if (checkOutLog.getCheckOutDate() > latestTime) {
				latestTime = checkOutLog.getCheckOutDate();
			}
		}
		return latestTime;
	}

	/**
	 * 库存流水列表
	 * 
	 * @param stoStream
	 * @return InventoryLogItem
	 */
	public static InventoryLogItem getInventoryLogItem(InventoryLogEntity stoStream) {
		InventoryLogItemImpl impl = new InventoryLogItemImpl();
		impl.setId(stoStream.getId());
		impl.setCheckedInAmount(stoStream.getInstoAmount());
		impl.setCheckedInCount(stoStream.getInstoCount());
		impl.setCheckedOutAmount(stoStream.getOutstoAmount());
		impl.setCheckedOutCount(stoStream.getOutstoCount());
		impl.setGoodsItemId(stoStream.getStockId());
		impl.setGoodsItemCode(stoStream.getCode());
		impl.setGoodsItemName(stoStream.getName());
		impl.setGoodsItemProperties(stoStream.getProperties());
		impl.setGoodsItemUnit(stoStream.getUnit());
		impl.setLogType(InventoryLogType.getEnum(stoStream.getLogType()));
		impl.setOccorDate(stoStream.getCreatedDate());
		impl.setRelatedNumber(stoStream.getOrderNo());
		impl.setCountDecimal(stoStream.getScale());
		impl.setGoodsItemNumber(stoStream.getStockNo());
		return impl;
	}

	/**
	 * 更新商品库存资源
	 * 
	 * @param context
	 * @param goodsInventoryList
	 *            void
	 */
	public static void updateResource(Context context, InventoryEntity goodsInventoryEntity, boolean isInsert) {
		if (InventoryType.Others.getCode().equals(goodsInventoryEntity.getInventoryType())) {
			return;
		}
		InventoryResourceTask rTask = new InventoryResourceTask(goodsInventoryEntity);
		if (isInsert) {
			context.handle(rTask, InventoryResourceTask.Type.INSERT);
		} else {
			context.handle(rTask, InventoryResourceTask.Type.UPDATE);
		}

	}

	/**
	 * 填充确认入库记录实体
	 * 
	 * @param rs
	 * @return CheckInLog
	 */
	public static CheckInLog fillCheckInLog(RecordSet rs) {
		CheckInLog log = new CheckInLog();
		int index = 0;
		log.setId(rs.getFields().get(index++).getGUID());
		log.setCheckInSheetId(rs.getFields().get(index++).getGUID());
		log.setKeeper(rs.getFields().get(index++).getString());
		log.setCheckInDate(rs.getFields().get(index++).getLong());
		log.setCheckInCount(rs.getFields().get(index++).getDouble());
		log.setCheckInAmount(rs.getFields().get(index++).getDouble());
		return log;
	}

	/**
	 * 填充确认出库记录实体
	 * 
	 * @param rs
	 * @return CheckOutLog
	 */
	public static CheckOutLog fillCheckOutLog(RecordSet rs) {

		CheckOutLog log = new CheckOutLog();
		int index = 0;
		log.setCheckOutSheetId(rs.getFields().get(index++).getGUID());
		log.setKeeper(rs.getFields().get(index++).getString());
		log.setCheckOutDate(rs.getFields().get(index++).getLong());
		// log.setCheckOutCount(rs.getFields().get(index++).getDouble());
		log.setCheckOutAmount(rs.getFields().get(index++).getDouble());
		log.setTaker(rs.getFields().get(index++).getString());
		log.setTakerUnit(rs.getFields().get(index++).getString());
		log.setVouchersNumber(rs.getFields().get(index++).getString());

		return log;
	}

	/**
	 * 调整成本时写流水
	 * 
	 * @param context
	 * @param list
	 * @param avgInventoryCost
	 *            void
	 */
	public static void insertInventoryLog(Context context, AdjustGoodsItemCostTask cTask) {
		StoStreamTask task = new StoStreamTask();

		ResourceToken<Inventory> token = context.findResourceToken(Inventory.class, cTask.getStoreId(), cTask
				.getGoodsItemId());

		List<InventoryLogEntity> stoStreams = new ArrayList<InventoryLogEntity>();
		InventoryLogEntity sto = new InventoryLogEntity();
		sto.setId(context.newRECID());
		sto.setStoreId(cTask.getStoreId());
		sto.setStockId(cTask.getGoodsItemId());
		sto.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId()).getName());
		sto.setCreatedDate(new Date().getTime());
		MaterialsItem goods = context.find(MaterialsItem.class, cTask.getGoodsItemId());
		if (null != goods) {
			sto.setCategoryId(goods.getCategoryId());
			sto.setName(goods.getMaterialName());
			sto.setProperties(MaterialsProperyUtil.subMaterialsPropertyToString(goods.getMaterialProperties()));
			sto.setUnit(goods.getMaterialUnit());
			sto.setCode(goods.getMaterialCode());
			sto.setStockNo(goods.getMaterialNo());
			sto.setScale(goods.getScale());
			sto.setInventoryType(InventoryType.Materials.getCode());
		}
		sto.setLogType(InventoryLogType.AdjustInventoryCost.getCode());
		sto.setCreatedDate(new Date().getTime());
		sto.setInstoCount(0);
		double amount = DoubleUtil.sub(DoubleUtil.mul(token.getFacade().getCount(), cTask.getNewCost(), 2), token
				.getFacade().getAmount());
		sto.setInstoAmount(amount);
		stoStreams.add(sto);

		task.setList(stoStreams);
		context.handle(task, StoStreamTask.Task.add);
	}

	/**
	 * 获取订单相关入库单带明细列表
	 * 
	 * @param context
	 * @param instorage
	 *            void
	 */
	public static OrderCheckSheetItem getOrderCheckInSheetItem(Context context, Instorage instorage) {
		OrderCheckSheetItemImpl impl = new OrderCheckSheetItemImpl();
		impl.setCheckSerialNumber("");
		impl.setPlayCheckDate(instorage.getCheckinDate());
		impl.setStoreName(instorage.getStoreName());
		List<InstorageItem> itemList = context.getList(InstorageItem.class, instorage.getRECID());
		if (CheckIsNull.isNotEmpty(itemList)) {
			OrderCheckSheetItem.Item[] items = new OrderCheckSheetItem.Item[itemList.size()];
			int i = 0;
			for (InstorageItem item : itemList) {
				OrderCheckSheetItem.Item ii = new OrderCheckSheetItem.Item();
				ii.setCheckedCount(item.getCount());
				ii.setGoodsInfo(item.getGoodsName() + item.getGoodsSpec());
				ii.setOrderCount(item.getCount());
				ii.setPrice(item.getPrice());
				ii.setCountDecimal(item.getScale());
				items[i] = ii;
				i++;
			}
			impl.setItems(items);
		}
		return impl;
	}

	/**
	 * 获取订单相关出库单带明细列表
	 * 
	 * @param context
	 * @param outstorage
	 * @return OrderCheckSheetItem
	 */
	public static OrderCheckSheetItem getOrderCheckOutSheetItem(Context context, Outstorage outstorage) {
		OrderCheckSheetItemImpl impl = new OrderCheckSheetItemImpl();
		// impl.setCheckSerialNumber(outstorage.getOutstoNo());
		impl.setPlayCheckDate(outstorage.getCheckoutDate());
		impl.setStoreName(outstorage.getStoreName());
		List<OutstorageItem> itemList = context.getList(OutstorageItem.class, outstorage.getRECID());
		if (CheckIsNull.isNotEmpty(itemList)) {
			OrderCheckSheetItem.Item[] items = new OrderCheckSheetItem.Item[itemList.size()];
			int i = 0;
			for (OutstorageItem item : itemList) {
				OrderCheckSheetItem.Item ii = new OrderCheckSheetItem.Item();
				ii.setCheckedCount(item.getCheckoutCount());
				ii.setGoodsInfo(item.getGoodsName() + item.getGoodsSpec());
				ii.setOrderCount(item.getPlanCount());
				ii.setPrice(item.getPrice());
				ii.setCountDecimal(item.getScale());
				items[i] = ii;
				i++;
			}
			impl.setItems(items);
		}
		return impl;
	}

}
