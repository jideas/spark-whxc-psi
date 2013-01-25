package com.spark.deliver.intf.publish.service;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.deliver.intf.entity.DeliverDetEntity;
import com.spark.deliver.intf.entity.DeliverEntity;
import com.spark.deliver.intf.publish.entity.DeliverInfoImpl;
import com.spark.deliver.intf.publish.entity.DeliverInfoItemImpl;
import com.spark.deliver.intf.publish.entity.DeliverItemImpl;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.intf.task.inventory.InventoryLockTask;
import com.spark.psi.publish.DeliverStatus;
import com.spark.psi.publish.DeliverTicketType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.deliver.entity.DeliverInfo;
import com.spark.psi.publish.deliver.entity.DeliverInfoItem;
import com.spark.psi.publish.deliver.entity.DeliverItem;
import com.spark.psi.publish.deliver.key.GetDeliverListKey;
import com.spark.psi.publish.deliver.task.CreateDeliverTask;
import com.spark.psi.publish.deliverticket.task.CreateDeliverTicketTask;
import com.spark.psi.publish.inventory.checkout.task.RealGoodsCheckOutTask;
import com.spark.psi.publish.inventory.checkout.task.RealGoodsCheckOutTaskItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;

public final class DeliverServiceUtil {

	final static String deliverTable = ERPTableNames.Sales.Deliver_Sheet.getTableName();

	public static List<DeliverItem> getDeliverItemList(Context context, GetDeliverListKey key) {
		List<DeliverItem> list = new ArrayList<DeliverItem>();
		StringBuffer sql = new StringBuffer();
		sql.append("define query getDeliverItem()\n");
		sql.append("begin\n");
		sql.append(" select \n");
		sql.append(getColumns());
		sql.append(" from ");
		sql.append(deliverTable);
		sql.append(" as t\n");
		sql.append(" where 1=1 ");
		sql.append(getStatusSql(key));
		sql.append(getSearchSql(key));
		sql.append(getOrderBySql(key));
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);
		RecordSet rs = db.executeQueryLimit(key.getOffset(), key.getCount());
		while (rs.next()) {
			list.add(fillDeliver(rs));
		}
		return list;
	}

	private static DeliverItem fillDeliver(RecordSet rs) {
		DeliverItemImpl i = new DeliverItemImpl();
		int index = 0;
		i.setId(rs.getFields().get(index++).getGUID());
		i.setSheetNo(rs.getFields().get(index++).getString());
		i.setRemark(rs.getFields().get(index++).getString());
		i.setCreatorId(rs.getFields().get(index++).getGUID());
		i.setCreator(rs.getFields().get(index++).getString());
		i.setCreateDate(rs.getFields().get(index++).getDate());
		i.setFinishDate(rs.getFields().get(index++).getDate());
		i.setStationId(rs.getFields().get(index++).getGUID());
		i.setStationName(rs.getFields().get(index++).getString());
		i.setAddress(rs.getFields().get(index++).getString());
		i.setStatus(DeliverStatus.getStatus(rs.getFields().get(index++).getString()));
		i.setDeliverPersonId(rs.getFields().get(index++).getGUID());
		i.setDeliverPerson(rs.getFields().get(index++).getString());
		i.setDeliverDate(rs.getFields().get(index++).getDate());
		i.setConsigneeId(rs.getFields().get(index++).getGUID());
		i.setConsignee(rs.getFields().get(index++).getString());
		i.setConsigneeDate(rs.getFields().get(index++).getDate());
		i.setDeliveredPackageCount(rs.getFields().get(index++).getInt());
		i.setReceivedPackageCount(rs.getFields().get(index++).getInt());
		i.setDescription(rs.getFields().get(index++).getString());
		i.setFormula(rs.getFields().get(index++).getString());
		i.setHandlerId(rs.getFields().get(index++).getGUID());
		i.setHandler(rs.getFields().get(index++).getString());
		i.setHandleDate(rs.getFields().get(index++).getDate());
		i.setPlanDate(rs.getFields().get(index++).getDate());

		return i;
	}

	private static StringBuffer getOrderBySql(GetDeliverListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSortField()) {
			sql.append(" order by ").append(key.getSortField().getFieldName());
			if (null != key.getSortType()) {
				sql.append(" ").append(key.getSortType()).append("\n");
			}
		} else {
			sql.append(" order by t.deliverDate asc \n");
		}
		return sql;
	}

	private static StringBuffer getSearchSql(GetDeliverListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSearchText()) {
			String searchText = key.getSearchText().trim();
			sql.append(" and (");

			sql.append(" t.sheetNo like '%").append(searchText).append("%' ");
			// sql.append(" or t.realName like '%'+").append(searchText).append("+'%' ");
			sql.append(" or t.stationName like '%").append(searchText).append("%' ");

			sql.append(")\n");
		}
		return sql;
	}

	private static StringBuffer getStatusSql(GetDeliverListKey key) {
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

		sql.append("t.RECID as id,");
		sql.append("t.sheetNo as sheetNo,");
		sql.append("t.remark as remark,");
		sql.append("t.creatorId as creatorId,");
		sql.append("t.creator as creator,");
		sql.append("t.createDate as createDate,");
		sql.append("t.finishDate as finishDate,");
		sql.append("t.stationId as stationId,");
		sql.append("t.stationName as stationName,");
		sql.append("t.address as address,");
		sql.append("t.status as status,");
		sql.append("t.deliverPersonId as deliverPersonId,");
		sql.append("t.deliverPerson as deliverPerson,");
		sql.append("t.deliverDate as deliverDate,");
		sql.append("t.consigneeId as consigneeId,");
		sql.append("t.consignee as consignee,");
		sql.append("t.consigneeDate as consigneeDate,");
		sql.append("t.deliveredPackageCount as deliveredPackageCount,");
		sql.append("t.receivedPackageCount as receivedPackageCount,");
		sql.append("t.description as description,");
		sql.append("t.formula as formula,");
		sql.append("t.handlerId as handlerId,");
		sql.append("t.handler as handler,");
		sql.append("t.handleDate as handleDate\n");
		sql.append(",t.planDate as planDate\n");

		return sql;
	}

	public static DeliverInfo getDeliverInfo(Context context, DeliverEntity e, List<DeliverDetEntity> dets) throws Throwable {
		DeliverInfoImpl impl = new DeliverInfoImpl();

		new BeanUtils().copyProperties(e, impl);

		impl.setStatus(DeliverStatus.getStatus(e.getStatus()));

		DeliverInfoItem[] items = new DeliverInfoItem[dets.size()];

		for (int i = 0; i < dets.size(); i++) {
			DeliverDetEntity det = dets.get(i);
			DeliverInfoItemImpl d = new DeliverInfoItemImpl();

			new BeanUtils().copyProperties(det, d);

			List<OnlineOrderInfoItem> list = context.getList(OnlineOrderInfoItem.class, det.getOnlineOrderId());
			if (null != list && list.size() > 0) {

				DeliverInfoItemImpl.ItemImpl[] dis = new DeliverInfoItemImpl.ItemImpl[list.size()];
				for (int j = 0; j < list.size(); j++) {
					OnlineOrderInfoItem od = list.get(j);
					DeliverInfoItemImpl.ItemImpl di = d.new ItemImpl();
					di.setCount(od.getCount());
					di.setGoodsItemId(od.getGoodsId());
					di.setGoodsName(od.getGoodsName());
					di.setGoodsSpec(od.getGoodsSpec());
					dis[j] = di;
				}
				d.setItems(dis);
			}
			items[i] = d;
		}
		impl.setItems(items);
		return impl;
	}

	public static void insertDeliverTicket(Context context, CreateDeliverTask task) throws Throwable {
		for (CreateDeliverTask.Item i : task.getItems()) {
			CreateDeliverTicketTask t = new CreateDeliverTicketTask();

			t.setAddress(i.getAddress());
			t.setDisAmount(i.getDisAmount());
			t.setMemberId(i.getMemberId());
			t.setMemberRealName(i.getMemberRealName());
			t.setMobilePhone(i.getMobilePhone());
			t.setOnlineOrderId(i.getOnlineOrderId());
			t.setRemark(i.getRemark());
			t.setOnlineOrderNo(i.getOnlineOrderNo());
			t.setStationId(i.getStationId());
			t.setStationName(i.getStationName());
			t.setTotalAmount(i.getTotalAmount());
			t.setDeliverTicketType(DeliverTicketType.Common.getCode());

			context.handle(t);
		}

	}

	public static void insertCheckOutSheet(Context context, DeliverInfo info) {
		for (DeliverInfoItem i : info.getItems()) {
			RealGoodsCheckOutTask ot = new RealGoodsCheckOutTask();
			ot.setRelaBillsId(i.getOnlineOrderId());
			ot.setRelaBillsNo(i.getOnlineOrderNo());
			// ot.setRemark(i.getRemark());
			ot.setStoreId(Store.GoodsStoreId);

			List<RealGoodsCheckOutTaskItem> items = new ArrayList<RealGoodsCheckOutTaskItem>();
			for (DeliverInfoItem.Item item : i.getItems()) {
				//TODO
//				InventoryLockTask task = new InventoryLockTask(Store.GoodsStoreId, item.getGoodsItemId());
//				task.setInventoryType(InventoryType.Goods);
//				task.setLockedCount(0-item.getCount());
//				context.handle(task);
				
				RealGoodsCheckOutTaskItem ri = new RealGoodsCheckOutTaskItem();

				ri.setCount(item.getCount());
				ri.setGoodsId(item.getGoodsItemId());
				ri.setGoodsName(item.getGoodsName());
				ri.setGoodsSpec(item.getGoodsSpec());
				GoodsItem gi = context.find(GoodsItem.class, item.getGoodsItemId());
				ri.setGoodsScale(gi.getScale());
				ri.setGoodsNo(gi.getGoodsNo());
				ri.setGoodsUnit(gi.getGoodsUnit());
				ri.setPrice(gi.getStandardCost());
				ri.setAmount(ri.getCount() * ri.getPrice());

				items.add(ri);
			}
			ot.setItems(items);
			context.handle(ot);
		}

	}

}
