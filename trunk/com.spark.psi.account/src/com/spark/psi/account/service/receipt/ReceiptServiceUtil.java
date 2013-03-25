package com.spark.psi.account.service.receipt;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.psi.account.intf.entity.receipt.ReceiptEntity;
import com.spark.psi.account.intf.entity.receipt.ReceiptItemEntity;
import com.spark.psi.account.intf.entity.receipt.ReceiptLogEntity;
import com.spark.psi.account.intf.publish.entity.ReceiptInfoImpl;
import com.spark.psi.account.intf.publish.entity.ReceiptInfoItemImpl;
import com.spark.psi.account.intf.publish.entity.ReceiptItemImpl;
import com.spark.psi.account.intf.publish.entity.ReceiptLogImpl;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.ReceiptType;
import com.spark.psi.publish.account.entity.ReceiptInfo;
import com.spark.psi.publish.account.entity.ReceiptItem;
import com.spark.psi.publish.account.entity.ReceiptListEntity;
import com.spark.psi.publish.account.key.GetReceiptListKey;

public final class ReceiptServiceUtil {

	final static String receiptTable = ERPTableNames.Account.Receipts.getTableName();
	final static String receiptDetTable = ERPTableNames.Account.Receipts_Det.getTableName();
	final static String receiptLogTable = ERPTableNames.Account.Receipts_Log.getTableName();

	public static ReceiptListEntity getReceiptItemList(Context context, GetReceiptListKey key) {
		List<ReceiptItem> list = new ArrayList<ReceiptItem>();

		StringBuffer sql = new StringBuffer();
		sql.append("define query getOrderItemList(\n");
		sql.append("@startTime date,@endTime date");
		sql.append(")\n");
		sql.append("begin\n");
		sql.append("select \n");
		sql.append(getColumns());
		sql.append("from\n");
		sql.append(receiptTable);
		sql.append(" as t\n");
		sql.append(" where 1=1 \n");
		sql.append(getStatusSql(key));
		sql.append(getSearchSql(key));
		if (CheckIsNull.isNotEmpty(key.getQueryTerm())) {
			sql.append(" and (t.createDate>@startTime").append(" or t.createDate=@startTime").append(")\n");
			sql.append(" and (t.createDate<@endTime").append(" or t.createDate=@endTime").append(")\n");
		}
		sql.append(getOrderSql(key));
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);
		if (CheckIsNull.isNotEmpty(key.getQueryTerm())) {
			db.setArgumentValues(key.getQueryTerm().getStartTime(),key.getQueryTerm().getEndTime());
		}
		RecordSet rs = null;
		if (key.getCount() > 0) {
			rs =  db.executeQueryLimit(key.getOffset(), key.getCount());
		} else {
			rs = db.executeQuery();
		}
		while (rs.next()) {
			list.add(fillReceipt(context, rs));
		}
		
		StringBuffer sql1 = new StringBuffer();
		sql1.append("define query getOrderItemList(\n");
		sql1.append("@startTime date,@endTime date");
		sql1.append(")\n");
		sql1.append("begin\n");
		sql1.append("select \n");
		sql1.append(" count(t.recid) as c,sum(t.amount) as totalamount ");
		sql1.append("from\n");
		sql1.append(receiptTable);
		sql1.append(" as t\n");
		sql1.append(" where 1=1 \n");
		sql1.append(getStatusSql(key));
		sql1.append(getSearchSql(key));
		if (CheckIsNull.isNotEmpty(key.getQueryTerm())) {
			sql1.append(" and (t.createDate>@startTime").append(" or t.createDate=@startTime").append(")\n");
			sql1.append(" and (t.createDate<@endTime").append(" or t.createDate=@endTime").append(")\n");
		}
//		sql1.append(getOrderSql(key));
		sql1.append("end");

		DBCommand db1 = context.prepareStatement(sql1);
		if (CheckIsNull.isNotEmpty(key.getQueryTerm())) {
			db1.setArgumentValues(key.getQueryTerm().getStartTime(),key.getQueryTerm().getEndTime());
		}
		RecordSet rs1 = db1.executeQuery();
		int totalCount = 0;
		double totalAmount = 0;
		while (rs1.next()) {
			totalCount = rs1.getFields().get(0).getInt();
			totalAmount = rs1.getFields().get(1).getDouble();
		}
		ReceiptListEntity entity = new ReceiptListEntity(list, totalCount);
		entity.setTotalAmount(totalAmount);
		return entity;
	}

	private static ReceiptItem fillReceipt(Context context, RecordSet rs) {

		ReceiptItemImpl impl = new ReceiptItemImpl();
		int index = 0;
		impl.setId(rs.getFields().get(index++).getGUID());
		impl.setReceiptsNo(rs.getFields().get(index++).getString());
		impl.setPartnerName(rs.getFields().get(index++).getString());
		impl.setPartnerId(rs.getFields().get(index++).getGUID());
		impl.setReceiptMode(DealingsWay.getDealingsWay(rs.getFields().get(index++).getString()));
		impl.setReason(rs.getFields().get(index++).getString());
		impl.setReceiptDate(rs.getFields().get(index++).getDate());
		impl.setStatus(ReceiptStatus.getReceiptStatus(rs.getFields().get(index++).getString()));
		impl.setAmount(rs.getFields().get(index++).getDouble());
		impl.setReceiptedAmount(rs.getFields().get(index++).getDouble());
		impl.setRemark(rs.getFields().get(index++).getString());
		impl.setCreatorId(rs.getFields().get(index++).getGUID());
		impl.setCreator(rs.getFields().get(index++).getString());
		impl.setCreateDate(rs.getFields().get(index++).getDate());
		impl.setReceiptType(ReceiptType.getReceiptType(rs.getFields().get(index++).getString()));

		return impl;
	}

	private static Object getOrderSql(GetReceiptListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSortField()) {
			sql.append(" order by ").append(key.getSortField().getFieldName());
			if (null != key.getSortType()) {
				sql.append(" ").append(key.getSortType()).append("\n");
			}
		} else {
			sql.append(" order by t.createDate desc ");
		}
		return sql;
	}

	private static Object getSearchSql(GetReceiptListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSearchText()) {
			String searchText = key.getSearchText().trim();
			sql.append(" and (");

			sql.append(" t.receiptsNo like '%").append(searchText).append("%' ");
			sql.append(" or t.partnerName like '%").append(searchText).append("%' ");

			sql.append(")\n");
		}
		return sql;
	}

	private static Object getStatusSql(GetReceiptListKey key) {
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

		sql.append("t.recid as id,\n");
		sql.append("t.receiptsNo as receiptsNo,\n");
		sql.append("t.partnerName as partnerName,\n");
		sql.append("t.partnerId as partnerId,\n");
		sql.append("t.receiptMode as receiptMode,\n");
		sql.append("t.reason as reason,\n");
		sql.append("t.receiptDate as receiptDate,\n");
		sql.append("t.status as status,\n");
		sql.append("t.amount as amount,\n");
		sql.append("t.receiptedAmount as receiptedAmount,\n");
		sql.append("t.remark as remark,\n");
		sql.append("t.creatorId as creatorId,\n");
		sql.append("t.creator as creator,\n");
		sql.append("t.createDate as createDate,\n");
		sql.append("t.receiptType as receiptType\n");

		return sql;
	}

	public static ReceiptInfo getReceiptInfo(Context context, ReceiptEntity e, List<ReceiptItemEntity> itemList, List<ReceiptLogEntity> logList)
			throws Exception {
		ReceiptInfoImpl i = new ReceiptInfoImpl();

		i.setAmount(e.getAmount());
		i.setCreateDate(e.getCreateDate());
		i.setCreator(e.getCreator());
		i.setCreatorId(e.getCreatorId());
		i.setId(e.getId());
		i.setPartnerId(e.getPartnerId());
		i.setPartnerName(e.getPartnerName());
		i.setReceiptMode(DealingsWay.getDealingsWay(e.getReceiptMode()));
		i.setReceiptDate(e.getReceiptDate());
		i.setReceiptedAmount(e.getReceiptedAmount());
		i.setReceiptsNo(e.getReceiptsNo());
		i.setReceiptType(ReceiptType.getReceiptType(e.getReceiptType()));
		i.setRemark(e.getRemark());
		i.setStatus(ReceiptStatus.getReceiptStatus(e.getStatus()));

		ReceiptInfoItemImpl[] items = new ReceiptInfoItemImpl[itemList.size()];
		ReceiptLogImpl[] logs = new ReceiptLogImpl[logList.size()];
		for (int j = 0; j < itemList.size(); j++) {
			ReceiptItemEntity ie = itemList.get(j);
			ReceiptInfoItemImpl ii = new ReceiptInfoItemImpl();

			new BeanUtils().copyProperties(ie, ii);

			items[j] = ii;
		}
		for (int k = 0; k < logList.size(); k++) {
			ReceiptLogEntity ie = logList.get(k);
			ReceiptLogImpl ii = new ReceiptLogImpl();

			new BeanUtils().copyProperties(ie, ii);

			logs[k] = ii;
		}
		i.setItems(items);
		i.setLogs(logs);
		return i;
	}

}
