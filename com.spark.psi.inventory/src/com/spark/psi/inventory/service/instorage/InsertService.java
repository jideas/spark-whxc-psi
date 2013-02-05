package com.spark.psi.inventory.service.instorage;

import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.intf.constant.instorage.InstoConstant;
import com.spark.psi.inventory.intf.entity.instorage.mod.CheckInSheet;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.inventory.intf.event.CheckingInEvent;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.task.instorage.InstoAddTask;
import com.spark.psi.inventory.intf.task.instorage.InstorageItemTask;
import com.spark.psi.inventory.intf.task.instorage.InstorageTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryOnWayTask;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.account.task.CreatePaymentTask;
import com.spark.psi.publish.account.task.CreatePaymentTask.Item;

/**
 * 新增入库单的操作接口类
 */

public class InsertService extends Service {

	protected InsertService() {
		super("InsertService");
	}

	/**
	 * 生成成品拆分入库
	 */
	@Publish
	protected class InsertOnebuyBillsService0 extends TaskMethodHandler<InstoAddTask, CheckingInType> {

		protected InsertOnebuyBillsService0() {
			super(CheckingInType.GoodsSplit);
		}

		@Override
		protected void handle(Context context, InstoAddTask task) throws Throwable {
			if (null == task.getEntity()) {
				return;
			}
			fillEntity(context, task.getEntity(), CheckingInType.GoodsSplit.getCode());
			task.getEntity().setStatus(CheckingInStatus.None.getCode());
			InstorageTask it = new InstorageTask();
			it.setInstorageEntity(task.getEntity());
			context.handle(it, Method.INSERT);
			addDetails(context, task.getEntity(), task.getDetailList());
			// 采购在途
			modfiyCountOnWay(context, task, false);

			CheckingInEvent event = new CheckingInEvent();
			event.setCheckInSheetId(task.getEntity().getRECID());
			context.dispatch(event);
		}
	}

	/**
	 * 生成采购入库
	 */
	@Publish
	protected class InsertOnebuyBillsService extends TaskMethodHandler<InstoAddTask, CheckingInType> {

		protected InsertOnebuyBillsService() {
			super(CheckingInType.Purchase);
		}

		@Override
		protected void handle(Context context, InstoAddTask task) throws Throwable {
			if (null == task.getEntity()) {
				return;
			}
			fillEntity(context, task.getEntity(), CheckingInType.Purchase.getCode());
			task.getEntity().setStatus(CheckingInStatus.None.getCode());
			InstorageTask it = new InstorageTask();
			it.setInstorageEntity(task.getEntity());
			context.handle(it, Method.INSERT);
			addDetails(context, task.getEntity(), task.getDetailList());
			// 采购在途
			modfiyCountOnWay(context, task, false);

			CheckingInEvent event = new CheckingInEvent();
			event.setCheckInSheetId(task.getEntity().getRECID());
			context.dispatch(event);
		}
	}

	/**
	 * 生成销售退货入库
	 */
	@Publish
	protected class InsertOnescBillsService extends TaskMethodHandler<InstoAddTask, CheckingInType> {

		protected InsertOnescBillsService() {
			super(CheckingInType.Return);
		}

		@Override
		protected void handle(Context context, InstoAddTask task) throws Throwable {
			if (null == task.getEntity()) {
				return;
			}
			fillEntity(context, task.getEntity(), CheckingInType.Return.getCode());
			task.getEntity().setStatus(CheckingInStatus.None.getCode());
			InstorageTask it = new InstorageTask();
			it.setInstorageEntity(task.getEntity());
			context.handle(it, Method.INSERT);
			addDetails(context, task.getEntity(), task.getDetailList());

			CheckingInEvent event = new CheckingInEvent();
			event.setCheckInSheetId(task.getEntity().getRECID());
		}
	}

	public void insertDealingItem(Context context, InstoAddTask task) {
		// double amount = task.getEntity().getOrderTotalAmount();
		// // for (InstorageItem det : task.getDetailList()) {
		// // AverageInventoryCostKey key = new AverageInventoryCostKey(det
		// // .getGoodsGuid());
		// // Double cost = context.find(Double.class, key);
		// // if (null != cost) {
		// // amount += DoubleUtil.mul(det.getPlanInstoCount(), cost);
		// // }
		// // }
		// if (0 == amount) {
		// return;
		// }
		// long date = new Date().getTime();
		// long date1 = date + 10;
		// CusdealTask cTask = new CusdealTask(task.getEntity().getCusproGuid(),
		// DealingsType.CUS_LSTH, DoubleUtil.sub(0, amount),
		// 0, task.getEntity().getRECID(), task.getEntity().getInstoNo());
		// cTask.setPubdate(date);
		// context.handle(cTask);
		//
		// CusdealTask dTask = new CusdealTask(task.getEntity().getCusproGuid(),
		// DealingsType.CUS_LSTK, 0,
		// DoubleUtil.sub(0, amount), task.getEntity().getRelaOrderGuid(),
		// task.getEntity().getRelaOrderNo());
		// dTask.setPubdate(date1);
		// dTask.setReceiptType(task.getDealingsWay());
		// context.handle(dTask);

	}

	/*
	 * 生成付款记录
	 * 
	 * @param task
	 * 
	 * @param context
	 */
	public void createPayBills(Context context, CheckInSheet sheet) {
		long today = new Date().getTime();
		CreatePaymentTask task = new CreatePaymentTask();
		CreatePaymentTask.Item item = task.new Item(sheet.getRECID(), sheet.getSheetNo(), sheet.getRelaBillsId(), sheet
				.getRelaBillsNo(), today, sheet.getAmount(), sheet.getAmount(), 0d);
		task.setId(context.newRECID());
		task.setAmount(sheet.getAmount());
		task.setPartnerId(sheet.getPartnerId());
		task.setPartnerName(sheet.getPartnerName());
		task.setPayDate(today);
		task.setPaymentType(PaymentType.PAY_CGFK.getCode());
		task.setRemark(sheet.getRemark());
		task.setItems(new Item[] { item });
		context.handle(task);
	}

	/**
	 * 对一个入库单实体添加基础数据
	 */
	private void fillEntity(Context context, Instorage entity, String type) {
		Login login = context.find(Login.class);
		Employee emp = context.find(Employee.class, login.getEmployeeId());
		entity.setRECID(context.newRECID());
		entity.setSheetType(type);
		entity.setCreateDate(new Date().getTime());
		if (CheckIsNull.isNotEmpty(entity.getPartnerId())) {
			Partner partner = context.find(Partner.class, entity.getPartnerId());
			if (null != partner) {
				entity.setPartnerName(partner.getName());
				entity.setPartnerShortName(partner.getShortName());
				entity.setPartnerCode(partner.getCode());
			}
		}
		Store store = context.find(Store.class, entity.getStoreId());
		if (null != store) {
			entity.setStoreName(store.getName());
			entity.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
		}
		if (CheckingInType.Irregular.getCode().equals(type) || CheckingInType.Kit.getCode().equals(type)) {
			entity.setCreatorId(login.getEmployeeId());
			entity.setCreator(emp.getName());
		}
	}

	/**
	 * 保存明细数据
	 */
	private void addDetails(Context context, Instorage entity, List<InstorageItem> detailList) throws Exception {
		if (null == detailList || detailList.isEmpty()) {
			throw new Exception("生成入库单失败(明细数据缺失)");
		}
		for (InstorageItem det : detailList) {
			det.setSheetId(entity.getRECID());
			det.setId(context.newRECID());
			context.handle(new InstorageItemTask(det), Method.INSERT);
		}
	}

	/**
	 * 更新采购在途
	 * 
	 * @param b
	 */
	private void modfiyCountOnWay(Context context, InstoAddTask data, boolean isPri) {
		for (InstorageItem det : data.getDetailList()) {
			GUID store = data.getEntity().getStoreId();
			if (isPri) {
				store = InstoConstant.PROVIDERSOTRE;
				return;
			}
			InventoryOnWayTask task = new InventoryOnWayTask(store, det.getGoodsId());
			task.setOnWayCount(det.getCount());
			context.handle(task);
		}
	}
}
