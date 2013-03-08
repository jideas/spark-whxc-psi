package com.spark.psi.inventory.service.outstorage;

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
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.account.intf.task.pub.CusdealTask;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.Store;
import com.spark.psi.base.key.store.GetUserStoreGuidsKey;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.inventory.intf.event.CheckOutEvent;
import com.spark.psi.inventory.intf.publish.checkout.entity.CheckoutSheetItemImpl;
import com.spark.psi.inventory.intf.task.outstorage.CreateCheckOutSheetTask;
import com.spark.psi.inventory.intf.task.outstorage.CreateCheckOutSheetTaskItem;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.constant.CheckoutReceiptedFlag;
import com.spark.psi.publish.inventory.checkout.entity.CheckoutSheetItem;
import com.spark.psi.publish.inventory.checkout.task.SureCheckOutTask;
import com.spark.psi.publish.inventory.checkout.task.SureCheckOutTaskItem;
import com.spark.psi.publish.inventory.entity.AllreadyAmountAndCount;
import com.spark.psi.publish.inventory.key.GetBillsAllreadyAmountAndCountKey;
import com.spark.psi.publish.inventory.key.GetCheckingOutListKey;
import com.spark.psi.publish.inventory.sheet.task.GiftCheckoutTask;
import com.spark.psi.publish.inventory.sheet.task.GiftCheckoutTaskItem;
import com.spark.psi.publish.inventory.sheet.task.JointCheckoutTask;
import com.spark.psi.publish.inventory.sheet.task.JointCheckoutTaskItem;
import com.spark.psi.publish.inventory.sheet.task.SureCheckInTask.CheckInError;

public class CheckOutPublishService extends Service {

	protected CheckOutPublishService() {
		super("com.spark.psi.inventory.service.outstorage.CheckOutPublishService");
	}

	/**
	 * 确认出库
	 */
	@Publish
	protected class SureCheckOutTaskHandle extends SimpleTaskMethodHandler<SureCheckOutTask> {

		@Override
		protected void handle(Context context, SureCheckOutTask t) throws Throwable {
			// 查询关联中间表数据
			Outstorage info = context.find(Outstorage.class, t.getId());
			if (null == info) {
				t.setSuccess(false);
				return;
			}
			List<OutstorageItem> list = context.getList(OutstorageItem.class, t.getId());
			Map<GUID, OutstorageItem> map = new HashMap<GUID, OutstorageItem>();
			for (OutstorageItem item : list) {
				if (DoubleUtil.sub(item.getPlanCount(), item.getCheckoutCount()) == 0) {
					continue;
				}
				map.put(item.getRECID(), item);
			}
			// 验证是否可以出库，仓库状态，出口数量，库存数量
			AllreadyAmountAndCount data = context.find(AllreadyAmountAndCount.class, GetBillsAllreadyAmountAndCountKey
					.getCheckOutKey(info.getRelaBillsId()));
			// 生成出库单及明细
			CreateCheckOutSheetTask task = new CreateCheckOutSheetTask();
			task.setRECID(context.newRECID());
			task.setCheckoutType(info.getSheetType());
			task.setGoodsFrom(t.getGoodsFrom());
			task.setGoodsUse(t.getGoodsUse());
			task.setIsReceipting(false);
			task.setPartnerId(info.getPartnerId());
			task.setPartnerName(info.getPartnerName());
			task.setPartnerShortName(info.getPartnerShortName());
			task.setReceiptedAmount(0);
			task.setReceiptedFlag(CheckoutReceiptedFlag.NeverReceipted.getCode());
			task.setNamePY(PinyinHelper.getLetter(info.getPartnerName()));
			task.setRelaBillsId(info.getRelaBillsId());
			task.setRelaBillsNo(info.getRelaBillsNo());
			task.setRemark(info.getRemark());
			String sheetNo = context.get(String.class, SheetNumberType.Checkout);
			task.setSheetNo(sheetNo);
			task.setStoreId(info.getStoreId());
			task.setStoreName(info.getStoreName());
			task.setStoreNamePY(PinyinHelper.getLetter(info.getStoreName()));
			task.setTakePerson(t.getDeliveryPerson());
			task.setTakeUnit(t.getDeliveryDepartment());
			task.setVouchersNo(t.getVoucherNumber());
			double amount = 0;
			List<CreateCheckOutSheetTaskItem> items = new ArrayList<CreateCheckOutSheetTaskItem>();
			double count = 0;
			for (int i = 0; i < t.getItems().size(); i++) {
				SureCheckOutTaskItem oi = t.getItems().get(i);
				OutstorageItem det = map.get(oi.getId());
				if (det.getPlanCount() < DoubleUtil.sum(det.getCheckoutCount(), oi.getCount())) {
					throw new Exception(det.getGoodsName() + "出库数量超出订单数量，请核对！");
				}
				CreateCheckOutSheetTaskItem item = new CreateCheckOutSheetTaskItem();
				item.setRECID(context.newRECID());
				item.setGoodsCode(det.getGoodsCode());
				item.setGoodsId(det.getGoodsId());
				item.setGoodsName(det.getGoodsName());
				item.setGoodsNo(det.getGoodsNo());
				item.setGoodsSpec(det.getGoodsSpec());
				item.setPrice(det.getPrice());
				item.setRealCount(oi.getCount());
				det.setThisTimeCount(oi.getCount());
				item.setRECID(context.newRECID());
				item.setScale(det.getScale());
				item.setSheetId(task.getRECID());
				item.setUnit(det.getUnit());
				count = DoubleUtil.sum(item.getRealCount(), count);
				if (!CheckingOutType.getCheckingOutType(task.getCheckoutType()).isMaterialTakeOrReturn()
						&& i == t.getItems().size() - 1 && data != null
						&& data.getBillCount() == DoubleUtil.sum(data.getAllreadyCount(), count)) {
					// 判断是否是单据的最后一次入库，是则计算金额
					item.setAmount(DoubleUtil.sub(data.getBillAmount(), DoubleUtil
							.sum(amount, data.getAllreadyAmount())));
				} else {
					item.setAmount(DoubleUtil.mul(oi.getCount(), item.getPrice()));
				}
				amount = DoubleUtil.sum(item.getAmount(), amount);
				items.add(item);
			}
			task.setItems(items);

			task.setAmount(amount);
			context.handle(task);
			if (!task.isSuccess()) {
				throw new Exception("生成出库单出现不可预知错误，请重试！");
			}
			// 更新库存
			for (CreateCheckOutSheetTaskItem item : task.getItems()) {
				// 更新库存和成本
				CheckOutInternalService.modfiyMaterialsStorage(context, task, item, true, t.getInventoryItems(),
						InventoryType.Materials);
			}
			// 库存流水
			CheckOutInternalService.doWrightStream(context, task, InventoryType.Materials);
			// 应收应付
			if (!CheckingOutType.getCheckingOutType(task.getCheckoutType()).isMaterialTakeOrReturn()) {
				DealingsType dealingsType = DealingsType.CUS_XSCK;
				if (task.getCheckoutType().equals(CheckingOutType.Return.getCode())) {
					dealingsType = DealingsType.PRO_THCK;
				}
				doUpdateDealing(context, task, dealingsType);
			}
			// 更改中间表状态
			doUpdateCheckingout(context, task, info, data, list);
			// 确认出库事件
			CheckOutEvent event = new CheckOutEvent(info.getRECID());
			event.setCheckOutLogId(task.getRECID());
			event.setRelaOrderId(task.getRelaBillsId());
			event.setType(CheckingOutType.getCheckingOutType(task.getCheckoutType()));
			context.dispatch(event);
			t.setSuccess(true);
		}
	}

	/**
	 * 更改出库中间表状态
	 */
	private void doUpdateCheckingout(Context context, CreateCheckOutSheetTask task, Outstorage info,
			AllreadyAmountAndCount data, List<OutstorageItem> list) throws Exception {
		double count = 0;
		boolean isFinished = true;
		for (OutstorageItem item : list) {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Inventory.Checkingout_Det.getTableName());
			ub.addColumn("checkoutCount", ub.DOUBLE, item.getCheckoutCount() + item.getThisTimeCount());
			ub.addCondition("oldcount", ub.DOUBLE, item.getCheckoutCount(), "t.checkoutCount = @oldcount");
			ub.addCondition("rid", ub.guid, item.getRECID(), "t.RECID=@rid");
			count = DoubleUtil.sum(item.getThisTimeCount(), count);
			int i = ub.execute();
			if (1 != i) {
				throw new Exception(CheckInError.DataChangedError.getMessage());
			}
			if (isFinished) {
				isFinished = item.getPlanCount() == DoubleUtil.sum(item.getCheckoutCount(), item.getThisTimeCount());
			}
		}
		UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
		ub.setTable(ERPTableNames.Inventory.Checkingout.getTableName());
		if (isFinished
		// && (data.getBillCount() == DoubleUtil.sum(data.getAllreadyCount(),
		// count) || CheckingOutType
		// .getCheckingOutType(task.getCheckoutType()).isMaterialTakeOrReturn())
		) {
			info.setStatus(CheckingOutStatus.Finish.getCode());
			ub.addColumn("status", ub.STRING, CheckingOutStatus.Finish.getCode());
		} else {
			ub.addColumn("status", ub.STRING, CheckingOutStatus.Part.getCode());
		}
		ub.addCondition("rid", ub.guid, info.getRECID(), "t.RECID=@rid");
		ub.execute();
	}

	/**
	 * 更新应收应付信息
	 */
	public void doUpdateDealing(Context context, CreateCheckOutSheetTask task, DealingsType type) {
		double amount = task.getAmount();
		if (type == DealingsType.PRO_THCK) {
			amount = DoubleUtil.sub(0, amount);
		}
		CusdealTask cTask = new CusdealTask(task.getPartnerId(), type, amount, 0, task.getRECID(), task.getSheetNo());
		cTask.setPubdate(System.currentTimeMillis());
		context.handle(cTask);
	}

	/**
	 * 查询出库列表
	 */
	@Publish
	protected class GetCheckingOutListProvider extends
			OneKeyResultListProvider<CheckoutSheetItem, GetCheckingOutListKey> {

		@Override
		protected void provide(Context context, GetCheckingOutListKey key, List<CheckoutSheetItem> resultList)
				throws Throwable {
			Login login = context.find(Login.class);
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckoutSheet.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID ");
			qb.addColumn("t.sheetNo", "sheetNo");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.checkoutDate", "checkoutDate");
			qb.addColumn("t.checkoutPersonName", "checkoutPersonName");
			qb.addColumn("t.storeName", "storeName");
			qb.addColumn("t.checkoutType", "checkoutType");

			qb.addArgs("citype", qb.STRING, CheckingOutType.RealGoods.getCode());
			qb.addArgs("citype1", qb.STRING, CheckingOutType.GoodsSplit.getCode());
			if (key.isRealGoods()||key.isGoodsSplit()) {
				if(key.isRealGoods())
				qb.addEquals("t.checkoutType", "@citype");
				else
					qb.addEquals("t.checkoutType", "@citype1");
			} else {
				qb.addNotEquals("t.checkoutType", "@citype");
				qb.addNotEquals("t.checkoutType", "@citype1");
			}
			// 权限
			if (login.hasAuth(Auth.Boss) || key.isRealGoods()) {

			} else {
				StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.ONCOUNTING,
						StoreStatus.STOP, StoreStatus.STOPANDONCOUNTING };
				GUID[] storeIds = context.find(GUID[].class, new GetUserStoreGuidsKey(statuss));
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < storeIds.length; i++) {
					list.add("@sid" + i);
					qb.addArgs("sid" + i, qb.guid, storeIds[i]);
				}
				qb.addIn("t.storeId", list);
			}
			// 搜索
			if (CheckIsNull.isNotEmpty(key.getSearchText())) {
				StringBuilder sb = new StringBuilder();
				qb.addArgs("searchKey", qb.STRING, key.getSearchText());
				sb.append(" (t.sheetNo like '%'+@searchKey+'%' ");
				sb.append(" or t.relaBillsNo like '%'+@searchKey+'%' ");
				sb.append(" or t.storeName like '%'+@searchKey+'%' ");
				sb.append(" or t.checkoutPersonName like '%'+@searchKey+'%' ");
				sb.append(")");
				qb.addCondition(sb.toString());
			}
			// 时间段
			if (key.getQueryTerm() != null) {
				qb.addArgs("beginTime", qb.DATE, key.getQueryTerm().getStartTime());
				qb.addArgs("endTime", qb.DATE, key.getQueryTerm().getEndTime());
				qb.addBetween("t.checkoutDate", "@beginTime", "@endTime");
			}

			// 排序
			if (key.getSortField() != null) {
				if (key.getSortType() == SortType.Desc) {
					qb.addOrderBy(key.getSortField().getFieldName() + " desc ");
				} else {
					qb.addOrderBy(key.getSortField().getFieldName() + " asc ");
				}
			} else {
				qb.addOrderBy("t.checkoutDate desc");
			}
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			while (rs.next()) {
				CheckoutSheetItemImpl item = new CheckoutSheetItemImpl();
				item.setId(rs.getFields().get(0).getGUID());
				item.setBillsNo(rs.getFields().get(1).getString());
				item.setRelaBillsId(rs.getFields().get(2).getGUID());
				item.setRelaBillsNo(rs.getFields().get(3).getString());
				item.setCheckoutDate(rs.getFields().get(4).getDate());
				item.setCheckoutPersonName(rs.getFields().get(5).getString());
				item.setStoreName(rs.getFields().get(6).getString());
				item.setSheetType(CheckingOutType.getCheckingOutType(rs.getFields().get(7).getString()));
				resultList.add(item);
			}
		}
	}

	/**
	 * 联营出库
	 */
	@Publish
	protected class JointCheckoutHandle extends SimpleTaskMethodHandler<JointCheckoutTask> {

		@Override
		protected void handle(Context context, JointCheckoutTask info) throws Throwable {
			 
			CreateCheckOutSheetTask task = new CreateCheckOutSheetTask();
			task.setRECID(context.newRECID());
			task.setCheckoutType(CheckingOutType.Joint.getCode());
			task.setTakePerson(info.getDeliveryPerson());
			task.setTakeUnit(info.getDeliveryDepartment());
			task.setVouchersNo(info.getVoucherNumber());
			task.setIsReceipting(true);
			task.setReceiptedAmount(0);
			task.setReceiptedFlag(CheckoutReceiptedFlag.FinishedReceipt.getCode());
			task.setRemark(info.getRemark());
			String sheetNo = context.get(String.class, SheetNumberType.Checkout);
			task.setSheetNo(sheetNo);
			task.setStoreId(info.getStoreId());
			Store store = context.find(Store.class, info.getStoreId());
			task.setStoreName(store.getName());
			task.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
			List<CreateCheckOutSheetTaskItem> items = new ArrayList<CreateCheckOutSheetTaskItem>();
			double count = 0;
			for (int i = 0; i < info.getItems().size(); i++) {
				JointCheckoutTaskItem det = info.getItems().get(i);
				CreateCheckOutSheetTaskItem item = new CreateCheckOutSheetTaskItem();
				item.setRECID(context.newRECID());
				MaterialsItem goods = context.find(MaterialsItem.class, det.getGoodsId());
				item.setGoodsCode(goods.getMaterialCode());
				item.setGoodsId(det.getGoodsId());
				item.setGoodsName(goods.getMaterialName());
				item.setGoodsNo(goods.getMaterialNo());
				item.setGoodsSpec(goods.getSpec());
				item.setPrice(0);
				item.setRealCount(det.getCount());
				item.setRECID(context.newRECID());
				item.setScale(2);
				item.setSheetId(task.getRECID());
				item.setUnit(goods.getMaterialUnit());
				count = DoubleUtil.sum(item.getRealCount(), count);
				items.add(item);
			}
			task.setItems(items);
			context.handle(task);
			if (!task.isSuccess()) {
				throw new Exception("生成出库单出现不可预知错误，请重试！");
			}
			// 更新库存
			for (CreateCheckOutSheetTaskItem item : task.getItems()) {
				// 更新库存和成本
				CheckOutInternalService.modfiyMaterialsStorage(context, task, item, false, info.getInventoryItems(),
						InventoryType.Materials);
			}
			// 库存流水
			CheckOutInternalService.doWrightStream(context, task, InventoryType.Materials);
		}
	}
	
	
	@Publish
	protected class GiftCheckoutHandle extends SimpleTaskMethodHandler<GiftCheckoutTask> {
		@Override
		protected void handle(Context context, GiftCheckoutTask info) throws Throwable {

			CreateCheckOutSheetTask task = new CreateCheckOutSheetTask();
			task.setRECID(context.newRECID());
			task.setCheckoutType(CheckingOutType.Gift.getCode());
			task.setTakePerson(info.getDeliveryPerson());
			task.setTakeUnit(info.getDeliveryDepartment());
			task.setVouchersNo(info.getVoucherNumber());
			task.setIsReceipting(true);
			task.setReceiptedAmount(0);
			task.setReceiptedFlag(CheckoutReceiptedFlag.FinishedReceipt.getCode());
			task.setRemark(info.getRemark());
			String sheetNo = context.get(String.class, SheetNumberType.Checkout);
			task.setSheetNo(sheetNo);
			task.setStoreId(info.getStoreId());
			Store store = context.find(Store.class, info.getStoreId());
			task.setStoreName(store.getName());
			task.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
			List<CreateCheckOutSheetTaskItem> items = new ArrayList<CreateCheckOutSheetTaskItem>();
			double count = 0;
			for (int i = 0; i < info.getItems().size(); i++) {
				GiftCheckoutTaskItem det = info.getItems().get(i);
				CreateCheckOutSheetTaskItem item = new CreateCheckOutSheetTaskItem();
				item.setRECID(context.newRECID());
				MaterialsItem goods = context.find(MaterialsItem.class, det.getGoodsId());
				item.setGoodsCode(goods.getMaterialCode());
				item.setGoodsId(det.getGoodsId());
				item.setGoodsName(goods.getMaterialName());
				item.setGoodsNo(goods.getMaterialNo());
				item.setGoodsSpec(goods.getSpec());
				item.setPrice(0);
				item.setRealCount(det.getCount());
				item.setRECID(context.newRECID());
				item.setScale(2);
				item.setSheetId(task.getRECID());
				item.setUnit(goods.getMaterialUnit());
				count = DoubleUtil.sum(item.getRealCount(), count);
				items.add(item);
			}
			task.setItems(items);
			context.handle(task);
			if (!task.isSuccess()) {
				throw new Exception("生成出库单出现不可预知错误，请重试！");
			}
			// 更新库存
			for (CreateCheckOutSheetTaskItem item : task.getItems()) {
				// 更新库存和成本
				CheckOutInternalService.modfiyMaterialsStorage(context, task, item, false, info.getDistributeInventoryItems(),
						InventoryType.Materials);
			}
			// 库存流水
			CheckOutInternalService.doWrightStream(context, task, InventoryType.Materials);
		}
		
	}

}
