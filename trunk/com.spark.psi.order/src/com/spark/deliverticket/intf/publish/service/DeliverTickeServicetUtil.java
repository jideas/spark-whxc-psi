package com.spark.deliverticket.intf.publish.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.deliverticket.intf.entity.DeliverTicketEntity;
import com.spark.deliverticket.intf.publish.entity.DeliverTicketInfoImpl;
import com.spark.deliverticket.intf.publish.entity.DeliverTicketInfoItemImpl;
import com.spark.deliverticket.intf.publish.entity.DeliverTicketItemImpl;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketInfo;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketItem;
import com.spark.psi.publish.deliverticket.key.GetDeliverTicketListKey;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;

public final class DeliverTickeServicetUtil {

	final static String deliverTicketTable = ERPTableNames.Sales.Deliver_Ticket.getTableName();

	public static List<DeliverTicketItem> getDeliverTicketItemList(Context context, GetDeliverTicketListKey key) {
		List<DeliverTicketItem> list = new ArrayList<DeliverTicketItem>();

		StringBuffer sql = new StringBuffer();
		sql.append("define query getDeliverTicketItemList(@dateBegin date,@dateEnd date)\n");
		sql.append("begin\n");
		sql.append("select \n");
		sql.append(getColumns());
		sql.append("from\n");
		sql.append(deliverTicketTable);
		sql.append(" as t\n");
		sql.append(" where 1=1 \n");
		if(key.getDateBegin()>0)
		{
			sql.append(" and (t.createDate>@dateBegin or t.createDate=@dateBegin) \n");
		}
		if(key.getDateEnd()>0)
		{
			sql.append(" and (t.createDate<@dateEnd or t.createDate=@dateEnd) \n");
		}
		sql.append(getSearchSql(key));
		sql.append(getOrderSql(key));
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);
		db.setArgumentValues(DateUtil.getDayStartTime(key.getDateBegin()),DateUtil.getDayEndTime(key.getDateEnd()));
		RecordSet rs = db.executeQueryLimit(key.getOffset(), key.getCount());
		while (rs.next()) {
			list.add(fillDeliverTicket(context, rs));
		}
		return list;
	}

	private static DeliverTicketItem fillDeliverTicket(Context context, RecordSet rs) {
		DeliverTicketItemImpl impl = new DeliverTicketItemImpl();
		int index = 0;
		impl.setId(rs.getFields().get(index++).getGUID());
		impl.setSheetNo(rs.getFields().get(index++).getString());
		impl.setOnlineOrderId(rs.getFields().get(index++).getGUID());
		impl.setOnlineOrderNo(rs.getFields().get(index++).getString());
		impl.setMemberId(rs.getFields().get(index++).getGUID());
		impl.setMemberRealName(rs.getFields().get(index++).getString());
		impl.setMobilePhone(rs.getFields().get(index++).getString());
		impl.setStationId(rs.getFields().get(index++).getGUID());
		impl.setStationName(rs.getFields().get(index++).getString());
		impl.setRemark(rs.getFields().get(index++).getString());
		impl.setDisAmount(rs.getFields().get(index++).getDouble());
		impl.setTotalAmount(rs.getFields().get(index++).getDouble());
		impl.setCreatorId(rs.getFields().get(index++).getGUID());
		impl.setCreator(rs.getFields().get(index++).getString());
		impl.setCreateDate(rs.getFields().get(index++).getDate());
		impl.setAddress(rs.getFields().get(index++).getString());

		return impl;
	}

	private static StringBuffer getColumns() {
		StringBuffer sql = new StringBuffer();

		sql.append("t.RECID as id,");
		sql.append("t.sheetNo as sheetNo,");
		sql.append("t.onlineOrderId as onlineOrderId,");
		sql.append("t.onlineOrderNo as onlineOrderNo,");
		sql.append("t.memberId as memberId,");
		sql.append("t.memberRealName as memberRealName,");
		sql.append("t.mobilePhone as mobilePhone,");
		sql.append("t.stationId as stationId,");
		sql.append("t.stationName as stationName,");
		sql.append("t.remark as remark,");
		sql.append("t.disAmount as disAmount,");
		sql.append("t.totalAmount as totalAmount,");
		sql.append("t.creatorId as creatorId,");
		sql.append("t.creator as creator,");
		sql.append("t.createDate as createDate,");
		sql.append("t.address as address,");
		sql.append("t.status as status\n");

		return sql;
	}

	private static StringBuffer getOrderSql(GetDeliverTicketListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSortField()) {
			sql.append(" order by ").append(key.getSortField().getFieldName());
			if (null != key.getSortType()) {
				sql.append(" ").append(key.getSortType()).append("\n");
			}
		} else {
			sql.append(" order by t.createDate asc \n");
		}
		return sql;
	}

	private static StringBuffer getSearchSql(GetDeliverTicketListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSearchText()) {
			String searchText = key.getSearchText().trim();
			sql.append(" and (");

			sql.append(" t.sheetNo like '%").append(searchText).append("%' ");
			sql.append(" or t.memberRealName like '%").append(searchText).append("%' ");
			sql.append(" or t.stationName like '%").append(searchText).append("%' ");

			sql.append(")\n");
		}
		return sql;
	}

	public static DeliverTicketInfo getDeliverInfo(Context context, DeliverTicketEntity e) throws Throwable {
		DeliverTicketInfoImpl impl = new DeliverTicketInfoImpl();

		new BeanUtils().copyObject(e, impl);

		List<OnlineOrderInfoItem> list = context.getList(OnlineOrderInfoItem.class, e.getOnlineOrderId());
		if (null != list && list.size() > 0) {
			DeliverTicketInfoItemImpl[] ds = new DeliverTicketInfoItemImpl[list.size()];

			for (int j = 0; j < list.size(); j++) {
				OnlineOrderInfoItem od = list.get(j);
				DeliverTicketInfoItemImpl d = new DeliverTicketInfoItemImpl();
				d.setCount(od.getCount());
				d.setGoodsId(od.getGoodsId());
				d.setGoodsName(od.getGoodsName());
				d.setGoodsSpec(od.getGoodsSpec());
				d.setGoodsNo(od.getGoodsNo());
				d.setAmount(od.getAmount());
				d.setCount(od.getCount());
				d.setDisAmount(od.getDisAmount());
				// d.setDisRate(od.get)
				d.setGoodsCode(od.getGoodsCode());
				d.setGoodsScale(od.getGoodsScale());
				d.setId(od.getId());
				d.setPrice(od.getPrice());
				d.setTicketId(e.getId());
				d.setUnit(od.getUnit());
				ds[j] = d;
			}
			impl.setItems(ds);
		}
		return impl;
	}

}
