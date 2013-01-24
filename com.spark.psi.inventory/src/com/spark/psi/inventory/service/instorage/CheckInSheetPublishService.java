package com.spark.psi.inventory.service.instorage;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.base.Login;
import com.spark.psi.base.key.store.GetUserStoreGuidsKey;
import com.spark.psi.inventory.internal.task.GetReceiptingCheckOutSheetKey;
import com.spark.psi.inventory.internal.task.LoadReceiptingCheckOutSheetKey;
import com.spark.psi.inventory.intf.publish.entity.CheckInBaseInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.CheckInBaseInfoItemImpl;
import com.spark.psi.inventory.intf.publish.entity.ReceiptingOrPayingItemImpl;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.ReceiptType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.account.entity.ReceiptingOrPayingItem;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;
import com.spark.psi.publish.inventory.checkin.entity.CheckInSheetShowItem;
import com.spark.psi.publish.inventory.checkin.key.GetReceiptingInventorySheetKey;
import com.spark.psi.publish.inventory.checkin.key.LoadReceiptingInventorySheetKey;
import com.spark.psi.publish.inventory.entity.CheckInSheetShowItemImpl;
import com.spark.psi.publish.inventory.key.GetCheckingInListKey;
import com.spark.psi.publish.inventory.sheet.task.InspectCheckinTask;
import com.spark.psi.publish.inventory.sheet.task.InspectCheckinTask.InspectItem;

public class CheckInSheetPublishService extends Service {

	protected CheckInSheetPublishService() {
		super("com.spark.psi.inventory.service.instorage.CheckInSheetPublishService");
	}

	/**
	 * 查询入库记录页签展现内容
	 */
	@Publish
	protected class CheckInSheetShowItemProvider extends
			OneKeyResultListProvider<CheckInSheetShowItem, GetCheckingInListKey> {
		@Override
		protected void provide(Context context, GetCheckingInListKey key, List<CheckInSheetShowItem> resultList)
				throws Throwable {
			Login login = context.find(Login.class);
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckinSheet.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.sheetNo", "sheetNo");
			qb.addColumn("t.sheetType", "sheetType");
			qb.addColumn("t.checkinPersonName", "checkinPersonName");
			qb.addColumn("t.checkinDate", "checkinDate");
			qb.addColumn("t.storeName", "storeName");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			// 类型
			qb.addArgs("citype", qb.STRING, CheckingInType.RealGoods.getCode());
			if (key.isRealGoods()) {
				qb.addEquals("t.sheetType", "@citype");
			} else {
				qb.addNotEquals("t.sheetType", "@citype");
			}
			// 权限
			if (login.hasAuth(Auth.Boss)||key.isRealGoods()) {

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
				sb.append(" or t.checkinPersonName like '%'+@searchKey+'%' ");
				sb.append(")");
				qb.addCondition(sb.toString());
			}
			// 时间段
			if (key.getQueryTerm() != null) {
				qb.addArgs("beginTime", qb.DATE, key.getQueryTerm().getStartTime());
				qb.addArgs("endTime", qb.DATE, key.getQueryTerm().getEndTime());
				qb.addBetween("t.checkinDate", "@beginTime", "@endTime");
			}

			// 排序
			if (key.getSortField() != null) {
				if (key.getSortType() == SortType.Desc) {
					qb.addOrderBy(key.getSortField().getFieldName() + " desc ");
				} else {
					qb.addOrderBy(key.getSortField().getFieldName() + " asc ");
				}
			} else {
				qb.addOrderBy("t.checkinDate desc");
			}
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			while (rs.next()) {
				CheckInSheetShowItemImpl item = new CheckInSheetShowItemImpl();
				item.setId(rs.getFields().get(0).getGUID());
				item.setSheetNo(rs.getFields().get(01).getString());
				item.setType(CheckingInType.getCheckingInType(rs.getFields().get(02).getString()));
				item.setCheckinPersonName(rs.getFields().get(03).getString());
				item.setCheckinDate(rs.getFields().get(04).getDate());
				item.setStoreName(rs.getFields().get(05).getString());
				item.setRelaBillsNo(rs.getFields().get(06).getString());
				resultList.add(item);
			}

		}

	}

	@Publish
	protected class CheckInBaseInfoProvider extends OneKeyResultProvider<CheckInBaseInfo, GUID> {
		@Override
		protected CheckInBaseInfo provide(Context context, GUID key) throws Throwable {
			CheckInBaseInfoImpl info = new CheckInBaseInfoImpl();
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckinSheet.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.sheetNo", "sheetNo");
			qb.addColumn("t.sheetType", "sheetType");
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
			qb.addColumn("t.remark", "remark");
			qb.addColumn("t.buyPerson", "buyPerson");
			qb.addColumn("t.buyDate", "buyDate");
			qb.addColumn("t.amount", "amount");
			qb.addColumn("t.askedAmount", "askedAmount");
			qb.addColumn("t.paidAmount", "paidAmount");
			qb.addColumn("t.disAmount", "disAmount");
			qb.addColumn("t.checkinDate", "checkinDate");
			qb.addColumn("t.checkinPerson", "checkinPerson");
			qb.addColumn("t.checkinPersonName", "checkinPersonName");
			qb.addColumn("t.deptId", "deptId");
			qb.addColumn("t.creatorId", "creatorId");
			qb.addColumn("t.creator", "creator");

			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.RECID", "@id");

			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				int index = 0;
				info.setId(rs.getFields().get(index++).getGUID());
				info.setSheetNo(rs.getFields().get(index++).getString());
				info.setSheetType(CheckingInType.getCheckingInType(rs.getFields().get(index++).getString()));
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
				info.setRemark(rs.getFields().get(index++).getString());
				info.setBuyPerson(rs.getFields().get(index++).getString());
				info.setBuyDate(rs.getFields().get(index++).getDate());
				info.setAmount(rs.getFields().get(index++).getDouble());
				info.setAskedAmount(rs.getFields().get(index++).getDouble());
				info.setPaidAmount(rs.getFields().get(index++).getDouble());
				info.setDisAmount(rs.getFields().get(index++).getDouble());
				info.setCheckinDate(rs.getFields().get(index++).getDate());
				info.setCheckinPerson(rs.getFields().get(index++).getGUID());
				info.setCheckinPersonName(rs.getFields().get(index++).getString());
				info.setDeptId(rs.getFields().get(index++).getGUID());
				info.setCreatorId(rs.getFields().get(index++).getGUID());
				info.setCreator(rs.getFields().get(index++).getString());
				info.setItems(queryItems(context, key));
			}
			return info;
		}

		private List<CheckInBaseInfoItem> queryItems(Context context, GUID key) {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckinSheet_Det.getTableName(), "t");
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

			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.sheetId", "@id");

			List<CheckInBaseInfoItem> items = new ArrayList<CheckInBaseInfoItem>();
			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				int index = 0;
				CheckInBaseInfoItemImpl item = new CheckInBaseInfoItemImpl();
				item.setId(rs.getFields().get(index++).getGUID());
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
				items.add(item);
			}
			return items;
		}
	}

	@Publish
	protected class InspectCheckinHandle extends SimpleTaskMethodHandler<InspectCheckinTask> {

		@Override
		protected void handle(Context context, InspectCheckinTask task) throws Throwable {
			for (InspectItem item : task.getItems()) {
				UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
				ub.setTable(ERPTableNames.Inventory.Checkingin_Det.getTableName());
				double value = DoubleUtil.sub(item.getOldInspectCount(), item.getCheckinCount());
				if (task.isReceipt()) {
					value = DoubleUtil.sum(item.getCheckinCount(), item.getOldInspectCount());
					if (value == 0) {
						throw new Exception("数据错误，请检查！");
					}
				}

				ub.addColumn("inspectCount", ub.DOUBLE, value);

				ub.addCondition("id", ub.guid, item.getId(), "t.RECID=@id");
				ub.addCondition("icount", ub.DOUBLE, item.getOldInspectCount(), "t.inspectCount = @icount");
				if (ub.execute() == 0) {
					throw new Exception("数据操作冲突，请检查！");
				}
			}
			task.setSuccess(true);
		}
	}

	@Publish
	protected class GetPayingCheckinSheetProvider extends
			OneKeyResultListProvider<ReceiptingOrPayingItem, GetReceiptingInventorySheetKey> {

		@Override
		protected void provide(Context context, GetReceiptingInventorySheetKey key, List<ReceiptingOrPayingItem> list)
				throws Throwable {
			CheckingInType checkinType = null;
			if (key.getPaymentType() != null) {
				if (key.getPaymentType() == PaymentType.PAY_XSTK) {
					checkinType = CheckingInType.Return;
				} else if (key.getPaymentType() == PaymentType.PAY_CGFK) {
					checkinType = CheckingInType.Purchase;
				} else {
					return;
				}
			} else if (key.getReceiptType() != null) {
				if (key.getReceiptType() == ReceiptType.RECEIPT_CGTK) {
					list.addAll(context.getList(ReceiptingOrPayingItem.class, new GetReceiptingCheckOutSheetKey(key
							.getPartnerId(), CheckingOutType.Return)));
					return;
				} else if (key.getReceiptType() == ReceiptType.RECEIPT_XSHK) {
					list.addAll(context.getList(ReceiptingOrPayingItem.class, new GetReceiptingCheckOutSheetKey(key
							.getPartnerId(), CheckingOutType.Sales)));
					return;
				} else {
					return;
				}
			}

			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckinSheet.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID ");
			qb.addColumn("t.sheetNo", "sheetNo");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.checkinDate", "checkoutDate");
			qb.addColumn("t.amount", "amount");
			qb.addColumn("t.askedAmount", "askedAmount");
			qb.addArgs("pid", qb.guid, key.getPartnerId());
			qb.addEquals("t.partnerId", "@pid");
			qb.addGreaterThan("t.amount", "t.askedAmount");

			qb.addArgs("stype", qb.STRING, checkinType.getCode());
			qb.addEquals("t.sheetType", "@stype");

			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				ReceiptingOrPayingItemImpl item = new ReceiptingOrPayingItemImpl();
				item.setSheetId(rs.getFields().get(0).getGUID());
				item.setSheetNo(rs.getFields().get(1).getString());
				item.setRelaBillsId(rs.getFields().get(2).getGUID());
				item.setRelaBillsNo(rs.getFields().get(3).getString());
				item.setCheckInOrOutDate(rs.getFields().get(4).getDate());
				item.setAmount(rs.getFields().get(5).getDouble());
				item.setAskedAmount(rs.getFields().get(6).getDouble());
				list.add(item);
			}

		}

	}

	@Publish
	protected class LoadPayingCheckinSheetProvider extends
			OneKeyResultProvider<ReceiptingOrPayingItem, LoadReceiptingInventorySheetKey> {

		@Override
		protected ReceiptingOrPayingItem provide(Context context, LoadReceiptingInventorySheetKey key) throws Throwable {
			CheckingInType checkinType = null;
			if (key.getPaymentType() != null) {
				if (key.getPaymentType() == PaymentType.PAY_XSTK) {
					checkinType = CheckingInType.Return;
				} else if (key.getPaymentType() == PaymentType.PAY_CGFK) {
					checkinType = CheckingInType.Purchase;
				} else {
					return null;
				}
			} else if (key.getReceiptType() != null) {
				if (key.getReceiptType() == ReceiptType.RECEIPT_CGTK) {
					return (context.find(ReceiptingOrPayingItem.class, new LoadReceiptingCheckOutSheetKey(key
							.getSheetId(), CheckingOutType.Return)));
				} else if (key.getReceiptType() == ReceiptType.RECEIPT_XSHK) {
					return (context.find(ReceiptingOrPayingItem.class, new LoadReceiptingCheckOutSheetKey(key
							.getSheetId(), CheckingOutType.Sales)));
				} else {
				}
			}

			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckinSheet.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID ");
			qb.addColumn("t.sheetNo", "sheetNo");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.checkinDate", "checkoutDate");
			qb.addColumn("t.amount", "amount");
			qb.addColumn("t.askedAmount", "askedAmount");
			qb.addArgs("id", qb.guid, key.getSheetId());
			qb.addEquals("t.RECID", "@id");

			qb.addArgs("stype", qb.STRING, checkinType.getCode());
			qb.addEquals("t.sheetType", "@stype");

			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				ReceiptingOrPayingItemImpl item = new ReceiptingOrPayingItemImpl();
				item.setSheetId(rs.getFields().get(0).getGUID());
				item.setSheetNo(rs.getFields().get(1).getString());
				item.setRelaBillsId(rs.getFields().get(2).getGUID());
				item.setRelaBillsNo(rs.getFields().get(3).getString());
				item.setCheckInOrOutDate(rs.getFields().get(4).getDate());
				item.setAmount(rs.getFields().get(5).getDouble());
				item.setAskedAmount(rs.getFields().get(6).getDouble());
				return (item);
			}
			return null;
		}

	}
}
