package com.spark.order.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.SendMessageType;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.onlineorder.intf.entity.OnlineOrderDetEntity;
import com.spark.onlineorder.intf.entity.OnlineOrderEntity;
import com.spark.onlineorder.intf.entity.OnlineOrderLogEntity;
import com.spark.onlineorder.intf.publish.entity.OnlineOrderInfoImpl;
import com.spark.onlineorder.intf.publish.entity.OnlineOrderInfoItemImpl;
import com.spark.onlineorder.intf.publish.entity.OnlineOrderItemImpl;
import com.spark.onlineorder.intf.publish.entity.TotalMaterialsItemImpl;
import com.spark.onlineorder.intf.publish.entity.TotalMaterialsItemImpl.GoodsItemImpl;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.key.GetInventoryByStockIdKey;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.base.station.entity.StationItem;
import com.spark.psi.publish.base.station.key.GetStationListKey;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfo;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.onlineorder.key.GetOnlineOrderListKey;
import com.spark.psi.publish.onlineorder.key.GetTotalMaterialsKey;
import com.spark.psi.publish.onlineorder.task.UpdateOnlineOrderStatusTask;
import com.spark.psi.publish.phonemessage.task.PhoneMessageSendTask;

public final class OnlineOrderUtil {

	final static String onlineOrderTable = ERPTableNames.Sales.OnlineOrder.getTableName();
	final static String onlineOrderDetTable = ERPTableNames.Sales.OnlineOrder_Det.getTableName();
	final static String onlineOrderLogTable = ERPTableNames.Sales.OnlineOrder_Log.getTableName();

	public static List<OnlineOrderItem> getOrderItemList(Context context, GetOnlineOrderListKey key) {
		List<OnlineOrderItem> list = new ArrayList<OnlineOrderItem>();
		GUID[] stationIds = getStationId(context, key);
		StringBuffer sql = new StringBuffer();
		sql.append("define query getOrderItemList(");
		if (CheckIsNull.isNotEmpty(stationIds)) {
			for (int i = 0; i < stationIds.length; i++) {
				sql.append("@stationId" + i + " guid,");
			}
		}
		if (CheckIsNull.isNotEmpty(key.getAdvanceValues()) && key.getAdvanceValues().getCreateDateBegin() > 0) {
			sql.append("@createDateBegin date,");
		}
		if (CheckIsNull.isNotEmpty(key.getAdvanceValues()) && key.getAdvanceValues().getCreateDateEnd() > 0) {
			sql.append("@createDateEnd date,");
		}
		sql.append("@temp string");
		sql.append(")\n");
		sql.append("begin\n");
		sql.append("select \n");
		sql.append(getColumns());
		sql.append("from\n");
		sql.append(onlineOrderTable);
		sql.append(" as t\n");
		sql.append(" where 1=1 \n");
		sql.append(getStatusSql(key));
		sql.append(getSearchSql(key));
		if (null != key.isReturnFlag()) {
			if (key.isReturnFlag()) {
				sql.append(" and t.returnFlag=true\n");
			} else {
				sql.append(" and t.returnFlag=false\n");
				sql.append(" and t.status='").append(OnlineOrderStatus.Finished.getCode()).append("'\n");
			}
		}
		if (CheckIsNull.isNotEmpty(stationIds)) {
			sql.append(" and (");
			for (int i = 0; i < stationIds.length; i++) {
				if (i != 0)
					sql.append(" or ");
				sql.append(" t.stationId=@stationId" + i + " ");
			}
			sql.append(")\n");
		}
		if (null != key.getOrderType()) {
			sql.append(" and t.type='").append(key.getOrderType().getCode()).append("'\n");
		}
		sql.append(getAdvanceSql(key));
		sql.append(getOrderSql(key));
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);
		if (null != stationIds && stationIds.length > 0) {
			int index = 0;
			for(int i=0;i<stationIds.length;i++)
			{
				db.setArgumentValue(index++, stationIds[i]);
			}
			if(null!=key.getAdvanceValues())
			{
				db.setArgumentValue(index++, key.getAdvanceValues().getCreateDateBegin());
				db.setArgumentValue(index++, key.getAdvanceValues().getCreateDateEnd());
			}
		} else if (null != key.getAdvanceValues()) {
			db.setArgumentValues(key.getAdvanceValues().getCreateDateBegin(), key.getAdvanceValues().getCreateDateEnd());
		}
		RecordSet rs = db.executeQueryLimit(key.getOffset(), key.getCount());
		while (rs.next()) {
			OnlineOrderItem item = fillOnlineOrder(context, rs);
			if (key.getAdvanceValues() != null && StringHelper.isNotEmpty(key.getAdvanceValues().getDeliverTime())) {
				// 判断取货时间点
				String dDateStr = DateUtil.dateFromat(item.getDeliveryeDate(), "HH:mm");
				if (key.getAdvanceValues().getDeliverTime().equals(dDateStr)) {
					list.add(item);
				}
			} else {
				list.add(item);
			}
			
		}
		return list;
	}

	private static GUID[] getStationId(Context context, GetOnlineOrderListKey key) {
		if (null != key.getStationId()) {
			return new GUID[] { key.getStationId() };
		}

		GetStationListKey sk = new GetStationListKey();
		List<StationItem> list = context.getList(StationItem.class, sk);

		if (CheckIsNull.isNotEmpty(list)) {
			GUID[] stationIds = new GUID[list.size()];
			for (int i = 0; i < list.size(); i++) {
				stationIds[i] = list.get(i).getId();
			}
			return stationIds;
		}
		return null;
	}

	private static StringBuffer getAdvanceSql(GetOnlineOrderListKey key) {
		StringBuffer sql = new StringBuffer();

		if (null != key.getAdvanceValues()) {
			sql.append(" and (1=1 ");
			GetOnlineOrderListKey.AdvanceValues av = key.getAdvanceValues();
			if (CheckIsNull.isNotEmpty(av.getBillsNo())) {
				sql.append(" and t.billsNo like '%").append(av.getBillsNo().trim()).append("%'\n");
			}
			if (CheckIsNull.isNotEmpty(av.getRealName())) {
				sql.append(" and t.realName like '%").append(av.getRealName().trim()).append("%'\n");
			}
			if (CheckIsNull.isNotEmpty(av.getStationName())) {
				sql.append(" and t.stationName like '%").append(av.getStationName().trim()).append("%'\n");
			}
			if (CheckIsNull.isNotEmpty(av.getCreateDateBegin()) && av.getCreateDateBegin() > 0) {
				sql.append("and (t.createDate>").append("@createDateBegin").append(" or t.createDate=").append("@createDateBegin").append(")\n");
			}
			if (CheckIsNull.isNotEmpty(av.getCreateDateEnd()) && av.getCreateDateEnd() > 0) {
				sql.append("and (t.createDate<").append("@createDateEnd").append(" or t.createDate=").append("@createDateEnd").append(")\n");
			}
			if (CheckIsNull.isNotEmpty(av.getDeliveryeDateBegin()) && av.getDeliveryeDateBegin() > 0) {
				sql.append("and (t.deliveryeDate>").append(av.getDeliveryeDateBegin()).append(" or t.deliveryeDate=").append(
						av.getDeliveryeDateBegin()).append(")\n");
			}
			if (CheckIsNull.isNotEmpty(av.getDeliveryeDateEnd()) && av.getDeliveryeDateEnd() > 0) {
				sql.append("and (t.deliveryeDate>").append(av.getDeliveryeDateEnd()).append(" or t.deliveryeDate=").append(av.getDeliveryeDateEnd())
						.append(")\n");
			}
			sql.append(")\n");
		}
		return sql;
	}

	private static OnlineOrderItem fillOnlineOrder(Context context, RecordSet rs) {
		OnlineOrderItemImpl impl = new OnlineOrderItemImpl();
		int index = 0;
		impl.setId(rs.getFields().get(index++).getGUID());
		impl.setBillsNo(rs.getFields().get(index++).getString());
		impl.setMemberId(rs.getFields().get(index++).getGUID());
		impl.setRealName(rs.getFields().get(index++).getString());
		impl.setConsignee(rs.getFields().get(index++).getString());
		impl.setConsigneeTel(rs.getFields().get(index++).getString());
		impl.setAddress(rs.getFields().get(index++).getString());
		impl.setDeliveryeDate(rs.getFields().get(index++).getDate());
		impl.setRemark(rs.getFields().get(index++).getString());
		impl.setDisAmount(rs.getFields().get(index++).getDouble());
		impl.setTotalAmount(rs.getFields().get(index++).getDouble());
		impl.setType(rs.getFields().get(index++).getString());
		impl.setStationId(rs.getFields().get(index++).getGUID());
		impl.setStationName(rs.getFields().get(index++).getString());
		impl.setStatus(rs.getFields().get(index++).getString());
		impl.setCreateDate(rs.getFields().get(index++).getDate());
		impl.setConsignorId(rs.getFields().get(index++).getGUID());
		impl.setConsignor(rs.getFields().get(index++).getString());
		impl.setConsignedDate(rs.getFields().get(index++).getDate());
		impl.setDeliverPersonId(rs.getFields().get(index++).getGUID());
		impl.setDeliverPerson(rs.getFields().get(index++).getString());
		impl.setDeliverDate(rs.getFields().get(index++).getDate());
		impl.setArrivedConfirmId(rs.getFields().get(index++).getGUID());
		impl.setArrivedConfirm(rs.getFields().get(index++).getString());
		impl.setArrivedConfirmDate(rs.getFields().get(index++).getDate());
		impl.setVerificationCode(rs.getFields().get(index++).getString());
		impl.setNoVerificationReason(rs.getFields().get(index++).getString());
		impl.setReturnFlag(rs.getFields().get(index++).getBoolean());
		impl.setToDoor(rs.getFields().get(index++).getBoolean());

		List<OnlineOrderInfoItem> items = context.getList(OnlineOrderInfoItem.class, impl.getId());

		if (null != items && items.size() > 0) {
			impl.setItems(items.toArray(new OnlineOrderInfoItem[0]));
		}
		return impl;
	}

	private static StringBuffer getOrderSql(GetOnlineOrderListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSortField()) {
			sql.append(" order by ").append(key.getSortField().getFieldName());
			if (null != key.getSortType()) {
				sql.append(" ").append(key.getSortType()).append("\n");
			}
		} else {
			sql.append(" order by t.deliveryeDate asc, t.realName asc \n");
		}
		return sql;
	}

	private static StringBuffer getSearchSql(GetOnlineOrderListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSearchText()) {
			String searchText = key.getSearchText().trim();
			sql.append(" and (");

			sql.append(" t.billsNo like '%").append(searchText).append("%' ");
			sql.append(" or t.realName like '%").append(searchText).append("%' ");
			sql.append(" or t.stationName like '%").append(searchText).append("%' ");

			sql.append(")\n");
		}
		return sql;
	}

	private static StringBuffer getStatusSql(GetOnlineOrderListKey key) {
		StringBuffer sql = new StringBuffer();
		if (key.getStatus().length > 0) {
			sql.append(" and ");
			if (key.getStatus().length > 1) {
				sql.append("(");
			}
			for (int i = 0; i < key.getStatus().length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" t.status='").append(key.getStatus()[i].getCode()).append("' ");
			}
			if (key.getStatus().length > 1) {
				sql.append(")\n");
			}
		}
		return sql;
	}

	private static StringBuffer getColumns() {
		StringBuffer sql = new StringBuffer();
		sql.append("t.RECID as id,\n");
		sql.append("t.billsNo as billsNo,\n");
		sql.append("t.memberId as memberId,\n");
		sql.append("t.realName as realName,\n");
		sql.append("t.consignee as consignee,\n");
		sql.append("t.consigneeTel as consigneeTel,\n");
		sql.append("t.address as address,\n");
		sql.append("t.deliveryeDate as deliveryeDate,\n");
		sql.append("t.remark as remark,\n");
		sql.append("t.disAmount as disAmount,\n");
		sql.append("t.totalAmount as totalAmount,\n");
		sql.append("t.type as type,\n");
		sql.append("t.stationId as stationId,\n");
		sql.append("t.stationName as stationName,\n");
		sql.append("t.status as status,\n");
		sql.append("t.createDate as createDate,\n");
		sql.append("t.consignorId as consignorId,\n");
		sql.append("t.consignor as consignor,\n");
		sql.append("t.consignedDate as consignedDate,\n");
		sql.append("t.deliverPersonId as deliverPersonId,\n");
		sql.append("t.deliverPerson as deliverPerson,\n");
		sql.append("t.deliverDate as deliverDate,\n");
		sql.append("t.arrivedConfirmId as arrivedConfirmId,\n");
		sql.append("t.arrivedConfirm as arrivedConfirm,\n");
		sql.append("t.arrivedConfirmDate as arrivedConfirmDate,\n");
		sql.append("t.verificationCode as verificationCode,\n");
		sql.append("t.noVerificationReason as noVerificationReason,\n");
		sql.append("t.returnFlag as returnFlag\n");
		sql.append(",t.toDoor as toDoor\n");
		sql.append(",t.deliveryCost as deliveryCost\n");
		sql.append(",t.bagsCost as bagsCost\n");
		sql.append(",t.vantages as vantages\n");
		sql.append(",t.vantagesCost as vantagesCost\n");
		sql.append(",t.payType as payType\n");

		return sql;
	}

	public static OnlineOrderInfoItem getOrderInfoItem(Context context, OnlineOrderDetEntity det) {
		OnlineOrderInfoItemImpl impl = new OnlineOrderInfoItemImpl();
		impl.setAmount(det.getAmount());
		impl.setBillsId(det.getBillsId());
		impl.setCount(det.getCount());
		impl.setDisAmount(det.getDisAmount());
		impl.setDiscount(det.getDiscount());
		impl.setGoodsCode(det.getGoodsCode());
		impl.setGoodsId(det.getGoodsId());
		impl.setGoodsNo(det.getGoodsNo());
		impl.setGoodsName(det.getGoodsName());
		impl.setGoodsScale(det.getGoodsScale());
		impl.setGoodsSpec(det.getGoodsSpec());
		impl.setId(det.getId());
		impl.setPrice(det.getPrice());
		impl.setPromotionId(det.getPromotionId());
		impl.setUnit(det.getUnit());
		return impl;
	}

	public static OnlineOrderInfo getOrderInfo(Context context, OnlineOrderEntity e) {
		OnlineOrderInfoImpl i = new OnlineOrderInfoImpl();
		i.setAddress(e.getAddress());
		i.setArrivedConfirm(e.getArrivedConfirm());
		i.setArrivedConfirmDate(e.getArrivedConfirmDate());
		i.setArrivedConfirmId(e.getArrivedConfirmId());
		i.setBillsNo(e.getBillsNo());
		i.setConsignedDate(e.getConsignedDate());
		i.setConsignee(e.getConsignee());
		i.setConsigneeTel(e.getConsigneeTel());
		i.setConsignor(e.getConsignor());
		i.setConsignorId(e.getConsignorId());
		i.setCreateDate(e.getCreateDate());
		i.setDeliverDate(e.getDeliverDate());
		i.setDeliverPerson(e.getDeliverPerson());
		i.setDeliverPersonId(e.getDeliverPersonId());
		i.setDeliveryeDate(e.getDeliveryeDate());
		i.setDisAmount(e.getDisAmount());
		i.setId(e.getId());
		i.setMemberId(e.getMemberId());
		i.setNoVerificationReason(e.getNoVerificationReason());
		i.setRealName(e.getRealName());
		i.setRemark(e.getRemark());
		i.setStationId(e.getStationId());
		i.setStationName(e.getStationName());
		i.setStatus(e.getStatus());
		i.setTotalAmount(e.getTotalAmount());
		i.setType(e.getType());
		i.setVerificationCode(e.getVerificationCode());
		i.setReturnFlag(e.isReturnFlag());
		i.setToDoor(e.isToDoor());

		List<OnlineOrderInfoItem> items = context.getList(OnlineOrderInfoItem.class, i.getId());
		if (null != items && items.size() > 0)
			i.setItems(items.toArray(new OnlineOrderInfoItem[0]));
		return i;
	}

	public static OnlineOrderLogEntity fillLogEntity(Context context, GUID id, Object task) {
		Login login = context.find(Login.class);
		Employee emp = context.find(Employee.class, login.getEmployeeId());
		OnlineOrderLogEntity entity = new OnlineOrderLogEntity();
		entity.setId(GUID.randomID());
		entity.setBillsId(id);
		entity.setProcessingTime(System.currentTimeMillis());
		entity.setOperator("系统");
		entity.setMessage("您的订单已生效");
		if (task instanceof UpdateOnlineOrderStatusTask) {
			UpdateOnlineOrderStatusTask t = (UpdateOnlineOrderStatusTask) task;
			if (UpdateOnlineOrderStatusTask.Method.Arrival.equals(t.getMethod())) {
				entity.setMessage("您的订单已配送到站点，可以提货");
				entity.setOperator(emp.getName());
			} else if (UpdateOnlineOrderStatusTask.Method.Deliver.equals(t.getMethod())) {
				entity.setMessage("您的订单已开始配送");
				entity.setOperator(emp.getName());
			} else if (UpdateOnlineOrderStatusTask.Method.Finish.equals(t.getMethod())) {
				entity.setMessage("您的订单已完成，感谢您在七号生活馆购物，欢迎再次光临！");
				entity.setOperator(emp.getName());
			} else if (UpdateOnlineOrderStatusTask.Method.Picking.equals(t.getMethod())) {
				entity.setMessage("您的订单已开始拣货");
				entity.setOperator(emp.getName());
			}
		}

		return entity;
	}

	public static int deleteOnlineOrder(Context context, GUID guid) {
		StringBuffer sql = new StringBuffer();
		sql.append("define delete deleteOrder(@id guid)\n");
		sql.append("begin\n");
		sql.append(" delete from ");
		sql.append(onlineOrderTable);
		sql.append(" as t\n");
		sql.append(" where ");
		sql.append(" t.RECID=@id\n");
		sql.append(" and t.status='").append(OnlineOrderStatus.Effected).append("'\n");
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);

		return db.executeUpdate();

	}

	public static void deleteOnlineOrderDet(Context context, GUID guid) {
		StringBuffer sql = new StringBuffer();
		sql.append("define delete deleteOrder(@id guid)\n");
		sql.append("begin\n");
		sql.append(" delete from ");
		sql.append(onlineOrderTable);
		sql.append(" as t\n");
		sql.append(" where ");
		sql.append(" t.billsId=@id\n");
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);
		db.executeUpdate();

	}

	public static void deleteOnlineOrderLog(Context context, GUID guid) {
		StringBuffer sql = new StringBuffer();
		sql.append("define delete deleteOrder(@id guid)\n");
		sql.append("begin\n");
		sql.append(" delete from ");
		sql.append(onlineOrderLogTable);
		sql.append(" as t\n");
		sql.append(" where ");
		sql.append(" t.billsId=@id\n");
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);
		db.executeUpdate();
	}

	public static Collection<? extends TotalMaterialsItem> getTotalMaterials(Map<GUID, TotalMaterialsItem> map) {
		List<TotalMaterialsItem> list = new ArrayList<TotalMaterialsItem>();

		for (Entry<GUID, TotalMaterialsItem> entry : map.entrySet()) {
			list.add((TotalMaterialsItem) entry.getValue());
		}
		return list;
	}

	public static String getVerificationCode() {
		return String.valueOf((Math.random() + 1) * 1000000).substring(1, 7);
	}

	/**
	 * 发送验证码
	 * 
	 * @param context
	 * @param id
	 * @param verificationCode
	 */
	public static void sendVerificationCode(Context context, OnlineOrderEntity od, String verificationCode) {
		if(!od.isToDoor())
		{
			String message = "尊敬的客户！您的订单："+od.getBillsNo().split("WSDD")[1]+"已到货，验证码："+verificationCode+"，请到"+od.getStationName()+"领取。";
			PhoneMessageSendTask task = new PhoneMessageSendTask();
			task.setMessage(message);
			task.setSendType(SendMessageType.ArrivalNotice);
			task.setPhoneNo(od.getConsigneeTel());
			context.handle(task);
		}
		
	}

	public static List<TotalMaterialsItemImpl.GoodsItemImpl> getGoods(Context context, GetTotalMaterialsKey key) {
		List<TotalMaterialsItemImpl.GoodsItemImpl> list = new ArrayList<GoodsItemImpl>();

		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable(onlineOrderDetTable, "d");
		qb.addTable(onlineOrderTable, "t");
		qb.addEquals("d.billsId", "t.recid");
		List<String> params = new ArrayList<String>();
		for (int i = 0; i < key.getOnlineOrderIds().length; i++) {
			String param = "recid" + i;
			qb.addArgs(param, qb.guid, key.getOnlineOrderIds()[i]);
			params.add("@" + param);
		}
		qb.addIn("t.recid", params);

		qb.addColumn("d.goodsId", "goodsId");
		qb.addColumn("d.goodsCode", "goodsCod");
		qb.addColumn("d.goodsNo", "goodsNo");
		qb.addColumn("d.goodsName", "goodsName");
		qb.addColumn("d.goodsSpec", "goodsSpec");
		qb.addColumn("d.unit", "unit");
		qb.addColumn("d.goodsScale", "goodsScale");
		qb.addColumn("sum(d.\"count\")", "\"count\"");
		qb.addGroupBy("d.goodsId");

		RecordSet rs = qb.getRecord();
		while (rs.next()) {
			list.add(fillGoods(context, rs));
		}
		return list;
	}

	private static TotalMaterialsItemImpl.GoodsItemImpl fillGoods(Context context, RecordSet rs) {
		TotalMaterialsItemImpl.GoodsItemImpl goods = new TotalMaterialsItemImpl().new GoodsItemImpl();
		int index = 0;
		goods.setGoodsId(rs.getFields().get(index++).getGUID());
		goods.setGoodsCode(rs.getFields().get(index++).getString());
		goods.setGoodsNo(rs.getFields().get(index++).getString());
		goods.setGoodsName(rs.getFields().get(index++).getString());
		goods.setGoodsSpec(rs.getFields().get(index++).getString());
		goods.setUnit(rs.getFields().get(index++).getString());
		goods.setGoodsScale(rs.getFields().get(index++).getInt());
		goods.setCount(rs.getFields().get(index++).getDouble());
		List<com.spark.psi.base.Inventory> inventorys = context.getList(com.spark.psi.base.Inventory.class, new GetInventoryByStockIdKey(goods
				.getGoodsId(), InventoryType.Goods));
		double count = 0;
		double lockedCount = 0;
		if (CheckIsNull.isNotEmpty(inventorys)) {
			for (com.spark.psi.base.Inventory inventory : inventorys) {
				count = DoubleUtil.sum(count, inventory.getCount());
				lockedCount = DoubleUtil.sum(lockedCount, inventory.getLockedCount());
			}
		}
		//TODO
//		if (DoubleUtil.sub(count, lockedCount) > 0) {
//			if (goods.getCount() > DoubleUtil.sub(count, lockedCount)) {
//				goods.setLockCount(DoubleUtil.sub(count, lockedCount));
//			} else {
//				goods.setLockCount(goods.getCount());
//			}
//		}
		return goods;
	}
}
