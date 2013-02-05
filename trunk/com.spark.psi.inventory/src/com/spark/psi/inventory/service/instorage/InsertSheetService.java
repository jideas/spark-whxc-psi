package com.spark.psi.inventory.service.instorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.account.intf.task.pub.CusdealTask;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Partner;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.internal.entity.OtherGoods;
import com.spark.psi.inventory.intf.entity.instorage.mod.CheckInSheet;
import com.spark.psi.inventory.intf.entity.instorage.mod.CheckInSheetItem;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.inventory.intf.event.CheckInEvent;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.task.instorage.CheckinSheetTask;
import com.spark.psi.inventory.intf.task.instorage.InstoAddTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryOnWayTask;
import com.spark.psi.inventory.intf.task.inventory.StoStreamTask;
import com.spark.psi.inventory.intf.task.inventory.UpdateOtherGoodsTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask.DetItem;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.account.task.CreatePaymentTask;
import com.spark.psi.publish.account.task.CreatePaymentTask.Item;
import com.spark.psi.publish.base.bom.entity.BomInfo;
import com.spark.psi.publish.base.bom.entity.BomInfoItem;
import com.spark.psi.publish.inventory.entity.AllreadyAmountAndCount;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItemDet;
import com.spark.psi.publish.inventory.key.GetBillsAllreadyAmountAndCountKey;
import com.spark.psi.publish.inventory.sheet.task.AdjustCheckinTask;
import com.spark.psi.publish.inventory.sheet.task.AdjustCheckinTaskItem;
import com.spark.psi.publish.inventory.sheet.task.CheckInKitTask;
import com.spark.psi.publish.inventory.sheet.task.CheckInKitTaskItem;
import com.spark.psi.publish.inventory.sheet.task.CheckInSheetTaskItem;
import com.spark.psi.publish.inventory.sheet.task.GiftCheckinTask;
import com.spark.psi.publish.inventory.sheet.task.GiftCheckinTaskItem;
import com.spark.psi.publish.inventory.sheet.task.JointCheckinTask;
import com.spark.psi.publish.inventory.sheet.task.JointCheckinTaskItem;
import com.spark.psi.publish.inventory.sheet.task.RealGoodsCheckinTask;
import com.spark.psi.publish.inventory.sheet.task.RealGoodsCheckinTaskItem;
import com.spark.psi.publish.inventory.sheet.task.SureCheckInTask;
import com.spark.psi.publish.inventory.sheet.task.SureCheckInTask.CheckInError;

public class InsertSheetService extends Service {

	protected InsertSheetService() {
		super("com.spark.psi.inventory.service.instorage.InsertSheetService");
	}

	/**
	 * 成品入库
	 */
	@Publish
	protected class RealGoodsCheckinTaskHandle extends SimpleTaskMethodHandler<RealGoodsCheckinTask> {

		@Override
		protected void handle(Context context, RealGoodsCheckinTask task) throws Throwable {
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());
			CheckInSheet sheet = new CheckInSheet();
			List<CheckInSheetItem> items = new ArrayList<CheckInSheetItem>();
			sheet.setRECID(context.newRECID());
			sheet.setRelaBillsId(task.getRelaBillsId());
			sheet.setRelaBillsNo(task.getRelaBillsNo());
			sheet.setCheckinDate(System.currentTimeMillis());
			sheet.setCheckinPerson(user.getId());
			sheet.setCheckinPersonName(user.getName());
			sheet.setCreator(user.getName());
			sheet.setCreatorId(user.getId());
			sheet.setDeptId(user.getDepartmentId());
			sheet.setStoreId(task.getStoreId());
			sheet.setRemark(task.getRemark());
			Store store = context.find(Store.class, task.getStoreId());
			String sheetNo = context.get(String.class, SheetNumberType.Checkin);
			sheet.setSheetNo(sheetNo);
			sheet.setSheetType(CheckingInType.RealGoods.getCode());
			sheet.setStoreName(store.getName());
			sheet.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
			double amount = 0;
			for (RealGoodsCheckinTaskItem item : task.getItems()) {
				CheckInSheetItem det = new CheckInSheetItem();
				det.setRECID(context.newRECID());
				det.setGoodsName(item.getGoodsName());
				det.setGoodsSpec(item.getGoodsSpec());
				det.setUnit(item.getGoodsUnit());
				det.setRealCount(item.getCount());
				det.setSheetId(sheet.getRECID());
				det.setGoodsCode(item.getGoodsCode());
				det.setGoodsId(item.getGoodsId());
				det.setGoodsNo(item.getGoodsNo());
				det.setScale(item.getGoodsScale());
				double itemPrice = 0, itemAmount = 0;
				GoodsItem goods = context.find(GoodsItem.class, det.getGoodsId());
				BomInfo bom = context.find(BomInfo.class, goods.getBomId());
				for (BomInfoItem bi : bom.getBomInfoItems()) {
					MaterialsItem mi = context.find(MaterialsItem.class, bi.getMaterialId());
					itemPrice = DoubleUtil.sum(itemPrice, DoubleUtil.mul(mi.getAvgBuyPrice(), bi.getRealCount()));
				}
				itemAmount = DoubleUtil.mul(itemPrice, det.getRealCount());
				det.setPrice(itemPrice);
				det.setAmount(itemAmount);
				amount = DoubleUtil.sum(item.getAmount(), amount);
				items.add(det);
			}
			sheet.setAmount(amount);
			CheckinSheetTask tttt = new CheckinSheetTask();
			tttt.setEntity(sheet);
			tttt.setItems(items);
			context.handle(tttt);

			for (CheckInSheetItem item : items) {
				// 更新库存和成本
				modfiyMaterialsStorage(context, sheet.getStoreId(), item, null, InventoryType.Goods, CheckingInType
						.getCheckingInType(sheet.getSheetType()));
			}
			// 库存流水
			doWrightStream(context, sheet, items, InventoryType.Goods);
		}
	}

	/**
	 * 生成其他入库
	 */
	@Publish
	protected class InsertOnelotherBillsService extends SimpleTaskMethodHandler<CheckInKitTask> {

		@Override
		protected void handle(Context context, CheckInKitTask task) throws Throwable {
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());

			CheckInSheet sheet = new CheckInSheet();
			List<CheckInSheetItem> items = new ArrayList<CheckInSheetItem>();
			sheet.setRECID(context.newRECID());
			sheet.setCheckinDate(System.currentTimeMillis());
			sheet.setCheckinPerson(user.getId());
			sheet.setCheckinPersonName(user.getName());
			sheet.setCreator(user.getName());
			sheet.setCreatorId(user.getId());
			sheet.setDeptId(user.getDepartmentId());
			sheet.setGoodsFrom(task.getGoodsFrom());
			sheet.setStoreId(task.getStoreId());
			sheet.setRemark(task.getRemark());
			Store store = context.find(Store.class, task.getStoreId());
			String sheetNo = context.get(String.class, SheetNumberType.Checkin);
			sheet.setSheetNo(sheetNo);
			sheet.setSheetType(CheckingInType.Kit.getCode());
			sheet.setStoreName(store.getName());
			sheet.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
			for (CheckInKitTaskItem item : task.getItems()) {
				CheckInSheetItem det = new CheckInSheetItem();
				det.setRECID(context.newRECID());
				det.setGoodsName(item.getName());
				det.setGoodsSpec(item.getDescription());
				det.setUnit(item.getUnit());
				det.setRealCount(item.getCount());
				det.setSheetId(sheet.getRECID());
				items.add(det);
			}
			CheckinSheetTask tttt = new CheckinSheetTask();
			tttt.setEntity(sheet);
			tttt.setItems(items);
			context.handle(tttt);
			// 更新其他库存
			if (tttt.isSuccess()) {
				addOtherStorage(context, tttt);
			}
		}
	}

	/**
	 * 更新其他库存
	 */
	public void addOtherStorage(Context context, CheckinSheetTask tttt) {
		for (CheckInSheetItem det : tttt.getItems()) {
			OtherGoods otherGoods = new OtherGoods();
			otherGoods.setName(det.getGoodsName());
			otherGoods.setDescription(det.getGoodsSpec());
			otherGoods.setUnit(det.getUnit());
			otherGoods.setNumber(det.getRealCount());
			UpdateOtherGoodsTask otherTask = new UpdateOtherGoodsTask(tttt.getEntity().getStoreId(), otherGoods);
			otherTask.setInit(false);
			context.handle(otherTask, Method.MODIFY);
		}
	}

	/**
	 * 生成零星采购入库
	 */
	@Publish
	protected class InsertOneBillsService extends TaskMethodHandler<InstoAddTask, CheckingInType> {

		protected InsertOneBillsService() {
			super(CheckingInType.Irregular);
		}

		@Override
		protected void handle(Context context, InstoAddTask task) throws Throwable {
			if (null == task.getEntity()) {
				return;
			}
			CheckInSheet sheet = fillEntity(context, task.getEntity(), CheckingInType.Irregular.getCode());
			List<CheckInSheetItem> items = fillDetails(context, task.getDetailList(), sheet, null);
			CheckinSheetTask tttt = new CheckinSheetTask();
			tttt.setEntity(sheet);
			tttt.setItems(items);
			context.handle(tttt);
			if (!tttt.isSuccess()) {
				throw new Exception("生成入库单出现不可预知错误，请重试！");
			}
			for (CheckInSheetItem item : items) {
				// 更新库存和成本
				modfiyMaterialsStorage(context, sheet.getStoreId(), item, task.getDistributeInventoryItems(),
						InventoryType.Materials, CheckingInType.getCheckingInType(sheet.getSheetType()));
			}
			// 库存流水
			doWrightStream(context, sheet, items, InventoryType.Materials);
			// 应收应付
			doUpdateDealing(context, sheet, DealingsType.PRO_LXCG);
			// 生成付款单
			createPayBills(context, sheet);

			CheckInEvent event = new CheckInEvent(null);
			event.setCheckInLogId(sheet.getRECID());
			event.setRelaOrderId(sheet.getRelaBillsId());
			event.setType(CheckingInType.getCheckingInType(task.getEntity().getSheetType()));
			context.dispatch(event);
		}
	}

	/**
	 * 更新应收应付信息
	 */
	public void doUpdateDealing(Context context, CheckInSheet sheet, DealingsType type) {
		double amount = sheet.getAmount();
		if (type == DealingsType.CUS_THRK) {
			amount = DoubleUtil.sub(0, amount);
		}
		CusdealTask cTask = new CusdealTask(sheet.getPartnerId(), type, sheet.getAmount(), sheet.getPaidAmount(), sheet
				.getRECID(), sheet.getSheetNo());
		cTask.setPubdate(sheet.getCheckinDate());
		context.handle(cTask);
	}

	/**
	 * 创建付款记录
	 */
	public void createPayBills(Context context, CheckInSheet sheet) {
		CreatePaymentTask task = new CreatePaymentTask();
		task.setId(context.newRECID());
		task.setAmount(sheet.getAmount());
		task.setPartnerId(sheet.getPartnerId());
		task.setPartnerName(sheet.getPartnerName());
		task.setPayDate(System.currentTimeMillis());
		task.setPaymentType(PaymentType.PAY_LCFK.getCode());
		task.setRemark(sheet.getRemark());
		CreatePaymentTask.Item item = task.new Item(sheet.getRECID(), sheet.getSheetNo(), null, null, System
				.currentTimeMillis(), task.getAmount(), task.getAmount(), 0);
		task.setItems(new Item[] { item });
		task.setDealingsWay(DealingsWay.Cash.getCode());
		context.handle(task);
		System.err.println("创建付款记录出错：com.spark.psi.inventory.service.instorage.InsertSheetService.createPayBills");
	}

	/**
	 * 添加库存流水信息
	 * 
	 * @param items
	 * @param sheet
	 */
	public void doWrightStream(Context context, CheckInSheet sheet, List<CheckInSheetItem> items,
			InventoryType inventoryType) {
		// 库存流水
		StoStreamTask stream = new StoStreamTask();
		List<InventoryLogEntity> list = new ArrayList<InventoryLogEntity>();
		Login login = context.find(Login.class);
		Employee user = context.find(Employee.class, login.getEmployeeId());
		for (CheckInSheetItem item : items) {
			InventoryLogEntity sto = new InventoryLogEntity();
			if (inventoryType.equals(InventoryType.Materials)) {
				MaterialsItem goods = context.find(MaterialsItem.class, item.getGoodsId());
				sto.setCategoryId(goods.getCategoryId());
				sto.setProperties(goods.getSpec());
				sto.setScale(goods.getScale());
				sto.setUnit(goods.getMaterialUnit());
				sto.setStockNo(goods.getMaterialNo());
				sto.setName(goods.getMaterialName());
				sto.setStockId(goods.getId());
				sto.setCode(goods.getMaterialCode());

			} else {
				GoodsItem goods = context.find(GoodsItem.class, item.getGoodsId());
				sto.setCategoryId(goods.getCategoryId());
				sto.setProperties(goods.getSpec());
				sto.setScale(goods.getScale());
				sto.setUnit(goods.getGoodsUnit());
				sto.setStockNo(goods.getGoodsNo());
				sto.setName(goods.getGoodsName());
				sto.setStockId(goods.getId());
				sto.setCode(goods.getGoodsCode());
			}
			sto.setStoreId(sheet.getStoreId());
			sto.setCode(item.getGoodsCode());
			sto.setCreatedDate(System.currentTimeMillis());
			sto.setCreatePerson(user.getName());
			sto.setId(context.newRECID());
			sto.setInstoAmount(item.getAmount());
			sto.setInstoCount(item.getRealCount());
			sto.setInventoryType(inventoryType.getCode());
			if (CheckingInType.Irregular.getCode().equals(sheet.getSheetType())) {
				sto.setLogType(InventoryLogType.HandtomouthBuying.getCode());
			} else {
				sto.setLogType(InventoryLogType.INSTORAGE.getCode());
				if (CheckingInType.RealGoods.getCode().equals(sheet.getSheetType())) {
					sto.setLogType(InventoryLogType.GoodsCheckin.getCode());
				}
			}
			sto.setOrderId(sheet.getRECID());
			sto.setOrderNo(sheet.getSheetNo());
			list.add(sto);
		}
		stream.setList(list);
		context.handle(stream, StoStreamTask.Task.add);
	}

	/**
	 * 更新材料库存信息
	 * 
	 * @param b
	 * @param item
	 */
	public void modfiyMaterialsStorage(Context context, GUID storeId, CheckInSheetItem item,
			DistributeInventoryItem[] inventoryItems, InventoryType inventoryType, CheckingInType checkintype) {
		// 更新库存
		InventoryBusTask task = new InventoryBusTask(storeId, item.getGoodsId());
		task.setInventoryType(inventoryType);
		task.setChangeCountAndAmount(item.getRealCount(), item.getAmount());
		if (null != inventoryItems) {
			setShelfItem(task, inventoryItems, storeId, item.getGoodsId(), checkintype);
		}
		context.handle(task);
		// 更新采购在途
		if (checkintype == CheckingInType.Purchase) {
			InventoryOnWayTask onway = new InventoryOnWayTask(storeId, item.getGoodsId());
			onway.setInventoryType(InventoryType.Materials);
			onway.setOnWayCount(DoubleUtil.sub(0, item.getRealCount()));
			context.handle(onway);
		}
	}

	private void setShelfItem(InventoryBusTask task, DistributeInventoryItem[] inventoryItems, GUID storeId,
			GUID goodsId, CheckingInType checkintype) {
		List<DetItem> dets = new ArrayList<DetItem>();
		for (DistributeInventoryItem iteminfo : inventoryItems) {
			if (!iteminfo.getStockId().equals(goodsId)) {
				continue;
			}
			for (DistributeInventoryItemDet item : iteminfo.getInventoryDetItems()) {
				double count = item.getCount();
				DetItem det = task.new DetItem(item.getShelfId(), item.getShelfNo(), item.getTiersNo(), iteminfo
						.getStockId(), count, item.getProduceDate(), storeId);
				dets.add(det);
			}
		}
		task.setDets(dets.toArray(new DetItem[dets.size()]));
	}

	/**
	 * 得到入库单sheet实体
	 */
	private CheckInSheet fillEntity(Context context, Instorage entity, String type) {
		Employee user = context.find(Employee.class, context.find(Login.class).getEmployeeId());
		CheckInSheet sheet = new CheckInSheet();
		sheet.setAskedAmount(0);
		sheet.setBuyDate(entity.getPurchaseDate());
		sheet.setBuyPerson(entity.getPurchasePerson());
		sheet.setCheckinDate(System.currentTimeMillis());
		sheet.setCheckinPerson(user.getId());
		sheet.setCheckinPersonName(user.getName());
		sheet.setCreator(user.getName());
		sheet.setCreatorId(user.getId());
		sheet.setDeptId(user.getDepartmentId());
		sheet.setDisAmount(0);
		sheet.setGoodsFrom(entity.getGoodsFrom());
		sheet.setNamePY(PinyinHelper.getLetter(entity.getPartnerName()));

		sheet.setPartnerId(entity.getPartnerId());
		sheet.setPartnerName(entity.getPartnerName());
		sheet.setPartnerShortName(entity.getPartnerShortName());
		sheet.setRECID(context.newRECID());
		sheet.setRelaBillsId(entity.getRelaBillsId());
		sheet.setRelaBillsNo(entity.getRelaBillsNo());
		sheet.setRemark(entity.getRemark());
		String stoNo = context.get(String.class, SheetNumberType.Checkin);
		sheet.setSheetNo(stoNo);
		sheet.setSheetType(type);
		sheet.setStoreId(entity.getStoreId());
		sheet.setStoreName(entity.getStoreName());
		sheet.setStoreNamePY(PinyinHelper.getLetter(entity.getStoreName()));
		return sheet;
	}

	/**
	 * 得到入库单sheet明细实体
	 */
	public List<CheckInSheetItem> fillDetails(Context context, List<InstorageItem> detailList, CheckInSheet sheet,
			AllreadyAmountAndCount data) {
		List<CheckInSheetItem> list = new ArrayList<CheckInSheetItem>();
		double amount = 0;
		double count = 0;
		for (int i = 0; i < detailList.size(); i++) {
			InstorageItem det = detailList.get(i);
			CheckInSheetItem item = new CheckInSheetItem();
			item.setGoodsCode(det.getGoodsCode());
			item.setGoodsId(det.getGoodsId());
			item.setGoodsName(det.getGoodsName());
			item.setGoodsNo(det.getGoodsNo());
			item.setGoodsSpec(det.getGoodsSpec());
			item.setPrice(det.getPrice());
			item.setRealCount(det.getThisTimeCount());
			if (det.getThisTimeCount() == 0) {
				continue;
			}
			item.setRECID(context.newRECID());
			item.setScale(det.getScale());
			item.setSheetId(sheet.getRECID());
			item.setUnit(det.getUnit());
			count = DoubleUtil.sum(item.getRealCount(), count);
			if (i == detailList.size() - 1 && data != null
					&& data.getBillCount() == DoubleUtil.sum(data.getAllreadyCount(), count)) {
				// 判断是否是单据的最后一次入库，是则计算金额
				item.setAmount(DoubleUtil.sub(data.getBillAmount(), DoubleUtil.sum(amount, data.getAllreadyAmount())));
			} else {
				item.setAmount(DoubleUtil.mul(det.getThisTimeCount(), det.getPrice()));
			}
			list.add(item);
			amount = DoubleUtil.sum(item.getAmount(), amount);
		}
		sheet.setAmount(amount);
		return list;
	}

	/**
	 * 生成调整入库
	 */
	@Publish
	protected class InsertOneAdjustBillsService extends TaskMethodHandler<InstoAddTask, CheckingInType> {

		protected InsertOneAdjustBillsService() {
			super(CheckingInType.Kit);
		}

		@Override
		protected void handle(Context context, InstoAddTask task) throws Throwable {
			if (null == task.getEntity()) {
				return;
			}
		}
	}

	/**
	 * 确认入库
	 */
	@Publish
	protected class SureCheckinHandle extends SimpleTaskMethodHandler<SureCheckInTask> {

		@Override
		protected void handle(Context context, SureCheckInTask task) throws Throwable {
			// 验证是否可以入库（仓库状态，入库单数据）
			Instorage info = context.find(Instorage.class, task.getId());
			List<InstorageItem> list = context.getList(InstorageItem.class, task.getId());
			AllreadyAmountAndCount data = context.find(AllreadyAmountAndCount.class, GetBillsAllreadyAmountAndCountKey
					.getCheckInKey(info.getRelaBillsId()));
			CheckinSheetTask tttt = validation(context, task, info, data, list);
			if (tttt == null) {
				return;
			}
			CheckInSheet sheet = tttt.getEntity();
			List<CheckInSheetItem> items = tttt.getItems();
			// 生成入库单及明细
			context.handle(tttt);
			if (!tttt.isSuccess()) {
				throw new Exception("生成入库单出现不可预知错误，请重试！");
			}
			for (CheckInSheetItem item : items) {
				// 更新库存和成本
				modfiyMaterialsStorage(context, sheet.getStoreId(), item, task.getInventoryItems(),
						InventoryType.Materials, CheckingInType.getCheckingInType(sheet.getSheetType()));
			}
			// 库存流水
			doWrightStream(context, sheet, items, InventoryType.Materials);
			if (!sheet.getSheetType().equals(CheckingInType.GoodsSplit.getCode())) {
				DealingsType dealingsType = DealingsType.PRO_CGRK;
				if (sheet.getSheetType().equals(CheckingInType.Return.getCode())) {
					dealingsType = DealingsType.CUS_THRK;
				}
				// 应收应付
				doUpdateDealing(context, sheet, dealingsType);
			}
			// 更改订单状态
			doUpdateCheckingin(context, tttt, info, data, list);

			// 确认入库事件
			CheckInEvent event = new CheckInEvent(info.getRECID());
			event.setCheckInLogId(sheet.getRECID());
			event.setRelaOrderId(sheet.getRelaBillsId());
			event.setType(CheckingInType.getCheckingInType(sheet.getSheetType()));
			context.dispatch(event);
		}

		private CheckinSheetTask validation(Context context, SureCheckInTask task, Instorage info,
				AllreadyAmountAndCount data, List<InstorageItem> list) {
			Map<GUID, InstorageItem> map = new HashMap<GUID, InstorageItem>();
			for (InstorageItem item : list) {
				if (DoubleUtil.sub(item.getCount(), item.getCheckinCount()) == 0) {
					continue;
				}
				map.put(item.getId(), item);
			}
			setThisTimeCount(task, map);
			CheckInSheet sheet = fillEntity(context, info, info.getSheetType());

			List<CheckInSheetItem> items = fillDetails(context, list, sheet, data);
			CheckinSheetTask ttt = new CheckinSheetTask();
			ttt.setEntity(sheet);
			ttt.setItems(items);

			if (null == info) {
				task.addError(CheckInError.BillsError);
				task.setSuccess(false);
			}
			Store store = context.find(Store.class, info.getStoreId());
			if (null == store || !StoreStatus.ENABLE.equals(store.getStatus())) {
				task.addError(CheckInError.StoreError);
				task.setSuccess(false);
			}
			if (task.isSuccess()) {
				return ttt;
			}
			return null;
		}

		private void setThisTimeCount(SureCheckInTask task, Map<GUID, InstorageItem> map) {
			for (CheckInSheetTaskItem item : task.getItems()) {
				InstorageItem det = map.get(item.getId());
				det.setThisTimeCount(item.getCheckinCount());
				map.put(det.getId(), det);
			}
		}
	}

	private void doUpdateCheckingin(Context context, CheckinSheetTask tttt, Instorage info,
			AllreadyAmountAndCount data, List<InstorageItem> list) throws Exception {
		double count = 0;
		boolean isFinished = true;
		for (InstorageItem item : list) {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Inventory.Checkingin_Det.getTableName());
			ub.addColumn("checkinCount", ub.DOUBLE, item.getCheckinCount() + item.getThisTimeCount());
			ub.addCondition("oldAmount", ub.DOUBLE, item.getCheckinCount(), "t.checkinCount = @oldAmount");
			ub.addCondition("rid", ub.guid, item.getId(), "t.RECID=@rid");
			count = DoubleUtil.sum(item.getThisTimeCount(), count);
			int i = ub.execute();
			if (1 != i) {
				throw new Exception(CheckInError.DataChangedError.getMessage());
			}
			if (isFinished) {
				isFinished = item.getCount() == DoubleUtil.sum(item.getCheckinCount(), item.getThisTimeCount());
			}
		}
		UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
		ub.setTable(ERPTableNames.Inventory.Checkingin.getTableName());
		if (isFinished
		// && (!info.getSheetType().equals(CheckingInType.Return) ||
		// data.getBillCount() == DoubleUtil.sum(data
		// .getAllreadyCount(), count))
		) {
			info.setStatus(CheckingInStatus.Finish.getCode());
			ub.addColumn("status", ub.STRING, CheckingInStatus.Finish.getCode());
		} else {
			ub.addColumn("status", ub.STRING, CheckingInStatus.Part.getCode());
		}
		ub.addCondition("rid", ub.guid, info.getRECID(), "t.RECID=@rid");
		ub.execute();
	}

	@Publish
	protected class AllreadyAmountAndCountProvider extends
			OneKeyResultProvider<AllreadyAmountAndCount, GetBillsAllreadyAmountAndCountKey> {

		@Override
		protected AllreadyAmountAndCount provide(Context context, GetBillsAllreadyAmountAndCountKey key)
				throws Throwable {
			AllreadyAmountAndCount data = new AllreadyAmountAndCount();
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addColumn("t.billsCount", "billsCount");
			qb.addColumn("t.billsAmount", "billsAmount");
			qb.addColumn("sum(d.amount)", "amount");
			qb.addColumn("sum(d.realCount)", "realCount");
			if (key.isCheckIn()) {
				qb.addGroupBy("s.paidAmount");
				qb.addColumn("s.paidAmount", "paidAmount");
				qb.addTable(ERPTableNames.Inventory.Checkingin.getTableName(), "t");
				qb.addTable(ERPTableNames.Inventory.CheckinSheet.getTableName(), "s");
				qb.addTable(ERPTableNames.Inventory.CheckinSheet_Det.getTableName(), "d");
			} else {
				qb.addGroupBy("s.receiptedAmount");
				qb.addColumn("s.receiptedAmount", "receiptedAmount");
				qb.addTable(ERPTableNames.Inventory.Checkingout.getTableName(), "t");
				qb.addTable(ERPTableNames.Inventory.CheckoutSheet.getTableName(), "s");
				qb.addTable(ERPTableNames.Inventory.CheckoutSheet_Det.getTableName(), "d");
			}
			qb.addArgs("relaId", qb.guid, key.getBillsId());
			qb.addEquals("t.relaBillsId", "@relaId");
			qb.addEquals("s.relaBillsId", "@relaId");
			qb.addEquals("t.relaBillsId", "s.relaBillsId");
			qb.addEquals("d.sheetId", "s.RECID");
			qb.addGroupBy("t.billsCount");
			qb.addGroupBy("t.billsAmount");
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				int index = 0;
				data.setBillCount(rs.getFields().get(index++).getDouble());
				data.setBillAmount(rs.getFields().get(index++).getDouble());
				data.setAllreadyAmount(rs.getFields().get(index++).getDouble());
				data.setAllreadyCount(rs.getFields().get(index++).getDouble());
				data.setPaidOrReceiptedAmount(rs.getFields().get(index++).getDouble());
			} else {
				fillBillCount(context, key, data);
			}
			return data;
		}

		private void fillBillCount(Context context, GetBillsAllreadyAmountAndCountKey key, AllreadyAmountAndCount data) {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addColumn("t.billsCount", "billsCount");
			qb.addColumn("t.billsAmount", "billsAmount");
			if (key.isCheckIn()) {
				qb.addTable(ERPTableNames.Inventory.Checkingin.getTableName(), "t");
			} else {
				qb.addTable(ERPTableNames.Inventory.Checkingout.getTableName(), "t");
			}
			qb.addArgs("relaId", qb.guid, key.getBillsId());
			qb.addEquals("t.relaBillsId", "@relaId");
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				int index = 0;
				data.setBillCount(rs.getFields().get(index++).getDouble());
				data.setBillAmount(rs.getFields().get(index++).getDouble());
			}
		}
	}

	/**
	 * 调整入库
	 */
	@Publish
	protected class AdjustCheckinTaskHandle extends SimpleTaskMethodHandler<AdjustCheckinTask> {

		@Override
		protected void handle(Context context, AdjustCheckinTask task) throws Throwable {
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());
			CheckInSheet sheet = new CheckInSheet();
			List<CheckInSheetItem> items = new ArrayList<CheckInSheetItem>();
			sheet.setRECID(context.newRECID());
			sheet.setCheckinDate(System.currentTimeMillis());
			sheet.setCheckinPerson(user.getId());
			sheet.setCheckinPersonName(user.getName());
			sheet.setCreator(user.getName());
			sheet.setCreatorId(user.getId());
			sheet.setStoreId(task.getStoreId());
			sheet.setRemark(task.getRemark());
			Store store = context.find(Store.class, task.getStoreId());
			String sheetNo = context.get(String.class, SheetNumberType.Checkin);
			sheet.setSheetNo(sheetNo);
			sheet.setSheetType(CheckingInType.Adjustment.getCode());
			sheet.setStoreName(store.getName());
			sheet.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
			sheet.setPartnerId(task.getPartnerId());
			Partner p = context.find(Partner.class, task.getPartnerId());
			sheet.setPartnerName(p.getName());
			sheet.setPartnerShortName(p.getShortName());
			sheet.setNamePY(PinyinHelper.getLetter(p.getName()));
			for (AdjustCheckinTaskItem item : task.getItems()) {
				CheckInSheetItem det = new CheckInSheetItem();
				det.setRECID(context.newRECID());
				det.setSheetId(sheet.getRECID());
				det.setAmount(item.getAmount());
				det.setGoodsId(item.getGoodsId());
				MaterialsItem goods = context.find(MaterialsItem.class, item.getGoodsId());
				det.setGoodsCode(goods.getMaterialCode());
				det.setGoodsName(goods.getMaterialName());
				det.setGoodsNo(goods.getMaterialNo());
				det.setGoodsSpec(goods.getSpec());
				det.setUnit(goods.getMaterialUnit());
				det.setScale(goods.getScale());
				items.add(det);
			}
			sheet.setAmount(task.getAmount());
			CheckinSheetTask tttt = new CheckinSheetTask();
			tttt.setEntity(sheet);
			tttt.setItems(items);
			context.handle(tttt);
		}
	}

	/**
	 * 联营入库
	 */
	@Publish
	protected class JointCheckinTaskHandle extends SimpleTaskMethodHandler<JointCheckinTask> {

		@Override
		protected void handle(Context context, JointCheckinTask task) throws Throwable {
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());
			CheckInSheet sheet = new CheckInSheet();
			List<CheckInSheetItem> items = new ArrayList<CheckInSheetItem>();
			sheet.setRECID(context.newRECID());
			sheet.setCheckinDate(System.currentTimeMillis());
			sheet.setCheckinPerson(user.getId());
			sheet.setCheckinPersonName(user.getName());
			sheet.setCreator(user.getName());
			sheet.setCreatorId(user.getId());
			sheet.setStoreId(task.getStoreId());
			sheet.setRemark(task.getRemark());
			Store store = context.find(Store.class, task.getStoreId());
			String sheetNo = context.get(String.class, SheetNumberType.Checkin);
			sheet.setSheetNo(sheetNo);
			sheet.setSheetType(CheckingInType.Joint.getCode());
			sheet.setStoreName(store.getName());
			sheet.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
			for (JointCheckinTaskItem item : task.getItems()) {
				CheckInSheetItem det = new CheckInSheetItem();
				det.setRECID(context.newRECID());
				det.setSheetId(sheet.getRECID());
				det.setRealCount(item.getCount());
				det.setGoodsId(item.getGoodsId());
				MaterialsItem goods = context.find(MaterialsItem.class, item.getGoodsId());
				det.setGoodsCode(goods.getMaterialCode());
				det.setGoodsName(goods.getMaterialName());
				det.setGoodsNo(goods.getMaterialNo());
				det.setGoodsSpec(goods.getSpec());
				det.setUnit(goods.getMaterialUnit());
				det.setScale(goods.getScale());
				items.add(det);
			}
			CheckinSheetTask tttt = new CheckinSheetTask();
			tttt.setEntity(sheet);
			tttt.setItems(items);
			context.handle(tttt);

			for (CheckInSheetItem item : items) {
				// 更新库存和成本
				modfiyMaterialsStorage(context, sheet.getStoreId(), item, task.getInventoryItems(),
						InventoryType.Materials, CheckingInType.getCheckingInType(sheet.getSheetType()));
			}
			// 库存流水
			doWrightStream(context, sheet, items, InventoryType.Materials);
		}
	}

	@Publish
	protected class GiftCheckinHandle extends SimpleTaskMethodHandler<GiftCheckinTask> {
		@Override
		protected void handle(Context context, GiftCheckinTask task) throws Throwable {
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());
			CheckInSheet sheet = new CheckInSheet();
			List<CheckInSheetItem> items = new ArrayList<CheckInSheetItem>();
			sheet.setRECID(context.newRECID());
			sheet.setCheckinDate(System.currentTimeMillis());
			sheet.setCheckinPerson(user.getId());
			sheet.setCheckinPersonName(user.getName());
			sheet.setCreator(user.getName());
			sheet.setCreatorId(user.getId());
			sheet.setStoreId(task.getStoreId());
			sheet.setRemark(task.getRemark());
			Store store = context.find(Store.class, task.getStoreId());
			String sheetNo = context.get(String.class, SheetNumberType.Checkin);
			sheet.setSheetNo(sheetNo);
			sheet.setSheetType(CheckingInType.Gift.getCode());
			sheet.setStoreName(store.getName());
			sheet.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
			sheet.setPartnerId(task.getPartnerId());
			Partner p = context.find(Partner.class, task.getPartnerId());
			sheet.setPartnerName(p.getName());
			sheet.setPartnerShortName(p.getShortName());
			sheet.setNamePY(PinyinHelper.getLetter(p.getName()));
			for (GiftCheckinTaskItem item : task.getItems()) {
				CheckInSheetItem det = new CheckInSheetItem();
				det.setRECID(context.newRECID());
				det.setSheetId(sheet.getRECID());
				det.setAmount(0);
				det.setRealCount(item.getCount());
				det.setGoodsId(item.getGoodsId());
				MaterialsItem goods = context.find(MaterialsItem.class, item.getGoodsId());
				det.setGoodsCode(goods.getMaterialCode());
				det.setGoodsName(goods.getMaterialName());
				det.setGoodsNo(goods.getMaterialNo());
				det.setGoodsSpec(goods.getSpec());
				det.setUnit(goods.getMaterialUnit());
				det.setScale(goods.getScale());
				items.add(det);
			}
			sheet.setAmount(task.getAmount());
			CheckinSheetTask tttt = new CheckinSheetTask();
			tttt.setEntity(sheet);
			tttt.setItems(items);
			context.handle(tttt);

			for (CheckInSheetItem item : items) {
				// 更新库存和成本
				modfiyMaterialsStorage(context, sheet.getStoreId(), item, task.getDistributeInventoryItems(),
						InventoryType.Materials, CheckingInType.getCheckingInType(sheet.getSheetType()));
			}
			// 库存流水
			doWrightStream(context, sheet, items, InventoryType.Materials);
		}

	}

}
