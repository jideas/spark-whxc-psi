/**
 * 
 */
package com.spark.psi.message.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.Login;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.SMessageTemplateEnum;
import com.spark.psi.publish.SMessageType;
import com.spark.psi.publish.smessage.entity.SMessageBuddleSet;
import com.spark.psi.publish.smessage.entity.SMessageItem;
import com.spark.psi.publish.smessage.entity.SMessageMonitorItem;
import com.spark.psi.publish.smessage.entity.SMessageTypeItem;
import com.spark.psi.report.constant.ReportConstants.SMessageSet;
import com.spark.psi.report.utils.AuthUtils;
import com.spark.psi.report.utils.QuerySqlBuilder;

/**
 *
 */
public class SMessageProvider extends Service {

	/**
	 * @param title
	 */
	protected SMessageProvider() {
		super("com.spark.psi.message.provider.SMessageProvider");
	}

	@Publish
	protected class CountProvider extends OneKeyResultProvider<Integer, SMessageType> {

		@Override
		protected Integer provide(Context context, SMessageType key) throws Throwable {
			int count = 0;
			Login login = context.find(Login.class);
			if (null == login) {
				return 0;
			}
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable("SA_MESSAGE_INFO", "t");
			qb.addTable("SA_MESSAGE_MAPPING", "m");
			qb.addArgs("userId", qb.guid, login.getEmployeeId());
			qb.addArgs("mtype", qb.STRING, key.getCode());
			qb.addEquals("m.messageId", "t.RECID");
			qb.addEquals("m.userId", "@userId");
			qb.addEquals("m.messageType", "@mtype");

			qb.addArgs("today", qb.DATE, DateUtil.truncDay(new Date().getTime()));
			qb.addArgs("zero", qb.DATE, 0);
			qb.addCondition("(m.endTime=@zero or m.endTime is null or m.endTime>=@today)");

			qb.addColumn("count(m.RECID)", "ccc");
			qb.addOrderBy("t.stringValue1");
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				count = rs.getFields().get(0).getInt();
			}
			return count;
		}
	}

	@Publish
	protected class DataProvider extends OneKeyResultListProvider<SMessageItem, SMessageType> {
		@Override
		protected void provide(Context context, SMessageType key, List<SMessageItem> list) throws Throwable {
			Login login = context.find(Login.class);
			if (null == login) {
				return;
			}
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable("SA_MESSAGE_INFO", "t");
			qb.addTable("SA_MESSAGE_MAPPING", "m");
			qb.addArgs("userId", qb.guid, login.getEmployeeId());
			qb.addArgs("mtype", qb.STRING, key.getCode());
			qb.addEquals("m.messageId", "t.RECID");
			qb.addEquals("m.userId", "@userId");
			qb.addEquals("m.messageType", "@mtype");

			qb.addArgs("today", qb.DATE, DateUtil.truncDay(new Date().getTime()));
			qb.addArgs("zero", qb.DATE, 0);
			qb.addCondition("(m.endTime=@zero or m.endTime is null or m.endTime>=@today)");

			qb.addColumn("m.RECID", "RECID");
			qb.addColumn("t.relaObjId", "relaObjId");
			qb.addColumn("t.templateCode", "templateCode");
			qb.addColumn("t.stringValue1", "stringValue1");
			qb.addColumn("t.stringValue2", "stringValue2");
			qb.addColumn("t.stringValue3", "stringValue3");
			qb.addColumn("m.showFlag", "showFlag");
			qb.addOrderBy("t.stringValue1");
			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				SMessageItem item = new SMessageItem();
				item.setRECID(rs.getFields().get(0).getGUID());
				item.setRelaObjId(rs.getFields().get(1).getGUID());
				item.setTemplate(SMessageTemplateEnum.getTemplate(rs.getFields().get(2).getString()));
				item.setStringValue1(rs.getFields().get(3).getString());
				item.setStringValue2(rs.getFields().get(4).getString());
				item.setStringValue3(rs.getFields().get(5).getString());
				item.setShowFlag(rs.getFields().get(6).getBoolean());
				list.add(item);
			}
		}
	}

	@Publish
	protected class AllDataProvider extends ResultListProvider<SMessageItem> {
		@Override
		protected void provide(Context context, List<SMessageItem> list) throws Throwable {
			Login login = context.find(Login.class);
			if (null == login) {
				return;
			}
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable("SA_MESSAGE_INFO", "t");
			qb.addTable("SA_MESSAGE_MAPPING", "m");
			qb.addArgs("userId", qb.guid, login.getEmployeeId());
			qb.addEquals("m.messageId", "t.RECID");
			qb.addEquals("m.userId", "@userId");
			List<SMessageTypeItem> types = context.getList(SMessageTypeItem.class);
			List<String> slist = new ArrayList<String>();
			for (int i = 0; i < types.size(); i++) {
				SMessageTypeItem type = types.get(i);
				qb.addArgs("msgType" + i, qb.STRING, type.getCode());
				slist.add("@msgType" + i);
			}

			qb.addArgs("today", qb.DATE, DateUtil.truncDay(new Date().getTime()));
			qb.addArgs("zero", qb.DATE, 0);
			qb.addCondition("(m.endTime=@zero or m.endTime is null or m.endTime>=@today)");

			qb.addIn("t.messageType", slist);
			qb.addColumn("m.RECID", "RECID");
			qb.addColumn("t.relaObjId", "relaObjId");
			qb.addColumn("t.templateCode", "templateCode");
			qb.addColumn("t.stringValue1", "stringValue1");
			qb.addColumn("t.stringValue2", "stringValue2");
			qb.addColumn("t.stringValue3", "stringValue3");
			qb.addColumn("m.showFlag", "showFlag");
			qb.addOrderBy("t.templateCode");
			RecordSet rs = qb.getRecord();
			SMessageBuddleSet set = context.find(SMessageBuddleSet.class);
			while (rs.next()) {
				SMessageItem item = new SMessageItem();
				item.setRECID(rs.getFields().get(0).getGUID());
				item.setRelaObjId(rs.getFields().get(1).getGUID());
				item.setTemplate(SMessageTemplateEnum.getTemplate(rs.getFields().get(2).getString()));
				item.setStringValue1(rs.getFields().get(3).getString());
				item.setStringValue2(rs.getFields().get(4).getString());
				item.setStringValue3(rs.getFields().get(5).getString());
				item.setShowFlag(rs.getFields().get(6).getBoolean());
				if (!item.isShowFlag()) {
					item.setShowFlag(!set.getBuddling(SMessageType
							.getType(item.getTemplate().getCode().substring(0, 2))));
				}
				list.add(item);
			}
		}
	}

	@Publish
	protected class DataTypeProvider extends ResultListProvider<SMessageTypeItem> {

		@Override
		protected void provide(Context context, List<SMessageTypeItem> list) throws Throwable {
			Login login = context.find(Login.class);
			List<Auth> authlist = AuthUtils.getAuthList(context, login.getEmployeeId());
			boolean boss = authlist.contains(Auth.Boss);
			boolean salesManager = authlist.contains(Auth.SalesManager);
			boolean sales = authlist.contains(Auth.Sales);
			boolean purchaseManager = authlist.contains(Auth.PurchaseManager);
			boolean purchase = authlist.contains(Auth.Purchase);
			boolean accountManager = authlist.contains(Auth.AccountManager);
			// boolean account = authlist.contains(Auth.Account);
			boolean storeKeeperManager = authlist.contains(Auth.StoreKeeperManager);
			boolean storeKeeper = authlist.contains(Auth.StoreKeeper);
			boolean distribute = authlist.contains(Auth.Distribute);

			SMessageBuddleSet set = context.find(SMessageBuddleSet.class);
			if (boss || salesManager || purchaseManager || storeKeeperManager) {
				list.add(new SMessageTypeItem(SMessageType.UnapproveOrders, set
						.getBuddling(SMessageType.UnapproveOrders)));
			}
			list.add(new SMessageTypeItem(SMessageType.UnreadNotices, set.getBuddling(SMessageType.UnreadNotices)));
			if (distribute) {
				list.add(new SMessageTypeItem(SMessageType.UndistributeOrders, set
						.getBuddling(SMessageType.UndistributeOrders)));
			}
			if (!boss && (storeKeeperManager || storeKeeper)) {
				list.add(new SMessageTypeItem(SMessageType.UncheckoutOrders, set
						.getBuddling(SMessageType.UncheckoutOrders)));
				list.add(new SMessageTypeItem(SMessageType.UncheckinOrders, set
						.getBuddling(SMessageType.UncheckinOrders)));
			}
			if (boss || salesManager || purchaseManager || storeKeeperManager || accountManager
					|| context.find(Boolean.class, Auth.Assistant, login.getEmployeeId())) {
				list.add(new SMessageTypeItem(SMessageType.NearBirthday, set.getBuddling(SMessageType.NearBirthday)));
			}
			if (boss || salesManager || purchaseManager || sales || purchase) {
				list.add(new SMessageTypeItem(SMessageType.NearOrderDate, set.getBuddling(SMessageType.NearOrderDate)));
			}
			if (boss || purchaseManager || purchase) {
				list
						.add(new SMessageTypeItem(SMessageType.GoodsInventory, set
								.getBuddling(SMessageType.GoodsInventory)));
			}
			if (boss || distribute) {
				list.add(new SMessageTypeItem(SMessageType.DeliveryException, set
						.getBuddling(SMessageType.DeliveryException)));
			}
		}
	}

	@Publish
	protected class DataTypeProvider2 extends OneKeyResultProvider<SMessageTypeItem, String> {

		@Override
		protected SMessageTypeItem provide(Context context, String key) throws Throwable {
			SMessageType type = SMessageType.getType(key);
			if (type != null) {
				SMessageBuddleSet set = context.find(SMessageBuddleSet.class);
				return new SMessageTypeItem(type, set.getBuddling(type));
			}
			return null;
		}

	}

	@Publish
	protected class MonitorTypeProvider extends ResultListProvider<SMessageMonitorItem> {
		@Override
		protected void provide(Context context, List<SMessageMonitorItem> list) throws Throwable {
			Login login = context.find(Login.class);
			List<Auth> authlist = AuthUtils.getAuthList(context, login.getEmployeeId());
			boolean boss = authlist.contains(Auth.Boss);
			boolean salesManager = authlist.contains(Auth.SalesManager);
			boolean sales = authlist.contains(Auth.Sales);
			boolean purchaseManager = authlist.contains(Auth.PurchaseManager);
			boolean purchase = authlist.contains(Auth.Purchase);
			// boolean accountManager = authlist.contains(Auth.AccountManager);
			// boolean account = authlist.contains(Auth.Account);
			boolean storeKeeperManager = authlist.contains(Auth.StoreKeeperManager);
			boolean storeKeeper = authlist.contains(Auth.StoreKeeper);
			boolean distribute = authlist.contains(Auth.Distribute);
			SMessageBuddleSet set = context.find(SMessageBuddleSet.class);
			if (boss || salesManager || purchaseManager || storeKeeperManager) {
				list.add(new SMessageMonitorItem(SMessageType.UnapproveOrders, set.getMonitor(
						SMessageType.UnapproveOrders, authlist)));
			}
			list.add(new SMessageMonitorItem(SMessageType.UnreadNotices, set.getMonitor(SMessageType.UnreadNotices,
					authlist)));
			if (distribute) {
				list.add(new SMessageMonitorItem(SMessageType.UndistributeOrders, set.getMonitor(
						SMessageType.UndistributeOrders, authlist)));
			}
			if (!boss && (storeKeeperManager || storeKeeper)) {
				list.add(new SMessageMonitorItem(SMessageType.UncheckoutOrders, set.getMonitor(
						SMessageType.UncheckoutOrders, authlist)));
				list.add(new SMessageMonitorItem(SMessageType.UncheckinOrders, set.getMonitor(
						SMessageType.UncheckinOrders, authlist)));
			}
			if (boss || salesManager || purchaseManager || sales || purchase) {
				list.add(new SMessageMonitorItem(SMessageType.NearOrderDate, set.getMonitor(SMessageType.NearOrderDate,
						authlist)));
			}
			if (boss || purchaseManager || purchase) {
				list.add(new SMessageMonitorItem(SMessageType.GoodsInventory, set.getMonitor(
						SMessageType.GoodsInventory, authlist)));
			}
		}
	}

	@Publish
	protected class BuddlingSetProvider extends ResultProvider<SMessageBuddleSet> {

		@Override
		protected SMessageBuddleSet provide(Context context) throws Throwable {
			Login login = context.find(Login.class);
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable("SA_MESSAGE_SET", "t");

			qb.addArgs("uid", qb.guid, login.getEmployeeId());
			qb.addEquals("t.userId", "@uid");

			qb.addColumn("t.setType", "st");
			qb.addColumn("t.messageType", "mt");
			qb.addColumn("t.bvalue", "buddling");
			RecordSet rs = qb.getRecord();
			SMessageBuddleSet set = new SMessageBuddleSet();
			while (rs.next()) {
				String setType = rs.getFields().get(0).getString();
				String mesType = rs.getFields().get(1).getString();
				if (setType.equals(SMessageSet.Bubbling.getCode())) {
					set.putBubbling(SMessageType.getType(mesType), rs.getFields().get(2).getBoolean());
				} else {
					set.putMonitor(SMessageType.getType(mesType), rs.getFields().get(2).getBoolean());
				}
			}
			return set;
		}

	}
}
