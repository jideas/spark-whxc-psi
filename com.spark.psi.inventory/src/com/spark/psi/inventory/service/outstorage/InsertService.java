package com.spark.psi.inventory.service.outstorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.psi.account.intf.task.pub.CusdealTask;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Partner;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.Store;
import com.spark.psi.base.key.GetInventoryByStockIdKey;
import com.spark.psi.base.task.goods.UpdateGoodsItemAveragePurchasePriceTask;
import com.spark.psi.base.utils.MaterialsProperyUtil;
import com.spark.psi.inventory.internal.entity.CheckInventory;
import com.spark.psi.inventory.internal.entity.CheckInventoryItem;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.internal.entity.OtherGoods;
import com.spark.psi.inventory.intf.entity.outstorage.CheckOutLog;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.inventory.intf.entity.outstorage.mod.SureOutItem;
import com.spark.psi.inventory.intf.entity.outstorage.mod.SureOutstorage;
import com.spark.psi.inventory.intf.event.CheckOutEvent;
import com.spark.psi.inventory.intf.event.CheckingOutEvent;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.task.checkinventory.CheckInventoryTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryDeliveringTask;
import com.spark.psi.inventory.intf.task.inventory.StoStreamTask;
import com.spark.psi.inventory.intf.task.inventory.UpdateOtherGoodsTask;
import com.spark.psi.inventory.intf.task.outstorage.CheckOutLogTask;
import com.spark.psi.inventory.intf.task.outstorage.OutstoAddTask;
import com.spark.psi.inventory.intf.task.outstorage.OutstorageItemTask;
import com.spark.psi.inventory.intf.task.outstorage.OutstorageTask;
import com.spark.psi.inventory.intf.task.outstorage.SureOutstorageTask;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.constant.CheckoutReceiptedFlag;
import com.spark.psi.publish.inventory.checkout.task.MaterialCheckOutTask;
import com.spark.psi.publish.inventory.checkout.task.MaterialCheckOutTaskItem;

/**
 * <p>
 * 新增出库单的操作接口类
 * </p>
 * 
 */
public class InsertService extends Service {

	protected InsertService() {
		super("InsertService");
	}

	/**
	 * 生成材料出库ok
	 */
	@Publish
	protected class InsertMaterialCheckoutServicer extends SimpleTaskMethodHandler<MaterialCheckOutTask> {
		@Override
		protected void handle(Context context, MaterialCheckOutTask task) throws Throwable {
			Outstorage out = new Outstorage();
			List<OutstorageItem> detailList = new ArrayList<OutstorageItem>();
			out.setRelaBillsId(task.getRelaBillsId());
			out.setRelaBillsNo(task.getRelaBillsNo());
			out.setRemark(task.getRemark());
			out.setStoreId(task.getStoreId());
			double amount = 0;
			for (MaterialCheckOutTaskItem item : task.getItems()) {
				OutstorageItem det = new OutstorageItem();
				MaterialsItem goods = context.find(MaterialsItem.class, item.getMateiralId());
				det.setGoodsCode(goods.getMaterialCode());
				det.setGoodsId(goods.getId());
				det.setGoodsName(goods.getMaterialName());
				det.setGoodsNo(goods.getMaterialNo());
				det.setGoodsSpec(goods.getSpec());
				det.setGoodsStorageType(InventoryType.Materials);
				det.setPlanCount(item.getPlanCount());
				det.setScale(goods.getScale());
				det.setUnit(goods.getMaterialUnit());
				det.setPrice(goods.getAvgBuyPrice());
				det.setAmount(DoubleUtil.mul(goods.getAvgBuyPrice(), det.getPlanCount()));
				amount = DoubleUtil.sum(amount, det.getAmount());
				detailList.add(det);
			}
			CheckingOutType type = CheckingOutType.Mateiral_Take;
			if (task.isReturn()) {
				type = CheckingOutType.Mateiral_Return;
			}
			fillEntity(context, out, type);
			out.setStatus(CheckingOutStatus.None.getCode());
			OutstorageTask it = new OutstorageTask();
			it.setEntity(out);
			context.handle(it, Method.INSERT);
			addDetails(context, out, detailList);
			// 交付需求
			modfiyToDeliver(context, new OutstoAddTask(out, detailList));
			CheckingOutEvent event = new CheckingOutEvent();
			event.setCheckOutSheetId(out.getRECID());
			context.dispatch(event);
		}
	}

	/**
	 * 生成销售出库ok
	 */
	@Publish
	protected class InsertOnebuyBillsService extends TaskMethodHandler<OutstoAddTask, CheckingOutType> {

		protected InsertOnebuyBillsService() {
			super(CheckingOutType.Sales);
		}

		@Override
		protected void handle(Context context, OutstoAddTask task) throws Throwable {
			if (null == task.getEntity()) {
				return;
			}
			fillEntity(context, task.getEntity(), CheckingOutType.Sales);
			task.getEntity().setStatus(CheckingOutStatus.None.getCode());
			task.getEntity().setSheetType(CheckingOutType.Sales.getCode());
			OutstorageTask it = new OutstorageTask();
			it.setEntity(task.getEntity());
			context.handle(it, Method.INSERT);
			addDetails(context, task.getEntity(), task.getDetailList());
			// 交付需求
			modfiyToDeliver(context, task);

			CheckingOutEvent event = new CheckingOutEvent();
			event.setCheckOutSheetId(task.getEntity().getRECID());
			context.dispatch(event);

		}
	}

	/**
	 * 生成采购退货出库
	 */
	@Publish
	protected class InsertOnescBillsService extends TaskMethodHandler<OutstoAddTask, CheckingOutType> {

		protected InsertOnescBillsService() {
			super(CheckingOutType.Return);
		}

		@Override
		protected void handle(Context context, OutstoAddTask task) throws Throwable {
			if (null == task.getEntity()) {
				return;
			}
			fillEntity(context, task.getEntity(), CheckingOutType.Return);
			task.getEntity().setStatus(CheckingOutStatus.None.getCode());
			OutstorageTask it = new OutstorageTask();
			it.setEntity(task.getEntity());
			context.handle(it, Method.INSERT);
			addDetails(context, task.getEntity(), task.getDetailList());
			// 交付需求
			modfiyToDeliver(context, task);

			CheckingOutEvent event = new CheckingOutEvent();
			event.setCheckOutSheetId(task.getEntity().getRECID());
			context.dispatch(event);
		}
	}

	/**
	 * 更新库存
	 */
	public void modfiyStorage(Context context, OutstoAddTask data, String type) {

		int checkProfit = 0;
		CheckInventory checkInventory = new CheckInventory();
		GUID checkInventoryId = context.newRECID();

		checkInventory.setRecid(checkInventoryId);
		checkInventory.setTenantsGuid(context.find(Login.class).getTenantId());
		checkInventory.setCheckOrdNo(context.find(String.class, SheetNumberType.InventoryCount));
		checkInventory.setCreateDate(new Date().getTime());
		checkInventory.setStartDate(new Date().getTime());
		checkInventory.setCreatePerson(context
				.find(Employee.class, context.find(Login.class).getEmployeeId()).getName());
		checkInventory.setStoreGuid(data.getEntity().getStoreId());
		checkInventory.setCheckOrdState(InventoryCountStatus.Counted.getCode());
		Store store = context.find(Store.class, data.getEntity().getStoreId());
		if (null != store) {
			checkInventory.setStoreName(store.getName());
			checkInventory.setStorePY(PinyinHelper.getLetter(store.getName()));
		}
		checkInventory.setCheckObj(InventoryCountType.Materials.getCode());
		checkInventory.setCheckPerson(context.find(Employee.class, context.find(Login.class).getEmployeeId())
				.getName());
		List<CheckInventoryItem> list = new ArrayList<CheckInventoryItem>();
		for (OutstorageItem det : data.getDetailList()) {
			if (InventoryType.Goods.equals(det.getGoodsStorageType())) {
				ResourceToken<Inventory> token = context.findResourceToken(Inventory.class, data.getEntity()
						.getStoreId(), det.getGoodsId());
				// 零售出库库存数量差值
				double subCount = 0;
				double carrayCount = 0;
				if (null != token) {
					subCount = DoubleUtil.sub(token.getFacade().getCount(), det.getCheckoutCount());
					carrayCount = token.getFacade().getCount();
				} else {
					subCount = DoubleUtil.sub(0, det.getCheckoutCount());
				}
				InventoryBusTask task = new InventoryBusTask(data.getEntity().getStoreId(), det.getGoodsId());
				task.setChangeCount(DoubleUtil.sub(0, det.getCheckoutCount()));
				task.setUpdateAvgPrice(false);
				task.setRetail(true);

				if (subCount < 0) {

					updateGoodsItemAveragePurchasePrice(context, det, subCount);
					context.handle(task);

					CheckInventoryItem item = new

					CheckInventoryItem();
					item.setRecid(context.newRECID());
					item.setTenantsGuid(context.find(Login.class).getTenantId());
					item.setCheckOrdGuid(checkInventoryId);

					item.setCarryCount(carrayCount);
					item.setRealCount(det.getCheckoutCount());
					item.setCreateDate(new Date().getTime());
					item.setCreatePerson(context.find(Employee.class,
							context.find(Login.class).getEmployeeId()).getName());
					item.setGoodsGuid(det.getGoodsId());
					GoodsItem goodsItem = context.find(GoodsItem.class, det.getGoodsId());
					if (null != goodsItem) {
						item.setGoodsAttr(goodsItem.getPropertiesWithoutUnit());
						item.setGoodsName(goodsItem.getGoodsName());
						item.setGoodsItemNo(goodsItem.getGoodsNo());
						item.setGoodsItemCode(goodsItem.getGoodsCode());
						item.setGoodsScale(goodsItem.getScale());
						item.setUnit(goodsItem.getGoodsUnit());
					}
					checkProfit += 1;
					list.add(item);
				} else {
					context.handle(task);
				}
			} else if (InventoryType.Others.equals(det.getGoodsStorageType())) {
				OtherGoods goods = new OtherGoods();
				goods.setDescription(det.getGoodsSpec());
				goods.setInit(false);
				goods.setName(det.getGoodsName());
				goods.setNumber(DoubleUtil.sub(0, det.getCheckoutCount()));
				goods.setUnit(det.getUnit());
				UpdateOtherGoodsTask task = new UpdateOtherGoodsTask(data.getEntity().getStoreId(), goods);
				task.setInit(false);
				context.handle(task, Method.MODIFY);
			}

		}

		// 生成盘点单，盘平
		if (checkProfit > 0) {
			checkInventory.setCheckProfit(checkProfit);
			checkInventory.setRemark("零售出库盘平");
			checkInventory.setCheckOrdState(InventoryCountStatus.Counted.getCode());
			checkInventory.setEndDate(new Date().getTime());
			CheckInventoryTask cTask = new CheckInventoryTask();
			cTask.setCheckInventoryEntity(checkInventory);
			cTask.setList(list);
			checkInventory.setCheckProfit(checkProfit);
			context.handle(cTask, Method.INSERT);

			//insertCountInventoryLog(context, cTask);
		}
		// 更新库存流水和台账
		StoStreamTask stream = new StoStreamTask();
		List<InventoryLogEntity> strList = new ArrayList<InventoryLogEntity>();
		for (int i = 0; i < data.getDetailList().size(); i++) {
			OutstorageItem det = data.getDetailList().get(i);
			if (InventoryType.Others == det.getGoodsStorageType()) {
				continue;
			}
			double avgInventoryCost = 0;
			InventoryLogEntity sto = new InventoryLogEntity();
			MaterialsItem goods = context.find(MaterialsItem.class, det.getGoodsId());
			if (null != goods) {
				avgInventoryCost = goods.getAvgBuyPrice();
				sto.setCategoryId(goods.getCategoryId());
				sto.setName(goods.getMaterialName());
				sto.setProperties(goods.getSpec());
				sto.setUnit(goods.getMaterialUnit());
				sto.setCode(goods.getMaterialCode());
				sto.setStockNo(goods.getMaterialNo());
				sto.setScale(goods.getScale());
				sto.setInventoryType(InventoryType.Materials.getCode());
			}
			double amount = DoubleUtil.mul(avgInventoryCost, det.getCheckoutCount(), 2);
			sto.setId(context.newRECID());
			sto.setStoreId(data.getEntity().getStoreId());
			sto.setStockId(det.getGoodsId());
			sto.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId())
					.getName());
			sto.setCreatedDate(new Date().getTime());

			sto.setLogType(type);
			sto.setCreatedDate(new Date().getTime());
			sto.setOutstoCount(det.getCheckoutCount());
			sto.setOutstoAmount(amount);
			sto.setOrderId(data.getEntity().getRECID());
			// sto.setOrderNo(data.getEntity().get);
			strList.add(sto);
		}
		stream.setList(strList);
		if (!strList.isEmpty()) {
			context.handle(stream, StoStreamTask.Task.add);
		}
	}

	/**
	 * 零售出库负库存更新平均采购成本
	 * 
	 * @param context
	 * @param det
	 * @param subCount
	 *            void
	 */
	private void updateGoodsItemAveragePurchasePrice(Context context, OutstorageItem det, double subCount) {
		GetInventoryByStockIdKey iKey = new GetInventoryByStockIdKey(det.getGoodsId(),InventoryType.Materials);
		List<Inventory> list = context.getList(Inventory.class, iKey);
		double avgPrice = 0;
		// GoodsItem goods = context.find(GoodsItem.class, det.getGoodsId());
		double amount = 0;
		// if (null != goods) {
		// amount = DoubleUtil.mul(goods.getAveragePurchasePrice(), DoubleUtil
		// .mul(-1, subCount),2);
		// }
		double count = DoubleUtil.mul(-1, subCount);
		if (CheckIsNull.isNotEmpty(list)) {
			for (Inventory inventory : list) {
				amount = DoubleUtil.sum(inventory.getAmount(), amount);
				count = DoubleUtil.sum(inventory.getCount(), count);
			}
		}
		if (count > 0) {
			avgPrice = DoubleUtil.div(amount, count);
		}
		UpdateGoodsItemAveragePurchasePriceTask avgTask = new UpdateGoodsItemAveragePurchasePriceTask(det
				.getGoodsId(), avgPrice);
		context.handle(avgTask);
	}

	public void insertLog(Context context, OutstoAddTask task) {
		GUID logId = context.newRECID();
		double sumCount = 0;
		double sumAmount = 0;
		sumCount = sumThisTimeCount(task.getDetailList());
		sumAmount = doInertSureInfo(context, logId, task.getEntity(), task.getDetailList());

		CheckOutLog log = new CheckOutLog();
		log.setCheckOutAmount(sumAmount);
		log.setCheckOutCount(sumCount);
		log.setCheckOutDate(new Date().getTime());
		log.setCheckOutSheetId(task.getEntity().getRECID());
		log.setId(logId);
		log.setTenantsId(context.find(Login.class).getTenantId());
		log.setKeeper(context.find(Employee.class, context.find(Login.class).getEmployeeId()).getName());
		// log.setTaker(task.getDeliveryPerson());
		// log.setTakerUnit(task.getDeliveryDepartment());
		// log.setVouchersNumber(task.getVoucherNumber());
		CheckOutLogTask lTask = new CheckOutLogTask();
		lTask.setCheckOutLog(log);
		context.handle(lTask);

		CheckOutEvent checkOutEvent = new CheckOutEvent(null);
		checkOutEvent.setCheckOutLogId(logId);
		checkOutEvent.setRelaOrderId(task.getEntity().getRelaBillsId());
		checkOutEvent.setType(CheckingOutType.getCheckingOutType(task.getEntity().getSheetType()));
		context.dispatch(checkOutEvent);

	}

	/**
	 * 计算本次总出库数量
	 * 
	 * @param detailList
	 * @return double
	 */
	private double sumThisTimeCount(List<OutstorageItem> detailList) {
		double count = 0;
		for (OutstorageItem det : detailList) {
			count = DoubleUtil.sum(count, det.getThisTimeCount());
		}
		return count;
	}

	/**
	 * 插入确认出库信息表
	 * 
	 * @param context
	 * @param logId
	 * @param entity
	 * @param detailList
	 *            void
	 * @param facade
	 */
	public double doInertSureInfo(Context context, GUID logId, Outstorage outsto,
			List<OutstorageItem> detailList) {
		double amount = 0;
		SureOutstorage entity = new SureOutstorage();
		Employee user = context.find(Employee.class, context.find(Login.class).getEmployeeId());
		GUID id = context.newRECID();
		String stoNo = context.get(String.class, SheetNumberType.Checkout);
		entity.setRECID(id);
		entity.setPartnerId(outsto.getPartnerId());
		entity.setPartnerName(outsto.getPartnerName());
		entity.setPartnerShortName(outsto.getPartnerShortName());
		entity.setNamePY(PinyinHelper.getLetter(outsto.getPartnerName()));
		entity.setCheckoutDate(System.currentTimeMillis());
		entity.setCheckoutType(outsto.getSheetType());
		entity.setCheckoutPerson(user.getId());
		entity.setCheckoutPersonName(user.getName());
		entity.setCreator(user.getName());
		entity.setCreatorId(user.getId());
		entity.setDeptId(user.getDepartmentId());
		entity.setReceiptedAmount(0);
		entity.setReceipting(false);
		entity.setRelaBillsId(outsto.getRelaBillsId());
		entity.setRelaBillsNo(outsto.getRelaBillsNo());
		entity.setRemark(outsto.getRemark());
		entity.setSheetNo(stoNo);
		entity.setStoreId(outsto.getStoreId());
		entity.setStoreName(outsto.getStoreName());
		entity.setStoreNamePY(PinyinHelper.getLetter(outsto.getStoreName()));
		entity.setTakePerson(outsto.getDeliveryPerson());
		entity.setTakeUnit(outsto.getDeliveryDepartment());
		entity.setVouchersNo(outsto.getVoucherNumber());
		entity.setReceiptedFlag(CheckoutReceiptedFlag.NeverReceipted.getCode());
		List<SureOutItem> items = new ArrayList<SureOutItem>();
		for (int index = 0; index < detailList.size(); index++) {
			OutstorageItem det = detailList.get(index);
			if (0 == det.getCheckoutCount()) {
				continue;
			}
			SureOutItem item = new SureOutItem();
			item.setRECID(context.newRECID());
			item.setGoodsSpec(det.getGoodsSpec());
			item.setGoodsId(det.getGoodsId());
			item.setGoodsName(det.getGoodsName());
			item.setScale(det.getScale());
			item.setCount(det.getCheckoutCount());
			MaterialsItem goods = context.find(MaterialsItem.class, det.getGoodsId());
			double price = 0;
			if (null != goods) {
				price = goods.getAvgBuyPrice();
			}
			item.setAmount(DoubleUtil.mul(price, det.getThisTimeCount(), 2));
			item.setCreateDate(entity.getCheckoutDate());
			item.setCreatePerson(user.getName());
			item.setGoodsCode(det.getGoodsCode());
			item.setGoodsNo(det.getGoodsNo());
			item.setSheetId(id);
			item.setUnit(det.getUnit());
			item.setPrice(det.getPrice());
			item.setAvgCost(price);
			amount = DoubleUtil.sum(amount, item.getAmount());
			items.add(item);
		}
		entity.setItems(items);
		SureOutstorageTask task = new SureOutstorageTask();
		task.setEntity(entity);
		context.handle(task, Method.INSERT);
		return amount;
	}

	public void insertDealingItem(Context context, OutstoAddTask task) {
		double amount = task.getEntity().getBillsAmount();
		if (0 == amount) {
			return;
		}
		long date = new Date().getTime();
		long date1 = date + 10;
		CusdealTask cTask = new CusdealTask(task.getEntity().getPartnerId(), DealingsType.CUS_LSCK, amount,
				0, task.getEntity().getRECID(), "");
		cTask.setPubdate(date);
		context.handle(cTask);

		CusdealTask dTask = new CusdealTask(task.getEntity().getPartnerId(), DealingsType.CUS_LSSK, 0,
				amount, task.getEntity().getRelaBillsId(), task.getEntity().getRelaBillsNo());
		dTask.setPubdate(date1);
		dTask.setReceiptType(task.getDealingsWay());
		context.handle(dTask);

	}

	/**
	 * 更新交付需求
	 */
	private void modfiyToDeliver(Context context, OutstoAddTask data) {
		for (OutstorageItem det : data.getDetailList()) {
			InventoryDeliveringTask task = new InventoryDeliveringTask(data.getEntity().getStoreId(), det
					.getGoodsId());
			task.setToDeliverCount(det.getPlanCount());
			context.handle(task);
		}
	}

	/**
	 * 对一个出库单实体添加基础数据
	 */
	private void fillEntity(Context context, Outstorage entity, CheckingOutType type) {

		// 出库单号
		Employee user = context.find(Employee.class, context.find(Login.class).getEmployeeId());
		entity.setRECID(context.newRECID());
		entity.setSheetType(type.getCode());
		entity.setDeptId(user.getDepartmentId());
		entity.setCreateDate(new Date().getTime());
		entity.setCreator(user.getName());
		entity.setCreatorId(user.getId());
		if (CheckIsNull.isNotEmpty(entity.getPartnerId())) {
			Partner partner = context.find(Partner.class, entity.getPartnerId());
			if (null != partner) {
				entity.setPartnerName(partner.getName());
				entity.setNamePY(PinyinHelper.getLetter(partner.getName()));
				entity.setPartnerShortName(partner.getShortName());
				entity.setPartnerCode(partner.getCode());
			}
		}

		Store store = null;
		if (null != entity.getStoreId()) {
			store = context.find(Store.class, entity.getStoreId());
		}
		if (null != store) {
			entity.setStoreName(store.getName());
			entity.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
		}
	}

	/**
	 * 保存明细数据
	 */
	private void addDetails(Context context, Outstorage entity, List<OutstorageItem> detailList)
			throws Exception {
		if (null == detailList || detailList.isEmpty()) {
			throw new Exception("生成出库单失败(明细数据缺失)");
		}
		for (OutstorageItem det : detailList) {
			det.setSheetId(entity.getRECID());
			det.setRECID(context.newRECID());
			det.setCreateDate(new Date().getTime());
			det.setCreator(context.find(Employee.class, context.find(Login.class).getEmployeeId()).getName());
			context.handle(new OutstorageItemTask(det), Method.INSERT);
		}
	}

}
