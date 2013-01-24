package com.spark.psi.account.service.pay;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.psi.account.intf.entity.pay.PaymentEntity;
import com.spark.psi.account.intf.entity.pay.PaymentItemEntity;
import com.spark.psi.account.intf.entity.pay.PaymentLogEntity;
import com.spark.psi.account.intf.publish.entity.PaymentInfoImpl;
import com.spark.psi.account.intf.publish.entity.PaymentInfoItemImpl;
import com.spark.psi.account.intf.publish.entity.PaymentItemImpl;
import com.spark.psi.account.intf.publish.entity.PaymentLogImpl;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.PaymentStatus;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.account.entity.PaymentInfo;
import com.spark.psi.publish.account.entity.PaymentItem;
import com.spark.psi.publish.account.key.GetPaymentListKey;

public final class PaymentServiceUtil {

	final static String paymentTable = ERPTableNames.Account.Payment.getTableName();
	final static String paymentDetTable = ERPTableNames.Account.Payment_Det.getTableName();
	final static String paymentLogTable = ERPTableNames.Account.Payment_Log.getTableName();

	public static List<PaymentItem> getPaymentItemList(Context context, GetPaymentListKey key) {
		List<PaymentItem> list = new ArrayList<PaymentItem>();

		StringBuffer sql = new StringBuffer();
		sql.append("define query getOrderItemList(\n");
		sql.append("@startTime date,@endTime date");
		sql.append(")\n");
		sql.append("begin\n");
		sql.append("select \n");
		sql.append(getColumns());
		sql.append("from\n");
		sql.append(paymentTable);
		sql.append(" as t\n");
		sql.append(" where 1=1 \n");
		sql.append(getStatusSql(key));
		sql.append(getSearchSql(key));
		if (CheckIsNull.isNotEmpty(key.getQueryTerm())) {
			sql.append(" and (t.createDate>@startTime").append(" or t.createDate=@startTime").append(")\n");
			sql.append(" and (t.createDate<@endTime").append(" or t.createDate=@endTime").append(")\n");
		}
		sql.append(getOrderSql(key));
		sql.append(" end");

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
			list.add(fillPayment(context, rs));
		}
		return list;
	}

	private static PaymentItem fillPayment(Context context, RecordSet rs) {

		PaymentItemImpl impl = new PaymentItemImpl();
		int index = 0;
		impl.setId(rs.getFields().get(index++).getGUID());

		impl.setPaymentNo(rs.getFields().get(index++).getString());
		impl.setPartnerName(rs.getFields().get(index++).getString());
		impl.setPartnerId(rs.getFields().get(index++).getGUID());
		impl.setPaymentType(PaymentType.getPaymentType(rs.getFields().get(index++).getString()));
		impl.setDenyReason(rs.getFields().get(index++).getString());
		impl.setPayDate(rs.getFields().get(index++).getDate());
		impl.setStatus(rs.getFields().get(index++).getString());
		impl.setAmount(rs.getFields().get(index++).getDouble());
		impl.setPaidAmount(rs.getFields().get(index++).getDouble());
		impl.setRemark(rs.getFields().get(index++).getString());
		impl.setApprovePerson(rs.getFields().get(index++).getGUID());
		impl.setApprovePersonName(rs.getFields().get(index++).getString());
		impl.setApproveDate(rs.getFields().get(index++).getDate());
		impl.setCreatorId(rs.getFields().get(index++).getGUID());
		impl.setCreator(rs.getFields().get(index++).getString());
		impl.setCreateDate(rs.getFields().get(index++).getDate());

		return impl;
	}

	private static Object getOrderSql(GetPaymentListKey key) {
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

	private static Object getSearchSql(GetPaymentListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSearchText()) {
			String searchText = key.getSearchText().trim();
			sql.append(" and (");

			sql.append(" t.paymentNo like '%").append(searchText).append("%' ");
			sql.append(" or t.partnerName like '%").append(searchText).append("%' ");

			sql.append(")\n");
		}
		return sql;
	}

	private static Object getStatusSql(GetPaymentListKey key) {
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
		sql.append("t.paymentNo as paymentNo,");
		sql.append("t.partnerName as partnerName,");
		sql.append("t.partnerId as partnerId,");
		sql.append("t.paymentType as paymentType,");
		sql.append("t.denyReason as denyReason,");
		sql.append("t.payDate as payDate,");
		sql.append("t.status as status,");
		sql.append("t.amount as amount,");
		sql.append("t.paidAmount as paidAmount,");
		sql.append("t.remark as remark,");
		sql.append("t.approvePerson as approvePerson,");
		sql.append("t.approvePersonName as approvePersonName,");
		sql.append("t.approveDate as approveDate,");
		sql.append("t.creatorId as creatorId,");
		sql.append("t.creator as creator,");
		sql.append("t.createDate as createDate \n");

		return sql;
	}

	public static PaymentInfo getPaymentInfo(Context context, PaymentEntity e, List<PaymentItemEntity> itemList, List<PaymentLogEntity> logList) {
		PaymentInfoImpl i = new PaymentInfoImpl();

		i.setAmount(e.getAmount());
		i.setApproveDate(e.getApproveDate());
		i.setApprovePerson(e.getApprovePerson());
		i.setApprovePersonName(e.getApprovePersonName());
		i.setCreator(e.getCreator());
		i.setCreatorId(e.getCreatorId());
		i.setId(e.getId());
		i.setPaidAmount(e.getPaidAmount());
		i.setPartnerId(e.getPartnerId());
		i.setPartnerName(e.getPartnerName());
		i.setPayDate(e.getPayDate());
		i.setPaymentNo(e.getPaymentNo());
		i.setPaymentType(PaymentType.getPaymentType(e.getPaymentType()));
		i.setDenyReason(e.getDenyReason());
		i.setStatus(PaymentStatus.getPaymentStatus(e.getStatus()));
		i.setRemark(e.getRemark());
		i.setDealingsWay(DealingsWay.getDealingsWay(e.getDealingsWay()));
		
		PaymentInfoItemImpl[] items = new PaymentInfoItemImpl[itemList.size()];
		PaymentLogImpl[] logs = new PaymentLogImpl[logList.size()];
		for (int j = 0; j < itemList.size(); j++) {
			PaymentItemEntity ie = itemList.get(j);
			PaymentInfoItemImpl ii = new PaymentInfoItemImpl();
			try {
				new BeanUtils().copyProperties(ie, ii);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			items[j] = ii;
		}
		for (int k = 0; k < logList.size(); k++) {
			PaymentLogEntity ie = logList.get(k);
			PaymentLogImpl ii = new PaymentLogImpl();
			try {
				new BeanUtils().copyProperties(ie, ii);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			logs[k] = ii;
		}
		i.setItems(items);
		i.setLogs(logs);
		return i;
	}

}
