package com.spark.psi.inventory.service.checkout;

import java.util.ArrayList;
import java.util.List;

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
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.internal.entity.OtherGoods;
import com.spark.psi.inventory.internal.task.GetReceiptingCheckOutSheetKey;
import com.spark.psi.inventory.internal.task.LoadReceiptingCheckOutSheetKey;
import com.spark.psi.inventory.internal.task.TurnFlagOfCheckoutReceiptTask;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.publish.entity.CheckOutBaseInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.CheckOutBaseInfoItemImpl;
import com.spark.psi.inventory.intf.publish.entity.ReceiptingOrPayingItemImpl;
import com.spark.psi.inventory.intf.task.inventory.UpdateOtherGoodsTask;
import com.spark.psi.inventory.intf.task.outstorage.CreateCheckOutSheetTask;
import com.spark.psi.inventory.intf.task.outstorage.CreateCheckOutSheetTaskItem;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.account.entity.ReceiptingOrPayingItem;
import com.spark.psi.publish.constant.CheckoutReceiptedFlag;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfo;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfoItem;
import com.spark.psi.publish.inventory.checkout.task.KitCheckOutTask;
import com.spark.psi.publish.inventory.checkout.task.KitCheckOutTaskItem;

public class CheckOutSheetPublishService extends Service {

	protected CheckOutSheetPublishService() {
		super("com.spark.psi.inventory.service.checkout.CheckOutSheetPublishService");
	}

	/**
	 * 查询未关联收款单的出库单列表
	 */
	@Publish
	protected class ReceiptingCheckoutSheetProvider extends OneKeyResultListProvider<ReceiptingOrPayingItem, GetReceiptingCheckOutSheetKey> {

		@Override
		protected void provide(Context context, GetReceiptingCheckOutSheetKey key, List<ReceiptingOrPayingItem> resultList)
				throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckoutSheet.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID ");
			qb.addColumn("t.sheetNo", "sheetNo");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.checkoutDate", "checkoutDate");
			qb.addColumn("t.amount", "amount");

			qb.addArgs("pid", qb.guid, key.getPartnerId());
			qb.addEquals("t.partnerId", "@pid");
			if (key.getType() != null) {
				qb.addArgs("ttt", qb.STRING, key.getType().getCode());
				qb.addEquals("t.checkoutType", "@ttt");
			}
			qb.addArgs("flag", qb.STRING, CheckoutReceiptedFlag.NeverReceipted.getCode());
			qb.addEquals("t.receiptedFlag", "@flag");

			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				ReceiptingOrPayingItemImpl item = new ReceiptingOrPayingItemImpl();
				item.setSheetId(rs.getFields().get(0).getGUID());
				item.setSheetNo(rs.getFields().get(1).getString());
				item.setRelaBillsId(rs.getFields().get(2).getGUID());
				item.setRelaBillsNo(rs.getFields().get(3).getString());
				item.setCheckInOrOutDate(rs.getFields().get(4).getDate());
				item.setAmount(rs.getFields().get(5).getDouble());
				resultList.add(item);
			}
		}
	}

	/**
	 * 查询未关联收款单的出库单列表
	 */
	@Publish
	protected class LoadReceiptingCheckoutSheetProvider extends
			OneKeyResultProvider<ReceiptingOrPayingItem, LoadReceiptingCheckOutSheetKey> {

		@Override
		protected ReceiptingOrPayingItem provide(Context context, LoadReceiptingCheckOutSheetKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckoutSheet.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID ");
			qb.addColumn("t.sheetNo", "sheetNo");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.checkoutDate", "checkoutDate");
			qb.addColumn("t.amount", "amount");

			qb.addArgs("pid", qb.guid, key.getSheetId());
			qb.addEquals("t.RECID", "@pid");
			if (key.getType() != null) {
				qb.addArgs("ttt", qb.STRING, key.getType().getCode());
				qb.addEquals("t.checkoutType", "@ttt");
			}

			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				ReceiptingOrPayingItemImpl item = new ReceiptingOrPayingItemImpl();
				item.setSheetId(rs.getFields().get(0).getGUID());
				item.setSheetNo(rs.getFields().get(1).getString());
				item.setRelaBillsId(rs.getFields().get(2).getGUID());
				item.setRelaBillsNo(rs.getFields().get(3).getString());
				item.setCheckInOrOutDate(rs.getFields().get(4).getDate());
				item.setAmount(rs.getFields().get(5).getDouble());
				return (item);
			}
			return null;
		}
	}

	/**
	 * 改变出库单的收款状态
	 */
	@Publish
	protected class TurnSheetFlagHandle extends SimpleTaskMethodHandler<TurnFlagOfCheckoutReceiptTask> {

		@Override
		protected void handle(Context context, TurnFlagOfCheckoutReceiptTask task) throws Throwable {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Inventory.CheckoutSheet.getTableName());
			ub.addColumn("receiptedFlag", ub.STRING, CheckoutReceiptedFlag.Receipting.getCode());

			ub.addCondition("id", ub.guid, task.getId(), "t.RECID = @id");
			ub.addCondition("flag", ub.STRING, CheckoutReceiptedFlag.NeverReceipted.getCode(), "t.receiptedFlag = @flag");

			int count = ub.execute();
			if (count != 1) {
				throw new Exception("数据已经发生了改变，请刷新重试！");
			}
		}
	}

	@Publish
	protected class CheckOutSheetInfoProvider extends OneKeyResultProvider<CheckOutBaseInfo, GUID> {

		@Override
		protected CheckOutBaseInfo provide(Context context, GUID key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckoutSheet.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.sheetNo", "sheetNo");
			qb.addColumn("t.checkoutType", "checkoutType");
			qb.addColumn("t.partnerId", "partnerId");
			qb.addColumn("t.partnerName", "partnerName");
			qb.addColumn("t.namePY", "namePY");
			qb.addColumn("t.partnerShortName", "partnerShortName");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.storeId", "storeId");
			qb.addColumn("t.storeName", "storeName");
			qb.addColumn("t.storeNamePY", "storeNamePY");
			qb.addColumn("t.goodsFrom", "goodsFrom");
			qb.addColumn("t.goodsUse", "goodsUse");
			qb.addColumn("t.takePerson", "takePerson");
			qb.addColumn("t.takeUnit", "takeUnit");
			qb.addColumn("t.vouchersNo", "vouchersNo");
			qb.addColumn("t.remark", "remark");
			qb.addColumn("t.amount", "amount");
			qb.addColumn("t.receiptedAmount", "receiptedAmount");
			qb.addColumn("t.receiptedFlag", "receiptedFlag");
			qb.addColumn("t.checkoutDate", "checkoutDate");
			qb.addColumn("t.checkoutPerson", "checkoutPerson");
			qb.addColumn("t.checkoutPersonName", "checkoutPersonName");
			qb.addColumn("t.deptId", "deptId");
			qb.addColumn("t.isReceipting", "isReceipting");
			qb.addColumn("t.creatorId", "creatorId");
			qb.addColumn("t.creator", "creator");

			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.RECID", "@id");
			CheckOutBaseInfoImpl info = null;
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				info = new CheckOutBaseInfoImpl();
				int index = 0;
				info.setRECID(rs.getFields().get(index++).getGUID());
				info.setSheetNo(rs.getFields().get(index++).getString());
				info.setCheckoutType(rs.getFields().get(index++).getString());
				info.setPartnerId(rs.getFields().get(index++).getGUID());
				info.setPartnerName(rs.getFields().get(index++).getString());
				info.setNamePY(rs.getFields().get(index++).getString());
				info.setPartnerShortName(rs.getFields().get(index++).getString());
				info.setRelaBillsId(rs.getFields().get(index++).getGUID());
				info.setRelaBillsNo(rs.getFields().get(index++).getString());
				info.setStoreId(rs.getFields().get(index++).getGUID());
				info.setStoreName(rs.getFields().get(index++).getString());
				info.setStoreNamePY(rs.getFields().get(index++).getString());
				info.setGoodsFrom(rs.getFields().get(index++).getString());
				info.setGoodsUse(rs.getFields().get(index++).getString());
				info.setTakePerson(rs.getFields().get(index++).getString());
				info.setTakeUnit(rs.getFields().get(index++).getString());
				info.setVouchersNo(rs.getFields().get(index++).getString());
				info.setRemark(rs.getFields().get(index++).getString());
				info.setAmount(rs.getFields().get(index++).getDouble());
				info.setReceiptedAmount(rs.getFields().get(index++).getDouble());
				info.setReceiptedFlag(rs.getFields().get(index++).getString());
				info.setCheckoutDate(rs.getFields().get(index++).getDate());
				info.setCheckoutPerson(rs.getFields().get(index++).getGUID());
				info.setCheckoutPersonName(rs.getFields().get(index++).getString());
				info.setDeptId(rs.getFields().get(index++).getGUID());
				info.setReceipting(rs.getFields().get(index++).getBoolean());
				info.setCreatorId(rs.getFields().get(index++).getGUID());
				info.setCreator(rs.getFields().get(index++).getString());
				info.setItems(getitems(context, key));
			}
			return info;
		}

		private List<CheckOutBaseInfoItem> getitems(Context context, GUID key) {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckoutSheet_Det.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.sheetId", "sheetId");
			qb.addColumn("t.goodsId", "goodsId");
			qb.addColumn("t.goodsCode", "goodsCode");
			qb.addColumn("t.goodsNo", "goodsNo");
			qb.addColumn("t.goodsName", "goodsName");
			qb.addColumn("t.goodsSpec", "goodsSpec");
			qb.addColumn("t.unit", "unit");
			qb.addColumn("t.scale", "scale");
			qb.addColumn("t.price", "price");
			qb.addColumn("t.amount", "amount");
			qb.addColumn("t.realCount", "realCount");
			qb.addColumn("t.avgCost", "avgCost");
			qb.addColumn("t.createPerson", "createPerson");
			qb.addColumn("t.createDate", "createDate");

			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.sheetId", "@id");

			RecordSet rs = qb.getRecord();
			List<CheckOutBaseInfoItem> items = new ArrayList<CheckOutBaseInfoItem>();
			while (rs.next()) {
				CheckOutBaseInfoItemImpl item = new CheckOutBaseInfoItemImpl();
				int index = 0;
				item.setRECID(rs.getFields().get(index++).getGUID());
				item.setSheetId(rs.getFields().get(index++).getGUID());
				item.setGoodsId(rs.getFields().get(index++).getGUID());
				item.setGoodsCode(rs.getFields().get(index++).getString());
				item.setGoodsNo(rs.getFields().get(index++).getString());
				item.setGoodsName(rs.getFields().get(index++).getString());
				item.setGoodsSpec(rs.getFields().get(index++).getString());
				item.setUnit(rs.getFields().get(index++).getString());
				item.setScale(rs.getFields().get(index++).getInt());
				item.setPrice(rs.getFields().get(index++).getDouble());
				item.setAmount(rs.getFields().get(index++).getDouble());
				item.setRealCount(rs.getFields().get(index++).getDouble());
				item.setAvgCost(rs.getFields().get(index++).getDouble());
				item.setCreatePerson(rs.getFields().get(index++).getString());
				item.setCreateDate(rs.getFields().get(index++).getDate());
				items.add(item);
			}
			return items;
		}

	}

	@Publish
	protected class KitCheckOutHandle extends SimpleTaskMethodHandler<KitCheckOutTask> {

		@Override
		protected void handle(Context context, KitCheckOutTask task) throws Throwable {
			String sheetNo = context.get(String.class, SheetNumberType.Checkout);
			CreateCheckOutSheetTask sheet = new CreateCheckOutSheetTask();
			sheet.setRECID(context.newRECID());
			sheet.setCheckoutType(CheckingOutType.Kit.getCode());
			sheet.setRemark(task.getRemark());
			Store store = context.find(Store.class, task.getStoreId());
			sheet.setStoreId(task.getStoreId());
			sheet.setStoreName(store.getName());
			sheet.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
			sheet.setSheetNo(sheetNo);
			sheet.setTakePerson(task.getTakePerson());
			sheet.setTakeUnit(task.getTakeUnit());
			sheet.setVouchersNo(task.getVoucherNumber());
			sheet.setGoodsFrom(task.getGoodsFrom());
			sheet.setGoodsUse(task.getGoodsUse());
			List<CreateCheckOutSheetTaskItem> items = new ArrayList<CreateCheckOutSheetTaskItem>();
			for (KitCheckOutTaskItem item : task.getItems()) {
				CreateCheckOutSheetTaskItem det = new CreateCheckOutSheetTaskItem();
				det.setRECID(context.newRECID());
				det.setGoodsSpec(item.getKitDesp());
				det.setUnit(item.getKitUnit());
				det.setScale(0);
				det.setSheetId(sheet.getRECID());
				det.setGoodsName(item.getKitName());
				det.setPrice(0);
				det.setRealCount(item.getCount());
				det.setAmount(0);
				items.add(det);
			}
			sheet.setItems(items);
			context.handle(sheet);
			// 更新其他库存
			if (sheet.isSuccess()) {
				addOtherStorage(context, sheet);
			}
		}
	}

	public void addOtherStorage(Context context, CreateCheckOutSheetTask sheet) {
		for (CreateCheckOutSheetTaskItem det : sheet.getItems()) {
			OtherGoods otherGoods = new OtherGoods();
			otherGoods.setName(det.getGoodsName());
			otherGoods.setDescription(det.getGoodsSpec());
			otherGoods.setUnit(det.getUnit());
			otherGoods.setNumber(DoubleUtil.sub(0, det.getRealCount()));
			UpdateOtherGoodsTask otherTask = new UpdateOtherGoodsTask(sheet.getStoreId(), otherGoods);
			otherTask.setInit(false);
			context.handle(otherTask, Method.MODIFY);
		}
	}
}
